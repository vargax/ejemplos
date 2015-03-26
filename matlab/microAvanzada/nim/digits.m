% Calcula el número de dígitos enteros de un número
function y=digits(x)
for i=1:2147483647
    if x<10^(i-1)
        y=i-1; break
    end
end
end