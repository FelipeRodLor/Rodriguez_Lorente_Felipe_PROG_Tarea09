package mvc.vista.iugrafica;

import java.awt.Toolkit;
import java.io.IOException;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mvc.modelo.dominio.Alquiler;
import mvc.modelo.dominio.Cliente;
import mvc.modelo.dominio.DireccionPostal;
import mvc.modelo.dominio.ExcepcionAlquilerVehiculos;
import mvc.modelo.dominio.vehiculo.DatosTecnicosVehiculo;
import mvc.modelo.dominio.vehiculo.TipoVehiculo;
import mvc.modelo.dominio.vehiculo.Vehiculo;
import mvc.vista.iugrafica.utilidades.Dialogos;

public class ControladorVista {

    @FXML
    private Button btCancelarC, btEjecutarC, btEliminarVehiculo, btDetalleAlquiler, btCerrarAlquiler, btDetalleVehiculo;

    @FXML
    private VBox vbAccionesClientes, vbAccionesVehiculos, vbAccionesAlquileres;

    @FXML
    private RadioButton rbAnadirCliente, rbTurismo, rbAutobus, rbDeCarga, rbAnadirVehiculo, rbAnadirAlquiler;

    private ToggleGroup tgTipoDeVehiculo;

    @FXML
    private CheckBox cbAlquileresAbiertos;

    @FXML
    private HBox hbAnadirCliente, hbAnadirVehiculo, hbAnadirAlquiler;

    @FXML
    private TextField tfNombre, tfNif, tfLocalidad, tfDireccion, tfCodigoPostal, tfMatricula, tfMarca, tfModelo, tfCilindrada, tfNumeroPlazas, tfPMA, tfMatriculaAlquiler, tfNifAlquiler;

    @FXML
    private TableView<Cliente> tvClientes;

    @FXML
    private TableView<Vehiculo> tvVehiculos;

    @FXML
    private TableView<Alquiler> tvAlquileres;

    @FXML
    private TableColumn<Cliente, String> tcNombre, tcNif;

    @FXML
    private TableColumn<Vehiculo, String> tcMarca, tcModelo, tcMatricula;
    
    @FXML
    private TableColumn<Vehiculo, Boolean>  tcDisponible;   

    @FXML
    private TableColumn<Alquiler, String> tcNombreA, tcNifA, tcMarcaA, tcMatriculaA;

    @FXML
    private TableColumn<Alquiler, Integer> tcAbiertoA;

    private ControladorDatosCliente controladorDatosCliente;
    private ControladorDatosVehiculo controladorDatosVehiculo;
    private ControladorDatosAlquiler controladorDatosAlquiler;
    private ControladorTablaAlquileresCliente controladorTablaAlquileresCliente;
    private ControladorTablaAlquileresVehiculo controladorTablaAlquileresVehiculo;
    private Stage detalle, detalleCliente, detalleVehiculo, detalleAlquiler, tablaAlquileresCliente, tablaAlquileresVehiculo;

    private final int TAMANO_MAXIMO_TFCODIGOPOSTAL = 5;
    private final int TAMANO_MAXIMO_TFNOMBRE = 37;
    private final int TAMANO_MAXIMO_TFNIF = 9;
    private final int TAMANO_MAXIMO_TFLOCALIDAD = 37;
    private final int TAMANO_MAXIMO_TFDIRECCION = 37;
    private final int TAMANO_MAXIMO_TFMODELO = 32;
    private final int TAMANO_MAXIMO_TFMARCA = 32;
    private final int TAMANO_MAXIMO_TFMATRICULA = 7;
    private final int TAMANO_MAXIMO_TFCILINDRADA = 4;
    private final int TAMANO_MAXIMO_TFNUMEROPLAZAS = 3;
    private final int TAMANO_MAXIMO_TFPMA = 4;

