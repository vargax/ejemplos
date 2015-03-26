function du=d1CRRA(c,gamma)
if gamma==1
    du=c.^(-1);
else
    du=c.^(-gamma);
end
end