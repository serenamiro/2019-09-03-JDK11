/**
 * Sample Skeleton for 'Food.fxml' Controller Class
 */

package it.polito.tdp.food;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import it.polito.tdp.food.model.Model2;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FoodController {
	
	private Model2 model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtCalorie"
    private TextField txtCalorie; // Value injected by FXMLLoader

    @FXML // fx:id="txtPassi"
    private TextField txtPassi; // Value injected by FXMLLoader

    @FXML // fx:id="btnAnalisi"
    private Button btnAnalisi; // Value injected by FXMLLoader

    @FXML // fx:id="btnCorrelate"
    private Button btnCorrelate; // Value injected by FXMLLoader

    @FXML // fx:id="btnCammino"
    private Button btnCammino; // Value injected by FXMLLoader

    @FXML // fx:id="boxPorzioni"
    private ComboBox<String> boxPorzioni; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCammino(ActionEvent event) {
    	txtResult.clear();
    	txtResult.appendText("Cerco cammino peso massimo...\n\n");
    	
    	String partenza = boxPorzioni.getValue();
    	if(partenza == null) {
    		txtResult.appendText("ERRORE: devi selezionare una porzione.\n");
    		return;
    	}
    	
    	String input = txtPassi.getText();
    	try {
    		Integer passi = Integer.parseInt(input);
    		List<String> cammino = model.calcolaCammino(partenza, passi);
    		if(cammino != null) {
    			txtResult.appendText("Peso totale: "+model.getPesoMax()+"\n");
    			for(String s : cammino) {
    				txtResult.appendText(s+"\n");
    			}
    		} else {
    			txtResult.appendText("Non esiste cammino.\n");
    		}
    	} catch (NumberFormatException e) {
    		txtResult.appendText("Inserire un numero valido.\n");
    	}
    }

    @FXML
    void doCorrelate(ActionEvent event) {
    	txtResult.clear();
    	txtResult.appendText("Cerco porzioni correlate...\n\n");
    	String partenza = boxPorzioni.getValue();
    	
    	if(partenza == null) {
    		txtResult.appendText("ERRORE: scegliere un tipo di porzione. \n");
    		return;
    	}
    	
    	txtResult.appendText(model.getVerticiViciniConPeso(partenza));
    }

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	txtResult.clear();
    	txtResult.appendText("Creazione grafo...\n\n");
    	
    	String input = txtCalorie.getText();
    	try {
    		Integer calorie = Integer.parseInt(input);
    		model.creaGrafo(calorie);
    		txtResult.appendText("Grafo creato\n");
    		txtResult.appendText("vertici: "+model.nVertici()+"\n");
    		txtResult.appendText("archi: "+model.nArchi()+"\n");
    		
    		boxPorzioni.getItems().addAll(model.getVertici());
    		
    	} catch (NumberFormatException e) {
    		txtResult.appendText("Inserire un numero valido.\n");
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtCalorie != null : "fx:id=\"txtCalorie\" was not injected: check your FXML file 'Food.fxml'.";
        assert txtPassi != null : "fx:id=\"txtPassi\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnAnalisi != null : "fx:id=\"btnAnalisi\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnCorrelate != null : "fx:id=\"btnCorrelate\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnCammino != null : "fx:id=\"btnCammino\" was not injected: check your FXML file 'Food.fxml'.";
        assert boxPorzioni != null : "fx:id=\"boxPorzioni\" was not injected: check your FXML file 'Food.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Food.fxml'.";

    }
    
    public void setModel(Model2 model2) {
    	this.model = model2;
    }
}