    @FXML
    private void initialize() throws IOException {

        crearTablaClientes();
        crearTablaVehiculos();
        crearTablaAlquileres();
        crearDetalleCliente();
        crearDetalleVehiculo();
        crearDetalleAlquiler();
        crearTablaAlquileresCliente();
        crearTablaAlquileresVehiculos();

        tgTipoDeVehiculo = new ToggleGroup();
        rbAutobus.setToggleGroup(tgTipoDeVehiculo);
        rbDeCarga.setToggleGroup(tgTipoDeVehiculo);
        rbTurismo.setToggleGroup(tgTipoDeVehiculo);

    }
    
    
    private void crearTablaAlquileresCliente() throws IOException {
      
        tablaAlquileresCliente = new Stage();
        FXMLLoader cargadorTablaAlquileresCliente = new FXMLLoader(
                getClass().getResource("/mvc/vista/iugrafica/vistas/TablaAlquileresCliente.fxml"));
        VBox raizTablaAlquileresCliente = (VBox) cargadorTablaAlquileresCliente.load();
        controladorTablaAlquileresCliente = cargadorTablaAlquileresCliente.getController();
        
        Scene escenaTablaAlquileresCliente = new Scene(raizTablaAlquileresCliente);
        tablaAlquileresCliente.setTitle("Alquileres Cliente");
        tablaAlquileresCliente.initModality(Modality.APPLICATION_MODAL);
        tablaAlquileresCliente.setScene(escenaTablaAlquileresCliente);
        tablaAlquileresCliente.setResizable(false);



    }
    
      private void crearTablaAlquileresVehiculos() throws IOException {
      
        tablaAlquileresVehiculo = new Stage();
        FXMLLoader cargadorTablaAlquileresVehiculo = new FXMLLoader(
                getClass().getResource("/mvc/vista/iugrafica/vistas/TablaAlquileresVehiculo.fxml"));
        VBox raizTablaAlquileresVehiculo = (VBox) cargadorTablaAlquileresVehiculo.load();
        controladorTablaAlquileresVehiculo = cargadorTablaAlquileresVehiculo.getController();
        
        Scene escenaTablaAlquileresVehiculo = new Scene(raizTablaAlquileresVehiculo);
        tablaAlquileresVehiculo.setTitle("Alquileres Vehiculo");
        tablaAlquileresVehiculo.initModality(Modality.APPLICATION_MODAL);
        tablaAlquileresVehiculo.setScene(escenaTablaAlquileresVehiculo);
        tablaAlquileresVehiculo.setResizable(false);



    }

    private void crearDetalleCliente() throws IOException {
        detalle = new Stage();
        FXMLLoader cargadorDetalle = new FXMLLoader(
                getClass().getResource("/mvc/vista/iugrafica/vistas/Detalle.fxml"));
        VBox raizDetalle = (VBox) cargadorDetalle.load();

        detalleCliente = new Stage();
        FXMLLoader cargadorDetalleCliente = new FXMLLoader(
                getClass().getResource("/mvc/vista/iugrafica/vistas/DatosCliente.fxml"));
        VBox raizDetalleCliente = (VBox) cargadorDetalleCliente.load();
        controladorDatosCliente = cargadorDetalleCliente.getController();
        raizDetalle.getChildren().add(0, raizDetalleCliente);
        Scene escenaDetalleCliente = new Scene(raizDetalle);
        detalleCliente.setTitle("Detalle del Cliente");
        detalleCliente.initModality(Modality.APPLICATION_MODAL);
        detalleCliente.setScene(escenaDetalleCliente);
        detalleCliente.centerOnScreen();

    }

    private void crearDetalleVehiculo() throws IOException {
        detalle = new Stage();
        FXMLLoader cargadorDetalle = new FXMLLoader(
                getClass().getResource("/mvc/vista/iugrafica/vistas/Detalle.fxml"));
        VBox raizDetalle = (VBox) cargadorDetalle.load();

        detalleVehiculo = new Stage();
        FXMLLoader cargadorDetalleVehiculo = new FXMLLoader(
                getClass().getResource("/mvc/vista/iugrafica/vistas/DatosVehiculo.fxml"));
        VBox raizDetalleVehiculo = (VBox) cargadorDetalleVehiculo.load();
        controladorDatosVehiculo = cargadorDetalleVehiculo.getController();
        raizDetalle.getChildren().add(0, raizDetalleVehiculo);
        Scene escenaDetalleVehiculo = new Scene(raizDetalle);
        detalleVehiculo.setTitle("Detalle del Vehiculo");
        detalleVehiculo.initModality(Modality.APPLICATION_MODAL);
        detalleVehiculo.setScene(escenaDetalleVehiculo);
        detalleVehiculo.centerOnScreen();

    }

