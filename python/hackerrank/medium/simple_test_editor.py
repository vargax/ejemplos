"""https://www.hackerrank.com/challenges/simple-text-editor/submissions/code/400761496"""

class Editor:
    def __init__(self):
        self.stack = ['']

    def append(self, string_to_append: str) -> None:
        current_string = self.stack[-1]
        current_string += string_to_append
        self.stack.append(current_string)

    def delete(self, index: int) -> None:
        current_string = self.stack[-1]
        current_string = current_string[:-index]
        self.stack.append(current_string)

    def print_(self, index: int) -> None:
        current_string = self.stack[-1]
        print(current_string[index - 1])

    def undo(self) -> None:
        self.stack.pop()


if __name__ == "__main__":

    editor = Editor()

    while True:
        try:
            line = input()
        except EOFError:
            break

        params = line.split()
        operation = int(params[0])

        match operation:
            case 1:  # append
                editor.append(params[1])

            case 2:  # delete
                index = int(params[1])
                editor.delete(index)

            case 3:  # print
                index = int(params[1])
                editor.print_(index)

            case 4:  # undo
                editor.undo()
