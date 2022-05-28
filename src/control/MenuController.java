package control;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import main.Main;
import structures.Edge;

public class MenuController {

    @FXML
    private Button addButton;

    @FXML
    private Button showButton;

    @FXML
    private Button findButton;

    @FXML
    void add(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(Main.class.getResource("../ui/Add.fxml"));
		loader.setController(new AddController());
		Parent parent = (Parent) loader.load();
		Scene scene = new Scene(parent);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
		close();
    }

    @FXML
    void find(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(Main.class.getResource("../ui/Find.fxml"));
		loader.setController(new FindController());
		Parent parent = (Parent) loader.load();
		Scene scene = new Scene(parent);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
		close();
    }

    @FXML
    void show(ActionEvent event) {
    	Main.routes.printEdges();
	
		ArrayList<Edge<String>> arregloCamino= new ArrayList<Edge<String>>();
		arregloCamino=Main.routes.getArregloCamino();
		
		System.out.println("------------ARISTAS PARA EL ARBOL------------------------");

		for(int i=0;i<arregloCamino.size();i++) {
			System.out.println(arregloCamino.get(i).getVertex1().getValue()+"()"+arregloCamino.get(i).getWeight()+"()"+arregloCamino.get(i).getVertex2().getValue());
		}
    }
    
    public void close() {
    	Stage stage = (Stage) addButton.getScene().getWindow();
    	stage.close();
    }
}