    private void crearDetalleAlquiler() throws IOException {
        detalle = new Stage();
        FXMLLoader cargadorDetalle = new FXMLLoader(
                getClass().getResource("/mvc/vista/iugrafica/vistas/Detalle.fxml"));
        VBox raizDetalle = (VBox) cargadorDetalle.load();

        detalleAlquiler = new Stage();
        FXMLLoader cargadorDetalleAlquiler = new FXMLLoader(
                getClass().getResource("/mvc/vista/iugrafica/vistas/DatosAlquiler.fxml"));
        VBox raizDetalleAlquiler = (VBox) cargadorDetalleAlquiler.load();
        controladorDatosAlquiler = cargadorDetalleAlquiler.getController();
        raizDetalle.getChildren().add(0, raizDetalleAlquiler);
        Scene escenaDetalleVehiculo = new Scene(raizDetalle);
        detalleAlquiler.setTitle("Detalle del Alquiler");
        detalleAlquiler.initModality(Modality.APPLICATION_MODAL);
        detalleAlquiler.setScene(escenaDetalleVehiculo);
        detalleAlquiler.centerOnScreen();

    }

    private void crearTablaAlquileres() {
        tcNombreA.setCellValueFactory(alquiler -> new ReadOnlyStringWrapper(alquiler.getValue().getCliente().getNombre()));
        tcNombreA.setResizable(false);
        tcNifA.setCellValueFactory(alquiler -> new ReadOnlyStringWrapper(alquiler.getValue().getCliente().getDni()));
        tcNifA.setResizable(false);
        tcMarcaA.setCellValueFactory(alquiler -> new ReadOnlyStringWrapper(alquiler.getValue().getVehiculo().getMarca()));
        tcMarcaA.setResizable(false);
        tcMatriculaA.setCellValueFactory(alquiler -> new ReadOnlyStringWrapper(alquiler.getValue().getVehiculo().getMatricula()));
        tcMatriculaA.setResizable(false);
        tcAbiertoA.setCellValueFactory(new PropertyValueFactory<Alquiler, Integer>("dias"));
        tcAbiertoA.setResizable(false);

        tvAlquileres.setItems(FXCollections.observableArrayList(IUGrafica.controladorMVC.obtenerAlquileres()));
        tvAlquileres.getSelectionModel().selectedItemProperty().addListener(
                (ov, oldValue, newValue) -> accionesAlquileres(ov.getValue()));
    }

    private void crearTablaVehiculos() {
        tcMarca.setCellValueFactory(new PropertyValueFactory<Vehiculo, String>("marca"));
        tcMarca.setResizable(false);
        tcModelo.setCellValueFactory(new PropertyValueFactory<Vehiculo, String>("modelo"));
        tcModelo.setResizable(false);
        tcMatricula.setCellValueFactory(new PropertyValueFactory<Vehiculo, String>("matricula"));
        tcMatricula.setResizable(false);
       // tcDisponible.setCellValueFactory(new PropertyValueFactory<Vehiculo, String>("disponible"));
        //tcDisponible.setResizable(false);
        tcDisponible.setCellValueFactory(vehiculo -> new SimpleBooleanProperty(vehiculo.getValue().getDisponible()));
	tcDisponible.setCellFactory(vehiculo -> new CheckBoxTableCell<>());
        tvVehiculos.setItems(FXCollections.observableArrayList(IUGrafica.controladorMVC.obtenerVehiculos()));
        tvVehiculos.getSelectionModel().selectedItemProperty().addListener(
                (ov, oldValue, newValue) -> accionesVehiculos(ov.getValue()));
    }

    private void crearTablaClientes() throws IOException {

        tcNombre.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nombre"));
        tcNombre.setResizable(false);
        tcNif.setCellValueFactory(new PropertyValueFactory<Cliente, String>("dni"));
        tcNombre.setResizable(false);
        tvClientes.setItems(FXCollections.observableArrayList(IUGrafica.controladorMVC.obtenerClientes()));
        tvClientes.getSelectionModel().selectedItemProperty().addListener(
                (ov, oldValue, newValue) -> accionesClientes(ov.getValue()));
    }

    public void accionesClientes(Cliente cliente) {
        vbAccionesClientes.setDisable(false);
        controladorDatosCliente.setCliente(cliente);
        controladorTablaAlquileresCliente.setCliente(cliente);
    }

    public void accionesVehiculos(Vehiculo vehiculo) {
        vbAccionesVehiculos.setDisable(false);
        controladorDatosVehiculo.setVehiculo(vehiculo);
        controladorTablaAlquileresVehiculo.setVehiculo(vehiculo);
       

    }

