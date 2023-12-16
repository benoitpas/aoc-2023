import zio._
import zio.Console._

object Day16 extends ZIOAppDefault {

  def part1(grid: List[String]) =
    val gMap = {
      for
        (y, l) <- (0 to grid.length - 1) zip grid
        (x, c) <- (0 to l.length - 1) zip l
      yield ((x, y), c)
    }.toMap

    val iP = (0, 0)
    val iD = (1, 0)
    val beams = LazyList.iterate((Set((iP, iD)), Set[((Int, Int), (Int, Int))]())) { (lpd, s) =>

      val nextPoints = lpd.flatMap { (p, d) =>
        val nP = (p._1 + d._1, p._2 + d._2)
        gMap.get(p) match
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

  def run =
    for {
      v <- Day1.readFile("day16_input.txt")
      _ <- printLine(s"part1=${part1(v)}")
    } yield ()
}
