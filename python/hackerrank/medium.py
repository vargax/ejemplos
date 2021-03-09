#!/bin/python3
import random


def main():
    print(extra_long_factorials(25))


def extra_long_factorials(n):
    result = 1
    for i in range(2, n+1):
        result *= i
    return result


# Factors of a Number
def factors(number):
    bottom = [1]
    top = [number]

    i = 2
    while i < top[0]:
        if number % i == 0:
            bottom.append(i)
            top.insert(0, number // i)
        i += 1

    if bottom[-1] == top[0]:
        bottom.pop()
    results = bottom + top

    print('Iterations: ', i)
    print('Factors: ', len(results))
    print(results)


# Complete the minimumBribes function below.
def minimum_bribes(q):
    q = [i - 1 for i in q]

    total_bribes = 0
    me = len(q) - 1
    while me >= 0:
        my_tag = q[me]
        given_bribes = max(0, my_tag - me)

        if given_bribes > 2:
            return 'Too chaotic'

        received_bribes = 0
        for i in q[max(0, my_tag - 1):me]:
            if i > my_tag:
                received_bribes += 1

        total_bribes += received_bribes
        me -= 1

    return total_bribes


if __name__ == "__main__":
    main()
