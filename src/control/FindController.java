package control;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Main;

public class FindController {

    @FXML
    private Button findButton;

    @FXML
    private TextField findText;
    
    @FXML
    private Button returnButton;

    @FXML
    void find(ActionEvent event) {
    	Main.routes.initDijktra(Main.routes.search(findText.getText()));

		System.out.println("------------DISTANCIAS MINIMAS DESDE EL VERTICE ELEGIDO------------------------");
		Main.routes.printDistancias();
		System.out.println("-------------------------------");
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

}
