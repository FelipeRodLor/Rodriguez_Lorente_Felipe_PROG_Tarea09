/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.vista.iutextual;

import mvc.vista.iutextual.Opcion;
import mvc.controlador.IControladorAlquilerVehiculos;
import mvc.vista.iutextual.utilidades.Consola;
import mvc.modelo.dominio.ExcepcionAlquilerVehiculos;
import mvc.modelo.dominio.Alquiler;
import mvc.modelo.dominio.vehiculo.Vehiculo;
import mvc.modelo.dominio.Cliente;
import mvc.vista.IVistaAlquilerVehiculos;

/**
 *
 * @author Felipillo
 *
 */
public class IUTextual implements IVistaAlquilerVehiculos {

    IControladorAlquilerVehiculos controlador;

    public IUTextual() {
        Opcion.setVista(this);

    }

    @Override
    public void setControlador(IControladorAlquilerVehiculos controlador) {
        this.controlador = controlador;

    }

    @Override
    public void comenzar() {
        int ordinalOpcion;

        do {
            Consola.mostrarMenu();
            ordinalOpcion = Consola.elegirOpcion();
            Opcion opcion = Opcion.getOpcionSegunOridnal(ordinalOpcion);
            opcion.ejecutar();

        } while (ordinalOpcion != Opcion.SALIR.ordinal());

    }

   
    public void salir() {
        System.out.println("HAS ABANDONADO SATISFACTORIAMENTE");
        controlador.salir();
    }

    
    public void abrirAlquiler() {

        Consola.mostrarCabecera("APERTURA DE ALQUILER");
        String dniAlquiler = Consola.leerDni();
        String matriculaAlquiler = Consola.leerMatricula();

        try {
            Cliente clienteAlquiler = controlador.buscarCliente(dniAlquiler);
            Vehiculo vehiculoAlquiler = controlador.buscarVehiculo(matriculaAlquiler);
            controlador.abrirAlquiler(clienteAlquiler, vehiculoAlquiler);
            System.out.println("\nOperacion realizada");

        } catch (ExcepcionAlquilerVehiculos e) {
            System.out.printf("\nERROR: %s%n%n", e.getMessage());
        }
    }

   
    public void cerrarAlquiler() {

        Consola.mostrarCabecera("CIERRE DE ALQUILER");
        String dniCierre = Consola.leerDni();
        String matriculaCierre = Consola.leerMatricula();

        try {
            Cliente clienteAlquiler = controlador.buscarCliente(dniCierre);
            Vehiculo turismoAlquiler = controlador.buscarVehiculo(matriculaCierre);
            controlador.cerrarAlquiler(clienteAlquiler, turismoAlquiler);
            System.out.println("\nOperacion realizada");

        } catch (ExcepcionAlquilerVehiculos e) {
            System.out.printf("\nERROR:  %s%n%n", e.getMessage());
        }
    }

    
    public void listarAlquileres() {
        Consola.mostrarCabecera("LISTADO DE ALQUILERES");

        for (Alquiler listaAlquileres : controlador.obtenerAlquileres()) {

            if (listaAlquileres != null) {
                System.out.println(listaAlquileres);
            }
        }
    }

   
    public void obtenerAlquileresAbiertos() {
        Consola.mostrarCabecera("LISTADO DE ALQUILERES ABIERTOS");

        for (Alquiler listaAlquileresAbiertos : controlador.obtenerAlquileresAbiertos()) {
            System.out.println(listaAlquileresAbiertos);

        }
    }

    
    public void obtenerAlquileresCliente() {
        String dni = Consola.leerDni();

        try {
            controlador.buscarCliente(dni);
            Consola.mostrarCabecera("LISTADO DE ALQUILERES DEL CLIENTE");

            if (controlador.obtenerAlquileresCliente(dni).isEmpty()) {
                System.out.println("No existen alquileres relacionados con el cliente introducido");
            } else {
                for (Alquiler listaAlquileresCliente : controlador.obtenerAlquileresCliente(dni)) {
                    System.out.println(listaAlquileresCliente);
                }
            }
        } catch (ExcepcionAlquilerVehiculos e) {
            System.out.printf("\nERROR: %s%n%n", e.getMessage());
        }
    }

   
    public void obtenerAlquileresVehiculo() {
        String matricula = Consola.leerMatricula();
        try {
            controlador.buscarVehiculo(matricula);
            Consola.mostrarCabecera("LISTADO DE ALQUILERES DEL VEHICULO");

            if (controlador.obtenerAlquileresVehiculo(matricula).isEmpty()) {
                System.out.println("No existen alquileres relacionados con el vehiculo introducido");
            } else {
                for (Alquiler listaAlquileresVehiculo : controlador.obtenerAlquileresVehiculo(matricula)) {
                    System.out.println(listaAlquileresVehiculo);
                }
            }
        } catch (ExcepcionAlquilerVehiculos e) {
            System.out.printf("\nERROR: %s%n%n", e.getMessage());
        }
    }

   
    public void listarVehiculos() {
        Consola.mostrarCabecera("LISTADO DE VEHICULOS");

        for (Vehiculo listaVehiculo : controlador.obtenerVehiculos()) {

            if (listaVehiculo != null) {
                System.out.println(listaVehiculo);
            }
        }
    }

 
    public void borrarVehiculo() {
        Consola.mostrarCabecera("BORRAR VEHICULO");
        String matriculaBorrar = Consola.leerMatricula();

        try {
            controlador.borrarVehiculo(matriculaBorrar);
            System.out.println("\nOperacion realizada");

        } catch (ExcepcionAlquilerVehiculos e) {
            System.out.printf("\nERROR: %s%n%n", e.getMessage());
        }
    }

   
    public void buscarVehiculo() {
        Consola.mostrarCabecera("BUSCAR VEHICULO");
        String matriculaBuscar = Consola.leerMatricula();

        try {
            System.out.println("");
            System.out.println(controlador.buscarVehiculo(matriculaBuscar));
        } catch (ExcepcionAlquilerVehiculos e) {
            System.out.printf("\nERROR: %s%n%n", e.getMessage());
        }
    }

    
    public void anadirVehiculo() {
        Consola.mostrarCabecera("ALTA VEHICULO");
        Vehiculo vehiculo = Consola.leerVehiculo();

        if (vehiculo != null) {

            try {
                controlador.anadirVehiculo(vehiculo);
                System.out.println("\nOperacion realizada");

            } catch (ExcepcionAlquilerVehiculos i) {
                System.out.printf("\nERROR: %s%n%n", i.getMessage());
            }
        }
    }

   
    public void listarClientes() {
        Consola.mostrarCabecera("LISTADO DE CLIENTES");

        for (Cliente listaCliente : controlador.obtenerClientes()) {

            if (listaCliente != null) {
                System.out.println(listaCliente);
            }
        }
    }

    
    public void borrarCliente() {
        Consola.mostrarCabecera("BORRAR CLIENTE");
        String dniBorrar = Consola.leerDni();

        try {
            controlador.borrarCliente(dniBorrar);
            System.out.println("\nOperacion realizada");

        } catch (ExcepcionAlquilerVehiculos e) {
            System.out.printf("\nERROR: %s%n%n", e.getMessage());
        }
    }


    public void buscarCliente() {
        Consola.mostrarCabecera("BUSCAR CLIENTE");
        String dniBuscar = Consola.leerDni();

        try {
            System.out.println("");
            System.out.println(controlador.buscarCliente(dniBuscar));
        } catch (ExcepcionAlquilerVehiculos e) {
            System.out.printf("\nERROR: %s%n%n", e.getMessage());
        }

    }


    public void anadirCliente() {
        Consola.mostrarCabecera("ALTA CLIENTE");
        Cliente cliente = Consola.leerCliente();

        if (cliente != null) {
            try {
                controlador.anadirCliente(cliente);
                System.out.println("\nOperacion realizada");

            } catch (ExcepcionAlquilerVehiculos e) {
                System.out.printf("\nERROR: %s%n%n", e.getMessage());
            }
        }
    }
}
