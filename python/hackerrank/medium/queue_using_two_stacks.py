"""https://www.hackerrank.com/challenges/one-week-preparation-kit-queue-using-two-stacks/submissions/code/399780417"""

if __name__ == "__main__":

    _ = input()

    queue = []
    while True:
        try:
            line = input()
        except EOFError:
            break

        params = [int(i) for i in line.split()]
        code = params[0]

        match code:
            case 1:
                value = params[1]
                queue.append(value)

            case 2:
                queue.pop(0)

            case 3:
                print(queue[0])
