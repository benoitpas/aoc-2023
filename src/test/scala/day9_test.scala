import org.junit.Test
import org.junit.Assert._

class Day9Test {

  val report = List("0 3 6 9 12 15", "1 3 6 10 15 21", "10 13 16 21 30 45")

  @Test
  def testNextMeasure1() =
    assertEquals(18, Day9.nextMeasure(report(0)))

  @Test
  def testPart1() =
    assertEquals(114, Day9.part1(report))

}
