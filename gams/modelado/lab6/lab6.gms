$ONTEXT
LABORATORIO 6 - Modelado, Simulacion y Optimizacion
Optimizacion Multiobjetivo :: Metodo de Sumas Ponderadas

PROBLEMA: Modelo de transporte
FUNCIONES OBJETIVO: Minimizar Tiempo :: Minimizar Costo

$OFFTEXT
set i iteraciones /0*10/;
parameter ws(i) vector de pesos /0 0, 1 0.1, 2 0.2, 3 0.3, 4 0.4, 5 0.5, 6 0.6, 7 0.7, 8 0.8, 9 0.9, 10 1/;
scalar w peso /0/;

Set o origenes: plantas / o1*o3 /;
Set d destinos: bodegas / d1*d4 /;

Parameters
sp(o) suministro en la planta /o1 35, o2 50, o3 40/
db(d) demanda de la bodega /d1 45, d2 20, d3 30, d4 30/
;

Table c(o,d) Costo transporte de la planta o a la bodega d
         d1      d2      d3      d4
o1       10      9       10      11
o2       9       10      11      10
o3       11      9       10      10
;

Table t(o,d) Tiempo para llevar un producto de la planta o a la bodega d
         d1      d2      d3      d4
o1       12      14      10      11
o2       11      8       7       13
o3       6       11      4       15
;

Variables
x(o,d)   Cantidad de producto despachado desde la planta o hacia la bodega d
fc       funcion costo    :: Primera funcion a minimizar
ft       funcion tiempo   :: Segunda funcion a minimizar
f        funcion objetivo :: suma ponderada
;
positive variable x;

Equations
funcionObjetivo   Funcion de sumas ponderadas
funcionCosto      Funcion de costo
funcionTiempo     Funcion de tiempo
restSuministro(o) Restriccion de suministro :: No se puede proveer mas de la capacidad de la planta
restDemanda(d)    Restriccion demanda :: No se puede despachar menos de los demandado por la bodega
;

funcionObjetivo  ..      f       =e= w*fc + (1-w)*ft;
funcionCosto     ..      fc      =e= sum((o,d), x(o,d)*c(o,d));
funcionTiempo    ..      ft      =e= sum((o,d), x(o,d)*t(o,d));
restSuministro(o)..      sp(o)   =l= sum(d, x(o,d));
restDemanda(d)   ..      db(d)   =e= sum(o, x(o,d));

Model lab6 /all/;
parameter f_res(i)       Resultados suma ponderada :: Min;
parameter fc_res(i)      Resultados funcion costo  :: Min;
parameter ft_res(i)      Resultados funcion tiempo :: Min;

parameter x_res(o,d,i)   Puntos que minimizan la suma ponderada para cada peso;

loop(i,
     w = ws(i);

     option lp=cplex;
     Solve lab6 using lp minimizing f;

     f_res(i) = f.l;
     fc_res(i)= fc.l;
     ft_res(i)= ft.l;

     x_res(o,d,i) = x.l(o,d);
     );

display f_res, fc_res, ft_res, x_res;

file resultados /C:\users\cvargasc\Mis Documentos\lab6.dat/;
resultados.pc = 5;
put resultados;
loop(i,
     put fc_res(i),ft_res(i) /
     );

$ONTEXT
G e n e r a l   A l g e b r a i c   M o d e l i n g   S y s t e m
E x e c u t i o n


----     77 PARAMETER f_res  Resultados suma ponderada :: Min

0  1000.000,    1  1035.000,    2  1070.000,    3  1105.000,    4  1140.000
5  1160.000,    6  1180.000,    7  1200.000,    8  1209.000,    9  1207.500
10 1185.000


----     77 PARAMETER fc_res  Resultados funcion costo  :: Min

0  1350.000,    1  1350.000,    2  1350.000,    3  1350.000,    4  1350.000
5  1260.000,    6  1260.000,    7  1260.000,    8  1220.000,    9  1200.000
10 1185.000


----     77 PARAMETER ft_res  Resultados funcion tiempo :: Min

0  1000.000,    1  1000.000,    2  1000.000,    3  1000.000,    4  1000.000
5  1060.000,    6  1060.000,    7  1060.000,    8  1165.000,    9  1275.000
10 1425.000


----     77 PARAMETER x_res  Puntos que minimizan la suma ponderada para cada pe
                             so

                0           1           2           3           4           5

o1.d1       5.000       5.000       5.000       5.000       5.000       5.000
o1.d4      30.000      30.000      30.000      30.000      30.000      30.000
o2.d1                                                                  30.000
o2.d2      20.000      20.000      20.000      20.000      20.000      20.000
o2.d3      30.000      30.000      30.000      30.000      30.000
o3.d1      40.000      40.000      40.000      40.000      40.000      10.000
o3.d3                                                                  30.000

    +           6           7           8           9          10

o1.d1       5.000       5.000
o1.d2                               5.000      20.000      20.000
o1.d3                                                      15.000
o1.d4      30.000      30.000      30.000      15.000
o2.d1      30.000      30.000      45.000      45.000      45.000
o2.d2      20.000      20.000       5.000
o2.d4                                           5.000       5.000
o3.d1      10.000      10.000
o3.d2                              10.000
o3.d3      30.000      30.000      30.000      30.000      15.000
o3.d4                                          10.000      25.000



EXECUTION TIME       =        0.001 SECONDS      3 MB  24.4.2 r51415 WEX-VS8
$OFFTEXT