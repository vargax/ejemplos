#!/bin/python3
"""https://www.hackerrank.com/challenges/find-maximum-index-product/submissions/code/256530656"""

import os

#
# Complete the 'solve' function below.
#
# The function is expected to return an INTEGER.
# The function accepts INTEGER_ARRAY arr as parameter.
#

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


if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    arr_count = int(input().strip())

    arr = list(map(int, input().rstrip().split()))

    result = solve(arr)

    fptr.write(str(result) + '\n')

    fptr.close()
