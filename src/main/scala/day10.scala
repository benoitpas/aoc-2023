import zio._
import zio.Console._

object Day10 extends ZIOAppDefault {

  val start = 'S'

  def part1(mazeStrings: List[String]) =
    val maze = mazeStrings.map(_.toCharArray()).toArray
    val xMax = maze(0).length - 1
    val yMax = maze.length - 1
    val yS = maze.indexWhere(_.contains(start))
    val xS = maze(yS).indexOf(start)
    val p0 = (xS, yS)
    val firstPoints =
      // There is an assumption the 'S' is in a loop, i.e. the dataset is well formed. The algo will crash for malformed input datasets
      // It would be nicer looking to put the character and the x/y offsets in a list and iterate instead, especially as the `else List()`
      // are particulary repetitive
      (if 0 < xS && maze(yS)(xS - 1) == '-' then List((xS - 1, yS)) else List())
        ++ (if 0 < xS && maze(yS)(xS - 1) == 'L' then List((xS - 1, yS)) else List())
        ++ (if 0 < xS && maze(yS)(xS - 1) == 'F' then List((xS - 1, yS)) else List())
        ++ (if xS < xMax && maze(yS)(xS + 1) == '-' then List((xS + 1, yS)) else List())
        ++ (if xS < xMax && maze(yS)(xS + 1) == 'J' then List((xS + 1, yS)) else List())
        ++ (if xS < xMax && maze(yS)(xS + 1) == '7' then List((xS + 1, yS)) else List())
        ++ (if 0 < yS && maze(yS - 1)(xS) == '|' then List((xS, yS - 1)) else List())
        ++ (if 0 < yS && maze(yS - 1)(xS) == '7' then List((xS, yS - 1)) else List())
        ++ (if 0 < yS && maze(yS - 1)(xS) == 'F' then List((xS, yS - 1)) else List())
        ++ (if yS < yMax && maze(yS + 1)(xS) == '|' then List((xS, yS + 1)) else List())
        ++ (if yS < yMax && maze(yS + 1)(xS) == 'J' then List((xS, yS + 1)) else List())
        ++ (if yS < yMax && maze(yS + 1)(xS) == 'L' then List((xS, yS + 1)) else List())
    val iDistances = (List(p0 -> 0) ++ firstPoints.map(_ -> 1).toList).toMap

    val distances = LazyList.iterate((firstPoints, 1, iDistances)) { (points, distance, mDistances) =>

      val nextPoints = points.flatMap { (x, y) =>
        (if maze(y)(x) == '-' && !mDistances.contains((x - 1, y)) then List((x - 1, y)) else List())
          ++ (if maze(y)(x) == '-' && !mDistances.contains((x + 1, y)) then List((x + 1, y)) else List())
          ++ (if maze(y)(x) == '|' && !mDistances.contains((x, y - 1)) then List((x, y - 1)) else List())
          ++ (if maze(y)(x) == '|' && !mDistances.contains((x, y + 1)) then List((x, y + 1)) else List())
          ++ (if maze(y)(x) == '7' && !mDistances.contains((x - 1, y)) then List((x - 1, y)) else List())
          ++ (if maze(y)(x) == '7' && !mDistances.contains((x, y + 1)) then List((x, y + 1)) else List())
          ++ (if maze(y)(x) == 'F' && !mDistances.contains((x + 1, y)) then List((x + 1, y)) else List())
          ++ (if maze(y)(x) == 'F' && !mDistances.contains((x, y + 1)) then List((x, y + 1)) else List())
          ++ (if maze(y)(x) == 'L' && !mDistances.contains((x + 1, y)) then List((x + 1, y)) else List())
          ++ (if maze(y)(x) == 'L' && !mDistances.contains((x, y - 1)) then List((x, y - 1)) else List())
          ++ (if maze(y)(x) == 'J' && !mDistances.contains((x - 1, y)) then List((x - 1, y)) else List())
          ++ (if maze(y)(x) == 'J' && !mDistances.contains((x, y - 1)) then List((x, y - 1)) else List())
      }

      val nextDistance = distance + 1
      val nextMap = mDistances ++ nextPoints.map((_, nextDistance)).toMap
      (nextPoints, nextDistance, nextMap)
    }
    distances.find(_._1.isEmpty).get._2 - 1

  def run =
    for {
      v <- Day1.readFile("day10_input.txt")
      _ <- printLine(s"part1=${part1(v)}")
    } yield ()
}
