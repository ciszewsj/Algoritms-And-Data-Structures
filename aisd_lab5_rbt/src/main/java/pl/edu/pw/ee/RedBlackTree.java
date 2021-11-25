package pl.edu.pw.ee;

import static pl.edu.pw.ee.Color.BLACK;
import static pl.edu.pw.ee.Color.RED;

public class RedBlackTree<K extends Comparable<K>, V> {

	private Node<K, V> root;

	private final String notElementFound = "Not element with key in map";
	private final String emptyListPrint = "Could not print empty map";

	private int putMethodDeep;

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

	public void put(K key, V value) {
		validateParams(key, value);
		putMethodDeep = 0;
		root = put(root, key, value);
		root.setColor(BLACK);
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

	private void validateParams(K key, V value) {
		if (key == null || value == null) {
			throw new IllegalArgumentException("Input params (key, value) cannot be null.");
		}
	}

	private Node<K, V> put(Node<K, V> node, K key, V value) {
		putMethodDeep += 1;

		if (node == null) {
			return new Node<>(key, value);
		}
		if (node.getKey().equals(key)) {
			throw new IllegalStateException("Argument with this key is already in map");
		}
		if (isKeyBiggerThanNode(key, node)) {
			putOnTheRight(node, key, value);

		} else if (isKeySmallerThanNode(key, node)) {
			putOnTheLeft(node, key, value);

		} else {
			node.setValue(value);
		}

		node = reorganizeTree(node);

		return node;
	}

	private boolean isKeyBiggerThanNode(K key, Node<K, V> node) {
		return key.compareTo(node.getKey()) > 0;
	}

	private void putOnTheRight(Node<K, V> node, K key, V value) {
		Node<K, V> rightChild = put(node.getRight(), key, value);
		node.setRight(rightChild);
	}

	private boolean isKeySmallerThanNode(K key, Node<K, V> node) {
		return key.compareTo(node.getKey()) < 0;
	}

	private void putOnTheLeft(Node<K, V> node, K key, V value) {
		Node<K, V> leftChild = put(node.getLeft(), key, value);
		node.setLeft(leftChild);
	}

	private Node<K, V> reorganizeTree(Node<K, V> node) {
		node = rotateLeftIfNeeded(node);
		node = rotateRightIfNeeded(node);
		changeColorsIfNeeded(node);

		return node;
	}

	private Node<K, V> rotateLeftIfNeeded(Node<K, V> node) {
		if (isBlack(node.getLeft()) && isRed(node.getRight())) {
			node = rotateLeft(node);
		}
		return node;
	}

	private Node<K, V> rotateLeft(Node<K, V> node) {
		Node<K, V> head = node.getRight();
		node.setRight(head.getLeft());
		head.setLeft(node);
		head.setColor(node.getColor());
		node.setColor(RED);
		return head;
	}

	private Node<K, V> rotateRightIfNeeded(Node<K, V> node) {
		if (isRed(node.getLeft()) && isRed(node.getLeft().getLeft())) {
			node = rotateRight(node);
		}
		return node;
	}

	private Node<K, V> rotateRight(Node<K, V> node) {
		Node<K, V> head = node.getLeft();
		node.setLeft(head.getRight());
		head.setRight(node);
		head.setColor(node.getColor());
		node.setColor(RED);
		return head;
	}

	private void changeColorsIfNeeded(Node<K, V> node) {
		if (isRed(node.getLeft()) && isRed(node.getRight())) {
			changeColors(node);
		}
	}

	private void changeColors(Node<K, V> node) {
		node.setColor(RED);
		node.getLeft().setColor(BLACK);
		node.getRight().setColor(BLACK);
	}

	private boolean isBlack(Node<K, V> node) {
		return !isRed(node);
	}

	private boolean isRed(Node<K, V> node) {
		return node != null && node.isRed();
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
			text = addStrings(text, getPreOrder(node.getLeft()));
		}
		if (node.getRight() != null) {
			text = addStrings(text, getPreOrder(node.getRight()));
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
			text = addStrings(text, getPostOrder(node.getLeft()));
		}
		if (node.getRight() != null) {
			text = addStrings(text, getPostOrder(node.getRight()));
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

	public void deleteMax() {
		if (root == null) {
			throw new IllegalStateException("Could not remove element. Map is empty");
		}
		root.setColor(RED);
		root = deorganizeTree(root);
		root = removeMaxElement(root);
		if (root != null) {
			root = reorganizeTree(root);
		}
	}

	private Node<K, V> removeMaxElement(Node<K, V> node) {
		if (node.getRight() == null) {

			return null;
		}
		node.setRight(removeMaxElement(node.getRight()));
		return node;
	}

	private Node<K, V> deorganizeTree(Node<K, V> node) {
		if (node.getLeft() == null && node.getRight() == null) {
			return node;
		}
		node = unrotateLeftIfNeeded(node);
		node = unrotatEndIfNeeded(node);
		unchangeColorsIfNeeded(node);

		node.setRight(deorganizeTree(node.getRight()));
		return node;
	}

	private Node<K, V> unrotateLeftIfNeeded(Node<K, V> node) {
		if (isRed(node.getLeft()) && isBlack(node.getRight())) {
			node = unrotateLeft(node);
		}
		return node;
	}

	private Node<K, V> unrotateLeft(Node<K, V> node) {
		Node<K, V> head = node.getLeft();
		node.setLeft(head.getRight());
		head.setRight(node);
		head.setColor(RED);
		node.setColor(BLACK);
		return head;
	}

	private Node<K, V> unrotatEndIfNeeded(Node<K, V> node) {
		if (node.getRight() == null && node.getLeft() != null) {
			node = unrotatEnd(node);
		}
		return node;
	}

	private Node<K, V> unrotatEnd(Node<K, V> node) {
		Node<K, V> head = node.getLeft();
		head.setRight(node);
		return head;
	}

	private void unchangeColorsIfNeeded(Node<K, V> node) {
		if (isRed(node) && isBlack(node.getLeft()) && node.getLeft() != null && node.getRight() != null && isBlack(node.getRight())) {
			unchangeColors(node);
		}
	}

	private void unchangeColors(Node<K, V> node) {
		node.setColor(BLACK);
		node.getLeft().setColor(RED);
		node.getRight().setColor(RED);
	}

	public int getPutDeep() {
		return putMethodDeep;
	}
}

