ispowof2 = lambda n: n != 0 and n & (n - 1) == 0
iseven = lambda n: n % 2 == 0
isodd = lambda n: n % 2 == 1
transpose = lambda mm: list(zip(*mm))
rotate = lambda l: l[1:] + [l[0]]
