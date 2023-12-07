import zio._
import zio.Console._

object Day6 extends ZIOAppDefault {

  def raceDistance(raceTime: Long, buttonTime: Long) = buttonTime * (raceTime - buttonTime)

  def raceDistances(time: Int) =
    (1 to time - 1).map(raceDistance(time, _))

  def part1(races: List[String]) =
    def toList(s: String) = s
      .split(':')(1)
      .split(' ')
      .flatMap {
        case "" => None
        case s  => Some(s.toLong)
      }
      .toList
    val times = toList(races(0))
    val distances = toList(races(1))
    (times zip distances)
      .map(nbDistances)
      .reduce(_ * _)

  def nbDistances(raceTime: Long, distanceThreshold: Long) =
    val maxDistance = raceDistance(raceTime, raceTime / 2)
    val b = -raceTime
    val c = distanceThreshold
    val s = math.sqrt(math.pow(b / 2.0, 2) - c)
    val t1 = math.ceil(-b / 2.0 - s).toLong
    val t2 = math.floor(-b / 2.0 + s).toLong
    def fixBorder(t: Long) = (if raceDistance(raceTime, t) <= distanceThreshold then 1 else 0)
    (t2 - t1) + 1 - fixBorder(t1) - fixBorder(t2)

  def part2(races: List[String]) =
    val raceTime :: distanceThreshold :: _ = races.map { s => s.split(':')(1).replace(" ", "").toLong }
    nbDistances(raceTime, distanceThreshold)
  def run =
    for {
      v <- Day1.readFile("day6_input.txt")
      _ <- printLine(s"part1=${part1(v)}")
      _ <- printLine(s"part2=${part2(v)}")
    } yield ()
}
