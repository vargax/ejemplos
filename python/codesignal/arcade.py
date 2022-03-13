#!/bin/python3

# https://app.codesignal.com/arcade/intro/level-2/xzKiBHjhoinnpdh6m
def adjacent_elements_product(arr):
    m = float('-inf')
    for i in range(1, len(arr)):
        m = max(m, arr[i - 1] * arr[i])
    return m


# https://app.codesignal.com/arcade/intro/level-2/yuGuHvcCaFCKk56rJ
def shape_area(n):
    if n == 1:
        return 1
    return shape_area(n - 1) + (n - 1) * 4


# https://app.codesignal.com/arcade/intro/level-2/bq2XnSr5kbHqpHGJC
def make_array_consecutive_2(ss):
    ss.sort()
    s = 0
    for i in range(1, len(ss)):
        s += ss[i] - ss[i - 1] - 1
    return s


# https://app.codesignal.com/arcade/intro/level-2/2mxbGwLzvkTCKAJMG
def almost_increasing_sequence(arr):
    i = 1
    while i < len(arr):
        if arr[i - 1] >= arr[i]:

            left = arr[:]
            left.pop(i - 1)
            if left == sorted(list(set(left))):
                return True

            me = arr[:]
            me.pop(i)
            if me == sorted(list(set(me))):
                return True

            right = arr[:]
            right.pop(i + 1)
            if right == sorted(list(set(right))):
                return True

            return False

        i += 1

    return True


# https://app.codesignal.com/arcade/intro/level-2/xskq4ZxLyqQMCLshr
def matrix_elements_sum(m):
    h = set()
    p = 0

    i = 0
    while i < len(m):
        j = 0
        while j < len(m[i]):
            if j in h or m[i][j] == 0:
                h.add(j)
            else:
                p += m[i][j]
            j += 1
        i += 1

    return p


# https://app.codesignal.com/arcade/intro/level-3/fzsCQGYbxaEcTr2bL/
def all_longest_strings(arr):
    m = -1
    ss = {}

    for i in range(len(arr)):
        s = arr[i]
        l = len(s)
        m = max(m, l)

        if l in ss:
            ss[l].append(s)
        else:
            ss[l] = [s]

    return ss[m]


# https://app.codesignal.com/arcade/intro/level-3/JKKuHJknZNj4YGL32
def common_character_count(s1, s2):
    s1 = list(s1)
    s2 = list(s2)
    c = 0

    for l in s1:
        if l in s2:
            c += 1
            s2.remove(l)

    return c


# https://app.codesignal.com/arcade/intro/level-3/3AdBC97QNuhF6RwsQ
def is_lucky(n):
    nn = [int(i) for i in list(str(n))]
    m = len(nn) // 2

    return sum(nn[:m]) == sum(nn[m:])


# https://app.codesignal.com/arcade/intro/level-3/D6qmdBL2NYz49XHwM/
def sort_by_height(a):
    aa = sorted([i for i in a if i != -1])

    for i in range(len(a)):
        if a[i] != -1:
            a[i] = aa.pop(0)

    return a
