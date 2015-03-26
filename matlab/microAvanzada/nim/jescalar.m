% Convierte en un escalar el vector que representa el juego (no soporta
% juegos con más de 9 elementos en alguna columna)
function Y=jescalar(X)
n=length(X);
for i=1:n
    Y(n+1-i)=X(n+1-i)*(10^(i-1));
end
Y=sum(Y);
end