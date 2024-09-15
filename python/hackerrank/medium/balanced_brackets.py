#!/bin/python3

"""https://www.hackerrank.com/challenges/balanced-brackets/submissions/code/400372513"""

import math
import os
import random
import re
import sys

#
# Complete the 'isBalanced' function below.
#
# The function is expected to return a STRING.
# The function accepts STRING s as parameter.
#

def is_balanced(string: str) -> bool:
    stack = []
    for char in string:
        if char in ('(', '{', '['):
            stack.append(char)
            continue

        if char in (')', '}', ']'):
            if not stack:
                return False

            match char:
                case ')':
                    if stack.pop() == '(':
                        continue
                case '}':
                    if stack.pop() == '{':
                        continue
                case ']':
                    if stack.pop() == '[':
                        continue

            return False

    if stack:
        return False

    return True


def isBalanced(string: str) -> str:
    response = is_balanced(string)
    
    if response:
        return 'YES'
    
    return 'NO'
    

if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    t = int(input().strip())

    for t_itr in range(t):
        s = input()

        result = isBalanced(s)

        fptr.write(result + '\n')

    fptr.close()
