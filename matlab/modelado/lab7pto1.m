% 1: Para modelar multiples lineas la variable Free pasa de ser un booleano a un entero que indica el numero de lineas libres
% luego si Free > 0 se atiende la llamada.
% 1.1: En el peor de los casos llegan llamadas cada minuto y cada llamada dura 20 minutos, luego para tener un porcentaje de
% aceptacion del 100% seria necesario tener 20 telefonos.


% -------------------------------------------------------------------------------------------------
clc, clear all, close all

%Inicializacion de parmetros y variables
NMAX=100;
Na=0; %nmero de llamadas
Nb=0; %nmero de llamadas aceptadas
Nc=0; %nmero de llamadas finalizadas
N_llamDen=0; %nmero de llamdas denegadas
Free=2; %numero de llamdas que el callcenter puede atender simultaneamente

%inicializacin del tiempo.
t=0;

%vectores para mostrar grafica al final de la simulacin
vec_t=[]; %almacena el valor del tiempo en cada iteracin
vec_porcLlamAcep=[]; %almacena el porcentaje de llamadas aceptadas a medida que avanza el tiempo

%Programacion del evento inicial
evt.t=unidrnd(10); % Genera un numero aleatorio entre 0 y 10
evt.type='A';      % El evento inicial es una llamada nueva

%Programacion en la cola de eventos
evtQueue=evt; % Inicializa la cola de eventos en el evento inicial

%Desarrollo de la simulacion
while length(evtQueue)>0 % Mientras la cola de evento tenga eventos pendientes
    evtAct=evtQueue(1); % El evento actual es el primer elemento de la cola
    evtQueue(1)=[];     % El primer elemento de la cola de evento se inicializa como un arreglo vacio
    
    t=evtAct.t; % El tiempo de la iteracin actual es el tiempo del evento actual
    
    %Procesamiento del evento
    if evtAct.type=='A' % Si el evento actual es una nueva llamada
        %Modificacion de variables
        Na=Na+1; % Incremento en 1 el numero de llamadas
      
        %Programacion de nuevos eventos
        newEvt.t=t; % El tiempo del siguiente evento es el mismo tiempo del evento actual (la llamada de se contesta si retraso)
        newEvt.type='B'; % El siguiente evento deberia ser atender la llamada
        evtQueue=[evtQueue newEvt]; % A la cola de eventos se concatena el nuevo evento
        if Na<NMAX
            newEvt.t=t+unidrnd(10); % Genero la siguiente llamada con un retraso entre 1 y 10 minutos
            newEvt.type='A'; 
            evtQueue=[evtQueue newEvt];
        end
        fprintf('Ocurre evento A: Llamada nueva \n');
    end
    
    if evtAct.type=='B' % Si el evento actual es atender una llamada
        %Modificacion de variables
        if Free > 0  % Si tengo al menos un telefono disponible
            Free = Free - 1; % Paso a a tener una linea menos disponible
            
            Nb=Nb+1; % Incremento en 1 el numero de llamdas atendidas
                        
            newEvt.t=t+unidrnd(20); % El tiempo del nuevo evento es el tiempo actual + rand(20) (tiempo maximo que tardo en atender la llamada)
            newEvt.type='C'; % El siguiente evento deberia ser terminar de atender la llamada
            evtQueue=[evtQueue newEvt]; % A la cola de eventos se concatena el nuevo evento
            fprintf('Llamada aceptada. \n');
                    
        end
        fprintf('Ocurre evento B: Atencin de la llamada \n');
    end
    
    if evtAct.type=='C' % Si el evento actual es la finalizacion de una llamada
        %Modificacion de variables
        Nc=Nc+1; % Incremento en 1 el numero de llamadas finalizadas
        Free = Free + 1; % Tengo una linea mas disponible para atender llamadas
        fprintf('Ocurre evento C: Finalizacin de la llamada \n');
    end
    
    vec_t=[vec_t t]; % Concateno al vector de tiempo el tiempo actual
    porcLlamAcep=(Nb/Na)*100; % En este periodo de tiempo la probabilidad de atender la llamada es las llamadas aceptadas / el total
    vec_porcLlamAcep=[vec_porcLlamAcep porcLlamAcep]; % Concateno las probabilidades que llevo con la probabilidad actual
    
    
    %Variables de estado actuales
    fprintf('T=%f -> Na=%d, Nb=%d, Free=%d PorcLlamAcep=%2d \n',t, Na, Nb, Free, porcLlamAcep);
    
    %Organizacion de la cola de eventos
    flag=1;
    while flag==1 % Organiza la cola en funcion del tiempo (algoritmo burbuja)
        flag=0; 
        for i=1:(length(evtQueue)-1) 
            if evtQueue(i).t>evtQueue(i+1).t
                temp=evtQueue(i);
                evtQueue(i)=evtQueue(i+1);
                evtQueue(i+1)=temp;
                flag=1; 
            end
        end
    end
    %Mostrado de la cola de eventos:
    fprintf('\nCola de eventos:\n');
    for i=1:length(evtQueue)
        fprintf('Evento %s en t=%f\n',evtQueue(i).type,evtQueue(i).t);
    end
    fprintf('----------------\n');
    %pause;
end

figure
plot(vec_t,vec_porcLlamAcep, '-o')
title('Desempeo del Callcenter');
xlabel('Tiempo [min]');
ylabel('% de aceptacin de llamadas');
ylim([0 110])


