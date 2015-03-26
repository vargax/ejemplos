clear all
global x w theta
%% Punto 1
disp('Punto 1')
[x,w]=qnwlege(10,0,1);
alfa=broyden('pto1',0)
%% Punto 2
disp('Punto 2')
[x,w]=qnwlogn([10 10], [0 0], [0.02 0.01; 0.01 0.01]);
p=ones(100,1)./2;
p=broyden('pto2',p);
Ep=w'*p
VARp=w'*(p-Ep).^2
%% Punto 3
disp(' ')
disp('Punto 3')
disp('We denote the subsidy expected by sub=E(q(f-p))')
disp('The answer are organized in a vector of the form V=[sub; Ef; Vf; Efq; Vfq]')
disp(' ')
[theta, w]=qnwlogn(10,0,0.03);

for j=0:2
    display(sprintf('For the minimun price equal to %d:',j))
    p=ones(10,1).*0.5;
    p=broyden('pto3',p);
    pbarra=j;
        for i=1:10;
            if      p(i,1)<pbarra f(i,1)=pbarra;
            else    f(i,1)=p(i,1);
            end
        end
    Sub=w'*(theta(:,1).*(1+(f.^0.5)).*(f-p));
    Ef=w'*f;
    Vf=w'*((f-Ef).^2);
    q=(theta(:,1).*(1+(f.^0.5)));
    Efq=w'*(f.*q);
    Vfq=w'*((f.*q-Efq).^2);
    V=[Sub; Ef; Vf; Efq; Vfq]
end
