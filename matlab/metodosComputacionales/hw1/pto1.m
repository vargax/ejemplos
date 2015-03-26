%Parametros:
k=0.2;
delta=0.95;
potencia=5;
%Vector Oferta:
s=[1:3]';
%Guess:             ************************
x=[1 0.1 0.01]';    %Para Function Iteration
y=[1 0.1 0.01]';    %Para Método de Newton
z=[1 0.1 0.01]';    %Para Broyden's Method
%Function Iteration
for i=1:10000
xold=x;
x=(s-((x+k)./delta).^(-inv(potencia))).^-potencia;
if abs(x-xold)<1.e-10,break,end
end
disp('Function Iteration:')
disp('Iteraciones:')
i
disp('Matriz de Resultados (s p1 p2 c1 c2):')
p1=x;
p2=(p1+k)./delta;
c1=p1.^(-inv(potencia));
c2=p2.^(-inv(potencia));
s=c1+c2;
R=[s p1 p2 c1 c2]
%Newton's Method
for i=1:10000
    f=y.^(-inv(potencia))+((y+k)./delta).^(-inv(potencia))-s;
    d=(-inv(potencia))*y.^(-inv(potencia)-1)-((1/delta)*inv(potencia))*((y+k)./delta).^(-inv(potencia)-1);
    n=-f./d;
    y=y+n;
    if abs(n)<1.e-10, break, end
end
disp('Método de Newton:')
disp('Iteraciones:')
i
disp('Matriz de Resultados(s p1 p2 c1 c2):')
p1=y;
p2=(p1+k)./delta;
c1=p1.^(-inv(potencia));
c2=p2.^(-inv(potencia));
s=c1+c2;
R=[s p1 p2 c1 c2]
disp('Broydens Method:')
optset('broyden','showiters',1);
disp('Iteraciones:')
z=broyden('potatoesbm',z);
disp('Matriz de Resultados (s p1 p2 c1 c2):')
p1=z;
p2=(p1+k)./delta;
c1=p1.^(-inv(potencia));
c2=p2.^(-inv(potencia));
s=c1+c2;
R=[s p1 p2 c1 c2]
optset('broyden','defaults');