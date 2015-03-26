%Broyden's Method
function [fval]=potatoesbm(z)
%Parametros:
k=0.2;
delta=0.95;
potencia=5;
%Vector Oferta:
s=[1:3]';
fval=z.^(-inv(potencia))+((z+k)./delta).^(-inv(potencia))-s;
end