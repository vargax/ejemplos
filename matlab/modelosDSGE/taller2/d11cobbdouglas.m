function Y=d11cobbdouglas(i,p)
alpha=p;
z=i(:,1);
K=i(:,2);
L=i(:,3);
Y=(alpha*z).*(K.^(alpha-1)).*(L.^(1-alpha));
end