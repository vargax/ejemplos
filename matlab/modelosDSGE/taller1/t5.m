function f=t5(b,x,y)
L=y-b(1)-(b(2)*exp(x*b(3)));
f=-((100/2)*log(1/2*pi) - (1/2)*(L'*L));
end