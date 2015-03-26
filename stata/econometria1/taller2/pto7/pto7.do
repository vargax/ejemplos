clear
capture log close
cd "C:\Users\Camilo\Documents\Uniandes\200720\Econometría I\Taller2.EconometríaI\pto7"
log using pto7.smcl, replace
use WAGE2
*****
* A *
*****
reg lwage educ exper tenure married black south urban
*----------
* El salario de los negros es en promedio un 18.8% menor que el de los no negros.
*----------
* El estadistico t para el coeficiente de [wage] es -5, por lo cual podemos afirmar que dicho coeficiente es significativamente diferente de cero (con un nivel de significancia del 99%)
*----------
*****
* B *
*****
gen expersquared=exper^2
gen tenuresquared=tenure^2
reg lwage educ exper tenure married black south urban
reg lwage educ exper tenure married black south urban expersquared tenuresquared
*----------
* Ho: beta8 = 0	H1: beta8 <> 0
*     beta9 = 0	H1: beta9 <> 0
* 
*      (0.2550-0.2526)/2
* F = -------------------- = 1.4899
*     (1-0.2550)/(935-9-1)
*
* Utilizando Stata:
*----------
test expersquared tenuresquared
*----------
* Dado que la masa de probabilidad acumulada 22.6%, la cual es mayor al 20%, se puede concluir que [expersquared] y [tenuresquared] son conjuntamente insignificantes incluso a un nivel de significancia del 20%
*----------
*****
* C *
*****
*
*****
* D *
*****
gen snn=(1-married)*(1-black)
gen sn=(1-married)*black
gen cnn=(1-black)*married
gen cn=married*black
label var snn "=1 si soltero no negro"
label var sn "=1 si soltero negro"
label var cnn "=1 si casado no negro"
label var cn "=1 si casado negro"
*----------
* Definimos el grupo de casadas negras como grupo de control
*----------
reg lwage educ exper tenure south urban snn sn cnn
*----------
* El salario de las personas casadas no negras es en promedio un 17.9% mayor que el de las casadas negras.
*----------
* El t estadistico para el coeficiente de [cnn] es 4.43, por lo cual hay evidencia estadistica suficiente para afirmar que dicho coeficiente es significativamente diferente de cero (a un nivel de significancia del 99%).
*----------