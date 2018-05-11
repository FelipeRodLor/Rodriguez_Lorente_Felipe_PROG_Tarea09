/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.vista.iugrafica.utilidades;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Felipon
 */
public class Dialogos {

   public static void mostrarDialogoError(String titulo, String contenido, Stage propietario) {
		Alert dialogo = new Alert(AlertType.ERROR);
		
		dialogo.setTitle(titulo);
		dialogo.setHeaderText(null);
		dialogo.setContentText(contenido);
		if (propietario != null) {
			dialogo.initModality(Modality.APPLICATION_MODAL);
			dialogo.initOwner(propietario);
		}
		dialogo.showAndWait();
		if (propietario != null)
			propietario.close();
	}
	
	public static void mostrarDialogoError(String titulo, String contenido) {
		Dialogos.mostrarDialogoError(titulo, contenido, null);
	}

    public static boolean mostrarDialogoConfirmacion(String titulo, String contenido, Stage propietario) {
        Alert dialogo = new Alert(Alert.AlertType.CONFIRMATION);
        dialogo.setTitle(titulo);
        dialogo.setHeaderText(null);
        dialogo.setContentText(contenido);
        if (propietario != null) {
            dialogo.initModality(Modality.APPLICATION_MODAL);
            dialogo.initOwner(propietario);
        }

        Optional<ButtonType> respuesta = dialogo.showAndWait();

        return (respuesta.get() == ButtonType.OK ? true : false);

    }

    public static boolean mostrarDialogoConfirmacion(String titulo, String contenido) {
      return  Dialogos.mostrarDialogoConfirmacion(titulo, contenido, null);
    }

    public static void mostrarDialogoAviso(String titulo, String contenido, Stage propietario) {
        Alert dialogo = new Alert(AlertType.WARNING);
        dialogo.setTitle(titulo);
        dialogo.setHeaderText(null);
        dialogo.setContentText(contenido);
        if (propietario != null) {
            dialogo.initModality(Modality.APPLICATION_MODAL);
            dialogo.initOwner(propietario);
        }
        dialogo.showAndWait();
        if (propietario != null) {
            propietario.close();
        }
    }

    public static void mostrarDialogoAviso(String titulo, String contenido) {
        Dialogos.mostrarDialogoAviso(titulo, contenido, null);
    }

    public static void mostrarDialogoInformacion(String titulo, String contenido, Stage propietario) {
        Alert dialogo = new Alert(AlertType.INFORMATION);
        dialogo.setTitle(titulo);
        dialogo.setHeaderText(null);
        dialogo.setContentText(contenido);
        if (propietario != null) {
            dialogo.initModality(Modality.APPLICATION_MODAL);
            dialogo.initOwner(propietario);
        }
        dialogo.showAndWait();
        if (propietario != null) {
            propietario.close();
        }
    }

    public static void mostrarDialogoInformacion(String titulo, String contenido) {
        Dialogos.mostrarDialogoInformacion(titulo, contenido, null);
    }
}
