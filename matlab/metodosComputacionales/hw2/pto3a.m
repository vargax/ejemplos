function pto3a=pto3a(p)
global x w pbarra
pto3a=(p.^-.2)+(p.^-.5)-w'*(1+max(x,pbarra).^.5);
end