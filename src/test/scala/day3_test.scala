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

}
