import zio._
import zio.Console._

object Day3 extends ZIOAppDefault {

  def padSchematics(schematics: List[String]) =
    val s2 = schematics.map("." + _ + ".")
    val pad = "." * s2(0).length
    pad :: s2.appended(pad)

  def horizontalZip(pSchematics: List[String]): List[List[(Char, Char, Char)]] =
    val n = pSchematics.length - 2
    val psr1 = pSchematics.take(n)
    val psr2 = pSchematics.tail.take(n)
    val psr3 = pSchematics.tail.tail
    ((psr1 zip psr2) zip psr3) map ((a, b) =>
      val bt = List(a._1, a._2, b)
      bt.transpose.map { case c1 :: c2 :: c3 :: _ =>
        (c1, c2, c3)
      }
    )

  def isDigit(c: Char) = '0' <= c && c <= '9'

  def isPart(c: Char): Boolean = !(isDigit(c) || c == '.')

  def isPart(col: (Char, Char, Char)): Boolean = isPart(col._1) || isPart(col._2) || isPart(col._3)

  def findPartsNumber(zSchematics: List[(Char, Char, Char)]): List[Int] =
    zSchematics
      .foldLeft((List[Int](), "", false)) {
        case ((l, digits, isPartNumber), col) if isDigit(col._2) =>
          (l, digits.appended(col._2), isPartNumber || isPart(col))
        case ((l, digits, isPartNumber), col) =>
          (
            if (isPartNumber || isPart(col)) && digits != "" then l.appended(digits.toInt)
            else l,
            "",
            isPart(col)
          )
      }
      ._1

  def part1(schematics: List[String]) =
    val zs = horizontalZip(padSchematics(schematics))
    zs.flatMap(findPartsNumber).sum

  def run =
    for {
      v <- Day1.readFile("day3_input.txt")
      _ <- printLine(s"part1=${part1(v)}")
    } yield ()
}
