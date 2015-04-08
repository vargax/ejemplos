Set i subíndice para los lados del triángulo /1*3/
;
Scalar s /30/
;

Variable ar area :: variable a maximizar;
Positive variables l(i) cada uno de los lados;

Equations
area
restPerimetro
restLadoUP
;

area             .. ar =e= sqrt(s*prod(i,(s-l(i))));
restPerimetro    .. sum(i, l(i)) =e= 60;
restLadoUP(i)    .. l(i) =g= 0.0001;

Model pto2 /all/;
option nlp = conopt

Solve pto2 using nlp maximizing ar;
