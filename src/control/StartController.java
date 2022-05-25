package control;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Main;

public class StartController {

    @FXML
    private Button addVertexButton;

    @FXML
    private TextField vertexText;

    @FXML
    private TextField v1Text;

    @FXML
    private TextField v2Text;

    @FXML
    private TextField weightText;

    @FXML
    private Button addEdgeButton;

    
    @FXML
    void addEdge(ActionEvent event) {

    	if(!v1Text.getText().equals("")&&!v2Text.getText().equals("")&&!weightText.getText().equals(""))
    		try {
    			Main.routes.addEdge(v1Text.getText(),v2Text.getText(),Integer.parseInt(weightText.getText()));
    			Alert alert = new Alert(Alert.AlertType.INFORMATION);
    		    alert.setHeaderText(null);
    		    alert.setTitle("Agregado");
    		    alert.setContentText("Se agrego su conexión exitosamente");
    		    alert.showAndWait();
    		}catch(NumberFormatException ex) {
    			Alert alert = new Alert(Alert.AlertType.ERROR);
    		    alert.setHeaderText(null);
    		    alert.setTitle("Error");
    		    alert.setContentText("El peso debe ser un numero entero");
    		    alert.showAndWait();
    		}
    	else {
    		Alert alert = new Alert(Alert.AlertType.ERROR);
		    alert.setHeaderText(null);
		    alert.setTitle("Error");
		    alert.setContentText("No pueden haber espacios en blanco");
		    alert.showAndWait();
    	}
    }

    @FXML
    void addVertex(ActionEvent event) {
    	if(!vertexText.getText().equals("")) {
    		Main.routes.addVertex(vertexText.getText());
	    	Alert alert = new Alert(Alert.AlertType.INFORMATION);
		    alert.setHeaderText(null);
		    alert.setTitle("Agregado");
		    alert.setContentText("Se agrego su estación exitosamente");
		    alert.showAndWait();
	    }
    	else {
    		Alert alert = new Alert(Alert.AlertType.ERROR);
		    alert.setHeaderText(null);
		    alert.setTitle("Error");
		    alert.setContentText("No pueden haber espacios en blanco");
		    alert.showAndWait();
    	}
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
    	Stage stage = (Stage) addVertexButton.getScene().getWindow();
    	stage.close();
    }
}
