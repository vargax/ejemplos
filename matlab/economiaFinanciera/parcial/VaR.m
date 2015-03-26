function y=VaR(w,S,gamma)
y=sqrt((w'*S*w))*norminv(gamma);
end