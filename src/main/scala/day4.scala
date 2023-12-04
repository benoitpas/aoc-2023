import zio._
import zio.Console._

object Day4 extends ZIOAppDefault {
  def cardValue(card: String) =
    val winningNumbers :: draw :: _ = card.split(':')(1).split('|').toList
    def toSet(numbers: String) = numbers
      .split(' ')
      .flatMap {
        case "" => None
        case s  => Some(s.trim().toInt)
      }
      .toSet
    val nbWinningNumbers = (toSet(winningNumbers) intersect toSet(draw)).size
    math.pow(2, nbWinningNumbers - 1).toInt

  def part1(cards: List[String]) =
    cards.map(cardValue).sum

  def run =
    for {
      v <- Day1.readFile("day4_input.txt")
      _ <- printLine(s"part1=${part1(v)}")
    } yield ()
}
