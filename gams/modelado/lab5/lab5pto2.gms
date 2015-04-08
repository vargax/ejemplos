$ONTEXT
LABORATORIO 5 - Modelado, Simulacion y Optimizacion

PROBLEMA: Maximizar el area de un triangulo con perimetro de 60 metros

$OFFTEXT
Positive Variable
a
b
c
;

a.LO = 1;
b.LO = 1;
c.LO = 1;

Scalar s /30/;

Variable ar area :: variable a maximizar;

Equations
area
restPerimetro
;

area             .. ar =e= sqrt(s*(s-a)*(s-b)*(s-c));
restPerimetro    .. a+b+c =e= 60;

Model pto2 /all/;
option nlp = conopt

Solve pto2 using nlp maximizing ar;

Display a.l, b.l, c.l, ar.l;

$ONTEXT
GAMS 24.4.2  r51415 Released Mar 15, 2015 WEX-VS8 x86 32bit/MS Windows 04/07/15 23:42:47 Page 6
G e n e r a l   A l g e b r a i c   M o d e l i n g   S y s t e m
E x e c u t i o n


----     34 VARIABLE a.L                   =       20.000
            VARIABLE b.L                   =       20.000
            VARIABLE c.L                   =       20.000
            VARIABLE ar.L                  =      173.205  area :: variable a ma
                                                           ximizar


EXECUTION TIME       =        0.001 SECONDS      2 MB  24.4.2 r51415 WEX-VS8
$OFFTEXT
