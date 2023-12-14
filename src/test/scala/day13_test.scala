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
  def testFindHorizontalReflection() =
    assertEquals(Some(4), Day13.findHorizontalReflection(patterns(1)))

  @Test
  def testFindReflection() =
    assertEquals(3, Day13.findReflection(pattern))

  @Test
  def testPart1() =
    assertEquals(405, Day13.part1(valley))

}
