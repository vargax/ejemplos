clear
capture log close
cd "C:\Users\Camilo\Documents\Uniandes\200810\Econometría II\taller3\pto2"
log using pto2d.smcl, replace
use murder
* ----------
* PUNTO D)
* ----------
*  Estimación del modelo a través de Primeras Diferencias
* ----------
reg cmrdrte cunem cexec d93, robust
* ----------
* PUNTO E)
* ----------
*  Estimación del modelo a través de Efectos Fijos
* ----------
xtreg mrdrte exec unem d90 d93, fe
* ----------
*  Estimación del modelo a través de Efectos Aleatorios
* ----------
xtreg mrdrte exec unem d93, re
* ----------
*  Estimación del modelo a través de MCO
* ----------
reg mrdrte exec unem
log close
