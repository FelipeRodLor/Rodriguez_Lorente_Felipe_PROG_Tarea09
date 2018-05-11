package mvc.vista.iugrafica;


import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import mvc.controlador.IControladorAlquilerVehiculos;
import mvc.vista.IVistaAlquilerVehiculos;
import mvc.vista.iugrafica.utilidades.Dialogos;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Felipon
 */
public class IUGrafica extends Application implements IVistaAlquilerVehiculos{
    
    public static IControladorAlquilerVehiculos controladorMVC;


    
    public void start (Stage escenarioPrincipal){
        try {
			SplitPane raiz = (SplitPane)FXMLLoader.load(getClass().getResource("/mvc/vista/iugrafica/vistas/GestionDeAlquileres.fxml"));
			Scene escena = new Scene(raiz);
                        escenarioPrincipal.setOnCloseRequest(e -> confirmarSalida(escenarioPrincipal, e));
			escenarioPrincipal.setTitle("Gestión de Alquileres");
			escenarioPrincipal.setScene(escena);
                        escenarioPrincipal.setResizable(false);
			escenarioPrincipal.show();
                                             
                       

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
    private void confirmarSalida(Stage escenarioPrincipal, WindowEvent e) {
		if (Dialogos.mostrarDialogoConfirmacion("Dialogo de confirmación", "¿Deseas abandonar la aplicacion?", escenarioPrincipal)) {
			controladorMVC.salir();
			escenarioPrincipal.close();
		}
		else
			e.consume();
			
	}
    
    @Override
    public void setControlador(IControladorAlquilerVehiculos controlador) {
     IUGrafica.controladorMVC = controlador;
             }
        
    @Override
    public void comenzar (){
       launch (this.getClass());
    }
	
	
    }

