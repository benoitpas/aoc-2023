import zio._
import zio.Console._
import scala.annotation.tailrec

object Day9 extends ZIOAppDefault {

  def nextMeasure(measureStr: String) =
    val measure = measureStr.split(' ').map(_.toLong).toList
    val derivations = LazyList.iterate(measure)(m => (m.tail zip m).map(_ - _)).takeWhile(_.map(math.abs).sum > 0).toList
    derivations.foldLeft(0L)((a, m) => a + m.last)

  def part1(report: List[String]) = report.map(nextMeasure).sum

  def run =
    for {
      v <- Day1.readFile("day9_input.txt")
      _ <- printLine(s"part1=${part1(v)}")
    } yield ()
}
