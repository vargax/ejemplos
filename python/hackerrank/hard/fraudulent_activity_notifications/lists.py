#!/bin/python3

"""https://www.hackerrank.com/challenges/fraudulent-activity-notifications/submissions/code/399696295"""

import os

from bisect import insort, bisect


#
# Complete the 'activityNotifications' function below.
#
# The function is expected to return an INTEGER.
# The function accepts following parameters:
#  1. INTEGER_ARRAY expenditure
#  2. INTEGER d
#
def activity_notifications(expenditure: list[int], trailing_days: int) -> int:

    pivot = trailing_days // 2
    is_odd = trailing_days % 2 != 0
    sorted_period = sorted(expenditure[:trailing_days])

    notifications = 0
    period_start, period_end = 0, trailing_days
    while period_end < len(expenditure)-1:

        current_period = expenditure[period_start:period_end]
        current_expenditure = expenditure[period_end]

        if is_odd:
            trailing_mean = sorted_period[pivot]
        else:
            trailing_mean = (sorted_period[pivot - 1] + sorted_period[pivot]) / 2

        if current_expenditure >= 2*trailing_mean:
            notifications += 1

        # find the location of the last period expediture in sorted_period
        index_to_remove = bisect(sorted_period, current_period[0])

        # removing the oldest & adding the newest expenditures
        del sorted_period[index_to_remove]
        insort(sorted_period, current_expenditure)

        period_start += 1
        period_end += 1

    return notifications


if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    first_multiple_input = input().rstrip().split()

    n = int(first_multiple_input[0])

    d = int(first_multiple_input[1])

    expenditure = list(map(int, input().rstrip().split()))

    result = activity_notifications(expenditure, d)

    fptr.write(str(result) + '\n')

    fptr.close()
