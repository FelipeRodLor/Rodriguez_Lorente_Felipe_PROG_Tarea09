/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.vista.iugrafica;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import mvc.modelo.dominio.vehiculo.Vehiculo;

/**
 *
 * @author Felipon
 */
public class ControladorDatosVehiculo {
     @FXML
    private Label lbMarca, lbModelo, lbMatricula, lbCilindrada, lbNumeroPlazas, lbPma, lbTipoVehiculo;
    
    private Vehiculo vehiculo;
    
    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
        mostrarDetalleVehiculo();
    }

  
    @FXML
    private void initialize() {

    }

  private void mostrarDetalleVehiculo() {
      if (vehiculo != null) {
        lbMarca.setText(vehiculo.getMarca());
        lbModelo.setText(vehiculo.getModelo());
        lbMatricula.setText(vehiculo.getMatricula());
        lbCilindrada.setText(Integer.toString(vehiculo.getDatosTecnicos().getCilindrada()));
        lbNumeroPlazas.setText(Integer.toString(vehiculo.getDatosTecnicos().getNumeroPlazas()));
        lbPma.setText(Integer.toString(vehiculo.getDatosTecnicos().getPma()));
        lbTipoVehiculo.setText(vehiculo.getTipoVehiculo().toString());
      }
    }
}
