import org.junit.Test
import org.junit.Assert._
import Day7.compareCards
import Day7.compareHands

class Day7Test {

  val hands = List("32T3K 765", "T55J5 684", "KK677 28", "KTJJT 220", "QQQJA 483")

  @Test
  def testCompareCardscompareHands1() =
    assert(compareHands("32T3K", "T55J5"))

  @Test
  def testCompareCardscompareHands2() =
    assert(compareHands("KTJJT", "KK677"))

  @Test
  def testCompareCardscompareHands3() =
    assert(compareHands("32T3K", "T55J5"))

  @Test
  def testCompareCardscompareHands4() =
    assertFalse(compareHands("22646", "227KJ"))

  @Test
  def testPart1() =
    assertEquals(6440, Day7.part1(hands))

}
