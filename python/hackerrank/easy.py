#!/bin/python3

# https://www.hackerrank.com/challenges/birthday-cake-candles/submissions/code/203466986
# Complete the 'birthdayCakeCandles' function below.
#
# The function is expected to return an INTEGER.
# The function accepts INTEGER_ARRAY candles as parameter.
#
def birthdayCakeCandles(candles):
    tallest = max(candles)
    return sum([1 for i in candles if i == tallest])


# https://www.hackerrank.com/challenges/mini-max-sum/submissions/code/203466298
# Complete the miniMaxSum function below.
def miniMaxSum(arr):
    arr.sort()
    min_sum = sum(arr[:4])
    max_sum = sum(arr[1:])
    print(min_sum, max_sum)


# https://www.hackerrank.com/challenges/simple-array-sum/submissions/code/203464941
# Complete the simpleArraySum function below.
#
def simpleArraySum(ar):
    return sum(ar)


# https://www.hackerrank.com/challenges/staircase/submissions/code/202390294
# Complete the staircase function below.
def staircase(n):
    floor = n
    while floor > 0:
        step = ''
        for i in range(floor-1):
            step += ' '
        for i in range(n+1-floor):
            step += '#'
        print(step)
        floor -= 1


# https://www.hackerrank.com/challenges/plus-minus/submissions/code/202388393
# Complete the plusMinus function below.
def plusMinus(arr):
    l = len(arr)
    positives = sum([1 for i in arr if i > 0])
    negatives = sum([1 for i in arr if i < 0])
    zeros = sum([1 for i in arr if i == 0])

    print(positives / l)
    print(negatives / l)
    print(zeros / l)


# https://www.hackerrank.com/challenges/diagonal-difference/submissions/code/202387617
# Complete the 'diagonalDifference' function below.
#
# The function is expected to return an INTEGER.
# The function accepts 2D_INTEGER_ARRAY arr as parameter.
#
def diagonalDifference(arr):
    d1, d2 = 0, 0
    l = len(arr)

    print(arr)

    for i in range(0, l):
        j = (l - 1) - i
        d1 += arr[i][i]
        d2 += arr[j][i]

    return abs(d1 - d2)


# https://www.hackerrank.com/challenges/a-very-big-sum/submissions/code/202380990
# Complete the aVeryBigSum function below.
def aVeryBigSum(ar):
    return sum(ar)


# https://www.hackerrank.com/challenges/compare-the-triplets/submissions/code/202380452
# Complete the compareTriplets function below.
def compareTriplets(a, b):
    comp = []
    for i in range(0, len(a)):
        comp.append(a[i] - b[i])

    a_wins = [1 for i in comp if i > 0]
    b_wins = [1 for i in comp if i < 0]
    return [sum(a_wins), sum(b_wins)]


# https://www.hackerrank.com/challenges/anagram/submissions/code/191997752
# Complete the anagram function below.
def anagram(s):
    if len(s) % 2 != 0:
        return -1

    half = len(s) // 2
    head, tail = list(s[:half]), list(s[half:])

    count = 0
    for char in head:
        try:
            tail.remove(char)
        except ValueError:
            count += 1

    return count


# https://www.hackerrank.com/challenges/find-the-merge-point-of-two-joined-linked-lists/submissions/code/187534488
# Complete the findMergeNode function below.
#
# For your reference:
#
# SinglyLinkedListNode:
#     int data
#     SinglyLinkedListNode next
def findMergeNode(head1, head2):
    d = {}
    current = head1
    while current:
        d[current] = current.data
        current = current.next

    current = head2
    while current:
        if d.get(current):
            return current.data
        current = current.next


"""
# https://www.hackerrank.com/challenges/ctci-linked-list-cycle/submissions/code/187529926
Detect a cycle in a linked list. Note that the head pointer may be 'None' if the list is empty.

A Node is defined as: 

    class Node(object):
        def __init__(self, data = None, next_node = None):
            self.data = data
            self.next = next_node
"""
def has_cycle(head):
    d = {}
    current = head
    while current:
        if d.get(head.data):
            return True
        else:
            d[head.data] = True
        current = current.next
    return False


# https://www.hackerrank.com/challenges/ctci-fibonacci-numbers/submissions/code/187528733
def fibonacci(n):
    if n == 0:
        return 0
    if n == 1:
        return 1

    return (fibonacci(n - 1) + fibonacci(n - 2))

