from random import uniform

def optimistic(n):
    T = []
    for i in range (0, n):
        T.append(i)
    return T

def unoptimistic(n):
    T = []
    for i in reversed(range (0, n)):
         T.append(i)
    return T

def random(n):
    T = []
    for i in reversed(range (0, n)):
         T.append(uniform(-100.0, 100.0))
    return T

def optimistic_quick_sort(n):
    return gen(n, n)

def gen(a, n):
    T =[]
    if (a >= 3):
        T.append(n)
        first = gen((a-1)/2, n - a / 2)
        first_el = first[0]
        first.remove(first_el)
        return T + first + [first_el]  + gen((a)/2, n+a/2) 
    elif ( a >= 2):
        T.append(n)
        T.append(n+1)
    else:
        T.append(n)
    return T

def pessimistic_heap_sort(n):
    T = []
    for i in range (0,n):
        T.append(1)
    return T


class heapSortPess:
    


def saveTAsFile(T, name):
    file = open("data_" + name + "_" + str(len(T)) + ".txt", "w")
    
    for num in T:
        file.write(str(num) + "\n")
    
    file.close()



a = [2, 10, 20, 50, 100, 200, 500, 1000, 2000, 5000, 10000, 20000, 50000, 100000]

if __name__ == "__main__":
    for i in a:
        saveTAsFile(random(i), "random")
        saveTAsFile(optimistic(i), "sorted")
        saveTAsFile(unoptimistic(i), "reversed_sorted")
        saveTAsFile(optimistic_quick_sort(i), "optimistic_quick_sort")
        saveTAsFile(pessimistic_heap_sort(i), "pessimistic_heap_sort")
