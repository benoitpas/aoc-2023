import zio._
import zio.Console._
import scala.compiletime.ops.string

object Day12 extends ZIOAppDefault {

  def parseArrangements(strings: List[String]) =
    strings.map(_.split(' ').toList match
      case a :: c :: _ => (a, c.split(",").map(_.toInt).toList)
    )

  def opSprings(springs: String) =
    springs
      .appended('.')
      .foldLeft((List[Int](), 0)) {
        case ((l, cnt), '#') => ((l, cnt + 1))
        case ((l, 0), _)     => ((l, 0))
        case ((l, cnt), _)   => ((l.appended(cnt), 0))
      }
      ._1

  def checkMatch(springs: String, n: Int): String =
    val nStr = List.fill(n)('#').mkString.appended('.')
    val springs2 = springs
    if springs2.size < nStr.size - 1 then ""
    else
      (nStr zip springs)
        .foldLeft(Some(""): Option[String]) {
          case (Some(s), (c1, c2)) if c1 == c2 || c2 == '?' => Some(s.appended(c1))
          case _                                            => None
        }
        .getOrElse("")

  def fCombinations(ac: (String, List[Int])): Int = {
    val springs = ac._1
    val numbers = ac._2
    if !springs.contains('?') || springs == "" then
      if opSprings(springs) == numbers then 1
      else 0
    else
      (
        if (springs.head == '#' || springs.head == '?') && !numbers.isEmpty then
          val m = checkMatch(springs, numbers.head)
          if m.size > 0 then
            val rSprings = springs.drop(m.size)
            if numbers.size > 0 then fCombinations((rSprings, numbers.tail))
            else if !rSprings.contains('#') then 1
            else 0
          else 0
        else 0
      )
      + (
        if springs.head == '.' || springs.head == '?' then fCombinations((springs.tail, numbers))
        else 0
      )
  }

  def part1(strings: List[String]) =
    parseArrangements(strings).map(fCombinations(_)).sum

  def part2(strings: List[String]) =
    parseArrangements(strings).map {
      case (springs, numbers) => {
        printLine(springs)
        val springs5 = List.fill(5)(springs).mkString("?")
        val numbers5 = List.fill(5)(numbers).flatten
        fCombinations(springs5, numbers5)
      }
    }.sum

  def run =
    for {
      v <- Day1.readFile("day12_input.txt")
      _ <- printLine(s"part1=${part1(v)}")
      _ <- printLine(s"part2=${part2(v)}")
    } yield ()
}
