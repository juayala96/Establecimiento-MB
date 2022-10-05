package com.secadero.controlador;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ControladorCronograma {
    public ControladorCronograma(){}

    @FXML
    private Button btnBuscarCronograma;
    @FXML
    private Button btnBuscarDisponibleCrear;
    @FXML
    private Button btnBuscarDisponibleModificar;
    @FXML
    private Button btnCalendario;
    @FXML
    private Button btnCrearCronograma;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnEliminarCronograma;
    @FXML
    private Button btnFiltroEmpleados;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnModificarCronograma;
    @FXML
    private Button btnRegresarCCMenu;
    @FXML
    private Button btnRegresarCMenu;
    @FXML
    private Button btnRegresarEMenu;
    @FXML
    private Button btnRegresarMMenu;
    @FXML
    private Button btnVolver;
    @FXML
    private ComboBox<String> cbTiposFiltrosCronograma;
    @FXML
    private ComboBox<String> cbTurnoCrear;
    @FXML
    private ComboBox<String> cbTurnoEliminar;
    @FXML
    private ComboBox<String> cbTurnoModificar;
    @FXML
    private DatePicker dbBuscarFecha;
    @FXML
    private DatePicker dpFechaCrear;
    @FXML
    private DatePicker dpFechaEliminar;
    @FXML
    private DatePicker dpFechaModificar;
    @FXML
    private Label labHoraLlegadaCrear;
    @FXML
    private Label labHoraLlegadaModificar;
    @FXML
    private Label labHoraSalidaCrear;
    @FXML
    private Label labHoraSalidaModificar;
    @FXML
    private TabPane panelPestaniasCronograma;
    @FXML
    private Tab tabCalendario;
    @FXML
    private Tab tabCrearCronograma;
    @FXML
    private Tab tabEliminarCronograma;
    @FXML
    private Tab tabListaCronograma;
    @FXML
    private Tab tabModificarCronograma;
    @FXML
    private TreeView<String> tabViewEmpleadosDisponiblesCrear;
    @FXML
    private TreeView<String> tabViewEmpleadosDisponiblesModificar;
    @FXML
    private TableView<String> tablaCronograma;
    @FXML
    private TableView<String> tablaEmpleados;
    @FXML
    private TextField textHoraLlegadaCrear;
    @FXML
    private TextField textHoraLlegadaEliminar;
    @FXML
    private TextField textHoraLlegadaModificar;
    @FXML
    private TextField textHoraSalidaCrear;
    @FXML
    private TextField textHoraSalidaEliminar;
    @FXML
    private TextField textHoraSalidaModificar;

    // -------------------------------------------- Inicialización ----------------------------------------------
    public void initialize() {}


    // --------------------------------------------- Búsqueda y Filtros -----------------------------------------
    @FXML
    private void btnBuscar() {

    }

    @FXML
    private void filtroCronograma() {

    }

    // ----------------------------------- Búsqueda Disponible Empleados ----------------------------------------
    @FXML
    private void buscarDisponibleCrear() {

    }

    @FXML
    private void buscarDisponibleModificar() {

    }
    //---------------------------------------------- Eventos Importantes ----------------------------------------

    @FXML
    private void guardar() {

    }

    @FXML
    private void modificar() {

    }

    @FXML
    private void eliminar() {

    }

    //------------------------------------ Acciones Simples de los Botones --------------------------------------
    @FXML
    private void crearCronograma() {
        SingleSelectionModel<Tab> modeloSeleccion = panelPestaniasCronograma.getSelectionModel();
        modeloSeleccion.select(tabCrearCronograma);
        dpFechaCrear.requestFocus();
    }

    @FXML
    private void modificarCronograma() {
        SingleSelectionModel<Tab> modeloSeleccion = panelPestaniasCronograma.getSelectionModel();
        modeloSeleccion.select(tabModificarCronograma);
        dpFechaModificar.requestFocus();
    }

    @FXML
    private void eliminarCronograma() {
        SingleSelectionModel<Tab> modeloSeleccion = panelPestaniasCronograma.getSelectionModel();
        modeloSeleccion.select(tabEliminarCronograma);
        btnEliminar.requestFocus();
    }

    @FXML
    private void irCalendario() {
        SingleSelectionModel<Tab> modeloSeleccion = panelPestaniasCronograma.getSelectionModel();
        modeloSeleccion.select(tabCalendario);
    }

    @FXML
    private void regresarCMenu() {
        SingleSelectionModel<Tab> modeloSeleccion = panelPestaniasCronograma.getSelectionModel();
        modeloSeleccion.select(tabListaCronograma);
    }

    @FXML
    private void regresarMMenu() {
        regresarCMenu();
    }

    @FXML
    private void regresarEMenu() {
        regresarCMenu();
    }

    @FXML
    private void regresarCCMenu() {
        regresarCMenu();
    }

    @FXML
    private void volverPrincipal() throws IOException {
        closeWindowsPrincipalCronograma();
    }


    // ---------------------------------- Cerrar Ventana -------------------------------------------
    public void closeWindowsPrincipalCronograma() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/principal.fxml"));
        AnchorPane root = loader.load();
        Scene escena = new Scene(root);
        Stage escenario = new Stage();
        escenario.setTitle("Gestión Secadero");
        escenario.setScene(escena);
        escenario.show();

        Stage myEscena = (Stage) this.btnVolver.getScene().getWindow();
        myEscena.close();
    }
}
