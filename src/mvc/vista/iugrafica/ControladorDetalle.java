/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.vista.iugrafica;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author Felipon
 */
public class ControladorDetalle {
    
    @FXML
    private Button btCerrar;
    
    @FXML
    private void cerrarDetalle(){
         
    Stage stage = (Stage) btCerrar.getScene().getWindow();
    stage.close();
}
    }

