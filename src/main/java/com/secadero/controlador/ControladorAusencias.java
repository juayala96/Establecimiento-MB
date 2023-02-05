package com.secadero.controlador;

import com.secadero.modelo.ausencia.CrearAusencia;
import com.secadero.modelo.ausencia.EliminarAusencia;
import com.secadero.modelo.ausencia.LeerAusencia;
import com.secadero.modelo.ausencia.ModificarAusencia;
import com.secadero.modelo.empleados.CrearEmpleado;
import com.secadero.modelo.empleados.EliminarEmpleado;
import com.secadero.modelo.empleados.LeerEmpleado;
import com.secadero.modelo.usuarios.LeerUsuario;
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
    private TableColumn<LeerAusencia, String> colApellido;
    @FXML
    private TableColumn<LeerEmpleado, Integer> colLegajoEmpleadoCrear;
    @FXML
    private TableColumn<LeerEmpleado, String> colApellidoEmpleadoCrear;
    @FXML
    private TableColumn<LeerAusencia, String> colCertificado;
    @FXML
    private TableColumn<LeerEmpleado, String> colEmailEmpleadoCrear;
    @FXML
    private TableColumn<LeerAusencia, Date> colFecha;
    @FXML
    private TableColumn<LeerAusencia, Integer> colIDAusencia;
    @FXML
    private TableColumn<LeerAusencia, Integer> colIDEmpleado;
    @FXML
    private TableColumn<LeerEmpleado, Integer> colIDEmpleadoCrear;
    @FXML
    private TableColumn<LeerAusencia, String> colJustificado;
    @FXML
    private TableColumn<LeerAusencia, Integer> colLegajo;
    @FXML
    private TableColumn<LeerAusencia, String> colMotivo;
    @FXML
    private TableColumn<LeerAusencia, String> colNombre;
    @FXML
    private TableColumn<LeerEmpleado, String> colNombreEmpleadoCrear;
    @FXML
    private TableColumn<LeerEmpleado, String> colTelefonoEmpleadoCrear;
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
    private Label labSeleccionJustificacion;
    @FXML
    private TabPane panelPestaniasAusencias;
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
    private TableView<LeerAusencia> tablaAusencias;
    @FXML
    private ToggleGroup justificadoCrear;
    @FXML
    private ToggleGroup justificadoModificar;
    @FXML
    private TextField textBuscarAusenciaLegajoE;
    @FXML
    private TextField textCertificadoCrear;
    @FXML
    private TextField textCertificadoEliminar;
    @FXML
    private TextField textCertificadoModificar;
    @FXML
    private TextArea textMotivoCrear;
    @FXML
    private TextArea textMotivoEliminar;
    @FXML
    private TextArea textMotivoModificar;

    ObservableList<LeerAusencia> listAusencia;
    int index = -1;

    ObservableList<LeerEmpleado> listEmpleadoAusencia;
    int index2 = -1;

    // -------------------------------------------- Inicialización ----------------------------------------------
    public void initialize() {
        String[] tipoFiltro = {"Nombre", "Legajo", "Fecha"};
        cbTiposFiltrosAusencias.getItems().addAll(tipoFiltro);
        cbTiposFiltrosAusencias.getSelectionModel().selectFirst();
        inicializarTabla();
        inicializarTablaEmpleado();
        getJustificadoCrear();
        getJustificadoModificar();
        fechasInicializar();
    }

    public void inicializarTabla(){
        colNombre.setCellValueFactory(new PropertyValueFactory<LeerAusencia, String>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<LeerAusencia, String>("apellido"));
        colLegajo.setCellValueFactory(new PropertyValueFactory<LeerAusencia, Integer>("legajo"));
        colFecha.setCellValueFactory(new PropertyValueFactory<LeerAusencia, Date>("fecha"));
        colMotivo.setCellValueFactory(new PropertyValueFactory<LeerAusencia, String>("motivo"));
        colJustificado.setCellValueFactory(new PropertyValueFactory<LeerAusencia, String>("justificado"));
        colCertificado.setCellValueFactory(new PropertyValueFactory<LeerAusencia, String>("certificado"));
        colIDAusencia.setCellValueFactory(new PropertyValueFactory<LeerAusencia, Integer>("idausencias"));
        colIDEmpleado.setCellValueFactory(new PropertyValueFactory<LeerAusencia, Integer>("idEmpleadoFK"));

        listAusencia = LeerAusencia.listaAusencia();
        tablaAusencias.getColumns().setAll(colNombre, colApellido, colLegajo, colFecha, colMotivo, colJustificado, colCertificado, colIDAusencia, colIDEmpleado);
        tablaAusencias.getItems().setAll(listAusencia);
    }

    public void inicializarTablaEmpleado(){
        colNombreEmpleadoCrear.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, String>("nombre"));
        colApellidoEmpleadoCrear.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, String>("apellido"));
        colLegajoEmpleadoCrear.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, Integer>("legajo"));
        colTelefonoEmpleadoCrear.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, String>("telefono"));
        colEmailEmpleadoCrear.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, String>("email"));
        colIDEmpleadoCrear.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, Integer>("idempleados"));

        listEmpleadoAusencia = LeerEmpleado.listaEmpleadoGeneral();
        tabEmpleadosCrear.getColumns().setAll(colNombreEmpleadoCrear, colApellidoEmpleadoCrear, colLegajoEmpleadoCrear, colTelefonoEmpleadoCrear, colEmailEmpleadoCrear, colIDEmpleadoCrear);
        tabEmpleadosCrear.getItems().setAll(listEmpleadoAusencia);
    }

    // ------------------------------------- Tomo todos los datos al Seleccionar en la tabla ------------------------
    @FXML
    private void getAusencia(){
        index = tablaAusencias.getSelectionModel().getSelectedIndex();
        if (index <= -1){

            return;
        }

        labNombreEmpleadoModificar.setText(colNombre.getCellData(index));
        labApellidoEmpleadoModificar.setText(colApellido.getCellData(index));
        labLegajoEmpleadoModificar.setText(colLegajo.getCellData(index).toString());
        dpFechaModificar.getEditor().setText(colFecha.getCellData(index).toString());
        dpFechaModificarDuplicada.getEditor().setText(colFecha.getCellData(index).toString());
        textMotivoModificar.setText(colMotivo.getCellData(index));
        labSeleccionJustificacion.setText(colJustificado.getCellData(index));
        textCertificadoModificar.setText(colCertificado.getCellData(index));
        labIDEmpleadoModificar.setText(colIDEmpleado.getCellData(index).toString());
        labIDAusenciaModificar.setText(colIDAusencia.getCellData(index).toString());

        labNombreEmpleadoEliminar.setText(colNombre.getCellData(index));
        labApellidoEmpleadoEliminar.setText(colApellido.getCellData(index));
        labLegajoEmpleadoEliminar.setText(colLegajo.getCellData(index).toString());
        dpFechaEliminar.getEditor().setText(colFecha.getCellData(index).toString());
        textMotivoEliminar.setText(colMotivo.getCellData(index));
        labJustificadoEliminar.setText(colJustificado.getCellData(index));
        textCertificadoEliminar.setText(colCertificado.getCellData(index));
        labIDEmpleadoEliminar.setText(colIDEmpleado.getCellData(index).toString());
        labIDAusenciaEliminar.setText(colIDAusencia.getCellData(index).toString());

        comprobacionRadioBotomSeleccion();

    }

    @FXML
    private void getEmpleadoCrear(){
        index2 = tabEmpleadosCrear.getSelectionModel().getSelectedIndex();
        if (index2 <= -1){

            return;
        }
        labIDEmpleadoCrear.setText(colIDEmpleadoCrear.getCellData(index2).toString());
        labNombreEmpleadoCrear.setText(colNombreEmpleadoCrear.getCellData(index2));
        labApellidoEmpleadoCrear.setText(colApellidoEmpleadoCrear.getCellData(index2));
        labLegajoEmpleadoCrear.setText(colLegajoEmpleadoCrear.getCellData(index2).toString());
    }

    // --------------------------------------------- Búsqueda y Filtros -----------------------------------------
    @FXML
    private void btnBuscar() {
        ObservableList<LeerAusencia> listBuscarAmbos;
        listBuscarAmbos = LeerAusencia.buscarAusenciaAmbos(textBuscarAusenciaLegajoE, dpBuscarFecha);
        if(textBuscarAusenciaLegajoE.getText().equals("")){
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Error!");
            alerta.setContentText("Debe de completar el Legajo para Buscar");
            alerta.showAndWait();
        } else {
            tablaAusencias.getItems().setAll(listBuscarAmbos);
        }
    }

    @FXML
    private void filtroAusencia() {
        ObservableList<LeerAusencia> listFiltros;
        listFiltros = LeerAusencia.filtroAusencia(cbTiposFiltrosAusencias);
        tablaAusencias.getItems().setAll(listFiltros);
    }

    @FXML
    private void actualizarTabla(){
        tablaAusencias.getItems().setAll(listAusencia);
    }

    //---------------------------------------------- Eventos Importantes ----------------------------------------
    @FXML
    private void guardar() {
        TextArea[] campoMotivo = {textMotivoCrear};
        Label[] campoIDEmpleado = {labIDEmpleadoCrear};
        if(comprobarIDCrearEmpleado(campoIDEmpleado)){
            if(comprobarValoresCrear(campoMotivo)){
                CrearAusencia ausenciaCrear = new CrearAusencia();
                ausenciaCrear.agregarAusencia(labIDEmpleadoCrear, dpFechaCrear, textMotivoCrear, labJustificacionCrear, textCertificadoCrear, labLimpiarCamposCrear);

                if(Objects.equals(labLimpiarCamposCrear.getText(), "OK")){
                    labLimpiarCamposCrear.setText("");
                    inicializarTabla();
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
                ausenciaModificar.modificarAusencia(labIDEmpleadoModificar, labIDAusenciaModificar, dpFechaModificar, dpFechaModificarDuplicada, textMotivoModificar, labJustificacionModificar, textCertificadoModificar, labLimpiarCamposModificar);

                if(Objects.equals(labLimpiarCamposModificar.getText(), "OK")){
                    labLimpiarCamposModificar.setText("");
                    dpFechaModificarDuplicada.getEditor().setText("");
                    inicializarTabla();
                    regresarCLista();
                    limpiarCamposModificar();
                    limpiarCamposEliminar();
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
            alerta.setContentText("Debe de seleccionar antes una Ausencia de un empleado para Modificarlo");
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
                inicializarTabla();

                limpiarCamposEliminar();
                limpiarCamposModificar();
            }
        } else{
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Error!");
            alerta.setContentText("Debe de seleccionar antes una Ausencia de un empleado para Eliminarlo");
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

    private void comprobacionRadioBotomSeleccion(){
        if(labSeleccionJustificacion.equals("NO")){
            rbJustificadoNoModificar.fire();
        } else{
            rbJustificadoSiModificar.fire();
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
        textCertificadoCrear.setText("");
        labLimpiarCamposCrear.setText("");
        getJustificadoCrear();
        fechasInicializar();
    }

    private void limpiarCamposModificar(){
        labIDEmpleadoModificar.setText("");
        labNombreEmpleadoModificar.setText("");
        labApellidoEmpleadoModificar.setText("");
        labLegajoEmpleadoModificar.setText("");
        textMotivoModificar.setText("");
        rbJustificadoNoModificar.fire();
        textCertificadoModificar.setText("");
        labLimpiarCamposModificar.setText("");
        labIDAusenciaModificar.setText("");
        getJustificadoModificar();
        fechasInicializar();
    }

    private void limpiarCamposEliminar(){
        labIDEmpleadoEliminar.setText("");
        labNombreEmpleadoEliminar.setText("");
        labApellidoEmpleadoEliminar.setText("");
        labLegajoEmpleadoEliminar.setText("");
        textMotivoEliminar.setText("");
        labJustificadoEliminar.setText("");
        textCertificadoEliminar.setText("");
        labIDAusenciaEliminar.setText("");
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
