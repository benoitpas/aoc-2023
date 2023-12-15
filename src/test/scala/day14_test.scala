import org.junit.Test
import org.junit.Assert._

class Day14Test {

  val platform = """O....#....
O.OO#....#
.....##...
OO.#O....O
.O.....O#.
O.#..O.#.#
..O..#O..O
.......O..
#....###..
#OO..#....""".split('\n').toList

  @Test
  def testPart1() =
    assertEquals(136, Day14.part1(platform))

}
