function f=tg5(b,x,y)
L=y-b(1)-b(2)*exp(b(3)*x);
 f=[sum(L);sum(L.*exp(b(3)*x));sum(L.*exp(b(3)*x).*b(2).*x)];
end