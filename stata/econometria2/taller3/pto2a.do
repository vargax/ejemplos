clear
capture log close
cd "C:\Users\Camilo\Documents\Uniandes\200810\Econometría II\taller3\pto2"
log using pto2a.smcl, replace
use murder
xtset id year
drop if d93==1
* ----------
* PUNTO A)
* ----------
*  Estimación por Efectos Fijos
* ----------
xtreg mrdrte d90 unem exec, fe
estimates store fe
corr _est_fe, _c c
* ----------
*  Estimación por Primeras Diferencias
* ----------
reg cmrdrte cexec cunem
estimates store pd
corr _est_pd, _c c
log close