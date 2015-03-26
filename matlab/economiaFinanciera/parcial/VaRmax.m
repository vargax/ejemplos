function [nolin,lin]=VaRmax(w,S,VaRmax,gamma)
nolin=VaR(w,S,gamma)-VaRmax;
lin=[];
end
