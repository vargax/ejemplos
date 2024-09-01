#!/bin/python3

import os

#
# Complete the 'arrayManipulation' function below.
#
# The function is expected to return a LONG_INTEGER.
# The function accepts following parameters:
#  1. INTEGER n
#  2. 2D_INTEGER_ARRAY queries
#

def array_manipulation(n, queries):
    a = [0] * n

    for q in queries:
        if q[2] == 0:
            continue
        for i in range(q[0] - 1, q[1]):
            a[i] += q[2]

    return max(a)


if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    first_multiple_input = input().rstrip().split()

    n = int(first_multiple_input[0])

    m = int(first_multiple_input[1])

    queries = []

    for _ in range(m):
        queries.append(list(map(int, input().rstrip().split())))

    result = array_manipulation(n, queries)

    fptr.write(str(result) + '\n')

    fptr.close()
