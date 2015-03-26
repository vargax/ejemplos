function du=d1CRRA(i,p)
gamma=p;
c=i;
if gamma==1
    du=c.^(-1);
else
    du=c.^(-gamma);
end
end