import zio._
import zio.Console._

object Day6 extends ZIOAppDefault {

  def raceDistances(time: Int) =
    (1 to time - 1).map(s => (time - s) * s)

  def part1(races: List[String]) =
    def toIntList(s: String) = s
      .split(':')(1)
      .split(' ')
      .flatMap {
        case "" => None
        case s  => Some(s.toInt)
      }
      .toList
    val times = toIntList(races(0))
    val distances = toIntList(races(1))
    (times zip distances)
      .map { (t, d) =>
        raceDistances(t).filter(_ > d).size
      }
      .reduce(_ * _)

  def run =
    for {
      v <- Day1.readFile("day6_input.txt")
      _ <- printLine(s"part1=${part1(v)}")
    } yield ()
}
