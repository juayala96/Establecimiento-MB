package com.secadero.controlador;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.IOException;

public class ControladorEmpleados {
    public ControladorEmpleados(){}

    @FXML
    private Button btnAgregarImagenCrear;
    @FXML
    private Button btnAgregarImagenModificar;
    @FXML
    private Button btnAusenciasEmpleado;
    @FXML
    private Button btnBuscarEmpleado;
    @FXML
    private Button btnCrearEmpleado;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnEliminarEmpleado;
    @FXML
    private Button btnFiltroUsuarios;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnLicenciasEmpleado;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnModificarEmpleado;
    @FXML
    private Button btnRegresarCLista;
    @FXML
    private Button btnRegresarELista;
    @FXML
    private Button btnRegresarMLista;
    @FXML
    private Button btnVolver;
    @FXML
    private ComboBox<?> cbAreaCrear;
    @FXML
    private ComboBox<?> cbAreaEliminar;
    @FXML
    private ComboBox<?> cbAreaModificar;
    @FXML
    private ComboBox<?> cbEstadoCivilCrear;
    @FXML
    private ComboBox<?> cbEstadoCivilEliminar;
    @FXML
    private ComboBox<?> cbEstadoCivilModificar;
    @FXML
    private ComboBox<?> cbGeneroCrear;
    @FXML
    private ComboBox<?> cbGeneroEliminar;
    @FXML
    private ComboBox<?> cbGeneroModificar;
    @FXML
    private ComboBox<?> cbPuestoCrear;
    @FXML
    private ComboBox<?> cbPuestoEliminar;
    @FXML
    private ComboBox<?> cbPuestoModificar;
    @FXML
    private ComboBox<?> cbTiposfiltrosEmpleados;
    @FXML
    private TableColumn<?, ?> colApellido;
    @FXML
    private TableColumn<?, ?> colArea;
    @FXML
    private TableColumn<?, ?> colFechaIngreso;
    @FXML
    private TableColumn<?, ?> colID;
    @FXML
    private TableColumn<?, ?> colLegajo;
    @FXML
    private TableColumn<?, ?> colNombre;
    @FXML
    private TableColumn<?, ?> colPuesto;
    @FXML
    private TableColumn<?, ?> colTelefono;
    @FXML
    private DatePicker dpFechaIngresoCrear;
    @FXML
    private DatePicker dpFechaIngresoEliminar;
    @FXML
    private DatePicker dpFechaIngresoModificar;
    @FXML
    private DatePicker dpFechaNaciminetoCrear;
    @FXML
    private DatePicker dpFechaNaciminetoEliminar;
    @FXML
    private DatePicker dpFechaNaciminetoModificar;
    @FXML
    private ImageView imgFotografiaCrear;
    @FXML
    private ImageView imgFotografiaEliminar;
    @FXML
    private ImageView imgFotografiaModificar;
    @FXML
    private Label labErrorApellidoCrear;
    @FXML
    private Label labErrorApellidoModificar;
    @FXML
    private Label labErrorDireccionCrear;
    @FXML
    private Label labErrorDireccionModificar;
    @FXML
    private Label labErrorFechaNacimientoCrear;
    @FXML
    private Label labErrorFechaNacimientoModificar;
    @FXML
    private Label labErrorFotografiaCrear;
    @FXML
    private Label labErrorFotografiaModificar;
    @FXML
    private Label labErrorGrupoSanguineoCrear;
    @FXML
    private Label labErrorGrupoSanguineoModificar;
    @FXML
    private Label labErrorLegajoCrear;
    @FXML
    private Label labErrorLegajoModificar;
    @FXML
    private Label labErrorNombreCrear;
    @FXML
    private Label labErrorNombreModificar;
    @FXML
    private Label labErrorTelefonoCrear;
    @FXML
    private Label labErrorTelefonoModificar;
    @FXML
    private Label labIDEliminar;
    @FXML
    private Label labIDModificar;
    @FXML
    private Label labInformacionCrearLegajo;
    @FXML
    private Label labInformacionModificarLegajo;
    @FXML
    private Label labLimpiarCamposCrear;
    @FXML
    private Label labLimpiarCamposModificar;
    @FXML
    private TabPane panelPestaniasEmpleados;
    @FXML
    private Tab tabCrearEmpleado;
    @FXML
    private Tab tabEliminarEmpleado;
    @FXML
    private Tab tabListaEmpleado;
    @FXML
    private Tab tabModificarEmpleado;
    @FXML
    private TableView<?> tablaEmpleados;
    @FXML
    private TextField textApellidoCrear;
    @FXML
    private TextField textApellidoEliminar;
    @FXML
    private TextField textApellidoModificar;
    @FXML
    private TextField textBuscarEmpleado;
    @FXML
    private TextField textDireccionCrear;
    @FXML
    private TextField textDireccionEliminar;
    @FXML
    private TextField textDireccionModificar;
    @FXML
    private TextField textGrupoSanguineoCrear;
    @FXML
    private TextField textGrupoSanguineoEliminar;
    @FXML
    private TextField textGrupoSanguineoModificar;
    @FXML
    private TextField textLegajoCrear;
    @FXML
    private TextField textLegajoEliminar;
    @FXML
    private TextField textLegajoModificar;
    @FXML
    private TextField textNombreCrear;
    @FXML
    private TextField textNombreEliminar;
    @FXML
    private TextField textNombreModificar;
    @FXML
    private TextField textTelefonoCrear;
    @FXML
    private TextField textTelefonoEliminar;
    @FXML
    private TextField textTelefonoModificar;

