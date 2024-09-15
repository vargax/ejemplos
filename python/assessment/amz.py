from collections import defaultdict


def count_groups(related: list[str]) -> int:
    connections: dict[int, set[int]] = defaultdict(set)
    for i in range(len(related)):
        line = related[i]
        row = [int(i) for i in line]

        for j in range(len(row)):
            if row[j] == 1:
                connections[i].add(j)

    groups: list[set[int]] = []
    for user, user_connections in connections.items():
        for group in groups:
            if group & user_connections:
                group.update(user_connections)
                break
        else:
            groups.append(user_connections)

    return len(groups)


count_groups(['1100', '1110', '0110', '0001'])

