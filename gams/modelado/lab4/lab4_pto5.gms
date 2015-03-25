$ONTEXT

PROBLEMA: Maximizar z sujeto a las restricciones dadas.

$OFFTEXT

Variable z        varible a maximizar

Positive Variables
A        A
B        B
C        C
;
Positive Variable v volumen;
v.LO  = 1;
v.UP = 5;

Positive Variable p presion;
p.LO  = 200;
p.UP = 400;

Positive Variable t temperatura;
t.LO  = 100;
t.UP = 200;

Equations
obj        Funcion objetivo
rest       Restriccion sobre A B y C
;

obj   .. z =e= 300 + 0.8*v + 0.01*p + 0.06*t + 0.001*t*p - 0.01*power(t,2) - 0.001*power(p,2) +11.7*A + 9.4*B + 16.4*C + 19*A*B + 11.4*A*C - 9.6*B*C;
rest  .. A+B+C =e= 1;

Model pto5 /all/;
option nlp = COUENNE

Solve pto5 using nlp maximizing z;

Display v.l;
Display p.l;
Display t.l;
Display A.l;
Display B.l;
Display C.l;
Display z.l;

$ONTEXT
----     39 VARIABLE v.L                   =        5.000  volumen

----     40 VARIABLE p.L                   =      200.000  presion

----     41 VARIABLE t.L                   =      100.000  temperatura

----     42 VARIABLE A.L                   =        0.294  A

----     43 VARIABLE B.L                   =        0.000  B

----     44 VARIABLE C.L                   =        0.706  C

----     45 VARIABLE z.L                   =      209.384  varible a maximizar                                                        r
$OFFTEXT

