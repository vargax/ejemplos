function fval=equilibrium(p)
%This function evaluates the vector of equilibrium that corresponds to the
%market specified in the excercise two of homework one. The input is a
%(4,1) vector which values are the following: [p2; x12 ; x22 ; x32].
a=[2 1.5;1.5 2;1.5 2];
v=-[2 0.5;1.5 0.5;0.5 1.5];
e=[2 3;1 2;4 0];

p1=1- p(1);
p2=p(1);
x=[p(2);p(3);p(4)];

for i=1:3
fval(i,:)=p1*(((p1*a(i,2)*(x(i,:)^v(i,2)))/(p2*a(i,1)))^(1/v(i,1)))+(p2*x(i,:))-((p1*e(i,1))+(p2*e(i,2)));
end

fval(4,:)=sum(x)-sum(e(:,2));
end
