package com.secadero.controlador;

import com.secadero.modelo.empleados.LeerEmpleado;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.ParseException;

public class ControladorCronograma {
    public ControladorCronograma(){}

    @FXML
    private Button btnActualizar;
    @FXML
    private Button btnBuscarCronogramaFecha;
    @FXML
    private Button btnBuscarListaEmpleadoDisponible;
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
    private TableColumn<?, ?> colApellido;
    @FXML
    private TableColumn<?, ?> colApellidoEmpleadoCrear;
    @FXML
    private TableColumn<?, ?> colEmailEmpleadoCrear;
    @FXML
    private TableColumn<?, ?> colFecha;
    @FXML
    private TableColumn<?, ?> colHoraEntrada;
    @FXML
    private TableColumn<?, ?> colHoraSalida;
    @FXML
    private TableColumn<?, ?> colIDCronograma;
    @FXML
    private TableColumn<?, ?> colIDEmpleado;
    @FXML
    private TableColumn<?, ?> colIDEmpleadoCrear;
    @FXML
    private TableColumn<?, ?> colLegajo;
    @FXML
    private TableColumn<?, ?> colLegajoEmpleadoCrear;
    @FXML
    private TableColumn<?, ?> colNombre;
    @FXML
    private TableColumn<?, ?> colNombreEmpleadoCrear;
    @FXML
    private TableColumn<?, ?> colTelefono;
    @FXML
    private TableColumn<?, ?> colTelefonoEmpleadoCrear;
    @FXML
    private TableColumn<?, ?> colTurno;
    @FXML
    private DatePicker dpBuscarFechaInicio;
    @FXML
    private DatePicker dpFechaCrear;
    @FXML
    private DatePicker dpFechaEliminar;
    @FXML
    private DatePicker dpFechaModificar;
    @FXML
    private Label labApellidoEmpleadoCrear;
    @FXML
    private Label labApellidoEmpleadoEliminar;
    @FXML
    private Label labApellidoEmpleadoModificar;
    @FXML
    private Label labIDCronogramaEliminar;
    @FXML
    private Label labIDCronogramaLista;
    @FXML
    private Label labIDCronogramaModificar;
    @FXML
    private Label labIDEmpleadoCrear;
    @FXML
    private Label labIDEmpleadoEliminar;
    @FXML
    private Label labIDEmpleadoLista;
    @FXML
    private Label labIDEmpleadoModificar;
    @FXML
    private Label labLegajoEmpleadoCrear;
    @FXML
    private Label labLegajoEmpleadoEliminar;
    @FXML
    private Label labLegajoEmpleadoModificar;
    @FXML
    private Label labLimpiarCamposCrear;
    @FXML
    private Label labLimpiarCamposModificar;
    @FXML
    private Label labNombreEmpleadoCrear;
    @FXML
    private Label labNombreEmpleadoEliminar;
    @FXML
    private Label labNombreEmpleadoModificar;
    @FXML
    private Label labResultadoID;
    @FXML
    private TabPane panelPestaniasCronograma;
    @FXML
    private Tab tabCalendario;
    @FXML
    private Tab tabCrearCronograma;
    @FXML
    private Tab tabEliminarCronograma;
    @FXML
    private TableView<?> tabEmpleadosCrear;
    @FXML
    private Tab tabListaCronograma;
    @FXML
    private Tab tabModificarCronograma;
    @FXML
    private TableView<?> tablaCronograma;
    @FXML
    private TableView<?> tablaEmpleados;
    @FXML
    private TableView<?> tablaListaEmpleados;
    @FXML
    private TextField textBuscarLegajoEmpleado;
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
    public void initialize() throws ParseException {
        String[] tipoFiltro = {"Nombre", "Legajo", "Fecha", "Turno"};
        String[] tipoTurnos = {"Mañana", "Tarde", "Noche"};
        cbTiposFiltrosCronograma.getItems().addAll(tipoFiltro);
        cbTiposFiltrosCronograma.getSelectionModel().selectFirst();
        cbTurnoCrear.getItems().addAll(tipoTurnos);
        cbTurnoCrear.getSelectionModel().selectFirst();
        cbTurnoModificar.getItems().addAll(tipoTurnos);
        cbTurnoModificar.getSelectionModel().selectFirst();
        cbTurnoEliminar.getItems().addAll(tipoTurnos);
        cbTurnoEliminar.getSelectionModel().selectFirst();
        relojEmpleadoDisponible();
    }

    // ------------------------------------- Tomo todos los datos al Seleccionar en la tabla ------------------------
    @FXML
    private void getListaEmpleados(){

    }

    @FXML
    private void getCronograma(){

    }

    @FXML
    private void getListaEmpleadosCrear(){

    }


    // --------------------------------------------- Búsqueda y Filtros -----------------------------------------
    @FXML
    private void btnBuscarEmpleadoDisponible() {

    }

    @FXML
    private void btnBuscarCronograma() {

    }

    @FXML
    private void filtroCronograma() {

    }

    @FXML
    private void actualizarTabla() {

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

    // ---------------------------- Reloj del estado de la fecha actual del empleado-----------------------------
    private void relojEmpleadoDisponible() throws ParseException {
        LeerEmpleado.relojEmpleados();
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
