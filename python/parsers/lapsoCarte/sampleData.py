# -*- coding: UTF-8 -*-
##########################
# IMPORTS
##########################
import os
import pprint
import random

##########################
# CONSTANTS
##########################
OUTPUT_FILE = 'output.csv'

##########################
# VARIABLES
##########################
spatialObjects_ids = range(1, 936)
num_periods = range(1, 11)

initialPop_range = (1000, 5000)
popGrowthRate_range = (0.03, 0.05)

##########################
# SCRIPT
##########################
if os.path.exists(OUTPUT_FILE):
    os.remove(OUTPUT_FILE)

output = open(OUTPUT_FILE, 'w')
output.write('objectid,t,population\n')

for sp in spatialObjects_ids:
    initialPop = random.randint(initialPop_range[0], initialPop_range[1])
    growthRate = random.uniform(popGrowthRate_range[0], popGrowthRate_range[1])

    for t in num_periods:
        pop = initialPop*((1+growthRate)**t)
        output.write(str(sp)+','+str(t)+','+str(pop)+'\n')

output.close()
