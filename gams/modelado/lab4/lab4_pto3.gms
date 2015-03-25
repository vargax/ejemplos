$ONTEXT

PROBLEMA: Que nadadores seleccionar y a que tipo de nado asignar cada uno,
tal que se minimice el tiempo en la carrera de relevos.

$OFFTEXT

Sets
n Nadadores / 1*6 /
e Estilos / espalda, pecho, mariposa, libre /
;

Table tiempos(e,n)
                 1       2       3       4       5       6
espalda          85      88      87      82      89      86
pecho            78      77      77      76      79      78
mariposa         82      81      82      80      83      81
libre            84      84      86      83      84      85
;

Variable t              tiempo en la carrera :: varible a minimizar;
Binary variable z(n,e)  asignacion  :: es 1 si asigno el nadador n al estilo e;

Equations
tiempo           Funcion objetivo
asigEstilo       Restriccion sobre filas    :: A un estilo solo asigno un nadador
asigNadador      Restriccion sobre columnas :: Cada nadador se asigna a lo sumo a un estilo
;

tiempo           ..      t  =e= sum((n,e), z(n,e)*tiempos(e,n));
asigEstilo(e)    ..      sum(n, z(n,e)) =e= 1;
asigNadador(n)   ..      sum(e, z(n,e)) =l= 1;

Model pto3 /all/;
option mip = CPLEX

Solve pto3 using mip minimizing t;

Display z.l;
Display t.l;

$ONTEXT
----     39 VARIABLE z.L  asignacion  :: es 1 si asigno el nadador n al estilo e

      espalda       pecho    mariposa       libre

1                                           1.000
2                   1.000
4       1.000
6                               1.000


----     40 VARIABLE t.L                   =      324.000  tiempo en la carrera
                                                           :: varible a minimizar
$OFFTEXT

