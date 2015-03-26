function y=garchm(x)
n=size(x,1);
m=size(x,2);
for i=1:n
    for j=1:m
        y(j,j,i)=x(i,j);
    end
end
end
    