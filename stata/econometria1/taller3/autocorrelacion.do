capture log close
clear
cd "C:\Users\Camilo\Documents\Uniandes\200720\Econometría I\Taller3.EconometríaI"
log using autocorrelación.smcl, replace
use agro

* -----
* a)
* -----
tsset obs
reg  y mc mn ku kp t r l
dwstat

* -----
* b)
* -----
predict urs,resid
gen lagurs=urs[_n-1]
scatter urs lagurs
plot urs lagurs

* -----
* d)
* -----
reg urs lagurs, nocons
gen lagy=y[_n-1]
gen yEst=y-.5754586*lagy

gen regmc=mc[_n-1]
gen regmn=mn[_n-1]
gen regku=ku[_n-1]
gen regkp=kp[_n-1]
gen regt=t[_n-1]
gen regr=r[_n-1]
gen regl=l[_n-1]

gen mcEs=mc-.5754586*regmc
gen mnEs=mn-.5754586*regmn
gen kuEs=ku-.5754586*regku
gen kpEs=kp-.5754586*regkp
gen tEs=t-.5754586*regt
gen rEs=r-.5754586*regr
gen lEs=l-.5754586*regr

reg yEst mcEs mnEs kuEs kpEs tEs rEs lEs

* -----
* e)
* -----
dwstat