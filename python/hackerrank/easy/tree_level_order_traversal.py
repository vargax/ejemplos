"""https://www.hackerrank.com/challenges/tree-level-order-traversal/submissions/code/400371107"""

class Node:
    def __init__(self, info):
        self.info = info
        self.left = None
        self.right = None
        self.level = None

    def __str__(self):
        return str(self.info)


class BinarySearchTree:
    def __init__(self):
        self.root = None

    def create(self, val):
        if self.root == None:
            self.root = Node(val)
        else:
            current = self.root

            while True:
                if val < current.info:
                    if current.left:
                        current = current.left
                    else:
                        current.left = Node(val)
                        break
                elif val > current.info:
                    if current.right:
                        current = current.right
                    else:
                        current.right = Node(val)
                        break
                else:
                    break


"""
Node is defined as
self.left (the left child of the node)
self.right (the right child of the node)
self.info (the value of the node)
"""

from collections import deque


def level_order(node: Node) -> str:
    queue = deque()

    level_order_ = []
    queue.append(node)
    while queue:
        current = queue.popleft()

        if not current:
            continue

        queue.append(current.left)
        queue.append(current.right)

        level_order_.append(str(current.info))

    response = ' '.join(level_order_)
    return response


def levelOrder(root: Node) -> None:
    level_order_traversal = level_order(root)
    print(level_order_traversal)


tree = BinarySearchTree()
t = int(input())

arr = list(map(int, input().split()))

for i in range(t):
    tree.create(arr[i])

levelOrder(tree.root)