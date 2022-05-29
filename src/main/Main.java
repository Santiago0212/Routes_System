package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import control.MenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import structures.Edge;
import structures.Graph;
import structures.Node;
import structures.Tree;

public class Main extends Application {
	
	//public static Graph<String> routes;

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		try {
			Graph<String> routes = createGraph();
			
			System.out.println("Wich vertex do you want to find the minimum tree?: ");
			String vertex = sc.nextLine();
			vertex = vertex.toUpperCase();
			
			routes.initDijkstra(routes.search(vertex));
			
			ArrayList<Edge<String>> arregloCamino = routes.getArregloCamino();
			
			for(int i=0;i<arregloCamino.size();i++) {
	           System.out.println(arregloCamino.get(i).getVertex1().getValue()+"()"+arregloCamino.get(i).getWeight()+"()"+arregloCamino.get(i).getVertex2().getValue());
	        }
			
			//routes.printDistancias();
			//routes.printEdges();
			
			Tree<String> routesTree = routes.getArbolGeneradorMinimo();
			
			System.out.println("Wich vertex do you want to find the route?: ");
			String destination = sc.nextLine();
			destination = destination.toUpperCase();
			ArrayList<String> route = routesTree.getRoute(destination);
			
			for(String s : route) {
				System.out.println(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
		//launch(args);

	}
	
	@Override
	public void start(Stage args) throws Exception {
		FXMLLoader loader = new FXMLLoader(Main.class.getResource("../ui/Menu.fxml"));
		loader.setController(new MenuController());
		Parent parent = loader.load();
		Scene scene = new Scene(parent);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();

	}
	
	public static Graph<String> createGraph() throws IOException {
		Graph<String> myGraph = new Graph<>();
	
		File data = new File("stations.txt");
	
		BufferedReader bf = new BufferedReader(new FileReader(data));
	
		int stationsNumber = Integer.parseInt(bf.readLine());
		

		for(int i=0; i<stationsNumber; i++) {
			myGraph.addVertex(bf.readLine());
		}
		
		int connectionsNumber = Integer.parseInt(bf.readLine());
		String line;
		for(int i=0; i<connectionsNumber; i++) {
			line = bf.readLine();
			String[] connection = line.split("\\|");
			myGraph.addEdge(connection[0],connection[1], (int) Double.parseDouble(connection[2])*100);
		}
		
		bf.close();
		
		return myGraph;
	}
}
