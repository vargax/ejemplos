%% Taller 1 - Teoría y Práctica de Modelos DSGE
% Solución del Modelo de Solow
% Camilo Vargas Cabrera
clc
clear all
close all
%% Parámetros

delta   = .025;             % Depreciación
sigma   = .2;               % Tasa de ahorro
theta   = .3;               % Parámetro función de producción
n       = (1+ .02)^(1/4);   % Tasa de crecimiento de la población

% Nivel de capital inicial para el cálculo de forma recursiva (90% del
% capital per cápita de estado estacionario) calculado de forma analítica.
k0      = .9*((n+delta)/sigma)^(1/(theta-1));
%% Cálculo del estado estacionario de forma recursiva
kt(1,1)=k0;
yt(1,1)=kt(1,1).^theta;
for i=2:1000
    kt(i,1)=((1-delta)*kt(i-1,1)+sigma*yt(i-1,1))/(1+n);
    yt(i,1)=kt(i,1).^theta;
    if abs(kt(i,1)-kt(i-1,1))<1.e-10, break, end
end

figure(1)
x=1:i;
title 'Evolución nivel de capital percápita'
plot(x,kt)

figure(2)
k=linspace(0,.4);   % Capital
y=k.^theta;         % Producto
s=sigma*y;          % Ahorro
x=(n+delta)*k;      % Depreciación
title 'Estado estacionario'
plot(k,[y' s' x'])
legend ('Producto per cápita','Ahorro per cápita','Depreciación per cápita','Location','NorthWest')