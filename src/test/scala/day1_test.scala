import org.junit.Test
import org.junit.Assert._

class Day1Test {
  val calibrationDocument1 = List("1abc2", "pqr3stu8vwx", "a1b2c3d4e5f", "treb7uchet")

  @Test def testDay1Part1(): Unit = {
    assertEquals(1, Day1.firstDigit(calibrationDocument1(0)))
    assertEquals(2, Day1.lastDigit(calibrationDocument1(0)))
    assertEquals(12, Day1.oneLineValue(calibrationDocument1(0)))
    assertEquals(142, Day1.part1(calibrationDocument1))
  }

  val calibrationDocument2 = List(
    "two1nine",
    "eightwothree",
    "abcone2threexyz",
    "xtwone3four",
    "4nineeightseven2",
    "zoneight234",
    "7pqrstsixteen"
  )

  @Test def testDay1Part2(): Unit = {
    assertEquals("2o19e", Day1.replaceSpelledDigit(calibrationDocument2(0)))
    assertEquals("82o3e", Day1.replaceSpelledDigit(calibrationDocument2(1)))
    assertEquals("2o83e", Day1.replaceSpelledDigit("twoeighthree"))
    assertEquals("679e2", Day1.replaceSpelledDigit("6sevenine2"))
    assertEquals(281, Day1.part2(calibrationDocument2))
  }
}
