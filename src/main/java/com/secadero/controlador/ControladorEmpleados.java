package com.secadero.controlador;

import com.secadero.modelo.empleados.CrearEmpleado;
import com.secadero.modelo.empleados.EliminarEmpleado;
import com.secadero.modelo.empleados.leerComboBoxes.*;
import com.secadero.modelo.empleados.LeerEmpleado;
import com.secadero.modelo.empleados.ModificarEmpleado;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;


import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

public class ControladorEmpleados {
    public ControladorEmpleados(){}

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
    private ComboBox<String> cbBuscarEmpleado;
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
    private TableColumn<LeerEmpleado, Integer> colDNI;
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
    private Label labErrorApellidoCrear;
    @FXML
    private Label labErrorApellidoModificar;
    @FXML
    private Label labErrorDireccionCrear;
    @FXML
    private Label labErrorDireccionModificar;
    @FXML
    private Label labErrorEmailCrear;
    @FXML
    private Label labErrorEmailModificar;
    @FXML
    private Label labErrorFechaNacimientoCrear;
    @FXML
    private Label labErrorFechaNacimientoModificar;
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
    private Label labInformacionModificarLegajo;
    @FXML
    private Label labInformacionModificarDNI;
    @FXML
    private Label labLimpiarCamposCrear;
    @FXML
    private Label labLimpiarCamposModificar;
    @FXML
    private Label labErrorDNICrear;
    @FXML
    private Label labErrorDNIModificar;
    @FXML
    private Label labErrorFechaIngresoCrear;
    @FXML
    private Label labErrorFechaIngresoModificar;
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
    private TextField textEmailCrear;
    @FXML
    private TextField textEmailEliminar;
    @FXML
    private TextField textEmailModificar;
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
    @FXML
    private TextField textDNICrear;
    @FXML
    private TextField textDNIModificar;
    @FXML
    private TextField textDNIEliminar;

    ObservableList<LeerEmpleado> listEmpleado;
    int index = -1;

    // -------------------------------------------- Inicialización ----------------------------------------------
    public void initialize() {
        String[] tipoFiltro = {"Nombre", "Apellido", "Legajo", "DNI", "Area", "Puesto"};
        cbTiposfiltrosEmpleados.getItems().addAll(tipoFiltro);
        cbTiposfiltrosEmpleados.getSelectionModel().selectFirst();
        String[] tipoBuscar = {"Legajo", "Nombre", "Apellido"};
        cbBuscarEmpleado.getItems().addAll(tipoBuscar);
        cbBuscarEmpleado.getSelectionModel().selectFirst();
        inicializarComboBoxBD();
        inicializarTabla();
        fechasInicializar();

        cbBuscarEmpleado.setOnAction(event -> {
            String selectedItem = cbBuscarEmpleado.getSelectionModel().getSelectedItem();
            textBuscarEmpleado.setPromptText("Ingrese el " + selectedItem);
        });
    }

