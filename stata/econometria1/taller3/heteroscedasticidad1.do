capture log close
clear
cd "C:\Users\Camilo\Documents\Uniandes\200720\Econometría I\Taller3.EconometríaI"
log using heteroscedasticidad1.smcl, replace
use vote1

* -----
* a)
* -----
reg voteA prtystrA democA lexpendA lexpendB
predict voteAaj
label var voteAaj "Valores ajustados"
predict voteArs, residuals
label var voteArs "Residuos"
reg voteArs prtystrA democA lexpendA lexpendB

* -----
* b)
* -----
gen voteArsq = (voteArs)^2
label var voteArsq "Cuadrado de los residuos"
reg voteArsq prtystrA democA lexpendA lexpendB

* -----
* c)
* -----
gen voteAajsq=voteAaj^2
reg voteArsq voteAaj voteAajsq
