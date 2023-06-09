package com.secadero.controlador;

import com.secadero.modelo.ausencia.*;
import com.secadero.modelo.empleados.LeerEmpleado;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

public class ControladorAusencias {
    public ControladorAusencias(){}

    @FXML
    private Button btnAgregarCrear;
    @FXML
    private Button btnAgregarModificar;
    @FXML
    private Button btnBuscarAusencia;
    @FXML
    private Button btnBuscarAusenciaEmpleado;
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
    private TableColumn<LeerEmpleado, String> colApellido;
    @FXML
    private TableColumn<LeerEmpleado, Integer> colLegajoEmpleadoCrear;
    @FXML
    private TableColumn<LeerEmpleado, String> colApellidoEmpleadoCrear;
    @FXML
    private TableColumn<LeerAusencia, String> colCertificado;
    @FXML
    private TableColumn<LeerEmpleado, Integer> colDNIEmpleadoCrear;
    @FXML
    private TableColumn<LeerAusencia, String> colFecha;
    @FXML
    private TableColumn<LeerAusencia, Integer> colIDAusencia;
    @FXML
    private TableColumn<LeerEmpleado, Integer> colIDEmpleado;
    @FXML
    private TableColumn<LeerEmpleado, Integer> colIDEmpleadoCrear;
    @FXML
    private TableColumn<LeerAusencia, String> colJustificado;
    @FXML
    private TableColumn<LeerEmpleado, Integer> colLegajo;
    @FXML
    private TableColumn<LeerAusencia, String> colMotivo;
    @FXML
    private TableColumn<LeerEmpleado, String> colNombre;
    @FXML
    private TableColumn<LeerEmpleado, String> colNombreEmpleadoCrear;
    @FXML
    private TableColumn<LeerEmpleado, String> colTelefonoEmpleadoCrear;
    @FXML
    private TableColumn<LeerEmpleado, Integer> colDNI;
    @FXML
    private DatePicker dpBuscarFecha;
    @FXML
    private DatePicker dpFechaCrear;
    @FXML
    private DatePicker dpFechaEliminar;
    @FXML
    private DatePicker dpFechaModificar;
    @FXML
    private DatePicker dpFechaModificarDuplicada;
    @FXML
    private Label labApellidoEmpleadoCrear;
    @FXML
    private Label labApellidoEmpleadoEliminar;
    @FXML
    private Label labApellidoEmpleadoModificar;
    @FXML
    private Label labCertificadoCrear;
    @FXML
    private Label labCertificadoEliminar;
    @FXML
    private Label labCertificadoModificar;
    @FXML
    private Label labIDAusenciaEliminar;
    @FXML
    private Label labIDAusenciaModificar;
    @FXML
    private Label labIDEmpleadoEliminar;
    @FXML
    private Label labIDEmpleadoLista;
    @FXML
    private Label labIDEmpleadoCrear;
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
    private Label labJustificacionCrear;
    @FXML
    private Label labJustificacionModificar;
    @FXML
    private Label labSeleccionCertificado;
    @FXML
    private Label labSeleccionJustificacion;
    @FXML
    private Label labIDAusenciaLista;
    @FXML
    private Label labResultadoID;
    @FXML
    private Label labErrorMotivoCrear;
    @FXML
    private Label labErrorMotivoModificar;
    @FXML
    private TabPane panelPestaniasAusencias;
    @FXML
    private RadioButton rbCertificadoNoCrear;
    @FXML
    private RadioButton rbCertificadoNoModificar;
    @FXML
    private RadioButton rbCertificadoSiCrear;
    @FXML
    private RadioButton rbCertificadoSiModificar;
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
    private TableView<LeerEmpleado> tabEmpleadosCrear;
    @FXML
    private Tab tabListaAusencias;
    @FXML
    private Tab tabModificarAusencia;
    @FXML
    private Tab tabRegistrarAusencia;
    @FXML
    private Tab tabConsultaAsistencia;
    @FXML
    private Tab tabConsultaSalida;
    @FXML
    private Tab tabRegistroEntrada;
    @FXML
    private Tab tabRegistroSalida;
    @FXML
    private TableView<LeerEmpleado> tablaListaEmpleados;
    @FXML
    private TableView<LeerAusencia> tablaAusencias;
    @FXML
    private ToggleGroup justificadoCrear;
    @FXML
    private ToggleGroup justificadoModificar;
    @FXML
    private ToggleGroup certificadoCrear;
    @FXML
    private ToggleGroup certificadoModificar;
    @FXML
    private TextField textBuscarLegajoEmpleado;
    @FXML
    private TextArea textMotivoCrear;
    @FXML
    private TextArea textMotivoEliminar;
    @FXML
    private TextArea textMotivoModificar;
    @FXML
    private TableView<ConsultaAsistencia> tablaCronogramaAsistencia;
    @FXML
    private TableView<ConsultaAsistencia> tablaListaEmpleadosPresentes;
    @FXML
    private TableView<VerificarSalida> tablaCronogramaSalida;
    @FXML
    private TableView<VerificarSalida> tablaListaEmpleadosSalida;
    @FXML
    private DatePicker dpBuscarFechaAsistencia;
    @FXML
    private DatePicker dpBuscarFechaSalida;
    @FXML
    private ComboBox<String> cbTurnoAsistencia;
    @FXML
    private ComboBox<String> cbBuscarEmpleadoLista;
    @FXML
    private ComboBox<String> cbBuscarEmpleado;
    @FXML
    private ComboBox<String> cbBuscarEmpleadoEntrada;
    @FXML
    private ComboBox<String> cbBuscarEmpleadoSalida;
    @FXML
    private ComboBox<String> cbTurnoSalida;
    @FXML
    private TextField textBuscarLegajoEmpleadoAsistencia;
    @FXML
    private TextField textBuscarLegajoEmpleadoSalida;
    @FXML
    private Button btnConsultaAsistenciaC;
    @FXML
    private Button btnConsultaSalida;
    @FXML
    private Button btnBuscarEmpleadoAsistencia;
    @FXML
    private Button btnBuscarEmpleadoSalida;
    @FXML
    private Label labCantidadCronograma;
    @FXML
    private Label labCantidadPresentes;
    @FXML
    private Label labCantidadCronogramaS;
    @FXML
    private Label labCantidadS;
    @FXML
    private Button btnRegresarCCLista;
    @FXML
    private Button btnVerificarSalida;
    @FXML
    private Button btnRegistrarEntrada;
    @FXML
    private Button btnRegistrarAusenciaA;
    @FXML
    private Button btnRegresarAsistenciaS;
    @FXML
    private Button btnRegistrarSalida;
    @FXML
    private Button btnRegresarAsistenciaEntrada;
    @FXML
    private Button btnMarcarEntrada;
    @FXML
    private Button btnRegresarAsistenciaSalida;
    @FXML
    private Button btnMarcarSalida;
    @FXML
    private TableColumn<ConsultaAsistencia, String> colNombreAsistenciaCE;
    @FXML
    private TableColumn<ConsultaAsistencia, String> colApellidoAsistenciaCE;
    @FXML
    private TableColumn<ConsultaAsistencia, Integer> colLegajoAsistenciaCE;
    @FXML
    private TableColumn<ConsultaAsistencia, String> colTurnoAsistenciaCE;
    @FXML
    private TableColumn<ConsultaAsistencia, String> colFechaAsistenciaCE;
    @FXML
    private TableColumn<ConsultaAsistencia, String> colHoraEntradaAsistenciaCE;
    @FXML
    private TableColumn<ConsultaAsistencia, String> colHoraSalidaAsistenciaCE;
    @FXML
    private TableColumn<ConsultaAsistencia, Integer> colIDCronogramaAsistenciaCE;
    @FXML
    private TableColumn<ConsultaAsistencia, String> colNombreEntrada;
    @FXML
    private TableColumn<ConsultaAsistencia, String> colApellidoEntrada;
    @FXML
    private TableColumn<ConsultaAsistencia, Integer> colLegajoEntrada;
    @FXML
    private TableColumn<ConsultaAsistencia, String> colFechaEntrada;
    @FXML
    private TableColumn<ConsultaAsistencia, String> colHoraEntrada;
    @FXML
    private TableColumn<ConsultaAsistencia, Integer> colIDEmpleadoEntrada;
    @FXML
    private TableColumn<VerificarSalida, String> colNombreAsistenciaCS;
    @FXML
    private TableColumn<VerificarSalida, String> colApellidoAsistenciaCS;
    @FXML
    private TableColumn<VerificarSalida, Integer> colLegajoAsistenciaCS;
    @FXML
    private TableColumn<VerificarSalida, String> colTurnoAsistenciaCS;
    @FXML
    private TableColumn<VerificarSalida, String> colFechaAsistenciaCS;
    @FXML
    private TableColumn<VerificarSalida, String> colHoraEntradaAsistenciaCS;
    @FXML
    private TableColumn<VerificarSalida, String> colHoraSalidaAsistenciaCS;
    @FXML
    private TableColumn<VerificarSalida, Integer> colIDCronogramaAsistenciaCS;
    @FXML
    private TableColumn<VerificarSalida, String> colNombreSalida;
    @FXML
    private TableColumn<VerificarSalida, String> colApellidoSalida;
    @FXML
    private TableColumn<VerificarSalida, Integer> colLegajoSalida;
    @FXML
    private TableColumn<VerificarSalida, String> colFechaSalida;
    @FXML
    private TableColumn<VerificarSalida, String> colHoraSalida;
    @FXML
    private TableColumn<VerificarSalida, Integer> colIDEmpleadoSalida;
    @FXML
    private TableColumn<ConsultaAsistencia, Integer> colIDEmpleadoCronogramaA;
    @FXML
    private TableColumn<VerificarSalida, Integer> colIDEmpleadoCronogramaS;
    @FXML
    private Label labNombreEmpleadoEntrada;
    @FXML
    private Label labApellidoEmpleadoEntrada;
    @FXML
    private Label labLegajoEmpleadoEntrada;
    @FXML
    private Label labNombreEmpleadoSalida;
    @FXML
    private Label labApellidoEmpleadoSalida;
    @FXML
    private Label labLegajoEmpleadoSalida;
    @FXML
    private Label labErrorHoraEntrada;
    @FXML
    private Label labErrorHoraEntrada2;
    @FXML
    private Label labErrorHoraSalida;
    @FXML
    private TextField textHoraEntrada;
    @FXML
    private TextField textHoraSalida;
    @FXML
    private Label labFechaEntrada;
    @FXML
    private Label labTurnoEntrada;
    @FXML
    private Label labIDEmpleadoEntradaA;
    @FXML
    private Label labExisteEntrada;
    @FXML
    private Label labFechaSalida;
    @FXML
    private Label labTurnoSalida;
    @FXML
    private Label labIDEmpleadoSalidaS;
    @FXML
    private Label labExisteSalida;
    @FXML
    private Label labErrorHoraSalida2;
    @FXML
    private TextField textBuscarLegajoEmpleadoCrear;

