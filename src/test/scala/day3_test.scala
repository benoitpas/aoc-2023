import org.junit.Test
import org.junit.Assert._

class Day3Test {

  val schematics = List(
    "467..114..",
    "...*......",
    "..35..633.",
    "......#...",
    "617*......",
    ".....+.58.",
    "..592.....",
    "......755.",
    "...$.*....",
    ".664.598.."
  )

  @Test def testPadSchematics(): Unit =
    assertEquals(List("...", ".#.", "..."), Day3.padSchematics(List("#")))

  @Test def testHorizontalZip(): Unit =
    assertEquals(
      List(List(('1', '3', '5'), ('2', '4', '6')), List(('3', '5', '7'), ('4', '6', '8'))),
      Day3.horizontalZip(List("12", "34", "56", "78"))
    )

  @Test def testFindPartsNumber(): Unit =
    val hz = Day3.horizontalZip(List(schematics(5), schematics(6), schematics(7)))(0)
    assertEquals(List(592), Day3.findPartsNumber(hz))

  @Test def testPart1(): Unit =
    assertEquals(4361, Day3.part1(schematics))

  @Test def testAddCoordinates(): Unit =
    val e = List(
      List(((1, 1), 'a'), ((2, 1), 'b'), ((3, 1), 'c')),
      List(((1, 2), 'd'), ((2, 2), 'e'), ((3, 2), 'f')),
      List(((1, 3), 'g'), ((2, 3), 'h'), ((3, 3), 'i'))
    )
    assertEquals(e, Day3.addCoordinates(List("abc", "def", "ghi")))

  @Test def testFindNumbers() =
    val hz = Day3.horizontalZip(Day3.addCoordinates(List(schematics(5), schematics(6), schematics(7))))(0)
    val e = List(
      (
        592,
        Set(
          (2, 2),
          (2, 1),
          (4, 1),
          (6, 2),
          (5, 2),
          (6, 3),
          (3, 2),
          (3, 1),
          (3, 3),
          (4, 3),
          (5, 3),
          (6, 1),
          (4, 2),
          (5, 1),
          (2, 3)
        )
      )
    )
    assertEquals(e, Day3.findNumbers(hz))

  @Test def testPart2() =
    assertEquals(467835, Day3.part2(schematics))

}
