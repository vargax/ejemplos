function y=garch(x,w,alpha,beta)
n=size(x,1);
m=size(x,2);
y(1,:)=var(x);
for j=1:m
    for i=2:n
        y(i,j)=w(j)+(alpha(j).*(x(i-1,j)^2))+(beta(j).*y(i-1,j));
    end
end
end
    
