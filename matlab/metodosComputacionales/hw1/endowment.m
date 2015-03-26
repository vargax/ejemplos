function fval=endowment(x)
%X vector [x1 x2 x3 p1]
a=[2 1.5; 1.5 2; 1.5 2];
v=[-2 -.5; -1.5 -.5; -.5 -1.5];
e=[2 3; 1 2; 4 0];
for i=1:3
    fval(i,1)=x(4)*((a(i,2)/a(i,1))*(x(4)*(x(i)^(v(i,2)))))^(1/v(i,1))+x(i)-x(4)*e(i,1)-e(i,2);
end
fval(4,1)=sum(x(1:3))-sum(e(:,2));
end