    ObservableList<LeerEmpleado> listTablaEmpleados;
    int index = -1;

    ObservableList<LeerAusencia> listAusencia;
    int index2 = -1;

    ObservableList<LeerEmpleado> listEmpleadoAusencia;
    int index3 = -1;

    String regresarConsultaAsistenia = "0";
    String asistencia = "NO";
    String salida = "NO";
    ObservableList<ConsultaAsistencia> listCronogramaCE;
    int index4 = -1;
    ObservableList<VerificarSalida> listCronogramaCS;
    int index5 = -1;
    ObservableList<ConsultaAsistencia> listEntrada;
    ObservableList<VerificarSalida> listSalida;

    // -------------------------------------------- Inicialización ----------------------------------------------
    public void initialize() {
        String[] turnos = {"Noche", "Dia"};
        cbTurnoAsistencia.getItems().addAll(turnos);
        cbTurnoAsistencia.getSelectionModel().selectFirst();
        cbTurnoSalida.getItems().addAll(turnos);
        cbTurnoSalida.getSelectionModel().selectFirst();
        String[] tipoBuscar = {"Legajo", "Nombre", "Apellido"};
        cbBuscarEmpleadoLista.getItems().addAll(tipoBuscar);
        cbBuscarEmpleado.getItems().addAll(tipoBuscar);
        cbBuscarEmpleadoEntrada.getItems().addAll(tipoBuscar);
        cbBuscarEmpleadoSalida.getItems().addAll(tipoBuscar);
        cbBuscarEmpleadoLista.getSelectionModel().selectFirst();
        cbBuscarEmpleado.getSelectionModel().selectFirst();
        cbBuscarEmpleadoEntrada.getSelectionModel().selectFirst();
        cbBuscarEmpleadoSalida.getSelectionModel().selectFirst();
        inicializarTablaListaEmpleados();
        inicializarTablaEmpleado();
        getJustificadoCrear();
        getJustificadoModificar();
        getCertificadoCrear();
        getCertificadoModificar();
        fechasInicializar();
        fechasInicializarConsultas();

        cbBuscarEmpleadoLista.setOnAction(event -> {
            String selectedItem = cbBuscarEmpleadoLista.getSelectionModel().getSelectedItem();
            textBuscarLegajoEmpleado.setPromptText("Ingrese el " + selectedItem);
        });

        cbBuscarEmpleado.setOnAction(event -> {
            String selectedItem = cbBuscarEmpleado.getSelectionModel().getSelectedItem();
            textBuscarLegajoEmpleadoCrear.setPromptText("Ingrese el " + selectedItem);
        });

        cbBuscarEmpleadoEntrada.setOnAction(event -> {
            String selectedItem = cbBuscarEmpleadoEntrada.getSelectionModel().getSelectedItem();
            textBuscarLegajoEmpleadoAsistencia.setPromptText("Ingrese el " + selectedItem);
        });

        cbBuscarEmpleadoSalida.setOnAction(event -> {
            String selectedItem = cbBuscarEmpleadoSalida.getSelectionModel().getSelectedItem();
            textBuscarLegajoEmpleadoSalida.setPromptText("Ingrese el " + selectedItem);
        });
    }

