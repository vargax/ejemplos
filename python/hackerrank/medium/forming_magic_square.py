
def forming_magic_square(matrix: list[list[int]]) -> int:
    # found the missing number(s)

    counts = {i: 0 for i in range(1, 10)}

    for row in matrix:
        for i in row:
            counts[i] += 1

    missing = [i for i in counts if counts[i] == 0]
    extra = [i for i in counts if counts[i] > 1]

    differences = [abs(i - j) for i, j in zip(missing, extra)]
    difference = sum(differences)

    return difference


s1 = forming_magic_square([[5,3,4], [1,5,8],[6,4,2]])
s2 = forming_magic_square([[4,9,2], [3,5,7],[8,1,5]])
s3 = forming_magic_square([[4,8,2], [4,5,7],[6,1,6]])
pass