    public void accionesAlquileres(Alquiler alquiler) {
        if (tvAlquileres.isPressed() && tvAlquileres.getSelectionModel().getSelectedItem().getDias() == 0) {
            btCerrarAlquiler.setDisable(false);
        } else {
            btCerrarAlquiler.setDisable(true);
        }

        btDetalleAlquiler.setDisable(false);
        controladorDatosAlquiler.setAlquiler(alquiler);
    }

    @FXML
    private void eliminarCliente() {
        if (Dialogos.mostrarDialogoConfirmacion("Aviso", "Vas a eliminar al cliente, ¿estas seguro?")) {
            try {
                IUGrafica.controladorMVC.borrarCliente(tvClientes.getSelectionModel().getSelectedItem().getDni());
                Dialogos.mostrarDialogoInformacion("Información", "Operación Realizada");
                tvClientes.setItems(FXCollections.observableArrayList(IUGrafica.controladorMVC.obtenerClientes()));
                vbAccionesClientes.setDisable(true);
            } catch (ExcepcionAlquilerVehiculos e) {
                Dialogos.mostrarDialogoError("Error", e.getMessage());

            }
        }
    }

    @FXML
    private void eliminarVehiculo() {
        if (Dialogos.mostrarDialogoConfirmacion("Aviso", "Vas a eliminar el vehículo, ¿estas seguro?")) {
            try {
                IUGrafica.controladorMVC.borrarVehiculo(tvVehiculos.getSelectionModel().getSelectedItem().getMatricula());
                Dialogos.mostrarDialogoInformacion("Información", "Operación Realizada");
                tvVehiculos.setItems(FXCollections.observableArrayList(IUGrafica.controladorMVC.obtenerVehiculos()));
                vbAccionesVehiculos.setDisable(true);
            } catch (ExcepcionAlquilerVehiculos e) {
                Dialogos.mostrarDialogoError("Error", e.getMessage());

            }
        }
    }

    @FXML
    private void mostrarDetalleCliente() throws IOException {

        detalleCliente.showAndWait();

    }

    @FXML
    private void mostrarDetalleVehiculo() throws IOException {

        detalleVehiculo.showAndWait();

    }

    @FXML
    private void mostrarDetalleAlquiler() throws IOException {

        detalleAlquiler.showAndWait();

    }
    
    @FXML
    private void mostrarTablaAlquileresCliente() throws IOException{
        tablaAlquileresCliente.showAndWait();
    }
    
      @FXML
    private void mostrarTablaAlquileresVehiculo() throws IOException{
        tablaAlquileresVehiculo.showAndWait();
    }

    @FXML
    private void cerrarAplicacion() {
        if (Dialogos.mostrarDialogoConfirmacion("Aviso", "¿Deseas abandonar la aplicacion?")) {
            IUGrafica.controladorMVC.salir();
            System.exit(0);
        }
    }

    @FXML
    private void rbAnadirCliente() {

        rbAnadirCliente.setDisable(true);
        hbAnadirCliente.setDisable(false);
        tvClientes.setDisable(true);
        tvClientes.getSelectionModel().clearSelection();
        vbAccionesClientes.setDisable(true);

    }

    private void deshabilitarLimpiarAnadirCliente() {
        rbAnadirCliente.setDisable(false);
        hbAnadirCliente.setDisable(true);
        rbAnadirCliente.setSelected(false);
        tvClientes.setDisable(false);
        tfNombre.setText("");
        tfNif.setText("");
        tfLocalidad.setText("");
        tfDireccion.setText("");
        tfCodigoPostal.setText("");
        rbAnadirCliente.requestFocus();
    }

    @FXML
    private void ejecutarAnadirCliente() {

        if (tfNombre.getText().isEmpty() || tfNif.getText().isEmpty() || tfLocalidad.getText().isEmpty() || tfDireccion.getText().isEmpty() || tfCodigoPostal.getText().isEmpty()) {
            Dialogos.mostrarDialogoAviso("Aviso", "Debe rellenar todos los campos");
        } else {
            try {
                DireccionPostal direccionPostal = new DireccionPostal(tfDireccion.getText(), tfLocalidad.getText(), tfCodigoPostal.getText());
                Cliente cliente = new Cliente(tfNombre.getText(), tfNif.getText(), direccionPostal);
                IUGrafica.controladorMVC.anadirCliente(cliente);
                Dialogos.mostrarDialogoInformacion("Informacion", "Operacion Realizada");
                deshabilitarLimpiarAnadirCliente();

                tvClientes.setItems(FXCollections.observableArrayList(IUGrafica.controladorMVC.obtenerClientes()));

            } catch (ExcepcionAlquilerVehiculos e) {
                Dialogos.mostrarDialogoError("Error", e.getMessage());
            }

        }
    }

