package com.secadero.controlador;

import com.secadero.modelo.ausencia.EliminarAusencia;
import com.secadero.modelo.ausencia.LeerAusencia;
import com.secadero.modelo.empleados.LeerEmpleado;
import com.secadero.modelo.licencias.CrearLicencia;
import com.secadero.modelo.licencias.EliminarLicencia;
import com.secadero.modelo.licencias.LeerLicencia;
import com.secadero.modelo.licencias.ModificarLicencia;
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
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

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
    private TableColumn<LeerEmpleado, String> colApellido;
    @FXML
    private TableColumn<LeerEmpleado, String> colApellidoEmpleadoCrear;
    @FXML
    private TableColumn<LeerEmpleado, String> colEmailEmpleadoCrear;
    @FXML
    private TableColumn<LeerLicencia, Date> colFechaFin;
    @FXML
    private TableColumn<LeerLicencia, Date> colFechaInicio;
    @FXML
    private TableColumn<LeerEmpleado, Integer> colIDEmpleado;
    @FXML
    private TableColumn<LeerEmpleado, Integer> colIDEmpleadoCrear;
    @FXML
    private TableColumn<LeerLicencia, Integer> colIDLicencia;
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
    private DatePicker dpFechaModificarDuplicadaInicio;
    @FXML
    private DatePicker dpFechaModificarDuplicadaFin;
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
    private Label labIDLicenciaLista;
    @FXML
    private Label labResultadoID;
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
    private TableView<LeerEmpleado> tablaListaEmpleados;
    @FXML
    private TableView<LeerLicencia> tablaLicencia;
    @FXML
    private TextField textBuscarLegajoEmpleado;

    ObservableList<LeerEmpleado> listTablaEmpleados;
    int index = -1;

    ObservableList<LeerLicencia> listLicencia;
    int index2 = -1;

    ObservableList<LeerEmpleado> listEmpleadoLicencia;
    int index3 = -1;

    // -------------------------------------------- Inicialización ----------------------------------------------
    public void initialize() {
        String[] tipoFiltro = {"Nombre", "Legajo", "Fecha_Inicio", "Fecha_Fin", "Tipo_Licencia"};
        cbTiposFiltrosLicencia.getItems().addAll(tipoFiltro);
        cbTiposFiltrosLicencia.getSelectionModel().selectFirst();
        inicializarTablaListaEmpleados();
        inicializarTablaEmpleado();
        inicializarComboBoxBD();
        fechasInicializar();
        cbTipoLicenciaCrear.getSelectionModel().getSelectedItem();
        cbTipoLicenciaCrear.getSelectionModel().selectedItemProperty().addListener(this::cambioDeItem);
    }

    // ----------------------------------------- Tabla de Empleados ---------------------------------------------
    public void inicializarTablaListaEmpleados(){
        colNombre.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, String>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, String>("apellido"));
        colLegajo.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, Integer>("legajo"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, String>("telefono"));
        colIDEmpleado.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, Integer>("idempleados"));

        listTablaEmpleados = LeerEmpleado.listaEmpleadoGeneral();
        tablaListaEmpleados.getColumns().setAll(colNombre, colApellido, colLegajo, colTelefono, colIDEmpleado);
        tablaListaEmpleados.getItems().setAll(listTablaEmpleados);
    }

    // ----------------------------------------- Tabla de Licencias ---------------------------------------------
    public void inicializarTablaLicencias(){
        colFechaInicio.setCellValueFactory(new PropertyValueFactory<LeerLicencia, Date>("fecha_Inicio"));
        colFechaFin.setCellValueFactory(new PropertyValueFactory<LeerLicencia, Date>("fecha_Fin"));
        colTipoLicencia.setCellValueFactory(new PropertyValueFactory<LeerLicencia, String>("descripcion"));
        colIDLicencia.setCellValueFactory(new PropertyValueFactory<LeerLicencia, Integer>("idLicencias"));

        listLicencia = LeerLicencia.listaLicencia(labIDEmpleadoLista);
        tablaLicencia.getColumns().setAll(colFechaInicio, colFechaFin, colTipoLicencia, colIDLicencia);
        tablaLicencia.getItems().setAll(listLicencia);
    }

    // -------------------------------------- Tabla de Empleados Crear ------------------------------------------
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

        labIDLicenciaLista.setText("0");
        labIDEmpleadoLista.setText(colIDEmpleado.getCellData(index).toString());
        inicializarTablaLicencias();

        LeerLicencia licenciaLeer = new LeerLicencia();
        licenciaLeer.listaLicenciaComprobacion(labIDEmpleadoLista, labIDLicenciaLista, labResultadoID);
        if(labResultadoID.getText().equals("NO")){
            limpiarCamposLicencias();
            labDiasDisponiblesModificar.setText("");
            labDiasDisponiblesEliminar.setText("");
        }
    }

    @FXML
    private void getLicencia(){
        index2 = tablaLicencia.getSelectionModel().getSelectedIndex();
        if (index2 <= -1){

            return;
        }

        labIDLicenciaModificar.setText(colIDLicencia.getCellData(index2).toString());
        dpFechaInicioModificar.getEditor().setText(colFechaInicio.getCellData(index2).toString());
        dpFechaFinModificar.getEditor().setText(colFechaFin.getCellData(index2).toString());
        cbTipoLicenciaModificar.getSelectionModel().select(colTipoLicencia.getCellData(index2));
        dpFechaModificarDuplicadaInicio.getEditor().setText(colFechaInicio.getCellData(index2).toString());
        dpFechaModificarDuplicadaFin.getEditor().setText(colFechaFin.getCellData(index2).toString());
        comboBoxModificar();

        labIDLicenciaEliminar.setText(colIDLicencia.getCellData(index2).toString());
        dpFechaInicioEliminar.getEditor().setText(colFechaInicio.getCellData(index2).toString());
        dpFechaFinEliminar.getEditor().setText(colFechaFin.getCellData(index2).toString());
        cbTipoLicenciaEliminar.getSelectionModel().select(colTipoLicencia.getCellData(index2));
        comboBoxEliminar();

        labIDLicenciaLista.setText(colIDLicencia.getCellData(index2).toString());
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
        comboBoxCrear();
    }

    // ------------------------ Combo Boxes actualiza los Dias Disponibles Automáticamente ------------------------
    public void comboBoxCrear(){
        String item = cbTipoLicenciaCrear.getSelectionModel().getSelectedItem();
        cbTipoLicenciaCrear.getSelectionModel().selectedItemProperty().addListener(this::cambioDeItem);
        LeerLicencia licenciaLeer = new LeerLicencia();
        String dato = licenciaLeer.comboBoxTipoLicencia(item, labIDEmpleadoCrear, dpFechaInicioCrear);
        labDiasDisponiblesCrear.setText(dato);
    }

    public void comboBoxModificar(){
        String item = cbTipoLicenciaModificar.getSelectionModel().getSelectedItem();
        cbTipoLicenciaModificar.getSelectionModel().selectedItemProperty().addListener(this::cambioDeItem2);
        LeerLicencia licenciaLeer = new LeerLicencia();
        String dato = licenciaLeer.comboBoxTipoLicencia(item, labIDEmpleadoModificar, dpFechaInicioModificar);
        labDiasDisponiblesModificar.setText(dato);
    }

    public void comboBoxEliminar(){
        String item = cbTipoLicenciaEliminar.getSelectionModel().getSelectedItem();
        cbTipoLicenciaEliminar.getSelectionModel().selectedItemProperty().addListener(this::cambioDeItem2);
        LeerLicencia licenciaLeer = new LeerLicencia();
        String dato = licenciaLeer.comboBoxTipoLicencia(item, labIDEmpleadoEliminar, dpFechaInicioEliminar);
        labDiasDisponiblesEliminar.setText(dato);
    }

    public void cambioDeItem(ObservableValue<? extends String> observable, String valorAntiguo, String valorActual){
        LeerLicencia licenciaLeer = new LeerLicencia();
        String dato = licenciaLeer.comboBoxTipoLicencia(valorActual, labIDEmpleadoCrear, dpFechaInicioCrear);

        labDiasDisponiblesCrear.setText(dato);
    }

    public void cambioDeItem2(ObservableValue<? extends String> observable, String valorAntiguo, String valorActual){
        LeerLicencia licenciaLeer = new LeerLicencia();
        String dato = licenciaLeer.comboBoxTipoLicencia(valorActual, labIDEmpleadoModificar, dpFechaInicioModificar);

        labDiasDisponiblesModificar.setText(dato);
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
    private void btnBuscarEmpleado(){
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
    private void btnBuscarLicencia(){
        ObservableList<LeerLicencia> listaBuscarLicencia;
        listaBuscarLicencia = LeerLicencia.buscarLicenciaFechaInicio(labIDEmpleadoLista, dpBuscarFechaInicio);
        tablaLicencia.getItems().setAll(listaBuscarLicencia);
    }


    @FXML
    private void filtroLicencia() {
        String dato = cbTiposFiltrosLicencia.getSelectionModel().getSelectedItem().toLowerCase();
        if(dato.equals("fecha_inicio") || dato.equals("fecha_fin") || dato.equals("tipo_licencia")){
            ObservableList<LeerLicencia> listFiltros;
            listFiltros = LeerLicencia.filtroLicencia(cbTiposFiltrosLicencia, labIDEmpleadoLista);
            tablaLicencia.getItems().setAll(listFiltros);
        } else {
            ObservableList<LeerEmpleado> listFiltrosEmpleados;
            listFiltrosEmpleados = LeerEmpleado.filtroEmpleadoGeneral(cbTiposFiltrosLicencia);
            tablaListaEmpleados.getItems().setAll(listFiltrosEmpleados);
        }
    }

    @FXML
    private void actualizarTabla() {
        labIDLicenciaLista.setText("0");
        labIDEmpleadoLista.setText("0");
        textBuscarLegajoEmpleado.setText("");
        tablaListaEmpleados.getItems().setAll(listEmpleadoLicencia);
        cbTiposFiltrosLicencia.getSelectionModel().selectFirst();
        inicializarTablaLicencias();
        limpiarCamposModificar();
        limpiarCamposEliminar();
    }

    //---------------------------------------------- Eventos Importantes ----------------------------------------
    @FXML
    private void guardar() throws ParseException, SQLException {
        Label[] campoIDEmpleado = {labIDEmpleadoCrear};
        if(comprobarIDCrearEmpleado(campoIDEmpleado)){
            CrearLicencia licenciaCrear = new CrearLicencia();
            licenciaCrear.agregarLicencia(labIDEmpleadoCrear, labDiasDisponiblesCrear, dpFechaInicioCrear, dpFechaFinCrear, cbTipoLicenciaCrear, labLimpiarCamposCrear);

            if(Objects.equals(labLimpiarCamposCrear.getText(), "OK")){
                labLimpiarCamposCrear.setText("");
                labIDLicenciaLista.setText("0");
                labIDEmpleadoLista.setText("0");
                inicializarTablaEmpleado();
                inicializarTablaLicencias();
                regresarCLista();
                limpiarCamposCrear();
            }
        } else {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Error");
            alerta.setContentText("Para guardar debes de tomar un empleado");
            alerta.showAndWait();
        }
    }

    @FXML
    private void modificar() throws SQLException, ParseException {
        Label[] campoIDEmpleado = {labIDLicenciaModificar};
        if(comprobarIDModificarLicencia(campoIDEmpleado)){
            ModificarLicencia licenciaModificar = new ModificarLicencia();
            licenciaModificar.modificarLicencia(labIDEmpleadoModificar, labIDLicenciaModificar, labDiasDisponiblesModificar, dpFechaModificarDuplicadaInicio, dpFechaModificarDuplicadaFin, dpFechaInicioModificar, dpFechaFinModificar, cbTipoLicenciaModificar, labLimpiarCamposModificar);

            if(Objects.equals(labLimpiarCamposModificar.getText(), "OK")){
                labLimpiarCamposModificar.setText("");
                labIDLicenciaLista.setText("0");
                labIDEmpleadoLista.setText("0");
                inicializarTablaEmpleado();
                inicializarTablaLicencias();
                regresarCLista();
                limpiarCamposModificar();
                limpiarCamposEliminar();
            }

        } else {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Error");
            alerta.setContentText("Para Modificar debes de tomar una Licencia del empleado");
            alerta.showAndWait();
        }
    }

    @FXML
    private void eliminar() throws ParseException {
        Label[] id = {labIDLicenciaEliminar};
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        String fechaI = dpFechaInicioEliminar.getEditor().getText();
        Date fechaInicio = formatoFecha.parse(fechaI);
        String fechaF = dpFechaFinEliminar.getEditor().getText();
        Date fechaFin = formatoFecha.parse(fechaF);

        Date fechaActualPais = new Date();
        String Fecha_Actual = (formatoFecha.format(fechaActualPais));
        Date fechaActual = formatoFecha.parse(Fecha_Actual);
        long DiferenciasInicio = fechaActual.getTime() - fechaInicio.getTime();
        long Cant_DiasInicio = DiferenciasInicio / (1000 * 60 * 60 * 24);
        long DiferenciasFin= fechaActual.getTime() - fechaFin.getTime();
        long Cant_DiasFin= DiferenciasFin/ (1000 * 60 * 60 * 24);

        if (comprobarIDEliminarLicencia(id)){
            if((-Cant_DiasInicio) >= 0 && (-Cant_DiasFin) >= 0){
                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setTitle("Confirmar Eliminar");
                alerta.setContentText("¿Desea Eliminar la Licencia del Empleado?");
                Optional<ButtonType> resultado = alerta.showAndWait();
                if (resultado.isPresent() && resultado.get() == ButtonType.OK){

                    EliminarLicencia licenciaEliminar = new EliminarLicencia();
                    licenciaEliminar.eliminarLicencia(labIDLicenciaEliminar, labIDEmpleadoEliminar, dpFechaInicioEliminar, dpFechaFinEliminar, cbTipoLicenciaEliminar);
                    labIDLicenciaLista.setText("0");
                    labIDEmpleadoLista.setText("0");
                    inicializarTablaListaEmpleados();
                    inicializarTablaLicencias();
                    limpiarCamposEliminar();
                    limpiarCamposModificar();
                    regresarELista();
                }
            } else {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error de Eliminar");
                alerta.setContentText("La Licencia que desea eliminar en este empleado ya no está" + "\n" + "permitido.  Motivo: es una Fecha Pasada o ya se encuentra" + "\n" + "con Licencia");
                alerta.showAndWait();

            }
        } else{
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Error!");
            alerta.setContentText("Debe de seleccionar antes una Licencia de dicho empleado para Eliminarlo");
            alerta.showAndWait();
            btnRegresarELista.requestFocus();
        }
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
        labDiasDisponiblesCrear.setText("");
        fechasInicializar();
    }

    private void limpiarCamposModificar(){
        labIDLicenciaModificar.setText("");
        labIDEmpleadoModificar.setText("");
        labNombreEmpleadoModificar.setText("");
        labApellidoEmpleadoModificar.setText("");
        labLegajoEmpleadoModificar.setText("");
        labLimpiarCamposModificar.setText("");
        cbTipoLicenciaModificar.getSelectionModel().selectFirst();
        labDiasDisponiblesModificar.setText("");
        dpFechaModificarDuplicadaInicio.getEditor().setText("");
        dpFechaModificarDuplicadaFin.getEditor().setText("");
        fechasInicializar();
    }

    private void limpiarCamposEliminar(){
        labIDLicenciaEliminar.setText("");
        labIDEmpleadoEliminar.setText("");
        labNombreEmpleadoEliminar.setText("");
        labApellidoEmpleadoEliminar.setText("");
        labLegajoEmpleadoEliminar.setText("");
        cbTipoLicenciaEliminar.getSelectionModel().selectFirst();
        labDiasDisponiblesEliminar.setText("");
        fechasInicializar();
    }

    private void limpiarCamposLicencias(){
        labIDLicenciaModificar.setText("");
        dpFechaModificarDuplicadaInicio.getEditor().setText("");
        dpFechaModificarDuplicadaFin.getEditor().setText("");
        cbTipoLicenciaModificar.getSelectionModel().selectFirst();
        labDiasDisponiblesModificar.setText("");

        labIDLicenciaEliminar.setText("");
        cbTipoLicenciaEliminar.getSelectionModel().selectFirst();
        labDiasDisponiblesEliminar.setText("");
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
