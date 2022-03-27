def sqrt(n):
    if n < 0:
        raise ValueError(f"{n} is not >= 0")

    g = n
    i = 0
    while g ** 2 != n and i < 20:
        g = (g + n / g) / 2.0
        i += 1
    return g


def isprime(n):
    if n < 2:
        return False
    for i in range(2, int(sqrt(n)) + 1):
        if n % i == 0:
            return False
    return True


pp = [n for n in range(100) if isprime(n)]
print(pp)
