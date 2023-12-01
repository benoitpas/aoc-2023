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

Also without the hint on reddit I think it would have taken me a long time to find that'sevenine' should be '79'.
