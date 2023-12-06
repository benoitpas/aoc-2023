import org.junit.Test
import org.junit.Assert._
import Day5.parseAlmanac

class Day5Test {

  val almanac = List(
    "seeds: 79 14 55 13",
    "",
    "seed-to-soil map:",
    "50 98 2",
    "52 50 48",
    "",
    "soil-to-fertilizer map:",
    "0 15 37",
    "37 52 2",
    "39 0 15",
    "",
    "fertilizer-to-water map:",
    "49 53 8",
    "0 11 42",
    "42 0 7",
    "57 7 4",
    "",
    "water-to-light map:",
    "88 18 7",
    "18 25 70",
    "",
    "light-to-temperature map:",
    "45 77 23",
    "81 45 19",
    "68 64 13",
    "",
    "temperature-to-humidity map:",
    "0 69 1",
    "1 0 69",
    "",
    "humidity-to-location map:",
    "60 56 37",
    "56 93 4"
  )

  @Test
  def testParseAlmanac() =
    val e =
      (
        List(79, 14, 55, 13),
        List(
          List((-48, 98, 2), (2, 50, 48)),
          List((-15, 15, 37), (-15, 52, 2), (39, 0, 15)),
          List((-4, 53, 8), (-11, 11, 42), (42, 0, 7), (50, 7, 4)),
          List((70, 18, 7), (-7, 25, 70)),
          List((-32, 77, 23), (36, 45, 19), (4, 64, 13)),
          List((-69, 69, 1), (1, 0, 69)),
          List((4, 56, 37), (-37, 93, 4))
        )
      )
    assertEquals(e, Day5.parseAlmanac(almanac))

  @Test
  def testFindElevation() =
    val e = List(82, 43, 86, 35)
    assertEquals(e, Day5.findElevation(parseAlmanac(almanac)))

}
