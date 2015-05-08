% La funcion dijkstra se queda en loop cuando no encuentra una ruta desde origen a destino

clc, clear all, close all

n = 20      % Dimensiones de la matriz
c_min = 1   % Costo minimo entre dos nodos
c_max = 100 % Costo maximo entre dos nodos

iteraciones = 100 % Numero de iteraciones en la simulacion
t_min_gen   = 1   % Tiempo minimo en el que se genera un flujo
t_max_gen   = 10  % Tiempo maximo en el que se genera un flujo
t_dlt_gen   = t_max_gen - t_min_gen % Delta para calcular t_gen aleatorio
t_min_flj   = 3   % Tiempo minimo que dura un flujo
t_max_flj   = 6   % Tiempo maximo que dura un flujo
t_dlt_flj   = t_max_flj - t_min_flj % Delta para calcular t_flj aleatorio

% Genero una matriz de costos aleatoria (se podria definir manualmente)
m_cto =  unidrnd(c_max,[n,n]);          % Tengo n nodos, los costos entre nodos conectados varian entre c_min y c_max
                                        % ¨Desconecto¨ algunos nodos --> El costo entre un nodo y el otro es infinito
tmp = inf*randi([0,1], n, n);              % Genero una matriz de booleans y la multiplico por inf
tmp(isnan(tmp)) = 0;                       % en Octave inf*0 = NaN, luego reemplazo los NaN por ceros para obtener solo los inf
m_cto = m_cto + tmp;                    % Sumo los inf obtenidos a m_cto para desconectar algunos nodos
m_cto = m_cto - diag(diag(m_cto));      % Hago la diagonal cero --> El costo entre un nodo y si mismo es cero
m_cto(isnan(m_cto)) = 0;
m_cto

% Se generan los estados de la matriz de costo para todas las iteraciones
tmp = zeros(i,n,n);
for i=1:iteraciones
  tmp(i,:,:) = m_cto;
end
m_cto = tmp;

for t=1:iteraciones
  fprintf('Iteracion %f \n',t)
  % Genero un flujo entre dos nodos
  origen  = unidrnd(n)  % --> Selecciono el nodo de origen
  destino = unidrnd(n)  % --> Selecciono el nodo de destino
  t_flj   = t_min_flj + unidrnd(t_dlt_flj) % Determino la duracion del flujo

  % Calculo la ruta entre esos dos nodos
  [ruta, cto] = dijkstra(squeeze(m_cto(t,:,:)), origen, destino) % --> Calculo la ruta sobre el estado actual del grafo
  % Deshabilito esos nodos durante un tiempo t_flj
  tmp_t = t; % --> empiezo en el t actual
  while t_flj ~= 0 && tmp_t < iteraciones % --> mientras t_flj no sea 0 y t tmp_t sea menor al total de iteraciones
    for i=ruta % --> Hago filas y columnas igual a inf durante t_flj iteraciones
      m_cto(tmp_t,:,i) = inf;
      m_cto(tmp_t,i,:) = inf;
    end
    tmp_t = tmp_t + 1; 
    t_flj = t_flj - 1; % --> Quedan t_flj - 1 periodos en los cuales estos nodos estaran inactivos
  end
end
