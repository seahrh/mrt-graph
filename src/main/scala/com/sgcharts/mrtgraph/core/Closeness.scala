package com.sgcharts.mrtgraph.core

import scala.collection.{SortedMap, mutable}
import scala.collection.mutable.ArrayBuffer
import scalax.collection.Graph
import scalax.collection.edge.WUnDiEdge

object Closeness {

  def rank(g: Graph[Platform, WUnDiEdge]): SortedMap[Int, ArrayBuffer[Platform]] = {
    val ps: Array[Platform] = g.toOuterNodes.toArray
    val wmap = mutable.Map[String, Int]()
    val smap = mutable.SortedMap[Int, ArrayBuffer[Platform]]()
    for (i <- ps.indices) {
      // Closeness centrality score of a platform (lower the better)
      var sum: Int = 0
      for (j <- ps.indices) {
        val pair: String = Platform.pairToString(ps(i), ps(j))
        val w: Int = if (j > i) {
          val tmp: Int = weight(g, ps(i), ps(j))
          wmap += pair -> tmp
          tmp
        } else if (j < i) {
          wmap.get(pair) match {
            case Some(v) => v
            case _ =>
              throw new IllegalStateException(s"Map: Path not found between ${ps(i)} and ${ps(j)}")
          }
        } else {
          // identity i==j
          0
        }
        sum += w
      }
      smap.get(sum) match {
        case Some(platforms) =>
          platforms += ps(i)
          smap += sum -> platforms
        case _ =>
          smap += sum -> ArrayBuffer(ps(i))
      }
    }
    smap
  }

  private def weight(g: Graph[Platform, WUnDiEdge], p1: Platform, p2: Platform): Int = {
    g.get(p1).shortestPathTo(g.get(p2)) match {
      case Some(path) =>
        path.weight.toInt
      case _ =>
        throw new IllegalStateException(s"Graph: Path not found between $p1 and $p2")
    }
  }
}
