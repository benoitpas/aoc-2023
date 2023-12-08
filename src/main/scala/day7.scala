import zio._
import zio.Console._

object Day7 extends ZIOAppDefault {

  val cardsOrder = List('A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2')
  val cardsRank = (cardsOrder zip (1 to cardsOrder.size)).toMap

  val handsOrder =
    List(List(5), List(1, 4), List(2, 3), List(1, 1, 3), List(1, 2, 2), List(1, 1, 1, 2), List(1, 1, 1, 1, 1))
  val handsRank = (handsOrder zip (1 to handsOrder.size)).toMap

  def countCards(hand: String) = hand.foldLeft(Map[Char, Int]()) { case (m, card) =>
    m.updated(card, m.getOrElse(card, 0) + 1)
  }

  def compareCards(h1: String, h2: String) =
    (h1 zip h2)
      .foldLeft(None: Option[Boolean]) {
        case (Some(f), _)                                       => Some(f)
        case (None, (c1, c2)) if cardsRank(c1) != cardsRank(c2) => Some(cardsRank(c1) > cardsRank(c2))
        case (None, _)                                          => None
      }
      .get // will fail if h1==h2

  def compareHands(h1: String, h2: String) =
    val s1 = countCards(h1).values.toList.sorted
    val s2 = countCards(h2).values.toList.sorted
    val h1Rank = handsRank(s1)
    val h2Rank = handsRank(s2)
    if (h1Rank == h2Rank)
      compareCards(h1, h2)
    else
      h1Rank > h2Rank

  def part1(hands: List[String]) =
    val handAndBids = hands.map {
      _.split(' ').toList match
        case hand :: bid :: _ => (hand, bid.toInt)
    }
    val sortedBids = handAndBids.sortWith((a, b) => compareHands(a._1, b._1)).map(_._2)
    (sortedBids zip (1 to sortedBids.size)).map(_ * _).sum

  def run =
    for {
      v <- Day1.readFile("day7_input.txt")
      _ <- printLine(s"part1=${part1(v)}")
    } yield ()
}
