#!/bin/python3

"""https://www.hackerrank.com/challenges/contacts/submissions/code/400387952"""

from __future__ import annotations

import math
import os
import random
import re
import sys

from collections import deque
from dataclasses import dataclass, field


@dataclass
class Node:
    data: str | None = None
    count: int = 0
    descendants: dict[str, Node] = field(default_factory=dict)

    def add_contract(self, name: str) -> None:
        queue = deque(name)
        self._add_contract(queue)

    def _add_contract(self, queue: deque) -> None:

        # if we are here, it is because a contract will be added
        # either here or below, so let's incremente the count
        self.count += 1

        if not queue:
            # we are done
            return

        prefix = queue.popleft()

        if prefix in self.descendants:
            node = self.descendants[prefix]
        else:
            node = Node(prefix)
            self.descendants[prefix] = node

        node._add_contract(queue)

    def count_contracts(self, prefix: str) -> int:

        # find the node with the prefix
        queue = deque(prefix)
        current_node = self

        while queue:
            current_char = queue.popleft()
            if current_char in current_node.descendants:
                current_node = current_node.descendants[current_char]
                continue

            # there are no contracts with that prefix
            return 0

        # this is the node with the prefix, so return its count
        return current_node.count


def contacts(queries: list[list[str]]) -> list[int]:
    root = Node()
    counts = []

    for query in queries:
        action, parameter = query

        match action:
            case 'add':
                root.add_contract(parameter)
            case 'find':
                count = root.count_contracts(parameter)
                counts.append(count)

    return counts


if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    queries_rows = int(input().strip())

    queries = []

    for _ in range(queries_rows):
        queries.append(input().rstrip().split())

    result = contacts(queries)

    fptr.write('\n'.join(map(str, result)))
    fptr.write('\n')

    fptr.close()
