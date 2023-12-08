import zio._
import zio.Console._
import scala.annotation.tailrec

object Day8 extends ZIOAppDefault {

  def parseMap(lines: List[String]) =
    val pattern = "([A-Z]+) = \\(([A-Z]+), ([A-Z]+)\\)".r
    val directions = lines.head
    val map = lines.tail.tail
      .map(pattern.findFirstMatchIn(_) match
        case Some(m) => (m.group(1), (m.group(2), m.group(3)))
      )
      .toMap
    (directions, map)

  def part1(lines: List[String]) =
    val (cmds, map) = parseMap(lines)
    val cmdsStream = Stream.continually(cmds.toStream).flatten

    // In haskell I would have a use a lazy foldleft on cmdsStream
    @tailrec
    def loop(count: Int, state: String, cmds: Stream[Char]): Int = (count, state, cmds) match
      case (cnt, _state, _) if state == "ZZZ" => count
      case (cnt, state, 'L' #:: _)            => loop(cnt + 1, map(state)(0), cmds.tail)
      case (cnt, state, 'R' #:: _)            => loop(cnt + 1, map(state)(1), cmds.tail)

    loop(0, "AAA", cmds = cmdsStream)

  def run =
    for {
      v <- Day1.readFile("day8_input.txt")
      _ <- printLine(s"part1=${part1(v)}")
    } yield ()
}
