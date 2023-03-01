package com.secadero.controlador;

import com.secadero.modelo.ausencia.LeerAusencia;
import com.secadero.modelo.cronograma.CrearCronograma;
import com.secadero.modelo.cronograma.LeerCronograma;
import com.secadero.modelo.empleados.LeerEmpleado;
import com.secadero.modelo.licencias.CrearLicencia;
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
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

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
    private TableColumn<LeerEmpleado, String> colApellido;
    @FXML
    private TableColumn<LeerEmpleado, String> colApellidoEmpleadoCrear;
    @FXML
    private TableColumn<LeerEmpleado, String> colEmailEmpleadoCrear;
    @FXML
    private TableColumn<LeerCronograma, Date> colFecha;
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
    private TableColumn<LeerEmpleado, String> colTelefono;
    @FXML
    private TableColumn<LeerEmpleado, String> colTelefonoEmpleadoCrear;
    @FXML
    private TableColumn<LeerCronograma, String> colTurno;
    @FXML
    private DatePicker dpBuscarFecha;
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
    private Label labErrorHoraEntradaCrear;
    @FXML
    private Label labErrorHoraSalidaCrear;
    @FXML
    private TabPane panelPestaniasCronograma;
    @FXML
    private Tab tabCalendario;
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
    private TableView<LeerCronograma> tablaCronograma;
    @FXML
    private TableView<?> tablaEmpleados;
    @FXML
    private TableView<LeerEmpleado> tablaListaEmpleados;
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
    private ListView<String> listEmpleadosAgregados;
    @FXML
    private Label labNombreEmpleadoAgregadoCrear;
    @FXML
    private Label labApellidoEmpleadoAgregadoCrear;

    ObservableList<LeerEmpleado> listTablaEmpleadosDisponible;
    int index = -1;

    ObservableList<LeerCronograma> listCronograma;
    int index2 = -1;

    ObservableList<LeerEmpleado> listEmpleado;
    int index3 = -1;
    int index4 = -1;

    // -------------------------------------------- Inicialización ----------------------------------------------
    public void initialize() throws ParseException {
        String[] tipoFiltro = {"Nombre", "Legajo", "Fecha", "Turno"};
        String[] tipoTurnos = {"Dia", "Noche"};
        comboBoxCrear();
        comboBoxModificar();
        comboBoxEliminar();
        cbTiposFiltrosCronograma.getItems().addAll(tipoFiltro);
        cbTiposFiltrosCronograma.getSelectionModel().selectFirst();
        cbTurnoCrear.getItems().addAll(tipoTurnos);
        cbTurnoCrear.getSelectionModel().selectFirst();
        cbTurnoModificar.getItems().addAll(tipoTurnos);
        cbTurnoModificar.getSelectionModel().selectFirst();
        cbTurnoEliminar.getItems().addAll(tipoTurnos);
        cbTurnoEliminar.getSelectionModel().selectFirst();
        fechasInicializar();
        relojEmpleadoDisponible();
        inicializarTablaListaEmpleadosDisponible();
        inicializarTablaEmpleado();
    }

    // ----------------------------------------- Tabla de Empleados ---------------------------------------------
    public void inicializarTablaListaEmpleadosDisponible(){
        colNombre.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, String>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, String>("apellido"));
        colLegajo.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, Integer>("legajo"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, String>("telefono"));
        colIDEmpleado.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, Integer>("idempleados"));

        listTablaEmpleadosDisponible = LeerEmpleado.listaEmpleadoDisponible();
        tablaListaEmpleados.getColumns().setAll(colNombre, colApellido, colLegajo, colTelefono, colIDEmpleado);
        tablaListaEmpleados.getItems().setAll(listTablaEmpleadosDisponible);
    }

    // ----------------------------------------- Tabla de Cronograma ---------------------------------------------
    public void inicializarTablaCronograma(){
        colFecha.setCellValueFactory(new PropertyValueFactory<LeerCronograma, Date>("fecha"));
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
        colTelefonoEmpleadoCrear.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, String>("telefono"));
        colEmailEmpleadoCrear.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, String>("email"));
        colIDEmpleadoCrear.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, Integer>("idempleados"));

        listEmpleado = LeerEmpleado.listaEmpleadoDisponible();
        tabEmpleadosCrear.getColumns().setAll(colNombreEmpleadoCrear, colApellidoEmpleadoCrear, colLegajoEmpleadoCrear, colTelefonoEmpleadoCrear, colEmailEmpleadoCrear, colIDEmpleadoCrear);
        tabEmpleadosCrear.getItems().setAll(listEmpleado);
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
        dpFechaEliminar.getEditor().setText(colFecha.getCellData(index2).toString());
        textHoraEntradaModificar.setText(colHoraEntrada.getCellData(index2));
        textHoraEntradaEliminar.setText(colHoraEntrada.getCellData(index2));
        textHoraSalidaModificar.setText(colHoraSalida.getCellData(index2));
        textHoraSalidaEliminar.setText(colHoraSalida.getCellData(index2));
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
            textHoraSalidaCrear.setText("00:00");
        } else if(valorActual.equals("Noche")){
            textHoraEntradaCrear.setText("00:00");
            textHoraSalidaCrear.setText("12:00");
        }
    }

    public void cambioDeItem2(ObservableValue<? extends String> observable, String valorAntiguo, String valorActual){
        if(valorActual.equals("Dia")){
            textHoraEntradaModificar.setText("12:00");
            textHoraSalidaModificar.setText("00:00");
        } else if(valorActual.equals("Noche")){
            textHoraEntradaModificar.setText("00:00");
            textHoraSalidaModificar.setText("12:00");
        }
    }

    public void cambioDeItem3(ObservableValue<? extends String> observable, String valorAntiguo, String valorActual){
        if(valorActual.equals("Dia")){
            textHoraEntradaEliminar.setText("12:00");
            textHoraSalidaEliminar.setText("00:00");
        } else if(valorActual.equals("Noche")){
            textHoraEntradaEliminar.setText("00:00");
            textHoraSalidaEliminar.setText("12:00");
        }
    }

    // --------------------------------------------- Búsqueda y Filtros -----------------------------------------
    @FXML
    private void btnBuscarEmpleadoDisponible() {
        ObservableList<LeerEmpleado> listBuscarEmpleado;
        listBuscarEmpleado = LeerEmpleado.buscarEmpleadoDisponible(textBuscarLegajoEmpleado);
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
    private void btnBuscarCronograma() {
        ObservableList<LeerCronograma> listaBuscarCronograma;
        listaBuscarCronograma = LeerCronograma.buscarFechaCronograma(labIDEmpleadoLista, dpBuscarFecha);
        tablaCronograma.getItems().setAll(listaBuscarCronograma);
    }

    @FXML
    private void filtroCronograma() {
        String dato = cbTiposFiltrosCronograma.getSelectionModel().getSelectedItem().toLowerCase();
        if(dato.equals("fecha") || dato.equals("turno")){
            ObservableList<LeerCronograma> listFiltros;
            listFiltros = LeerCronograma.filtroCronograma(cbTiposFiltrosCronograma, labIDEmpleadoLista);
            tablaCronograma.getItems().setAll(listFiltros);
        } else {
            ObservableList<LeerEmpleado> listFiltrosEmpleados;
            listFiltrosEmpleados = LeerEmpleado.filtroEmpleadoDisponible(cbTiposFiltrosCronograma);
            tablaListaEmpleados.getItems().setAll(listFiltrosEmpleados);
        }
    }

    @FXML
    private void actualizarTabla() {
        labIDCronogramaLista.setText("0");
        labIDEmpleadoLista.setText("0");
        textBuscarLegajoEmpleado.setText("");
        tablaListaEmpleados.getItems().setAll(listTablaEmpleadosDisponible);
        cbTiposFiltrosCronograma.getSelectionModel().selectFirst();
        inicializarTablaCronograma();
        limpiarCamposModificar();
        limpiarCamposEliminar();
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
        String fechaI = dpFechaCrear.getEditor().getText();
        Date fecha = formatoFecha.parse(fechaI);

        Date fechaActualPais = new Date();
        String Fecha_Actual = (formatoFecha.format(fechaActualPais));
        Date fechaActual = formatoFecha.parse(Fecha_Actual);
        long Diferencias2 = fechaActual.getTime() - fecha.getTime();
        long Cant_Dias2 = Diferencias2 / (1000 * 60 * 60 * 24);


        if(listEmpleadosAgregados.getItems().size() != 0){
            if((-Cant_Dias2) > 0){
                CrearCronograma cronogramaCrear = new CrearCronograma();
                cronogramaCrear.agregarCronograma(listEmpleadosAgregados, dpFechaCrear, cbTurnoCrear, textHoraEntradaCrear, textHoraSalidaCrear, labLimpiarCamposCrear);

                if(Objects.equals(labLimpiarCamposCrear.getText(), "OK")){
                    labLimpiarCamposCrear.setText("");
                    labIDCronogramaLista.setText("0");
                    labIDEmpleadoLista.setText("0");
                    inicializarTablaEmpleado();
                    inicializarTablaCronograma();
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
    private void modificar() {
        limpiarCamposModificar();
    }

    @FXML
    private void eliminar() {
        limpiarCamposEliminar();
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

    //----------------------------------------- Limpiador de Campos ----------------------------------------------
    private void limpiarCamposCrear(){
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
    }

    private void limpiarCamposModificar(){
        labIDCronogramaModificar.setText("");
        labIDEmpleadoModificar.setText("");
        labNombreEmpleadoModificar.setText("");
        labApellidoEmpleadoModificar.setText("");
        labLegajoEmpleadoModificar.setText("");
        labLimpiarCamposModificar.setText("");
        cbTurnoModificar.getSelectionModel().selectFirst();
        comboBoxModificar();

        //dpFechaModificarDuplicada.getEditor().setText("");
        fechasInicializar();
    }

    private void limpiarCamposEliminar(){
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
        fechasInicializar();
    }

    // ---------------------------------------- Fechas Actuales Inicializadas ----------------------------------------
    public void fechasInicializar() {
        dpBuscarFecha.setValue(LocalDate.now());
        dpBuscarFecha.setConverter(new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate localDate) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
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
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
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
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
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
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
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
