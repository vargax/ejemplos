__NOTOC__

To ''foldr'', ''foldl'' or ''foldl''' that's the question! This article demonstrates the differences between these different folds by a simple example.

If you want you can copy/paste this article into your favorite editor and run it.

We are going to define our own folds so we hide the ones from the Prelude:

<haskell>> import Prelude hiding (foldr, foldl)</haskell>

==Foldr==

Say we want to calculate the sum of a very big list:

<haskell>> veryBigList = [1..1000000]</haskell>

Lets start with the following:

<haskell>
> foldr f z []     = z
> foldr f z (x:xs) = x `f` foldr f z xs

> sum1 = foldr (+) 0

> try1 = sum1 veryBigList
</haskell>

If we evaluate ''try1'' we get:

<tt><nowiki>*** Exception: stack overflow</nowiki></tt>

Too bad... So what happened:
<haskell>
try1 -->
sum1 veryBigList -->
foldr (+) 0 veryBigList -->

foldr (+) 0 [1..1000000] -->
1 + (foldr (+) 0 [2..1000000]) -->
1 + (2 + (foldr (+) 0 [3..1000000])) -->
1 + (2 + (3 + (foldr (+) 0 [4..1000000]))) -->
1 + (2 + (3 + (4 + (foldr (+) 0 [5..1000000])))) -->
-- ...
-- ...  My stack overflows when there's a chain of around 500000 (+)'s !!!
-- ...  But the following would happen if you got a large enough stack:
-- ...
1 + (2 + (3 + (4 + (... + (999999 + (foldr (+) 0 [1000000]))...)))) -->
1 + (2 + (3 + (4 + (... + (999999 + (1000000 + ((foldr (+) 0 []))))...)))) -->

1 + (2 + (3 + (4 + (... + (999999 + (1000000 + 0))...)))) -->
1 + (2 + (3 + (4 + (... + (999999 + 1000000)...)))) -->
1 + (2 + (3 + (4 + (... + 1999999 ...)))) -->

1 + (2 + (3 + (4 + 500000499990))) -->
1 + (2 + (3 + 500000499994)) -->
1 + (2 + 500000499997) -->
1 + 500000499999 -->
500000500000
</haskell>

The problem is that (+) is strict in both of its arguments. This means that both arguments must be fully evaluated before (+) can return a result. So to evaluate:

<haskell>1 + (2 + (3 + (4 + (...))))</haskell>

<tt>1</tt> is pushed on the stack. Then:

<haskell>2 + (3 + (4 + (...)))</haskell>

is evaluated. So <tt>2</tt> is pushed on the stack. Then:

<haskell>3 + (4 + (...))</haskell>

is evaluated. So <tt>3</tt> is pushed on the stack. Then:

<haskell>4 + (...)</haskell>

is evaluated. So <tt>4</tt> is pushed on the stack. Then: ... 

... your limited stack will eventually run full when you evaluate a large enough chain of (+)s. This then triggers the stack overflow exception.

Lets think about how to solve it...  

==Foldl==

One problem with the chain of (+)'s is that it can't be made smaller (reduced) until the very last moment, when it's already too late.

The reason we can't reduce it is that the chain doesn't contain an
expression which can be reduced (a ''redex'', for '''red'''ucible
'''ex'''pression.) If it did we could reduce that expression before going
to the next element.

We can introduce a redex by forming the chain in another way. If
instead of the chain <tt>1 + (2 + (3 + (...)))</tt> we could form the chain
<tt>(((0 + 1) + 2) + 3) + ...</tt>, then there would always be a redex.

We can form such a chain by using a function called ''foldl'':

<haskell>
> foldl f z []     = z
> foldl f z (x:xs) = let z' = z `f` x 
>                    in foldl f z' xs

> sum2 = foldl (+) 0

> try2 = sum2 veryBigList
</haskell>

Lets evaluate ''try2'':

<tt><nowiki>*** Exception: stack overflow</nowiki></tt>

Good Lord! Again a stack overflow! Lets see what happens:

<haskell>
try2 -->
sum2 veryBigList -->
foldl (+) 0 veryBigList -->

foldl (+) 0 [1..1000000] -->

let z1 =  0 + 1
in foldl (+) z1 [2..1000000] -->

let z1 =  0 + 1
    z2 = z1 + 2
in foldl (+) z2 [3..1000000] -->

let z1 =  0 + 1
    z2 = z1 + 2
    z3 = z2 + 3
in foldl (+) z3 [4..1000000] -->

let z1 =  0 + 1
    z2 = z1 + 2
    z3 = z2 + 3
    z4 = z3 + 4
in foldl (+) z4 [5..1000000] -->

-- ... after many foldl steps ...

let z1 =  0 + 1
    z2 = z1 + 2
    z3 = z2 + 3
    z4 = z3 + 4
    ...
    z999997 = z999996 + 999997
in foldl (+) z999997 [999998..1000000] -->

let z1 =  0 + 1
    z2 = z1 + 2
    z3 = z2 + 3
    z4 = z3 + 4
    ...
    z999997 = z999996 + 999997
    z999998 = z999997 + 999998
in foldl (+) z999998 [999999..1000000] -->

let z1 =  0 + 1
    z2 = z1 + 2
    z3 = z2 + 3
    z4 = z3 + 4
    ...
    z999997 = z999996 + 999997
    z999998 = z999997 + 999998
    z999999 = z999998 + 999999
in foldl (+) z999999 [1000000] -->

let z1 =  0 + 1
    z2 = z1 + 2
    z3 = z2 + 3
    z4 = z3 + 4
    ...
    z999997 = z999996 + 999997
    z999998 = z999997 + 999998
    z999999 = z999998 + 999999
    z100000 = z999999 + 1000000
in foldl (+) z1000000 [] -->

let z1 =  0 + 1
    z2 = z1 + 2
    z3 = z2 + 3
    z4 = z3 + 4
    ...
    z999997 = z999996 + 999997
    z999998 = z999997 + 999998
    z999999 = z999998 + 999999
    z100000 = z999999 + 1000000
in z1000000 -->

-- Now a large chain of +'s will be created:

let z1 =  0 + 1
    z2 = z1 + 2
    z3 = z2 + 3
    z4 = z3 + 4
    ...
    z999997 = z999996 + 999997
    z999998 = z999997 + 999998
    z999999 = z999998 + 999999
in z999999 + 1000000 -->

let z1 =  0 + 1
    z2 = z1 + 2
    z3 = z2 + 3
    z4 = z3 + 4
    ...
    z999997 = z999996 + 999997
    z999998 = z999997 + 999998
in  (z999998 + 999999) + 1000000 -->

let z1 =  0 + 1
    z2 = z1 + 2
    z3 = z2 + 3
    z4 = z3 + 4
    ...
    z999997 = z999996 + 999997
in  ((z999997 + 999998) + 999999) + 1000000 -->

let z1 =  0 + 1
    z2 = z1 + 2
    z3 = z2 + 3
    z4 = z3 + 4
    ...
in  (((z999996 + 999997) + 999998) + 999999) + 1000000 -->

-- ...
-- ... My stack overflows when there's a chain of around 500000 (+)'s !!!
-- ... But the following would happen if you got a large enough stack:
-- ...

let z1 =  0 + 1
    z2 = z1 + 2
    z3 = z2 + 3
    z4 = z3 + 4
in  (((((z4 + 5) + ...) + 999997) + 999998) + 999999) + 1000000 -->

let z1 =  0 + 1
    z2 = z1 + 2
    z3 = z2 + 3
in  ((((((z3 + 4) + 5) + ...) + 999997) + 999998) + 999999) + 1000000 -->

let z1 =  0 + 1
    z2 = z1 + 2
in  (((((((z2 + 3) + 4) + 5) + ...) + 999997) + 999998) + 999999) + 1000000 -->

let z1 =  0 + 1
in  ((((((((z1 + 2) + 3) + 4) + 5) + ...) + 999997) + 999998) + 999999) + 1000000 -->

(((((((((0 + 1) + 2) + 3) + 4) + 5) + ...) + 999997) + 999998) + 999999) + 1000000 -->

-- Now we can actually start reducing:

((((((((1 + 2) + 3) + 4) + 5) + ...) + 999997) + 999998) + 999999) + 1000000 -->

(((((((3 + 3) + 4) + 5) + ...) + 999997) + 999998) + 999999) + 1000000 -->

((((((6 + 4) + 5) + ...) + 999997) + 999998) + 999999) + 1000000 -->

(((((10 + 5) + ...) + 999997) + 999998) + 999999) + 1000000 -->

((((15 + ...) + 999997) + 999998) + 999999) + 1000000 -->

(((499996500006 + 999997) + 999998) + 999999) + 1000000 -->

((499997500003 + 999998) + 999999) + 1000000 -->

(499998500001 + 999999) + 1000000 -->

499999500000 + 1000000 -->

500000500000 -->
</haskell>

Well, you clearly see that the redexes are created. But instead of being directly reduced, they are allocated on the heap:

<haskell>
let z1 =  0 + 1
    z2 = z1 + 2
    z3 = z2 + 3
    z4 = z3 + 4
    ...
    z999997 = z999996 + 999997
    z999998 = z999997 + 999998
    z999999 = z999998 + 999999
    z100000 = z999999 + 1000000
in z1000000
</haskell>

Note that your heap is only limited by the amount of memory in your system (RAM and swap). So the only thing this does is filling up a large part of your memory. 

The problem starts when we finally evaluate z1000000:

We must evaluate <tt>z1000000 = z999999 + 1000000</tt>, so <tt>1000000</tt> is pushed on the stack. Then <tt>z999999</tt> is evaluated; <tt>z999999 = z999998 + 999999</tt>, so <tt>999999</tt> is pushed on the stack. Then <tt>z999998</tt> is evaluated; <tt>z999998 = z999997 + 999998</tt>, so <tt>999998</tt> is pushed on the stack. Then <tt>z999997</tt> is evaluated...

...your stack will eventually fill when you evaluate a large enough chain of (+)'s. This then triggers the stack overflow exception.

But this is exactly the problem we had in the foldr case &mdash; only now the chain of (+)'s is going to the left instead of the right.

So why doesn't the chain reduce sooner than
before?

It's because of GHC's lazy reduction strategy: expressions are reduced only when they are actually needed. In this case, the outer-left-most redexes are reduced first. In this case it's the outer <tt>foldl (+) ... [1..10000]</tt>
redexes which are repeatedly reduced. So the inner <tt>z1, z2, z3, ...</tt> redexes only get reduced when the foldl is completely gone.

==Foldl'==

We somehow have to tell the system that the inner redex should be
reduced before the outer. Fortunately this is possible with the
''seq'' function:

<haskell>seq :: a -> b -> b</haskell>

''seq'' is a primitive system function that when applied to ''x'' and
''y'' will first reduce ''x'' then return ''y''. The idea is that ''y'' references ''x'' so that when ''y'' is reduced ''x'' will not be a big unreduced chain anymore.

Now lets fill in the pieces:

<haskell>
> foldl' f z []     = z
> foldl' f z (x:xs) = let z' = z `f` x 
>                     in seq z' $ foldl' f z' xs

> sum3 = foldl' (+) 0

> try3 = sum3 veryBigList
</haskell>

If we now evaluate ''try3'' we get the correct answer and we get it very quickly:

<haskell>500000500000</haskell>

Lets see what happens:

<haskell>
try3 -->
sum3 veryBigList -->
foldl' (+) 0 veryBigList -->

foldl' (+) 0 [1..1000000] -->
foldl' (+) 1 [2..1000000] -->
foldl' (+) 3 [3..1000000] -->
foldl' (+) 6 [4..1000000] -->
foldl' (+) 10 [5..1000000] -->
-- ...
-- ... You see that the stack doesn't overflow
-- ...
foldl' (+) 499999500000 [1000000] -->
foldl' (+) 500000500000 [] -->
500000500000
</haskell>

You can clearly see that the inner redex is repeatedly reduced
first.

==Conclusion==

Usually the choice is between <hask>foldr</hask> and <hask>foldl'</hask>, since <hask>foldl</hask> and <hask>foldl'</hask> are the same except for their strictness properties, so if both return a result, it must be the same. <hask>foldl'</hask> is the more efficient way to arrive at that result because it doesn't build a huge thunk. However, if the combining function is lazy in its ''first'' argument, <hask>foldl</hask> may happily return a result where <hask>foldl'</hask> hits an exception:

<haskell>
> (?) :: Int -> Int -> Int
> _ ? 0 = 0
> x ? y = x*y
>
> list :: [Int]
> list = [2,3,undefined,5,0]
> 
> okey = foldl (?) 1 list
>
> boom = foldl' (?) 1 list
</haskell>

Let's see what happens:

<haskell>
okey -->
foldl (?) 1 [2,3,undefined,5,0] -->
foldl (?) (1 ? 2) [3,undefined,5,0] -->
foldl (?) ((1 ? 2) ? 3) [undefined,5,0] -->
foldl (?) (((1 ? 2) ? 3) ? undefined) [5,0] -->
foldl (?) ((((1 ? 2) ? 3) ? undefined) ? 5) [0] -->
foldl (?) (((((1 ? 2) ? 3) ? undefined) ? 5) ? 0) [] -->
((((1 ? 2) ? 3) ? undefined) ? 5) ? 0 -->
0

boom -->
foldl' (?) 1 [2,3,undefined,5,0] -->
    1 ? 2 --> 2
foldl' (?) 2 [3,undefined,5,0] -->
    2 ? 3 --> 6
foldl' (?) 6 [undefined,5,0] -->
    6 ? undefined -->
*** Exception: Prelude.undefined
</haskell>

Note that even <hask>foldl'</hask> may not do what you expect.
The involved <hask>seq</hask> function does only evaluate the ''top-most constructor''.

If the accumulator is a more complex object, then <hask>fold'</hask> will still build up unevaluated thunks.  You can introduce a function or a strict data type which forces the values as far as you need.  Failing that, the "brute force" solution is to use {{HackagePackage|id=deepseq}}.  For a worked example of this issue, see [http://book.realworldhaskell.org/read/profiling-and-optimization.html#id678431 ''Real World Haskell'' chapter 25].

== See also ==

* [[Fold]]
* [[Foldl as foldr]]

[[Category:FAQ]]
[[Category:Idioms]]

