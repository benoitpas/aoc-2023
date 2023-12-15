import zio._
import zio.Console._

object Day14 extends ZIOAppDefault {

  def parsePlatform(strings: List[String]) = ???

  def part1(platform: List[String]) =
    val nbCol = platform.length
    val rocks =
      for
        (y, l) <- (0 to nbCol - 1) zip platform
        (x, c) <- (0 to l.length - 1) zip l
        if c == '#' || c == 'O'
      yield (c, (x, y))

    // the rocks are already soerted per rows, we will process them in  that order
    // (the rows closest to the north first)
    val roundedRocks = rocks.filter(_._1 == 'O').map(_._2)
    val squareRocks = rocks.filter(_._1 == '#').map(_._2).toSet
    val roundedRocksAfter = roundedRocks.foldLeft(Set[(Int, Int)]()) {
      case (a, (x, y)) => {
        val nY = (1 to y).foldLeft(y) { (y, _) =>
          (y, a((x, y - 1)), squareRocks((x, y - 1))) match
            case (y, false, false) if y > 0 => y - 1
            case _                          => y
        }
        a + ((x, nY))
      }
    }
    roundedRocksAfter.toList.map(nbCol - _._2).sum

  def run =
    for {
      v <- Day1.readFile("day14_input.txt")
      _ <- printLine(s"part1=${part1(v)}")
    } yield ()
}
