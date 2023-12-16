import zio._
import zio.Console._
import Day16.parseGrid

object Day16 extends ZIOAppDefault {

  type Point = (Int, Int)
  type Direction = (Int, Int)

  def parseGrid(grid: List[String]) = {
    for
      (y, l) <- (0 to grid.length - 1) zip grid
      (x, c) <- (0 to l.length - 1) zip l
    yield ((x, y), c)
  }.toMap

  def energizedCnt(iP: Point, iD: Direction, grid: Map[Point, Char]) =
    val beams = LazyList.iterate((Set((iP, iD)), Set[((Int, Int), (Int, Int))]())) { (lpd, s) =>

      val nextPoints = lpd.flatMap { (p, d) =>
        val nP = (p._1 + d._1, p._2 + d._2)
        grid.get(p) match
          case _ if s.contains((p, d)) => List()
          case Some('.')               => List(((p, d), (nP, d)))
          case Some('\\')              => List(((p, d), ((p._1 + d._2, p._2 + d._1), (d._2, d._1))))
          case Some('/')               => List(((p, d), ((p._1 - d._2, p._2 - d._1), (-d._2, -d._1))))
          case Some('|') if d._1 == 0  => List(((p, d), (nP, d)))
          case Some('|') =>
            List(((p, d), ((p._1, p._2 + d._1), (0, d._1))), ((p, d), ((p._1, p._2 - d._1), (0, -d._1))))
          case Some('-') if d._2 == 0 => List(((p, d), (nP, d)))
          case Some('-') =>
            List(((p, d), ((p._1 + d._2, p._2), (d._2, 0))), ((p, d), ((p._1 - d._2, p._2), (-d._2, 0))))
          case None => List()
      }
      val nS = s ++ nextPoints.map(_._1).toSet
      val nLpd = nextPoints.map(_._2)
      (nLpd, nS)
    }
    beams.filter(_._1.size == 0).head._2.map(_._1).toSet.size

  def part1(lines: List[String]) =
    energizedCnt((0, 0), (1, 0), parseGrid(lines))

  def part2(lines: List[String]) =
    val grid = parseGrid(lines)
    val nbCol = lines(0).size
    val nbRow = lines.size
    val starts = (0 to nbCol - 1).flatMap(c => List(((c, 0), (0, 1)), ((c, nbRow - 1), (0, -1))))
      ++ (0 to nbRow - 1).flatMap(r => List(((0, r), (1, 0)), ((nbCol - 1, r), (-1, 0))))
    starts.map((p, d) => energizedCnt(p, d, grid)).reduce(math.max)

  def run =
    for {
      v <- Day1.readFile("day16_input.txt")
      _ <- printLine(s"part1=${part1(v)}")
      _ <- printLine(s"part2=${part2(v)}")
    } yield ()
}
