#!/bin/python3

# https://www.hackerrank.com/challenges/tree-huffman-decoding/submissions/code/255757598
# Complete the decodeHuff function below.
def decodeHuff(root, ss):
    null = root.data

    d = []
    i = 0
    n = root

    while i < len(ss):
        while n.data == null:
            s = ss[i]

            if s == '0':
                n = n.left
            else:
                n = n.right

            i += 1

        d.append(n.data)
        n = root

    print(''.join(d))


# https://www.hackerrank.com/challenges/ctci-comparator-sorting/submissions/code/232534638
# Comparators are used to compare two objects. In this challenge you'll create a comparator and use it to sort an array.
class Player:
    def __init__(self, name, score):
        self.name = name
        self.score = score

    def __repr__(self):
        return f"{self.name} {self.score}"

    def comparator(self, other):
        if other.score == self.score:
            if other.name < self.name:
                return 1
            return -1
        return other.score - self.score


# https://www.hackerrank.com/challenges/extra-long-factorials/submissions/code/203469687
# Complete the extraLongFactorials function below.
def extraLongFactorials(n):
    result = 1
    for i in range(2, n + 1):
        result *= i
    print(result)


# https://www.hackerrank.com/challenges/ctci-ice-cream-parlor/submissions/code/189628352
# Complete the whatFlavors function below.
def whatFlavors(prices, money):
    prices_dic = {}
    flavor = 0
    while flavor < len(prices):
        price = prices[flavor]

        if prices_dic.get(price):
            (prices_dic.get(price)).append(flavor)
        else:
            prices_dic[price] = [flavor]

        flavor += 1

    for price, flavors in prices_dic.items():
        first_flavor = flavors[0] + 1
        remaining_money = money - price

        possible_flavors = prices_dic.get(remaining_money)
        if possible_flavors:
            second_flavor = possible_flavors[0] + 1

            if possible_flavors == flavors:
                if len(flavors) >= 2:
                    second_flavor = flavors[1] + 1
                else:
                    continue

            return str(first_flavor) + " " + str(second_flavor)


# https://www.hackerrank.com/challenges/ctci-recursive-staircase/submissions/code/188121002
# Complete the stepPerms function below.
STEPS_AT_TIME = [1, 2, 3]


class tree_node:
    cache = {}

    def __init__(self, steps):
        self.steps = steps

    def climb(self, remaining_steps):
        remaining_steps = remaining_steps - self.steps

        if tree_node.cache.get(remaining_steps):
            return tree_node.cache.get(remaining_steps)

        if remaining_steps < 0:
            return 0
        if remaining_steps == 0:
            return 1

        ways = 0
        for i in STEPS_AT_TIME:
            ways += tree_node(i).climb(remaining_steps)

        ways = ways % 10000000007
        tree_node.cache[remaining_steps] = ways

        return ways


def stepPerms(n):
    root = tree_node(0)
    return root.climb(n)


# https://www.hackerrank.com/challenges/minimum-swaps-2/submissions/code/184045864
# Complete the minimumSwaps function below.
def minimumSwaps(arr):
    arr = [i - 1 for i in arr]

    dic = {}
    i = 0
    while i < len(arr):
        if arr[i] != i:
            dic[arr[i]] = i
        i += 1

    swaps = 0
    for k, v in dic.items():
        if k != v:
            arr[v] = arr[k]
            dic[arr[k]] = v
            arr[k] = k
            dic[k] = k
            swaps += 1

    return swaps


# https://www.hackerrank.com/challenges/new-year-chaos/submissions/code/182889656
# Complete the minimumBribes function below.
def minimumBribes(q):
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
