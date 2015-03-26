% Convierte un número decimal en binario representado por un vector
function Y=bin(X)
Y(1)=rem(X,2);
x=(X-rem(X,2))/2;
for i=2:2147483647
   Y(i)=rem(x,2);
   x=(x-rem(x,2))/2;
   if x==0, break
   end
end
Y=fliplr(Y);
end