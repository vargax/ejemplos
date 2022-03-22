def escape_unicode(f):
    def wrap(*args, **kwargs):
        x = f(*args, **kwargs)
        return ascii(x)

    return wrap


@escape_unicode
def city_name() -> str:
    return 'Bogotá'


print(city_name())


class CallCount:
    def __init__(self, f):
        self.f = f
        self.count = 0

    def __call__(self, *args, **kwargs):
        self.count += 1
        return self.f(*args, **kwargs)


@CallCount
def hello(name: str) -> None:
    print(f"Hello {name}!")


hello('Pedro')
hello('Pablo')
hello('Juan')
print(hello.count)
