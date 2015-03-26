clear
capture log close
cd "C:\Users\Camilo\Documents\Uniandes\200810\Econometría II\taller3\pto2"
log using pto2c.smcl, replace
use murder
xtset id year
* ----------
* PUNTO C)
* ----------
* Prueba de Haussman
*  ----------
*  Efectos Fijos
*  ----------
xtreg mrdrte exec unem d90 d93, fe
estimates store estfe
*  ----------
*  Efectos Aleatorios
*  ----------
xtreg mrdrte unem exec d93, re
estimates store estre
*  ----------
*  Haussman
*  ----------
hausman estfe estre
*  ----------
*  Efectos Fijos vs. Primeras Diferencias
*  ----------
reg cmrdrte cunem cexec d93
predict deltau, residuals
by id: gen deltaur = deltau[_n-1]
reg deltau deltaur, noconst
log close