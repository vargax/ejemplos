toList :: String -> [Integer]
toList input = read("[" ++ input ++ "]")

main = do
  putStrLn "Introduzca una lista de números separados por comas:"
  input <- getLine
  print $ sum (toList input)
