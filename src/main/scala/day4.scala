import zio._
import zio.Console._

object Day4 extends ZIOAppDefault {
  def parseCard(card: String): (Int, Int) =
    val idString :: numbers :: _ = card.split(':').toList
    val id = idString.split(' ').last.toInt
    val winningNumbers :: draw :: _ = numbers.split('|').toList

    def toSet(numbers: String) = numbers
      .split(' ')
      .flatMap {
        case "" => None
        case s  => Some(s.trim().toInt)
      }
      .toSet
    val nbWinningNumbers = (toSet(winningNumbers) intersect toSet(draw)).size
    (id, nbWinningNumbers)

  def part1(cards: List[String]) =
    cards.map { card => math.pow(2, parseCard(card)._2 - 1).toInt }.sum

  def memoize[I, O](f: I => O): I => O = new scala.collection.mutable.HashMap[I, O]() {
    override def apply(key: I) = getOrElseUpdate(key, f(key))
  }

  def part2(cards: List[String]) =
    // Add card 0 to add all cards
    val cm = (cards.map(parseCard) ++ List(0 -> cards.size)).toMap

    lazy val countCards: Int => Int = memoize { index =>
      1 + (index + 1 to index + cm(index)).map(countCards).sum
    }

    countCards(0) - 1 // Then remove card 0

  def run =
    for {
      v <- Day1.readFile("day4_input.txt")
      _ <- printLine(s"part1=${part1(v)}")
      _ <- printLine(s"part2=${part2(v)}")
    } yield ()
}
