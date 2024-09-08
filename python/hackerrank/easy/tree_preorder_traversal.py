"""https://www.hackerrank.com/challenges/tree-preorder-traversal/submissions/code/399829820"""

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


def pre_order(root: Node) -> None:
    stack: list[Node | None] = [root]
    result = []

    while stack:
        current = stack.pop()

        if current is None:
            continue

        result.append(current.info)

        stack.append(current.right)
        stack.append(current.left)

    print(*result)


tree = BinarySearchTree()
t = int(input())

arr = list(map(int, input().split()))

for i in range(t):
    tree.create(arr[i])

pre_order(tree.root)