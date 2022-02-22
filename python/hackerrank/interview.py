#!/bin/python3


# https://www.hackerrank.com/challenges/one-week-preparation-kit-recursive-digit-sum/submissions/code/255436912
# Complete the 'superDigit' function below.
#
# The function is expected to return an INTEGER.
# The function accepts following parameters:
#  1. STRING n
#  2. INTEGER k
#
def superDigit(n: str, k: int):
    if len(n) == 1:
        return int(n[0])

    d = list(n)
    d = [int(i) for i in d]
    d = sum(d) * k

    return superDigit(str(d), 1)


# https://www.hackerrank.com/challenges/one-week-preparation-kit-grid-challenge/submissions/code/255378721
# Complete the 'gridChallenge' function below.
#
# The function is expected to return a STRING.
# The function accepts STRING_ARRAY grid as parameter.
#
def gridChallenge(grid):
    rs = len(grid)  # RowS
    cs = len(grid[0])  # ColumnS

    for i in range(rs):
        grid[i] = sorted(list(grid[i]))

    for j in range(cs):
        c = []
        for i in range(rs):
            c.append(grid[i][j])
        if c != sorted(c):
            return 'NO'
    return 'YES'


# https://www.hackerrank.com/challenges/one-week-preparation-kit-caesar-cipher-1/submissions/code/255081121
# Complete the 'caesarCipher' function below.
#
# The function is expected to return a STRING.
# The function accepts following parameters:
#  1. STRING s
#  2. INTEGER k
#
def caesarCipher(s, k):
    k = k % 26
    a, A = ord('a'), ord('A')
    z, Z = ord('z'), ord('Z')

    out = ''
    for c in s:
        i = ord(c)
        if a <= i <= z:
            i = i + k
            if i > z:
                i = (a - 1) + (i - z)

        if A <= i <= Z:
            i = i + k
            if i > Z:
                i = (A - 1) + (i - Z)

        out += chr(i)
    return out


# https://www.hackerrank.com/challenges/one-week-preparation-kit-tower-breakers-1/submissions/code/254979853
# Complete the 'towerBreakers' function below.
#
# The function is expected to return an INTEGER.
# The function accepts following parameters:
#  1. INTEGER n
#  2. INTEGER m
#
def towerBreakers(towers, height):
    if height == 1 or towers % 2 == 0:
        return 2
    return 1


#
# Complete the 'countingSort' function below.
#
# The function is expected to return an INTEGER_ARRAY.
# The function accepts INTEGER_ARRAY arr as parameter.
#
def countingSort(arr):
    frr = [0] * 100
    for i in arr:
        frr[i] += 1
    return frr


#
# Complete the 'diagonalDifference' function below.
#
# The function is expected to return an INTEGER.
# The function accepts 2D_INTEGER_ARRAY arr as parameter.
#
def diagonalDifference(arr):
    n = len(arr) - 1
    d1 = d2 = 0
    for i in range(n + 1):
        d1 += arr[i][i]
        d2 += arr[n - i][i]
    return abs(d1 - d2)


#
# Complete the 'fizzBuzz' function below.
#
# The function accepts INTEGER n as parameter.
#
def fizzBuzz(n):
    for i in range(1, n + 1):
        if i % 3 == i % 5 == 0:
            print('FizzBuzz')
        elif i % 3 == 0:
            print('Fizz')
        elif i % 5 == 0:
            print('Buzz')
        else:
            print(i)


# https://www.hackerrank.com/challenges/one-week-preparation-kit-time-conversion/submissions/code/254220110
# Complete the 'timeConversion' function below.
#
# The function is expected to return a STRING.
# The function accepts STRING s as parameter.
#
def timeConversion(s):
    if s.startswith('12'):
        if s.endswith('AM'):
            return f"00{s[2:-2]}"
        if s.endswith('PM'):
            return s[:-2]
    if s.endswith('AM'):
        return s[:-2]
    return f"{int(s[:2]) + 12}{s[2:-2]}"
