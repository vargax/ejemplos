$ONTEXT
         ESCENARIO:

                 Fabrica con (ensElectronico, ensMecanico) produce (automoviles, camiones).
                 automoviles y camiones deben pasar por las dos secciones
                 1 camion vale 3 | un automovil vale 2

         RESTRICCIONES:

                 Si ensElectronico(camiones) => 40 camiones/dia | Si ensElectronico(automoviles) => 60 automoviles/dia
                 Si ensMecanico(camiones)    => 50 camiones/dia | Si ensMecanico(automoviles)    => 50 automoviles/dia

         PROBLEMA: Maximizar utilidad sujeto a restricciones de produccion de las dos secciones
$OFFTEXT
VARIABLE u;
POSITIVE VARIABLES camiones, automoviles;

         camiones.L$(automoviles.L eq 0) = 40;

EQUATIONS utilidad, restrMecanico;
         utilidad.. u =e= 3*camiones + 2*automoviles;
         restrMecanico.. camiones + automoviles =l= 50;

MODEL pto3 /ALL/;
SOLVE pto3 USING LP MAXIMIZING u;

DISPLAY u.L, camiones.L, automoviles.L;


