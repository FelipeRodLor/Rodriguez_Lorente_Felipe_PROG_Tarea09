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
import mvc.modelo.dominio.Alquiler;
import mvc.modelo.dominio.Cliente;
import mvc.modelo.dominio.DireccionPostal;

/**
 *
 * @author Felipon
 */
public class ControladorDatosAlquiler {

    @FXML
    private Label lbMarca, lbModelo, lbMatricula, lbNombre, lbNif, lbFecha, lbCerrado, lbDias, lbPrecio, lbTipoVehiculo;

    private Alquiler alquiler;

    public void setAlquiler(Alquiler alquiler) {
        this.alquiler = alquiler;
        System.out.println(alquiler);
        mostrarDatosAlquiler();
    }

    @FXML
    private void initialize() {

    }

    private void mostrarDatosAlquiler() {
        if (alquiler != null) {
            lbNombre.setText(alquiler.getCliente().getNombre());
            lbNif.setText(alquiler.getCliente().getDni());
            lbMarca.setText(alquiler.getVehiculo().getMarca());
            lbModelo.setText(alquiler.getVehiculo().getModelo());
            lbTipoVehiculo.setText(alquiler.getVehiculo().getTipoVehiculo().toString());
            lbMatricula.setText(alquiler.getVehiculo().getMatricula());
            lbFecha.setText(alquiler.getFecha().toString());
            if (alquiler.getDias() == 0) {
                lbCerrado.setText("NO");
                lbDias.setText("-");
                lbPrecio.setText("-");
            } else {
                lbCerrado.setText("SI");
                lbDias.setText((Integer.toString(alquiler.getDias())));
               lbPrecio.setText(String.valueOf(alquiler.getPrecio()));
            }

        }
    }

}