n = int(input())
print(fibonacci(n))


# https://www.hackerrank.com/challenges/reverse-a-doubly-linked-list/submissions/code/186771220
# Complete the reverse function below.
#
# For your reference:
#
# DoublyLinkedListNode:
#     int data
#     DoublyLinkedListNode next
#     DoublyLinkedListNode prev
#
#
def reverse(head):
    prev, current, next = None, head, None

    while current:
        next = current.next
        current.next, current.prev = current.prev, current.next
        prev = current

        current = next

    return prev


# https://www.hackerrank.com/challenges/insert-a-node-into-a-sorted-doubly-linked-list/submissions/code/186763803
# Complete the sortedInsert function below.
#
# For your reference:
#
# DoublyLinkedListNode:
#     int data
#     DoublyLinkedListNode next
#     DoublyLinkedListNode prev
#
#
def sortedInsert(head, data):
    new_node = DoublyLinkedListNode(data)

    if data < head.data:
        new_node.next = head
        head.prev = new_node
        return new_node

    current = head
    while current.next and current.next.data < data:
        current = current.next

    new_node.prev, new_node.next = current, current.next
    current.next = new_node
    if new_node.next:
        new_node.next.prev = new_node

    return head


# https://www.hackerrank.com/challenges/insert-a-node-at-a-specific-position-in-a-linked-list/submissions/code/186760696
# Complete the insertNodeAtPosition function below.
#
# For your reference:
#
# SinglyLinkedListNode:
#     int data
#     SinglyLinkedListNode next
#
#
def insertNodeAtPosition(head, data, position):
    new_node = SinglyLinkedListNode(data)

    i = 1
    current = head
    while i < position:
        current = current.next
        i += 1

    new_node.next = current.next
    current.next = new_node

    return head


# https://www.hackerrank.com/challenges/luck-balance/submissions/code/186620455
# Complete the luckBalance function below.
def luckBalance(k, contests):
    ic = []  # important_contests
    ml = 0  # max_luck
    for contest in contests:
        luck = contest[0]
        importance = contest[1]
        if importance:
            ic.append(luck)
        else:
            ml += luck

    ic.sort()

    try:
        i = 0
        while i < k:
            ml += ic.pop()
            i += 1
    except IndexError:
        pass

    ml -= sum(ic)
    return ml


# https://www.hackerrank.com/challenges/minimum-absolute-difference-in-an-array/submissions/code/186547194
# Complete the minimumAbsoluteDifference function below.
def minimumAbsoluteDifference(arr):
    arr.sort()
    mad = float("inf")

    i = 0
    while i + 1 < len(arr):
        current = abs(arr[i] - arr[i + 1])
        mad = min(mad, current)
        i += 1

    return mad


# https://www.hackerrank.com/challenges/binary-search-tree-lowest-common-ancestor/submissions/code/186088146
'''
class Node:
      def __init__(self,info): 
          self.info = info  
          self.left = None  
          self.right = None 


       // this is a node of the tree , which contains info as data, left , right
'''
def lca(root, v1, v2):
    min_v, max_v = min(v1, v2), max(v1, v2)

    if min_v > root.info:
        return lca(root.right, v1, v2)

    if root.info > max_v:
        return lca(root.left, v1, v2)

    return root


# https://www.hackerrank.com/challenges/tree-height-of-a-binary-tree/submissions/code/185962832
'''
class Node:
      def __init__(self,info): 
          self.info = info  
          self.left = None  
          self.right = None 


       // this is a node of the tree , which contains info as data, left , right
'''
def height(root):
    l_deep, r_deep = 0, 0

    if root.left:
        l_deep = 1 + height(root.left)
    if root.right:
        r_deep = 1 + height(root.right)

    return max(l_deep, r_deep)


# https://www.hackerrank.com/challenges/alternating-characters/submissions/code/184517715
# Complete the alternatingCharacters function below.
def alternatingCharacters(s):
    l = list(s)

    try:
        i = 0
        while True:
            while l[i] == l[i+1]:
                l.pop(i+1)
            i += 1
    except IndexError:
        return len(s) - len(l)


