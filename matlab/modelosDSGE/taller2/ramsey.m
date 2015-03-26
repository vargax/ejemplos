function Y=ramsey(X,E,e,dcu,y,dky,pu,py,p)
% X:       Niveles de consumo en t (:,1) y capital para t+1 (:,2)
% E:       Vector de variable estocástica z (:,1)
% e:       Vector de valores esperados variable estocástica z (:,1)
% @dcu:    Primera derivada función de utilidad
% @y:      Función de producción
% @dky:    Primera derivada de la función de producción respecto a k
% pu:      Parámetros función de utilidad
% py:      Parámetros función de producción
% p:       Parámetros modelo [T beta delta k0 h]
%           T:      Número de iteraciones
%           beta:   Tasa subjetiva de descuento
%           delta:  Tasa de depreciación del capital
%           k0:     Nivel de capital inicial
%           h:      Horizonte de tiempo (0) finito (1) infinito
Y=zeros(p(5),2);    % Prealocación matriz de resultados  
Y(1,1:2)=[(dcu(X(1,1),pu)/(p(2)*dcu(X(2,1),pu)))-(dky([e(1) X(1,2) 1],py)+(1-p(3))) y([E(1,1) p(4) 1],py)+(1-p(3))*p(4)-(X(1,1)+X(1,2))];
for t=2:(p(1)-1)
    Y(t,1:2)=[(dcu(X(t,1),pu)/(p(2)*dcu(X(t+1,1),pu)))-(dky([e(t) X(t,2) 1],py)+(1-p(3))) y([E(t,1) X(t-1,2) 1],py)+(1-p(3))*X(t-1,2)-(X(t,1)+X(t,2))];
end
    if p(5)==0;
        Y(p(1),1:2)=[X(p(1),2) y([E(p(1),1) X(p(1)-1,2) 1],py)+(1-p(3))*X(p(1)-1,2)-(X(p(1),1)+X(p(1),2))];
    else
        Y(p(1),1:2)=[1/p(2)-(dky([e(p(1)) X(p(1),2) 1],py)+(1-p(3))) y([e(p(1)) X(p(1),2) 1],py)-p(3)*X(p(1),2)-X(p(1),1)];
    end
end