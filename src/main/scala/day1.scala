import zio._
import zio.Console._
import scala.io.BufferedSource

object MyApp extends ZIOAppDefault {

  def acquire = ZIO.attempt(scala.io.Source.fromFile("src/main/resources/day1_input.txt"))
  def release = (buffer :BufferedSource) => ZIO.succeed(buffer.close)
  def process = (buffer :BufferedSource)=> ZIO.attempt(buffer.getLines.toList)

  val readFile : Task[List[String]] = ZIO.acquireReleaseWith(acquire)(release)(process)

  def part1(calories:List[Int]) = calories.reduce(math.max)

  def part2(calories:List[Int]) = calories.sortBy((x) => -x).take(3).sum

  def run =
    for {
      v    <- readFile
      _    <- printLine(v)
    } yield ()
}