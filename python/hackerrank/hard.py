#!/bin/python3


# Complete the 'solve' function below.
#
# The function is expected to return an INTEGER.
# The function accepts INTEGER_ARRAY arr as parameter.

# OPTIMAL (based on Discussions)
#
def solve(a: [int]) -> int:
    s = stack()

    l: list[int] = [0] * len(a)
    for i in range(len(a)):
        while not s.isempty() and a[s.top()] <= a[i]:
            s.pop()
        if not s.isempty():
            l[i] = s.top() + 1
        s.push(i)

    s.clear()

    r: list[int] = [0] * len(a)
    for i in range(len(a) - 1, -1, -1):
        while not s.isempty() and a[s.top()] <= a[i]:
            s.pop()
        if not s.isempty():
            r[i] = s.top() + 1
        s.push(i)

    m = 0
    for i in range(len(a)):
        m = max(m, l[i] * r[i])

    return m


class stack:
    def __init__(self):
        self.s = []

    def top(self) -> int:
        return self.s[-1]

    def pop(self) -> int:
        return self.s.pop()

    def push(self, e: int) -> None:
        self.s.append(e)

    def isempty(self) -> bool:
        return not self.s

    def clear(self) -> None:
        self.s.clear()


# ORIGINAL
# https://www.hackerrank.com/challenges/find-maximum-index-product/submissions/code/256530656
def solve(a: [int]) -> int:
    m = []

    for i in range(len(a)):
        l = left(i, a)
        r = right(i, a)
        m.append(l * r)

    return max(m)


def left(i: int, a: [int]) -> int:
    j = i - 1
    while j >= 0:
        if a[j] > a[i]:
            return j + 1
        j -= 1
    return 0


def right(i: int, a: [int]) -> int:
    k = i + 1
    while k < len(a):
        if a[k] > a[i]:
            return k + 1
        k += 1
    return 0


# Complete the 'arrayManipulation' function below.
#
# The function is expected to return a LONG_INTEGER.
# The function accepts following parameters:
#  1. INTEGER n
#  2. 2D_INTEGER_ARRAY queries

# OPTIMAL (based on Discussions)
# https://www.hackerrank.com/challenges/crush/submissions/code/255502595
def arrayManipulation(n, queries):
    arr = [0] * n

    for q in queries:
        a, b, k = q[0] - 1, q[1], q[2]

        arr[a] += k
        if b < n:
            arr[b] -= k

    m = c = 0
    for i in arr:
        c += i
        if c > m:
            m = c

    return m


# ORIGINAL
# https://www.hackerrank.com/challenges/crush/submissions/code/255445013
def arrayManipulation(n, queries):
    a = [0] * n

    for q in queries:
        for i in range(q[0] - 1, q[1]):
            a[i] += q[2]

    return max(a)
