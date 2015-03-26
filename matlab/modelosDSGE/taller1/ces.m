function Q=ces(z,K,L,alpha,ro)
% z:    Productividad Multifactorial
% alpha:Participación de cada factor
% ro:   Elasticidad de sustitución
gamma=(ro-1)./ro;
Q=z.*((alpha.*K.^gamma)+((1-alpha).*L.^gamma)).^(1./gamma);
end