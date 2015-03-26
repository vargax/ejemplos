function y=retlog(x)
n=size(x,1);
y=log(x(2:n,:))-log(x(1:(n-1),:));
end