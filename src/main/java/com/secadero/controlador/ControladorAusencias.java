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
    private ComboBox<String> cbTiposFiltrosAusencias;
    @FXML
    private DatePicker dbBuscarFecha;
    @FXML
    private DatePicker dpFechaCrear;
    @FXML
    private DatePicker dpFechaEliminar;
    @FXML
    private DatePicker dpFechaModificar;
    @FXML
    private TabPane panelPestaniasAusencias;
    @FXML
    private RadioButton rbJustificadoNoCrear;
    @FXML
    private RadioButton rbJustificadoNoEliminar;
    @FXML
    private RadioButton rbJustificadoNoModificar;
    @FXML
    private RadioButton rbJustificadoSiCrear;
    @FXML
    private RadioButton rbJustificadoSiEliminar;
    @FXML
    private RadioButton rbJustificadoSiModificar;
    @FXML
    private Tab tabEliminarAusencia;
    @FXML
    private Tab tabListaAusencias;
    @FXML
    private Tab tabModificarAusencia;
    @FXML
    private Tab tabRegistrarAusencia;
    @FXML
    private TableView<String> tablaAusencias;
    @FXML
    private TextArea textAreaMotivoCrear;
    @FXML
    private TextArea textAreaMotivoEliminar;
    @FXML
    private TextArea textAreaMotivoModificar;
    @FXML
    private TextField textCertificadoCrear;
    @FXML
    private TextField textCertificadoEliminar;
    @FXML
    private TextField textCertificadoModificar;

    // -------------------------------------------- Inicialización ----------------------------------------------
    public void initialize() {}

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
