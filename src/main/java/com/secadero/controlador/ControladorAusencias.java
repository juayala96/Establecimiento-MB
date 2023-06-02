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
    private TableView<ConsultaAsistencia> tablaCronogramaSalida;
    @FXML
    private TableView<ConsultaAsistencia> tablaListaEmpleadosSalida;
    @FXML
    private DatePicker dpBuscarFechaAsistencia;
    @FXML
    private DatePicker dpBuscarFechaSalida;
    @FXML
    private ComboBox<String> cbTurnoAsistencia;
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
    private TableColumn<ConsultaAsistencia, String> colNombreAsistenciaCS;
    @FXML
    private TableColumn<ConsultaAsistencia, String> colApellidoAsistenciaCS;
    @FXML
    private TableColumn<ConsultaAsistencia, Integer> colLegajoAsistenciaCS;
    @FXML
    private TableColumn<ConsultaAsistencia, String> colTurnoAsistenciaCS;
    @FXML
    private TableColumn<ConsultaAsistencia, String> colFechaAsistenciaCS;
    @FXML
    private TableColumn<ConsultaAsistencia, String> colHoraEntradaAsistenciaCS;
    @FXML
    private TableColumn<ConsultaAsistencia, String> colHoraSalidaAsistenciaCS;
    @FXML
    private TableColumn<ConsultaAsistencia, Integer> colIDCronogramaAsistenciaCS;
    @FXML
    private TableColumn<ConsultaAsistencia, String> colNombreSalida;
    @FXML
    private TableColumn<ConsultaAsistencia, String> colApellidoSalida;
    @FXML
    private TableColumn<ConsultaAsistencia, Integer> colLegajoSalida;
    @FXML
    private TableColumn<ConsultaAsistencia, String> colFechaSalida;
    @FXML
    private TableColumn<ConsultaAsistencia, String> colHoraSalida;
    @FXML
    private TableColumn<ConsultaAsistencia, Integer> colIDEmpleadoSalida;
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
    private Label labErrorHoraSalida;
    @FXML
    private TextField textHoraEntrada;
    @FXML
    private TextField textHoraSalida;
    @FXML
    private TextField textBuscarLegajoEmpleadoCrear;

    ObservableList<LeerEmpleado> listTablaEmpleados;
    int index = -1;

    ObservableList<LeerAusencia> listAusencia;
    int index2 = -1;

    ObservableList<LeerEmpleado> listEmpleadoAusencia;
    int index3 = -1;

    String regresarConsultaAsistenia = "0";
    ObservableList<ConsultaAsistencia> listCronogramaCE;
    int index4 = -1;
    ObservableList<ConsultaAsistencia> listCronogramaCS;
    int index5 = -1;

    // -------------------------------------------- Inicialización ----------------------------------------------
    public void initialize() {
        String[] turnos = {"Dia", "Noche"};
        cbTurnoAsistencia.getItems().addAll(turnos);
        cbTurnoAsistencia.getSelectionModel().selectFirst();
        cbTurnoSalida.getItems().addAll(turnos);
        cbTurnoSalida.getSelectionModel().selectFirst();
        inicializarTablaListaEmpleados();
        inicializarTablaEmpleado();
        getJustificadoCrear();
        getJustificadoModificar();
        getCertificadoCrear();
        getCertificadoModificar();
        fechasInicializar();
        fechasInicializarConsultas();
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

    // --------------------------------------------- Búsqueda y Filtros -----------------------------------------
    @FXML
    private void btnBuscarEmpleado() {
        ObservableList<LeerEmpleado> listBuscarEmpleado;
        listBuscarEmpleado = LeerEmpleado.buscarEmpleadoGeneral(textBuscarLegajoEmpleado);
        if(textBuscarLegajoEmpleado.getText().equals("")){
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Error!");
            alerta.setContentText("Debe de completar el Legajo para Buscar");
            alerta.showAndWait();
        } else {
            tablaListaEmpleados.getItems().setAll(listBuscarEmpleado);
        }
    }

    @FXML
    private void btnBuscarAusencia(){
        ObservableList<LeerAusencia> listaBuscarAusencia;
        listaBuscarAusencia = LeerAusencia.buscarAusenciaFecha(labIDEmpleadoLista, dpBuscarFecha);
        tablaAusencias.getItems().setAll(listaBuscarAusencia);
    }

    @FXML
    private void btnBuscarEmpleadoCrear(){
        ObservableList<LeerEmpleado> listBuscarEmpleadoCrear;
        listBuscarEmpleadoCrear = LeerEmpleado.buscarEmpleadoGeneralCrear(textBuscarLegajoEmpleadoCrear);
        tabEmpleadosCrear.getItems().setAll(listBuscarEmpleadoCrear);
    }

    @FXML
    private void btnBuscarEmpleadoAsistencia(){

    }

    @FXML
    private void btnBuscarEmpleadoSalida(){

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
    private void consultaAsistenciaGeneral(){
        textBuscarLegajoEmpleadoAsistencia.setText("");
        colNombreAsistenciaCE.setCellValueFactory(new PropertyValueFactory<ConsultaAsistencia, String>("nombre"));
        colApellidoAsistenciaCE.setCellValueFactory(new PropertyValueFactory<ConsultaAsistencia, String>("apellido"));
        colLegajoAsistenciaCE.setCellValueFactory(new PropertyValueFactory<ConsultaAsistencia, Integer>("legajo"));
        colTurnoAsistenciaCE.setCellValueFactory(new PropertyValueFactory<ConsultaAsistencia, String>("turno"));
        colFechaAsistenciaCE.setCellValueFactory(new PropertyValueFactory<ConsultaAsistencia, String>("fecha"));
        colHoraEntradaAsistenciaCE.setCellValueFactory(new PropertyValueFactory<ConsultaAsistencia, String>("horaEntrada"));
        colHoraSalidaAsistenciaCE.setCellValueFactory(new PropertyValueFactory<ConsultaAsistencia, String>("horaSalida"));
        colIDCronogramaAsistenciaCE.setCellValueFactory(new PropertyValueFactory<ConsultaAsistencia, Integer>("idCronograma"));

        listCronogramaCE = ConsultaAsistencia.listaCronogramaCE(dpBuscarFechaAsistencia, cbTurnoAsistencia);
        tablaCronogramaAsistencia.getColumns().setAll(colNombreAsistenciaCE, colApellidoAsistenciaCE, colLegajoAsistenciaCE, colTurnoAsistenciaCE, colFechaAsistenciaCE, colHoraEntradaAsistenciaCE, colHoraSalidaAsistenciaCE, colIDCronogramaAsistenciaCE);
        tablaCronogramaAsistencia.getItems().setAll(listCronogramaCE);
    }

    @FXML
    private void consultaSalidaGeneral(){
        textBuscarLegajoEmpleadoSalida.setText("");
    }

    @FXML
    private void marcarEntrada(){

    }

    @FXML
    private void marcarSalida(){

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
        SingleSelectionModel<Tab> modeloSeleccion = panelPestaniasAusencias.getSelectionModel();
        modeloSeleccion.select(tabRegistroEntrada);
        LocalTime horaActual = LocalTime.now();
        int hora = horaActual.getHour();
        int minuto = horaActual.getMinute();
        textHoraEntrada.setText(hora + ":" + minuto);
    }

    @FXML
    private void registrarAusenciaA(){
        SingleSelectionModel<Tab> modeloSeleccion = panelPestaniasAusencias.getSelectionModel();
        modeloSeleccion.select(tabRegistrarAusencia);
        regresarConsultaAsistenia = "1";
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
        SingleSelectionModel<Tab> modeloSeleccion = panelPestaniasAusencias.getSelectionModel();
        modeloSeleccion.select(tabRegistroSalida);
        LocalTime horaActual = LocalTime.now();
        int hora = horaActual.getHour();
        int minuto = horaActual.getMinute();
        textHoraSalida.setText(hora + ":" + minuto);
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
        escenario.show();

        Stage myEscena = (Stage) this.btnVolver.getScene().getWindow();
        myEscena.close();
    }
}
