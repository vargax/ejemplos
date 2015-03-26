clear
capture log close
cd "C:\Users\Camilo\Documents\Uniandes\200810\Econometría II\taller1"
log using 9.8.smcl,replace
*------------------------------
* Taller 1 - Econometría II
* Ejercicio 9.8 Wooldridge
* -----------------------------
* Manuel Godoy 	cod.200611480
* Camilo Vargas 	cod.200612197
*------------------------------
use jtrain
*-----------
* 9.8 i)
*-----------
reg lscrap grant
*-----------
* Se podría pensar que los factores inobservables en u se correlacionan con subs (grant) por que es probable que las compañías que reciben subsidio proporcionen una tasa de desechos industriales por debajo de lo que realmente obtuvieron, con el fin de hacer que el subsidio parezca conveniente.
*-----------
* 9.8 ii)
*-----------
reg lscrap grant if d88==1
*-----------
* 9.8 iii)
*-----------
reg clscrap grant
*-----------
* Ahora el coeficiente para subs es negativo, mientras que en la regresión original era positivo
* Un incremento de una unidad en el nivel de subsios disminuye en un 17% la cantidad de desechos industriales.
*-----------
test grant
*-----------
* Comparando con el t de tablas (1.83) podemos afirmar que es estadísticamente significativo
*-----------
* 9.8 iv)
*-----------
gen lscrap_87=lscrap
replace lscrap_87=0 if year>1987
replace lscrap_87=0 if lscrap_87==.
reg lscrap grant lscrap_87
test lscrap_87=1
*-----------
* lscrap_87 no es significativo a un nivel de significancia del 5%
* Prob > F =    0.7594
*-----------
* 9.8 v)
*-----------
reg clscrap grant, robust
reg lscrap grant lscrap_87, robust
*-----------
* A diferencia de las regresiones originales hechas en punto iii y iv, los errores estándar son más pequeños, es decir estamos sacrificando eficiencia para obtener consistencia.