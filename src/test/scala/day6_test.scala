import org.junit.Test
import org.junit.Assert._

class Day6Test {

  val races = List("Time:      7  15   30", "Distance:  9  40  200")

  @Test
  def testRaceDistances() =
    assertEquals(List(6, 10, 12, 12, 10, 6), Day6.raceDistances(7))

  @Test
  def testPart1() =
    assertEquals(288, Day6.part1(races))
}
