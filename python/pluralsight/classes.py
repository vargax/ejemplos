class Patient:
    blood_types = ('A', 'B', 'AB', 'O')

    def __init__(self, name: str, blood_type: chr):
        self.name = name
        self.blood_type = blood_type

    @property
    def firstname(self):
        return self.name.split(' ')[0]

    @property
    def blood_type(self):
        return self._blood_type

    @blood_type.setter
    def blood_type(self, blood_type):
        if blood_type not in self.blood_types:
            raise ValueError(f"{blood_type} is not an allowed. The allowed types are {self.blood_types}")
        self._blood_type = blood_type
