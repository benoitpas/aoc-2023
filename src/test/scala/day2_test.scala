import org.junit.Test
import org.junit.Assert._

class Day2Test {
  val games = List("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green",
    "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue",
    "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red",
    "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red",
    "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green")

  @Test def testDrawToMap() : Unit =
    assertEquals(List("blue" -> 1,"green" -> 2).toMap, Day2.drawToMap("1 blue, 2 green"))

  @Test def testGameToDraws1() : Unit =
    val e = (2, List())
    assertEquals(e, Day2.gameToDraws("Game 2: "))

  @Test def testGameToDraws2() : Unit =
    val e = (1, List(List("blue"->3,"red"->4).toMap, List("red"->1, "green"->2,"blue"->6).toMap,List("green"->2).toMap))
    assertEquals(e, Day2.gameToDraws(games(0)))

  @Test def testPart1() : Unit =
    assertEquals(8, Day2.part1(games))

  @Test def testPart2() : Unit =
    assertEquals(2286, Day2.part2(games))

}