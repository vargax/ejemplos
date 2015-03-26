%% Algebra Matricial - Taller 1 - Teoría y Práctica de Modelos DSGE
clc
clear all
close all
%% Punto 1
A=[5 6.5 -9; 1 -4.67 4; 7.6 2 7/3];
%% Punto 2
B=normrnd(5,sqrt(3),3);
%% Punto 3
%  a)
Atrans=A';
Btrans=B';
%  b)
Ainv=inv(A);
Binv=inv(B);
%  c)                   
[vecpA,valpA]=eig(A);   %  La Matriz es indefinida por que los valores son tanto positivas como negativos de fomra imaginaria
[vecpB,valpB]=eig(B);   %  La Matriz es positiva definida por que los valores son positivos (pero estos cambian con la simulacion poor que cambia la simulacion
%  d)
d1=A+B;
d2=A*B;
d3=B/A;
%  e)
e1=A(:,2);
e2=B(3,:);
e3=Ainv(2,2);
e4=d2(3,1);