/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.vista.iugrafica;

import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import mvc.modelo.dominio.Alquiler;
import mvc.modelo.dominio.Cliente;
import mvc.modelo.dominio.vehiculo.TipoVehiculo;
import mvc.modelo.dominio.vehiculo.Vehiculo;

/**
 *
 * @author Felipon
 */
public class ControladorTablaAlquileresCliente {
     @FXML
    private Label lbNombre, lbNif;
     
     @FXML
     private TableView<Alquiler> tvAlquileresCliente;
     
     @FXML
     private TableColumn<Alquiler, String> tcMarca, tcModelo, tcMatricula, tcFecha;
     
     @FXML
     private TableColumn<Alquiler, TipoVehiculo> tcTipoVehiculo;
    
     @FXML
     private TableColumn<Alquiler, Integer> tcDias;
     

            


  
    private Cliente cliente;
    private Alquiler alquiler;
    private final SimpleDateFormat FORMATO_FECHA = new SimpleDateFormat("dd/MM/yyyy HH:mm");
      
    public void setCliente (Cliente cliente){
        this.cliente = cliente;
        mostrarDatosCliente();
        mostrarDatosAlquiler(cliente);
    }

    @FXML
    private void initialize() {
        tcMarca.setCellValueFactory(alquiler -> new ReadOnlyStringWrapper(alquiler.getValue().getVehiculo().getMarca()));
        tcMarca.setResizable(false);
        tcModelo.setCellValueFactory(alquiler -> new ReadOnlyStringWrapper(alquiler.getValue().getVehiculo().getModelo()));
        tcModelo.setResizable(false);
        tcMatricula.setCellValueFactory(alquiler -> new ReadOnlyStringWrapper(alquiler.getValue().getVehiculo().getMatricula()));
        tcMatricula.setResizable(false);
        tcTipoVehiculo.setCellValueFactory(alquiler -> new ReadOnlyObjectWrapper<>(alquiler.getValue().getVehiculo().getTipoVehiculo()));
        tcTipoVehiculo.setResizable(false);
        tcFecha.setCellValueFactory(alquiler -> new ReadOnlyStringWrapper(FORMATO_FECHA.format(alquiler.getValue().getFecha())));
        tcFecha.setResizable(false);
        tcDias.setCellValueFactory(new PropertyValueFactory<Alquiler, Integer>("dias"));
        tcDias.setResizable(false);

    }

    private void mostrarDatosAlquiler(Cliente cliente) {
     
     tvAlquileresCliente.setItems(FXCollections.observableArrayList(IUGrafica.controladorMVC.obtenerAlquileresCliente(cliente.getDni())));
       
        
    }
    
    private void mostrarDatosCliente(){
            if (cliente != null) {
            lbNombre.setText(cliente.getNombre());
            lbNif.setText(cliente.getDni());
           
        }
        
    }

    
}
