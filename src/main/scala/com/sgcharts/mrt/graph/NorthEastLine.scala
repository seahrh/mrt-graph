package com.sgcharts.mrt.graph

import scalax.collection.Graph
import scalax.collection.edge.Implicits._
import scalax.collection.edge.WUnDiEdge

object NorthEastLine extends RailLine {
  val name: String = "nel"
  val graph: Graph[Platform, WUnDiEdge] = Graph(
    (HarbourFront ~% OutramParkNel) (4),
    (Chinatown ~% OutramParkNel) (2),
    (Chinatown ~% ClarkeQuay) (1),
    (DhobyGhautNel ~% ClarkeQuay) (3),
    (DhobyGhautNel ~% LittleIndia) (2),
    (FarrerPark ~% LittleIndia) (1),
    (FarrerPark ~% BoonKeng) (3),
    (PotongPasir ~% BoonKeng) (2),
    (PotongPasir ~% Woodleigh) (1),
    (Serangoon ~% Woodleigh) (2),
    (Serangoon ~% Kovan) (3),
    (Hougang ~% Kovan) (2),
    (Hougang ~% Buangkok) (2),
    (Sengkang ~% Buangkok) (2),
    (Sengkang ~% Punggol) (3)
  )
}
