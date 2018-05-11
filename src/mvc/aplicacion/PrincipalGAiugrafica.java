/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.aplicacion;

import mvc.controlador.ControladorAlquilerVehiculos;
import mvc.controlador.IControladorAlquilerVehiculos;
import mvc.modelo.IModeloAlquilerVehiculos;
import mvc.modelo.ModeloAlquilerVehiculos;
import mvc.vista.IVistaAlquilerVehiculos;
import mvc.vista.iugrafica.IUGrafica;


/**
 *
 * @author Felipon
 */
public class PrincipalGAiugrafica {
    
    public static void main(String[] args) {
        IVistaAlquilerVehiculos vista = new IUGrafica();
        IModeloAlquilerVehiculos modelo = new ModeloAlquilerVehiculos();
        IControladorAlquilerVehiculos controlador = new ControladorAlquilerVehiculos(modelo, vista);
        controlador.comenzar();
    }
}
