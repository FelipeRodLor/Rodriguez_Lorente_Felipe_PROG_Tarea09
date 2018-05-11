/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.vista;

import mvc.controlador.IControladorAlquilerVehiculos;

/**
 *
 * @author Felipon
 */
public interface IVistaAlquilerVehiculos {

    void comenzar();
    
    void setControlador(IControladorAlquilerVehiculos controlador);

    
}
