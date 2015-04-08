% Laboratorio 5 - Modelado, Simulación y Optimización
clc
close all
clear all

'PUNTO 1'
t=-10:0.1:10;   % El tiempo
f=[0.01; 0.1; 10; 100];    % Las frecuencias a las cuales se generara la onda senoidal
C = sin((f*t)'); % Matriz con las diferentes ondas senoidales

'PUNTO 2'
for i=1:size(C,2)
  figure(i)
  plot(t,C(:,i))
  title(strcat('Punto 2 :: Figura ',num2str(i)))
  xlabel('Tiempo')
  ylabel('Amplitud')
end

'PUNTO 3'
figure(size(C,2)+1)
plot(t,C)
title('Punto 3')
xlabel('Tiempo')
ylabel('Amplitud')

'PUNTO 4'
figure(size(C,2)+2)
for i=1:size(C,2)
  subplot(2,2,i)
  plot(t,C(:,i))
  title(strcat('Punto 4 :: Figura ',num2str(i)))
  xlabel('Tiempo')
  ylabel('Amplitud')
end

'PUNTO 5'
R = unidrnd(20,10,10)
maximosFilas = max(R,[],2)

'PUNTO 6'
noRep = zeros(10,10);
for i=1:10
  noRep(i,:) = randperm(20,10);
end
noRep

'PROBLEMA NLP'
lab5pto2(30,20,20,20)