    @FXML
    private void cancelarAnadirCliente() {

        if (tfNombre == null || tfNombre.getText().length() > 0 || tfNif == null || tfNif.getText().length() > 0 || tfLocalidad == null
                || tfLocalidad.getText().length() > 0 || tfDireccion == null || tfDireccion.getText().length() > 0 || tfCodigoPostal == null || tfCodigoPostal.getText().length() > 0) {

            if (Dialogos.mostrarDialogoConfirmacion("Aviso", "Si abandonas se perderán los cambios, ¿estás seguro?")) {

                deshabilitarLimpiarAnadirCliente();

            }
        } else {
            deshabilitarLimpiarAnadirCliente();

        }
    }

    @FXML
    private void compruebaCodigoPostal(KeyEvent e) {

        String texto = tfCodigoPostal.getText();
        int longitud = texto.length();
        if (longitud >= TAMANO_MAXIMO_TFCODIGOPOSTAL) {
            e.consume();
            Toolkit.getDefaultToolkit().beep();
        }
        if (!e.getCharacter().matches("[0-9]") && !Character.isISOControl(e.getCharacter().charAt(0))) {
            e.consume();
            Toolkit.getDefaultToolkit().beep();
        }

    }

    @FXML
    private void compruebaNombre(KeyEvent e) {
        String texto = tfNombre.getText();
        int longitud = texto.length();
        if (longitud >= TAMANO_MAXIMO_TFNOMBRE) {
            e.consume();
            Toolkit.getDefaultToolkit().beep();
        }

        if (!e.getCharacter().matches("[A-Za-z]") && !e.getCharacter().matches(" ") && !Character.isISOControl(e.getCharacter().charAt(0))) {
            e.consume();
            Toolkit.getDefaultToolkit().beep();
        }

    }

    @FXML
    private void compruebaLocalidad(KeyEvent e) {
        String texto = tfLocalidad.getText();
        int longitud = texto.length();
        if (longitud >= TAMANO_MAXIMO_TFLOCALIDAD) {
            e.consume();
            Toolkit.getDefaultToolkit().beep();
        }

        if (!e.getCharacter().matches("[a-zA-Z/s]") && !Character.isISOControl(e.getCharacter().charAt(0))) {
            e.consume();
            Toolkit.getDefaultToolkit().beep();
        }
    }

    @FXML
    private void compruebaNif(KeyEvent e) {

        String texto = tfNif.getText();
        int longitud = texto.length();
        if (longitud >= TAMANO_MAXIMO_TFNIF) {
            e.consume();
            Toolkit.getDefaultToolkit().beep();
        }

        if (!e.getCharacter().matches("[A-Za-z0-9]") && !e.getCharacter().matches(" ") && !Character.isISOControl(e.getCharacter().charAt(0))) {
            e.consume();
            Toolkit.getDefaultToolkit().beep();
        }

    }

    @FXML
    private void compruebaNifAlquiler(KeyEvent e) {

        String texto = tfNifAlquiler.getText();
        int longitud = texto.length();
        if (longitud >= TAMANO_MAXIMO_TFNIF) {
            e.consume();
            Toolkit.getDefaultToolkit().beep();
        }

        if (!e.getCharacter().matches("[A-Za-z0-9]") && !e.getCharacter().matches(" ") && !Character.isISOControl(e.getCharacter().charAt(0))) {
            e.consume();
            Toolkit.getDefaultToolkit().beep();
        }

    }

    @FXML
    private void compruebaDireccion(KeyEvent e) {
        String texto = tfDireccion.getText();
        int longitud = texto.length();
        if (longitud >= TAMANO_MAXIMO_TFDIRECCION) {
            e.consume();
            Toolkit.getDefaultToolkit().beep();
        }

    }

