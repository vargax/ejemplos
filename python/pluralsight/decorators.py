import functools
from typing import Callable


def escape_unicode(f: Callable) -> Callable:
    """
    Function decorator example
    :param f: The function to be decorated
    :return: Wrapped function
    """

    @functools.wraps(f)  # Required to pass the original function metadata to the wrapped function
    def wrap(*args, **kwargs):
        x = f(*args, **kwargs)
        return ascii(x)

    return wrap  # The wrap function object is returned (note there is no () )


class CallCount:
    """
    Class decorator example:
    - Effectively you are replacing the function with an instance of the Class
    - The Class must be callable
    """

    def __init__(self, f: Callable):
        """
        :param f: The function to be decorated
        """
        self.f = f
        self.count = 0
        functools.update_wrapper(self, f)  # It will only work partially
        # Don't use a class as decorator if you want help() to work for the decorated function
        # https://stackoverflow.com/questions/25973376/functools-update-wrapper-doesnt-work-properly/25973438#25973438

    def __call__(self, *args, **kwargs):
        """
        This is what actually will happen when the decorated function is called.
        Extended formal & Extended call syntax are used to pass the original function parameters
        :param args: Original function args
        :param kwargs: Original function kwargs
        :return: The result of calling the original function
        """
        self.count += 1
        return self.f(*args, **kwargs)


class Trace:
    """
    Class INSTANCE decorator
    """

    def __init__(self):
        self.enabled = True

    def __call__(self, f: Callable) -> Callable:
        """
        :param f: The function to be decorated
        :return: wrapped function
        """

        @functools.wraps(f)
        def wrap(*args, **kwargs):
            if self.enabled:
                print(f"Calling {f}")
            return f(*args, **kwargs)

        return wrap


# Function decorator
@escape_unicode
def city_name() -> str:
    """This simple function will return Bogotá"""
    return 'Bogotá'


help(city_name)
print(city_name())


# Class decorator
@CallCount
def hello(name: str) -> None:
    """I just salutes you!"""
    print(f"Hello {name}!")


help(hello)
for n in ['Pedro', 'Pablo', 'Juan']:
    hello(n)
print(f"hello was called {hello.count} times!")

# Class INSTANCE decorator
tracer = Trace()


@tracer  # Note that this is the INSTANCE of Trace
def rotate_list(l: list) -> list:
    return l[1:] + [l[0]]


print(rotate_list([1, 2, 3, 4]))
tracer.enabled = False
print(rotate_list([1, 2, 3, 4]))

# Multiple decorators
tracer.enabled = True


@tracer
@escape_unicode
def f():
    """Simple function that returns a phrase"""
    return "Era el año 2022 en Bogotá, Colombia"


print(f())
