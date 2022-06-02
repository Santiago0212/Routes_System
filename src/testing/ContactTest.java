package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import main.Main;
import structures.Graph;
import structures.Tree;

class ContactTest {
	
	private int distance;
	private Graph<String> graph;
	private ArrayList<String> route;
	
	private void stage1() {
		try {
			graph = Main.createGraph();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		distance = 600;
	}
	
	private void stage2() {
		graph.initDijkstra(graph.search("FLORA INDUSTRIAL"));
		Tree<String> routesTree = graph.getArbolGeneradorMinimo();
    	 route = routesTree.getRoute("POPULAR");
	}
	
	@Test
	void test() {
		stage1();
		assertEquals(distance,graph.floyd()[graph.search("CHIMINANGOS").getNum()][graph.search("FLORA INDUSTRIAL").getNum()]);
		stage2();
		assertEquals(route.get(0),"FLORA INDUSTRIAL");
		assertEquals(route.get(1),"SALOMIA");
		assertEquals(route.get(2),"POPULAR");
	}

}
