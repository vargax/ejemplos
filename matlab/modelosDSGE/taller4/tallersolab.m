clear;
clc;

% Orden de las variables en kt
%1  k (Predeterminada)
%2  z (Predeterminada)
%3  l (No predeterminada)
%4  c (No predeterminada)
%5  r (No predeterminada)
%6  y (No predeterminada)
%7  x (No predeterminada)

eta=0.3;
alpha=0.3;
delta=0.025;
phi=0.8;
beta=0.98;
chi=4.011;
lbar=0.3;
zbar=1;
shock=normrnd(0,0.0025);


% Estado estacionario

rbar=(1/beta)-(1-delta);
kbar=(((alpha*beta)/(1-beta*(1-delta)))^(1/(1-alpha)))*lbar;
ybar=zbar*(kbar^alpha)*(lbar^(1-alpha));
xbar=delta*kbar;
cbar=(ybar-xbar);
lbarr=(((1-alpha)*ybar)/(chi*cbar))^(1/(1+eta));

disp('Cbar='); disp(cbar);
disp('Ybar='); disp(ybar);
disp('Kbar='); disp(kbar);
disp('Lbar='); disp(lbar);
disp('Rbar='); disp(rbar);
disp('Zbar='); disp(zbar);
disp('Xbar='); disp(xbar);

% Sistema
nvar=7; %Nùmero de variables (kappa)
nk=2;   %Nùmero de variables backward-looking o predeterminadas

A=zeros(nvar,nvar);
B=zeros(nvar,nvar);

%Orden de la variables en la matiz
%cunsumo
%producto
%trabajo
%tasa de interes
%inversion

%ecuacion de euler (consumo)
A(1,3)=1;
A(1,6)=-beta*rbar;
B(1,3)=1;

%ecuacion de oferta de trabajo
B(2,3)=1;
B(2,4)=-1;
B(2,5)=(1+eta);

%ecuacion de  la tasa de interes
B(3,1)=1;
B(3,4)=-1;
B(3,6)=1;

%restriccion presupuestal
B(4,3)=cbar/ybar;
B(4,4)=-1;
B(4,7)=xbar/ybar;

%funcion de produccion
B(5,1)=alpha;
B(5,2)=1;
B(5,4)=-1;
B(5,5)=(1-alpha);

%ecuación de la productividad
A(6,2)=-1;
B(6,2)=-phi;

%ecuacion del capital
A(7,1)=kbar;
B(7,1)=(1+delta)*kbar;
B(7,7)=xbar;

A;
B;

[P,T]=solab(A,B,nk);

disp('Función de política'); disp(P);
disp('Función de transición'); disp(T);


% Funciones de Impulso Respuesta

IRF=irf2(P,T,2,150);

%1  k (Predeterminada)
%2  z (Predeterminada)
%3  h (No predeterminada)
%4  c (No predeterminada)
%5  r (No predeterminada)
%6  y (No predeterminada)

t=(0:size(IRF,1)-1)';
l=IRF(:,5);
c=IRF(:,3);
r=IRF(:,6);                                                                 
y=IRF(:,4);
x=IRF(:,7);
k=IRF(:,1);
z=IRF(:,2);

subplot(4,2,1)
plot(t,l)
title('Horas')

subplot(4,2,2)
plot(t,c)
title('Consumo')

subplot(4,2,3)
plot(t,r)
title('Tasa de Interes')

subplot(4,2,4)
plot(t,y)
title('Producto')

subplot(4,2,5)
plot(t,k)
title('Capital')

subplot(4,2,6)
plot(t,z)
title('Choque de Tecnologia')

subplot(4,2,7)
plot(t,x)
title('Inversión')

shg