$ONTEXT
         ESCENARIO:
                 brownie         400 calorias, 3 chocolate, 2 azucar, 2 grasa    | $50
                 helado          200 calorias, 2 chocolate, 2 azucar, 4 grasa    | $20
                 cocacola        150 calorias, 0 chocolate, 4 azucar, 1 grasa    | $30
                 cheesecake      500 calorias, 0 chocolate, 4 azucar, 5 grasa    | $80

         RESTRICCIONES:

                 calorias        > 500
                 chocolate       > 6
                 azucar          > 10
                 grasa           > 8

         PROBLEMA:  Minimizar costo alimentacion, satisfaciendo los requerimientos nutricionales

$OFFTEXT
SETS
         i alimentos /brownie, helado, cocacola, cheesecake/
         j caracteristicasNutricionales /calorias, chocolate, azucar, grasa/;

PARAMETERS
         d(j) demanda nutricional persona
         / calorias      500
           chocolate     6
           azucar        10
           grasa         8 /

         p(i) precio de cada alimento
         / brownie       50
           helado        20
           cocacola      30
           cheesecake    50 /;

TABLE
         o(i,j) oferta nutricional alimento
                         calorias        chocolate       azucar          grasa
         brownie         400             3               2               2
         helado          200             2               2               4
         cocacola        150             0               4               1
         cheesecake      500             0               4               5       ;

POSITIVE VARIABLE x(i) cantidad consumida del alimento i;
VARIABLE cto costo total de los alimentos consumidos;

EQUATIONS
         costo costo total de los alimentos consumidos
         demanda(j) demanda de cada uno de los factores nutricionales;

         costo.. cto =e= sum(i, x(i)*p(i));
         demanda(j).. sum(i, x(i)*o(i,j)) =g= d(j);

MODEL pto2 /ALL/;
SOLVE pto2 USING LP MINIMIZING cto;

DISPLAY cto.L, x.L;