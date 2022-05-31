package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import control.MenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Station;
import structures.Edge;
import structures.Graph;
import structures.Node;
import structures.Tree;

public class Main extends Application {
	
	public static Graph<String> routes;
	public static Map<String,ArrayList<String>> buses = new HashMap<String,ArrayList<String>>();
	public static String currentBus;

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		try {
			routes = createGraph();
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
		launch(args);

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
			ArrayList<String> stationBuses = new ArrayList<>();
			String[] stationData = bf.readLine().split("\\|");
			String name = stationData[0];
				
			for (int j=0; j<stationData[1].split("-").length; j++){
				stationBuses.add(stationData[1].split("-")[j]);
	        }
			
			buses.put(name, stationBuses);
			myGraph.addVertex(name,i);
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
