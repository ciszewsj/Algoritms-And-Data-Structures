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
        
def saveTAsFile(T, name):
    file = open("data_" + name + "_" + str(len(T)) + ".txt", "w")
    
    for num in T:
        file.write(str(num) + "\n")
    
    file.close()

a = [2, 10, 20, 50, 100, 200, 500, 1000, 2000, 5000, 10000, 20000, 50000, 100000]

if __name__ == "__main__":
    for i in a:
        saveTAsFile(random(i), "random")
        saveTAsFile(optimistic(i), "optimistic")
        saveTAsFile(unoptimistic(i), "unoptimistic")
