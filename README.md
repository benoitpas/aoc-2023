# [Advent of code 2023](https://adventofcode.com/2023) in Scala3

[![Continuous Integration](https://github.com/benoitpas/aoc-2023/actions/workflows/main.yml/badge.svg)](https://github.com/benoitpas/aoc-2023/actions/workflows/main.yml)

Scala3 has matured a lot since 2020, when I last did the [advent of code with it](https://github.com/benoitpas/advent-of-code-2020). 

So after python (and Excel !) in [2021](https://github.com/benoitpas/advent-of-code-2021) and haskell (and Excel again when possible !) in [2922](https://github.com/benoitpas/advent-of-code-2021), let's give it another go ! Probably no Excel this year but maybe a bit of [ZIO](https://typelevel.org/projects/) or the [cats ecosystem](https://typelevel.org/projects/).

## Day1
Part 1 was straightforward and helped me setup my project
Part 2 has a nice twist,because of cases like 'eightwothree' we can use a simple replacement logic like the following:
```
  val spelledDigits = List("zero" -> "0","one" -> "1", "two" -> "2","three" -> "3","four" -> "4","five" -> "5","six" -> "6","seven" -> "7", "eight" -> "8","nine" -> "9")
  def replaceSpelledDigit(s0:String) = spelledDigits.foldLeft(s0)( (s,p) => s.replaceAll(p._1,p._2))``
```
As the 'two' could be replaced before the 'eight'.

Also without the hint on reddit I think it would have taken me a long time to find that pattern like 'sevenine' should be '79'.

## Day2
Part 1 is mostly about parsing the input, I went for a quick and dirty 'split' based solution. No to be used in prod, that would be very fragile !

Part 2 was straightforward, we didn't even need to worry about interger overflows.

## Day 3
For part 1, the most straightforward solution is probably to iterate over the grid, down to bottom and left to right to find the number and determines which ones are close to symbols.

Out of curiosity I'm going to try a solution where I zip the current line with the lines below and above (to find the nearby symbols) and then 'split' the line to find the symbols.

Initially I thought it would be a lot more complex than the iterative approach but it turns out to be quite short.

It may be interesting to try to implement the iterative solution with a state monad.

Part 2 is a lot more complex. With Part 1 we need to find if they are symbols near numbers, part 2 is about finding numbers which are close to symbol `*``.

With an iterative approach, we need to find if they are any digits close to a `*`` and then find the numbers that the digits belong to.

With a declarative approach, we generate the coordinates of all `*` and the coordinates of the areas around the numbers and cpmputing the intersection, we find the numbers close to the `*`. Part1 could also be solved using a similar method.

While implementing part 2, it was quite satisfying to be able to parametrise `horizontalZip()` using a type without having to change the implementation.

## Day 4

Part 1 is really simple, just a matter of converting the list of numbers to set and compute the intersection to find the numbers in common.

Part 2 looks like a good showcase of recursion.

## Day 5

Part 1, no real difficulty, just need to use 64 bits signed ints to store the numbers.

For Part 2, we need to adapt the algorithm to work with ranges instead of numbers. Because there are too many numbers (seeds), we cannot just expand the ranges to number and use part 1.

## Day 6

Part 1, the distances follow a discrete [parabola](https://en.wikipedia.org/wiki/Parabola). To simply count the number of points, it is easier to generate the points and count them. It is probably quite possible to find an analytical solution but the edge cases are probably tricky.

Part 2, as the numbers are much bigger here we need the analytical solution ;-).

## Day 7

Part 1: We need to define the ordering on the hands of cards.

Part 2: When a joker is present, we need to find the best card to 'upgrade' the hand. For example if we have one pair, we can use the joker to go to 2. To choose the card to make the pair, we can choose the 'strongest' of the possible 2 in case our newly created hand is compared to another 'two pair' hand.

## Day 8

Part 1: Here as the 'left/right' commands need to be repeated, in a language like Haskell with lazy evaluation we would have used a foldleft on the commands to find the solution. As Scala foldleft does not support stream (Scala version of lazy lists), I simply used a recursive function with tail recursion.

Part 2: The input data has been very cleverly generated, here the state becomes a list.

## Day 9

Part 1: Quite easy to implement without a loop using a lazy stream (I just remembered doing Day 10 they were deprecated and replaced by LazyList)

Part 2: Adapt the extrapolation algorithm to use the first element of the seqquences instead of the last element.

## Day 10

Part 1: From the start, find all the distances ['breadth' first](https://en.wikipedia.org/wiki/Breadth-first_search) and stop when all positions in the loop have been explored. Implemented using a LazyList.

Part 2:To find the points which are inside or outside, we can count the number of intersection between an outside point and the inside point. if the number is odd, then the point is inside, if it is even then the point is outside.

## Day 11

Part 1: In the previous example, to store the map, I hesitated between using a two dimensional array or a map of the coordinates to the point at the location. Here as the galaxies are quite sparsed, it makes sense to use a map and only keep the coordinates. That will also help when 'expanding' the empty spaces.

Part 2: Here to store the larger coordinates I used Long instead of Int. The expansion coefficient was relatively easy to add

## Day 12

Part 1: I'm going to initially generate all the possible combinations and filter them. There may be a smarter way but let's see if that works.

Part 2: The brute force solution did work for part 1 with only a few seconds way but it is not going to work obviously for part 2. While working on part 1 I thought of a pre-filtering that should keep the number of combinations under control.

Basically, no need to keep the combinations where the number of contiguous '#' is higher than any groups in the expected counts. Looking again at the problem, that may not be enough. A backtracking where we iteratively check the number of combinations may be better.

## Day 13

Part 1: classic AoC problem where we need to look for symetries. To help with the checks of vertical symetries I used array to store the patterns. Thinking about it, I could have used indexed sequenced as well. At least implementing the function that looks for horizontal symetries, I learned about [sameElements](https://nrinaudo.github.io/scala-best-practices/unsafe/array_comparison.html).

Part 2: One way to solve it without increasing the complexity would be to count the number of differences for each possible symetry and keep the ones with only 1 difference.

## Day 14

Part 1: Here are the rocks are quite sparse and the squared one are not going to move, I used Sets to store the coordinates. Then the code to move them is quite straightforward, especially that they were already ordered from north to south so only one pass is necessary to move them all.

Part 2: By changing the order the rounded rocks to also do a one pass processing, the processing time for 1 step should still be linear with the number of rounded rocks. After a number of steps, the movements may repeat themselves so it may be good to memoize some of the transforms.