"""https://www.hackerrank.com/challenges/one-week-preparation-kit-merge-two-sorted-linked-lists/submissions/code/399768256"""

class SinglyLinkedListNode:
    def __init__(self, node_data):
        self.data = node_data
        self.next = None

def merge_lists(head_1: SinglyLinkedListNode, head_2: SinglyLinkedListNode) -> SinglyLinkedListNode:
    # Border case: there is an empty list
    if head_1 is None:
        return head_2

    if head_2 is None:
        return head_1

    # What list has is the lowest head?
    if head_1.data <= head_2.data:
        main_list = head_1
        other_list = head_2
    else:
        main_list = head_2
        other_list = head_1

    # Merge `other_list` into `list`
    current_node = main_list
    candidate = other_list

    while current_node and candidate:

        # End case: `current_node` is the last one
        if not current_node.next:
            current_node.next = candidate
            break

        if current_node.data <= candidate.data <= current_node.next.data:
            # save reference to next candidate
            new_candidate = candidate.next

            # merge candidate into `main_list`
            candidate.next = current_node.next
            current_node.next = candidate

            # update candidate
            candidate = new_candidate
        else:
            # move to the next node in the `main_list`
            current_node = current_node.next

    return main_list