# https://www.hackerrank.com/challenges/ctci-making-anagrams/submissions/code/184516087
# Complete the makeAnagram function below.
def makeAnagram(a, b):
    a, b = list(a), list(b)

    removals = 0
    for l in a.copy():
        try:
            a.remove(l)
            b.remove(l)
        except ValueError:
            removals += 1
    for l in b:
        try:
            a.remove(l)
        except ValueError:
            removals += 1

    return removals


# https://www.hackerrank.com/challenges/mark-and-toys/submissions/code/183400224
# Complete the maximumToys function below.
def maximumToys(prices, k):
    prices.sort()
    i = 0
    while k >= prices[i]:
        k -= prices[i]
        i += 1

    return i


# https://www.hackerrank.com/challenges/ctci-bubble-sort/submissions/code/183385088
# Complete the countSwaps function below.
def countSwaps(a):
    swaps = 0
    for i in range(len(a)):
        for j in range(len(a) - 1):
            if a[j] > a[j + 1]:
                a[j], a[j + 1] = a[j + 1], a[j]
                swaps += 1

    print('Array is sorted in', swaps, 'swaps.')
    print('First Element:', a[0])
    print('Last Element:', a[-1])


# https://www.hackerrank.com/challenges/two-strings/submissions/code/183382630
# Complete the twoStrings function below.
def twoStrings(s1, s2):
    return 'YES' if set(s1) & set(s2) else 'NO'


# https://www.hackerrank.com/challenges/ctci-ransom-note/submissions/code/183271633
# Complete the checkMagazine function below.
def checkMagazine(magazine, note):
    note_words = {}
    for word in note:
        try:
            note_words[word] += 1
        except KeyError:
            note_words[word] = 1

    missing_words = len(note)
    i = 0
    while missing_words != 0 and i < len(magazine):
        current_word = magazine[i]
        if note_words.get(current_word):
            note_words[current_word] -= 1
            missing_words -= 1
        i += 1

    if missing_words == 0:
        print('Yes')
    else:
        print('No')


# https://www.hackerrank.com/challenges/ctci-array-left-rotation/submissions/code/181900486
# Complete the rotLeft function below.
def rotLeft(a, d):
    return a[d:]+a[:d]


# https://www.hackerrank.com/challenges/2d-array/submissions/code/181899367
# Complete the hourglassSum function below.
def hourglassSum(arr):
    print(arr)
    Max = float("-inf")
    for x0 in range(0, 4):
        x1 = x0 + 1
        x2 = x0 + 2
        for y0 in range(0, 4):
            y1 = y0 + 1
            y2 = y0 + 2

            Sum = arr[x0][y0] + arr[x0][y1] + arr[x0][y2] \
                  + arr[x1][y1] + \
                  arr[x2][y0] + arr[x2][y1] + arr[x2][y2]
            print(Sum)
            Max = max(Max, Sum)
    return Max


# https://www.hackerrank.com/challenges/repeated-string/submissions/code/181892816
# Complete the repeatedString function below.
def repeatedString(s, n):

    times = n // len(s)
    rem = n - (len(s)*times)

    occurrences = times*s.count('a')
    occurrences += s[:rem].count('a')

    return occurrences


# https://www.hackerrank.com/challenges/jumping-on-the-clouds/submissions/code/181601528
# Complete the jumpingOnClouds function below.
def jumpingOnClouds(c):
    jumps = 0
    current_cloud = 0

    while (current_cloud + 1) < len(c):

        jumps += 1
        if current_cloud + 2 < len(c) and c[current_cloud + 2] == 1:
            current_cloud += 1
        else:
            current_cloud += 2

    return jumps


# https://www.hackerrank.com/challenges/counting-valleys/submissions/code/181592124
# Complete the 'countingValleys' function below.
#
# The function is expected to return an INTEGER.
# The function accepts following parameters:
#  1. INTEGER steps
#  2. STRING path
#
def countingValleys(steps, path):
    valleys = 0
    steps = [1 if x == 'U' else -1 for x in path]

    altitude = 0
    for step in steps:
        altitude += step
        if altitude == 0 and step == 1:
            valleys += 1

    return valleys


# https://www.hackerrank.com/challenges/sock-merchant/submissions/code/181589784
# Complete the sockMerchant function below.
def sockMerchant(n, ar):
    dic = {}
    for color in ar:
        try:
            dic[color] += 1
        except KeyError:
            dic[color] = 1

    pairs = 0
    for socks in dic.values():
        pairs += int(socks / 2)

    return pairs