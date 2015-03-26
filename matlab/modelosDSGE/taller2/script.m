%% Teoría y práctica de modelos DSGE - Taller 2 - Modelo de Ramsey
clear all
close all
clc
%  Parámetros
gamma   = .7;           % Elasticidad de sustitución función de utilidad
alpha   = .3;           % Elasticidad función de producción
beta    = .98;          % Tasa subjetiva de descuento
theta   = (1/beta)-1;   % Factor subjetivo de descuento
delta   = 1;            % Tasa de depreciación del capital
T       = 60;           % Periodos simulación
%  Niveles de Estado Estacionario
kss     = ((theta + delta)/alpha)^(1/(alpha-1));
css     = cobbdouglas([1 kss 1],alpha)-delta*kss;
k0      = .7*kss;       % Capital inicial
%  Parametros dependientes de T
Z       = ones(T,1);    % Vector de choques de productividad
z       = ones(T,1);    % Vector de valores esperados de los choques
%  Probando función objetivo (test = zeros(T,2) para todo t evaluando en css y kss, salvo en caso de horizonte finito donde test0(T,1)>0)
test0   = ramsey([css*ones(T,1) kss*ones(T,1)],Z,z,@d1CRRA,@cobbdouglas,@d11cobbdouglas,gamma,alpha,[T beta delta kss 0]);
test1   = ramsey([css*ones(T,1) kss*ones(T,1)],Z,z,@d1CRRA,@cobbdouglas,@d11cobbdouglas,gamma,alpha,[T beta delta kss 1]);
%% 1a) Ramsey Determinístico - Horizonte Finito - beta=.98
%  Parametros
h       = 0;            % Horizonte temporal (0=finito 1=infinito)
%  Raices
foc1a   = fsolve(@(X) ramsey(X,Z,z,@d1CRRA,@cobbdouglas,@d11cobbdouglas,gamma,alpha,[T beta delta k0 h]), [css*ones(T,1) kss*ones(T,1)]);
l1a= [k0; foc1a(:,2)];  % Capital
y1a = cobbdouglas ([ones(T+1,1) l1a ones(T+1,1)], alpha);   % Producto
i1a = y1a(1:T,1)-foc1a(:,1);                                % Inversión
%% 1b) Ramsey - Determinístico - Horizonte Finito - beta=.4
%  Parámetros
beta    = .4;           % Tasa subjetiva de descuento
theta   = (1/beta)-1;   % Factor subjetivo de descuento
kss     = ((theta + delta)/alpha)^(1/(alpha-1));  % Capital Estado Estacionario
css     = cobbdouglas([1 kss 1],alpha)-delta*kss; % Consumo Estado Estacionario
k0      = .7*kss;       % Capital inicial
%  Raices
foc1b   = fsolve(@(X) ramsey(X,Z,z,@d1CRRA,@cobbdouglas,@d11cobbdouglas,gamma,alpha,[T beta delta k0 h]), [css*ones(T,1) kss*ones(T,1)]);
l1b     = [k0; foc1b(:,2)];  % Capital
y1b     = cobbdouglas ([ones(T+1,1) l1b ones(T+1,1)], alpha);   % Producto
i1b     = y1b(1:T,1)-foc1b(:,1);                                % Inversión
%%  1) Gráficos
figure(1)
subplot(221)
plot([foc1a(:,1) foc1b(:,1)])
title 'Consumo Óptimo'
xlabel('t')
ylabel('Consumo')
legend('beta=.98','beta=.4')
subplot(222)
plot([l1a l1b])
title 'Capital Óptimo'
xlabel('t')
ylabel('Capital')
legend('beta=.98','beta=.4')
subplot(223)
plot ([y1a y1b])
title 'Producto Óptimo'
xlabel('t')
ylabel('Producto')
legend('beta=.98','beta=.4')
subplot(224)
plot ([i1a i1b])
title 'Inversión Óptima'
xlabel('t')
ylabel('Inversión')
legend('beta=.98','beta=.4')
%% 2a) Ramsey Determinístico - Horizonte Infinito - k0=.7*kss
%  Parámetros
beta    = .98;          % Tasa subjetiva de descuento
theta   = (1/beta)-1;   % Factor subjetivo de descuento
kss     = ((theta + delta)/alpha)^(1/(alpha-1));  % Capital Estado Estacionario
css     = cobbdouglas([1 kss 1],alpha)-delta*kss; % Consumo Estado Estacionario
k0      = .7*kss;       % Capital inicial
h       = 1;            % Horizonte temporal (0=finito 1=infinito)
%  Raices
foc2a   = fsolve(@(X) ramsey(X,Z,z,@d1CRRA,@cobbdouglas,@d11cobbdouglas,gamma,alpha,[T beta delta k0 h]), [css*ones(T,1) kss*ones(T,1)]);
l2a     = [k0; foc2a(:,2)];
y2a     = cobbdouglas ([ones(T+1,1) l2a ones(T+1,1)], alpha);
i2a     = y2a(1:T,1)-foc2a(:,1);
%% 2b) Ramsey Determinístico - Horizonte Infinito - k0=1.5*kss
%  Parámetros
k0      = 1.5*kss;      % Capital inicial
%  Raices
foc2b   = fsolve(@(X) ramsey(X,Z,z,@d1CRRA,@cobbdouglas,@d11cobbdouglas,gamma,alpha,[T beta delta k0 h]), [css*ones(T,1) kss*ones(T,1)]);
l2b     = [k0; foc2b(:,2)];
y2b     = cobbdouglas([ones(T+1,1) l2b ones(T+1,1)], alpha);
i2b     = y2b(T,1)-foc2b(:,1);
%%  2) Gráficos
figure(2)
subplot(221)
plot([foc2a(:,1) foc2b(:,1)])
title 'Consumo Óptimo'
xlabel('t')
ylabel('Consumo')
legend('k0=.7*kss','k0=1.5*kss')
subplot(222)
plot([l2a l2b])
title 'Capital Óptimo'
xlabel('t')
ylabel('Capital')
legend('k0=.7*kss','k0=1.5*kss')
subplot(223)
plot ([y2a y2b])
title 'Producto Óptimo'
xlabel('t')
ylabel('Producto')
legend('k0=.7*kss','k0=1.5*kss')
subplot(224)
plot ([i2a i2b])
title 'Inversión Óptimo'
xlabel('t')
ylabel('Inversión')
legend('k0=.7*kss','k0=1.5*kss')
%% 3a) Ramsey Estocástico - Horizonte Infinito
%  Parámetros
delta   = .01;                  % Tasa de depreciación del capital
T       = 200;                  % Periodos Simulación
kss     = ((theta + delta)/alpha)^(1/(alpha-1));  % Capital Estado Estacionario
css     = cobbdouglas([1 kss 1],alpha)-delta*kss; % Consumo Estado Estacionario
k0      = kss;                  % Capital Inicial = kss
e       = normrnd(0,.05,T,1);   % Variable Estocástica
ro      = .95;                  % Parámetro proceso autoregresivo
Z       = [exp(e(1)); zeros(T-1,1)];    % Prealocación choques de productividad
for i=2:T                               % Vector choques de productividad
    Z(i)=(Z(i-1)^ro)*exp(e(i));
