function d2u=d2CRRA(c,gamma)
if gamma == 1
    d2u=-c.^(-2);
else
    d2u=-gamma*c.^(-gamma-1);
end
end