package com.secadero.controlador;

import com.secadero.modelo.ausencia.LeerAusencia;
import com.secadero.modelo.licencias.LeerLicencia;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Date;

public class ControladorLicencias {
    public ControladorLicencias(){}

    @FXML
    private Button btnActualizar;
    @FXML
    private Button btnBuscarLicencia;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnEliminarLicencia;
    @FXML
    private Button btnFiltroLicencias;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnModificarLicencia;
    @FXML
    private Button btnRegistrarLicencia;
    @FXML
    private Button btnRegresarCLista;
    @FXML
    private Button btnRegresarELista;
    @FXML
    private Button btnRegresarMLista;
    @FXML
    private Button btnVolver;
    @FXML
    private ComboBox<?> cbTipoLicenciaCrear;
    @FXML
    private ComboBox<?> cbTipoLicenciaEliminar;
    @FXML
    private ComboBox<?> cbTipoLicenciaModificar;
    @FXML
    private ComboBox<?> cbTiposFiltrosLicencia;
    @FXML
    private TableColumn<LeerLicencia, String> colApellido;
    @FXML
    private TableColumn<?, ?> colApellidoEmpleadoCrear;
    @FXML
    private TableColumn<?, ?> colEmailEmpleadoCrear;
    @FXML
    private TableColumn<LeerLicencia, Date> colFechaFin;
    @FXML
    private TableColumn<LeerLicencia, Date> colFechaInicio;
    @FXML
    private TableColumn<LeerLicencia, Integer> colIDEmpleado;
    @FXML
    private TableColumn<?, ?> colIDEmpleadoCrear;
    @FXML
    private TableColumn<LeerLicencia, Integer> colIDLicencia;
    @FXML
    private TableColumn<LeerLicencia, Integer> colLegajo;
    @FXML
    private TableColumn<?, ?> colLegajoEmpleadoCrear;
    @FXML
    private TableColumn<LeerLicencia, String> colNombre;
    @FXML
    private TableColumn<?, ?> colNombreEmpleadoCrear;
    @FXML
    private TableColumn<LeerLicencia, String> colTelefono;
    @FXML
    private TableColumn<?, ?> colTelefonoEmpleadoCrear;
    @FXML
    private TableColumn<LeerLicencia, String> colTipoLicencia;
    @FXML
    private DatePicker dpBuscarFechaInicio;
    @FXML
    private DatePicker dpFechaFinCrear;
    @FXML
    private DatePicker dpFechaFinEliminar;
    @FXML
    private DatePicker dpFechaFinModificar;
    @FXML
    private DatePicker dpFechaInicioCrear;
    @FXML
    private DatePicker dpFechaInicioEliminar;
    @FXML
    private DatePicker dpFechaInicioModificar;
    @FXML
    private DatePicker dpFechaModificarDuplicada;
    @FXML
    private Label labApellidoEmpleadoCrear;
    @FXML
    private Label labApellidoEmpleadoEliminar;
    @FXML
    private Label labApellidoEmpleadoModificar;
    @FXML
    private Label labDiasDisponiblesCrear;
    @FXML
    private Label labDiasDisponiblesEliminar;
    @FXML
    private Label labDiasDisponiblesModificar;
    @FXML
    private Label labIDEmpleadoCrear;
    @FXML
    private Label labIDEmpleadoEliminar;
    @FXML
    private Label labIDEmpleadoLista;
    @FXML
    private Label labIDEmpleadoModificar;
    @FXML
    private Label labIDLicenciaEliminar;
    @FXML
    private Label labIDLicenciaModificar;
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
    private TabPane panelPestaniasLicencias;
    @FXML
    private Tab tabEliminarLicencia;
    @FXML
    private TableView<?> tabEmpleadosCrear;
    @FXML
    private Tab tabListaLicencias;
    @FXML
    private Tab tabModificarLicencia;
    @FXML
    private Tab tabRegistrarLicencia;
    @FXML
    private TableView<LeerLicencia> tablaLicencia;
    @FXML
    private TextField textBuscarLicenciaLegajoE;

    ObservableList<LeerLicencia> listLicencia;
    int index = -1;

    // -------------------------------------------- Inicialización ----------------------------------------------
    public void initialize() {
        inicializarTabla();
    }

    public void inicializarTabla(){
        colNombre.setCellValueFactory(new PropertyValueFactory<LeerLicencia, String>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<LeerLicencia, String>("apellido"));
        colLegajo.setCellValueFactory(new PropertyValueFactory<LeerLicencia, Integer>("legajo"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<LeerLicencia, String>("telefono"));
        colFechaInicio.setCellValueFactory(new PropertyValueFactory<LeerLicencia, Date>("fecha_Inicio"));
        colFechaFin.setCellValueFactory(new PropertyValueFactory<LeerLicencia, Date>("fecha_Fin"));
        colTipoLicencia.setCellValueFactory(new PropertyValueFactory<LeerLicencia, String>("descripcion"));
        colIDLicencia.setCellValueFactory(new PropertyValueFactory<LeerLicencia, Integer>("idLicencias"));
        colIDEmpleado.setCellValueFactory(new PropertyValueFactory<LeerLicencia, Integer>("idempleados"));

        listLicencia = LeerLicencia.listaLiencia();
        tablaLicencia.getColumns().setAll(colNombre, colApellido, colLegajo, colTelefono, colFechaInicio, colFechaFin, colTipoLicencia, colIDLicencia, colIDEmpleado);
        tablaLicencia.getItems().setAll(listLicencia);
    }

    @FXML
    private void getLicencia(){}

    @FXML
    private void getEmpleadoCrear(){}

    // --------------------------------------------- Búsqueda y Filtros -----------------------------------------

    @FXML
    private void btnBuscar() {

    }

    @FXML
    private void filtroLicencia() {

    }

    @FXML
    private void actualizarTabla() {}

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
    private void registrarLicencia() {
        SingleSelectionModel<Tab> modeloSeleccion = panelPestaniasLicencias.getSelectionModel();
        modeloSeleccion.select(tabRegistrarLicencia);
        dpFechaInicioCrear.requestFocus();
    }

    @FXML
    private void modificarLicencia() {
        SingleSelectionModel<Tab> modeloSeleccion = panelPestaniasLicencias.getSelectionModel();
        modeloSeleccion.select(tabModificarLicencia);
        dpFechaInicioModificar.requestFocus();
    }

    @FXML
    private void eliminarLicencia() {
        SingleSelectionModel<Tab> modeloSeleccion = panelPestaniasLicencias.getSelectionModel();
        modeloSeleccion.select(tabEliminarLicencia);
        btnEliminar.requestFocus();
    }

    @FXML
    private void regresarCLista() {
        SingleSelectionModel<Tab> modeloSeleccion = panelPestaniasLicencias.getSelectionModel();
        modeloSeleccion.select(tabListaLicencias);
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
        closeWindowsPrincipalLicencias();
    }

    // ---------------------------------- Cerrar Ventana -------------------------------------------
    public void closeWindowsPrincipalLicencias() throws IOException {
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
