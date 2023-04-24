package com.secadero.controlador;

import com.secadero.modelo.ausencia.CrearAusencia;
import com.secadero.modelo.ausencia.EliminarAusencia;
import com.secadero.modelo.ausencia.LeerAusencia;
import com.secadero.modelo.ausencia.ModificarAusencia;
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
    private ComboBox<String> cbTiposFiltrosAusencias;
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
    private TableColumn<LeerAusencia, Date> colFecha;
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
    private TextField textBuscarLegajoEmpleadoCrear;

    ObservableList<LeerEmpleado> listTablaEmpleados;
    int index = -1;

    ObservableList<LeerAusencia> listAusencia;
    int index2 = -1;

    ObservableList<LeerEmpleado> listEmpleadoAusencia;
    int index3 = -1;

    // -------------------------------------------- Inicialización ----------------------------------------------
    public void initialize() {
        String[] tipoFiltro = {"Nombre", "Legajo", "DNI", "Fecha"};
        cbTiposFiltrosAusencias.getItems().addAll(tipoFiltro);
        cbTiposFiltrosAusencias.getSelectionModel().selectFirst();
        inicializarTablaListaEmpleados();
        inicializarTablaEmpleado();
        getJustificadoCrear();
        getJustificadoModificar();
        getCertificadoCrear();
        getCertificadoModificar();
        fechasInicializar();
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
        colFecha.setCellValueFactory(new PropertyValueFactory<LeerAusencia, Date>("fecha"));
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

        dpFechaModificar.getEditor().setText(colFecha.getCellData(index2).toString());
        dpFechaModificarDuplicada.getEditor().setText(colFecha.getCellData(index2).toString());
        textMotivoModificar.setText(colMotivo.getCellData(index2));
        labSeleccionJustificacion.setText(colJustificado.getCellData(index2));
        labSeleccionCertificado.setText(colCertificado.getCellData(index2));
        labIDAusenciaModificar.setText(colIDAusencia.getCellData(index2).toString());

        dpFechaEliminar.getEditor().setText(colFecha.getCellData(index2).toString());
        textMotivoEliminar.setText(colMotivo.getCellData(index2));
        labJustificadoEliminar.setText(colJustificado.getCellData(index2));
        labCertificadoEliminar.setText(colCertificado.getCellData(index2));
        labIDAusenciaEliminar.setText(colIDAusencia.getCellData(index2).toString());
        comprobacionRadioBotomSeleccion();
        comprobacionRadioBotomSeleccionC();

        labIDAusenciaLista.setText(colIDAusencia.getCellData(index2).toString());
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
    private void filtroAusencia() {
        String dato = cbTiposFiltrosAusencias.getSelectionModel().getSelectedItem().toLowerCase();
        if(dato.equals("fecha")){
            ObservableList<LeerAusencia> listFiltros;
            listFiltros = LeerAusencia.filtroAusencia(cbTiposFiltrosAusencias, labIDEmpleadoLista);
            tablaAusencias.getItems().setAll(listFiltros);
        } else {
            ObservableList<LeerEmpleado> listFiltrosEmpleados;
            listFiltrosEmpleados = LeerEmpleado.filtroEmpleadoGeneral(cbTiposFiltrosAusencias);
            tablaListaEmpleados.getItems().setAll(listFiltrosEmpleados);
        }
    }

    @FXML
    private void btnBuscarEmpleadoCrear(){
        ObservableList<LeerEmpleado> listBuscarEmpleadoCrear;
        listBuscarEmpleadoCrear = LeerEmpleado.buscarEmpleadoGeneralCrear(textBuscarLegajoEmpleadoCrear);
        tabEmpleadosCrear.getItems().setAll(listBuscarEmpleadoCrear);
    }

    @FXML
    private void actualizarTabla(){
        labIDAusenciaLista.setText("0");
        labIDEmpleadoLista.setText("0");
        textBuscarLegajoEmpleado.setText("");
        tablaListaEmpleados.getItems().setAll(listEmpleadoAusencia);
        cbTiposFiltrosAusencias.getSelectionModel().selectFirst();
        inicializarTablaAusencias();
        limpiarCamposModificar();
        limpiarCamposEliminar();
    }

    //---------------------------------------------- Eventos Importantes ----------------------------------------
    @FXML
    private void guardar() {
        TextArea[] campoMotivo = {textMotivoCrear};
        Label[] campoIDEmpleado = {labIDEmpleadoCrear};
        if(comprobarIDCrearEmpleado(campoIDEmpleado)){
            if(comprobarValoresCrear(campoMotivo)){
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
            } else {
                Alert alerta = new Alert(Alert.AlertType.WARNING);
                alerta.setTitle("Error");
                alerta.setContentText("Debes de completar el campo [Motivo] y que no sea corto");
                alerta.showAndWait();
            }
        } else {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Error");
            alerta.setContentText("Para guardar debes de tomar un empleado");
            alerta.showAndWait();
        }
    }

    @FXML
    private void modificar() {
        TextArea[] campoMotivo = {textMotivoModificar};
        Label[] campoIDAusencia = {labIDAusenciaModificar};
        if(comprobarIDModificarAusencia(campoIDAusencia)){
            if(comprobarValoresModificar(campoMotivo)){
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
            } else {
                Alert alerta = new Alert(Alert.AlertType.WARNING);
                alerta.setTitle("Error");
                alerta.setContentText("Debes de completar el campo [Motivo] y que no sea corto");
                alerta.showAndWait();
            }
        } else {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Error!");
            alerta.setContentText("Debe de seleccionar antes una Ausencia de dicho empleado para Modificarlo");
            alerta.showAndWait();
        }
    }

    @FXML
    private void eliminar() {
        Label[] id = {labIDAusenciaEliminar};
        if (comprobarIDEliminarAusencia(id)){
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
        } else{
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Error!");
            alerta.setContentText("Debe de seleccionar antes una Ausencia de dicho empleado para Eliminarlo");
            alerta.showAndWait();
            btnRegresarELista.requestFocus();
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

    //---------------------------------------- Comprobación de Campos -------------------------------------------
    private boolean comprobarValoresCrear(TextArea[] campos){
        boolean valido = true;
        for (int i = 0; i < campos.length; i++) {
            String valor = campos[i].getText();
            if(valor == null || textMotivoCrear.getLength() < 10){
                valido = false;
            }
        }
        return valido;
    }

    private boolean comprobarValoresModificar(TextArea[] campos){
        boolean valido = true;
        for (int i = 0; i < campos.length; i++) {
            String valor = campos[i].getText();
            if(valor == null || textMotivoModificar.getLength() < 10){
                valido = false;
            }
        }
        return valido;
    }

    private boolean comprobarIDCrearEmpleado(Label[] id){
        boolean valido = true;
        for (int i = 0; i < id.length; i++) {
            String valor = id[i].getText();
            if(valor == null || valor.trim().isEmpty()){
                valido = false;
            }
        }
        return valido;
    }

    private boolean comprobarIDModificarAusencia(Label[] id){
        boolean valido = true;
        for (int i = 0; i < id.length; i++) {
            String valor = id[i].getText();
            if(valor == null || valor.trim().isEmpty()){
                valido = false;
            }
        }
        return valido;
    }

    private boolean comprobarIDEliminarAusencia(Label[] id){
        boolean valido = true;
        for (int i = 0; i < id.length; i++) {
            String valor = id[i].getText();
            if(valor == null || valor.trim().isEmpty()){
                valido = false;
            }
        }
        return valido;
    }

    //----------------------------------------- Limpiador de Campos ----------------------------------------------
    private void limpiarCamposCrear(){
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
