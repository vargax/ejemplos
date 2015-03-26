% Convierte un vector de números decimales en una matriz binaria
% Depende de la función bin.m
function Y=mbin(X)
n=length(X);
m=length(bin(max(X)));
for i=1:n
    x=bin(X(i));
    if m==length(x);
        Y(i,:)=x;
    else
    Y(i,:)=[zeros(1,m-length(x)) x];
    end
end
end