end
z       = [1; Z(1:T-1)];                % Vector de esperanza de los choques
%  Raices
foc3a   = fsolve(@(X) ramsey(X,Z,z,@d1CRRA,@cobbdouglas,@d11cobbdouglas,gamma,alpha,[T beta delta k0 h]), [css*ones(T,1) kss*ones(T,1)]);
l3a     = [k0; foc3a(1:T-1,2)];                     % Capital
y3a     = cobbdouglas ([Z l3a ones(T,1)], alpha);   % Producto
i3a     = y3a(1:T,1)-foc3a(:,1);                    % Inversión
%  Gráficos
figure(3)
subplot(221)
plot(foc3a(:,1))
title 'Consumo Óptimo'
xlabel('t')
ylabel('Consumo')
subplot(222)
plot(l3a)
title 'Capital Óptimo'
xlabel('t')
ylabel('Capital')
subplot(223)
plot (y3a)
title 'Producto Óptimo'
xlabel('t')
ylabel('Producto')
subplot(224)
plot (z)
title 'Productividad Esperada'
xlabel('t')
ylabel('Productividad')
%% 3a) Ramsey Estocástico - Horizonte Infinito- Choque en el primer periodo
e       = [-.05; zeros(T-1,1)];        % Choque de una desviaci�n estandar negativa
Z       = [exp(e(1)); zeros(T-1,1)];   % Vector de choques de productividad
for i=2:T                              % Vector choques de productividad
    Z(i)=(Z(i-1)^ro)*exp(e(i));
end
z       = [1; Z(1:T-1)];                % Vector de esperanza de los choques
%  Raices
foc3b   = fsolve(@(X) ramsey(X,Z,z,@d1CRRA,@cobbdouglas,@d11cobbdouglas,gamma,alpha,[T beta delta k0 h]), [css*ones(T,1) kss*ones(T,1)]);
l3b     = [k0; foc3b(1:T-1,2)];                     % Capital
y3b     = cobbdouglas ([Z l3b ones(T,1)], alpha);   % Producto
i3b     = y3b(1:T,1)-foc3b(:,1);                    % Inversión
%  Gráficos
figure(4)
subplot(221)
plot(foc3b(:,1))
title 'Consumo Óptimo'
xlabel('t')
ylabel('Consumo')
subplot(222)
plot(l3b)
title 'Capital Óptimo'
xlabel('t')
ylabel('Capital')
subplot(223)
plot (y3b)
title 'Producto Óptimo'
xlabel('t')
ylabel('Producto')
subplot(224)
plot (z)
title 'Productividad Esperada'
xlabel('t')
ylabel('Productividad')