clear
capture log close
cd "C:\Users\Camilo\Documents\Uniandes\200810\Econometría II\taller1"
log using 15.12.smcl,replace
*------------------------------
* Taller 1 - Econometría II
* Ejercicio 15.12 Wooldridge
* -----------------------------
* Manuel Godoy 	cod.200611480
* Camilo Vargas 	cod.200612197
*------------------------------
use wage2
*-----------
* 15.12 i)
*-----------
reg lwage sibs
*-----------
* Al comparar el estimador obtenido para sibs en la regresión anterior con el obtenido en el ejemplo 15.2, se observa que para la regresión del ejemplo, un aumento en un año de educación aumenta en un 12.2% el nivel del salario; mientras que el efecto ceteris paribus del número de hermanos sobre el nivel de salario es de -2.7%
*-----------
* 15.12 ii)
*-----------
reg educ brthord
*-----------
* La regresión de educación contra orden de nacimiento muestra que por cada hermano adicional nacido antes, el nivel de educación disminuye en 0.28 años. Este estimador es estadísticamente significativo a un 5% de significancia ( -1.96 > -6.11)
*-----------
* 15.12 iii)
*-----------
ivreg lwage (educ=brthord), first
*-----------
* Se puede observar que la estimación por MC2E utilizando a brthord como variable instrumental, un año de educación adicional aumenta en un 13.06% el nivel de salario. El estimador para educación estadísticamente significativo a un 5% de significancia. 
*-----------
* 15.12 iv)
*-----------
*ho:Igualmente identificada
*h1:Sobreidentificada
*-----------
reg educ sibs brthord
predict ErroresPE, resid
reg lwage educ sibs ErroresPE
predict EMO, resid
reg EMO sibs brthord
scalar rcuadrado=e(r2)
scalar numeroobs=e(N)
scalar estadchi=rcuadrado*numeroobs
scalar list
*-----------
* No podemos rechazar Ho. 
*-----------
* Prueba de Hausman
*-----------
reg educ sibs brthord
predict ErroresE1, resid
reg lwage educ sibs ErroresE1
test ErroresE1
*-----------
* Podemos que ver educ es exógena.
*-----------
* 15.12 v)
*-----------
ivreg lwage (educ=brthord) sibs, first
*-----------
* Los errores estándar para los dos estimadores aumentan como consecuencia de la estimación por MC2E (vs. la estimación por MCO); se observa además que la desviación estandar de la variable instrumentada (educ) aumenta mucho más que la de la variable exógena sibs.
*-----------
* 15.12 vi)
*-----------
reg educ sibs brthord
predict educg
corr educg brthord
*-----------
* La alta correlación se debe a que al utilizar a brthord como instrumento para educación y a partir de ahí obtener educg, ésta termina siendo una combinación lineal de brthord y las demás variables exógenas, es por esto que la correlación es tan alta
*-----------
