#!/bin/python3

"""https://www.hackerrank.com/challenges/find-maximum-index-product/submissions/code/256973797"""

import os

#
# Complete the 'solve' function below.
#
# The function is expected to return an INTEGER.
# The function accepts INTEGER_ARRAY arr as parameter.
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


if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    arr_count = int(input().strip())

    arr = list(map(int, input().rstrip().split()))

    result = solve(arr)

    fptr.write(str(result) + '\n')

    fptr.close()
