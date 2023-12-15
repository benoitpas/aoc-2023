import zio._
import zio.Console._

object Day15 extends ZIOAppDefault {

  def hashCmd(cmd: String): Int =
    cmd.foldLeft(0)((a, c) => {
      val na = (a + c) * 17 % 256
      na
    })

  def part1(cmds: List[String]) =
    cmds.flatMap(_.split(',')).map(hashCmd).sum

  def part2(cmds: List[String]) =
    val m = cmds.flatMap(_.split(',')).foldLeft(Map[Int, List[(String, Int)]]()) { (m, cmd) =>
      (m, cmd.split('=').toList, cmd.split('-').toList) match
        case (m, label :: fl :: _, _) =>
          m.updatedWith(hashCmd(label)) {
            case Some(l) if l.exists(_._1 == label) => Some(l.map(p => if p._1 == label then (label, fl.toInt) else p))
            case Some(l)                            => Some(l.appended((label, fl.toInt)))
            case None                               => Some(List((label, fl.toInt)))
          }
        case (m, _, label :: _) =>
          m.updatedWith(hashCmd(label)) {
            case Some(l) => Some(l.filter(_._1 != label))
            case l       => l
          }
    }
    m.toList.flatMap { case (boxIndex, l) =>
      (l zip (1 to l.length)).map(e => ((boxIndex + 1) * e._2 * e._1._2))
    }.sum

  def run =
    for {
      v <- Day1.readFile("day15_input.txt")
      _ <- printLine(s"part1=${part1(v)}")
      _ <- printLine(s"part2=${part2(v)}")
    } yield ()
}
