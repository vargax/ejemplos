clear
capture log close
cd "C:\Users\Camilo\Documents\Uniandes\200810\Econometría II\taller4\pto2"
log using pto2.smcl, replace
use pto2

gen x1_1=x1[_n-1]
gen x1_2=x1[_n-2]
gen x1_3=x1[_n-3]
gen x1_4=x1[_n-4]

gen x2_1=x2[_n-1]
gen x2_2=x2[_n-2]
gen x2_3=x2[_n-3]
gen x2_4=x2[_n-4]

gen x3_1=x3[_n-1]
gen x3_2=x3[_n-2]
gen x3_3=x3[_n-3]
gen x3_4=x3[_n-4]

reg y x1 x2 x3
reg y x1 x1_1 x2 x2_1 x3 x3_1
reg y x1 x1_1 x1_2 x2 x2_1 x2_2 x3 x3_1 x3_2
reg y x1 x1_1 x1_2 x1_3 x2 x2_1 x2_2 x2_3 x3 x3_1 x3_2 x3_3
reg y x1 x1_1 x1_2 x1_3 x1_4 x2 x2_1 x2_2 x2_3 x2_4 x3 x3_1 x3_2 x3_3 x3_4

**************************************************************************

reg y x1 x1_1 x2 x2_1 x3