    @FXML
    private void compruebaMarca(KeyEvent e) {

        String texto = tfMarca.getText();
        int longitud = texto.length();
        if (longitud >= TAMANO_MAXIMO_TFMARCA) {
            e.consume();
            Toolkit.getDefaultToolkit().beep();
        }
        if (!e.getCharacter().matches("[a-zA-Z/s]") && !Character.isISOControl(e.getCharacter().charAt(0))) {
            e.consume();
            Toolkit.getDefaultToolkit().beep();
        }

    }

    @FXML
    private void compruebaModelo(KeyEvent e) {

        String texto = tfModelo.getText();
        int longitud = texto.length();
        if (longitud >= TAMANO_MAXIMO_TFMODELO) {
            e.consume();
            Toolkit.getDefaultToolkit().beep();
        }
        if (!e.getCharacter().matches("[a-zA-Z0-9/s]") && !Character.isISOControl(e.getCharacter().charAt(0))) {
            e.consume();
            Toolkit.getDefaultToolkit().beep();
        }

    }

    @FXML
    private void compruebaMatricula(KeyEvent e) {

        String texto = tfMatricula.getText();
        int longitud = texto.length();
        if (longitud >= TAMANO_MAXIMO_TFMATRICULA) {
            e.consume();
            Toolkit.getDefaultToolkit().beep();
        }
        if (!e.getCharacter().matches("[A-Za-z0-9]") && !Character.isISOControl(e.getCharacter().charAt(0))) {
            e.consume();
            Toolkit.getDefaultToolkit().beep();
        }

    }

    @FXML
    private void compruebaMatriculaAlquiler(KeyEvent e) {

        String texto = tfMatriculaAlquiler.getText();
        int longitud = texto.length();
        if (longitud >= TAMANO_MAXIMO_TFMATRICULA) {
            e.consume();
            Toolkit.getDefaultToolkit().beep();
        }
        if (!e.getCharacter().matches("[A-Za-z0-9]") && !Character.isISOControl(e.getCharacter().charAt(0))) {
            e.consume();
            Toolkit.getDefaultToolkit().beep();
        }

    }

    @FXML
    private void compruebaCilindrada(KeyEvent e) {

        String texto = tfCilindrada.getText();
        int longitud = texto.length();
        if (longitud >= TAMANO_MAXIMO_TFCILINDRADA) {
            e.consume();
            Toolkit.getDefaultToolkit().beep();
        }
        if (!e.getCharacter().matches("[0-9]") && !Character.isISOControl(e.getCharacter().charAt(0))) {
            e.consume();
            Toolkit.getDefaultToolkit().beep();
        }

    }

    @FXML
    private void compruebaNumeroPlazas(KeyEvent e) {

        String texto = tfNumeroPlazas.getText();
        int longitud = texto.length();
        if (longitud >= TAMANO_MAXIMO_TFNUMEROPLAZAS) {
            e.consume();
            Toolkit.getDefaultToolkit().beep();
        }
        if (!e.getCharacter().matches("[0-9]") && !Character.isISOControl(e.getCharacter().charAt(0))) {
            e.consume();
            Toolkit.getDefaultToolkit().beep();
        }

    }

    @FXML
    private void compruebaPMA(KeyEvent e) {

        String texto = tfPMA.getText();
        int longitud = texto.length();
        if (longitud >= TAMANO_MAXIMO_TFPMA) {
            e.consume();
            Toolkit.getDefaultToolkit().beep();
        }
        if (!e.getCharacter().matches("[0-9]") && !Character.isISOControl(e.getCharacter().charAt(0))) {
            e.consume();
            Toolkit.getDefaultToolkit().beep();
        }

    }

    @FXML
    private void rbAnadirVehiculo() {

        rbAnadirVehiculo.setDisable(true);
        hbAnadirVehiculo.setDisable(false);
        tvVehiculos.getSelectionModel().clearSelection();
        tvVehiculos.setDisable(true);
        vbAccionesVehiculos.setDisable(true);

    }

    private void deshabilitarLimpiarAnadirVehiculo() {
        rbAnadirVehiculo.setDisable(false);
        hbAnadirVehiculo.setDisable(true);
        rbAnadirVehiculo.setSelected(false);
        tvVehiculos.setDisable(false);
        tfMarca.setText("");
        tfModelo.setText("");
        tfMatricula.setText("");
        tfCilindrada.setText("");
        tfNumeroPlazas.setText("");
        tfPMA.setText("");
        rbAnadirVehiculo.requestFocus();
        if (rbTurismo.isSelected() || rbAutobus.isSelected() || rbDeCarga.isSelected()) {
            tgTipoDeVehiculo.getSelectedToggle().setSelected(false);
        }

    }

