import org.junit.Test
import org.junit.Assert._

class Day11Test {

  val space = """...#......
.......#..
#.........
..........
......#...
.#........
.........#
..........
.......#..
#...#.....""".split('\n').toList

  val space2 = List("#..#..#", ".......", ".......", "......#")

  @Test
  def testFindSpaces() =
    assertEquals(Set(3, 6, 9), Day11.findSpaces(Set(1, 2, 4, 5, 7, 8, 10)))

  @Test
  def testPart1() =
    assertEquals(374, Day11.part1(space, 1))

  @Test
  def testPart1b() =
    assertEquals(50, Day11.part1(space2, 1))

  @Test
  def testPart2a() =
    assertEquals(1030, Day11.part1(space, 9))

  @Test
  def testPart2b() =
    assertEquals(8410, Day11.part1(space, 99))

}
