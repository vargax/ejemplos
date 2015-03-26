clear
capture log close
cd "C:\Users\Camilo\Documents\Uniandes\200810\Econometría II\taller2\pto3"
log using pto3.smcl, replace
use MROZ
d
* -------------------------
* Condición De Rango
* -------------------------
* MC2E EC1
* -------------------------
ivregress 2sls hours educ age kidslt6 kidsge6 nwifeinc (lwage=educ age kidslt6 kidsge6 nwifeinc exper expersq), first
* -------------------------
* MC2E EC2
* -------------------------
ivregress 2sls lwage educ exper expersq (hours=educ age kidslt6 kidsge6 nwifeinc exper expersq), first
* -------------------------
* MC3E
* -------------------------
reg3 (hours lwage educ age kidslt6 kidsge6 nwifeinc) (lwage hours educ exper expersq)
* *************************
* Educación como variable endógena
* -------------------------
* MC2E EC1
* -------------------------
ivregress 2sls hours age kidslt6 kidsge6 nwifeinc (educ lwage= age kidslt6 kidsge6 nwifeinc motheduc fatheduc exper expersq), first
* -------------------------
* MC2E EC2
* -------------------------
ivregress 2sls lwage exper expersq (hours educ = age kidslt6 kidsge6 nwifeinc motheduc fatheduc exper expersq), first
* -------------------------
* MC2E EC3
* -------------------------
ivregress 2sls educ motheduc fatheduc
* -------------------------
* MC3E
* -------------------------
reg3 (hours lwage educ age kidslt6 kidsge6 nwifeinc) (lwage hours educ exper expersq) (educ motheduc fatheduc)