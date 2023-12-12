import org.junit.Test
import org.junit.Assert._

class Day12Test {

  val arrangements = List(
    "???.### 1,1,3",
    ".??..??...?##. 1,1,3",
    "?#?#?#?#?#?#?#? 1,3,1,6",
    "????.#...#... 4,1,1",
    "????.######..#####. 1,6,5",
    "?###???????? 3,2,1"
  )

  @Test
  def testPart1() =
    assertEquals(21, Day12.part1(arrangements))

  @Test
  def testFCombinations2a() =
    assertEquals(List("#.#.###"), Day12.fCombinations(("???.###", List(1, 1, 3))))

  @Test
  def testFCombinations2b() =
    assertEquals(10, Day12.fCombinations(("?###????????", List(3, 2, 1))).size)

  @Test
  def testPart2() =
    assertEquals(525152, Day12.part2(arrangements))

}
