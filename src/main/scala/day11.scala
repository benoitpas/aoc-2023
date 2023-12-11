import zio._
import zio.Console._
import Day6.nbDistances

object Day11 extends ZIOAppDefault {

  def findSpaces(coordinates: Set[Long]) =
    val min = coordinates.min
    val max = coordinates.max
    (min to max).toSet.diff(coordinates)

  def getDistance(g1: (Long, Long), g2: (Long, Long), emptyCols: Set[Long], emptyRows: Set[Long], expansion: Long) =
    val cols =
      (g1._1 - g2._1).abs + expansion * emptyCols.count(x => math.min(g1._1, g2._1) < x && x < math.max(g1._1, g2._1))
    val rows =
      (g1._2 - g2._2).abs + expansion * emptyRows.count(y => math.min(g1._2, g2._2) < y && y < math.max(g1._2, g2._2))
    cols + rows

  def part1(space: List[String], expansion: Int) =
    val galaxies = {
      for
        (y, l) <- (1L to space.length) zip space
        (x, c) <- (1L to l.length) zip l
        if c == '#'
      yield (x, y)
    }.toList

    val (xGalaxies, yGalaxies) = galaxies.unzip
    val emptyCols = findSpaces(xGalaxies.toSet)
    val emptyRows = findSpaces(yGalaxies.toSet)
    val distances = galaxies.toList.combinations(2).map { case g1 :: g2 :: _ =>
      getDistance(g1, g2, emptyCols, emptyRows, expansion)
    }
    distances.sum

  def run =
    for {
      v <- Day1.readFile("day11_input.txt")
      _ <- printLine(s"part1=${part1(v, 1)}")
      _ <- printLine(s"part2=${part1(v, 999999)}")
    } yield ()
}
