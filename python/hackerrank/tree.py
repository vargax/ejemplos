from __future__ import annotations
from dataclasses import dataclass

@dataclass
class Node:
    data: int
    height: int = 0
    parent: Node | Node = None
    left: Node | None = None
    right: Node | None = None

    @property
    def _balance_factor(self) -> int:
        left_height = self.left.height if self.left else 0
        right_height = self.right.height if self.right else 0
        return left_height - right_height

    def _balance(self, ancestors: list[Node]) -> None:

        if not (-1 <= self._balance_factor <= 1):
            if self.right and self.right.right:
                pass

            pass

        if self._balance_factor == -2:
            # left rotation
            pass

        if self._balance_factor == 2:
            # right rotation
            pass

    def insert(self, data: int) -> None:
        stack: list[Node | None] = [self]

        while True:
            current = stack.pop()
            if data <= current.data:
                if current.left is None:
                    break
                stack.append(current)
                stack.append(current.left)
            else:
                if current.right is None:
                    break
                stack.append(current)
                stack.append(current.right)

        parent = stack.pop()
        node = Node(data, height=parent.height+1)
        if data <= parent.data:
            parent.left = node
        else:
            parent.right = node

        parent._balance(stack)

    def in_order(self) -> list[int]:
        result: list[int] = []
        stack: list[Node | None] = [self]

        while True:
            current = stack.pop()
            if current is not None:
                stack.append(current)
                stack.append(current.left)
                continue

            if not stack:
                break

            current = stack.pop()
            result.append(current.data)
            stack.append(current.right)

        return result

expenditure = [2, 3, 4, 2, 3, 6, 8, 4, 5]

root = Node(expenditure.pop())
for i in expenditure:
    root.insert(i)

in_order = root.in_order()
pass
