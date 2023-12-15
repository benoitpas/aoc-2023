import zio._
import zio.Console._

object Day15 extends ZIOAppDefault {

  def hashCmd(cmd:String) : Int = 
    cmd.foldLeft(0)( (a,c) => {
        val na = (a + c)*17 % 256
        na
    })

  def part1(cmds: List[String]) = 
    cmds.flatMap(_.split(',')).map(hashCmd).sum


  def run =
    for {
      v <- Day1.readFile("day15_input.txt")
      _ <- printLine(s"part1=${part1(v)}")
    } yield ()
}
