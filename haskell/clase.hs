type Mat = [[Int]]
type Vect = [Int]

sProd :: Vect -> Vect -> Int
sProd v v' = sum (zipWith (*) v v')

sProd' :: (Vect, Vect) -> Int
sProd' = sum . (uncurry (zipWith (*)))

sProd'' :: Vect -> Vect -> Int
sProd'' = curry (sum . (uncurry (zipWith (*))))

t1 = [0,1,2]    :: [Int]
t2 = [10,20,30] :: [Int]

m = [t1, t2, t1]

eval :: Matrix -> Vect -> Vect
eval m v = map ( (\ v' -> sProd v' v)) m

tr :: Matrix -> Matrix
tr [] = []
tr [v] = map (\x -> [x]) v
tr (v:vs)
