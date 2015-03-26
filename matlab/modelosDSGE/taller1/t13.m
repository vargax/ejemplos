%% Series de tiempo - Taller 1 - Teoría y Práctica de Modelos DSGE
clc
clear all
close all
%% Parámetros
alpha   =1/3;       % Parámetro función de producción
kbarra  =10;        % Constante capital
nbarra  =15;        % Constante trabajo
rok     =4/5;       % AR capital
ron     =1/2;       % AR trabajo
%% Ecuaciones
k=[kbarra zeros(1,50)]';                        % Capital inicial + prealocación
n=[nbarra zeros(1,50)]';                        % Trabajo inicial + prealocación
y=[(k(1)^alpha)*(n(1)^(1-alpha)) zeros(1,50)]'; % Producto en t=1 + prealocación

e0=normrnd(0,2,3,2);                            % Choques
e=zeros(51,2);
    e(02,:)=e0(1,:);
    e(26,:)=e0(2,:);
    e(51,:)=e0(3,:);
clear e0

for i=2:51
    k(i) = kbarra + rok*k(i-1) + e(i,1);
    n(i) = nbarra + ron*n(i-1) + e(i,2);
    y(i) = (k(i)^alpha)*(n(i)^(1-alpha));
end
%% Gráficos
figure(1)
plot([y k n])
legend('Producto','Capital','Trabajo','Location','SouthEast')