    public void inicializarTabla(){
        colNombre.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, String>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, String>("apellido"));
        colLegajo.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, Integer>("legajo"));
        colDNI.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, Integer>("dni"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, String>("telefono"));
        colFechaIngreso.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, Date>("fechaIngreso"));
        colArea.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, String>("idAreaFK"));
        colPuesto.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, String>("idPuestoFK"));
        colID.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, Integer>("idempleados"));

        listEmpleado = LeerEmpleado.listaEmpleados();
        tablaEmpleados.getColumns().setAll(colNombre, colApellido, colLegajo, colDNI, colTelefono, colFechaIngreso, colArea, colPuesto, colID);
        tablaEmpleados.getItems().setAll(listEmpleado);
    }

    @FXML
    private void getSelecciono() {
        index = tablaEmpleados.getSelectionModel().getSelectedIndex();
        if (index <= -1) {

            return;
        }

        labIDModificar.setText(colID.getCellData(index).toString());
        LeerEmpleado empleadoSeleccionado = new LeerEmpleado();
        empleadoSeleccionado.listaEmpleadoSeleccionadoM(textNombreModificar, textApellidoModificar, textLegajoModificar, textDNIModificar,textTelefonoModificar, textDireccionModificar, textEmailModificar, cbGeneroModificar, cbEstadoCivilModificar, dpFechaNaciminetoModificar, cbGrupoSanguineoModificar, dpFechaIngresoModificar, cbAreaModificar, cbPuestoModificar, labIDModificar, labInformacionModificarLegajo, labInformacionModificarDNI);

        labIDEliminar.setText(colID.getCellData(index).toString());
        LeerEmpleado empleadoSeleccion = new LeerEmpleado();
        empleadoSeleccion.listaEmpleadoSeleccionadoE(textNombreEliminar, textApellidoEliminar, textLegajoEliminar, textDNIEliminar, textTelefonoEliminar, textDireccionEliminar, textEmailEliminar, cbGeneroEliminar, cbEstadoCivilEliminar, dpFechaNaciminetoEliminar, cbGrupoSanguineoEliminar, dpFechaIngresoEliminar, cbAreaEliminar, cbPuestoEliminar, labIDEliminar);

        String fechaNacimientoM = dpFechaNaciminetoModificar.getEditor().getText();
        dpFechaNaciminetoModificar.setValue(LocalDate.of(Integer.parseInt(fechaNacimientoM.substring(6, 10)), Integer.parseInt(fechaNacimientoM.substring(3, 5)), Integer.parseInt(fechaNacimientoM.substring(0, 2))));
        String fechaIngresoM = dpFechaIngresoModificar.getEditor().getText();
        dpFechaIngresoModificar.setValue(LocalDate.of(Integer.parseInt(fechaIngresoM.substring(6, 10)), Integer.parseInt(fechaIngresoM.substring(3, 5)), Integer.parseInt(fechaIngresoM.substring(0, 2))));
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
        limpiarCamposGeneralizada();
        ObservableList<LeerEmpleado> listBuscar;
        listBuscar = LeerEmpleado.buscarEmpleado(textBuscarEmpleado, cbBuscarEmpleado);

        if(!textBuscarEmpleado.getText().equals("")){
            tablaEmpleados.getItems().setAll(listBuscar);
            labIDModificar.setText("");
            labIDEliminar.setText("");
        }
    }

    @FXML
    private void filtroEmpleado() {
        if(textBuscarEmpleado.getText().equals("")){
            limpiarCamposGeneralizada();
            ObservableList<LeerEmpleado> listFiltros;
            listFiltros = LeerEmpleado.filtroEmpleado(cbTiposfiltrosEmpleados);
            tablaEmpleados.getItems().setAll(listFiltros);
            labIDModificar.setText("");
            labIDEliminar.setText("");
        } else {
            limpiarCamposGeneralizada();
            ObservableList<LeerEmpleado> listFiltros2;
            listFiltros2 = LeerEmpleado.filtroEmpleadoBusqueda(cbTiposfiltrosEmpleados, textBuscarEmpleado);
            tablaEmpleados.getItems().setAll(listFiltros2);
            labIDModificar.setText("");
            labIDEliminar.setText("");
        }
    }

    @FXML
    private void actualizarTabla(){
        textBuscarEmpleado.setText("");
        limpiarCamposGeneralizada();
        cbTiposfiltrosEmpleados.getSelectionModel().selectFirst();
        tablaEmpleados.getItems().setAll(listEmpleado);
        inicializarTabla();
    }

    //---------------------------------------------- Eventos Importantes ----------------------------------------
    @FXML
    private void guardar() throws SQLException, ParseException {
        String fechaNacimientoClave = dpFechaNaciminetoCrear.getEditor().getText();
        String fechaAnio = fechaNacimientoClave.substring(6, 10);
        String fechaMes = fechaNacimientoClave.substring(3, 5);
        String fechaDia = fechaNacimientoClave.substring(0, 2);

        LocalDate fechaActual = LocalDate.now();
        // Obtener la fecha ingresada (ejemplo: 2020-01-01)
        LocalDate fechaIngresada = LocalDate.of(Integer.parseInt(fechaAnio), Integer.parseInt(fechaMes), Integer.parseInt(fechaDia));
        // Calcular la diferencia en años
        int diferenciaAnios = Period.between(fechaIngresada, fechaActual).getYears();

        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        String fechaIngresoClave = dpFechaIngresoCrear.getEditor().getText();
        String fechaAnio2 = fechaIngresoClave.substring(6, 10);
        String fechaMes2 = fechaIngresoClave.substring(3, 5);
        String fechaDia2 = fechaIngresoClave.substring(0, 2);
        String fechaModificadaInicio = (fechaAnio2 + "-" + fechaMes2 + "-" + fechaDia2);

        String fechaI = fechaModificadaInicio;
        Date fechaInicio = formatoFecha.parse(fechaI);
        Date fechaActualPais = new Date();
        String Fecha_Actual = (formatoFecha.format(fechaActualPais));
        Date fechaActual2 = formatoFecha.parse(Fecha_Actual);
        long Diferencias2 = fechaActual2.getTime() - fechaInicio.getTime();
        long Cant_Dias2 = Diferencias2 / (1000 * 60 * 60 * 24);

        camposObligatoriosCrear();
        if(Objects.equals(labErrorNombreCrear.getText(), "") && Objects.equals(labErrorApellidoCrear.getText(), "") && Objects.equals(labErrorLegajoCrear.getText(), "") && Objects.equals(labErrorDNICrear.getText(), "") && Objects.equals(labErrorTelefonoCrear.getText(), "") && Objects.equals(labErrorDireccionCrear.getText(), "") && Objects.equals(labErrorEmailCrear.getText(), "")){
            validacionCamposCaracteresCrear();
            if(Objects.equals(labErrorNombreCrear.getText(), "") && Objects.equals(labErrorApellidoCrear.getText(), "") && Objects.equals(labErrorLegajoCrear.getText(), "") && Objects.equals(labErrorDNICrear.getText(), "") && Objects.equals(labErrorTelefonoCrear.getText(), "") && Objects.equals(labErrorDireccionCrear.getText(), "") && Objects.equals(labErrorEmailCrear.getText(), "")){
                validacionCamposLongitudCrear();
                if(Objects.equals(labErrorNombreCrear.getText(), "") && Objects.equals(labErrorApellidoCrear.getText(), "") && Objects.equals(labErrorLegajoCrear.getText(), "") && Objects.equals(labErrorDNICrear.getText(), "") && Objects.equals(labErrorTelefonoCrear.getText(), "") && Objects.equals(labErrorDireccionCrear.getText(), "") && Objects.equals(labErrorEmailCrear.getText(), "")){
                    if(diferenciaAnios >= 18){
                        labErrorFechaNacimientoCrear.setText("");
                        if((-Cant_Dias2) <= 0){
                            labErrorFechaIngresoCrear.setText("");
                            CrearEmpleado empleadoCrear = new CrearEmpleado();
                            empleadoCrear.agregarEmpleado(textNombreCrear, textApellidoCrear, textLegajoCrear, textDNICrear, textTelefonoCrear, textDireccionCrear, textEmailCrear, cbGeneroCrear, cbEstadoCivilCrear, dpFechaNaciminetoCrear, cbGrupoSanguineoCrear, dpFechaIngresoCrear, cbAreaCrear, cbPuestoCrear, labLimpiarCamposCrear);

                            if(Objects.equals(labLimpiarCamposCrear.getText(), "OK")){
                                labLimpiarCamposCrear.setText("");
                                inicializarTabla();
                                vaciarComboBox();
                                inicializarComboBoxBD();
                                regresarCLista();
                                limpiarCamposCrear();
                            }
                        } else {
                            labErrorFechaIngresoCrear.setText("No esta disponible una fecha futuro");
                        }
                    } else {
                        labErrorFechaNacimientoCrear.setText("No esta permitido menores de edad");
                    }
                }
            }
        }
    }

    @FXML
    private void modificar() throws SQLException, ParseException {
        String fechaNacimientoClave = dpFechaNaciminetoModificar.getEditor().getText();
        String fechaAnio = fechaNacimientoClave.substring(6, 10);
        String fechaMes = fechaNacimientoClave.substring(3, 5);
        String fechaDia = fechaNacimientoClave.substring(0, 2);
        String fechaModificada = (fechaAnio + "-" + fechaMes + "-" + fechaDia);

        LocalDate fechaActual = LocalDate.now();
        // Obtener la fecha ingresada (ejemplo: 2020-01-01)
        LocalDate fechaIngresada = LocalDate.of(Integer.parseInt(fechaAnio), Integer.parseInt(fechaMes), Integer.parseInt(fechaDia));
        // Calcular la diferencia en años
        int diferenciaAnios = Period.between(fechaIngresada, fechaActual).getYears();

        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        String fechaIngresoClave = dpFechaIngresoModificar.getEditor().getText();
        String fechaAnio2 = fechaIngresoClave.substring(6, 10);
        String fechaMes2 = fechaIngresoClave.substring(3, 5);
        String fechaDia2 = fechaIngresoClave.substring(0, 2);
        String fechaModificadaInicio = (fechaAnio2 + "-" + fechaMes2 + "-" + fechaDia2);

        String fechaI = fechaModificadaInicio;
        Date fechaInicio = formatoFecha.parse(fechaI);
        Date fechaActualPais = new Date();
        String Fecha_Actual = (formatoFecha.format(fechaActualPais));
        Date fechaActual2 = formatoFecha.parse(Fecha_Actual);
        long Diferencias2 = fechaActual2.getTime() - fechaInicio.getTime();
        long Cant_Dias2 = Diferencias2 / (1000 * 60 * 60 * 24);

        if(!labIDModificar.getText().equals("")) {
            camposObligatoriosModificar();
            if (Objects.equals(labErrorNombreModificar.getText(), "") && Objects.equals(labErrorApellidoModificar.getText(), "") && Objects.equals(labErrorLegajoModificar.getText(), "") && Objects.equals(labErrorDNIModificar.getText(), "") && Objects.equals(labErrorTelefonoModificar.getText(), "") && Objects.equals(labErrorDireccionModificar.getText(), "") && Objects.equals(labErrorEmailModificar.getText(), "")) {
                validacionCamposCaracteresModificar();
                if (Objects.equals(labErrorNombreModificar.getText(), "") && Objects.equals(labErrorApellidoModificar.getText(), "") && Objects.equals(labErrorLegajoModificar.getText(), "") && Objects.equals(labErrorDNIModificar.getText(), "") && Objects.equals(labErrorTelefonoModificar.getText(), "") && Objects.equals(labErrorDireccionModificar.getText(), "") && Objects.equals(labErrorEmailModificar.getText(), "")) {
                    validacionCamposLongitudModificar();
                    if (Objects.equals(labErrorNombreModificar.getText(), "") && Objects.equals(labErrorApellidoModificar.getText(), "") && Objects.equals(labErrorLegajoModificar.getText(), "") && Objects.equals(labErrorDNIModificar.getText(), "") && Objects.equals(labErrorTelefonoModificar.getText(), "") && Objects.equals(labErrorDireccionModificar.getText(), "") && Objects.equals(labErrorEmailModificar.getText(), "")) {
                        if(diferenciaAnios >= 18){
                            labErrorFechaNacimientoModificar.setText("");
                            if((-Cant_Dias2) <= 0){
                                labErrorFechaIngresoModificar.setText("");
                                ModificarEmpleado empleadoModificar = new ModificarEmpleado();
                                empleadoModificar.modificarEmpleado(textNombreModificar, textApellidoModificar, textLegajoModificar, textDNIModificar, textTelefonoModificar, textDireccionModificar, textEmailModificar, cbGeneroModificar, cbEstadoCivilModificar, dpFechaNaciminetoModificar, cbGrupoSanguineoModificar, dpFechaIngresoModificar, cbAreaModificar, cbPuestoModificar, labLimpiarCamposModificar, labInformacionModificarLegajo, labInformacionModificarDNI, labIDModificar);

                                if(Objects.equals(labLimpiarCamposModificar.getText(), "OK")){
                                    inicializarTabla();
                                    vaciarComboBox();
                                    inicializarComboBoxBD();
                                    regresarCLista();
                                    limpiarCamposModificar();
                                    limpiarCamposEliminar();
                                }
                            } else {
                                labErrorFechaIngresoModificar.setText("No esta disponible una fecha futuro");
                            }
                        } else {
                            labErrorFechaNacimientoModificar.setText("No esta permitido menores de edad");
                        }
                    }
                }
            }
        } else {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("");
            alerta.setContentText("Debe de seleccionar antes un Empleado para Modificarlo");
            Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/icono_Alerta.png")));
            Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
            stage.getIcons().add(icon);
            alerta.showAndWait();
        }
    }

    @FXML
    private void eliminar() {
        Label[] id = {labIDEliminar};
        if (!labIDEliminar.getText().equals("")){
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle("");
            alerta.setContentText("¿Desea Eliminar el Empleado?");
            Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/icono_Alerta.png")));
            Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
            stage.getIcons().add(icon);
            Optional<ButtonType> resultado = alerta.showAndWait();
            if (resultado.isPresent() && resultado.get() == ButtonType.OK){

                EliminarEmpleado empleadoEliminar = new EliminarEmpleado();
                empleadoEliminar.eliminarEmpleado(labIDEliminar);
                inicializarTabla();

                limpiarCamposEliminar();
                limpiarCamposModificar();
                regresarCLista();
            }
        } else{
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("");
            alerta.setContentText("Debe de seleccionar antes un Empleado para Eliminarlo");
            Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/icono_Alerta.png")));
            Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
            stage.getIcons().add(icon);
            alerta.showAndWait();
            btnRegresarELista.requestFocus();
        }
    }

    //------------------------------------ Acciones Simples de los Botones --------------------------------------
    @FXML
    private void crearEmpleado() {
        SingleSelectionModel<Tab> modeloSeleccion = panelPestaniasEmpleados.getSelectionModel();
        modeloSeleccion.select(tabCrearEmpleado);
        textNombreCrear.requestFocus();
    }

    @FXML
    private void modificarEmpleado() {
        if(labIDModificar.getText().trim().isEmpty() || labIDModificar.getText() == null){
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("");
            alerta.setContentText("Debe de seleccionar antes un Empleado para Modificarlo");
            Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/icono_Alerta.png")));
            Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
            stage.getIcons().add(icon);
            alerta.showAndWait();
        } else {
            SingleSelectionModel<Tab> modeloSeleccion = panelPestaniasEmpleados.getSelectionModel();
            modeloSeleccion.select(tabModificarEmpleado);
            textNombreModificar.requestFocus();
        }
    }

    @FXML
    private void eliminarEmpleado() {
        if(labIDEliminar.getText().trim().isEmpty() || labIDEliminar.getText() == null){
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("");
            alerta.setContentText("Debe de seleccionar antes un Empleado para Eliminarlo");
            Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/icono_Alerta.png")));
            Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
            stage.getIcons().add(icon);
            alerta.showAndWait();
        } else {
            SingleSelectionModel<Tab> modeloSeleccion = panelPestaniasEmpleados.getSelectionModel();
            modeloSeleccion.select(tabEliminarEmpleado);
            btnEliminar.requestFocus();
        }
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

    // -------------------------------------------- Ausencias --------------------------------------------
    @FXML
    private void ausenciasEmpleado() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/ausencias.fxml"));
        AnchorPane root = loader.load();
        ControladorAusencias ausencias = loader.getController();
        Scene escena = new Scene(root);
        Stage escenario = new Stage();
        escenario.setTitle("Ausencias");
        escenario.setScene(escena);
        escenario.getIcons().add(new javafx.scene.image.Image(Objects.requireNonNull(getClass().getResource("/imagenes/icono.png")).toString()));
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

    // -------------------------------------------- Licencias --------------------------------------------
    @FXML
    private void licenciasEmpleado() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/licencias.fxml"));
        AnchorPane root = loader.load();
        ControladorLicencias licencias = loader.getController();
        Scene escena = new Scene(root);
        Stage escenario = new Stage();
        escenario.setTitle("Licencias");
        escenario.setScene(escena);
        escenario.getIcons().add(new javafx.scene.image.Image(Objects.requireNonNull(getClass().getResource("/imagenes/icono.png")).toString()));
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

    //---------------------------------------- Validación de Caracteres ------------------------------------------
    public static boolean validarNumeros(String datos){
        return datos.matches("[0-9]*");
    }
    public static boolean validarNumerosTelefono(String datos){
        return datos.matches("[0-9--]*");
    }
    public static boolean validarLetras(String datos){
        return datos.matches("[a-zA-Z]*");
    }

    public static boolean validarLetrasEmail(String datos){
        return datos.matches("^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    }

    //----------------------------------------- Limpiador de Campos ----------------------------------------------
    private void limpiarCamposCrear(){
        textNombreCrear.setText("");
        textApellidoCrear.setText("");
        textLegajoCrear.setText("");
        textDNICrear.setText("");
        textTelefonoCrear.setText("");
        textDireccionCrear.setText("");
        textEmailCrear.setText("");
        cbGeneroCrear.getSelectionModel().selectFirst();
        cbEstadoCivilCrear.getSelectionModel().selectFirst();
        cbGrupoSanguineoCrear.getSelectionModel().selectFirst();
        cbAreaCrear.getSelectionModel().selectFirst();
        cbPuestoCrear.getSelectionModel().selectFirst();
        labLimpiarCamposCrear.setText("");
        fechasInicializar();
    }

    private void limpiarCamposModificar(){
        textBuscarEmpleado.setText("");
        textNombreModificar.setText("");
        textApellidoModificar.setText("");
        textLegajoModificar.setText("");
        textDNIModificar.setText("");
        textTelefonoModificar.setText("");
        textDireccionModificar.setText("");
        textEmailModificar.setText("");
        cbGeneroModificar.getSelectionModel().selectFirst();
        cbEstadoCivilModificar.getSelectionModel().selectFirst();
        cbGrupoSanguineoModificar.getSelectionModel().selectFirst();
        cbAreaModificar.getSelectionModel().selectFirst();
        cbPuestoModificar.getSelectionModel().selectFirst();
        labIDModificar.setText("");
        labLimpiarCamposModificar.setText("");
        labInformacionModificarLegajo.setText("");
        labInformacionModificarDNI.setText("");
        fechasInicializar();
    }

    private void limpiarCamposEliminar(){
        textBuscarEmpleado.setText("");
        textNombreEliminar.setText("");
        textApellidoEliminar.setText("");
        textLegajoEliminar.setText("");
        textDNIEliminar.setText("");
        textTelefonoEliminar.setText("");
        textDireccionEliminar.setText("");
        textEmailEliminar.setText("");
        cbGeneroEliminar.getSelectionModel().selectFirst();
        cbEstadoCivilEliminar.getSelectionModel().selectFirst();
        cbGrupoSanguineoEliminar.getSelectionModel().selectFirst();
        cbAreaEliminar.getSelectionModel().selectFirst();
        cbPuestoEliminar.getSelectionModel().selectFirst();
        labIDEliminar.setText("");
        fechasInicializar();
    }

    private void limpiarCamposGeneralizada(){
        textNombreModificar.setText("");
        textApellidoModificar.setText("");
        textLegajoModificar.setText("");
        textDNIModificar.setText("");
        textTelefonoModificar.setText("");
        textDireccionModificar.setText("");
        textEmailModificar.setText("");
        cbGeneroModificar.getSelectionModel().selectFirst();
        cbEstadoCivilModificar.getSelectionModel().selectFirst();
        cbGrupoSanguineoModificar.getSelectionModel().selectFirst();
        cbAreaModificar.getSelectionModel().selectFirst();
        cbPuestoModificar.getSelectionModel().selectFirst();
        labIDModificar.setText("");
        labLimpiarCamposModificar.setText("");
        labInformacionModificarLegajo.setText("");
        labInformacionModificarDNI.setText("");
        textNombreEliminar.setText("");
        textApellidoEliminar.setText("");
        textLegajoEliminar.setText("");
        textDNIEliminar.setText("");
        textTelefonoEliminar.setText("");
        textDireccionEliminar.setText("");
        textEmailEliminar.setText("");
        cbGeneroEliminar.getSelectionModel().selectFirst();
        cbEstadoCivilEliminar.getSelectionModel().selectFirst();
        cbGrupoSanguineoEliminar.getSelectionModel().selectFirst();
        cbAreaEliminar.getSelectionModel().selectFirst();
        cbPuestoEliminar.getSelectionModel().selectFirst();
        labIDEliminar.setText("");
        fechasInicializar();
    }

    private void vaciarComboBox(){
        cbGeneroCrear.getItems().clear();
        cbEstadoCivilCrear.getItems().clear();
        cbGrupoSanguineoCrear.getItems().clear();
        cbAreaCrear.getItems().clear();
        cbPuestoCrear.getItems().clear();
        cbGeneroModificar.getItems().clear();
        cbEstadoCivilModificar.getItems().clear();
        cbGrupoSanguineoModificar.getItems().clear();
        cbAreaModificar.getItems().clear();
        cbPuestoModificar.getItems().clear();
        cbGeneroEliminar.getItems().clear();
        cbEstadoCivilEliminar.getItems().clear();
        cbGrupoSanguineoEliminar.getItems().clear();
        cbAreaEliminar.getItems().clear();
        cbPuestoEliminar.getItems().clear();
    }

    //---------------------------------------- Comprobación de Campos -------------------------------------------
    private void camposObligatoriosCrear(){
        if(textNombreCrear.getText().trim().isEmpty() || textNombreCrear.getText() == null){
            labErrorNombreCrear.setText("Campo Obligatorio");
        } else {
            labErrorNombreCrear.setText("");
        }
        if(textApellidoCrear.getText().trim().isEmpty() || textApellidoCrear.getText() == null){
            labErrorApellidoCrear.setText("Campo Obligatorio");
        } else {
            labErrorApellidoCrear.setText("");
        }
        if(textLegajoCrear.getText().trim().isEmpty() || textLegajoCrear.getText() == null){
            labErrorLegajoCrear.setText("Campo Obligatorio");
        } else {
            labErrorLegajoCrear.setText("");
        }
        if(textDNICrear.getText().trim().isEmpty() || textDNICrear.getText() == null){
            labErrorDNICrear.setText("Campo Obligatorio");
        } else {
            labErrorDNICrear.setText("");
        }
        if(textTelefonoCrear.getText().trim().isEmpty() || textTelefonoCrear.getText() == null){
            labErrorTelefonoCrear.setText("Campo Obligatorio");
        } else {
            labErrorTelefonoCrear.setText("");
        }
        if(textDireccionCrear.getText().trim().isEmpty() || textDireccionCrear.getText() == null){
            labErrorDireccionCrear.setText("Campo Obligatorio");
        } else {
            labErrorDireccionCrear.setText("");
        }
        if(textEmailCrear.getText().trim().isEmpty() || textEmailCrear.getText() == null){
            labErrorEmailCrear.setText("Campo Obligatorio");
        } else {
            labErrorEmailCrear.setText("");
        }
    }

    private void validacionCamposLongitudCrear(){
        String nombre = textNombreCrear.getText().trim();
        String apellido = textApellidoCrear.getText().trim();
        String legajo = textLegajoCrear.getText().trim();
        String dni = textDNICrear.getText().trim();
        String telefono = textTelefonoCrear.getText().trim();
        String direccion = textDireccionCrear.getText().trim();
        String email = textEmailCrear.getText().trim();
        nombre = nombre.replaceAll("\\s+", "");
        apellido = apellido.replaceAll("\\s+", "");
        legajo = legajo.replaceAll("\\s+", "");
        dni = dni.replaceAll("\\s+", "");
        telefono = telefono.replaceAll("\\s+", "");
        direccion = direccion.replaceAll("\\s+", "");
        email = email.replaceAll("\\s+", "");

        if(nombre.length() < 4){
            labErrorNombreCrear.setText("Usá 4 o más caracteres");
        } else {
            labErrorNombreCrear.setText("");
        }
        if(apellido.length() < 4){
            labErrorApellidoCrear.setText("Usá 4 o más caracteres");
        } else {
            labErrorApellidoCrear.setText("");
        }
        if(legajo.length() < 2 || legajo.length() > 6){
            labErrorLegajoCrear.setText("Usá 2 o hasta 6 números");
        } else {
            labErrorLegajoCrear.setText("");
        }
        if(dni.length() < 7 || dni.length() >= 9){
            labErrorDNICrear.setText("Usá entre 7 u 8 números");
        } else {
            labErrorDNICrear.setText("");
        }
        if(telefono.length() < 10 || telefono.length() >= 12){
            labErrorTelefonoCrear.setText("Usá entre 10 u 11 números");
        } else {
            labErrorTelefonoCrear.setText("");
        }
        if(direccion.length() < 6){
            labErrorDireccionCrear.setText("Usá 6 o más caracteres");
        } else {
            labErrorDireccionCrear.setText("");
        }
        if(email.length() < 4){
            labErrorEmailCrear.setText("Usá 6 o más caracteres");
        } else {
            labErrorEmailCrear.setText("");
        }
    }

    private void validacionCamposCaracteresCrear(){
        String nombre = textNombreCrear.getText().trim();
        String apellido = textApellidoCrear.getText().trim();
        String legajo = textLegajoCrear.getText().trim();
        String dni = textDNICrear.getText().trim();
        String telefono = textTelefonoCrear.getText().trim();
        String direccion = textDireccionCrear.getText().trim();
        String email = textEmailCrear.getText().trim();
        nombre = nombre.replaceAll("\\s+", "");
        apellido = apellido.replaceAll("\\s+", "");
        legajo = legajo.replaceAll("\\s+", "");
        dni = dni.replaceAll("\\s+", "");
        telefono = telefono.replaceAll("\\s+", "");
        direccion = direccion.replaceAll("\\s+", "");
        email = email.replaceAll("\\s+", "");

        if(validarLetras(nombre)){
            labErrorNombreCrear.setText("");
        } else {
            labErrorNombreCrear.setText("Solo se admiten Letras");
        }
        if(validarLetras(apellido)){
            labErrorApellidoCrear.setText("");
        } else {
            labErrorApellidoCrear.setText("Solo se admiten Letras");
        }
        if(validarNumeros(legajo)){
            labErrorLegajoCrear.setText("");
        } else {
            labErrorLegajoCrear.setText("Solo se admiten Números");
        }
        if(validarNumeros(dni)){
            labErrorDNICrear.setText("");
        } else {
            labErrorDNICrear.setText("Solo se admiten Números");
        }
        if(validarNumerosTelefono(telefono)){
            labErrorTelefonoCrear.setText("");
        } else {
            labErrorTelefonoCrear.setText("Solo se admiten Números");
        }
        if(validarLetrasEmail(email)){
            labErrorEmailCrear.setText("");
        } else {
            labErrorEmailCrear.setText("No se parece a un E-mail");
        }
    }

    private void camposObligatoriosModificar(){
        if(textNombreModificar.getText().trim().isEmpty() || textNombreModificar.getText() == null){
            labErrorNombreModificar.setText("Campo Obligatorio");
        } else {
            labErrorNombreModificar.setText("");
        }
        if(textApellidoModificar.getText().trim().isEmpty() || textApellidoModificar.getText() == null){
            labErrorApellidoModificar.setText("Campo Obligatorio");
        } else {
            labErrorApellidoModificar.setText("");
        }
        if(textLegajoModificar.getText().trim().isEmpty() || textLegajoModificar.getText() == null){
            labErrorLegajoModificar.setText("Campo Obligatorio");
        } else {
            labErrorLegajoModificar.setText("");
        }
        if(textDNIModificar.getText().trim().isEmpty() || textDNIModificar.getText() == null){
            labErrorDNIModificar.setText("Campo Obligatorio");
        } else {
            labErrorDNIModificar.setText("");
        }
        if(textTelefonoModificar.getText().trim().isEmpty() || textTelefonoModificar.getText() == null){
            labErrorTelefonoModificar.setText("Campo Obligatorio");
        } else {
            labErrorTelefonoModificar.setText("");
        }
        if(textDireccionModificar.getText().trim().isEmpty() || textDireccionModificar.getText() == null){
            labErrorDireccionModificar.setText("Campo Obligatorio");
        } else {
            labErrorDireccionModificar.setText("");
        }
        if(textEmailModificar.getText().trim().isEmpty() || textEmailModificar.getText() == null){
            labErrorEmailModificar.setText("Campo Obligatorio");
        } else {
            labErrorEmailModificar.setText("");
        }
    }

    private void validacionCamposLongitudModificar(){
        String nombre = textNombreModificar.getText().trim();
        String apellido = textApellidoModificar.getText().trim();
        String legajo = textLegajoModificar.getText().trim();
        String dni = textDNIModificar.getText().trim();
        String telefono = textTelefonoModificar.getText().trim();
        String direccion = textDireccionModificar.getText().trim();
        String email = textEmailModificar.getText().trim();
        nombre = nombre.replaceAll("\\s+", "");
        apellido = apellido.replaceAll("\\s+", "");
        legajo = legajo.replaceAll("\\s+", "");
        dni = dni.replaceAll("\\s+", "");
        telefono = telefono.replaceAll("\\s+", "");
        direccion = direccion.replaceAll("\\s+", "");
        email = email.replaceAll("\\s+", "");

        if(nombre.length() < 4){
            labErrorNombreModificar.setText("Usá 4 o más caracteres");
        } else {
            labErrorNombreModificar.setText("");
        }
        if(apellido.length() < 4){
            labErrorApellidoModificar.setText("Usá 4 o más caracteres");
        } else {
            labErrorApellidoModificar.setText("");
        }
        if(legajo.length() < 2 || legajo.length() > 6){
            labErrorLegajoModificar.setText("Usá 2 o hasta 6 números");
        } else {
            labErrorLegajoModificar.setText("");
        }
        if(dni.length() < 7 || dni.length() >= 9){
            labErrorDNIModificar.setText("Usá entre 7 u 8 números");
        } else {
            labErrorDNIModificar.setText("");
        }
        if(telefono.length() < 10 || telefono.length() >= 12){
            labErrorTelefonoModificar.setText("Usá entre 10 u 11 números");
        } else {
            labErrorTelefonoModificar.setText("");
        }
        if(direccion.length() < 6){
            labErrorDireccionModificar.setText("Usá 6 o más caracteres");
        } else {
            labErrorDireccionModificar.setText("");
        }
        if(email.length() < 4){
            labErrorEmailModificar.setText("Usá 6 o más caracteres");
        } else {
            labErrorEmailModificar.setText("");
        }
    }

    private void validacionCamposCaracteresModificar(){
        String nombre = textNombreModificar.getText().trim();
        String apellido = textApellidoModificar.getText().trim();
        String legajo = textLegajoModificar.getText().trim();
        String dni = textDNIModificar.getText().trim();
        String telefono = textTelefonoModificar.getText().trim();
        String direccion = textDireccionModificar.getText().trim();
        String email = textEmailModificar.getText().trim();
        nombre = nombre.replaceAll("\\s+", "");
        apellido = apellido.replaceAll("\\s+", "");
        legajo = legajo.replaceAll("\\s+", "");
        dni = dni.replaceAll("\\s+", "");
        telefono = telefono.replaceAll("\\s+", "");
        direccion = direccion.replaceAll("\\s+", "");
        email = email.replaceAll("\\s+", "");

        if(validarLetras(nombre)){
            labErrorNombreModificar.setText("");
        } else {
            labErrorNombreModificar.setText("Solo se admiten Letras");
        }
        if(validarLetras(apellido)){
            labErrorApellidoModificar.setText("");
        } else {
            labErrorApellidoModificar.setText("Solo se admiten Letras");
        }
        if(validarNumeros(legajo)){
            labErrorLegajoModificar.setText("");
        } else {
            labErrorLegajoModificar.setText("Solo se admiten Números");
        }
        if(validarNumeros(dni)){
            labErrorDNIModificar.setText("");
        } else {
            labErrorDNIModificar.setText("Solo se admiten Números");
        }
        if(validarNumerosTelefono(telefono)) {
            labErrorTelefonoModificar.setText("");
        } else {
            labErrorTelefonoModificar.setText("Solo se admiten Números");
        }
        if(validarLetrasEmail(email)){
            labErrorEmailModificar.setText("");
        } else {
            labErrorEmailModificar.setText("No se parece a un E-mail");
        }
    }

    // ---------------------------------------- Fechas Actuales Inicializadas ----------------------------------------
    public void fechasInicializar() {
        dpFechaNaciminetoCrear.setValue(LocalDate.of(2000, 01, 01));
        dpFechaNaciminetoCrear.setConverter(new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate localDate) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyy");
                return dtf.format(localDate);
            }

            @Override
            public LocalDate fromString(String s) {
                return null;
            }
        });

        dpFechaNaciminetoModificar.setValue(LocalDate.of(2000, 01, 01));
        dpFechaNaciminetoModificar.setConverter(new StringConverter<LocalDate>() {
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

        dpFechaNaciminetoEliminar.setValue(LocalDate.of(2000, 01, 01));
        dpFechaNaciminetoEliminar.setConverter(new StringConverter<LocalDate>() {
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

        dpFechaIngresoCrear.setValue(LocalDate.now());
        dpFechaIngresoCrear.setConverter(new StringConverter<LocalDate>() {
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

        dpFechaIngresoModificar.setValue(LocalDate.now());
        dpFechaIngresoModificar.setConverter(new StringConverter<LocalDate>() {
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

        dpFechaIngresoEliminar.setValue(LocalDate.now());
        dpFechaIngresoEliminar.setConverter(new StringConverter<LocalDate>() {
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
    public void closeWindowsPrincipalEmpleado() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/principal.fxml"));
        AnchorPane root = loader.load();
        Scene escena = new Scene(root);
        Stage escenario = new Stage();
        escenario.setTitle("Sistema de Gestión y Organización de Empleados");
        escenario.setScene(escena);
        escenario.getIcons().add(new javafx.scene.image.Image(Objects.requireNonNull(getClass().getResource("/imagenes/icono.png")).toString()));
        escenario.show();

        Stage myEscena = (Stage) this.btnVolver.getScene().getWindow();
        myEscena.close();
    }
}
