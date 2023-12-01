import org.junit.Test
import org.junit.Assert._

class Day1Test {
  val calibrationDocument = List("1abc2","pqr3stu8vwx","a1b2c3d4e5f","treb7uchet")

  @Test def testDay1(): Unit = {
    assertEquals(1, Day1.firstDigit(calibrationDocument(0)))
    assertEquals(2, Day1.lastDigit(calibrationDocument(0)))
    assertEquals(12, Day1.oneLineValue(calibrationDocument(0)))
    assertEquals(142, Day1.part1(calibrationDocument))
  }

}
