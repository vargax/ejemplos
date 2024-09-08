#!/bin/python3

"""https://www.hackerrank.com/challenges/truck-tour/submissions/code/399829642"""

from __future__ import annotations

import os
from dataclasses import dataclass


@dataclass
class Node:
    id: int
    balance: int
    next: Node | None = None


def truck_tour(petrolpumps):
    # must start in a pump with a positive balance
    initial_pumps: list[Node] = []

    # the first pump
    petrol, distance = petrolpumps[0]
    balance = petrol - distance
    head = Node(0, balance)
    if balance >= 0:
        initial_pumps.append(head)

    # build a linked list of pumps
    current = head
    for i in range(1, len(petrolpumps)):
        pump = petrolpumps[i]
        petrol, distance = pump

        balance = petrol - distance
        node = Node(i, balance)

        if balance >= 0:
            initial_pumps.append(node)

        current.next = node
        current = node

    # close the list
    current.next = head

    # locate the pump where we can start the pump
    for potential_head in initial_pumps:
        balance = potential_head.balance
        current = potential_head.next

        while current is not potential_head:
            balance += current.balance
            if balance < 0:
                break
            current = current.next

        if current is potential_head:
            return potential_head.id


if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    n = int(input().strip())

    petrolpumps = []

    for _ in range(n):
        petrolpumps.append(list(map(int, input().rstrip().split())))

    result = truck_tour(petrolpumps)

    fptr.write(str(result) + '\n')

    fptr.close()
