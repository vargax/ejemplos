import Data.List(sort,nub)
{-|
    Se modela el juego como un Integral (natural positivo de longitud arbitraria)
    donde cada dígito representa una pila
        |--> Limitado por construcción a pilas de hasta 9 fichas
        |--> Se ordenan las pilas siempre de menor a mayor número de fichas
        
    l :: lista
    n :: entero
-}

-- Construye las pilas del juego a partir del entero que lo representa
entero2pilas :: Integral a => a -> [a]
entero2pilas n = sort (digitos n)
               where digitos 0 = []
                     digitos n = digitos (div n 10) ++ [mod n 10]

-- Construye el entero que representa el juego a partir de las pilas
pilas2entero :: Integral a => [a] -> a
pilas2entero l = sum $ zipWith (*) potencias $ sort l
               where potencias = [10^n | n <- [s-1,s-2..0]]
                     s = length l
                  
pilas2entero' :: Integral a => [a] -> a
pilas2entero' l = suma (sort l)
                where suma (x:xs) = foldl (\ x y -> 10*x + y) x xs
 
-- Calcula las jugadas posibles para un estado del juego dado
jugadas :: Integral a => a -> [[a]]
jugadas n = (mult 0 . aRemover . reverse . entero2pilas) n
          where aRemover :: Integral a => [a] -> [[a]]
                aRemover [] = []
                aRemover (x:xs) = [1..x] : aRemover xs
                
                mult :: Integral a => a -> [[a]] -> [[a]]
                mult _ [] = []
                mult p (x:xs) = (map (*10^p) x) : (mult (p+1) xs)

-- Calcula los sucesores posibles para un estado del juego dado
sucesores :: Integral a => a -> [a]
sucesores n = (limpieza . map ((-)n) . concat . jugadas) n
            where limpieza :: Integral a => [a] -> [a]
                  limpieza = nub . map (pilas2entero . entero2pilas)
                  
-- Calcula todas las estrategias posibles para un juego inicial dado
estrategia :: Integral a => [a] -> [a]
estrategia [] = []
estrategia l  = l ++ estrategia (sucesor)
               where sucesor = 
                             if sucesores (last l) == []
                             then []
                             else [head (sucesores (last l))]
         
