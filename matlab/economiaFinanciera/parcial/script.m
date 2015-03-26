%% Economía Financiera y Administración del Riesgo - Examen Final
close all
clear all
%% Parámetros
lamda   =0.94;      % Parámetro para el cálculo de las correlaciones condicionales (Gamma)
gamma   =0.99;      % Nivel de tolerancia del VaR

%% Cálculos Acciones
p=xlsread('data.xls','datos','b2:d337');        % Importar Precios Acciones
r=retlog(p);                                    % Calcular Retornos Acciones

n=size(r,1);                                    % Calcular retornos esperados de las Acciones (promedio de los retornos históricos)
Er(1,:)=r(1,:);
for i=2:n
    Er(i,:)=mean(r(1:i,:));
end
clear n i

w       =[.000027 .000025 .000016];             % Parametros GARCH
alpha   =[.2685 .3375 .3695];
beta    =[.7314 .6624 .6304];

g=garch(r,w,alpha,beta);        % Calcular GARCH
clear w alpha beta

z=r./g;                         % Calcular Retornos Estandar con mu=0(Z)
G=qcorr(z,lamda);               % Calcular matriz de correlaciones condicionales (Gamma)
G=qnorm(G);                     % Normalizar matriz de correlaciones condicionales (Gamma)

D=sqrt(garchm(g));              % Generar matrices de desviaciones estandar condicionales (D)

n=size(D,3);                    % Generar matrices de Varianza-Covarianza (Sigma)
for i=1:n
    S(:,:,i)=D(:,:,i)*G(:,:,i)*D(:,:,i);
end
clear n i
%% Cálculos Colcap
pcolcap=xlsread('data.xls','datos','e2:e337');  % Importar índice Colcap
rcolcap=retlog(pcolcap);                        % Calcular retornos Colcap

w       =0.0000100741286164726;                 % Parametros GARCH Colcap
alpha   =0.156417447452401;
beta    =0.800006117209916;

gcolcap=garch(rcolcap,w,alpha,beta);            % Calcular GARCH Colcap
clear w alpha beta

VaRcolcap=VaR(1,gcolcap,gamma);                 % Calcular VaR Colcap
%% Maximización del retorno
n=size(r,2);                                    % Número de acciones del portafolio
m=size(r,1);                                    % Número de periodos utilizados
w0=ones(n,1)./n;                                % Valores iniciales de los pesos de cada acción en el portafolio
restig=ones(1,n);                               % Restricciones de igualdad (Suma de los w's = 1)
restig0=1;
restdig=zeros(1,n);                             % Restricciones de desigualdad (Para todo wi: wi>=0)

%% Punto 1) 
% Encontrar la composición óptima del portafolio tal que se
% maximice el retorno del último periodo sujeto a que el valor en riesgo
% del portafolio sea menor a 2.5%

[w,Rp]=fmincon(@(w) retp(w,Er(m,:)),w0,[],[],restig,restig0,restdig,[],@(w) VaRmax(w,S(:,:,m),0.025,gamma));
%% Punto 2) 
% Encontrar las composiciones óptimas del portafolio para todos
% los periodos sujeto a que el valor en riesgo del portafolio sea menor a
% 2.5%
[w(:,1),ERp(1)]=fmincon(@(w) retp(w,Er(1,:)),w0,[],[],restig,restig0,restdig,[],@(w) VaRmax(w,S(:,:,1),0.025,gamma)); % Para el primer periodo iniciando en w0
for i=2:m
    [w(:,i),ERp(i)]=fmincon(@(w) retp(w,Er(i,:)),w(:,i-1),[],[],restig,restig0,restdig,[],@(w) VaRmax(w,S(:,:,i),0.025,gamma)); % Para los siguientes periodos tomando como valores iniciales las composiciones del portafolio del periodo anterior
