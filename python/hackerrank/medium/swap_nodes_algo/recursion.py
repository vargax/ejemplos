#!/bin/python3
from __future__ import annotations

import math
import os
import random
import re
import sys

from dataclasses import dataclass
from functools import cached_property
from collections import defaultdict


@dataclass
class Node:
    index: int
    parent: Node | None
    left: Node | None = None
    right: Node | None = None

    def swap(self) -> None:
        self.left, self.right = self.right, self.left

    @cached_property
    def deep(self):
        if self.parent:
            return self.parent.deep + 1
        return 1

    @property
    def in_order_traversal(self) -> list[int]:
        traversal = []

        if self.left:
            traversal += self.left.in_order_traversal

        traversal.append(self.index)

        if self.right:
            traversal += self.right.in_order_traversal

        return traversal


def build_tree(indexes: list[list[int]]) -> dict[int, Node]:
    nodes = {1: Node(1, None)}

    n = 1
    for index in indexes:

        current_node = nodes[n]
        left, right = index

        if left > 1:
            left_node = Node(left, parent=current_node)
            current_node.left = left_node
            nodes[left] = left_node

        if right > 1:
            right_node = Node(right, parent=current_node)
            current_node.right = right_node
            nodes[right] = right_node

        n += 1

    return nodes


def nodes_by_deep(nodes: dict[int, Node]) -> dict[int, list[Node]]:
    by_deep: dict[int, list[Node]] = defaultdict(list)

    for node in nodes.values():
        by_deep[node.deep].append(node)

    return by_deep


def swap_nodes(indexes: list[list[int]], queries: list[int]) -> list[list[int]]:
    nodes = build_tree(indexes)
    root_node = nodes[1]

    by_deep = nodes_by_deep(nodes)
    max_deep = max(by_deep)

    output = []
    for k in queries:

        i = 1
        h = k * i
        while h <= max_deep:
            for node in by_deep[h]:
                node.swap()
            i += 1
            h = k * i

        traversal = root_node.in_order_traversal
        output.append(traversal)

    return output


if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    n = int(input().strip())

    indexes = []

    for _ in range(n):
        indexes.append(list(map(int, input().rstrip().split())))

    queries_count = int(input().strip())

    queries = []

    for _ in range(queries_count):
        queries_item = int(input().strip())
        queries.append(queries_item)

    result = swap_nodes(indexes, queries)

    fptr.write('\n'.join([' '.join(map(str, x)) for x in result]))
    fptr.write('\n')

    fptr.close()
