package com.secadero.controlador;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ControladorAusencias {
    public ControladorAusencias(){}

    @FXML
    private Button btnAgregarCrear;
    @FXML
    private Button btnAgregarModificar;
    @FXML
    private Button btnBuscarAusencia;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnEliminarAusencia;
    @FXML
    private Button btnFiltroAusencias;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnModificarAusencia;
    @FXML
    private Button btnRegistrarAusencia;
    @FXML
    private Button btnRegresarCLista;
    @FXML
    private Button btnRegresarELista;
    @FXML
    private Button btnRegresarMLista;
    @FXML
    private Button btnVolver;
    @FXML
    private ComboBox<?> cbTiposFiltrosAusencias;
    @FXML
    private TableColumn<?, ?> colApellido;
    @FXML
    private TableColumn<?, ?> colLegajoEmpleadoCrear;
    @FXML
    private TableColumn<?, ?> colApellidoEmpleadoCrear;
    @FXML
    private TableColumn<?, ?> colCertificado;
    @FXML
    private TableColumn<?, ?> colEmailEmpleadoCrear;
    @FXML
    private TableColumn<?, ?> colFecha;
    @FXML
    private TableColumn<?, ?> colIDAusencia;
    @FXML
    private TableColumn<?, ?> colIDEmpleado;
    @FXML
    private TableColumn<?, ?> colIDEmpleadoCrear;
    @FXML
    private TableColumn<?, ?> colJustificado;
    @FXML
    private TableColumn<?, ?> colLegajo;
    @FXML
    private TableColumn<?, ?> colMotivo;
    @FXML
    private TableColumn<?, ?> colNombre;
    @FXML
    private TableColumn<?, ?> colNombreEmpleadoCrear;
    @FXML
    private TableColumn<?, ?> colTelefonoEmpleadoCrear;
    @FXML
    private DatePicker dbBuscarFecha;
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
    private Label labIDAusenciaEliminar;
    @FXML
    private Label labIDAusenciaModificar;
    @FXML
    private Label labIDEmpleadoEliminar;
    @FXML
    private Label labIDEmpleadoLista;
    @FXML
    private Label labIDEmpleadoModificar;
    @FXML
    private Label labJustificadoEliminar;
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
    private TabPane panelPestaniasAusencias;
    @FXML
    private RadioButton rbJustificadoNoCrear;
    @FXML
    private RadioButton rbJustificadoNoModificar;
    @FXML
    private RadioButton rbJustificadoSiCrear;
    @FXML
    private RadioButton rbJustificadoSiModificar;
    @FXML
    private Tab tabEliminarAusencia;
    @FXML
    private TableView<?> tabEmpleadosCrear;
    @FXML
    private Tab tabListaAusencias;
    @FXML
    private Tab tabModificarAusencia;
    @FXML
    private Tab tabRegistrarAusencia;
    @FXML
    private TableView<?> tablaAusencias;
    @FXML
    private TextField textBuscarAusenciaLegajoE;
    @FXML
    private TextField textCertificadoCrear;
    @FXML
    private TextField textCertificadoEliminar;
    @FXML
    private TextField textCertificadoModificar;
    @FXML
    private TextArea textMotivoCrear;
    @FXML
    private TextArea textMotivoEliminar;
    @FXML
    private TextArea textMotivoModificar;

    // -------------------------------------------- Inicialización ----------------------------------------------
    public void initialize() {

    }

    // --------------------------------------------- Búsqueda y Filtros -----------------------------------------

    @FXML
    private void btnBuscar() {

    }

    @FXML
    private void filtroAusencia() {

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
    private void registrarAusencia() {
        SingleSelectionModel<Tab> modeloSeleccion = panelPestaniasAusencias.getSelectionModel();
        modeloSeleccion.select(tabRegistrarAusencia);
        dpFechaCrear.requestFocus();
    }

    @FXML
    private void modificarAusencia() {
        SingleSelectionModel<Tab> modeloSeleccion = panelPestaniasAusencias.getSelectionModel();
        modeloSeleccion.select(tabModificarAusencia);
        dpFechaModificar.requestFocus();
    }

    @FXML
    private void eliminarAusencia() {
        SingleSelectionModel<Tab> modeloSeleccion = panelPestaniasAusencias.getSelectionModel();
        modeloSeleccion.select(tabEliminarAusencia);
        btnEliminar.requestFocus();
    }

    @FXML
    private void regresarCLista() {
        SingleSelectionModel<Tab> modeloSeleccion = panelPestaniasAusencias.getSelectionModel();
        modeloSeleccion.select(tabListaAusencias);
    }

    @FXML
    private void regresarMLista() {
        regresarCLista();
    }

    @FXML
    private void regresarELista() {
        regresarCLista();
    }

    @FXML
    private void volverEmpleados() throws IOException {
        closeWindowsPrincipalAusencia();
    }

    // ---------------------------------- Cerrar Ventana -------------------------------------------
    public void closeWindowsPrincipalAusencia() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/empleados.fxml"));
        AnchorPane root = loader.load();
        Scene escena = new Scene(root);
        Stage escenario = new Stage();
        escenario.setTitle("Gestión Secadero (Empleados)");
        escenario.setScene(escena);
        escenario.show();

        Stage myEscena = (Stage) this.btnVolver.getScene().getWindow();
        myEscena.close();
    }
}
