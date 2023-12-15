import org.junit.Test
import org.junit.Assert._

class Day13Test {

  val valley = """#.##..##.
..#.##.#.
##......#
##......#
..#.##.#.
..##..##.
#.#.##.#.

#...##..#
#....#..#
..##..###
#####.##.
#####.##.
..##..###
#....#..#""".split('\n').toList

  val pattern = """######..#....#.
######.#.#.####
##..###.##.#..#
......######.#.
#....###...##.#
.#..#.###.#.##.
.#..#..##.#.##.""".split('\n').map(_.toCharArray()).toArray

  val patterns = Day13.parseValley(valley)

  @Test
  def testFindHorizontalReflection1() =
    assertEquals(Some(4), Day13.findHorizontalReflection(patterns(1), 0))

  @Test
  def testFindReflection1() =
    assertEquals(3, Day13.findReflection(0)(pattern))

  @Test
  def testPart1() =
    assertEquals(405, Day13.part12(valley, 0))

  @Test
  def testPart2() =
    assertEquals(400, Day13.part12(valley, 1))

}
