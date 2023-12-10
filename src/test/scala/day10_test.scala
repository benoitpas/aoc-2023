import org.junit.Test
import org.junit.Assert._

class Day10Test {

  val maze1 = List("-L|F7", "7S-7|", "L|7||", "-L-J|", "L|-JF")

  val maze2 = List("7-F7-", ".FJ|7", "SJLL7", "|F--J", "LJ.LJ")

  @Test
  def testPart11() =
    assertEquals(4, Day10.part1(maze1))

  @Test
  def testPart12() =
    assertEquals(8, Day10.part1(maze2))

}
