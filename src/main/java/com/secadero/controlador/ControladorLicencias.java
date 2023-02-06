package com.secadero.controlador;

import com.secadero.modelo.ausencia.CrearAusencia;
import com.secadero.modelo.ausencia.LeerAusencia;
import com.secadero.modelo.empleados.LeerEmpleado;
import com.secadero.modelo.empleados.leerComboBoxes.GeneroEmpleado;
import com.secadero.modelo.licencias.CrearLicencia;
import com.secadero.modelo.licencias.LeerLicencia;
import com.secadero.modelo.licencias.leerComboBoxes.TipoLicencia;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

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
    private ComboBox<String> cbTipoLicenciaCrear;
    @FXML
    private ComboBox<String> cbTipoLicenciaEliminar;
    @FXML
    private ComboBox<String> cbTipoLicenciaModificar;
    @FXML
    private ComboBox<String> cbTiposFiltrosLicencia;
    @FXML
    private TableColumn<LeerLicencia, String> colApellido;
    @FXML
    private TableColumn<LeerEmpleado, String> colApellidoEmpleadoCrear;
    @FXML
    private TableColumn<LeerEmpleado, String> colEmailEmpleadoCrear;
    @FXML
    private TableColumn<LeerLicencia, Date> colFechaFin;
    @FXML
    private TableColumn<LeerLicencia, Date> colFechaInicio;
    @FXML
    private TableColumn<LeerLicencia, Integer> colIDEmpleado;
    @FXML
    private TableColumn<LeerEmpleado, Integer> colIDEmpleadoCrear;
    @FXML
    private TableColumn<LeerLicencia, Integer> colIDLicencia;
    @FXML
    private TableColumn<LeerLicencia, Integer> colLegajo;
    @FXML
    private TableColumn<LeerEmpleado, Integer> colLegajoEmpleadoCrear;
    @FXML
    private TableColumn<LeerLicencia, String> colNombre;
    @FXML
    private TableColumn<LeerEmpleado, String> colNombreEmpleadoCrear;
    @FXML
    private TableColumn<LeerLicencia, String> colTelefono;
    @FXML
    private TableColumn<LeerEmpleado, String> colTelefonoEmpleadoCrear;
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
    private TableView<LeerEmpleado> tabEmpleadosCrear;
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

    ObservableList<LeerEmpleado> listEmpleadoLicencia;
    int index2 = -1;

    // -------------------------------------------- Inicialización ----------------------------------------------
    public void initialize() {
        String[] tipoFiltro = {"Nombre", "Legajo", "Fecha_Inicio", "Fecha_Fin", "Tipo_Licencia"};
        cbTiposFiltrosLicencia.getItems().addAll(tipoFiltro);
        cbTiposFiltrosLicencia.getSelectionModel().selectFirst();
        inicializarTabla();
        inicializarTablaEmpleado();
        inicializarComboBoxBD();
        fechasInicializar();
        cbTipoLicenciaCrear.getSelectionModel().getSelectedItem();
        cbTipoLicenciaCrear.getSelectionModel().selectedItemProperty().addListener(this::cambioDeItem);
    }

    public void comboBoxCrear(){
        String item = cbTipoLicenciaCrear.getSelectionModel().getSelectedItem();
        cbTipoLicenciaCrear.getSelectionModel().selectedItemProperty().addListener(this::cambioDeItem);
        LeerLicencia licenciaLeer = new LeerLicencia();
        String dato = licenciaLeer.comboBoxTipoLicencia(item, labIDEmpleadoCrear);
        labDiasDisponiblesCrear.setText(dato);
    }

    public void comboBoxModificar(){
        String item = cbTipoLicenciaModificar.getSelectionModel().getSelectedItem();
        cbTipoLicenciaModificar.getSelectionModel().selectedItemProperty().addListener(this::cambioDeItem2);
        LeerLicencia licenciaLeer = new LeerLicencia();
        String dato = licenciaLeer.comboBoxTipoLicencia(item, labIDEmpleadoModificar);
        labDiasDisponiblesModificar.setText(dato);
    }

    public void comboBoxEliminar(){
        String item = cbTipoLicenciaEliminar.getSelectionModel().getSelectedItem();
        cbTipoLicenciaEliminar.getSelectionModel().selectedItemProperty().addListener(this::cambioDeItem2);
        LeerLicencia licenciaLeer = new LeerLicencia();
        String dato = licenciaLeer.comboBoxTipoLicencia(item, labIDEmpleadoEliminar);
        labDiasDisponiblesEliminar.setText(dato);
    }

    public void cambioDeItem(ObservableValue<? extends String> observable, String valorAntiguo, String valorActual){
        LeerLicencia licenciaLeer = new LeerLicencia();
        String dato = licenciaLeer.comboBoxTipoLicencia(valorActual, labIDEmpleadoCrear);

        labDiasDisponiblesCrear.setText(dato);
    }

    public void cambioDeItem2(ObservableValue<? extends String> observable, String valorAntiguo, String valorActual){
        LeerLicencia licenciaLeer = new LeerLicencia();
        String dato = licenciaLeer.comboBoxTipoLicencia(valorActual, labIDEmpleadoModificar);

        labDiasDisponiblesModificar.setText(dato);
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

    public void inicializarTablaEmpleado(){
        colNombreEmpleadoCrear.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, String>("nombre"));
        colApellidoEmpleadoCrear.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, String>("apellido"));
        colLegajoEmpleadoCrear.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, Integer>("legajo"));
        colTelefonoEmpleadoCrear.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, String>("telefono"));
        colEmailEmpleadoCrear.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, String>("email"));
        colIDEmpleadoCrear.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, Integer>("idempleados"));

        listEmpleadoLicencia = LeerEmpleado.listaEmpleadoGeneral();
        tabEmpleadosCrear.getColumns().setAll(colNombreEmpleadoCrear, colApellidoEmpleadoCrear, colLegajoEmpleadoCrear, colTelefonoEmpleadoCrear, colEmailEmpleadoCrear, colIDEmpleadoCrear);
        tabEmpleadosCrear.getItems().setAll(listEmpleadoLicencia);
    }

    @FXML
    private void getLicencia(){
        index = tablaLicencia.getSelectionModel().getSelectedIndex();
        if (index <= -1){

            return;
        }

        labIDLicenciaModificar.setText(colIDLicencia.getCellData(index).toString());
        labIDEmpleadoModificar.setText(colIDEmpleado.getCellData(index).toString());
        labNombreEmpleadoModificar.setText(colNombre.getCellData(index));
        labApellidoEmpleadoModificar.setText(colApellido.getCellData(index));
        labLegajoEmpleadoModificar.setText(colLegajo.getCellData(index).toString());
        dpFechaInicioModificar.getEditor().setText(colFechaInicio.getCellData(index).toString());
        dpFechaFinModificar.getEditor().setText(colFechaFin.getCellData(index).toString());
        cbTipoLicenciaModificar.getSelectionModel().select(colTipoLicencia.getCellData(index));
        comboBoxModificar();

        labIDLicenciaEliminar.setText(colIDLicencia.getCellData(index).toString());
        labIDEmpleadoEliminar.setText(colIDEmpleado.getCellData(index).toString());
        labNombreEmpleadoEliminar.setText(colNombre.getCellData(index));
        labApellidoEmpleadoEliminar.setText(colApellido.getCellData(index));
        labLegajoEmpleadoEliminar.setText(colLegajo.getCellData(index).toString());
        dpFechaInicioEliminar.getEditor().setText(colFechaInicio.getCellData(index).toString());
        dpFechaFinEliminar.getEditor().setText(colFechaFin.getCellData(index).toString());
        cbTipoLicenciaEliminar.getSelectionModel().select(colTipoLicencia.getCellData(index));
        comboBoxEliminar();
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

        comboBoxCrear();
    }

    public void inicializarComboBoxBD() {
        // --------------------- Tipo de Licencia -----------------------
        String datoTipo_Licencia;
        TipoLicencia tipoLienciaEmpleado = new TipoLicencia();
        ObservableList<TipoLicencia> empleado_TipoLicencia = tipoLienciaEmpleado.getTipo_Licencia();
        for (int i = 0; i < empleado_TipoLicencia.size(); i++) {
            datoTipo_Licencia = empleado_TipoLicencia.get(i).getDescripcion();
            cbTipoLicenciaCrear.getItems().addAll(datoTipo_Licencia);
            cbTipoLicenciaModificar.getItems().addAll(datoTipo_Licencia);
            cbTipoLicenciaEliminar.getItems().addAll(datoTipo_Licencia);
        }
        cbTipoLicenciaCrear.getSelectionModel().selectFirst();
        cbTipoLicenciaModificar.getSelectionModel().selectFirst();
        cbTipoLicenciaEliminar.getSelectionModel().selectFirst();
    }

    // --------------------------------------------- Búsqueda y Filtros -----------------------------------------

    @FXML
    private void btnBuscar() {
        ObservableList<LeerLicencia> listBuscarAmbos;
        listBuscarAmbos = LeerLicencia.buscarLicencia(textBuscarLicenciaLegajoE, dpBuscarFechaInicio);
        if(textBuscarLicenciaLegajoE.getText().equals("")){
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Error!");
            alerta.setContentText("Debe de completar el Legajo para Buscar");
            alerta.showAndWait();
        } else {
            tablaLicencia.getItems().setAll(listBuscarAmbos);
        }
    }

    @FXML
    private void filtroLicencia() {
        ObservableList<LeerLicencia> listFiltros;
        listFiltros = LeerLicencia.filtroLicencia(cbTiposFiltrosLicencia);
        tablaLicencia.getItems().setAll(listFiltros);
    }

    @FXML
    private void actualizarTabla() {
        tablaLicencia.getItems().setAll(listLicencia);
    }

    //---------------------------------------------- Eventos Importantes ----------------------------------------
    @FXML
    private void guardar() throws ParseException {
        Label[] campoIDEmpleado = {labIDEmpleadoCrear};
        if(comprobarIDCrearEmpleado(campoIDEmpleado)){
            CrearLicencia licenciaCrear = new CrearLicencia();
            licenciaCrear.agregarLicencia(labIDEmpleadoCrear, dpFechaInicioCrear, dpFechaFinCrear, cbTipoLicenciaCrear, labLimpiarCamposCrear);

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
            alerta.setContentText("Para guardar debes de tomar un empleado");
            alerta.showAndWait();
        }
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

    //---------------------------------------- Comprobación de Campos -------------------------------------------
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

    private boolean comprobarIDModificarLicencia(Label[] id){
        boolean valido = true;
        for (int i = 0; i < id.length; i++) {
            String valor = id[i].getText();
            if(valor == null || valor.trim().isEmpty()){
                valido = false;
            }
        }
        return valido;
    }

    private boolean comprobarIDEliminarLicencia(Label[] id){
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
        cbTipoLicenciaCrear.getSelectionModel().selectFirst();
        labLimpiarCamposCrear.setText("");
        fechasInicializar();
    }

    private void limpiarCamposModificar(){
        labIDLicenciaModificar.setText("");
        labIDEmpleadoModificar.setText("");
        labNombreEmpleadoModificar.setText("");
        labApellidoEmpleadoModificar.setText("");
        labLegajoEmpleadoModificar.setText("");
        cbTipoLicenciaModificar.getSelectionModel().selectFirst();
        labLimpiarCamposModificar.setText("");
        fechasInicializar();
    }

    private void limpiarCamposEliminar(){
        labIDLicenciaEliminar.setText("");
        labIDEmpleadoEliminar.setText("");
        labNombreEmpleadoEliminar.setText("");
        labApellidoEmpleadoEliminar.setText("");
        labLegajoEmpleadoEliminar.setText("");
        cbTipoLicenciaEliminar.getSelectionModel().selectFirst();
        fechasInicializar();
    }

    // ---------------------------------------- Fechas Actuales Inicializadas ----------------------------------------
    public void fechasInicializar() {
        dpBuscarFechaInicio.setValue(LocalDate.now());
        dpBuscarFechaInicio.setConverter(new StringConverter<LocalDate>() {
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

        dpFechaInicioCrear.setValue(LocalDate.now());
        dpFechaInicioCrear.setConverter(new StringConverter<LocalDate>() {
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

        dpFechaFinCrear.setValue(LocalDate.now());
        dpFechaFinCrear.setConverter(new StringConverter<LocalDate>() {
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

        dpFechaInicioModificar.setValue(LocalDate.now());
        dpFechaInicioModificar.setConverter(new StringConverter<LocalDate>() {
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

        dpFechaFinModificar.setValue(LocalDate.now());
        dpFechaFinModificar.setConverter(new StringConverter<LocalDate>() {
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

        dpFechaInicioEliminar.setValue(LocalDate.now());
        dpFechaInicioEliminar.setConverter(new StringConverter<LocalDate>() {
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

        dpFechaFinEliminar.setValue(LocalDate.now());
        dpFechaFinEliminar.setConverter(new StringConverter<LocalDate>() {
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
