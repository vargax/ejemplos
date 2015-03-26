function Y=cobbdouglas(i,p)
alpha=p;
z=i(:,1);
K=i(:,2);
L=i(:,3);
Y=z.*(K.^alpha).*(L.^(1-alpha));
end