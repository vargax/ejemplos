clear
capture log close
cd"C:\Users\Camilo\Documents\Uniandes\200720\Econometría I\Taller2.EconometríaI\pto8"
log using pto8.smcl, replace
use MLB1
*****
* A *
*****
reg lsalary years games atbats hruns runs runsyr fldperc yrsallst frstbase scndbase thrdbase shrtstop catcher
*---------- 
* Ho: Catchers y jardineros ganan en promedio la misma suma. 
* B13--> Coeficiente de los Catchers.
*	Ho: beta13 == 0
*	H1: beta13 <> 0
*
* El salario de los catchers es en promedio un 31.3% mayor que el de los jardineros.
* Dado que el t estadistico para el coeficiente de [catcher] es 2.38, podemos afirmar que dicho coeficiente es significativamente diferente de cero, incluso a un nivel de significancia del 95%
*----------
*****
* B *
*****
reg lsalary years games atbats hruns runs runsyr fldperc yrsallst frstbase scndbase thrdbase shrtstop catcher
*----------
* Ho: beta8==beta9==beta10==beta11==beta12==beta13
* H1: beta8<>beta9<>beta10<>beta11<>beta12<>beta13
*----------
test frstbase scndbase thrdbase shrtstop catcher
*----------
* Dado que la masa de probabilidad acumulada es 11.1%, se puede concluir que [frstbase] [scndbase] [thrdbase] [shrtstop] [catcher] son conjuntamente insignificantes incluso a un nivel de significancia del 10%.
*----------
*****
* C *
*****
*----------
* Los resultados obtenidos en los puntos a y b son congruentes, ya que en ambos casos concluimos que los salarios son diferentes de acuerdo a la posición de cada jugador.
*----------