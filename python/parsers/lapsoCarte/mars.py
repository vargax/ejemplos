# -*- coding: UTF-8 -*-
import os

INPUT_FILE = 'data.csv'

NAME, FUNCTION, DATA_STRUCTURE = 'name', 'function', 'data_structure'
# ----------------------------------------------------------------------------------------------------------------------
# Utility functions
# ----------------------------------------------------------------------------------------------------------------------


def string_to_float_array(string):
    return [float(x) for x in string.split(',')[-len(time):]]


def time_parser(line):
    output = []
    for t in line.split(','):
        try:
            output.append(int(t))
        except ValueError:
            print 'Time '+t+' no parsable to integer'
            continue
    return output


def one_dim_parser(line):
    output = []

    upz = line[line.find('[')+1:line.find(']')]
    data = string_to_float_array(line)

    for t in range(0, len(time)):
        output.append([time[t], upz, data[t]])
    return output


def two_dim_parser(line):
    output = []

    gid, mode = (line[line.find('[')+1:line.find(']')]).split(',')
    data = string_to_float_array(line)

    for t in range(0, len(time)):
        output.append([time[t], gid, mode, data[t]])
    return output


def array_to_csv(array_of_arrays):
    output = ''
    for row in array_of_arrays:
        for column in row:
            output += column+','
        output = output[:-1]
        output += '\n'
    return output


# ----------------------------------------------------------------------------------------------------------------------
# Script
# ----------------------------------------------------------------------------------------------------------------------
time = []
times = {
    NAME: 'times',
    FUNCTION: time_parser,
    DATA_STRUCTURE: time
}
trips = {
    NAME: 'trips',
    FUNCTION: two_dim_parser,
    DATA_STRUCTURE: []
}
tours = {
    NAME: 'tours',
    FUNCTION: two_dim_parser,
    DATA_STRUCTURE: []
}
hh_by_zone = {
    NAME: 'hh_by_zone',
    FUNCTION: one_dim_parser,
    DATA_STRUCTURE: []
}
employed_pop = {
    NAME: 'employed_pop',
    FUNCTION: one_dim_parser,
    DATA_STRUCTURE: []
}
residents = {
    NAME: 'residents',
    FUNCTION: one_dim_parser,
    DATA_STRUCTURE: []
}
cars = {
    NAME: 'cars',
    FUNCTION: one_dim_parser,
    DATA_STRUCTURE: []
}
co2 = {
    NAME: 'co2',
    FUNCTION: one_dim_parser,
    DATA_STRUCTURE: []
}

patterns = {
    'Time': times,
    'trip time mode i': trips,
    'total tours i mode': tours,
    'HH by zone i': hh_by_zone,
    'employed population': employed_pop,
    'residents J': residents,
    'cars': cars,
    'total CO2 i per year WW': co2
}


input_file = open(INPUT_FILE)
for line in input_file:
    for pattern in patterns.keys():
        if line.startswith(pattern):
            function = patterns[pattern][FUNCTION]
            data_structure = patterns[pattern][DATA_STRUCTURE]
            data_structure.extend(function(line))
            break

for result in [trips, tours, hh_by_zone, employed_pop, residents, cars, co2]:
    print result
