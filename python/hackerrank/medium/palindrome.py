#!/bin/python3

import math
import os
import random
import re
import sys


def is_palindrome(arr: list[str]) -> bool:
    size = len(arr)

    i = 0
    while i <= size // 2:
        if arr[i] != arr[size-1-i]:
            return False
        i += 1

    return True


def palindrome_index(string: str) -> int:
    # string to array of chars
    arr = list(string)
    size = len(arr)

    i = 0
    while i <= size // 2:
        left_index = i
        right_index = size - 1 - i

        if arr[left_index] != arr[right_index]:
            if arr[left_index] == arr[right_index - 1]:
                return right_index
            else:
                return left_index

        i += 1

    return -1


if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    q = int(input().strip())

    for q_itr in range(q):
        s = input()

        result = palindrome_index(s)

        fptr.write(str(result) + '\n')

    fptr.close()
