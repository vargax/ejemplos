% Toma un juego en su forma escalar y lo transforma en su forma vectorial
% Depende de la función digits y de l (numero de filas del juego original)
function Y=escalarj(x,l)
d=digits(x);
Y(1)=floor(x/(10^(d-1)));
X(1)=Y(1);
for i=2:d
    Y(i)=floor(x/(10^(d-i)))-(10*X(i-1));
    X(i)=floor(x/(10^(d-i)));
end
if length(Y)<l
    Y=[zeros(1,(l-length(Y))) Y];
end
Y=Y';
end
    
    