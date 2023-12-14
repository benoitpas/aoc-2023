import zio._
import zio.Console._
import scala.compiletime.ops.string

object Day13 extends ZIOAppDefault {

  def parseValley(strings: List[String]): List[Array[Array[Char]]] =
    strings
      .appended("")
      .foldLeft((List[Array[Array[Char]]](), List[Array[Char]]())) {
        case ((r, l), "") => (r.appended(l.toArray), List())
        case ((r, l), s)  => (r, l.appended(s.toCharArray()))
      }
      ._1

  def findVerticalReflection(pattern: Array[Array[Char]]): Option[Int] =
    val nbCol = pattern(0).length
    val nbRow = pattern.length
    (1 to nbCol - 1)
      .to(LazyList)
      .map { i =>
        val n = math.min(i, nbCol - i)
        // compare colomns
        val eq = (1 to n).foldLeft(true) { case (a, j) =>
          (0 to nbRow - 1).foldLeft(a) { (a, k) => a && pattern(k)(i - j) == pattern(k)(i + j - 1) }
        }
        (eq, i)
      }
      .find(_._1)
      .map(_._2)

  def findHorizontalReflection(pattern: Array[Array[Char]]) =
    val nbRow = pattern.length
    (1 to nbRow - 1)
      .to(LazyList)
      .map { i =>
        val n = math.min(i, nbRow - i)
        // compare rows
        val eq = (1 to n).foldLeft(true) { case (a, j) =>
          a && (pattern(i - j) sameElements pattern(i + j - 1))
        }
        (eq, i)
      }
      .find(_._1)
      .map(_._2)

  def findReflection(pattern: Array[Array[Char]]) =
    findHorizontalReflection(pattern).getOrElse(0) * 100 + findVerticalReflection(pattern).getOrElse(0)

  def part1(strings: List[String]) =
    val patterns = parseValley(strings)
    patterns.map(findReflection).sum

  def run =
    for {
      v <- Day1.readFile("day13_input.txt")
      _ <- printLine(s"part1=${part1(v)}")
    } yield ()
}