    @FXML
    private void cancelarAnadirVehiculo() {

        if (tfMarca.getText().length() > 0 || tfMatricula.getText().length() > 0 || tfModelo.getText().length() > 0 || tfCilindrada.getText().length() > 0
                || tfPMA.getText().length() > 0) {

            if (Dialogos.mostrarDialogoConfirmacion("Aviso", "Si abandonas se perderán los cambios, ¿estas seguro?")) {

                deshabilitarLimpiarAnadirVehiculo();

            }
        } else {
            deshabilitarLimpiarAnadirVehiculo();

        }
    }

    @FXML
    private void ejecutarAnadirVehiculo() {

        Vehiculo vehiculo;

        if (tfMarca.getText().isEmpty() || tfModelo.getText().isEmpty() || tfMatricula.getText().isEmpty() || tfCilindrada.getText().isEmpty() || tfNumeroPlazas.getText().isEmpty()
                || tfPMA.getText().isEmpty()) {
            Dialogos.mostrarDialogoAviso("Aviso", "Debe rellenar todos los campos");
        } else {
            if (!rbAutobus.isSelected() && !rbTurismo.isSelected() && !rbDeCarga.isSelected()) {
                Dialogos.mostrarDialogoAviso("Aviso", "Debe seleccionar el tipo de vehiculo");
            } else {
                try {
                    DatosTecnicosVehiculo datosTecnicos = new DatosTecnicosVehiculo(Integer.parseInt(tfCilindrada.getText()), Integer.parseInt(tfNumeroPlazas.getText()), Integer.parseInt(tfPMA.getText()));
                    if (rbTurismo.isSelected()) {
                        vehiculo = TipoVehiculo.TURISMO.getInstancia(tfMatricula.getText(), tfMarca.getText(), tfModelo.getText(), datosTecnicos);
                    } else if (rbAutobus.isSelected()) {
                        vehiculo = TipoVehiculo.AUTOBUS.getInstancia(tfMatricula.getText(), tfMarca.getText(), tfModelo.getText(), datosTecnicos);
                    } else {
                        vehiculo = TipoVehiculo.DE_CARGA.getInstancia(tfMatricula.getText(), tfMarca.getText(), tfModelo.getText(), datosTecnicos);
                    }
                    IUGrafica.controladorMVC.anadirVehiculo(vehiculo);
                    tvVehiculos.setItems(FXCollections.observableArrayList(IUGrafica.controladorMVC.obtenerVehiculos()));
                    deshabilitarLimpiarAnadirVehiculo();
                    Dialogos.mostrarDialogoInformacion("Información", "Operación realizada");

                } catch (ExcepcionAlquilerVehiculos e) {
                    Dialogos.mostrarDialogoError("Error", e.getMessage());
                }
            }
        }
    }

    @FXML
    private void listarAlquileresAbiertos() {
        if (cbAlquileresAbiertos.isSelected()) {
            tvAlquileres.setItems(FXCollections.observableArrayList(IUGrafica.controladorMVC.obtenerAlquileresAbiertos()));
            if (tvAlquileres.getItems().isEmpty()) {
                Tooltip infoNoHayDatos = new Tooltip("No hay datos");

                tvAlquileres.setTooltip(infoNoHayDatos);

            } else {
                tvAlquileres.getSelectionModel().clearSelection();
                btDetalleAlquiler.setDisable(true);
                btCerrarAlquiler.setDisable(true);
            }
        } else {
            tvAlquileres.setItems(FXCollections.observableArrayList(IUGrafica.controladorMVC.obtenerAlquileres()));
            tvAlquileres.getSelectionModel().clearSelection();
            btDetalleAlquiler.setDisable(true);
            btCerrarAlquiler.setDisable(true);
        }
    }

    @FXML
    private void rbAnadirAlquiler() {

        rbAnadirAlquiler.setDisable(true);
        hbAnadirAlquiler.setDisable(false);
        tvAlquileres.getSelectionModel().clearSelection();
        tvAlquileres.setDisable(true);
        btDetalleAlquiler.setDisable(true);
        btCerrarAlquiler.setDisable(true);
        cbAlquileresAbiertos.setDisable(true);

    }

