clear
capture log close
cd "C:\Users\Camilo\Documents\Uniandes\200810\Econometría II\taller4\pto1"
log using pto1.smcl, replace
use jtrain2
* --------
* PUNTO A)
* --------
count if train==1
sum mostrn
* --------
* PUNTO B)
* --------
reg train unem74 unem75 age edu black hisp married
test unem74 unem75 age edu black hisp married
* --------
* Modelo Logit:
* --------
logit train unem74 unem75 age edu black hisp married
mfx
predict trainlg
gen lg=trainlg>0.5
table lg train, contents(freq)
* --------
* Modelo Probit:
* --------
probit train unem74 unem75 age edu black hisp married
mfx
predict trainprb
gen prb=trainprb>0.5
table prb train, contents(freq)
