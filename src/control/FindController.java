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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Main;
import structures.Tree;

public class FindController implements Initializable{

    @FXML
    private TextField destinationTF;

    @FXML
    private Button findButton;

    @FXML
    private TextArea minimumTA;

    @FXML
    private TextField originTF;

    @FXML
    private Button returnButton;

    @FXML
    void find(ActionEvent event) {
    	
    	Main.routes.initDijkstra(Main.routes.search(originTF.getText().toUpperCase()));


		Random r = new Random();
		int number = r.nextInt(Main.buses.get(originTF.getText().toUpperCase()).size());
		Main.currentBus = Main.buses.get(originTF.getText().toUpperCase()).get(number);

    	Tree<String> routesTree = Main.routes.getArbolGeneradorMinimo();
    	ArrayList<String> route = routesTree.getRoute(destinationTF.getText().toUpperCase());
    	
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
    	
    	int originNumber=0;
    	int i = 0;
    	for(String s : Main.buses.keySet()) {
    		if(originTF.getText().toUpperCase().equalsIgnoreCase(s)) {
    			originNumber = i;
    		}
    		i++;
    	}
    	
    	int destinationNumber=0;
    	int j = 0;
    	for(String s : Main.buses.keySet()) {
    		if(destinationTF.getText().toUpperCase().equalsIgnoreCase(s)) {
    			destinationNumber = j;
    		}
    		j++;
    	}
    	
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
		minimumTA.setText("");
		
	}

}