    // ----------------------------------------- Tabla de Empleados ---------------------------------------------
    public void inicializarTablaListaEmpleados(){
        colNombre.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, String>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, String>("apellido"));
        colLegajo.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, Integer>("legajo"));
        colDNI.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, Integer>("dni"));
        colIDEmpleado.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, Integer>("idempleados"));

        listTablaEmpleados = LeerEmpleado.listaEmpleadoGeneral();
        tablaListaEmpleados.getColumns().setAll(colNombre, colApellido, colLegajo, colDNI, colIDEmpleado);
        tablaListaEmpleados.getItems().setAll(listTablaEmpleados);
    }

    // ----------------------------------------- Tabla de Ausencias ---------------------------------------------
    public void inicializarTablaAusencias(){
        colFecha.setCellValueFactory(new PropertyValueFactory<LeerAusencia, String>("fecha"));
        colMotivo.setCellValueFactory(new PropertyValueFactory<LeerAusencia, String>("motivo"));
        colJustificado.setCellValueFactory(new PropertyValueFactory<LeerAusencia, String>("justificado"));
        colCertificado.setCellValueFactory(new PropertyValueFactory<LeerAusencia, String>("certificado"));
        colIDAusencia.setCellValueFactory(new PropertyValueFactory<LeerAusencia, Integer>("idausencias"));

        listAusencia = LeerAusencia.listaAusencia(labIDEmpleadoLista);
        tablaAusencias.getColumns().setAll(colFecha, colMotivo, colJustificado, colCertificado, colIDAusencia);
        tablaAusencias.getItems().setAll(listAusencia);

    }

    // ------------------------------------ Tabla de Empleados Crear ---------------------------------------------
    public void inicializarTablaEmpleado(){
        colNombreEmpleadoCrear.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, String>("nombre"));
        colApellidoEmpleadoCrear.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, String>("apellido"));
        colLegajoEmpleadoCrear.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, Integer>("legajo"));
        colDNIEmpleadoCrear.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, Integer>("dni"));
        colTelefonoEmpleadoCrear.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, String>("telefono"));
        colIDEmpleadoCrear.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, Integer>("idempleados"));

        listEmpleadoAusencia = LeerEmpleado.listaEmpleadoGeneral();
        tabEmpleadosCrear.getColumns().setAll(colNombreEmpleadoCrear, colApellidoEmpleadoCrear, colLegajoEmpleadoCrear, colDNIEmpleadoCrear, colTelefonoEmpleadoCrear, colIDEmpleadoCrear);
        tabEmpleadosCrear.getItems().setAll(listEmpleadoAusencia);
    }

    // ------------------------------------- Tomo todos los datos al Seleccionar en la tabla ------------------------
    @FXML
    private void getListaEmpleados(){
        index = tablaListaEmpleados.getSelectionModel().getSelectedIndex();
        if (index <= -1){

            return;
        }
        labNombreEmpleadoModificar.setText(colNombre.getCellData(index));
        labApellidoEmpleadoModificar.setText(colApellido.getCellData(index));
        labLegajoEmpleadoModificar.setText(colLegajo.getCellData(index).toString());
        labIDEmpleadoModificar.setText(colIDEmpleado.getCellData(index).toString());

        labNombreEmpleadoEliminar.setText(colNombre.getCellData(index));
        labApellidoEmpleadoEliminar.setText(colApellido.getCellData(index));
        labLegajoEmpleadoEliminar.setText(colLegajo.getCellData(index).toString());
        labIDEmpleadoEliminar.setText(colIDEmpleado.getCellData(index).toString());

        labIDAusenciaLista.setText("0");
        labIDEmpleadoLista.setText(colIDEmpleado.getCellData(index).toString());
        inicializarTablaAusencias();

        LeerAusencia ausenciaLeer = new LeerAusencia();
        ausenciaLeer.listaAusenciaComprobacion(labIDEmpleadoLista, labIDAusenciaLista, labResultadoID);
        if(labResultadoID.getText().equals("NO")){
            limpiarCamposAusencias();
        }
    }

    @FXML
    private void getAusencia(){
        index2 = tablaAusencias.getSelectionModel().getSelectedIndex();
        if (index2 <= -1){

            return;
        }

        dpFechaModificar.getEditor().setText(colFecha.getCellData(index2));
        dpFechaModificarDuplicada.getEditor().setText(colFecha.getCellData(index2));
        textMotivoModificar.setText(colMotivo.getCellData(index2));
        labSeleccionJustificacion.setText(colJustificado.getCellData(index2));
        labSeleccionCertificado.setText(colCertificado.getCellData(index2));
        labIDAusenciaModificar.setText(colIDAusencia.getCellData(index2).toString());

        dpFechaEliminar.getEditor().setText(colFecha.getCellData(index2));
        textMotivoEliminar.setText(colMotivo.getCellData(index2));
        labJustificadoEliminar.setText(colJustificado.getCellData(index2));
        labCertificadoEliminar.setText(colCertificado.getCellData(index2));
        labIDAusenciaEliminar.setText(colIDAusencia.getCellData(index2).toString());
        comprobacionRadioBotomSeleccion();
        comprobacionRadioBotomSeleccionC();

        labIDAusenciaLista.setText(colIDAusencia.getCellData(index2).toString());

        String fecha = colFecha.getCellData(index2);
        dpFechaModificar.setValue(LocalDate.of(Integer.parseInt(fecha.substring(6, 10)), Integer.parseInt(fecha.substring(3, 5)), Integer.parseInt(fecha.substring(0, 2))));
    }

    @FXML
    private void getEmpleadoCrear(){
        index3 = tabEmpleadosCrear.getSelectionModel().getSelectedIndex();
        if (index3 <= -1){

            return;
        }
        labIDEmpleadoCrear.setText(colIDEmpleadoCrear.getCellData(index3).toString());
        labNombreEmpleadoCrear.setText(colNombreEmpleadoCrear.getCellData(index3));
        labApellidoEmpleadoCrear.setText(colApellidoEmpleadoCrear.getCellData(index3));
        labLegajoEmpleadoCrear.setText(colLegajoEmpleadoCrear.getCellData(index3).toString());
    }

    @FXML
    private void getCronogramaAsistencia(){
        asistencia = "YES";
        index4 = tablaCronogramaAsistencia.getSelectionModel().getSelectedIndex();
        if (index4 <= -1){

            return;
        }
        // Registrar Ausencia
        labIDEmpleadoCrear.setText(colIDEmpleadoCronogramaA.getCellData(index4).toString());
        labNombreEmpleadoCrear.setText(colNombreAsistenciaCE.getCellData(index4));
        labApellidoEmpleadoCrear.setText(colApellidoAsistenciaCE.getCellData(index4));
        labLegajoEmpleadoCrear.setText(colLegajoAsistenciaCE.getCellData(index4).toString());

        // Registrar Entrada
        labIDEmpleadoEntradaA.setText(colIDEmpleadoCronogramaA.getCellData(index4).toString());
        labNombreEmpleadoEntrada.setText(colNombreAsistenciaCE.getCellData(index4));
        labApellidoEmpleadoEntrada.setText(colApellidoAsistenciaCE.getCellData(index4));
        labLegajoEmpleadoEntrada.setText(colLegajoAsistenciaCE.getCellData(index4).toString());
        labFechaEntrada.setText(colFechaAsistenciaCE.getCellData(index4));
        labTurnoEntrada.setText(colTurnoAsistenciaCE.getCellData(index4));
        textHoraEntrada.setText(colHoraEntradaAsistenciaCE.getCellData(index4));
    }

    @FXML
    private void getCronogramaSalida(){
        salida = "YES";
        index5 = tablaCronogramaSalida.getSelectionModel().getSelectedIndex();
        if (index5 <= -1){

            return;
        }

        // Registrar Salida
        labIDEmpleadoSalidaS.setText(colIDEmpleadoCronogramaS.getCellData(index5).toString());
        labNombreEmpleadoSalida.setText(colNombreAsistenciaCS.getCellData(index5));
        labApellidoEmpleadoSalida.setText(colApellidoAsistenciaCS.getCellData(index5));
        labLegajoEmpleadoSalida.setText(colLegajoAsistenciaCS.getCellData(index5).toString());
        labFechaSalida.setText(colFechaAsistenciaCS.getCellData(index5));
        labTurnoSalida.setText(colTurnoAsistenciaCS.getCellData(index5));
        textHoraSalida.setText(colHoraSalidaAsistenciaCS.getCellData(index5));
    }

    // --------------------------------------------- Búsqueda y Filtros -----------------------------------------
    @FXML
    private void btnBuscarEmpleado() {
        limpiarCamposGeneralizada();
        ObservableList<LeerEmpleado> listBuscarEmpleado;
        listBuscarEmpleado = LeerEmpleado.buscarEmpleadoGeneral(textBuscarLegajoEmpleado, cbBuscarEmpleadoLista);
        if(textBuscarLegajoEmpleado.getText().equals("")){
            String dato = cbBuscarEmpleadoLista.getSelectionModel().getSelectedItem();
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Error!");
            alerta.setContentText("Debe de completar el " + dato +" para Buscar");
            alerta.showAndWait();
        } else {
            tablaListaEmpleados.getItems().setAll(listBuscarEmpleado);
            labIDEmpleadoLista.setText("0");
            inicializarTablaAusencias();
        }
    }

    @FXML
    private void btnBuscarAusencia(){
        labIDAusenciaModificar.setText("");
        labIDAusenciaEliminar.setText("");
        if(!labIDEmpleadoLista.getText().equals("")){
            ObservableList<LeerAusencia> listaBuscarAusencia;
            listaBuscarAusencia = LeerAusencia.buscarAusenciaFecha(labIDEmpleadoLista, dpBuscarFecha);
            tablaAusencias.getItems().setAll(listaBuscarAusencia);
        }
    }

    @FXML
    private void btnBuscarEmpleadoCrear(){
        ObservableList<LeerEmpleado> listBuscarEmpleadoCrear;
        listBuscarEmpleadoCrear = LeerEmpleado.buscarEmpleadoGeneralCrear(textBuscarLegajoEmpleadoCrear, cbBuscarEmpleado);
        tabEmpleadosCrear.getItems().setAll(listBuscarEmpleadoCrear);
    }

    @FXML
    private void btnBuscarEmpleadoAsistencia() throws ParseException {
        limpiarCamposEntradaAusencia();
        limpiarCamposEntrada();
        asistencia = "NO";
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        LocalTime horaActual = LocalTime.now();
        int hora = horaActual.getHour();
        int minuto = horaActual.getMinute();

        String fechaInicioClave = dpBuscarFechaAsistencia.getEditor().getText();
        String fechaAnio = fechaInicioClave.substring(6, 10);
        String fechaMes = fechaInicioClave.substring(3, 5);
        String fechaDia = fechaInicioClave.substring(0, 2);
        String fechaModificadaBuscar = (fechaAnio + "-" + fechaMes + "-" + fechaDia);

        Date fechaBuscar = formatoFecha.parse(fechaModificadaBuscar);
        Date fechaActualPais = new Date();
        String Fecha_Actual = (formatoFecha.format(fechaActualPais));
        Date fechaActual = formatoFecha.parse(Fecha_Actual);
        long Diferencias = fechaActual.getTime() - fechaBuscar.getTime();
        long Cant_Dias = Diferencias / (1000 * 60 * 60 * 24);

        if((-Cant_Dias) < 0){
            tablasAsistenciasBuscar();
        } else if((-Cant_Dias) == 0){
            if(Objects.equals(cbTurnoAsistencia.getSelectionModel().getSelectedItem(), "Dia")){
                LocalTime horaInicio = LocalTime.of(12, 10);
                LocalTime horaFin = LocalTime.of(23, 59);
                if (horaActual.isAfter(horaInicio) && horaActual.isBefore(horaFin)) { // Despues y Antes
                    tablasAsistenciasBuscar();
                } else if(horaActual.isBefore(horaInicio) && horaActual.isBefore(horaFin)){
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("Error de Consulta");
                    alerta.setContentText("La Fecha y Turno asignado no esta permitido según la (Fecha y Hora Actual)");
                    alerta.showAndWait();
                }

            } else if(Objects.equals(cbTurnoAsistencia.getSelectionModel().getSelectedItem(), "Noche")){
                LocalTime horaInicio = LocalTime.of(0, 10);
                LocalTime horaFin = LocalTime.of(12, 0);
                if (horaActual.isAfter(horaInicio) && horaActual.isBefore(horaFin)) { // Despues y Antes
                    tablasAsistenciasBuscar();
                } else if(horaActual.isAfter(horaInicio) && horaActual.isAfter(horaFin)){
                    tablasAsistenciasBuscar();
                } else {
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("Error de Consulta");
                    alerta.setContentText("La Fecha y Turno asignado no esta permitido según la (Fecha y Hora Actual)");
                    alerta.showAndWait();
                }
            }
        } else {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error de Consulta");
            alerta.setContentText("La Fecha y Turno asignado no esta permitido según la (Fecha y Hora Actual)");
            alerta.showAndWait();
        }
    }

    @FXML
    private void btnBuscarEmpleadoSalida() throws ParseException {
        limpiarCamposSalida();
        salida = "NO";
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        LocalTime horaActual = LocalTime.now();
        int hora = horaActual.getHour();
        int minuto = horaActual.getMinute();

        String fechaInicioClave = dpBuscarFechaSalida.getEditor().getText();
        String fechaAnio = fechaInicioClave.substring(6, 10);
        String fechaMes = fechaInicioClave.substring(3, 5);
        String fechaDia = fechaInicioClave.substring(0, 2);
        String fechaModificadaBuscar = (fechaAnio + "-" + fechaMes + "-" + fechaDia);

        Date fechaBuscar = formatoFecha.parse(fechaModificadaBuscar);
        Date fechaActualPais = new Date();
        String Fecha_Actual = (formatoFecha.format(fechaActualPais));
        Date fechaActual = formatoFecha.parse(Fecha_Actual);
        long Diferencias = fechaActual.getTime() - fechaBuscar.getTime();
        long Cant_Dias = Diferencias / (1000 * 60 * 60 * 24);

        if((-Cant_Dias) < 0){
            tablasSalidaBuscar();
        } else if((-Cant_Dias) == 0){
            if(Objects.equals(cbTurnoSalida.getSelectionModel().getSelectedItem(), "Dia")){
                LocalTime horaInicio = LocalTime.of(12, 10);
                LocalTime horaFin = LocalTime.of(23, 59);
                if (horaActual.isAfter(horaInicio) && horaActual.isBefore(horaFin)) { // Despues y Antes
                    tablasSalidaBuscar();
                } else if(horaActual.isBefore(horaInicio) && horaActual.isBefore(horaFin)){
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("Error de Consulta");
                    alerta.setContentText("La Fecha y Turno asignado no esta permitido según la (Fecha y Hora Actual)");
                    alerta.showAndWait();
                }

            } else if(Objects.equals(cbTurnoSalida.getSelectionModel().getSelectedItem(), "Noche")){
                LocalTime horaInicio = LocalTime.of(0, 10);
                LocalTime horaFin = LocalTime.of(12, 0);
                if (horaActual.isAfter(horaInicio) && horaActual.isBefore(horaFin)) { // Despues y Antes
                    tablasSalidaBuscar();
                } else if(horaActual.isAfter(horaInicio) && horaActual.isAfter(horaFin)){
                    tablasSalidaBuscar();
                } else {
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("Error de Consulta");
                    alerta.setContentText("La Fecha y Turno asignado no esta permitido según la (Fecha y Hora Actual)");
                    alerta.showAndWait();
                }
            }
        } else {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error de Consulta");
            alerta.setContentText("La Fecha y Turno asignado no esta permitido según la (Fecha y Hora Actual)");
            alerta.showAndWait();
        }
    }

    @FXML
    private void actualizarTabla(){
        labIDAusenciaLista.setText("0");
        labIDEmpleadoLista.setText("0");
        textBuscarLegajoEmpleado.setText("");
        tablaListaEmpleados.getItems().setAll(listEmpleadoAusencia);
        inicializarTablaListaEmpleados();
        inicializarTablaAusencias();
        limpiarCamposModificar();
        limpiarCamposEliminar();
    }

    //---------------------------------------------- Eventos Importantes ----------------------------------------
    @FXML
    private void guardar() {
        if(labIDEmpleadoCrear.getText().trim().isEmpty() || labIDEmpleadoCrear.getText() == null){
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Advertencia!");
            alerta.setContentText("Para Guardar debes de tomar un Empleado antes");
            alerta.showAndWait();
        } else {
            camposObligatoriosCrear();
            if(Objects.equals(labErrorMotivoCrear.getText(), "")){
                validacionCamposCaracteresCrear();
                if(Objects.equals(labErrorMotivoCrear.getText(), "")){
                    validacionCamposLongitudCrear();
                    if(Objects.equals(labErrorMotivoCrear.getText(), "")){
                        CrearAusencia ausenciaCrear = new CrearAusencia();
                        ausenciaCrear.agregarAusencia(labIDEmpleadoCrear, dpFechaCrear, textMotivoCrear, labJustificacionCrear, labCertificadoCrear, labLimpiarCamposCrear);

                        if(Objects.equals(labLimpiarCamposCrear.getText(), "OK")){
                            labLimpiarCamposCrear.setText("");
                            labIDAusenciaLista.setText("0");
                            labIDEmpleadoLista.setText("0");
                            inicializarTablaListaEmpleados();
                            inicializarTablaAusencias();
                            regresarCLista();
                            limpiarCamposCrear();
                            inicializarTablaEmpleado();
                        }
                    }
                }
            }
        }
    }

    @FXML
    private void modificar() {
        if(labIDAusenciaModificar.getText().trim().isEmpty() || labIDAusenciaModificar.getText() == null){
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Advertencia!");
            alerta.setContentText("Debe de seleccionar antes una Ausencia de dicho empleado para Modificarlo");
            alerta.showAndWait();
        } else {
            camposObligatoriosModificar();
            if(Objects.equals(labErrorMotivoModificar.getText(), "")){
                validacionCamposCaracteresModificar();
                if(Objects.equals(labErrorMotivoModificar.getText(), "")){
                    validacionCamposLongitudModificar();
                    if(Objects.equals(labErrorMotivoModificar.getText(), "")){
                        ModificarAusencia ausenciaModificar = new ModificarAusencia();
                        ausenciaModificar.modificarAusencia(labIDEmpleadoModificar, labIDAusenciaModificar, dpFechaModificar, dpFechaModificarDuplicada, textMotivoModificar, labJustificacionModificar, labCertificadoModificar, labLimpiarCamposModificar);

                        if(Objects.equals(labLimpiarCamposModificar.getText(), "OK")){
                            labLimpiarCamposModificar.setText("");
                            dpFechaModificarDuplicada.getEditor().setText("");
                            labIDAusenciaLista.setText("0");
                            labIDEmpleadoLista.setText("0");
                            inicializarTablaListaEmpleados();
                            inicializarTablaAusencias();
                            limpiarCamposModificar();
                            limpiarCamposEliminar();
                            regresarCLista();
                        }
                    }
                }
            }
        }
    }

    @FXML
    private void eliminar() {
        if (labIDAusenciaEliminar.getText().trim().isEmpty() || labIDAusenciaEliminar.getText() == null){
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Error!");
            alerta.setContentText("Debe de seleccionar antes una Ausencia de dicho empleado para Eliminarlo");
            alerta.showAndWait();
            btnRegresarELista.requestFocus();
        } else{
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle("Confirmar Eliminar");
            alerta.setContentText("¿Desea Eliminar la Ausencia del Empleado?");
            Optional<ButtonType> resultado = alerta.showAndWait();
            if (resultado.isPresent() && resultado.get() == ButtonType.OK){

                EliminarAusencia ausenciaEliminar = new EliminarAusencia();
                ausenciaEliminar.eliminarAusencia(labIDAusenciaEliminar);
                labIDAusenciaLista.setText("0");
                labIDEmpleadoLista.setText("0");
                inicializarTablaListaEmpleados();
                inicializarTablaAusencias();
                limpiarCamposEliminar();
                limpiarCamposModificar();
                regresarELista();
            }
        }
    }

    @FXML
    private void consultaAsistenciaGeneral() throws ParseException {
        textBuscarLegajoEmpleadoAsistencia.setText("");
        limpiarCamposEntradaAusencia();
        limpiarCamposEntrada();
        asistencia = "NO";
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        LocalTime horaActual = LocalTime.now();

        String fechaInicioClave = dpBuscarFechaAsistencia.getEditor().getText();
        String fechaAnio = fechaInicioClave.substring(6, 10);
        String fechaMes = fechaInicioClave.substring(3, 5);
        String fechaDia = fechaInicioClave.substring(0, 2);
        String fechaModificadaBuscar = (fechaAnio + "-" + fechaMes + "-" + fechaDia);

        Date fechaBuscar = formatoFecha.parse(fechaModificadaBuscar);
        Date fechaActualPais = new Date();
        String Fecha_Actual = (formatoFecha.format(fechaActualPais));
        Date fechaActual = formatoFecha.parse(Fecha_Actual);
        long Diferencias = fechaActual.getTime() - fechaBuscar.getTime();
        long Cant_Dias = Diferencias / (1000 * 60 * 60 * 24);

        if((-Cant_Dias) < 0){
            tablasAsistencias();
        } else if((-Cant_Dias) == 0){
            if(Objects.equals(cbTurnoAsistencia.getSelectionModel().getSelectedItem(), "Dia")){
                LocalTime horaInicio = LocalTime.of(12, 10);
                LocalTime horaFin = LocalTime.of(23, 59);
                if (horaActual.isAfter(horaInicio) && horaActual.isBefore(horaFin)) { // Despues y Antes
                    tablasAsistencias();
                } else if(horaActual.isBefore(horaInicio) && horaActual.isBefore(horaFin)){
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("Error de Consulta");
                    alerta.setContentText("La Fecha y Turno asignado no esta permitido según la (Fecha y Hora Actual)");
                    alerta.showAndWait();
                }

            } else if(Objects.equals(cbTurnoAsistencia.getSelectionModel().getSelectedItem(), "Noche")){
                LocalTime horaInicio = LocalTime.of(0, 10);
                LocalTime horaFin = LocalTime.of(12, 0);
                if (horaActual.isAfter(horaInicio) && horaActual.isBefore(horaFin)) { // Despues y Antes
                    tablasAsistencias();
                } else if(horaActual.isAfter(horaInicio) && horaActual.isAfter(horaFin)){
                    tablasAsistencias();
                } else {
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("Error de Consulta");
                    alerta.setContentText("La Fecha y Turno asignado no esta permitido según la (Fecha y Hora Actual)");
                    alerta.showAndWait();
                }
            }
        } else {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error de Consulta");
            alerta.setContentText("La Fecha y Turno asignado no esta permitido según la (Fecha y Hora Actual)");
            alerta.showAndWait();
        }
    }

    @FXML
    private void consultaSalidaGeneral() throws ParseException {
        textBuscarLegajoEmpleadoSalida.setText("");
        limpiarCamposSalida();
        salida = "NO";
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        LocalTime horaActual = LocalTime.now();

        String fechaInicioClave = dpBuscarFechaSalida.getEditor().getText();
        String fechaAnio = fechaInicioClave.substring(6, 10);
        String fechaMes = fechaInicioClave.substring(3, 5);
        String fechaDia = fechaInicioClave.substring(0, 2);
        String fechaModificadaBuscar = (fechaAnio + "-" + fechaMes + "-" + fechaDia);

        Date fechaBuscar = formatoFecha.parse(fechaModificadaBuscar);
        Date fechaActualPais = new Date();
        String Fecha_Actual = (formatoFecha.format(fechaActualPais));
        Date fechaActual = formatoFecha.parse(Fecha_Actual);
        long Diferencias = fechaActual.getTime() - fechaBuscar.getTime();
        long Cant_Dias = Diferencias / (1000 * 60 * 60 * 24);

        if((-Cant_Dias) < 0){
            tablasSalidas();
        } else if((-Cant_Dias) == 0){
            if(Objects.equals(cbTurnoSalida.getSelectionModel().getSelectedItem(), "Dia")){
                LocalTime horaInicio = LocalTime.of(12, 10);
                LocalTime horaFin = LocalTime.of(23, 59);
                if (horaActual.isAfter(horaInicio) && horaActual.isBefore(horaFin)) { // Despues y Antes
                    tablasSalidas();
                } else if(horaActual.isBefore(horaInicio) && horaActual.isBefore(horaFin)){
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("Error de Consulta");
                    alerta.setContentText("La Fecha y Turno asignado no esta permitido según la (Fecha y Hora Actual)");
                    alerta.showAndWait();
                }

            } else if(Objects.equals(cbTurnoSalida.getSelectionModel().getSelectedItem(), "Noche")){
                LocalTime horaInicio = LocalTime.of(0, 10);
                LocalTime horaFin = LocalTime.of(12, 0);
                if (horaActual.isAfter(horaInicio) && horaActual.isBefore(horaFin)) { // Despues y Antes
                    tablasSalidas();
                } else if(horaActual.isAfter(horaInicio) && horaActual.isAfter(horaFin)){
                    tablasSalidas();
                } else {
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("Error de Consulta");
                    alerta.setContentText("La Fecha y Turno asignado no esta permitido según la (Fecha y Hora Actual)");
                    alerta.showAndWait();
                }
            }
        } else {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error de Consulta");
            alerta.setContentText("La Fecha y Turno asignado no esta permitido según la (Fecha y Hora Actual)");
            alerta.showAndWait();
        }
    }

    @FXML
    private void marcarEntrada(){
        String respuesta = "NO";
        labExisteEntrada.setText("");
        if(labIDEmpleadoEntradaA.getText().trim().isEmpty() || labIDEmpleadoEntradaA.getText() == null){
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Error al Marcar la Entrada!");
            alerta.setContentText("Debe de seleccionar antes un empleado en la (Tabla del Cronograma) para marcar su entrada");
            alerta.showAndWait();
        } else {
            if (!textHoraEntrada.getText().matches("[0-9:]*") || textHoraEntrada.getText().length() > 5 | textHoraEntrada.getText().length() < 5 || textHoraEntrada.getText().charAt(2) != ':') {
                if(labTurnoEntrada.getText().equals("Dia")){
                    textHoraEntrada.setText("12:00");
                }
                if(labTurnoEntrada.getText().equals("Noche")){
                    textHoraEntrada.setText("00:00");
                }
                labErrorHoraEntrada2.setText("No está Permitido ese formato");
            } else {
                labErrorHoraEntrada.setText("");
                labErrorHoraEntrada2.setText("");
                respuesta = "YES";
            }

            if(respuesta.equals("YES")){
                LocalTime horaE = LocalTime.of(Integer.parseInt(textHoraEntrada.getText().substring(0, 2)), Integer.parseInt(textHoraEntrada.getText().substring(3, 5)));

                if(labTurnoEntrada.getText().equals("Dia")){
                    LocalTime horaInicio = LocalTime.of(12, 0);
                    LocalTime horaFin = LocalTime.of(12, 30);

                    if ((horaE.equals(horaInicio) || horaE.isAfter(horaInicio)) && ((horaE.equals(horaFin) || horaE.isBefore(horaFin)))) {
                        ConsultaAsistencia verificar = new ConsultaAsistencia();
                        verificar.verificacionEntrada(labIDEmpleadoEntradaA, labFechaEntrada, labTurnoEntrada, labExisteEntrada);
                        if(labExisteEntrada.getText().equals("")){
                            ConsultaAsistencia crear = new ConsultaAsistencia();
                            crear.crearEntrada(labIDEmpleadoEntradaA, labFechaEntrada, textHoraEntrada);
                            textBuscarLegajoEmpleadoAsistencia.setText("");
                            tablasAsistencias();
                            limpiarCamposEntrada();
                            limpiarCamposEntradaAusencia();
                            consultaAsistencia();
                            asistencia = "NO";
                        } else {
                            labExisteEntrada.setText("");
                            Alert alerta = new Alert(Alert.AlertType.ERROR);
                            alerta.setTitle("Error de Marcar Entrada");
                            alerta.setContentText("La entrada de dicho empleado ya está registrada. ");
                            alerta.showAndWait();
                        }
                    } else {
                        Alert alerta = new Alert(Alert.AlertType.ERROR);
                        alerta.setTitle("Error de Marcar Entrada");
                        alerta.setContentText("La Hora que a asignado se encuentra afuera del rango del Turno. (Permitido desde: 12:00 a 12:30 hs)");
                        alerta.showAndWait();
                        labErrorHoraEntrada.setText("X");
                    }
                } else if(labTurnoEntrada.getText().equals("Noche")){
                    LocalTime horaInicio = LocalTime.of(0, 0);
                    LocalTime horaFin = LocalTime.of(0, 30);

                    if ((horaE.equals(horaInicio) || horaE.isAfter(horaInicio)) && ((horaE.equals(horaFin) || horaE.isBefore(horaFin)))) {
                        ConsultaAsistencia verificar = new ConsultaAsistencia();
                        verificar.verificacionEntrada(labIDEmpleadoEntradaA, labFechaEntrada, labTurnoEntrada, labExisteEntrada);
                        if(labExisteEntrada.getText().equals("")){
                            ConsultaAsistencia crear = new ConsultaAsistencia();
                            crear.crearEntrada(labIDEmpleadoEntradaA, labFechaEntrada, textHoraEntrada);
                            textBuscarLegajoEmpleadoAsistencia.setText("");
                            tablasAsistencias();
                            limpiarCamposEntrada();
                            limpiarCamposEntradaAusencia();
                            consultaAsistencia();
                            asistencia = "NO";
                        } else {
                            Alert alerta = new Alert(Alert.AlertType.ERROR);
                            alerta.setTitle("Error de Marcar Entrada");
                            alerta.setContentText("La entrada de dicho empleado ya está registrada. ");
                            alerta.showAndWait();
                        }
                    } else {
                        Alert alerta = new Alert(Alert.AlertType.ERROR);
                        alerta.setTitle("Error de Marcar Entrada");
                        alerta.setContentText("La Hora que a asignado se encuentra afuera del rango del Turno. (Permitido desde: 00:00 a 00:30 hs)");
                        alerta.showAndWait();
                        labErrorHoraEntrada.setText("X");
                    }
                }
            }
        }
    }

    @FXML
    private void marcarSalida(){
        String respuesta = "NO";
        labExisteSalida.setText("");
        if(labIDEmpleadoSalidaS.getText().trim().isEmpty() || labIDEmpleadoSalidaS.getText() == null){
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Error al Marcar la Salida!");
            alerta.setContentText("Debe de seleccionar antes un empleado en la (Tabla del Cronograma) para marcar su salida");
            alerta.showAndWait();
        } else {
            if (!textHoraSalida.getText().matches("[0-9:]*") || textHoraSalida.getText().length() > 5 | textHoraSalida.getText().length() < 5 || textHoraSalida.getText().charAt(2) != ':') {
                if(labTurnoSalida.getText().equals("Dia")){
                    textHoraSalida.setText("23:59");
                }
                if(labTurnoSalida.getText().equals("Noche")){
                    textHoraSalida.setText("12:00");
                }
                labErrorHoraSalida2.setText("No está Permitido ese formato");
            } else {
                labErrorHoraSalida.setText("");
                labErrorHoraSalida2.setText("");
                respuesta = "YES";
            }

            if(respuesta.equals("YES")){
                LocalTime horaS = LocalTime.of(Integer.parseInt(textHoraSalida.getText().substring(0, 2)), Integer.parseInt(textHoraSalida.getText().substring(3, 5)));

                if(labTurnoSalida.getText().equals("Dia")){
                    LocalTime horaInicio = LocalTime.of(23, 30);
                    LocalTime horaFin = LocalTime.of(23, 59);

                    if ((horaS.equals(horaInicio) || horaS.isAfter(horaInicio)) && ((horaS.equals(horaFin) || horaS.isBefore(horaFin)))) {
                        ConsultaAsistencia contulta = new ConsultaAsistencia();
                        contulta.verificacionEntrada(labIDEmpleadoSalidaS, labFechaSalida, labTurnoSalida, labExisteEntrada);
                        if(labExisteEntrada.getText().equals("YES")){
                            VerificarSalida verificar = new VerificarSalida();
                            verificar.verificacionSalida(labIDEmpleadoSalidaS, labFechaSalida, labTurnoSalida, labExisteSalida);
                            if(labExisteSalida.getText().equals("")){
                                System.out.println(labExisteSalida.getText());
                                VerificarSalida crear = new VerificarSalida();
                                crear.crearSalida(labIDEmpleadoSalidaS, labFechaSalida, textHoraSalida);
                                textBuscarLegajoEmpleadoSalida.setText("");
                                tablasSalidas();
                                limpiarCamposSalida();
                                verificarSalida();
                                salida = "NO";
                            } else {
                                Alert alerta = new Alert(Alert.AlertType.ERROR);
                                alerta.setTitle("Error de Marcar Salida");
                                alerta.setContentText("La salida de dicho empleado ya está registrada. ");
                                alerta.showAndWait();
                            }
                        } else {
                            labExisteEntrada.setText("");
                            Alert alerta = new Alert(Alert.AlertType.ERROR);
                            alerta.setTitle("Error de Marcar Salida");
                            alerta.setContentText("La salida de dicho empleado no se puede realizar porque no a ingresado su Entrada.");
                            alerta.showAndWait();
                        }
                    } else {
                        Alert alerta = new Alert(Alert.AlertType.ERROR);
                        alerta.setTitle("Error de Marcar Salida");
                        alerta.setContentText("La Hora que a asignado se encuentra afuera del rango del Turno. (Permitido desde: 23:30 a 23:59 hs)");
                        alerta.showAndWait();
                        labErrorHoraSalida.setText("X");
                    }
                } else if(labTurnoSalida.getText().equals("Noche")){
                    LocalTime horaInicio = LocalTime.of(11, 30);
                    LocalTime horaFin = LocalTime.of(12, 0);

                    if ((horaS.equals(horaInicio) || horaS.isAfter(horaInicio)) && ((horaS.equals(horaFin) || horaS.isBefore(horaFin)))) {
                        ConsultaAsistencia contulta = new ConsultaAsistencia();
                        contulta.verificacionEntrada(labIDEmpleadoSalidaS, labFechaSalida, labTurnoSalida, labExisteEntrada);
                        if(labExisteEntrada.getText().equals("YES")){
                            VerificarSalida verificar = new VerificarSalida();
                            verificar.verificacionSalida(labIDEmpleadoSalidaS, labFechaSalida, labTurnoSalida, labExisteSalida);
                            if(labExisteSalida.getText().equals("")){
                                System.out.println(labExisteSalida.getText());
                                VerificarSalida crear = new VerificarSalida();
                                crear.crearSalida(labIDEmpleadoSalidaS, labFechaSalida, textHoraSalida);
                                textBuscarLegajoEmpleadoSalida.setText("");
                                tablasSalidas();
                                limpiarCamposSalida();
                                verificarSalida();
                                salida = "NO";
                            } else {
                                Alert alerta = new Alert(Alert.AlertType.ERROR);
                                alerta.setTitle("Error de Marcar Salida");
                                alerta.setContentText("La salida de dicho empleado ya está registrada. ");
                                alerta.showAndWait();
                            }
                        } else {
                            labExisteEntrada.setText("");
                            Alert alerta = new Alert(Alert.AlertType.ERROR);
                            alerta.setTitle("Error de Marcar Salida");
                            alerta.setContentText("La salida de dicho empleado no se puede realizar porque no a ingresado su Entrada.");
                            alerta.showAndWait();
                        }
                    } else {
                        Alert alerta = new Alert(Alert.AlertType.ERROR);
                        alerta.setTitle("Error de Marcar Salida");
                        alerta.setContentText("La Hora que a asignado se encuentra afuera del rango del Turno. (Permitido desde: 11:30 a 12:00 hs)");
                        alerta.showAndWait();
                        labErrorHoraSalida.setText("X");
                    }
                }
            }
        }
    }

    //------------------------------------ Acciones Simples de los Botones --------------------------------------
    @FXML
    public void getJustificadoCrear(){
        if(rbJustificadoNoCrear.isSelected()){
            labJustificacionCrear.setText("NO");
        } else if(rbJustificadoSiCrear.isSelected()){
            labJustificacionCrear.setText("SI");
        }
    }

    @FXML
    public void getJustificadoModificar(){
        if(rbJustificadoNoModificar.isSelected()){
            labJustificacionModificar.setText("NO");
        } else if(rbJustificadoSiModificar.isSelected()){
            labJustificacionModificar.setText("SI");
        }
    }

    @FXML
    public void getCertificadoCrear(){
        if(rbCertificadoNoCrear.isSelected()){
            labCertificadoCrear.setText("NO");
        } else if(rbCertificadoSiCrear.isSelected()){
            labCertificadoCrear.setText("SI");
        }
    }

    @FXML
    public void getCertificadoModificar(){
        if(rbCertificadoNoModificar.isSelected()){
            labCertificadoModificar.setText("NO");
        } else if(rbCertificadoSiModificar.isSelected()){
            labCertificadoModificar.setText("SI");
        }
    }

    private void comprobacionRadioBotomSeleccion(){
        if(labSeleccionJustificacion.getText().equals("NO")){
            rbJustificadoNoModificar.fire();
        } else{
            rbJustificadoSiModificar.fire();
        }
    }

    private void comprobacionRadioBotomSeleccionC(){
        if(labSeleccionCertificado.getText().equals("NO")){
            rbCertificadoNoModificar.fire();
        } else{
            rbCertificadoSiModificar.fire();
        }
    }

    @FXML
    private void registrarAusencia() {
        SingleSelectionModel<Tab> modeloSeleccion = panelPestaniasAusencias.getSelectionModel();
        modeloSeleccion.select(tabRegistrarAusencia);
        dpFechaCrear.requestFocus();
        regresarConsultaAsistenia = "0";
    }

    @FXML
    private void modificarAusencia() {
        if(labIDAusenciaModificar.getText().trim().isEmpty() || labIDAusenciaModificar.getText() == null){
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Advertencia!");
            alerta.setContentText("Debe de seleccionar antes una Ausencia de dicho empleado para Modificarlo");
            alerta.showAndWait();
        } else {
            SingleSelectionModel<Tab> modeloSeleccion = panelPestaniasAusencias.getSelectionModel();
            modeloSeleccion.select(tabModificarAusencia);
            dpFechaModificar.requestFocus();
        }
    }

    @FXML
    private void eliminarAusencia() {
        if (labIDAusenciaEliminar.getText().trim().isEmpty() || labIDAusenciaEliminar.getText() == null){
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Advertencia!");
            alerta.setContentText("Debe de seleccionar antes una Ausencia de dicho empleado para Eliminarlo");
            alerta.showAndWait();
        } else {
            SingleSelectionModel<Tab> modeloSeleccion = panelPestaniasAusencias.getSelectionModel();
            modeloSeleccion.select(tabEliminarAusencia);
            btnEliminar.requestFocus();
        }
    }

    @FXML
    private void consultaAsistencia() {
        SingleSelectionModel<Tab> modeloSeleccion = panelPestaniasAusencias.getSelectionModel();
        modeloSeleccion.select(tabConsultaAsistencia);
    }

    @FXML
    private void regresarCLista() {
        if(Objects.equals(regresarConsultaAsistenia, "0")){
            SingleSelectionModel<Tab> modeloSeleccion = panelPestaniasAusencias.getSelectionModel();
            modeloSeleccion.select(tabListaAusencias);
        } else if(Objects.equals(regresarConsultaAsistenia, "1")){
            SingleSelectionModel<Tab> modeloSeleccion = panelPestaniasAusencias.getSelectionModel();
            modeloSeleccion.select(tabConsultaAsistencia);
        }
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
    private void regresarCCLista() {
        SingleSelectionModel<Tab> modeloSeleccion = panelPestaniasAusencias.getSelectionModel();
        modeloSeleccion.select(tabListaAusencias);
    }

    @FXML
    private void verificarSalida(){
        SingleSelectionModel<Tab> modeloSeleccion = panelPestaniasAusencias.getSelectionModel();
        modeloSeleccion.select(tabConsultaSalida);
    }

    @FXML
    private void registrarEntrada(){
        if(asistencia.equals("YES")){
            SingleSelectionModel<Tab> modeloSeleccion = panelPestaniasAusencias.getSelectionModel();
            modeloSeleccion.select(tabRegistroEntrada);
            labErrorHoraEntrada.setText("");
            labErrorHoraEntrada2.setText("");
        } else {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Advertencia!");
            alerta.setContentText("Debe de seleccionar antes un empleado en la (Tabla del Cronograma) para registrar su entrada");
            alerta.showAndWait();
        }
    }

    @FXML
    private void registrarAusenciaA(){
        if(asistencia.equals("YES")){
            SingleSelectionModel<Tab> modeloSeleccion = panelPestaniasAusencias.getSelectionModel();
            modeloSeleccion.select(tabRegistrarAusencia);
            regresarConsultaAsistenia = "1";
        } else {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Advertencia!");
            alerta.setContentText("Debe de seleccionar antes un empleado en la (Tabla del Cronograma) para registrar su ausencia");
            alerta.showAndWait();
        }
    }

    @FXML
    private void regresarAsistenciaEntrada(){
        SingleSelectionModel<Tab> modeloSeleccion = panelPestaniasAusencias.getSelectionModel();
        modeloSeleccion.select(tabConsultaAsistencia);
    }

    @FXML
    private void regresarAsistenciaS(){
        SingleSelectionModel<Tab> modeloSeleccion = panelPestaniasAusencias.getSelectionModel();
        modeloSeleccion.select(tabConsultaAsistencia);

    }

    @FXML
    private void registrarSalida(){
        if(salida.equals("YES")){
            SingleSelectionModel<Tab> modeloSeleccion = panelPestaniasAusencias.getSelectionModel();
            modeloSeleccion.select(tabRegistroSalida);
            labErrorHoraSalida.setText("");
            labErrorHoraSalida2.setText("");
        } else {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Advertencia!");
            alerta.setContentText("Debe de seleccionar antes un empleado en la (Tabla del Cronograma) para registrar su salida");
            alerta.showAndWait();
        }
    }

    @FXML
    private void regresarAsistenciaSalida(){
        SingleSelectionModel<Tab> modeloSeleccion = panelPestaniasAusencias.getSelectionModel();
        modeloSeleccion.select(tabConsultaSalida);
    }

    @FXML
    private void volverEmpleados() throws IOException {
        closeWindowsPrincipalAusencia();
    }

    //---------------------------------------- Comprobación de Campos -------------------------------------------
    private void camposObligatoriosCrear(){
        if (textMotivoCrear.getText().trim().isEmpty() || textMotivoCrear.getText() == null) {
            labErrorMotivoCrear.setText("Campo Obligatorio");
        } else {
            labErrorMotivoCrear.setText("");
        }
    }

    private void validacionCamposLongitudCrear(){
        String motivo = textMotivoCrear.getText().trim();
        motivo = motivo.replaceAll("\\s+", "");

        if (motivo.length() < 10) {
            labErrorMotivoCrear.setText("Usa 10 o más caracteres");
        } else {
            labErrorMotivoCrear.setText("");
        }
    }

    private void validacionCamposCaracteresCrear() {
        String motivo = textMotivoCrear.getText().trim();
        motivo = motivo.replaceAll("\\s+", "");
        if (validarLetras(motivo)) {
            labErrorMotivoCrear.setText("");
        } else {
            labErrorMotivoCrear.setText("Solo se admiten Letras");
        }
    }

    private void camposObligatoriosModificar(){
        if (textMotivoModificar.getText().trim().isEmpty() || textMotivoModificar.getText() == null) {
            labErrorMotivoModificar.setText("Campo Obligatorio");
        } else {
            labErrorMotivoModificar.setText("");
        }
    }

    private void validacionCamposLongitudModificar(){
        String motivo = textMotivoModificar.getText().trim();
        motivo = motivo.replaceAll("\\s+", "");

        if (motivo.length() < 10) {
            labErrorMotivoModificar.setText("Usa 10 o más caracteres");
        } else {
            labErrorMotivoModificar.setText("");
        }
    }

    private void validacionCamposCaracteresModificar() {
        String motivo = textMotivoModificar.getText().trim();
        motivo = motivo.replaceAll("\\s+", "");

        if (validarLetras(motivo)) {
            labErrorMotivoModificar.setText("");
        } else {
            labErrorMotivoModificar.setText("Solo se admiten Letras");
        }
    }

    //---------------------------------------- Validación de Caracteres ------------------------------------------
    public static boolean validarLetras(String datos){
        return datos.matches("[a-zA-Z]*");
    }

    //----------------------------------------- Limpiador de Campos ----------------------------------------------
    private void limpiarCamposCrear(){
        textBuscarLegajoEmpleadoCrear.setText("");
        labIDEmpleadoCrear.setText("");
        labNombreEmpleadoCrear.setText("");
        labApellidoEmpleadoCrear.setText("");
        labLegajoEmpleadoCrear.setText("");
        textMotivoCrear.setText("");
        rbJustificadoNoCrear.fire();
        rbCertificadoNoCrear.fire();
        labLimpiarCamposCrear.setText("");
        getJustificadoCrear();
        getCertificadoCrear();
        fechasInicializar();
    }

    private void limpiarCamposModificar(){
        textBuscarLegajoEmpleado.setText("");
        labIDEmpleadoModificar.setText("");
        labNombreEmpleadoModificar.setText("");
        labApellidoEmpleadoModificar.setText("");
        labLegajoEmpleadoModificar.setText("");
        textMotivoModificar.setText("");
        rbJustificadoNoModificar.fire();
        rbCertificadoNoModificar.fire();
        labLimpiarCamposModificar.setText("");
        labIDAusenciaModificar.setText("");
        getJustificadoModificar();
        getCertificadoModificar();
        fechasInicializar();
    }

    private void limpiarCamposEliminar(){
        textBuscarLegajoEmpleado.setText("");
        labIDEmpleadoEliminar.setText("");
        labNombreEmpleadoEliminar.setText("");
        labApellidoEmpleadoEliminar.setText("");
        labLegajoEmpleadoEliminar.setText("");
        textMotivoEliminar.setText("");
        labJustificadoEliminar.setText("");
        labCertificadoEliminar.setText("");
        labIDAusenciaEliminar.setText("");
        fechasInicializar();
    }

    private void limpiarCamposGeneralizada(){
        labIDEmpleadoModificar.setText("");
        labNombreEmpleadoModificar.setText("");
        labApellidoEmpleadoModificar.setText("");
        labLegajoEmpleadoModificar.setText("");
        textMotivoModificar.setText("");
        rbJustificadoNoModificar.fire();
        rbCertificadoNoModificar.fire();
        labLimpiarCamposModificar.setText("");
        labIDAusenciaModificar.setText("");
        getJustificadoModificar();
        getCertificadoModificar();
        labIDEmpleadoEliminar.setText("");
        labNombreEmpleadoEliminar.setText("");
        labApellidoEmpleadoEliminar.setText("");
        labLegajoEmpleadoEliminar.setText("");
        textMotivoEliminar.setText("");
        labJustificadoEliminar.setText("");
        labCertificadoEliminar.setText("");
        labIDAusenciaEliminar.setText("");
        fechasInicializar();
    }

    private void limpiarCamposAusencias(){
        labIDAusenciaModificar.setText("");
        dpFechaModificarDuplicada.getEditor().setText("");
        textMotivoModificar.setText("");
        rbJustificadoNoModificar.fire();
        rbCertificadoNoModificar.fire();

        labIDAusenciaEliminar.setText("");
        textMotivoEliminar.setText("");
        labJustificadoEliminar.setText("");
        labCertificadoEliminar.setText("");
        fechasInicializarMyE();
    }

    private void limpiarCamposEntrada(){
        labExisteEntrada.setText("");
        labIDEmpleadoEntradaA.setText("");
        labNombreEmpleadoEntrada.setText("");
        labApellidoEmpleadoEntrada.setText("");
        labLegajoEmpleadoEntrada.setText("");
        labFechaEntrada.setText("");
        labTurnoEntrada.setText("");
        textHoraEntrada.setText("");
    }

    private void limpiarCamposEntradaAusencia(){
        textBuscarLegajoEmpleadoCrear.setText("");
        labIDEmpleadoCrear.setText("");
        labNombreEmpleadoCrear.setText("");
        labApellidoEmpleadoCrear.setText("");
        labLegajoEmpleadoCrear.setText("");
    }

    private void limpiarCamposSalida(){
        labExisteSalida.setText("");
        labIDEmpleadoSalidaS.setText("");
        labNombreEmpleadoSalida.setText("");
        labApellidoEmpleadoSalida.setText("");
        labLegajoEmpleadoSalida.setText("");
        labFechaSalida.setText("");
        labTurnoSalida.setText("");
        textHoraSalida.setText("");
    }

    private void tablasAsistencias(){
        // Cronograma
        colNombreAsistenciaCE.setCellValueFactory(new PropertyValueFactory<ConsultaAsistencia, String>("nombre"));
        colApellidoAsistenciaCE.setCellValueFactory(new PropertyValueFactory<ConsultaAsistencia, String>("apellido"));
        colLegajoAsistenciaCE.setCellValueFactory(new PropertyValueFactory<ConsultaAsistencia, Integer>("legajo"));
        colTurnoAsistenciaCE.setCellValueFactory(new PropertyValueFactory<ConsultaAsistencia, String>("turno"));
        colFechaAsistenciaCE.setCellValueFactory(new PropertyValueFactory<ConsultaAsistencia, String>("fecha"));
        colHoraEntradaAsistenciaCE.setCellValueFactory(new PropertyValueFactory<ConsultaAsistencia, String>("horaEntrada"));
        colHoraSalidaAsistenciaCE.setCellValueFactory(new PropertyValueFactory<ConsultaAsistencia, String>("horaSalida"));
        colIDCronogramaAsistenciaCE.setCellValueFactory(new PropertyValueFactory<ConsultaAsistencia, Integer>("idCronograma"));
        colIDEmpleadoCronogramaA.setCellValueFactory(new PropertyValueFactory<ConsultaAsistencia, Integer>("idEmpleado"));

        listCronogramaCE = ConsultaAsistencia.listaCronogramaCE(dpBuscarFechaAsistencia, cbTurnoAsistencia, labCantidadCronograma);
        tablaCronogramaAsistencia.getColumns().setAll(colNombreAsistenciaCE, colApellidoAsistenciaCE, colLegajoAsistenciaCE, colTurnoAsistenciaCE, colFechaAsistenciaCE, colHoraEntradaAsistenciaCE, colHoraSalidaAsistenciaCE, colIDCronogramaAsistenciaCE, colIDEmpleadoCronogramaA);
        tablaCronogramaAsistencia.getItems().setAll(listCronogramaCE);

        // Entrada
        colNombreEntrada.setCellValueFactory(new PropertyValueFactory<ConsultaAsistencia, String>("nombre"));
        colApellidoEntrada.setCellValueFactory(new PropertyValueFactory<ConsultaAsistencia, String>("apellido"));
        colLegajoEntrada.setCellValueFactory(new PropertyValueFactory<ConsultaAsistencia, Integer>("legajo"));
        colFechaEntrada.setCellValueFactory(new PropertyValueFactory<ConsultaAsistencia, String>("fecha"));
        colHoraEntrada.setCellValueFactory(new PropertyValueFactory<ConsultaAsistencia, String>("horaEntrada"));
        colIDEmpleadoEntrada.setCellValueFactory(new PropertyValueFactory<ConsultaAsistencia, Integer>("idEmpleado"));

        listEntrada = ConsultaAsistencia.listaEntrada(dpBuscarFechaAsistencia, cbTurnoAsistencia, labCantidadPresentes);
        tablaListaEmpleadosPresentes.getColumns().setAll(colNombreEntrada, colApellidoEntrada, colLegajoEntrada, colFechaEntrada, colHoraEntrada, colIDEmpleadoEntrada);
        tablaListaEmpleadosPresentes.getItems().setAll(listEntrada);
    }

    private void tablasAsistenciasBuscar(){
        // Cronograma
        colNombreAsistenciaCE.setCellValueFactory(new PropertyValueFactory<ConsultaAsistencia, String>("nombre"));
        colApellidoAsistenciaCE.setCellValueFactory(new PropertyValueFactory<ConsultaAsistencia, String>("apellido"));
        colLegajoAsistenciaCE.setCellValueFactory(new PropertyValueFactory<ConsultaAsistencia, Integer>("legajo"));
        colTurnoAsistenciaCE.setCellValueFactory(new PropertyValueFactory<ConsultaAsistencia, String>("turno"));
        colFechaAsistenciaCE.setCellValueFactory(new PropertyValueFactory<ConsultaAsistencia, String>("fecha"));
        colHoraEntradaAsistenciaCE.setCellValueFactory(new PropertyValueFactory<ConsultaAsistencia, String>("horaEntrada"));
        colHoraSalidaAsistenciaCE.setCellValueFactory(new PropertyValueFactory<ConsultaAsistencia, String>("horaSalida"));
        colIDCronogramaAsistenciaCE.setCellValueFactory(new PropertyValueFactory<ConsultaAsistencia, Integer>("idCronograma"));
        colIDEmpleadoCronogramaA.setCellValueFactory(new PropertyValueFactory<ConsultaAsistencia, Integer>("idEmpleado"));

        listCronogramaCE = ConsultaAsistencia.listaCronogramaCEBuscar(dpBuscarFechaAsistencia, cbTurnoAsistencia, labCantidadCronograma, textBuscarLegajoEmpleadoAsistencia, cbBuscarEmpleadoEntrada);
        tablaCronogramaAsistencia.getColumns().setAll(colNombreAsistenciaCE, colApellidoAsistenciaCE, colLegajoAsistenciaCE, colTurnoAsistenciaCE, colFechaAsistenciaCE, colHoraEntradaAsistenciaCE, colHoraSalidaAsistenciaCE, colIDCronogramaAsistenciaCE, colIDEmpleadoCronogramaA);
        tablaCronogramaAsistencia.getItems().setAll(listCronogramaCE);

        // Entrada
        colNombreEntrada.setCellValueFactory(new PropertyValueFactory<ConsultaAsistencia, String>("nombre"));
        colApellidoEntrada.setCellValueFactory(new PropertyValueFactory<ConsultaAsistencia, String>("apellido"));
        colLegajoEntrada.setCellValueFactory(new PropertyValueFactory<ConsultaAsistencia, Integer>("legajo"));
        colFechaEntrada.setCellValueFactory(new PropertyValueFactory<ConsultaAsistencia, String>("fecha"));
        colHoraEntrada.setCellValueFactory(new PropertyValueFactory<ConsultaAsistencia, String>("horaEntrada"));
        colIDEmpleadoEntrada.setCellValueFactory(new PropertyValueFactory<ConsultaAsistencia, Integer>("idEmpleado"));

        listEntrada = ConsultaAsistencia.listaEntradaBuscar(dpBuscarFechaAsistencia, cbTurnoAsistencia, labCantidadPresentes, textBuscarLegajoEmpleadoAsistencia, cbBuscarEmpleadoEntrada);
        tablaListaEmpleadosPresentes.getColumns().setAll(colNombreEntrada, colApellidoEntrada, colLegajoEntrada, colFechaEntrada, colHoraEntrada, colIDEmpleadoEntrada);
        tablaListaEmpleadosPresentes.getItems().setAll(listEntrada);
    }

    private void tablasSalidas(){
        // Cronograma
        colNombreAsistenciaCS.setCellValueFactory(new PropertyValueFactory<VerificarSalida, String>("nombre"));
        colApellidoAsistenciaCS.setCellValueFactory(new PropertyValueFactory<VerificarSalida, String>("apellido"));
        colLegajoAsistenciaCS.setCellValueFactory(new PropertyValueFactory<VerificarSalida, Integer>("legajo"));
        colTurnoAsistenciaCS.setCellValueFactory(new PropertyValueFactory<VerificarSalida, String>("turno"));
        colFechaAsistenciaCS.setCellValueFactory(new PropertyValueFactory<VerificarSalida, String>("fecha"));
        colHoraSalidaAsistenciaCS.setCellValueFactory(new PropertyValueFactory<VerificarSalida, String>("horaSalida"));
        colHoraEntradaAsistenciaCS.setCellValueFactory(new PropertyValueFactory<VerificarSalida, String>("horaEntrada"));
        colIDCronogramaAsistenciaCS.setCellValueFactory(new PropertyValueFactory<VerificarSalida, Integer>("idCronograma"));
        colIDEmpleadoCronogramaS.setCellValueFactory(new PropertyValueFactory<VerificarSalida, Integer>("idEmpleado"));

        listCronogramaCS = VerificarSalida.listaCronogramaCS(dpBuscarFechaSalida, cbTurnoSalida, labCantidadCronogramaS);
        tablaCronogramaSalida.getColumns().setAll(colNombreAsistenciaCS, colApellidoAsistenciaCS, colLegajoAsistenciaCS, colTurnoAsistenciaCS, colFechaAsistenciaCS, colHoraSalidaAsistenciaCS, colHoraEntradaAsistenciaCS, colIDCronogramaAsistenciaCS, colIDEmpleadoCronogramaS);
        tablaCronogramaSalida.getItems().setAll(listCronogramaCS);

        // Salida
        colNombreSalida.setCellValueFactory(new PropertyValueFactory<VerificarSalida, String>("nombre"));
        colApellidoSalida.setCellValueFactory(new PropertyValueFactory<VerificarSalida, String>("apellido"));
        colLegajoSalida.setCellValueFactory(new PropertyValueFactory<VerificarSalida, Integer>("legajo"));
        colFechaSalida.setCellValueFactory(new PropertyValueFactory<VerificarSalida, String>("fecha"));
        colHoraSalida.setCellValueFactory(new PropertyValueFactory<VerificarSalida, String>("horaSalida"));
        colIDEmpleadoSalida.setCellValueFactory(new PropertyValueFactory<VerificarSalida, Integer>("idEmpleado"));

        listSalida = VerificarSalida.listaSalida(dpBuscarFechaSalida, cbTurnoSalida, labCantidadS);
        tablaListaEmpleadosSalida.getColumns().setAll(colNombreSalida, colApellidoSalida, colLegajoSalida, colFechaSalida, colHoraSalida, colIDEmpleadoSalida);
        tablaListaEmpleadosSalida.getItems().setAll(listSalida);
    }

    private void tablasSalidaBuscar(){
        // Cronograma
        colNombreAsistenciaCS.setCellValueFactory(new PropertyValueFactory<VerificarSalida, String>("nombre"));
        colApellidoAsistenciaCS.setCellValueFactory(new PropertyValueFactory<VerificarSalida, String>("apellido"));
        colLegajoAsistenciaCS.setCellValueFactory(new PropertyValueFactory<VerificarSalida, Integer>("legajo"));
        colTurnoAsistenciaCS.setCellValueFactory(new PropertyValueFactory<VerificarSalida, String>("turno"));
        colFechaAsistenciaCS.setCellValueFactory(new PropertyValueFactory<VerificarSalida, String>("fecha"));
        colHoraSalidaAsistenciaCS.setCellValueFactory(new PropertyValueFactory<VerificarSalida, String>("horaSalida"));
        colHoraEntradaAsistenciaCS.setCellValueFactory(new PropertyValueFactory<VerificarSalida, String>("horaEntrada"));
        colIDCronogramaAsistenciaCS.setCellValueFactory(new PropertyValueFactory<VerificarSalida, Integer>("idCronograma"));
        colIDEmpleadoCronogramaS.setCellValueFactory(new PropertyValueFactory<VerificarSalida, Integer>("idEmpleado"));

        listCronogramaCS = VerificarSalida.listaCronogramaCSBuscar(dpBuscarFechaSalida, cbTurnoSalida, labCantidadCronogramaS, textBuscarLegajoEmpleadoSalida, cbBuscarEmpleadoSalida);
        tablaCronogramaSalida.getColumns().setAll(colNombreAsistenciaCS, colApellidoAsistenciaCS, colLegajoAsistenciaCS, colTurnoAsistenciaCS, colFechaAsistenciaCS, colHoraSalidaAsistenciaCS, colHoraEntradaAsistenciaCS, colIDCronogramaAsistenciaCS, colIDEmpleadoCronogramaS);
        tablaCronogramaSalida.getItems().setAll(listCronogramaCS);

        // Salida
        colNombreSalida.setCellValueFactory(new PropertyValueFactory<VerificarSalida, String>("nombre"));
        colApellidoSalida.setCellValueFactory(new PropertyValueFactory<VerificarSalida, String>("apellido"));
        colLegajoSalida.setCellValueFactory(new PropertyValueFactory<VerificarSalida, Integer>("legajo"));
        colFechaSalida.setCellValueFactory(new PropertyValueFactory<VerificarSalida, String>("fecha"));
        colHoraSalida.setCellValueFactory(new PropertyValueFactory<VerificarSalida, String>("horaSalida"));
        colIDEmpleadoSalida.setCellValueFactory(new PropertyValueFactory<VerificarSalida, Integer>("idEmpleado"));

        listSalida = VerificarSalida.listaSalidaBuscar(dpBuscarFechaSalida, cbTurnoSalida, labCantidadS, textBuscarLegajoEmpleadoSalida, cbBuscarEmpleadoSalida);
        tablaListaEmpleadosSalida.getColumns().setAll(colNombreSalida, colApellidoSalida, colLegajoSalida, colFechaSalida, colHoraSalida, colIDEmpleadoSalida);
        tablaListaEmpleadosSalida.getItems().setAll(listSalida);
    }

    // ---------------------------------------- Fechas Actuales Inicializadas ----------------------------------------
    public void fechasInicializar() {
        dpBuscarFecha.setValue(LocalDate.now());
        dpBuscarFecha.setConverter(new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate localDate) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                return dtf.format(localDate);
            }

            @Override
            public LocalDate fromString(String s) {
                return null;
            }
        });

        dpFechaCrear.setValue(LocalDate.now());
        dpFechaCrear.setConverter(new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate localDate) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                return dtf.format(localDate);
            }

            @Override
            public LocalDate fromString(String s) {
                return null;
            }
        });

        dpFechaModificar.setValue(LocalDate.now());
        dpFechaModificar.setConverter(new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate localDate) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                return dtf.format(localDate);
            }

            @Override
            public LocalDate fromString(String s) {
                return null;
            }
        });

        dpFechaEliminar.setValue(LocalDate.now());
        dpFechaEliminar.setConverter(new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate localDate) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                return dtf.format(localDate);
            }

            @Override
            public LocalDate fromString(String s) {
                return null;
            }
        });
    }

    public void fechasInicializarMyE() {
        dpFechaModificar.setValue(LocalDate.now());
        dpFechaModificar.setConverter(new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate localDate) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                return dtf.format(localDate);
            }

            @Override
            public LocalDate fromString(String s) {
                return null;
            }
        });

        dpFechaEliminar.setValue(LocalDate.now());
        dpFechaEliminar.setConverter(new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate localDate) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                return dtf.format(localDate);
            }

            @Override
            public LocalDate fromString(String s) {
                return null;
            }
        });
    }

    public void fechasInicializarConsultas() {
        dpBuscarFechaAsistencia.setValue(LocalDate.now());
        dpBuscarFechaAsistencia.setConverter(new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate localDate) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                return dtf.format(localDate);
            }

            @Override
            public LocalDate fromString(String s) {
                return null;
            }
        });

        dpBuscarFechaSalida.setValue(LocalDate.now());
        dpBuscarFechaSalida.setConverter(new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate localDate) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                return dtf.format(localDate);
            }

            @Override
            public LocalDate fromString(String s) {
                return null;
            }
        });
    }


    // ---------------------------------- Cerrar Ventana -------------------------------------------
    public void closeWindowsPrincipalAusencia() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/empleados.fxml"));
        AnchorPane root = loader.load();
        Scene escena = new Scene(root);
        Stage escenario = new Stage();
        escenario.setTitle("Gestión Secadero (Empleados)");
        escenario.setScene(escena);
        escenario.getIcons().add(new javafx.scene.image.Image(Objects.requireNonNull(getClass().getResource("/imagenes/icono.png")).toString()));
        escenario.show();

        Stage myEscena = (Stage) this.btnVolver.getScene().getWindow();
        myEscena.close();
    }
}
