/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.vista.iugrafica;

import java.text.SimpleDateFormat;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import mvc.modelo.dominio.Alquiler;
import mvc.modelo.dominio.vehiculo.Vehiculo;

/**
 *
 * @author Felipon
 */
public class ControladorTablaAlquileresVehiculo {
        @FXML
    private Label lbMarca, lbModelo, lbMatricula;
     
     @FXML
     private TableView<Alquiler> tvAlquileresVehiculo;
     
     @FXML
     private TableColumn<Alquiler, String> tcNombre, tcNif, tcFecha;
              
     @FXML
     private TableColumn<Alquiler, Integer> tcPrecio;
    
     @FXML
     private TableColumn<Alquiler, Integer> tcDias;
               


  
    private Vehiculo vehiculo;
    private Alquiler alquiler;
    private final SimpleDateFormat FORMATO_FECHA = new SimpleDateFormat("dd/MM/yyyy HH:mm");
      
    public void setVehiculo (Vehiculo vehiculo){
        System.out.println(vehiculo);
        this.vehiculo = vehiculo;
        mostrarDatosVehiculo();
        mostrarDatosAlquiler(vehiculo);
     
    }

    @FXML
    private void initialize() {
        tcNombre.setCellValueFactory(alquiler -> new ReadOnlyStringWrapper(alquiler.getValue().getCliente().getNombre()));
        tcNombre.setResizable(false);
        tcNif.setCellValueFactory(alquiler -> new ReadOnlyStringWrapper(alquiler.getValue().getCliente().getDni()));
        tcNif.setResizable(false);       
        tcFecha.setCellValueFactory(alquiler -> new ReadOnlyStringWrapper(FORMATO_FECHA.format(alquiler.getValue().getFecha())));
        tcFecha.setResizable(false);
        tcDias.setCellValueFactory(new PropertyValueFactory<Alquiler, Integer>("dias"));
        tcDias.setResizable(false);
        tcPrecio.setCellValueFactory(new PropertyValueFactory<Alquiler, Integer>("precio"));
        tcPrecio.setResizable(false);

    }

    private void mostrarDatosAlquiler(Vehiculo vehiculo) {
     
     tvAlquileresVehiculo.setItems(FXCollections.observableArrayList(IUGrafica.controladorMVC.obtenerAlquileresVehiculo(vehiculo.getMatricula())));
       
        
    }
    
    private void mostrarDatosVehiculo(){
            if (vehiculo != null) {
            lbMarca.setText(vehiculo.getMarca());
            lbModelo.setText(vehiculo.getModelo());
            lbMatricula.setText(vehiculo.getMatricula());
           
        }
        
    }

    
}
