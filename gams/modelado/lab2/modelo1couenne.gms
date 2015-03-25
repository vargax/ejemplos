* Modelado, Optimización y Simulación
* Taller 2
* Camilo Vargas Cabrera (200612197) c.vargas124

* MODELO 1
variables x, y, z;
         positive variable x;
         positive variable y;

equations objetivo, restr1, restr2;
         objetivo..      z =e= 50*x + 100*y;
         restr1..        7*x + 2*y =g= 28;
         restr2..        2*x + 12*y =g= 24;

model modelo1 /ALL/;
option mip = COUENNE;
solve modelo1 using mip minimizing z;

display x.l;
display y.l;
display z.l;