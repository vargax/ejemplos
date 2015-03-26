function [out1,out2,out3]=f(flag,s,x,e,alpha,beta,gamma,k)
n=length(s);
switch flag
    case 'b'
        out1=zeros(n,1);
        out2=s;
    case 'f'
        out1=(x.^(1-gamma))/(1-gamma);
        out2=x.^-gamma;
        out3=-gamma*(x.^(-gamma-1));
    case 'g'
        out1=alpha*(s-x)-.5*beta*(s-x).^2;
        out2=-alpha+beta*(s-x);
        out3=-beta*ones(n,1);
end