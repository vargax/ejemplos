#!/bin/python3
import random


def main():
    print(staircase(25))


# Complete the staircase function below.
def staircase(n):
    floor = n
    while floor > 0:
        step = ''
        for i in range(floor - 1):
            step += ' '
        for i in range(n + 1 - floor):
            step += '#'
        print(step)
        floor -= 1


# Complete the plusMinus function below.
def plus_minus(arr):
    l = len(arr)
    positives = sum([1 for i in arr if i > 0])
    negatives = sum([1 for i in arr if i < 0])
    zeros = sum([1 for i in arr if i == 0])

    print(positives / l)
    print(negatives / l)
    print(zeros / l)


# Complete the 'diagonalDifference' function below.
#  The function is expected to return an INTEGER.
#  The function accepts 2D_INTEGER_ARRAY arr as parameter.
def diagonal_difference(arr):
    d1, d2 = 0, 0
    l = len(arr)

    print(arr)

    for i in range(0, l):
        j = (l - 1) - i
        d1 += arr[i][i]
        d2 += arr[j][i]

    return abs(d1 - d2)


# Complete the compareTriplets function below.
def compare_triplets(a, b):
    comp = []
    for i in range(0, len(a)):
        comp.append(a[i] - b[i])

    a_wins = [1 for i in comp if i > 0]
    b_wins = [1 for i in comp if i < 0]
    return [sum(a_wins), sum(b_wins)]


def shuffle():
    deck = list(range(1, 13))
    print(deck)

    result = []
    while len(deck) > 0:
        rand = random.randint(0, len(deck) - 1)
        result.append(deck.pop(rand))

    print(result)


# Complete the anagram function below.
def anagram(s):
    if len(s) % 2 != 0:
        return -1

    half = len(s) // 2
    head, tail = list(s[:half]), list(s[half:])

    count = 0
    for char in head:
        try:
            tail.remove(char)
        except ValueError:
            count += 1

    return count


if __name__ == "__main__":
    main()
