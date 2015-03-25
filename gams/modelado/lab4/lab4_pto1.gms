$ONTEXT

PROBLEMA: Como asignar las 4 maquinas a los 4 trabajos minimizando el tiempo
en el que se realizan los 4 trabajos? => Que maquina asigno a que trabajo?

z(m,t) = 1 => Maquina m asignada al trabajo t
z(m,t) = 0 => Maquina m NO asignada al trabajo t

RESTRICCIONES: Cada maquina solo se puede asignar aun trabajo, cada trabajo solo
puede tener una maquina asignada.

Busco una matriz en la cual la suma sobre las filas sea 1 y la suma sobre las
columnas sea 1

$OFFTEXT

Sets
m Maquinas / m1, m2, m3, m4 /
t Trabajos / t1, t2, t3, t4 /
;

Table tiempo(m,t)
         t1      t2      t3      t4
m1       14      5       8       7
m2       2       12      6       5
m3       7       8       3       9
m4       2       4       6       10
;

Variable st              suma tiempo :: varible a minimizar;
Binary variable z(m,t)   asignacion  :: es 1 si asigno la maquina m al trabajo t;

Equations
sumaTiempo       Funcion objetivo
asigMaquina      Restriccion sobre filas    :: Una maquina asignada solo a un trabajo
asigTrabajo      Restriccion sobre columnas :: A un trabajo se asigna una sola maquina
;

sumaTiempo       ..      st =e= sum((m,t), z(m,t)*tiempo(m,t));
asigMaquina(m)   ..      1  =e= sum(t, z(m,t));
asigTrabajo(t)   ..      1  =e= sum(m, z(m,t));

Model pto1 /all/;
option mip = CPLEX

Solve pto1 using mip minimizing st;

Display z.l;
Display st.l;

$ONTEXT
----     48 VARIABLE z.L  asignacion  :: es 1 si asigno la maquina m al trabajo t

            t1          t2          t3          t4

m1                   1.000
m2                                           1.000
m3                               1.000
m4       1.000


----     49 VARIABLE st.L                  =       15.000  suma tiempo :: varible a minimizar
$OFFTEXT

