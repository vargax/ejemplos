function y=qnorm(x)
n=size(x,3);
m=size(x,2);
for i=1:n
    for j=1:m
        for k=1:m
            y(j,k,i)=x(j,k,i)/((x(j,j,i)*x(k,k,i))^(0.5));
        end
    end
end
end
