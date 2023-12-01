import zio._
import zio.Console._
import scala.io.BufferedSource

object Day1 extends ZIOAppDefault {

  def acquire = ZIO.attempt(scala.io.Source.fromFile("src/main/resources/day1_input.txt"))
  def release = (buffer :BufferedSource) => ZIO.succeed(buffer.close)
  def process = (buffer :BufferedSource)=> ZIO.attempt(buffer.getLines.toList)

  val readFile : Task[List[String]] = ZIO.acquireReleaseWith(acquire)(release)(process)

  // Would fail if no digit but not an issue as input data is known
  def firstDigit(s: String) = s.filter(c => '0' <= c && c <='9').head - '0'
  // def lastDigit = firstDigit compose ((s:String) => s.reverse) // non idiomatic alternative
  def lastDigit(s: String) = firstDigit(s.reverse)
  def oneLineValue(s:String) = firstDigit(s)*10 + lastDigit(s)

  def part1(lines:List[String]) = lines.map(oneLineValue).sum

  def run =
    for {
      v    <- readFile
      _    <- printLine(s"part1=${part1(v)}")
    } yield ()
}