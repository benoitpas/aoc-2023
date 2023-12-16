import org.junit.Test
import org.junit.Assert._

class Day16Test {

  val grid = """.|...\....
|.-.\.....
.....|-...
........|.
..........
.........\
..../.\\..
.-.-/..|..
.|....-|.\
..//.|....""".split('\n').toList

  @Test
  def testPart1() =
    assertEquals(46, Day16.part1(grid))

}
