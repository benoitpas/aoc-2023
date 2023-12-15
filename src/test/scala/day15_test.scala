import org.junit.Test
import org.junit.Assert._

class Day15Test {

  val cmds = List("rn=1,cm-,qp=3,cm=2,qp-,pc=4,ot=9,ab=5,pc-,pc=6,ot=7")

  @Test
  def testPart1() =
    assertEquals(1320, Day15.part1(cmds))

  @Test
  def testPart2() =
    assertEquals(145, Day15.part2(cmds))

}
