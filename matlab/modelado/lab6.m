% Laboratorio 6 - Modelado, Simulación y Optimización
clc
close all
clear all

[fc, ft] = textread('lab6.dat','%f,%f')

plot(fc,ft)
title('Frente de Pareto')
xlabel('Funcion Costo')
ylabel('Funcion Tiempo')