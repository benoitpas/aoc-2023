import zio._
import zio.Console._

object Day13 extends ZIOAppDefault {

  def parseValley(strings: List[String]): List[Array[Array[Char]]] =
    strings
      .appended("")
      .foldLeft((List[Array[Array[Char]]](), List[Array[Char]]())) {
        case ((r, l), "") => (r.appended(l.toArray), List())
        case ((r, l), s)  => (r, l.appended(s.toCharArray()))
      }
      ._1

  def findVerticalReflection(pattern: Array[Array[Char]], nbDiff: Int) =
    val nbCol = pattern(0).length
    val nbRow = pattern.length
    (1 to nbCol - 1)
      .to(LazyList)
      .map { i =>
        val n = math.min(i, nbCol - i)
        // compare colomns
        val eq = (1 to n).foldLeft(0) { case (a, j) =>
          (0 to nbRow - 1).foldLeft(a) { (a, k) => a + (if pattern(k)(i - j) == pattern(k)(i + j - 1) then 0 else 1) }
        }
        (eq == nbDiff, i)
      }
      .find(_._1)
      .map(_._2)

  def findHorizontalReflection(pattern: Array[Array[Char]], nbDiff: Int) =
    val nbCol = pattern(0).length
    val nbRow = pattern.length
    (1 to nbRow - 1)
      .to(LazyList)
      .map { i =>
        val n = math.min(i, nbRow - i)
        // compare rows
        val eq = (1 to n).foldLeft(0) { case (a, j) =>
          (0 to nbCol - 1).foldLeft(a) { (a, k) => a + (if pattern(i - j)(k) == pattern(i + j - 1)(k) then 0 else 1) }
//          a + ( if (pattern(i - j) sameElements pattern(i + j - 1)) then 0 else 1)
        }
        (eq == nbDiff, i)
      }
      .find(_._1)
      .map(_._2)

  def findReflection(nbDiff: Int)(pattern: Array[Array[Char]]) =
    findHorizontalReflection(pattern, nbDiff).getOrElse(0) * 100 + findVerticalReflection(pattern, nbDiff).getOrElse(0)

  def part12(strings: List[String], nbDiff: Int) =
    val patterns = parseValley(strings)
    patterns.map(findReflection(nbDiff)).sum

  def run =
    for {
      v <- Day1.readFile("day13_input.txt")
      _ <- printLine(s"part1=${part12(v, 0)}")
      _ <- printLine(s"part2=${part12(v, 1)}")
    } yield ()
}
