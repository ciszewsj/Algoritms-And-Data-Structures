package pl.edu.pw.ee;

import com.sun.nio.sctp.IllegalUnbindException;

import static pl.edu.pw.ee.Color.BLACK;
import static pl.edu.pw.ee.Color.RED;

public class RedBlackTree<K extends Comparable<K>, V> {

	private Node<K, V> root;

	private String notElementFound = "Not element with key in map";
	private String emptyListPrint = "Could not print empty map";

	public V get(K key) {
		validateKey(key);
		Node<K, V> node = root;

		V result = null;

		while (node != null) {

			if (shouldCheckOnTheLeft(key, node)) {
				node = node.getLeft();

			} else if (shouldCheckOnTheRight(key, node)) {
				node = node.getRight();

			} else {
				result = node.getValue();
				break;
			}
		}
		if (node == null) {
			throw new IllegalStateException(notElementFound);
		} else if (node.getKey() != key) {
			throw new IllegalStateException(notElementFound);
		}

		return result;
	}

	public int put(K key, V value) {
		validateParams(key, value);
		int deep = putToRoot(key, value);
		reorganizeTree(root, null);
		return deep;
	}

	private void validateParams(K key, V value) {
		if (key == null || value == null) {
			System.out.println(key + "   " + value);
			throw new IllegalArgumentException("Input params (key, value) cannot be null.");
		}
	}

	private void reorganizeTree(Node<K, V> node, Node<K, V> parent) {
		if (node != null) {
			if (node.getLeft() != null) {
				reorganizeTree(node.getLeft(), node);
			} else if (node.getRight() != null) {
				reorganizeTree(node.getRight(), node);
			}
			if (isRed(node.getLeft()) && isRed(node.getLeft().getLeft())) {
				Node<K, V> pom = node.getLeft();
				node.setLeft(pom.getRight());
				pom.setRight(node);
				pom.setColor(BLACK);
				node.setColor(RED);
				changeNode(node, parent, pom);
			}
			if (isBlack(node.getLeft()) && isRed(node.getRight())) {
				Node<K, V> pom = node.getRight();
				node.setRight(pom.getLeft());
				pom.setLeft(node);
				changeNode(node, parent, pom);
			}

			if (isRed(node.getLeft()) && isRed(node.getRight())) {
				node.setColor(RED);
				node.getLeft().setColor(BLACK);
				node.getRight().setColor(BLACK);
			}
		}
	}

	private void changeNode(Node<K, V> node, Node<K, V> parent, Node<K, V> pom) {
		if (parent != null) {
			if (parent.getLeft().getValue().equals(node.getValue())) {
				parent.setLeft(pom);
			} else {
				parent.setRight(pom);
			}
		} else {
			root = pom;
			root.setColor(BLACK);
		}
//		reorganizeTree(pom, parent);
	}


	private boolean isBlack(Node<K, V> node) {
		return !isRed(node);
	}

	private boolean isRed(Node<K, V> node) {
		return node != null && node.isRed();
	}

	private int putToRoot(K key, V value) {
		int deep = 1;
		if (root == null) {
			root = new Node<>(key, value);
		} else {
			Node<K, V> current = root;
			do {
				if (key.compareTo(current.getKey()) == 0) {
					throw new IllegalStateException("Object with this key is already in map");
				} else if (key.compareTo(current.getKey()) < 0) {
					if (current.getLeft() == null) {
						current.setLeft(new Node<>(key, value));
						break;
					} else {
						current = current.getLeft();
					}
				} else {
					if (current.getRight() == null) {
						current.setRight(new Node<>(key, value));
						break;
					} else {
						current = current.getRight();

					}
				}
				deep += 1;
			} while (true);
		}
		return deep;
	}

	private void validateKey(K key) {
		if (key == null) {
			throw new IllegalArgumentException("Key cannot be null.");
		}
	}

	private boolean shouldCheckOnTheLeft(K key, Node<K, V> node) {
		return key.compareTo(node.getKey()) < 0;
	}

	private boolean shouldCheckOnTheRight(K key, Node<K, V> node) {
		return key.compareTo(node.getKey()) > 0;
	}

	public void deleteMax() {
		if (root == null) {
			throw new IllegalStateException("Could not remove element. Map is empty");
		}
		root.setColor(RED);
		deorganizeTree(root, null);
		removeMaxElement(root, null);
		reorganizeTree(root, null);
	}

	private void deorganizeTree(Node<K, V> node, Node<K, V> parent) {
		if (node.getLeft() != null && node.getRight() != null) {
			if (isBlack(node.getLeft()) && isRed(node.getRight())) {
				node.getLeft().getLeft().setColor(RED);
				node.getRight().getLeft().setColor(RED);
				deorganizeTree(node.getRight(), node);
			} else if (isRed(node.getLeft()) && isBlack(node.getRight())) {
				if (isBlack(node.getLeft().getRight()) && node.getLeft().getRight() != null &&
						isBlack(node.getLeft().getLeft()) && node.getLeft().getLeft() != null
				) {
					node.getLeft().setColor(BLACK);
					node.getLeft().getLeft().setColor(RED);
					node.getLeft().getRight().setColor(RED);
					deorganizeTree(node, parent);
				} else {
					throw new IllegalUnbindException("WTF ?");
				}
			}
		} else if (node.getLeft() != null) {
			node.getLeft().setRight(node);
			if (parent != null) {
				parent.setRight(node.getLeft());
			} else {
				root = node.getLeft();
			}
			node.setLeft(null);
		}
	}

	private void removeMaxElement(Node<K, V> node, Node<K, V> parent) {
		if (node.getRight() != null) {
			removeMaxElement(node.getRight(), node);
		} else if (parent != null) {
			parent.setRight(null);
		} else {
			root = null;
		}
	}

	public String getPreOrder() {
		if (root == null) {
			throw new IllegalStateException(emptyListPrint);
		}
		return getPreOrder(root);
	}

	private String getPreOrder(Node<K, V> node) {
		String text = "";
		if (node.getLeft() != null) {
			text = addStrings(text, getInOrder(node.getLeft()));
		}
		if (node.getRight() != null) {
			text = addStrings(text, getInOrder(node.getRight()));
		}
		text = addStrings(text, node.toString());
		return text;
	}


	public String getInOrder() {
		if (root == null) {
			throw new IllegalStateException(emptyListPrint);
		}
		return getInOrder(root);
	}

	private String getInOrder(Node<K, V> node) {
		String text = "";
		if (node.getLeft() != null) {
			text = addStrings(text, getInOrder(node.getLeft()));
		}
		text = addStrings(text, node.toString());
		if (node.getRight() != null) {
			text = addStrings(text, getInOrder(node.getRight()));
		}
		return text;
	}


	public String getPostOrder() {
		if (root == null) {
			throw new IllegalStateException(emptyListPrint);
		}
		return getPostOrder(root);
	}

	private String getPostOrder(Node<K, V> node) {
		String text = "";
		text = addStrings(text, node.toString());
		if (node.getLeft() != null) {
			text = addStrings(text, getInOrder(node.getLeft()));
		}
		if (node.getRight() != null) {
			text = addStrings(text, getInOrder(node.getRight()));
		}
		return text;
	}

	private String addStrings(String s1, String s2) {
		if (s1.length() > 0) {
			if (s2.length() > 0) {
				return s1 + " " + s2;
			}
			return s1;
		}
		return s2;
	}

}
