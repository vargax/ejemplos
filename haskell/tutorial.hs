f :: Num a => a -> a -> a
f x y = x*x + y*y

x :: Float
x = 3
y :: Float
y = 2.4

evenSum :: [Integer] -> Integer
evenSum l = accumSum 0 l

accumSum

main = print (f x y)
