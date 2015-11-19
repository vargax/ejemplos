# -*- coding: UTF-8 -*-
import os

INPUT_FILE = 'demo.csv'

output = []

input_file = open(INPUT_FILE)
for line in input_file:
    values = line.split(';')

    variable, zone = values.pop(0), values.pop(0)
    values = [float(x) for x in values]

    t, base = 0, values[0]
    for data in values:
        output.append(['Nivel', variable, t, zone, data])
        output.append(['Base 0', variable, t, zone, data/base])
        t += 1

try:
    os.remove('demo.sql')
except OSError:
    pass

sql = open('demo.sql', 'w')
sql.write('DROP TABLE IF EXISTS demo;\n')
sql.write('CREATE TABLE demo(how_column TEXT, what_column TEXT, when_column INT, where_column INT, data_column FLOAT);\n')
sql.write('ALTER TABLE demo ADD CONSTRAINT demo_pk PRIMARY KEY(how_column, what_column, when_column, where_column);\n')

query = "INSERT INTO demo VALUES "
for row in output:
    how, what, when, where, data = row
    query += "('"+how+"','"+what+"',"+str(when)+","+str(where)+","+str(data)+"),"

query = query[:-1] + ';\n'
sql.write(query)
sql.close()
