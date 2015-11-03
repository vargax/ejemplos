# -*- coding: UTF-8 -*-
import os
import shutil

INPUT_FILE = 'data.csv'
OUTPUT_DIR = 'output'
HEADER = 't,gid,data'

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

    gid = line[line.find('[')+2:line.find(']')]
    data = string_to_float_array(line)

    for t in range(0, len(time)):
        output.append([time[t], gid, data[t]])
    return output


def two_dim_parser(line):
    output = []

    gid, mode = (line[line.find('[')+2:line.find(']')]).split(',')
    data = string_to_float_array(line)

    for t in range(0, len(time)):
        output.append([time[t], gid, data[t]])
    return mode, output


def array_to_csv(array_of_arrays):
    output = ''
    for row in array_of_arrays:
        for column in row:
            output += str(column)+','
        output = output[:-1]
        output += '\n'
    return output


def sql_gen(name):
    query = 'DROP TABLE IF EXISTS '+name+';\n'
    query += 'CREATE TABLE '+name+'(t int, mars int,'+name+' float);\n'
    query += 'ALTER TABLE '+name+' ADD CONSTRAINT '+name+'_pk PRIMARY KEY(t,mars);\n'
    query += "COPY "+name+" FROM '/tmp/mars/"+name+".csv' DELIMITER ',' CSV HEADER;\n"

    select = ', '+name+'.'+name
    join = 'INNER JOIN '+name+' ON a.t='+name+'.t '
    join += 'AND a.mars='+name+'.mars\n'

    return query, select, join


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
    FUNCTION: two_dim_parser,
    DATA_STRUCTURE: {}
}
tours = {
    NAME: 'tours',
    FUNCTION: two_dim_parser,
    DATA_STRUCTURE: {}
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

            if function is two_dim_parser:
                mode, output = function(line)
                try:
                    data_structure[mode].extend(output)
                except KeyError:
                    data_structure[mode] = output
            else:
                data_structure.extend(function(line))
            break

try:
    shutil.rmtree(OUTPUT_DIR)
except OSError:
    pass

os.mkdir(OUTPUT_DIR)
sql = open(OUTPUT_DIR+'/db.sql', 'w')
sql_select = ''
sql_join = ''

for topic in patterns.values():
    if topic[FUNCTION] is time_parser:
        continue

    if topic[FUNCTION] is one_dim_parser:
        topic[DATA_STRUCTURE] = {'': topic[DATA_STRUCTURE]}

    for mode in topic[DATA_STRUCTURE].keys():
        name = topic[NAME]
        if mode != '':
            name += '_'+mode
        name = name.replace(' ', '')
        query, select, join = sql_gen(name)

        result = open(OUTPUT_DIR+'/'+name+'.csv', 'w')
        result.write(HEADER+'\n')
        result.write(array_to_csv(topic[DATA_STRUCTURE][mode]))
        result.close()

        sql.write(query)
        if sql_select == '':
            sql_select += 'SELECT a.t, a.mars'
            sql_join += '\nFROM '+name+' a \n'
        else:
            sql_select += select
            sql_join += join

sql.write(sql_select+sql_join)
sql.close()
