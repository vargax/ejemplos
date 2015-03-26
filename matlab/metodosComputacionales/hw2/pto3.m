function resid=pto3(p)
global theta
resid=(p.^-0.5)+(p.^-0.2)-theta(:,1).*(1+(p.^0.5));
end