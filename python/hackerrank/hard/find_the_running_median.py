from __future__ import annotations

from dataclasses import dataclass

from hackerrank.hard.find_maximum_index_product.optimal import stack


@dataclass
class Node:
    data: int
    parent: Node | None = None
    left: Node | None = None
    right: Node | None = None

    def insert(self, data: int) -> None:

        # general case
        if self.is_full:
            if self.left.is_full:
                self.right.insert(data)
            else:
                self.left.insert(data)
            return

        # base case:
        node = Node(data, parent=self)
        if not self.left:
            self.left = node
        else:
            self.right = node

        node._bubble_up()


    def _bubble_up(self) -> None:
        if not self.parent:
            return

        if self.data < self.parent.data:
            self.parent.data, self.data = self.data, self.parent.data

        self.parent._bubble_up()

    @property
    def is_full(self) -> bool:
        return bool(self.left and self.right)


root = Node(4)
root.insert(50)
pass

root.insert(7)
pass

root.insert(55)
pass

root.insert(90)
pass

root.insert(87)
pass

root.insert(2)
pass