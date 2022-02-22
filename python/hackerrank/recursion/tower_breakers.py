#!/bin/python3

cache = {}


class node:
    def __init__(self, towers: list[int]):
        self.tt = towers
        self.tt.sort()
        self.id = ''.join(str(i) for i in self.tt)
        self.decendents = set()

        if self.id not in cache:
            self.gen_decendents()
            cache[self.id] = self

    def gen_decendents(self) -> None:
        for t in self.tt:
            c = self.tt.copy()
            c.remove(t)

            for h in range(1, t):
                d = c.copy()
                d.append(h)
                n = node(d)
                self.decendents.add(cache[n.id])

    def is_winner_node(self) -> bool:
        if len(self.tt) == sum(self.tt):
            return False
        for n in self.decendents:
            if not n.is_winner_node():
                return True
        return False

    def __str__(self) -> str:
        return self.id


def tower_breakers(towers, height):
    tt = [height] * towers
    n = node(tt)
    if n.is_winner_node():
        return 1
    return 2
