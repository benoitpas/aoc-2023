import org.junit.Test
import org.junit.Assert._

class Day8Test {

  val map1 = """RL

AAA = (BBB, CCC)
BBB = (DDD, EEE)
CCC = (ZZZ, GGG)
DDD = (DDD, DDD)
EEE = (EEE, EEE)
GGG = (GGG, GGG)
ZZZ = (ZZZ, ZZZ)""".split('\n').toList

  val map2 = """LLR

AAA = (BBB, BBB)
BBB = (AAA, ZZZ)
ZZZ = (ZZZ, ZZZ)""".split('\n').toList

  @Test
  def testPart1a() =
    assertEquals(2, Day8.part1(map1))

  @Test
  def testPart1b() =
    assertEquals(6, Day8.part1(map2))
}
