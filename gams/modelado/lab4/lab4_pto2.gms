$ONTEXT

PROBLEMA: Que baldosas debo levantar para verificar todos los tubos, quiero
levantar la minima cantidad de baldosas => Que baldosas debo levantar?

Se define la matriz B de las baldosas donde b(i,j) = 1 => Levanto la baladosa i,j

OBJETIVO: Minimizar la sumatoria sobre todas las b(i,j)

RESTRICCION: Debo revisar todos los tubos.
Para cada tubo t se define una matriz T en la cual t(i,j) = 1 si el tubo esta bajo
esa baldosa. Para todo tubo t el producto punto entre T*M debe sumar al menos uno.

$OFFTEXT

Sets
i filas      /1*5/
j columnas   /1*4/
t tubos      /1*7/
;

Parameter tuboBajoBaldosa(t,i,j) 1 si tubo t esta bajo baldosa ij/
1.1.1 1,
1.2.1 1,
2.1.2 1,
2.1.3 1,
2.2.2 1,
2.2.3 1,
3.2.1 1,
3.3.1 1,
4.2.4 1,
4.3.4 1,
4.4.4 1,
4.5.3 1,
4.5.4 1,
5.3.1 1,
5.3.2 1,
5.4.1 1,
5.4.2 1,
6.3.2 1,
6.3.3 1,
6.4.2 1,
6.4.3 1,
7.4.1 1,
7.5.1 1
/;

Binary variable b(i,j)   levanto la baldosa en la fila i y columna j
Variable baldosas        baldosas levantadas

Equations
obj              baldosas levantadas
restTubo(t)      cada tubo lo debo revisar al menos bajo una baldosa
;

obj              ..      baldosas =e= sum((i,j), b(i,j));
restTubo(t)      ..      sum((i,j), tuboBajoBaldosa(t,i,j)*b(i,j)) =g= 1;

Model pto2 /all/;
option mip=CPLEX

Solve pto2 using mip minimizing baldosas;

Display b.l
Display baldosas.l

$ONTEXT
----     64 VARIABLE b.L  levanto la baldosa en la fila i y columna j

            1           2           4

1                   1.000
2       1.000                   1.000
3                   1.000
4       1.000


----     65 VARIABLE baldosas.L            =        5.000  baldosas levantadas
$OFFTEXT

