import zio._
import zio.Console._
import scala.io.BufferedSource

object Day1 extends ZIOAppDefault {

  def acquire(f:String) = ZIO.attempt(scala.io.Source.fromFile(s"src/main/resources/${f}"))
  def release = (buffer :BufferedSource) => ZIO.succeed(buffer.close)
  def process = (buffer :BufferedSource)=> ZIO.attempt(buffer.getLines.toList)

  def readFile(f:String) : Task[List[String]] = ZIO.acquireReleaseWith(acquire(f))(release)(process)

  // Would fail if no digit but not an issue as input data is known
  def firstDigit(s: String) = s.filter(c => '0' <= c && c <='9').head - '0'
  // def lastDigit = firstDigit compose ((s:String) => s.reverse) // non idiomatic alternative
  def lastDigit(s: String) = firstDigit(s.reverse)
  def oneLineValue(s:String) = firstDigit(s)*10 + lastDigit(s)

  // I could have used a mix of fold but that's easier to read (and write !)
  def replaceSpelledDigit(s:String):String = s match
    case "" => ""
    case s"zero${r}" => s"0${replaceSpelledDigit("o" + r)}"
    case s"one${r}"  => s"1${replaceSpelledDigit("e" + r)}"
    case s"two${r}"  => s"2${replaceSpelledDigit("o" + r)}"
    case s"three${r}" => s"3${replaceSpelledDigit("e" + r)}"
    case s"four${r}" => s"4${replaceSpelledDigit(r)}" // no digit starts with 'r' so no need to add it
    case s"five${r}" => s"5${replaceSpelledDigit("e" + r)}"
    case s"six${r}" => s"6${replaceSpelledDigit(r)}" // same for 'x'
    case s"seven${r}" => s"7${replaceSpelledDigit("n" + r)}"
    case s"eight${r}" => s"8${replaceSpelledDigit("t" + r)}"
    case s"nine${r}" => s"9${replaceSpelledDigit("e" + r)}"
    case _ => s.head.toString() ++ replaceSpelledDigit(s.tail)

  def part1(lines:List[String]) = lines.map(oneLineValue).sum
  def part2(lines:List[String]) = lines.map(oneLineValue compose replaceSpelledDigit).sum

  def run =
    for {
      v    <- readFile("day1_input.txt")
      _    <- printLine(s"part1=${part1(v)}")
      _    <- printLine(s"part2=${part2(v)}") // not 54871
    } yield ()
}