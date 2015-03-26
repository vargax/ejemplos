%% Optimizaci�n - Taller 1 - Teoría y Práctica de Modelos DSGE
clc
clear all
close all
%% Punto 1
e=normrnd(0,1,100,1);
x=normrnd(-3,4,100,1);
y=1-2.*(exp(3*x))+e;
v=fminsearch(@(b) t5(b,x,y),[1,-2,3]);
w=fsolve(@(b) tg5(b,x,y),[1,-2,3]);
%% Punto 2
e=normrnd(0,1,100,1);
x=normrnd(100,4,100,1);
z=normrnd(10,sqrt(2),100,1);
y=(0.5*(x.^0.3)+(1-0.5)*(z.^0.3)).^(1/0.3)+e;
g=fminsearch(@(p) t5b(p,x,y,z),[0.5,0.3]);