package com.secadero.controlador;

import com.secadero.modelo.cronograma.CrearCronograma;
import com.secadero.modelo.cronograma.EliminarCronograma;
import com.secadero.modelo.cronograma.LeerCronograma;
import com.secadero.modelo.cronograma.ModificarCronograma;
import com.secadero.modelo.empleados.LeerEmpleado;
import com.secadero.modelo.licencias.LeerLicencia;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ControladorCronograma {
    public ControladorCronograma(){}

    @FXML
    private Button btnActualizar;
    @FXML
    private Button btnBuscarCronogramaFecha;
    @FXML
    private Button btnBuscarListaEmpleadoDisponible;
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
    private Button btnCalendario;
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
    private Button btnBuscarFechaEmpleadoCalendario;
    @FXML
    private Button btnActualizarCalendario;
    @FXML
    private Button btnBuscarListaEmpleadoCalendario;
    @FXML
    private Button btnbuscarTurno;
    @FXML
    private ComboBox<String> cbTurnoCrear;
    @FXML
    private ComboBox<String> cbTurnoEliminar;
    @FXML
    private ComboBox<String> cbTurnoModificar;
    @FXML
    private ComboBox<String> cbBuscarEmpleadoDisponibleLista;
    @FXML
    private ComboBox<String> cbBuscarEmpleadoDisponibleCrear;
    @FXML
    private ComboBox<String> cbBuscarEmpleadoCalendario;
    @FXML
    private ComboBox<String> cbBuscarEmpleado;
    @FXML
    private ComboBox<String> cbBuscarTurnoCalendario;
    @FXML
    private TableColumn<LeerEmpleado, String> colApellido;
    @FXML
    private TableColumn<LeerEmpleado, String> colApellidoEmpleadoCrear;
    @FXML
    private TableColumn<LeerEmpleado, Integer> colDNIEmpleadoCrear;
    @FXML
    private TableColumn<LeerCronograma, String> colFecha;
    @FXML
    private TableColumn<LeerCronograma, String> colHoraEntrada;
    @FXML
    private TableColumn<LeerCronograma, String> colHoraSalida;
    @FXML
    private TableColumn<LeerCronograma, Integer> colIDCronograma;
    @FXML
    private TableColumn<LeerEmpleado, Integer> colIDEmpleado;
    @FXML
    private TableColumn<LeerEmpleado, Integer> colIDEmpleadoCrear;
    @FXML
    private TableColumn<LeerEmpleado, Integer> colLegajo;
    @FXML
    private TableColumn<LeerEmpleado, Integer> colLegajoEmpleadoCrear;
    @FXML
    private TableColumn<LeerEmpleado, String> colNombre;
    @FXML
    private TableColumn<LeerEmpleado, String> colNombreEmpleadoCrear;
    @FXML
    private TableColumn<LeerEmpleado, Integer> colDNI;
    @FXML
    private TableColumn<LeerEmpleado, String> colTelefonoEmpleadoCrear;
    @FXML
    private TableColumn<LeerCronograma, String> colTurno;
    @FXML
    private TableColumn<LeerCronograma, String> colNombreCalendario;
    @FXML
    private TableColumn<LeerCronograma, String> colApellidoCalendario;
    @FXML
    private TableColumn<LeerCronograma, Integer> colLegajoCalendario;
    @FXML
    private TableColumn<LeerCronograma, Integer> colDNICalendario;
    @FXML
    private TableColumn<LeerCronograma, String> colFechaCalendario;
    @FXML
    private TableColumn<LeerCronograma, String> colTurnoCalendario;
    @FXML
    private TableColumn<LeerCronograma, String> colHoraEntradaCalendario;
    @FXML
    private TableColumn<LeerCronograma, String> colHoraSalidaCalendario;
    @FXML
    private TableColumn<LeerCronograma, Integer> colIDEmpleadoCalendario;
    @FXML
    private TableColumn<LeerCronograma, Integer> colIDCronogramaCalendario;
    @FXML
    private DatePicker dpBuscarFecha;
    @FXML
    private DatePicker dpFechaCrear;
    @FXML
    private DatePicker dpFechaEliminar;
    @FXML
    private DatePicker dpFechaModificarDuplicada;
    @FXML
    private DatePicker dpFechaModificar;
    @FXML
    private DatePicker dpFechaCalendario;
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
    private Label labFechaCrearSeleccionada;
    @FXML
    private TabPane panelPestaniasCronograma;
    @FXML
    private Tab tabCrearCronograma;
    @FXML
    private Tab tabEliminarCronograma;
    @FXML
    private TableView<LeerEmpleado> tabEmpleadosCrear;
    @FXML
    private Tab tabListaCronograma;
    @FXML
    private Tab tabModificarCronograma;
    @FXML
    private Tab tabCalendario;
    @FXML
    private TableView<LeerCronograma> tablaCronograma;
    @FXML
    private TableView<LeerEmpleado> tablaListaEmpleados;
    @FXML
    private TableView<LeerCronograma> tablaListaEmpleadosCalendario;
    @FXML
    private TextField textBuscarLegajoEmpleado;
    @FXML
    private TextField textHoraEntradaCrear;
    @FXML
    private TextField textHoraEntradaEliminar;
    @FXML
    private TextField textHoraEntradaModificar;
    @FXML
    private TextField textHoraSalidaCrear;
    @FXML
    private TextField textHoraSalidaEliminar;
    @FXML
    private TextField textHoraSalidaModificar;
    @FXML
    private TextField textBuscarLegajoEmpleadoCalendario;
    @FXML
    private ListView<String> listEmpleadosAgregados;
    @FXML
    private Label labNombreEmpleadoAgregadoCrear;
    @FXML
    private Label labApellidoEmpleadoAgregadoCrear;
    @FXML
    private Tab tabConsultaLicencia;
    @FXML
    private TableView<LeerEmpleado> tablaListaEmpleadosConsulta;
    @FXML
    private TableColumn<LeerEmpleado, String> colNombreConsulta;
    @FXML
    private TableColumn<LeerEmpleado, String> colApellidoConsulta;
    @FXML
    private TableColumn<LeerEmpleado, Integer> colLegajoConsulta;
    @FXML
    private TableColumn<LeerEmpleado, Integer> colDNIConsulta;
    @FXML
    private TableColumn<LeerEmpleado, Integer> colIDEmpleadoConsulta;
    @FXML
    private TextField textBuscarLegajoEmpleadoConsulta;
    @FXML
    private Button btnBuscarEmpleadoConsulta;
    @FXML
    private TableView<LeerLicencia> tablaLicenciaConsulta;
    @FXML
    private TableColumn<LeerLicencia, String> colFechaInicio;
    @FXML
    private TableColumn<LeerLicencia, String> colFechaFin;
    @FXML
    private TableColumn<LeerLicencia, String> colTipoLicencia;
    @FXML
    private TableColumn<LeerLicencia, Integer> colIDLicencia;
    @FXML
    private DatePicker dpBuscarFechaInicioConsulta;
    @FXML
    private Label labIDEmpleadoConsulta;
    @FXML
    private Label labInfoLegajoConsulta;
    @FXML
    private Button btnBuscarLicenciaFechaConsulta;
    @FXML
    private Button btnRegresarCLMenu;
    @FXML
    private TextField textBuscarLegajoEmpleadoCrear;

    ObservableList<LeerEmpleado> listTablaEmpleadosDisponible;
    int index = -1;

    ObservableList<LeerCronograma> listCronograma;
    int index2 = -1;

    ObservableList<LeerEmpleado> listEmpleado;
    int index3 = -1;
    int index4 = -1;

    ObservableList<LeerEmpleado> listEmpleadoConsulta;
    ObservableList<LeerLicencia> listLicencia;
    int index5 = -1;

    ObservableList<LeerCronograma> listEmpleadoCalendario;
    String regresarConsultaLicencia = "0";

    // -------------------------------------------- Inicialización ----------------------------------------------
    public void initialize() throws ParseException {
        String[] tipoTurnos = {"Dia", "Noche"};
        dpFechaModificarDuplicada.getEditor().setText("01-01-2000");
        comboBoxCrear();
        comboBoxModificar();
        comboBoxEliminar();
        cbTurnoCrear.getItems().addAll(tipoTurnos);
        cbTurnoCrear.getSelectionModel().selectFirst();
        cbTurnoModificar.getItems().addAll(tipoTurnos);
        cbTurnoModificar.getSelectionModel().selectFirst();
        cbTurnoEliminar.getItems().addAll(tipoTurnos);
        cbTurnoEliminar.getSelectionModel().selectFirst();
        fechasInicializar();
        fechaCalendario();
        fechaConsultaLicencia();
        relojEmpleadoDisponible();
        inicializarTablaListaEmpleadosDisponible();
        inicializarTablaEmpleado();
        labIDEmpleadoConsulta.setText("0");

        String[] tipoTurno = {"Noche", "Dia"};
        cbBuscarTurnoCalendario.getItems().addAll(tipoTurno);
        cbBuscarTurnoCalendario.getSelectionModel().selectFirst();

        String[] tipoBuscar = {"Legajo", "Nombre", "Apellido"};
        cbBuscarEmpleadoDisponibleLista.getItems().addAll(tipoBuscar);
        cbBuscarEmpleadoDisponibleCrear.getItems().addAll(tipoBuscar);
        cbBuscarEmpleadoCalendario.getItems().addAll(tipoBuscar);
        cbBuscarEmpleado.getItems().addAll(tipoBuscar);
        cbBuscarEmpleadoDisponibleLista.getSelectionModel().selectFirst();
        cbBuscarEmpleadoDisponibleCrear.getSelectionModel().selectFirst();
        cbBuscarEmpleadoCalendario.getSelectionModel().selectFirst();
        cbBuscarEmpleado.getSelectionModel().selectFirst();

        cbBuscarEmpleadoDisponibleLista.setOnAction(event -> {
            String selectedItem = cbBuscarEmpleadoDisponibleLista.getSelectionModel().getSelectedItem();
            textBuscarLegajoEmpleado.setPromptText("Ingrese el " + selectedItem);
        });

        cbBuscarEmpleadoDisponibleCrear.setOnAction(event -> {
            String selectedItem = cbBuscarEmpleadoDisponibleCrear.getSelectionModel().getSelectedItem();
            textBuscarLegajoEmpleadoCrear.setPromptText("Ingrese el " + selectedItem);
        });

        cbBuscarEmpleadoCalendario.setOnAction(event -> {
            String selectedItem = cbBuscarEmpleadoCalendario.getSelectionModel().getSelectedItem();
            textBuscarLegajoEmpleadoCalendario.setPromptText("Ingrese el " + selectedItem);
        });

        cbBuscarEmpleado.setOnAction(event -> {
            String selectedItem = cbBuscarEmpleado.getSelectionModel().getSelectedItem();
            textBuscarLegajoEmpleadoConsulta.setPromptText("Ingrese el " + selectedItem);
        });

        // Agregar un escuchador de eventos al DatePicker
        dpFechaCrear.setOnAction(event -> {
            LocalDate selectedDate = dpFechaCrear.getValue();
            labFechaCrearSeleccionada.setText(String.valueOf(selectedDate));
            inicializarTablaEmpleado();
        });
    }

    // ----------------------------------------- Tabla de Empleados ---------------------------------------------
    public void inicializarTablaListaEmpleadosDisponible(){
        colNombre.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, String>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, String>("apellido"));
        colLegajo.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, Integer>("legajo"));
        colDNI.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, Integer>("dni"));
        colIDEmpleado.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, Integer>("idempleados"));

        listTablaEmpleadosDisponible = LeerEmpleado.listaEmpleadoDisponibleLista();
        tablaListaEmpleados.getColumns().setAll(colNombre, colApellido, colLegajo, colDNI, colIDEmpleado);
        tablaListaEmpleados.getItems().setAll(listTablaEmpleadosDisponible);
    }

    // ----------------------------------------- Tabla de Cronograma ---------------------------------------------
    public void inicializarTablaCronograma(){
        colFecha.setCellValueFactory(new PropertyValueFactory<LeerCronograma, String>("fecha"));
        colTurno.setCellValueFactory(new PropertyValueFactory<LeerCronograma, String>("turno"));
        colHoraEntrada.setCellValueFactory(new PropertyValueFactory<LeerCronograma, String>("horario_entrada"));
        colHoraSalida.setCellValueFactory(new PropertyValueFactory<LeerCronograma, String>("horario_salida"));
        colIDCronograma.setCellValueFactory(new PropertyValueFactory<LeerCronograma, Integer>("idCronograma"));

        listCronograma = LeerCronograma.listaCronograma(labIDEmpleadoLista);
        tablaCronograma.getColumns().setAll(colFecha, colTurno, colHoraEntrada, colHoraSalida, colIDCronograma);
        tablaCronograma.getItems().setAll(listCronograma);
    }

    // ------------------------------------ Tabla de Empleados Crear ---------------------------------------------
    public void inicializarTablaEmpleado(){
        colNombreEmpleadoCrear.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, String>("nombre"));
        colApellidoEmpleadoCrear.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, String>("apellido"));
        colLegajoEmpleadoCrear.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, Integer>("legajo"));
        colDNIEmpleadoCrear.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, Integer>("dni"));
        colIDEmpleadoCrear.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, Integer>("idempleados"));

        listEmpleado = LeerEmpleado.listaEmpleadoDisponible(labFechaCrearSeleccionada);
        tabEmpleadosCrear.getColumns().setAll(colNombreEmpleadoCrear, colApellidoEmpleadoCrear, colLegajoEmpleadoCrear, colDNIEmpleadoCrear, colIDEmpleadoCrear);
        tabEmpleadosCrear.getItems().setAll(listEmpleado);
    }

    // --------------------------------- Tabla de Empleados (Consulta Licencia) -----------------------------------------
    public void inicializarTablaEmpleadoConsultaCrear(){
        colNombreConsulta.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, String>("nombre"));
        colApellidoConsulta.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, String>("apellido"));
        colLegajoConsulta.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, Integer>("legajo"));
        colDNIConsulta.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, Integer>("dni"));
        colIDEmpleadoConsulta.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, Integer>("idempleados"));

        listEmpleadoConsulta = LeerEmpleado.listaEmpleadoConsulta(listEmpleadosAgregados);
        tablaListaEmpleadosConsulta.getColumns().setAll(colNombreConsulta, colApellidoConsulta, colLegajoConsulta, colDNIConsulta, colIDEmpleadoConsulta);
        tablaListaEmpleadosConsulta.getItems().setAll(listEmpleadoConsulta);
    }

    public void inicializarTablaEmpleadoConsultaModificar(){
        colNombreConsulta.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, String>("nombre"));
        colApellidoConsulta.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, String>("apellido"));
        colLegajoConsulta.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, Integer>("legajo"));
        colDNIConsulta.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, Integer>("dni"));
        colIDEmpleadoConsulta.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, Integer>("idempleados"));

        listEmpleadoConsulta = LeerEmpleado.listaEmpleadoConsultaModificar(labIDEmpleadoModificar);
        tablaListaEmpleadosConsulta.getColumns().setAll(colNombreConsulta, colApellidoConsulta, colLegajoConsulta, colDNIConsulta, colIDEmpleadoConsulta);
        tablaListaEmpleadosConsulta.getItems().setAll(listEmpleadoConsulta);
    }

    // --------------------------------- Tabla de Licencia (Consulta Licencia) -----------------------------------------
    public void inicializarTablaLicenciaConsulta(){
        colFechaInicio.setCellValueFactory(new PropertyValueFactory<LeerLicencia, String>("fecha_Inicio"));
        colFechaFin.setCellValueFactory(new PropertyValueFactory<LeerLicencia, String>("fecha_Fin"));
        colTipoLicencia.setCellValueFactory(new PropertyValueFactory<LeerLicencia, String>("descripcion"));

        listLicencia = LeerLicencia.listaLicencia(labIDEmpleadoConsulta);
        tablaLicenciaConsulta.getColumns().setAll(colFechaInicio, colFechaFin, colTipoLicencia, colIDLicencia);
        tablaLicenciaConsulta.getItems().setAll(listLicencia);
    }

    // ------------------------------------ Tabla de Empleados Calendario ---------------------------------------------
    public void inicializarTablaEmpleadoCalendario(){
        colNombreCalendario.setCellValueFactory(new PropertyValueFactory<LeerCronograma, String>("nombre"));
        colApellidoCalendario.setCellValueFactory(new PropertyValueFactory<LeerCronograma, String>("apellido"));
        colLegajoCalendario.setCellValueFactory(new PropertyValueFactory<LeerCronograma, Integer>("legajo"));
        colDNICalendario.setCellValueFactory(new PropertyValueFactory<LeerCronograma, Integer>("dni"));
        colFechaCalendario.setCellValueFactory(new PropertyValueFactory<LeerCronograma, String>("fecha"));
        colTurnoCalendario.setCellValueFactory(new PropertyValueFactory<LeerCronograma, String>("turno"));
        colHoraEntradaCalendario.setCellValueFactory(new PropertyValueFactory<LeerCronograma, String>("horario_entrada"));
        colHoraSalidaCalendario.setCellValueFactory(new PropertyValueFactory<LeerCronograma, String>("horario_salida"));
        colIDEmpleadoCalendario.setCellValueFactory(new PropertyValueFactory<LeerCronograma, Integer>("idempleados"));
        colIDCronogramaCalendario.setCellValueFactory(new PropertyValueFactory<LeerCronograma, Integer>("idCronograma"));

        listEmpleadoCalendario = LeerCronograma.listaEmpleadoCalendario(dpFechaCalendario);
        tablaListaEmpleadosCalendario.getColumns().setAll(colNombreCalendario, colApellidoCalendario, colLegajoCalendario, colDNICalendario, colFechaCalendario, colTurnoCalendario, colHoraEntradaCalendario, colHoraSalidaCalendario, colIDEmpleadoCalendario, colIDCronogramaCalendario);
        tablaListaEmpleadosCalendario.getItems().setAll(listEmpleadoCalendario);
    }

    // ------------------------------------- Tomo todos los datos al Seleccionar en la tabla ------------------------
    @FXML
    private void getListaEmpleados(){
        index = tablaListaEmpleados.getSelectionModel().getSelectedIndex();
        if (index <= -1){

            return;
        }

        labIDCronogramaLista.setText("0");
        labIDEmpleadoLista.setText(colIDEmpleado.getCellData(index).toString());
        labIDEmpleadoModificar.setText(colIDEmpleado.getCellData(index).toString());
        labNombreEmpleadoModificar.setText(colNombre.getCellData(index));
        labApellidoEmpleadoModificar.setText(colApellido.getCellData(index));
        labLegajoEmpleadoModificar.setText(colLegajo.getCellData(index).toString());

        labIDEmpleadoEliminar.setText(colIDEmpleado.getCellData(index).toString());
        labNombreEmpleadoEliminar.setText(colNombre.getCellData(index));
        labApellidoEmpleadoEliminar.setText(colApellido.getCellData(index));
        labLegajoEmpleadoEliminar.setText(colLegajo.getCellData(index).toString());
        inicializarTablaCronograma();

        LeerCronograma cronogramaLeer = new LeerCronograma();
        cronogramaLeer.listaCronogramaComprobacion(labIDEmpleadoLista, labIDCronogramaLista, labResultadoID);
        if(labResultadoID.getText().equals("NO")){
            limpiarCamposCronograma();
        }
    }

    @FXML
    private void getCronograma(){
        index2 = tablaCronograma.getSelectionModel().getSelectedIndex();
        if (index2 <= -1){

            return;
        }
        labIDCronogramaLista.setText(colIDCronograma.getCellData(index2).toString());
        labIDCronogramaModificar.setText(colIDCronograma.getCellData(index2).toString());
        labIDCronogramaEliminar.setText(colIDCronograma.getCellData(index2).toString());
        cbTurnoModificar.getSelectionModel().select(colTurno.getCellData(index2));
        cbTurnoEliminar.getSelectionModel().select(colTurno.getCellData(index2));
        dpFechaModificar.getEditor().setText(colFecha.getCellData(index2).toString());
        dpFechaModificarDuplicada.getEditor().setText(colFecha.getCellData(index2).toString());
        dpFechaEliminar.getEditor().setText(colFecha.getCellData(index2).toString());
        textHoraEntradaModificar.setText(colHoraEntrada.getCellData(index2));
        textHoraEntradaEliminar.setText(colHoraEntrada.getCellData(index2));
        textHoraSalidaModificar.setText(colHoraSalida.getCellData(index2));
        textHoraSalidaEliminar.setText(colHoraSalida.getCellData(index2));

        String fechaM = colFecha.getCellData(index2);
        dpFechaModificar.setValue(LocalDate.of(Integer.parseInt(fechaM.substring(6, 10)), Integer.parseInt(fechaM.substring(3, 5)), Integer.parseInt(fechaM.substring(0, 2))));
    }

    @FXML
    private void getListaEmpleadosCrear(){
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
    private void getEmpleadoAgregadoCrear(){
        index4 = listEmpleadosAgregados.getSelectionModel().getSelectedIndex();
        if (index4 <= -1){

            return;
        }
        String legajo = listEmpleadosAgregados.getItems().get(index4);
        LeerEmpleado dato = new LeerEmpleado();
        dato.listaEmpleadoAgregado(labNombreEmpleadoAgregadoCrear, labApellidoEmpleadoAgregadoCrear, legajo);
    }

    @FXML
    private void getListaEmpleadosConsulta(){
        index5 = tablaListaEmpleadosConsulta.getSelectionModel().getSelectedIndex();
        if (index5 <= -1){

            return;
        }
        labIDEmpleadoConsulta.setText(colIDEmpleadoConsulta.getCellData(index5).toString());
        inicializarTablaLicenciaConsulta();
    }

    // ------------------------ Combo Boxes actualiza las Horas Automáticamente por Turno ------------------------
    public void comboBoxCrear(){
        String item = cbTurnoCrear.getSelectionModel().getSelectedItem();
        cbTurnoCrear.getSelectionModel().selectedItemProperty().addListener(this::cambioDeItem);
    }

    public void comboBoxModificar(){
        String item = cbTurnoModificar.getSelectionModel().getSelectedItem();
        cbTurnoModificar.getSelectionModel().selectedItemProperty().addListener(this::cambioDeItem2);
    }

    public void comboBoxEliminar(){
        String item = cbTurnoEliminar.getSelectionModel().getSelectedItem();
        cbTurnoEliminar.getSelectionModel().selectedItemProperty().addListener(this::cambioDeItem3);
    }

    public void cambioDeItem(ObservableValue<? extends String> observable, String valorAntiguo, String valorActual){
        if(valorActual.equals("Dia")){
            textHoraEntradaCrear.setText("12:00");
            textHoraSalidaCrear.setText("23:59");
        } else if(valorActual.equals("Noche")){
            textHoraEntradaCrear.setText("00:00");
            textHoraSalidaCrear.setText("12:00");
        }
    }

    public void cambioDeItem2(ObservableValue<? extends String> observable, String valorAntiguo, String valorActual){
        if(valorActual.equals("Dia")){
            textHoraEntradaModificar.setText("12:00");
            textHoraSalidaModificar.setText("23:59");
        } else if(valorActual.equals("Noche")){
            textHoraEntradaModificar.setText("00:00");
            textHoraSalidaModificar.setText("12:00");
        }
    }

    public void cambioDeItem3(ObservableValue<? extends String> observable, String valorAntiguo, String valorActual){
        if(valorActual.equals("Dia")){
            textHoraEntradaEliminar.setText("12:00");
            textHoraSalidaEliminar.setText("23:59");
        } else if(valorActual.equals("Noche")){
            textHoraEntradaEliminar.setText("00:00");
            textHoraSalidaEliminar.setText("12:00");
        }
    }

    // --------------------------------------------- Búsqueda y Filtros -----------------------------------------
    @FXML
    private void btnBuscarEmpleadoDisponible() {
        limpiarCamposGeneralizada();
        ObservableList<LeerEmpleado> listBuscarEmpleado;
        listBuscarEmpleado = LeerEmpleado.buscarEmpleadoDisponible(textBuscarLegajoEmpleado, cbBuscarEmpleadoDisponibleLista);
        if(textBuscarLegajoEmpleado.getText().equals("")){
            String dato = cbBuscarEmpleadoDisponibleLista.getSelectionModel().getSelectedItem();
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Error!");
            alerta.setContentText("Debe de completar el "+ dato +" para Buscar");
            alerta.showAndWait();
        } else {
            tablaListaEmpleados.getItems().setAll(listBuscarEmpleado);
            labIDEmpleadoLista.setText("0");
            inicializarTablaCronograma();
        }
    }

    @FXML
    private void btnBuscarCronograma() {
        labIDCronogramaModificar.setText("");
        labIDCronogramaEliminar.setText("");
        if(!labIDEmpleadoLista.getText().equals("")){
            ObservableList<LeerCronograma> listaBuscarCronograma;
            listaBuscarCronograma = LeerCronograma.buscarFechaCronograma(labIDEmpleadoLista, dpBuscarFecha);
            tablaCronograma.getItems().setAll(listaBuscarCronograma);
        }
    }

    @FXML
    private void btnBuscarEmpleadoCrear(){
        ObservableList<LeerEmpleado> listBuscarEmpleado;
        listBuscarEmpleado = LeerEmpleado.buscarEmpleadoDisponible(textBuscarLegajoEmpleadoCrear, cbBuscarEmpleadoDisponibleCrear);
        tabEmpleadosCrear.getItems().setAll(listBuscarEmpleado);
    }

    @FXML
    private void actualizarTabla() {
        labIDCronogramaLista.setText("0");
        labIDEmpleadoLista.setText("0");
        textBuscarLegajoEmpleado.setText("");
        tablaListaEmpleados.getItems().setAll(listTablaEmpleadosDisponible);
        inicializarTablaListaEmpleadosDisponible();
        inicializarTablaCronograma();
        limpiarCamposModificar();
        limpiarCamposEliminar();
    }

    @FXML
    private void actualizarTablaConsulta(){
        labIDEmpleadoConsulta.setText("0");
        textBuscarLegajoEmpleadoConsulta.setText("");
        fechaConsultaLicencia();
        inicializarTablaLicenciaConsulta();
        if(regresarConsultaLicencia.equals("0")){
            inicializarTablaEmpleadoConsultaCrear();
        } else if(regresarConsultaLicencia.equals("1")){
            inicializarTablaEmpleadoConsultaModificar();
        }
    }

    @FXML
    private void btnBuscarEmpleadoConsulta(){
        if(!textBuscarLegajoEmpleadoConsulta.getText().equals("")){
            if(regresarConsultaLicencia.equals("0")){
                ObservableList<LeerEmpleado> listBuscarEmpleadoConsultaCrear;
                listBuscarEmpleadoConsultaCrear = LeerEmpleado.buscarEmpleadoConsultaCrear(listEmpleadosAgregados, textBuscarLegajoEmpleadoConsulta, cbBuscarEmpleado);
                tablaListaEmpleadosConsulta.getItems().setAll(listBuscarEmpleadoConsultaCrear);
                labIDEmpleadoConsulta.setText("0");
                inicializarTablaLicenciaConsulta();
            }
        } else {
            String dato = cbBuscarEmpleado.getSelectionModel().getSelectedItem();
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Error!");
            alerta.setContentText("Debe de completar el "+ dato +" para Buscar");
            alerta.showAndWait();
        }
    }

    @FXML
    private void btnBuscarLicenciaConsulta(){
        ObservableList<LeerLicencia> listaBuscarLicencia;
        listaBuscarLicencia = LeerLicencia.buscarLicenciaFechaInicio(labIDEmpleadoConsulta, dpBuscarFechaInicioConsulta);
        tablaLicenciaConsulta.getItems().setAll(listaBuscarLicencia);
    }

    @FXML
    private void buscarFechaEmpleadoCalendario(){
        ObservableList<LeerCronograma> listaBuscarCalendario;
        listaBuscarCalendario = LeerCronograma.buscarFechaCalendario(dpFechaCalendario);
        tablaListaEmpleadosCalendario.getItems().setAll(listaBuscarCalendario);
    }

    @FXML
    private void buscarListaEmpleadoCalendario(){
        ObservableList<LeerCronograma> listaBuscarCalendario;
        listaBuscarCalendario = LeerCronograma.buscarLegajoCalendario(textBuscarLegajoEmpleadoCalendario, dpFechaCalendario, cbBuscarEmpleadoCalendario);
        tablaListaEmpleadosCalendario.getItems().setAll(listaBuscarCalendario);
    }

    @FXML
    private void buscarTurno(){
        ObservableList<LeerCronograma> listaBuscarCalendarioTurno;
        listaBuscarCalendarioTurno = LeerCronograma.buscarLegajoCalendarioTurno(textBuscarLegajoEmpleadoCalendario, dpFechaCalendario, cbBuscarEmpleadoCalendario, cbBuscarTurnoCalendario);
        tablaListaEmpleadosCalendario.getItems().setAll(listaBuscarCalendarioTurno);
    }

    @FXML
    private void actualizarCalendario(){
        textBuscarLegajoEmpleadoCalendario.setText("");
        fechaCalendario();
        inicializarTablaEmpleadoCalendario();
        cbBuscarTurnoCalendario.getSelectionModel().selectFirst();
    }

    // ------------------------------------------ Agrego en la Lista ---------------------------------------------
    @FXML
    private void agregarCrear(){
        if(!labLegajoEmpleadoCrear.getText().equals("")){
            boolean bandera = false;
            for (int i = 0; i < listEmpleadosAgregados.getItems().size(); i++) {
                if(labLegajoEmpleadoCrear.getText().equals(listEmpleadosAgregados.getItems().get(i))){
                    bandera = true;
                }
            }
            if(bandera == false){
                listEmpleadosAgregados.getItems().add(labLegajoEmpleadoCrear.getText());

            } else if(bandera == true){
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error!");
                alerta.setContentText("El Empleado ya fue agregado");
                alerta.showAndWait();
            }
        }
    }

    // ------------------------------------------ Eliminas en la Lista ---------------------------------------------
    @FXML
    private void quitarCrear(){
        int seleccionoID = listEmpleadosAgregados.getSelectionModel().getSelectedIndex();
        if(seleccionoID != -1){
            listEmpleadosAgregados.getItems().remove(seleccionoID);
            labNombreEmpleadoAgregadoCrear.setText("");
            labApellidoEmpleadoAgregadoCrear.setText("");
            getEmpleadoAgregadoCrear();
        }
    }

    //---------------------------------------------- Eventos Importantes ----------------------------------------
    @FXML
    private void guardar() throws ParseException {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = dpFechaCrear.getEditor().getText();
        String fechaAnio = fecha.substring(6, 10);
        String fechaMes = fecha.substring(3, 5);
        String fechaDia = fecha.substring(0, 2);
        String fechaModificada = (fechaAnio + "-" + fechaMes + "-" + fechaDia);
        Date fechaModificadaDate = java.sql.Date.valueOf(fechaModificada);

        Date fechaActualPais = new Date();
        String Fecha_Actual = (formatoFecha.format(fechaActualPais));
        Date fechaActual = formatoFecha.parse(Fecha_Actual);
        long Diferencias2 = fechaActual.getTime() - fechaModificadaDate.getTime();
        long Cant_Dias2 = Diferencias2 / (1000 * 60 * 60 * 24);

        if(listEmpleadosAgregados.getItems().size() != 0){
            if((-Cant_Dias2) > 0){
                CrearCronograma cronogramaCrear = new CrearCronograma();
                cronogramaCrear.agregarCronograma(listEmpleadosAgregados, dpFechaCrear, cbTurnoCrear, textHoraEntradaCrear, textHoraSalidaCrear, labLimpiarCamposCrear);

                if(Objects.equals(labLimpiarCamposCrear.getText(), "OK")){
                    labLimpiarCamposCrear.setText("");
                    labIDCronogramaLista.setText("0");
                    labIDEmpleadoLista.setText("0");
                    inicializarTablaListaEmpleadosDisponible();
                    inicializarTablaEmpleado();
                    inicializarTablaCronograma();
                    inicializarTablaEmpleadoCalendario();
                    regresarCCMenu();
                    limpiarCamposCrear();
                }
            } else {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error de Fecha!");
                alerta.setContentText("La Fecha Ingresada debe de realizarse un dia después de la Fecha Actual");
                alerta.showAndWait();
            }
        } else {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Error");
            alerta.setContentText("Para guardar debes de agregar un empleado");
            alerta.showAndWait();
        }
    }

    @FXML
    private void modificar() throws ParseException {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = dpFechaModificar.getEditor().getText();
        String fechaAnio = fecha.substring(6, 10);
        String fechaMes = fecha.substring(3, 5);
        String fechaDia = fecha.substring(0, 2);
        String fechaModificada = (fechaAnio + "-" + fechaMes + "-" + fechaDia);
        Date fechaModificadaDate = java.sql.Date.valueOf(fechaModificada);

        String fecha2 = dpFechaModificarDuplicada.getEditor().getText();
        String fechaAnio2 = fecha2.substring(6, 10);
        String fechaMes2 = fecha2.substring(3, 5);
        String fechaDia2 = fecha2.substring(0, 2);
        String fechaModificada2 = (fechaAnio2 + "-" + fechaMes2 + "-" + fechaDia2);
        Date fechaModificadaDateDuplicada = java.sql.Date.valueOf(fechaModificada2);

        Date fechaActualPais = new Date();
        String Fecha_Actual = (formatoFecha.format(fechaActualPais));
        Date fechaActual = formatoFecha.parse(Fecha_Actual);
        long Diferencias2 = fechaActual.getTime() - fechaModificadaDate.getTime();
        long Cant_Dias2 = Diferencias2 / (1000 * 60 * 60 * 24);

        long Diferencias = fechaActual.getTime() - fechaModificadaDateDuplicada.getTime();
        long Cant_Dias = Diferencias / (1000 * 60 * 60 * 24);

        if(!Objects.equals(labIDCronogramaModificar.getText(), "")){
            if((-Cant_Dias) >= 0){
                if((-Cant_Dias2) >= 0){
                    ModificarCronograma cronogramaModificar = new ModificarCronograma();
                    cronogramaModificar.modificarCronograma(labIDEmpleadoModificar, labIDCronogramaModificar, dpFechaModificar, dpFechaModificarDuplicada, cbTurnoModificar, textHoraEntradaModificar, textHoraSalidaModificar, labLimpiarCamposModificar);

                    if(Objects.equals(labLimpiarCamposModificar.getText(), "OK")){
                        labLimpiarCamposModificar.setText("");
                        labIDCronogramaLista.setText("0");
                        labIDEmpleadoLista.setText("0");
                        inicializarTablaListaEmpleadosDisponible();
                        inicializarTablaCronograma();
                        inicializarTablaEmpleadoCalendario();
                        regresarCCMenu();
                        limpiarCamposModificar();
                        limpiarCamposEliminar();
                    }
                } else {
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("Error de Fecha!");
                    alerta.setContentText("La Fecha Ingresada solo se permite Modificar desde la Fecha Actual o Superior");
                    alerta.showAndWait();
                }
            } else {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error de Modificar");
                alerta.setContentText("El cronograma que desea modificar en este empleado ya no está permitido.  Motivo: es una Fecha Pasada");
                alerta.showAndWait();
            }
        } else {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Error");
            alerta.setContentText("Para Modificar debes de tomar un Cronograma del empleado");
            alerta.showAndWait();
        }

    }

    @FXML
    private void eliminar() throws ParseException {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = dpFechaEliminar.getEditor().getText();
        String fechaAnio = fecha.substring(6, 10);
        String fechaMes = fecha.substring(3, 5);
        String fechaDia = fecha.substring(0, 2);
        String fechaModificada = (fechaAnio + "-" + fechaMes + "-" + fechaDia);
        Date fechaModificadaDate = java.sql.Date.valueOf(fechaModificada);

        Date fechaActualPais = new Date();
        String Fecha_Actual = (formatoFecha.format(fechaActualPais));
        Date fechaActual = formatoFecha.parse(Fecha_Actual);
        long Diferencias2 = fechaActual.getTime() - fechaModificadaDate.getTime();
        long Cant_Dias2 = Diferencias2 / (1000 * 60 * 60 * 24);

        if (!Objects.equals(labIDCronogramaEliminar.getText(), "")){
            if((-Cant_Dias2) >= 0){
                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setTitle("Confirmar Eliminar");
                alerta.setContentText("¿Desea Eliminar el Cronograma del Empleado?");
                Optional<ButtonType> resultado = alerta.showAndWait();
                if (resultado.isPresent() && resultado.get() == ButtonType.OK){

                    EliminarCronograma licenciaEliminar = new EliminarCronograma();
                    licenciaEliminar.eliminarCronograma(labIDCronogramaEliminar, labIDEmpleadoEliminar);
                    labIDCronogramaLista.setText("0");
                    labIDEmpleadoLista.setText("0");
                    inicializarTablaListaEmpleadosDisponible();
                    inicializarTablaCronograma();
                    inicializarTablaEmpleadoCalendario();
                    limpiarCamposEliminar();
                    limpiarCamposModificar();
                    regresarCMenu();
                }
            } else {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error de Eliminar");
                alerta.setContentText("El cronograma que desea eliminar en este empleado ya no está permitido.  Motivo: es una Fecha Pasada");
                alerta.showAndWait();
            }
        } else{
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Error!");
            alerta.setContentText("Debe de seleccionar antes una Licencia de dicho empleado para Eliminarlo");
            alerta.showAndWait();
            btnRegresarEMenu.requestFocus();
        }
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
        if(labIDCronogramaModificar.getText().trim().isEmpty() || labIDCronogramaModificar.getText() == null){
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Advertencia!");
            alerta.setContentText("Para Modificar debes de tomar un Cronograma del empleado");
            alerta.showAndWait();
        } else {
            SingleSelectionModel<Tab> modeloSeleccion = panelPestaniasCronograma.getSelectionModel();
            modeloSeleccion.select(tabModificarCronograma);
            dpFechaModificar.requestFocus();
        }
    }

    @FXML
    private void eliminarCronograma() {
        if(labIDCronogramaEliminar.getText().trim().isEmpty() || labIDCronogramaEliminar.getText() == null){
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Advertencia!");
            alerta.setContentText("Para Eliminar debes de tomar un Cronograma del empleado");
            alerta.showAndWait();
        } else {
            SingleSelectionModel<Tab> modeloSeleccion = panelPestaniasCronograma.getSelectionModel();
            modeloSeleccion.select(tabEliminarCronograma);
            btnEliminar.requestFocus();
        }
    }

    @FXML
    private void calendario() {
        SingleSelectionModel<Tab> modeloSeleccion = panelPestaniasCronograma.getSelectionModel();
        modeloSeleccion.select(tabCalendario);
        inicializarTablaEmpleadoCalendario();
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
    private void consultaLicenciaCrear(){
        if(listEmpleadosAgregados.getItems().size() != 0){
            SingleSelectionModel<Tab> modeloSeleccion = panelPestaniasCronograma.getSelectionModel();
            modeloSeleccion.select(tabConsultaLicencia);
            inicializarTablaEmpleadoConsultaCrear();
            btnBuscarEmpleadoConsulta.setVisible(true);
            textBuscarLegajoEmpleadoConsulta.setVisible(true);
            textBuscarLegajoEmpleadoConsulta.setText("");
            cbBuscarEmpleado.setVisible(true);
        } else {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Advertencia");
            alerta.setContentText("Para Consultar la Licencia debe de haber uno o más empleados agregados");
            alerta.showAndWait();
        }
        regresarConsultaLicencia = "0";
        labIDEmpleadoConsulta.setText("0");
        inicializarTablaLicenciaConsulta();
    }

    @FXML
    private void consultaLicenciaModificar(){
        if(!labIDEmpleadoModificar.getText().equals("")){
            SingleSelectionModel<Tab> modeloSeleccion = panelPestaniasCronograma.getSelectionModel();
            modeloSeleccion.select(tabConsultaLicencia);
            inicializarTablaEmpleadoConsultaModificar();
            btnBuscarEmpleadoConsulta.setVisible(false);
            textBuscarLegajoEmpleadoConsulta.setVisible(false);
            cbBuscarEmpleado.setVisible(false);
        } else {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Advertencia!");
            alerta.setContentText("Debe de seleccionar un Empleado antes para Consultar la Licencia");
            alerta.showAndWait();
        }
        regresarConsultaLicencia = "1";
        labIDEmpleadoConsulta.setText("0");
        inicializarTablaLicenciaConsulta();
    }

    @FXML
    private void regresarCLMenu(){
        if(Objects.equals(regresarConsultaLicencia, "0")){
            SingleSelectionModel<Tab> modeloSeleccion = panelPestaniasCronograma.getSelectionModel();
            modeloSeleccion.select(tabCrearCronograma);
        } else if(Objects.equals(regresarConsultaLicencia, "1")){
            SingleSelectionModel<Tab> modeloSeleccion = panelPestaniasCronograma.getSelectionModel();
            modeloSeleccion.select(tabModificarCronograma);
        }
    }

    @FXML
    private void volverPrincipal() throws IOException {
        closeWindowsPrincipalCronograma();
    }

    //----------------------------------------- Limpiador de Campos ----------------------------------------------
    private void limpiarCamposCrear(){
        textBuscarLegajoEmpleadoCrear.setText("");
        labIDEmpleadoCrear.setText("");
        labNombreEmpleadoCrear.setText("");
        labApellidoEmpleadoCrear.setText("");
        labLegajoEmpleadoCrear.setText("");
        cbTurnoCrear.getSelectionModel().selectFirst();
        labLimpiarCamposCrear.setText("");

        while (listEmpleadosAgregados.getItems().size() > 0){
            for (int i = 0; i < listEmpleadosAgregados.getItems().size(); i++) {
                listEmpleadosAgregados.getItems().remove(i);
            }
        }
        comboBoxCrear();
        fechasInicializar();
        labNombreEmpleadoAgregadoCrear.setText("");
        labApellidoEmpleadoAgregadoCrear.setText("");
    }

    private void limpiarCamposModificar(){
        textBuscarLegajoEmpleado.setText("");
        labIDCronogramaModificar.setText("");
        labIDEmpleadoModificar.setText("");
        labNombreEmpleadoModificar.setText("");
        labApellidoEmpleadoModificar.setText("");
        labLegajoEmpleadoModificar.setText("");
        labLimpiarCamposModificar.setText("");
        dpFechaModificarDuplicada.getEditor().setText("01-01-2000");
        cbTurnoModificar.getSelectionModel().selectFirst();
        comboBoxModificar();
        fechasInicializar();
    }

    private void limpiarCamposGeneralizada(){
        labIDEmpleadoCrear.setText("");
        labNombreEmpleadoCrear.setText("");
        labApellidoEmpleadoCrear.setText("");
        labLegajoEmpleadoCrear.setText("");
        cbTurnoCrear.getSelectionModel().selectFirst();
        labLimpiarCamposCrear.setText("");

        while (listEmpleadosAgregados.getItems().size() > 0){
            for (int i = 0; i < listEmpleadosAgregados.getItems().size(); i++) {
                listEmpleadosAgregados.getItems().remove(i);
            }
        }
        comboBoxCrear();
        fechasInicializar();
        labNombreEmpleadoAgregadoCrear.setText("");
        labApellidoEmpleadoAgregadoCrear.setText("");
        labIDCronogramaModificar.setText("");
        labIDEmpleadoModificar.setText("");
        labNombreEmpleadoModificar.setText("");
        labApellidoEmpleadoModificar.setText("");
        labLegajoEmpleadoModificar.setText("");
        labLimpiarCamposModificar.setText("");
        dpFechaModificarDuplicada.getEditor().setText("01-01-2000");
        cbTurnoModificar.getSelectionModel().selectFirst();
        comboBoxModificar();
        fechasInicializar();
    }

    private void limpiarCamposEliminar(){
        textBuscarLegajoEmpleado.setText("");
        labIDCronogramaEliminar.setText("");
        labIDEmpleadoEliminar.setText("");
        labNombreEmpleadoEliminar.setText("");
        labApellidoEmpleadoEliminar.setText("");
        labLegajoEmpleadoEliminar.setText("");
        cbTurnoEliminar.getSelectionModel().selectFirst();
        comboBoxEliminar();
        fechasInicializar();
    }

    private void limpiarCamposCronograma(){
        labIDCronogramaModificar.setText("");
        cbTurnoModificar.getSelectionModel().selectFirst();
        comboBoxModificar();

        labIDCronogramaEliminar.setText("");
        cbTurnoEliminar.getSelectionModel().selectFirst();
        comboBoxEliminar();
        fechasInicializarMyE();
    }

    // ---------------------------------------- Fechas Actuales Inicializadas ----------------------------------------
    public void fechasInicializar() {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        // Obtener la fecha actual
        Calendar calendar = Calendar.getInstance();
        // Sumar un día a la fecha actual
        calendar.add(Calendar.DATE, 1);
        Date fechaActual = calendar.getTime();
        String fecha_Actual_Mas1 = (formatoFecha.format(fechaActual));

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

        dpFechaCrear.setValue(LocalDate.of(Integer.parseInt(fecha_Actual_Mas1.substring(0, 4)), Integer.parseInt(fecha_Actual_Mas1.substring(5, 7)), Integer.parseInt(fecha_Actual_Mas1.substring(8, 10))));
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
        labFechaCrearSeleccionada.setText(fecha_Actual_Mas1.substring(0, 4) + "-" + fecha_Actual_Mas1.substring(5, 7) + "-" + fecha_Actual_Mas1.substring(8, 10));

        dpFechaModificar.setValue(LocalDate.of(Integer.parseInt(fecha_Actual_Mas1.substring(0, 4)), Integer.parseInt(fecha_Actual_Mas1.substring(5, 7)), Integer.parseInt(fecha_Actual_Mas1.substring(8, 10))));
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

        dpFechaEliminar.setValue(LocalDate.of(Integer.parseInt(fecha_Actual_Mas1.substring(0, 4)), Integer.parseInt(fecha_Actual_Mas1.substring(5, 7)), Integer.parseInt(fecha_Actual_Mas1.substring(8, 10))));
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

    private void fechasInicializarMyE(){
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        // Obtener la fecha actual
        Calendar calendar = Calendar.getInstance();
        // Sumar un día a la fecha actual
        calendar.add(Calendar.DATE, 1);
        Date fechaActual = calendar.getTime();
        String fecha_Actual_Mas1 = (formatoFecha.format(fechaActual));

        dpFechaModificar.setValue(LocalDate.of(Integer.parseInt(fecha_Actual_Mas1.substring(0, 4)), Integer.parseInt(fecha_Actual_Mas1.substring(5, 7)), Integer.parseInt(fecha_Actual_Mas1.substring(8, 10))));
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

        dpFechaEliminar.setValue(LocalDate.of(Integer.parseInt(fecha_Actual_Mas1.substring(0, 4)), Integer.parseInt(fecha_Actual_Mas1.substring(5, 7)), Integer.parseInt(fecha_Actual_Mas1.substring(8, 10))));
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

    public void fechaCalendario(){
        dpFechaCalendario.setValue(LocalDate.now());
        dpFechaCalendario.setConverter(new StringConverter<LocalDate>() {
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

    public void fechaConsultaLicencia(){
        dpBuscarFechaInicioConsulta.setValue(LocalDate.now());
        dpBuscarFechaInicioConsulta.setConverter(new StringConverter<LocalDate>() {
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
