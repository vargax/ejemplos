# -*- coding: UTF-8 -*-
import os

INPUT_FILE = 'data.csv'

patterns = [
    'trip time mode i',
    'total tours i mode',
    'HH by zone i',
    'employed population',
    'residents J',
    'cars',
    'total CO2 i per year WW'
]

time = []
gid = []

data = open(INPUT_FILE)
for line in data:
    # First line in file is time
    for t in line.split(','):
        try:
            time.append(int(t))
        except ValueError:
            print 'Time '+t+' no parsable to integer'
            continue
    print str(time)+' time periods registered!'