    // -------------------------------------------- Inicialización ----------------------------------------------
    public void initialize() {}

    // --------------------------------------------- Búsqueda y Filtros -----------------------------------------

    @FXML
    private void btnBuscar() {

    }

    @FXML
    private void filtroEmpleado() {

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
    //------------------------------------------- Agregar Imagen ------------------------------------------------
    @FXML
    private void agregarImagenCrear(){}

    @FXML
    private void agregarImagenModificar(){}


    //------------------------------------ Acciones Simples de los Botones --------------------------------------
    @FXML
    private void crearEmpleado() {
        SingleSelectionModel<Tab> modeloSeleccion = panelPestaniasEmpleados.getSelectionModel();
        modeloSeleccion.select(tabCrearEmpleado);
        textLegajoCrear.requestFocus();
    }

    @FXML
    private void modificarEmpleado() {
        SingleSelectionModel<Tab> modeloSeleccion = panelPestaniasEmpleados.getSelectionModel();
        modeloSeleccion.select(tabModificarEmpleado);
        textLegajoModificar.requestFocus();
    }

    @FXML
    private void eliminarEmpleado() {
        SingleSelectionModel<Tab> modeloSeleccion = panelPestaniasEmpleados.getSelectionModel();
        modeloSeleccion.select(tabEliminarEmpleado);
        btnEliminar.requestFocus();
    }

    @FXML
    private void ausenciasEmpleado() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/ausencias.fxml"));
        AnchorPane root = loader.load();
        ControladorAusencias ausencias = loader.getController();
        Scene escena = new Scene(root);
        Stage escenario = new Stage();
        escenario.setTitle("Gestión Secadero (Ausencias)");
        escenario.setScene(escena);
        escenario.show();

        escenario.setOnCloseRequest(e -> {
            try {
                ausencias.closeWindowsPrincipalAusencia();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        Stage myEscena = (Stage) this.btnAusenciasEmpleado.getScene().getWindow();
        myEscena.close();
    }

    @FXML
    private void licenciasEmpleado() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/licencias.fxml"));
        AnchorPane root = loader.load();
        ControladorLicencias licencias = loader.getController();
        Scene escena = new Scene(root);
        Stage escenario = new Stage();
        escenario.setTitle("Gestión Secadero (Licencias)");
        escenario.setScene(escena);
        escenario.show();

        escenario.setOnCloseRequest(e -> {
            try {
                licencias.closeWindowsPrincipalLicencias();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        Stage myEscena = (Stage) this.btnAusenciasEmpleado.getScene().getWindow();
        myEscena.close();
    }

    @FXML
    private void regresarCLista() {
        SingleSelectionModel<Tab> modeloSeleccion = panelPestaniasEmpleados.getSelectionModel();
        modeloSeleccion.select(tabListaEmpleado);
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
    private void volverPrincipal() throws IOException {
        closeWindowsPrincipalEmpleado();
    }

    // ---------------------------------- Cerrar Ventana -------------------------------------------
    public void closeWindowsPrincipalEmpleado() throws IOException {
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
