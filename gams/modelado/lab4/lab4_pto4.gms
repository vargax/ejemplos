$ONTEXT

PROBLEMA: Cuantos productos de cada tipo despachar desde cada almacen hacia cada
tienda minimizando en costo del transporte.

$OFFTEXT

Sets
a Almacenes / 1*3 /
t Tiendas / 1*2 /
p Productos / televisores, grabadoras /
;

Table oferta(p, a)
                 1       2       3
televisores      60      80      50
grabadoras       80      50      50
;

Table demanda(p, t)
                 1       2
televisores      100     90
grabadoras       60      120
;

Table costos(a,t)
         1       2
1        300     500
2        200     300
3        600     300
;

Variable c         costo del transporte :: varible a minimizar;
Positive variable z(p,a,t)  asignacion  :: num productos despachados desde a hacia t;

Equations
costo            Funcion objetivo
restOferta       Restriccion sobre la oferta  :: No puedo despachar mas de los productos disponibles en el almacen
restDemanda      Restriccion sobre la demanda :: Debo satisfacer la demanda de cada tienda
;

costo            ..      c  =e= sum((p,a,t), z(p,a,t)*costos(a,t));
restOferta(p,a)  ..      sum(t, z(p,a,t)) =l= oferta(p,a);
restDemanda(p,t) ..      sum(a, z(p,a,t)) =g= demanda(p,t);

Model pto4 /all/;
option mip = CPLEX

Solve pto4 using mip minimizing c;

Display z.l;
Display c.l;

$ONTEXT
----     51 VARIABLE z.L  asignacion  :: num productos despachados desde a hacia
                          t

                        1           2

televisores.1      60.000
televisores.2      40.000      40.000
televisores.3                  50.000
grabadoras .1      60.000      20.000
grabadoras .2                  50.000
grabadoras .3                  50.000


----     52 VARIABLE c.L                   =   111000.000  costo del transporte
                                                           :: varible a minimiza
                                                           r                                                        r
$OFFTEXT

