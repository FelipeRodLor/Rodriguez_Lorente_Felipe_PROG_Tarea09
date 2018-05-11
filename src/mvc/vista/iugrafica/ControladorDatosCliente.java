/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.vista.iugrafica;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mvc.modelo.dominio.Cliente;
import mvc.modelo.dominio.DireccionPostal;

/**
 *
 * @author Felipon
 */
public class ControladorDatosCliente {


    @FXML
    private Label lbNombre, lbNif, lbLocalidad, lbDireccion, lbCodigoPostal;
    
    private Cliente cliente;
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        System.out.println(cliente);
        mostrarDetalleCliente();
    }

  
    @FXML
    private void initialize() {

    }

  private void mostrarDetalleCliente() {
      if (cliente != null) {
        lbNombre.setText(cliente.getNombre());
        lbNif.setText(cliente.getDni());
        lbLocalidad.setText(cliente.direccionPostal.getLocalidad());
        lbDireccion.setText(cliente.direccionPostal.getCalle());
        lbCodigoPostal.setText(cliente.direccionPostal.getCodigoPostal());
      }
    }

}
