#!/bin/python3

#
# Complete the 'fizzBuzz' function below.
#
# The function accepts INTEGER n as parameter.
#
def fizzBuzz(n):
    for i in range(1, n + 1):
        mod3 = i % 3 == 0
        mod5 = i % 5 == 0

        if mod3 and mod5:
            print("FizzBuzz")
        elif mod3 and not mod5:
            print("Fizz")
        elif not mod3 and mod5:
            print("Buzz")
        else:
            print(i)


#
# Complete the 'maximumProfit' function below.
#
# The function is expected to return a LONG_INTEGER.
# The function accepts INTEGER_ARRAY price as parameter.
#
def maximumProfit(price):
    # The best strategy is:
    # If there will be a higher price in the future, buy as much as you can now (i.e. buy one in this case),
    # When you reach the maximum price, sell all you have.

    # The buying price doesn't matter as long as it is lower than a higher price in the future.
    # Given that the restriction is in the number of shares that you can buy by period, and not in the budget
    # available, buying is always better if the price is below the maximum.

    # In the test cases I can see, the predicted profit is higher than the expect result, and I don't see that
    # I am breaking any rule.

    # That's why I think the test cases are wrong... but most likely I'm missing something since this will be the
    # first time this happen to me.

    b = 0  # balance
    s = 0  # shares

    print(price)
    while len(price) > 1:
        mp = max(price)
        p = price[0]

        if p == mp:
            b = s * p
            s = 0
        else:
            b -= p
            s += 1

        price = price[1:]
        print(b, s, p, price)

    b += s * price[0]
    print(b)

    return b


#
# Complete the 'rodOffcut' function below.
#
# The function is expected to return an INTEGER_ARRAY.
# The function accepts INTEGER_ARRAY lengths as parameter.
#
def rodOffcut(lengths):
    n = []
    while len(lengths) > 0:
        n.append(len(lengths))
        m = min(lengths)

        try:
            while True:
                lengths.remove(m)
        except ValueError:
            pass

        for i in range(len(lengths)):
            lengths[i] -= m

    return n