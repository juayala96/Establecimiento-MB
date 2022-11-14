package com.secadero.controlador;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ControladorUsuarios {
    public ControladorUsuarios(){};

    @FXML
    private Button btnBuscarUsuario;
    @FXML
    private Button btnCrearUsuario;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnEliminarUsuario;
    @FXML
    private Button btnFiltroUsuarios;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnGuardarCambios;
    @FXML
    private Button btnModificarUsuario;
    @FXML
    private Button btnRegresarCLista;
    @FXML
    private Button btnRegresarELista;
    @FXML
    private Button btnRegresarMLista;
    @FXML
    private Button btnVolver;
    @FXML
    private ComboBox<?> cbRolCrear;
    @FXML
    private ComboBox<?> cbRolEliminar;
    @FXML
    private ComboBox<?> cbRolModificar;
    @FXML
    private ComboBox<?> cbTiposFiltrosUsuarios;
    @FXML
    private TableColumn<?, ?> colApellido;
    @FXML
    private TableColumn<?, ?> colContrasenia;
    @FXML
    private TableColumn<?, ?> colEmail;
    @FXML
    private TableColumn<?, ?> colID;
    @FXML
    private TableColumn<?, ?> colLegajo;
    @FXML
    private TableColumn<?, ?> colNombre;
    @FXML
    private TableColumn<?, ?> colRol;
    @FXML
    private TableColumn<?, ?> colTelefono;
    @FXML
    private Label labErrorApellidoCrear;
    @FXML
    private Label labErrorApellidoModificar;
    @FXML
    private Label labErrorContraseniaCrear;
    @FXML
    private Label labErrorContraseniaModificar;
    @FXML
    private Label labErrorEmailCrear;
    @FXML
    private Label labErrorEmailModificar;
    @FXML
    private Label labErrorLegajoCrear;
    @FXML
    private Label labErrorLegajoModificar;
    @FXML
    private Label labErrorNombreCrear;
    @FXML
    private Label labErrorNombreModificar;
    @FXML
    private Label labErrorRepetirContraseniaCrear;
    @FXML
    private Label labErrorRepetirContraseniaModificar;
    @FXML
    private Label labErrorTelefonoCrear;
    @FXML
    private Label labErrorTelefonoModificar;
    @FXML
    private Label labIDEliminar;
    @FXML
    private Label labIDModificar;
    @FXML
    private Label labLimpiarCamposCrear;
    @FXML
    private Label labLimpiarCamposModificar;
    @FXML
    private TabPane panelPestaniasUsuarios;
    @FXML
    private Tab tabCrearUsuario;
    @FXML
    private Tab tabEliminarUsuario;
    @FXML
    private Tab tabListaUsuarios;
    @FXML
    private Tab tabModificarUsuario;
    @FXML
    private TableView<?> tablaUsuarios;
    @FXML
    private TextField textApellidoCrear;
    @FXML
    private TextField textApellidoEliminar;
    @FXML
    private TextField textApellidoModificar;
    @FXML
    private TextField textBuscarUsuario;
    @FXML
    private TextField textContraseniaCrear;
    @FXML
    private TextField textContraseniaEliminar;
    @FXML
    private TextField textContraseniaModificar;
    @FXML
    private TextField textEmailCrear;
    @FXML
    private TextField textEmailEliminar;
    @FXML
    private TextField textEmailModificar;
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
    private TextField textRepetirContraseniaCrear;
    @FXML
    private TextField textRepetirContraseniaEliminar;
    @FXML
    private TextField textRepetirContraseniaModificar;
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
    private void filtroUsuarios() {

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
    private void crearUsuario() {
        SingleSelectionModel<Tab> modeloSeleccion = panelPestaniasUsuarios.getSelectionModel();
        modeloSeleccion.select(tabCrearUsuario);
        textNombreCrear.requestFocus();
    }

    @FXML
    private void modificarUsuario() {
        SingleSelectionModel<Tab> modeloSeleccion = panelPestaniasUsuarios.getSelectionModel();
        modeloSeleccion.select(tabModificarUsuario);
        textNombreModificar.requestFocus();
    }

    @FXML
    private void eliminarUsuario() {
        SingleSelectionModel<Tab> modeloSeleccion = panelPestaniasUsuarios.getSelectionModel();
        modeloSeleccion.select(tabEliminarUsuario);
        btnEliminar.requestFocus();
    }

    @FXML
    private void regresarCLista() {
        SingleSelectionModel<Tab> modeloSeleccion = panelPestaniasUsuarios.getSelectionModel();
        modeloSeleccion.select(tabListaUsuarios);
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
        closeWindowsPrincipalUsuario();
    }

    // ---------------------------------- Cerrar Ventana -------------------------------------------
    public void closeWindowsPrincipalUsuario() throws IOException {
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