"""https://www.hackerrank.com/challenges/one-week-preparation-kit-balanced-brackets/submissions/code/399783686"""

def is_balanced(string: str) -> bool:
    stack = []
    for char in string:
        if char in ('(', '{', '['):
            stack.append(char)
            continue

        if char in (')', '}', ']'):
            if not stack:
                return False

            match char:
                case ')':
                    if stack.pop() == '(':
                        continue
                case '}':
                    if stack.pop() == '{':
                        continue
                case ']':
                    if stack.pop() == '[':
                        continue

            return False

    if stack:
        return False

    return True