    @FXML
    private void cancelarAnadirAlquiler() {

        if (tfMatriculaAlquiler == null || tfMatriculaAlquiler.getText().length() > 0 || tfNifAlquiler == null || tfNifAlquiler.getText().length() > 0) {

            if (Dialogos.mostrarDialogoConfirmacion("Aviso", "Si abandonas se perderán los cambios, ¿estás seguro?")) {

                deshabilitarLimpiarAnadirAlquiler();

            }
        } else {
            deshabilitarLimpiarAnadirAlquiler();

        }

    }

    private void deshabilitarLimpiarAnadirAlquiler() {
        rbAnadirAlquiler.setDisable(false);
        hbAnadirAlquiler.setDisable(true);
        rbAnadirAlquiler.setSelected(false);
        tvAlquileres.setDisable(false);
        cbAlquileresAbiertos.setDisable(false);
        tfMatriculaAlquiler.setText("");
        tfNifAlquiler.setText("");
        rbAnadirAlquiler.requestFocus();
    }

    @FXML
    private void ejecutarAnadirAlquiler() {
        if (tfMatriculaAlquiler.getText().isEmpty() || tfNifAlquiler.getText().isEmpty()) {
            Dialogos.mostrarDialogoAviso("Aviso", "Debe rellenar todos los campos");
        } else {
            try {
                Cliente cliente = new Cliente(IUGrafica.controladorMVC.buscarCliente(tfNifAlquiler.getText()));
                Vehiculo vehiculo = IUGrafica.controladorMVC.buscarVehiculo(tfMatriculaAlquiler.getText());
                Alquiler alquiler = new Alquiler(cliente, vehiculo);
                IUGrafica.controladorMVC.abrirAlquiler(cliente, vehiculo);
                Dialogos.mostrarDialogoInformacion("Informacion", "Operacion Realizada");
                deshabilitarLimpiarAnadirAlquiler();

                tvAlquileres.setItems(FXCollections.observableArrayList(IUGrafica.controladorMVC.obtenerAlquileres()));
                tvVehiculos.refresh();

            } catch (ExcepcionAlquilerVehiculos e) {
                Dialogos.mostrarDialogoError("Error", e.getMessage());
            }
        }

    }

    @FXML
    private void cerrarAlquiler() {
        if (Dialogos.mostrarDialogoConfirmacion("Aviso", "Vas a cerrar el alquiler seleccionado, ¿estas seguro?")) {
            try {
                IUGrafica.controladorMVC.cerrarAlquiler(tvAlquileres.getSelectionModel().getSelectedItem().getCliente(), tvAlquileres.getSelectionModel().getSelectedItem().getVehiculo());
                Dialogos.mostrarDialogoInformacion("Información", "Operación Realizada");
                btDetalleAlquiler.setDisable(true);
                btCerrarAlquiler.setDisable(true);
                tvAlquileres.refresh();
                tvAlquileres.refresh();

                tvVehiculos.refresh();
            } catch (ExcepcionAlquilerVehiculos e) {
                Dialogos.mostrarDialogoError("Error", e.getMessage());

            }
        }
    }

}

/**
 * private void compruebaNumero(String oldValue) { String texto =
 * tfNumerico.getText(); if (texto.matches("[0-9]*(\\.[0-9]*)?")) {
 * lbInfo.setText("Longitud: " + texto.length() + " caracteres"); } else {
 * tfNumerico.setText(oldValue); Toolkit.getDefaultToolkit().beep(); } }
 *
 */
/**
 * @Override public void initialize(URL url, ResourceBundle rb) {
 *
 *
 *    /**
 *
 * tfCodigoPostal.focusedProperty().addListener(new ChangeListener<Boolean>() {
 * @Override public void changed(ObservableValue<? extends Boolean> arg0,
 * Boolean arg1, Boolean arg2) { //Controlamos cuando dimX pierde el foco
 * if(!arg2){
 *
 * if (!tfCodigoPostal.getText().matches("([0-9]){5}")){ Tooltip tooltip = new
 * Tooltip ("Formato invalido"); tfCodigoPostal.setTooltip(tooltip); } //Aquí va
 * el código que queremos que se ejecute cuando dimX pierda el foco. * } } }); }
 *
 */
