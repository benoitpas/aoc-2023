import zio._
import zio.Console._

object Day5 extends ZIOAppDefault {

  def parseAlmanac(lines: List[String]) =
    val lines2 = lines.appended("")
    val groups = lines2.foldLeft((List[List[String]](), List[String]()))((a, l) =>
      l match {
        case "" => (a._1.appended(a._2), List())
        case _  => (a._1, a._2.appended(l))
      }
    )

    val seeds = groups._1(0)(0).split(' ').tail.map(_.toLong).toList
    val maps = groups._1.tail.map { lines =>
      lines.tail.map(l =>
        l.split(' ').map(_.toLong).toList match
          case destination :: source :: length :: _ => (destination - source, source, length)
      )
    }
    (seeds, maps)

  def findElevation(parsedAlmanac: (List[Long], List[List[(Long, Long, Long)]])) =
    val (seeds, maps) = parsedAlmanac
    seeds.map { seed =>
      maps.foldLeft(seed)((s, m) =>
        val next = m.flatMap {
          case (offset, source, length) if (source <= s && s < source + length) => List(s + offset)
          case _                                                                => List()
        }
        next match
          case first :: _ => first
          case _          => s
      )
    }

  def part1(almanac: List[String]) =
    findElevation(parseAlmanac(almanac)).min

  def run =
    for {
      v <- Day1.readFile("day5_input.txt")
      _ <- printLine(s"part1=${part1(v)}")
    } yield ()
}
