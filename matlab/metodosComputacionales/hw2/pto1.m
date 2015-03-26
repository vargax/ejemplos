function f=pto1(alpha)
global x w
f0=exp(alpha*x-((x.^2)./2));
f1=w'*f0;
f=(alpha*f1)-1;