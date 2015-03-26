%% Mínimos Cuadrados Ordinarios - Taller 1 - Teoría y Práctica de Modelos DSGE
clc
clear all
close all
%% Datos
x1=ones(15,1);
x2=[1:15]';
X=[x1 x2];
Y=[.58 1.1 1.2 1.3 1.95 2.55 2.6 2.9 3.45 3.5 3.6 4.1 4.35 4.4 4.5]';
%% Punto 1)
% b=(X'*X)\(X'*Y)
XtransX=X'*X;
XtransY=X'*Y;
invXtransX=inv(XtransX);
beta0=invXtransX*XtransY;
%% Punto 2)    
%%Para b1 cuando los insumos son 0 la produccion es de 0.4656
%%Para b2 por aumentos de una uniadad en el insumo el producto aumenta en
%%0.2925 unidades
%% Punto 3)
Ygorro0=X*beta0;
Egorro=Y-Ygorro0;
varE=var(Egorro);
%% Punto 4)    
VarCov=varE*invXtransX;
%%Este es el estiamdores. sigma que representa la varianza de los errores
%%que es de 0,6081. La varianza del estimador b0 es igual a
%%0.0128233076611597, la varianza de b1  es 0.000155120657191448 y la
%%covarianza de los 2 estimadores es -0.00124096525753158
%% Punto 5)
%  ln(Y)=beta1*x1+beta2*ln(x2)+e
logx2=log(x2);
logY=log(Y);
logX=[ones(15,1) logx2];
beta1=(logX'*logX)\(logX'*logY);
%% Punto 6)
X8=[1 8];
Y8=X8*beta0;
error8=2.9-Y8;
%%El error estimado es el valor real 2.9000 menos el valor  estimado 2.8053
%%que es igual a 0,0947
lX8=[1 log(8)];
IY8=exp(lX8*beta1);
errorl8=2.9-IY8;
%%El error estimado es el valor real 2.9000 menos el valor estimado
%%2.87633792797228 que es igual a 0.0236620720277179 con relaci�n a la variable realmente
%%observada y no el logaritmo 
%%el error estimado para la regresion exponecial linealiazada es:
Ygorro1=logX*beta1;
figure(1)
plot([Ygorro0 Ygorro1])
legend('Estimación 1 - Rendimientos Constantes','Estimación 2 - Rendimientos Decrecientes')