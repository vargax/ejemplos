import Data.List (foldl') -- Usar mejor foldl' que foldl --> investigar por que!

f :: Num a => a -> a -> a
f x y = x*x + y*y

x :: Float
x = 3
y :: Float
y = 2.4

-- Version 1
evenSum1 :: [Integer] -> Integer
evenSum1 l = accumSum 0 l
accumSum n l = 
    if l == []
    then n
    else
        let x = head l
            xs = tail l
        in if even x
           then accumSum (n+x) xs
           else accumSum n xs

-- Version 2 --> Generalización del tipo, uso de where (definir función dentro del scope) y let (asignación variable)
evenSum2 :: Integral a => [a] -> a
evenSum2 l = accumSum 0 l
    where accumSum n l =
            if l == []
            then n
            else
                let x  = head l
                    xs = tail l
                in  if even x
                    then accumSum (n+x) xs
                    else accumSum n xs
                    
-- Version 3 --> pattern matching (remover if redundantes)
evenSum3 :: Integral a => [a] -> a
evenSum3 l = accumSum 0 l
    where accumSum n [] = n
          accumSum n (x:xs) =
            if even x
            then accumSum (x+n) xs
            else accumSum n xs
            
-- Version 4 --> n reducing (suprimir l)
evenSum4 :: Integral a => [a] -> a
evenSum4 = accumSum 0
    where accumSum n [] = n
          accumSum n (x:xs) =
            if even x
            then accumSum (x+n) xs
            else accumSum n xs

-- Version 5 --> Higher order functions (functions taking functions as parameters) FILTER
evenSum5 l = mysum 0 (filter even l) -- Aquí ya tengo solo los pares
    where mysum n [] = n             -- luego no tengo que preguntar si es par o impar
          mysum n (x:xs) = mysum (x+n) xs
          
-- Version 6 --> Loop!! loops en haskell! --> foldl
-- foldl f z [] = z
-- foldl f z (x:xs) = foldl f (f z x) xs
evenSum6 l = foldl' mysum 0 (filter even l)
    where mysum acc value = acc + value
    
-- Version 7 --> Lambda notation para eliminar el where
evenSum7 l = foldl' (\x y -> x+y) 0 (filter even l)

-- Version 8
evenSum8 :: Integral a => [a] -> a
evenSum8 l = foldl' (+) 0 (filter even l)

-- Version 9
evenSum9 :: Integral a => [a] -> a
evenSum9 = (foldl' (+) 0) . (filter even)

-- Version 10
sum' :: (Num a) => [a] -> a
sum' = foldl' (+) 0
evenSum10 :: Integral a => [a] -> a
evenSum10 = sum' . (filter even)

-- Square sum
sumSquare :: (Num a) => [a] -> a
sumSquare = foldl' (\ x y -> x + y^2 ) 0
evenSumSquare1 :: Integral a => [a] -> a
evenSumSquare1 = sumSquare . (filter even)

evenSumSquare2 :: Integral a => [a] -> a
evenSumSquare2 = evenSum10 . (map (^2))

evenSumSquare3 :: Integral a => [a] -> a
evenSumSquare3 = sum' . (filter even) . (map (^2))
