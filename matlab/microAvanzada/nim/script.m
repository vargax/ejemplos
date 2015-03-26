%% Nim
clear all
close all
clc
%% Juego inicial
J0=[1 3 5 7]';
l =length(J0);    % Registrando el número de filas del juego
%% Calculando Ganador y primer turno
k=mbin(J0);        
k=nimsum(k);
k=sum(k);
if k==0
    disp('Juego en estado kernel --> Gana el jugador 2')
    J=jposibles(J0);
    for n=1:size(J,1)    % Loop sobre filas (fila sobre la cual juega el perdedor)
        for m=1:size(J,2)% Loop sobre columnas (cantidad removida por el perdedor)
            j=J(n,m);    % Tomando el juego en forma escalar
            j=escalarj(j,l);% Transformando el juego en su forma vectorial
            jopt=nimopt(j); % Aplicando la estrategia óptima para ese estado del juego en particular
            es=j-jopt;      % Obteniendo estrategia aplicada
            for i=1:length(es)  % Registrando estrategia aplicada
                if es(i)~=0     % (La parte real indica la fila y la parte imaginaria la cantidad removida)
                    E(n,m)=complex(i,es(i)); break
                end
            end
            Jopt(n,m)=jescalar(jopt); % Transformando el juego óptimo en su forma escalar y almacenandolo
        end
    end
    %  Almacenando el turno t (matriz de n x m x 3)
    %  J    = (:,:,1) --> Estado inicial del juego
    %  E    = (:,:,2) --> Estrategia aplicada por el ganador
    %  Jopt = (:,:,3) --> Estado final del juego
    for n=1:size(J,1)
        for m=1:size(J,2)
            for i=1:3
                if      i==1, t(n,m,i)=J(n,m);
                elseif  i==2, t(n,m,i)=E(n,m);
                elseif  i==3, t(n,m,i)=Jopt(n,m);
                end
            end
        end
    end
    %  Realizando limpieza
    clear E J Jopt es i j jopt k m n
else
    disp('Juego fuera del estado kernel --> Gana el jugador 1')
    J=nimopt(J0);
    es=J0-J;      % Obteniendo estrategia aplicada
    for i=1:length(es)  % Registrando estrategia aplicada
        if es(i)~=0     % (La parte real indica la fila y la parte imaginaria la cantidad removida)
            E=complex(i,es(i)); break
        end
    end
    t(1,1,1)=jescalar(J0);
    t(1,1,2)=E;
    t(1,1,3)=jescalar(J);
    % Realizando limpieza
    clear E J es i k
end
%% Calculando estrategia conjunta para el ganador
for f=1:size(t,1)
    for c=1:size(t,2)
        if t(f,c,3)==0, break, end
        J=jposibles(escalarj(t(f,c,3),l)); % Calculando las jugadas posibles
        % Calculando estrategia para el turno t, en el estado (f,c)
        for n=1:size(J,1)    % Loop sobre filas (fila sobre la cual juega el perdedor)
            for m=1:size(J,2)% Loop sobre columnas (cantidad removida por el perdedor)
                j=J(n,m);    % Tomando el juego en forma escalar
                j=escalarj(j,l);% Transformando el juego en su forma vectorial
                jopt=nimopt(j); % Aplicando la estrategia óptima para ese estado del juego en particular
                es=j-jopt;      % Obteniendo estrategia aplicada
                for i=1:length(es)  % Registrando estrategia aplicada
                    if es(i)~=0     % (La parte real indica la fila y la parte imaginaria la cantidad removida)
                        E(n,m)=complex(i,es(i)); break
                    end
                end
                Jopt(n,m)=jescalar(jopt); % Transformando el juego óptimo en su forma escalar y almacenandolo
            end
        end
        % Almacenando el turno t (matriz de n x m x 3)
        % J    = : x : x 1 --> Estado inicial del juego
        % E    = : x : x 2 --> Estrategia aplicada por el ganador
        % Jopt = : x : x 3 --> Estado final del juego
        for n=1:size(J,1)
            for m=1:size(J,2)
                for i=1:3
                    if      i==1, T{f,c}(n,m,i)=J(n,m);
                    elseif  i==2, T{f,c}(n,m,i)=E(n,m);
                    elseif  i==3, T{f,c}(n,m,i)=Jopt(n,m);
                    end
                end
            end
        end
        % Realizando limpieza
        clear E J Jopt es i j jopt k m n
    end
end