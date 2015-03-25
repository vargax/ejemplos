$ONTEXT
         ESCENARIO:

                 comedia --> 7' mujeres,  2' hombres |  50.000 / minuto
                 futbol  --> 2' mujeres, 12' hombres | 100.000 / minuto

         RESTRICCIONES:

                 mujeres > 28'   hombres > 24'

         PROBLEMA: Cuantos comerciales de 1 minuto debo comprar? (en partidos / comedias diferentes)
$OFFTEXT
VARIABLE cto;
POSITIVE VARIABLES numComedias, numPartidos;

EQUATIONS costo, restMujeres, restHombres;
costo.. cto =e= 50*numPartidos + 100*numComedias;
restMujeres.. 7*numComedias + 2*numPartidos =g= 28;
restHombres.. 2*numComedias + 12*numPartidos =g= 24;

MODEL pto1 /ALL/;
SOLVE pto1 USING LP MINIMIZING cto;

DISPLAY numComedias.L, numPartidos.L, cto.L;