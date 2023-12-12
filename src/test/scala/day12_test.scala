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
  def testACombinations() =
    val e = List("....###", "..#.###", ".#..###", ".##.###", "#...###", "#.#.###", "##..###", "###.###")
    assertEquals(e, Day12.aCombinations("???.###"))

  @Test
  def testPart1() =
    assertEquals(21, Day12.part1(arrangements))

}
