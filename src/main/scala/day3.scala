import zio._
import zio.Console._

object Day3 extends ZIOAppDefault {

  def padSchematics(schematics: List[String]) =
    val s2 = schematics.map("." + _ + ".")
    val pad = "." * s2(0).length
    pad :: s2.appended(pad)

  def horizontalZip[T](pSchematics: Seq[Seq[T]]): Seq[Seq[(T, T, T)]] =
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

  def findPartsNumber(zSchematics: Seq[(Char, Char, Char)]): Seq[Int] =
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
    val zs = horizontalZip(padSchematics(schematics).map(_.toSeq))
    zs.flatMap(findPartsNumber).sum

  def addCoordinates(schematics: List[String]) =
    val nX = schematics(0).length()
    val nY = schematics.length

    val xyCoordinates = (1 to nY).map(y => (1 to nX).map(x => (x, y)))
    (xyCoordinates zip schematics) map (_ zip _)

  def findNumbers(schematicsWCoords: Seq[(((Int, Int), Char), ((Int, Int), Char), ((Int, Int), Char))]) =
    schematicsWCoords
      .foldLeft((List[(Int, Set[(Int, Int)])](), "", Set[(Int, Int)]())) {
        case ((l, digits, points), col) if isDigit(col._2._2) =>
          (l, digits.appended(col._2._2), points ++ Set(col._1._1, col._2._1, col._3._1))
        case ((l, digits, points), col) =>
          val colPoints = Set(col._1._1, col._2._1, col._3._1)
          val newL = if digits != "" then l.appended((digits.toInt, points ++ colPoints)) else l
          (newL, "", colPoints)
      }
      ._1

  def part2(schematics: List[String]) =
    val sc = addCoordinates(padSchematics(schematics))
    val zs = horizontalZip(sc)
    val numbers = zs flatMap findNumbers
    val starPositions = sc.flatten.flatMap {
      case (p, '*') => Some(p)
      case _        => None
    }
    starPositions
      .map(p =>
        numbers.toList.flatMap {
          case (n, s) if s.contains(p) => Some(n)
          case _                       => None
        }
      )
      .map {
        case a :: b :: Nil => a * b
        case _             => 0
      }
      .sum

  def run =
    for {
      v <- Day1.readFile("day3_input.txt")
      _ <- printLine(s"part1=${part1(v)}")
      _ <- printLine(s"part2=${part2(v)}")
    } yield ()
}
