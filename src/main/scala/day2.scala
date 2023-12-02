import zio._
import zio.Console._
import scala.io.BufferedSource

object Day2 extends ZIOAppDefault {

  def drawToMap(draw: String) = 
    draw.split(", ").map(dice => {
        val number::color::_ = dice.split(" ").toList
        (color, number.toInt)
    }).toMap
 
  def gameToDraws(game:String) = 
    val gameKey::drawStrings = game.split(": ").toList
    val gameId = gameKey.split(" ")(1).toInt
    val draws = drawStrings.flatMap(d => d.split("; ").map(drawToMap).toList)
    (gameId, draws)

  def possibleDraw(draw:Map[String,Int]) =
    draw.getOrElse("red",0) <= 12 && draw.getOrElse("green",0) <= 13 && draw.getOrElse("blue",0) <= 14

  def part1(games:List[String]) = games.map(s => {
    val (gameId,draws) = gameToDraws(s)
    if draws.foldLeft(true)((a,d) => a && possibleDraw(d)) then
      gameId
    else 0
  }).sum

  def run =
    for {
      v    <- Day1.readFile("day2_input.txt")
      _    <- printLine(s"part1=${Day2.part1(v)}")
    } yield ()
}