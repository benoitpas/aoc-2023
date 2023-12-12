import zio._
import zio.Console._

object Day12 extends ZIOAppDefault {

  def aCombinations(arrangement: String) =
    (0 to arrangement.size - 1).foldLeft(List(arrangement)) {
      case (arrangements, i) if arrangement(i) == '?' =>
        arrangements.flatMap((a) => List(a.take(i) + "." + a.drop(i + 1), a.take(i) + "#" + a.drop(i + 1)))
      case (arrangements, _) => arrangements
    }

  def opSprings(springs: String) =
    springs
      .appended('.')
      .foldLeft((List[Int](), 0)) {
        case ((l, cnt), '#') => ((l, cnt + 1))
        case ((l, 0), '.')   => ((l, 0))
        case ((l, cnt), '.') => ((l.appended(cnt), 0))
      }
      ._1

  def fCombinations(ac: (String, List[Int])) =
    aCombinations(ac._1).filter(opSprings(_) == ac._2)

  def part1(strings: List[String]) =
    val arrangements = strings.map(s =>
      s.split(' ').toList match
        case a :: c :: _ => (a, c.split(",").map(_.toInt).toList)
    )
    arrangements.map(fCombinations(_).size).sum

  def run =
    for {
      v <- Day1.readFile("day12_input.txt")
      _ <- printLine(s"part1=${part1(v)}")
    } yield ()
}
