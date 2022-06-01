package control;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Main;
import structures.Tree;

public class FindController implements Initializable{


    @FXML
    private Button findButton;

    @FXML
    private TextArea minimumTA;

    @FXML
    private Button returnBTN;
    
    @FXML
    private ChoiceBox<String> originCB;

    @FXML
    private ChoiceBox<String> destinationCB;

    @FXML
    void find(ActionEvent event) {
    	String origin=originCB.getSelectionModel().getSelectedItem();
    	String destination=destinationCB.getSelectionModel().getSelectedItem();
    	
    	Main.routes.initDijkstra(Main.routes.search(origin.toUpperCase()));


		Random r = new Random();
		int number = r.nextInt(Main.buses.get(origin.toUpperCase()).size());
		Main.currentBus = Main.buses.get(origin.toUpperCase()).get(number);

    	Tree<String> routesTree = Main.routes.getArbolGeneradorMinimo();
    	ArrayList<String> route = routesTree.getRoute(destination.toUpperCase());
    	
    	String minimum = "";
    	minimum += "Subirse en ---------> "+Main.currentBus+"\n";
    	for(String s : route) {
    		if(!Main.buses.get(s).contains(Main.currentBus)) {
    			Random r2 = new Random();
    			int number2 = r2.nextInt(Main.buses.get(s).size());	
    			Main.currentBus = Main.buses.get(s).get(number2);
    			minimum += "Subirse en ---------> "+Main.currentBus+"\n";
    		}
			minimum += s+"\n";
		}
    	
    	int originNumber = Main.routes.search(origin.toUpperCase()).getNum();
    	int destinationNumber= Main.routes.search(destination.toUpperCase()).getNum();
    	
    	
    	int distance = Main.routes.floyd()[originNumber][destinationNumber];
    	
    	minimum += "\nDitancia recorrida ---------> "+distance+" metros\n";
    	
    	minimumTA.setText(minimum);
    	
    }
    
    @FXML
    void back(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(Main.class.getResource("../ui/Menu.fxml"));
		loader.setController(new MenuController());
		Parent parent = (Parent) loader.load();
		Scene scene = new Scene(parent);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
    	close();
    }
    
    public void close() {
    	Stage stage = (Stage) findButton.getScene().getWindow();
    	stage.close();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		for(int i=0;i<Main.routes.getElements().size();i++) {
			originCB.getItems().add(Main.routes.getElements().get(i).getValue());
			destinationCB.getItems().add(Main.routes.getElements().get(i).getValue());
		}
		minimumTA.setText("");
		
	}

}
