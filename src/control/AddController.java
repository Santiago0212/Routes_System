package control;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Main;

public class AddController implements Initializable{

    @FXML
    private Button addVertexButton;

    @FXML
    private TextField vertexText;

    @FXML
    private TextField weightTF;

    @FXML
    private Button addEdgeButton;
    
    @FXML
    private Button returnBTN;
    
    @FXML
    private ChoiceBox<String> v1CB;

    @FXML
    private ChoiceBox<String> v2CB;

    
    @FXML
    void addEdge(ActionEvent event) {
    	String v1=v1CB.getSelectionModel().getSelectedItem();
    	String v2=v2CB.getSelectionModel().getSelectedItem();
    	String weight=weightTF.getText();
    	
    	//System.out.println(v1+"---"+v2+"----------"+weight);

    	if(v1!=""&&v2!=""&&!weight.equals("")) {
    		
    		if(Main.routes.search(v1)!=null&Main.routes.search(v2)!=null)
	    		try {
	    			Main.routes.addEdge(v1,v2,Integer.parseInt(weight));
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
    			Alert alert = new Alert(Alert.AlertType.WARNING);
    		    alert.setHeaderText(null);
    		    alert.setTitle("Advertencia");
    		    alert.setContentText("Algun o ambas estaciones que intentas conectar no se han creado aun");
    		    alert.showAndWait();
    		}
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
    		Main.routes.addVertex(vertexText.getText(),Main.routes.getSize());
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		for(int i=0;i<Main.routes.getElements().size();i++) {
			v1CB.getItems().add(Main.routes.getElements().get(i).getValue());
			v2CB.getItems().add(Main.routes.getElements().get(i).getValue());
		}
	}
}
