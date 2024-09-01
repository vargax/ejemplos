"""https://www.hackerrank.com/challenges/is-binary-search-tree/submissions/code/398907458"""

class Node:
    def __init__(self, data):
        self.data = data
        self.left = None
        self.right = None


def in_order_traversal(node: Node) -> list[int]:
    result: list[int] = []
    stack: list[Node | None] = [node]

    while stack:
        current = stack.pop()

        if current:
            stack.append(current)
            stack.append(current.left)
            continue

        if not stack:
            break

        current = stack.pop()
        result.append(current.data)
        stack.append(current.right)

    return result


def check_binary_search_tree_(root: Node):
    in_order = in_order_traversal(root)

    is_ordered = in_order == sorted(in_order)
    unique_elements = len(in_order) == len(set(in_order))

    return is_ordered and unique_elements