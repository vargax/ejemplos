% Convierte un vector binario en decimal
function Y=dec(X)
m=size(X,2);
Y=[2*X(:,1:(m-1)) X(:,m)];
for i=1:(m-1)
        Y(:,i)=(Y(:,i)).^(m-i);
end
Y=sum(Y,2);
end