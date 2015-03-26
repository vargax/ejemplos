%% Funciones - Taller 1 - Teoría y Práctica de Modelos DSGE
clc
clear all
close all
%% Parámetros y cálculo de las funciones
gamma = .8;
alpha = .8;
x=[0:0.1:100]';
crra=CRRA(x,gamma);
d1crra=d1CRRA(x,gamma);
d2crra=d2CRRA(x,gamma);
cara=CARA(x,alpha);
d1cara=d1CARA(x,alpha);
d2cara=d2CARA(x,alpha);
%% Gráficos nivel, primera y segunda derivada
figure(1)
subplot(211)
plot(x,[crra d1crra d2crra])
title 'CRRA (Constant Relative Risk Aversion)'
legend('Nivel','Primera Derivada','Segunda Derivada','Location','Best')
axis([0 10 -5 10])
subplot(212)
plot(x,[cara d1cara d2cara])
title 'CARA (Constant Absolute Risk Aversion)'
legend('Nivel','Primera Derivada','Segunda Derivada','Location','Best')
axis([0 10 -1 1])
%% Coeficientes de Aversión al riesgo
aaCRRA=-(d2crra./d1crra);       % Aversión absoluta al riesgo CRRA
aaCARA=-(d2cara./d1cara);       % Aversión absoluta al riesgo CARA
arCRRA=-(d2crra.*x)./d1crra;    % Aversión relativa al riesgo CRRA
arCARA=-(d2cara.*x)./d1cara;    % Aversión relativa al riesgo CARA
%% Gráficos aversión absoluta y relativa al riesgo
figure(2)                       
subplot(211)                    % Aversión absoluta al riesgo
plot(x,[aaCRRA aaCARA])
title 'Aversión absoluta al riesgo'
legend('CRRA','CARA','Location','Best')
axis([0 10 0 10])
subplot(212)                    % Aversión relativa al riesgo
plot(x,[arCRRA arCARA])
title 'Aversión relativa al riesgo'
legend('CRRA','CARA','Location','Best')
axis([0 10 0 10])
%% Simulación
x=linspace(0,100,101);          % Generando niveles de consumo
alpha=linspace(0.001,1,101);    % Generando coeficientes funciones
[x,alpha]=meshgrid(x,alpha);    % Generando espacio R2
crra=CRRA(x,alpha);             % Calculando CRRA
d1crra=d1CRRA(x,alpha);
d2crra=d2CRRA(x,alpha);
cara=CARA(x,alpha);             % Calculando CARA
d1cara=d1CARA(x,alpha);
d2cara=d2CARA(x,alpha);
%% Gráficos CRRA
figure(3)
mesh(x,alpha,crra)              % CRRA nivel
title 'Aversión Relativa al Riesgo Constante (Nivel)'
xlabel('Consumo')
ylabel('Gamma')
zlabel('Utilidad')
figure(4)
mesh(x,alpha,d1crra)            % CRRA primera derivada
title 'Aversión Relativa al Riesgo Constante (Primera Derivada)'
xlabel('Consumo')
ylabel('Gamma')
zlabel('Utilidad')
figure(5)
mesh(x,alpha,d2crra)            % CRRA segunda derivada
title 'Aversión Relativa al Riesgo Constante (Segunda Derivada)'
xlabel('Consumo')
ylabel('Gamma')
zlabel('Utilidad')
% Estas gráficas nos muestran que ha medida que aumenta el valor de gamma
% la función de utilidad se vuelve más logarítmica y a medida que
% disminuye se vuelve mas lineal por lo que la primera derivada es  mas
% convexa a medida que aumenta gamma y se vuelve una constante a medida que
% disminuye y llega a 0. La segunda derivada es cero cuando esta muy cerca
% a 0 y se vuelve negativa cóncava cuando aumenta a 1. Por lo cual la
% aversión relativa es constante pero la aversión absoluta tiende a ser más
% cóncava.
%% Gráficos CARA
figure(6)
mesh(x,alpha,cara)              % CARA nivel
title 'Aversión Absoluta al Riesgo Constante (Nivel)'
xlabel('Consumo')
ylabel('Gamma')
zlabel('Utilidad')
figure(7)
mesh(x,alpha,d1cara)            % CARA primera derivada
title 'Aversión Absoluta al Riesgo Constante (Primera Derivada)'
xlabel('Consumo')
ylabel('Gamma')
zlabel('Utilidad')
figure(8)
mesh(x,alpha,d2cara)            % CARA segunda derivada
title 'Aversión Absoluta al Riesgo Constante (Segunda Derivada)'
xlabel('Consumo')
ylabel('Gamma')
zlabel('Utilidad')
% Estas gráficas nos muestran que ha medida que aumenta el valor de alpha
% la función de utilidad se vuelve más plana y a medida que
% disminuye se vulve más logaritmica. La segunda derivada es negativa y se vuelve más
% cóncava a medida que alpha aumenta y constante a medida que llega a 0. Por lo cual la
% aversion relativa es constante pero la aversion absoluta el creciente 
% y la funcion de de aversion relativa se va volviendo más empinada a medida que alpha aumenta.
%% Función CES (Elasticidad de sustitución constante)
alpha   =.5;                % Participación de cada factor (Que se supone por facilidad del trabajo)
z       = 1;                % Productividad Multifactorial

ro  =[0:0.1:2 3:30];           % Elasticidad de sustitución

K   =linspace(0,100);          % Capital
L   =linspace(0,100);          % Trabajo
[ro,K,L]=ndgrid(ro,K,L);       % Tripletas (k,l,ro)

Y=ces(z,K,L,alpha,ro);         % Evaluando la función

for i=1:size(Y,1)              % Graficando para todos los ro
k(:,:)=K(i,:,:);
l(:,:)=L(i,:,:);
y(:,:)=Y(i,:,:);
figure(9)
subplot(121)
mesh(k,l,y)
axis([0 100 0 100 0 100])
xlabel 'Capital'
ylabel 'Trabajo'
zlabel 'Producto'
subplot(122)
contour(k,l,y)
xlabel 'Capital'
ylabel 'Trabajo'
end
%%
K=0:100;                        % Graficando para ro->0 ro->1 y ro->inf
L=0:100;

[K L]=ndgrid(K,L);

Y0=ces(1,K,L,.5,.01);
Y1=ces(1,K,L,.5,0.9);
Y2=ces(1,K,L,.5,200);

figure(10)
mesh(K,L,Y0)
title 'Leontief (Sustitutos Perfectos)'
xlabel 'Capital'
ylabel 'Trabajo'
zlabel 'Producto'

figure(11)
mesh(K,L,Y1)
title 'CobbDouglas'
xlabel 'Capital'
ylabel 'Trabajo'
zlabel 'Producto'

figure(12)
mesh(K,L,Y2)
title 'Lineal (Complementos Perfectos)'
xlabel 'Capital'
ylabel 'Trabajo'
zlabel 'Producto'
 % En las simulaciones anteriores se observa que cuando ro
 % tiende a 0, es decir cuando gamma tiende al infinito, la funcion tiende a
 % comportarse como una funcion Leontief de sustitutos perfectos. 
 % Cuando  ro tiende a 1 es decir
 % cuando gamma tiende a 0 la funcion tiende a comportarse como una función
 % Cobb-Douglas y cuando ro tiende a infinito, es decir cuando gama tiende
 % a 0, la funcion se comporta como una funcion lineal. Esto se puede
 % observar por las simulaciones en las que grafican diferntes contornos
 % para diversos valores de ro.