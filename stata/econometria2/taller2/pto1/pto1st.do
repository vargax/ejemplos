clear
capture log close
cd "C:\Users\Camilo\Documents\Uniandes\200810\Econometría II\taller2\pto1"
log using pto1.smcl, replace
use datospto1
* -------------------------
* FORMA REDUCIDA DEL MODELO
* -------------------------
reg y1 x1 x2 x3
reg y2 x1 x2 x3
* -------------------------
* MCI - MC2E EC1
* -------------------------
ivregress 2sls y1 x1 x2 (y2=x1 x2 x3), first
* -------------------------
* MCI - MC2E EC2
* -------------------------
ivregress 2sls y2 x3 (y1= x1 x2 x3), first
* -------------------------
* MC3E
* -------------------------
reg3 (y1 y2 x1 x2) (y2 y1 x3)
