function y=qcorr(x,lamda)
n=size(x,1);
m=size(x,2);

for i=1:m
    for j=1:m
        y(i,j,1)=x(1,i)*x(1,j);
    end
end

for i=2:n
    for j=1:m
        for k=1:m
            y(j,k,i)=((1-lamda)*(x((i-1),j)*x((i-1),k))+lamda*(y(j,k,(i-1))));
        end
    end
end        