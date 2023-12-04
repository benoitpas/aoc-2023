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

  def part2(cards: List[String]) =
    val cm = cards.map(parseCard).toMap

    def countCards(index: Int): Int =
      1 + ((index + 1) to (index + cm(index))).map(countCards).sum

    (1 to cm.size).map(countCards).sum

  def run =
    for {
      v <- Day1.readFile("day4_input.txt")
      _ <- printLine(s"part1=${part1(v)}")
      _ <- printLine(s"part2=${part2(v)}")
    } yield ()
}
