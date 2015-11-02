# -*- coding: UTF-8 -*-
import os
import shutil

INPUT_FILE = 'data.csv'
OUTPUT_DIR = 'output'

NAME, COLUMNS, FUNCTION, DATA_STRUCTURE = 'name', 'columns', 'function', 'data_structure'
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
            output += str(column)+','
        output = output[:-1]
        output += '\n'
    return output


# ----------------------------------------------------------------------------------------------------------------------
# Different data sets in INPUT_FILE
# ----------------------------------------------------------------------------------------------------------------------
time = []
times = {
    NAME: 'times',
    FUNCTION: time_parser,
    DATA_STRUCTURE: time
}
trips = {
    NAME: 'trips',
    COLUMNS: 't,gid,mode,trips',
    FUNCTION: two_dim_parser,
    DATA_STRUCTURE: []
}
tours = {
    NAME: 'tours',
    COLUMNS: 't,gid,mode,tours',
    FUNCTION: two_dim_parser,
    DATA_STRUCTURE: []
}
hh_by_zone = {
    NAME: 'hh_by_zone',
    COLUMNS: 't,gid,hh_by_zone',
    FUNCTION: one_dim_parser,
    DATA_STRUCTURE: []
}
employed_pop = {
    NAME: 'employed_pop',
    COLUMNS: 't,gid,employed_pop',
    FUNCTION: one_dim_parser,
    DATA_STRUCTURE: []
}
residents = {
    NAME: 'residents',
    COLUMNS: 't,gid,residents',
    FUNCTION: one_dim_parser,
    DATA_STRUCTURE: []
}
cars = {
    NAME: 'cars',
    COLUMNS: 't,gid,cars',
    FUNCTION: one_dim_parser,
    DATA_STRUCTURE: []
}
co2 = {
    NAME: 'co2',
    COLUMNS: 't,gid,co2',
    FUNCTION: one_dim_parser,
    DATA_STRUCTURE: []
}

# ----------------------------------------------------------------------------------------------------------------------
# Script
# ----------------------------------------------------------------------------------------------------------------------
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

try:
    shutil.rmtree(OUTPUT_DIR)
except OSError:
    pass

os.mkdir(OUTPUT_DIR)
sql = open(OUTPUT_DIR+'/db.sql', 'w')
for topic in trips, tours, hh_by_zone, employed_pop, residents, cars, co2:
    query = 'DROP TABLE IF EXISTS '+topic[NAME]+';\n'

    query += 'CREATE TABLE '+topic[NAME]+'(t int, gid int,'
    if topic[FUNCTION] is two_dim_parser:
        query += 'mode text,'+topic[NAME]+' float);\n'
        query += 'ALTER TABLE '+topic[NAME]+' ADD CONSTRAINT '+topic[NAME]+'_pk PRIMARY KEY(t,gid,mode);\n'
    else:
        query += topic[NAME]+' float);\n'
        query += 'ALTER TABLE '+topic[NAME]+' ADD CONSTRAINT '+topic[NAME]+'_pk PRIMARY KEY(t,gid);\n'

    query += 'COPY '+topic[NAME]+' FROM "/tmp/mars/'+topic[NAME]+'.csv" DELIMITER "," CSV HEADER;'
    sql.write(query)

    result = open(OUTPUT_DIR+'/'+topic[NAME]+'.csv', 'w')
    result.write(topic[COLUMNS]+'\n')
    result.write(array_to_csv(topic[DATA_STRUCTURE]))
    result.close()

sql.close()
