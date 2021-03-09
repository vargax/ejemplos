"""
First
"""
FIRST_YEAR, LAST_YEAR = 1900, 2019
DENOMINATIONS = {10, 20, 50, 100, 200, 500, 1000}
A, Z = ord('A'), ord('Z')


def count_counterfeit(serial_numbers):
    valid_currency = 0

    for serial_number in serial_numbers:
        try:
            if is_valid(serial_number):
                print('  VALID: ', serial_number)
                valid_currency += int(serial_number[7:-1])
            else:
                print('INVALID: ', serial_number)
        except (TypeError, ValueError):
            print(' EXCEPT: ', serial_number)
            continue

    return valid_currency


def is_valid(serial_number):
    first_three_letters = serial_number[:3]
    year = serial_number[3:7]
    denomination = serial_number[7:-1]
    last_letter = serial_number[-1:]

    """ DEBUG
    split = [serial_number, first_three_letters, year, denomination, last_letter]
    print(split)

    checks = []
    checks.append(begin_distinct_uppercase(first_three_letters))
    checks.append(check_note_year(year))
    checks.append(check_denomination(denomination))
    checks.append(is_uppercase_letter(last_letter))
    print(checks)
    """

    return \
        begin_distinct_uppercase(first_three_letters) and \
        check_note_year(year) and \
        check_denomination(denomination) and \
        is_uppercase_letter(last_letter)


def begin_distinct_uppercase(chars_1_to_3):
    if len(set(chars_1_to_3)) != 3:
        return False

    for char in chars_1_to_3:
        if not is_uppercase_letter(char):
            return False

    return True


def check_note_year(chars_3_to_7):
    note_year = int(chars_3_to_7)
    return FIRST_YEAR <= note_year <= LAST_YEAR


def check_denomination(chars_8_to_penult):
    note_denomination = int(chars_8_to_penult)
    return note_denomination in DENOMINATIONS


def is_uppercase_letter(char):
    return A <= ord(char) <= Z


"""
SECOND
"""


def moves(arr):
    who_are_odd = [i % 2 for i in arr]
    number_of_odds = sum(who_are_odd)
    odds_in_place = sum(who_are_odd[-number_of_odds:])
    odds_to_swap = number_of_odds - odds_in_place

    return odds_to_swap
