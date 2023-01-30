package com.secadero.controlador;

import com.secadero.modelo.empleados.LeerComboBoxes.*;
import com.secadero.modelo.empleados.LeerEmpleado;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.Date;

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
    private ComboBox<String> cbAreaCrear;
    @FXML
    private ComboBox<String> cbAreaEliminar;
    @FXML
    private ComboBox<String> cbAreaModificar;
    @FXML
    private ComboBox<String> cbEstadoCivilCrear;
    @FXML
    private ComboBox<String> cbEstadoCivilEliminar;
    @FXML
    private ComboBox<String> cbEstadoCivilModificar;
    @FXML
    private ComboBox<String> cbGeneroCrear;
    @FXML
    private ComboBox<String> cbGeneroEliminar;
    @FXML
    private ComboBox<String> cbGeneroModificar;
    @FXML
    private ComboBox<String> cbGrupoSanguineoCrear;
    @FXML
    private ComboBox<String> cbGrupoSanguineoEliminar;
    @FXML
    private ComboBox<String> cbGrupoSanguineoModificar;
    @FXML
    private ComboBox<String> cbPuestoCrear;
    @FXML
    private ComboBox<String> cbPuestoEliminar;
    @FXML
    private ComboBox<String> cbPuestoModificar;
    @FXML
    private ComboBox<String> cbTiposfiltrosEmpleados;
    @FXML
    private TableColumn<LeerEmpleado, String> colApellido;
    @FXML
    private TableColumn<LeerEmpleado, String> colArea;
    @FXML
    private TableColumn<LeerEmpleado, Date> colFechaIngreso;
    @FXML
    private TableColumn<LeerEmpleado, Integer> colID;
    @FXML
    private TableColumn<LeerEmpleado, Integer> colLegajo;
    @FXML
    private TableColumn<LeerEmpleado, String> colNombre;
    @FXML
    private TableColumn<LeerEmpleado, String> colPuesto;
    @FXML
    private TableColumn<LeerEmpleado, String> colTelefono;
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
    private TableView<LeerEmpleado> tablaEmpleados;
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

    ObservableList<LeerEmpleado> listEmpleado;
    int index = -1;

    String ErrorCrear = "";
    String ErrorModificar = "";

    // -------------------------------------------- Inicialización ----------------------------------------------
    public void initialize() {
        String[] tipoFiltro = {"Nombre", "Apellido", "Legajo", "Area", "Puesto"};
        cbTiposfiltrosEmpleados.getItems().addAll(tipoFiltro);
        cbTiposfiltrosEmpleados.getSelectionModel().selectFirst();
        //inicializarComboBoxBD();
        inicializarTabla();
    }

    public void inicializarTabla(){
        colNombre.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, String>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, String>("apellido"));
        colLegajo.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, Integer>("legajo"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, String>("telefono"));
        colFechaIngreso.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, Date>("fechaIngreso"));
        colArea.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, String>("idAreaFK"));
        colPuesto.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, String>("idPuestoFK"));
        colID.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, Integer>("idempleados"));

        listEmpleado = LeerEmpleado.listaEmpleados();
        for (int i = 0; i < listEmpleado.size(); i++) {
            System.out.println(listEmpleado.get(i).getNombre());
            System.out.println(listEmpleado.get(i).getApellido());
            System.out.println(listEmpleado.get(i).getLegajo());
            System.out.println(listEmpleado.get(i).getTelefono());
            System.out.println(listEmpleado.get(i).getIdAreaFK());
            System.out.println(listEmpleado.get(i).getIdPuestoFK());
        }
        tablaEmpleados.getColumns().setAll(colNombre, colApellido, colLegajo, colTelefono, colFechaIngreso, colArea, colPuesto, colID);
        tablaEmpleados.getItems().setAll(listEmpleado);
    }

    public void inicializarComboBoxBD() {
        // --------------------- Genero del Empleado -----------------------
        String datoEmpleado_Genero;
        GeneroEmpleado generoEmpleado = new GeneroEmpleado();
        ObservableList<GeneroEmpleado> empleado_Genero = generoEmpleado.getGenero_Empleado();
        for (int i = 0; i < empleado_Genero.size(); i++) {
            datoEmpleado_Genero = empleado_Genero.get(i).getDescripcion();
            cbGeneroCrear.getItems().addAll(datoEmpleado_Genero);
            cbGeneroModificar.getItems().addAll(datoEmpleado_Genero);
            cbGeneroEliminar.getItems().addAll(datoEmpleado_Genero);
        }
        cbGeneroCrear.getSelectionModel().selectFirst();
        cbGeneroModificar.getSelectionModel().selectFirst();
        cbGeneroEliminar.getSelectionModel().selectFirst();

        // --------------------- Estado Civil del Empleado -----------------------
        String datoEmpleado_EstadoCivil;
        EstadoCivilEmpleado estadoCivilEmpleado = new EstadoCivilEmpleado();
        ObservableList<EstadoCivilEmpleado> empleado_EstadoCivil = estadoCivilEmpleado.getEstadoCivil_Empleado();
        for (int i = 0; i < empleado_EstadoCivil.size(); i++) {
            datoEmpleado_EstadoCivil = empleado_EstadoCivil.get(i).getDescripcion();
            cbEstadoCivilCrear.getItems().addAll(datoEmpleado_EstadoCivil);
            cbEstadoCivilModificar.getItems().addAll(datoEmpleado_EstadoCivil);
            cbEstadoCivilEliminar.getItems().addAll(datoEmpleado_EstadoCivil);
        }
        cbEstadoCivilCrear.getSelectionModel().selectFirst();
        cbEstadoCivilModificar.getSelectionModel().selectFirst();
        cbEstadoCivilEliminar.getSelectionModel().selectFirst();

        // --------------------- Grupo Sanguine del Empleado -----------------------
        String datoEmpleado_GrupoSanguineo;
        GrupoSanguineoEmpleado grupoSanguineoEmpleado = new GrupoSanguineoEmpleado();
        ObservableList<GrupoSanguineoEmpleado> empleado_GrupoSanguineo = grupoSanguineoEmpleado.getGrupoSanguineo_Empleado();
        for (int i = 0; i < empleado_GrupoSanguineo.size(); i++) {
            datoEmpleado_GrupoSanguineo = empleado_GrupoSanguineo.get(i).getDescripcion();
            cbGrupoSanguineoCrear.getItems().addAll(datoEmpleado_GrupoSanguineo);
            cbGrupoSanguineoModificar.getItems().addAll(datoEmpleado_GrupoSanguineo);
            cbGrupoSanguineoEliminar.getItems().addAll(datoEmpleado_GrupoSanguineo);
        }
        cbGrupoSanguineoCrear.getSelectionModel().selectFirst();
        cbGrupoSanguineoModificar.getSelectionModel().selectFirst();
        cbGrupoSanguineoEliminar.getSelectionModel().selectFirst();

        // --------------------- Area del Empleado -----------------------
        String datoEmpleado_Area;
        AreaEmpleado areaEmpleado = new AreaEmpleado();
        ObservableList<AreaEmpleado> empleado_Area = areaEmpleado.getArea_Empleado();
        for (int i = 0; i < empleado_Area.size(); i++) {
            datoEmpleado_Area = empleado_Area.get(i).getDescripcion();
            cbAreaCrear.getItems().addAll(datoEmpleado_Area);
            cbAreaModificar.getItems().addAll(datoEmpleado_Area);
            cbAreaEliminar.getItems().addAll(datoEmpleado_Area);
        }
        cbAreaCrear.getSelectionModel().selectFirst();
        cbAreaModificar.getSelectionModel().selectFirst();
        cbAreaEliminar.getSelectionModel().selectFirst();

        // --------------------- Puesto del Empleado -----------------------
        String datoEmpleado_Puesto;
        PuestoEmpleado puestoEmpleado = new PuestoEmpleado();
        ObservableList<PuestoEmpleado> empleado_Puesto = puestoEmpleado.getPuesto_Empleado();
        for (int i = 0; i < empleado_Puesto.size(); i++) {
            datoEmpleado_Puesto = empleado_Puesto.get(i).getDescripcion();
            cbPuestoCrear.getItems().addAll(datoEmpleado_Puesto);
            cbPuestoModificar.getItems().addAll(datoEmpleado_Puesto);
            cbPuestoEliminar.getItems().addAll(datoEmpleado_Puesto);
        }
        cbPuestoCrear.getSelectionModel().selectFirst();
        cbPuestoModificar.getSelectionModel().selectFirst();
        cbPuestoEliminar.getSelectionModel().selectFirst();
    }
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
        ErrorCrear = "";
        TextField[] campos = {textNombreCrear, textApellidoCrear, textLegajoCrear, textLegajoCrear, textTelefonoCrear, textDireccionCrear};


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
