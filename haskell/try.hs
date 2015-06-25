main = do
    print "What is your name?"
    name <- getLine
    print ("Hello " ++ name ++ "!")
    
f :: Int -> Int -> Int
f x y = x*x + y^2

dothis = print (f 2 3)

ones = [1,1..]

fib = 1:1:(zipWith (+) fib(tail fib))

f' x = x^2
g x = x+1
h x = 100

sum1 = (+1)
sum2  = (+) 1

prop x = (sum1 x == sum2 x)

abs' x
    | x >= 0 = x
    | otherwise = -x
    
filter' _ []   = []
filter' p (x:xs)
            | p x       = x : (filter' p xs)
            | otherwise = (filter' p xs)