end
%% Gráficos Punto 2)
figure(1)       % Gráfico composición óptima del portafolio.
x=[1:m];
plot(x,w);
title('Composición óptima del portafolio')
legend('Bancolombia','Ecopetrol','Isagen')
figure(2)       % Valor del portafolio óptimo
y=w'.*p(2:1+size(w,2),:);
y=sum(y,2);
x=1:(size(y,1));
plot(x,y);
title('Valor del portafolio')
figure(3)       % Retornos esperados contra retornos realizados del portafolio
rr=w'.*r;
rr=sum(rr,2);
x=1:(size(rr,1));
Er0=sum(Er,2);
plot(x,[rr Er0])
title('Retornos esperados vs. Retornos realizados del portafolio óptimo')
legend('Retornos Realizados','Retornos Esperados')
figure(4)       % Sumatoria de los pesos óptimos (se observan los puntos donde no se satisface la restricción lineal de fmincon)
w1=sum(w);
x=1:size(w1,2);
plot(x,w1)
title('Sumatoria de los pesos de las acciones')
%% Punto 3)
% Encontrar las composiciones óptimas del portafolio para todos
% los periodos sujeto a que el valor en riesgo del portafolio sea menor al
% valor en riesgo del COLCAP
[w(:,1),ERp(1)]=fmincon(@(w) retp(w,Er(1,:)),w0,[],[],restig,restig0,restdig,[],@(w) VaRmax(w,S(:,:,1),VaRcolcap(1,1),gamma)); % Para el primer periodo iniciando en w0
for i=2:m
    [w(:,i),ERp(i)]=fmincon(@(w) retp(w,Er(i,:)),w(:,i-1),[],[],restig,restig0,restdig,[],@(w) VaRmax(w,S(:,:,i),VaRcolcap(i,1),gamma)); % Para los siguientes periodos tomando como valores iniciales las composiciones del portafolio del periodo anterior
end
%% Gráficos Punto 3)
figure(5)       % Gráfico composición óptima del portafolio.
x=[1:m];
plot(x,w);
title('Composición óptima del portafolio')
legend('Bancolombia','Ecopetrol','Isagen')
figure(6)       % Valor del portafolio óptimo
y=w'.*p(2:1+size(w,2),:);
y=sum(y,2);
x=1:(size(y,1));
plot(x,y);
title('Valor del portafolio')
figure(7)       % Retornos esperados contra retornos realizados del portafolio
rr=w'.*r;
rr=sum(rr,2);
x=1:(size(rr,1));
Er0=sum(Er,2);
plot(x,[rr Er0])
title('Retornos esperados vs. Retornos realizados del portafolio óptimo')
legend('Retornos Realizados','Retornos Esperados')
figure(8)          % Retornos realizados del portafolio contra retornos COLCAP
plot(x, [rr rcolcap])
title('Retornos realizados del portafolio vs. Retornos COLCAP')
legend('Retornos Portafolio','Retornos COLCAP')
figure(9)          % Retornos realizados del portafolio contra retornos COLCAP últimos 60 dias
plot(x, [rr rcolcap])
title('Retornos realizados del portafolio vs. Retornos COLCAP para los últimos 60 días')
legend('Retornos Portafolio','Retornos COLCAP')
axis([(size(x,2)-60) (size(x,2)) -.05 .05])
figure(10)          % Desempeño Portafolio vs. desempeño COLCAP todos los periodos
rra(1)=100;
rcolcapa(1)=100;
for i=1:m
    rra(i+1)=rra(i)*(1+rr(i));
    rcolcapa(i+1)=rcolcapa(i)*(1+rcolcap(i));
end
x=1:size(rra,2);
plot(x,[rra; rcolcapa])
title('Desempeño portafolio vs. desempeño COLCAP')
legend('Desempeño portafolio','Desempeño COLCAP')
clear rra rcolcapa
figure(11)          % Desempeño Portafolio vs. desempeño COLCAP últimos 60 dias
rra(1)=100;
rcolcapa(1)=100;
for i=1:60
    rra(i+1)=rra(i)*(1+rr(m-60+i));
    rcolcapa(i+1)=rcolcapa(i)*(1+rcolcap(m-60+i));
end
x=1:size(rra,2);
plot(x,[rra; rcolcapa])
title('Desempeño portafolio vs. desempeño COLCAP últimos 60 días')
legend('Desempeño portafolio','Desempeño COLCAP')
figure(12)          % Sumatoria de los pesos óptimos (se observan los puntos donde no se satisface la restricción lineal de fmincon)
w1=sum(w);
x=1:size(w1,2);
plot(x,w1)
title('Sumatoria de los pesos de las acciones')
figure(13)          % VaR portafolio vs. VaR COLCAP para todo el periodo
for i=1:m
    VaRp(i,:)=VaR(w(:,i),S(:,:,i),gamma);
end
x=1:size(VaRp,1);
plot(x,[VaRp VaRcolcap])
title('VaR portafolio vs. VaR COLCAP')
legend('VaR portafolio','VaR COLCAP')
figure(14)
plot(x,[VaRp VaRcolcap])
title('VaR portafolio vs. VaR COLCAP')
legend('VaR portafolio','VaR COLCAP')
axis([(size(x,2)-60) (size(x,2)) 0 .07])