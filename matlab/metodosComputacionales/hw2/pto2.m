function f=pto2(p)
global x
f=(x(:,1).*p.^(-.8))+(x(:,2).*p.^(-.5))-2;
end