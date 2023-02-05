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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;


import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
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
    private Label labLimpiarCamposCrear;
    @FXML
    private Label labLimpiarCamposModificar;
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

    ObservableList<LeerEmpleado> listEmpleado;
    int index = -1;

    String ErrorCrear = "";
    String ErrorModificar = "";

    // -------------------------------------------- Inicialización ----------------------------------------------
    public void initialize() {
        String[] tipoFiltro = {"Nombre", "Apellido", "Legajo", "Area", "Puesto"};
        cbTiposfiltrosEmpleados.getItems().addAll(tipoFiltro);
        cbTiposfiltrosEmpleados.getSelectionModel().selectFirst();
        inicializarComboBoxBD();
        inicializarTabla();
        fechasInicializar();
    }

    public void inicializarTabla(){
        colNombre.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, String>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, String>("apellido"));
        colLegajo.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, Integer>("legajo"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, String>("telefono"));
        colFechaIngreso.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, Date>("fechaIngreso"));
        colArea.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, String>("idAreaFK"));
        colPuesto.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, String>("idPuestoFK"));
        colID.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, Integer>("idempleados"));

        listEmpleado = LeerEmpleado.listaEmpleados();
        tablaEmpleados.getColumns().setAll(colNombre, colApellido, colLegajo, colTelefono, colFechaIngreso, colArea, colPuesto, colID);
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
        empleadoSeleccionado.listaEmpleadoSeleccionadoM(textNombreModificar, textApellidoModificar, textLegajoModificar, textTelefonoModificar, textDireccionModificar, textEmailModificar, cbGeneroModificar, cbEstadoCivilModificar, dpFechaNaciminetoModificar, cbGrupoSanguineoModificar, dpFechaIngresoModificar, cbAreaModificar, cbPuestoModificar, labIDModificar, labInformacionModificarLegajo);

        labIDEliminar.setText(colID.getCellData(index).toString());
        LeerEmpleado empleadoSeleccion = new LeerEmpleado();
        empleadoSeleccion.listaEmpleadoSeleccionadoE(textNombreEliminar, textApellidoEliminar, textLegajoEliminar, textTelefonoEliminar, textDireccionEliminar, textEmailEliminar, cbGeneroEliminar, cbEstadoCivilEliminar, dpFechaNaciminetoEliminar, cbGrupoSanguineoEliminar, dpFechaIngresoEliminar, cbAreaEliminar, cbPuestoEliminar, labIDEliminar);
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
        ObservableList<LeerEmpleado> listBuscar;
        listBuscar = LeerEmpleado.buscarEmpleado(textBuscarEmpleado);

        if(textBuscarEmpleado.getText().equals("")){
            tablaEmpleados.getItems().setAll(listEmpleado);
        } else {
            tablaEmpleados.getItems().setAll(listBuscar);
        }
    }

    @FXML
    private void filtroEmpleado() {
        ObservableList<LeerEmpleado> listFiltros;
        listFiltros = LeerEmpleado.filtroEmpleado(cbTiposfiltrosEmpleados);

        tablaEmpleados.getItems().setAll(listFiltros);
    }

    //---------------------------------------------- Eventos Importantes ----------------------------------------
    @FXML
    private void guardar() throws SQLException, ParseException {
        ErrorCrear = "";
        TextField[] campos = {textNombreCrear, textApellidoCrear, textLegajoCrear, textTelefonoCrear, textDireccionCrear, textEmailCrear};
        TextField[] campoLegajo = {textLegajoCrear};
        labErroresCrear();
        if(comprobarValoresCrear(campos)){
            if(validarLetras(textNombreCrear.getText())) {
                if (validarLetras(textApellidoCrear.getText())) {
                    if (validarNumeros(textLegajoCrear.getText())) {
                        if (comprobarValoresCrearLegajo(campoLegajo)) {
                            if (validarNumerosTelefono(textTelefonoCrear.getText())) {
                                if (validarLetrasEmail(textEmailCrear.getText())) {
                                    labErroresCrear();

                                    CrearEmpleado empleadoCrear = new CrearEmpleado();
                                    empleadoCrear.agregarEmpleado(textNombreCrear, textApellidoCrear, textLegajoCrear, textTelefonoCrear, textDireccionCrear, textEmailCrear, cbGeneroCrear, cbEstadoCivilCrear, dpFechaNaciminetoCrear, cbGrupoSanguineoCrear, dpFechaIngresoCrear, cbAreaCrear, cbPuestoCrear, labLimpiarCamposCrear);

                                    if(Objects.equals(labLimpiarCamposCrear.getText(), "OK")){
                                        labLimpiarCamposCrear.setText("");
                                        inicializarTabla();
                                        vaciarComboBox();
                                        inicializarComboBoxBD();
                                        regresarCLista();
                                        limpiarCamposCrear();
                                    }
                                } else {
                                    labErrorEmailCrear.setText("No se parece a un E-mail");
                                    ErrorCrear = "Email";
                                    labErroresCrear2(ErrorCrear);
                                }
                            } else {
                                labErrorTelefonoCrear.setText("Solo se admiten Números");
                                ErrorCrear = "Telefono";
                                labErroresCrear2(ErrorCrear);
                            }
                        } else {
                            labErrorLegajoCrear.setText("Solo se permite hasta 10 Números");
                            ErrorCrear = "Legajo";
                            labErroresCrear2(ErrorCrear);
                        }
                    } else {
                        labErrorLegajoCrear.setText("Solo se admiten Números");
                        ErrorCrear = "Legajo";
                        labErroresCrear2(ErrorCrear);
                    }
                } else {
                    labErrorApellidoCrear.setText("Solo se admiten Letras");
                    ErrorCrear = "Apellido";
                    labErroresCrear2(ErrorCrear);
                }
            } else {
                labErrorNombreCrear.setText("Solo se admiten Letras");
                ErrorCrear = "Nombre";
                labErroresCrear2(ErrorCrear);
            }
        } else {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Error!");
            alerta.setContentText("Debe de completar todos los campos y que NO sean cortos.");
            alerta.showAndWait();
        }
    }

    @FXML
    private void modificar() throws SQLException, ParseException {
        ErrorModificar = "";
        TextField[] campos = {textNombreModificar, textApellidoModificar, textLegajoModificar, textTelefonoModificar, textDireccionModificar, textEmailModificar};
        TextField[] campoLegajo = {textLegajoModificar};
        Label[] id = {labIDModificar};
        labErroresModificar();

        if(comprobarIDModificar(id)){
            if(comprobarValoresModificar(campos)) {
                if (validarLetras(textNombreModificar.getText())) {
                    if (validarLetras(textApellidoModificar.getText())) {
                        if (validarNumeros(textLegajoModificar.getText())) {
                            if (comprobarValoresModificarLegajo(campoLegajo)) {
                                if (validarNumerosTelefono(textTelefonoModificar.getText())) {
                                    if (validarLetrasEmail(textEmailModificar.getText())) {
                                        labErroresModificar();

                                        ModificarEmpleado empleadoModificar = new ModificarEmpleado();
                                        empleadoModificar.modificarEmpleado(textNombreModificar, textApellidoModificar, textLegajoModificar, textTelefonoModificar, textDireccionModificar, textEmailModificar, cbGeneroModificar, cbEstadoCivilModificar, dpFechaNaciminetoModificar, cbGrupoSanguineoModificar, dpFechaIngresoModificar, cbAreaModificar, cbPuestoModificar, labLimpiarCamposModificar, labInformacionModificarLegajo, labIDModificar);

                                        if(Objects.equals(labLimpiarCamposModificar.getText(), "OK")){
                                            inicializarTabla();
                                            vaciarComboBox();
                                            inicializarComboBoxBD();
                                            regresarCLista();
                                            limpiarCamposModificar();
                                            limpiarCamposEliminar();
                                        }

                                    } else {
                                        labErrorEmailModificar.setText("No se parece a un E-mail");
                                        ErrorModificar = "Email";
                                        labErroresModificar2(ErrorModificar);
                                    }
                                }  else {
                                    labErrorTelefonoModificar.setText("Solo se admiten Números");
                                    ErrorModificar = "Telefono";
                                    labErroresModificar2(ErrorModificar);
                                }
                            } else {
                                labErrorLegajoModificar.setText("Solo se permite hasta 10 Números");
                                ErrorModificar = "Legajo";
                                labErroresModificar2(ErrorModificar);
                            }
                        } else {
                            labErrorLegajoModificar.setText("Solo se admiten Números");
                            ErrorModificar = "Legajo";
                            labErroresModificar2(ErrorModificar);
                        }
                    } else {
                        labErrorApellidoModificar.setText("Solo se admiten Letras");
                        ErrorModificar = "Apellido";
                        labErroresModificar2(ErrorModificar);
                    }
                } else {
                    labErrorNombreModificar.setText("Solo se admiten Letras");
                    ErrorModificar = "Nombre";
                    labErroresModificar2(ErrorModificar);
                }
            }  else {
                Alert alerta = new Alert(Alert.AlertType.WARNING);
                alerta.setTitle("Error!");
                alerta.setContentText("Debe de completar todos los campos y que NO sean cortos.");
                alerta.showAndWait();
            }
        } else {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Error!");
            alerta.setContentText("Debe de seleccionar antes un Empleado para Modificarlo");
            alerta.showAndWait();
        }
    }

    @FXML
    private void eliminar() {
        Label[] id = {labIDEliminar};
        if (comprobarIDEliminar(id)){
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle("Confirmar Eliminar");
            alerta.setContentText("¿Desea Eliminar el Empleado?");
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
            alerta.setTitle("Error!");
            alerta.setContentText("Debe de seleccionar antes un Empleado para Eliminarlo");
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
        SingleSelectionModel<Tab> modeloSeleccion = panelPestaniasEmpleados.getSelectionModel();
        modeloSeleccion.select(tabModificarEmpleado);
        textNombreModificar.requestFocus();
    }

    @FXML
    private void eliminarEmpleado() {
        SingleSelectionModel<Tab> modeloSeleccion = panelPestaniasEmpleados.getSelectionModel();
        modeloSeleccion.select(tabEliminarEmpleado);
        btnEliminar.requestFocus();
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
        escenario.setTitle("Gestión Secadero (Ausencias)");
        escenario.setScene(escena);
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
        escenario.setTitle("Gestión Secadero (Licencias)");
        escenario.setScene(escena);
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

    //---------------------------------------- Comprobación de Campos -------------------------------------------
    private boolean comprobarValoresCrear(TextField[] campos){
        boolean valido = true;
        for (int i = 0; i < campos.length; i++) {
            String valor = campos[i].getText();
            if(valor == null || valor.trim().isEmpty() || textNombreCrear.getLength() < 4|| textApellidoCrear.getLength() < 4|| textLegajoCrear.getLength() < 4 || textTelefonoCrear.getLength() < 10 || textDireccionCrear.getLength() < 4 || textEmailCrear.getLength() < 6){
                valido = false;
            }
        }
        return valido;
    }

    private boolean comprobarValoresModificar(TextField[] campos){
        boolean valido = true;
        for (int i = 0; i < campos.length; i++) {
            String valor = campos[i].getText();
            if(valor == null || valor.trim().isEmpty() || textNombreModificar.getLength() < 4|| textApellidoModificar.getLength() < 4|| textLegajoModificar.getLength() < 4|| textTelefonoModificar.getLength() < 10 || textDireccionModificar.getLength() < 4 || textEmailModificar.getLength() < 6){
                valido = false;
            }
        }
        return valido;
    }

    private boolean comprobarValoresCrearLegajo(TextField[] campoLegajo){
        boolean valido = true;
        for (int i = 0; i < campoLegajo.length; i++) {
            String valor = campoLegajo[i].getText();
            if(valor == null || valor.trim().isEmpty() || textLegajoCrear.getLength() > 10){
                valido = false;
            }
        }
        return valido;
    }

    private boolean comprobarValoresModificarLegajo(TextField[] campoLegajo){
        boolean valido = true;
        for (int i = 0; i < campoLegajo.length; i++) {
            String valor = campoLegajo[i].getText();
            if(valor == null || valor.trim().isEmpty() || textLegajoModificar.getLength() > 10){
                valido = false;
            }
        }
        return valido;
    }

    private boolean comprobarIDModificar(Label[] id){
        boolean valido = true;
        for (int i = 0; i < id.length; i++) {
            String valor = id[i].getText();
            if(valor == null || valor.trim().isEmpty()){
                valido = false;
            }
        }
        return valido;
    }

    private boolean comprobarIDEliminar(Label[] id){
        boolean valido = true;
        for (int i = 0; i < id.length; i++) {
            String valor = id[i].getText();
            if(valor == null || valor.trim().isEmpty()){
                valido = false;
            }
        }
        return valido;
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
        textNombreModificar.setText("");
        textApellidoModificar.setText("");
        textLegajoModificar.setText("");
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
        fechasInicializar();
    }

    private void limpiarCamposEliminar(){
        textNombreEliminar.setText("");
        textApellidoEliminar.setText("");
        textLegajoEliminar.setText("");
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

    // ----------------------------------------------- Labels de Errores ----------------------------------------
    private void labErroresCrear() {
        labErrorNombreCrear.setText("");
        labErrorApellidoCrear.setText("");
        labErrorLegajoCrear.setText("");
        labErrorTelefonoCrear.setText("");
        labErrorDireccionCrear.setText("");
        labErrorEmailCrear.setText("");
        labErrorFechaNacimientoCrear.setText("");
    }

    private void labErroresModificar() {
        labErrorNombreModificar.setText("");
        labErrorApellidoModificar.setText("");
        labErrorLegajoModificar.setText("");
        labErrorTelefonoModificar.setText("");
        labErrorDireccionModificar.setText("");
        labErrorEmailModificar.setText("");
        labErrorFechaNacimientoModificar.setText("");
    }

    private void labErroresCrear2(String ErrorCrear) {
        if (Objects.equals(ErrorCrear, "Nombre")) {
            labErrorApellidoCrear.setText("");
            labErrorLegajoCrear.setText("");
            labErrorTelefonoCrear.setText("");
            labErrorDireccionCrear.setText("");
            labErrorEmailCrear.setText("");
            labErrorFechaNacimientoCrear.setText("");
        } else if (Objects.equals(ErrorCrear, "Apellido")) {
            labErrorNombreCrear.setText("");
            labErrorLegajoCrear.setText("");
            labErrorTelefonoCrear.setText("");
            labErrorDireccionCrear.setText("");
            labErrorEmailCrear.setText("");
            labErrorFechaNacimientoCrear.setText("");
        } else if (Objects.equals(ErrorCrear, "Legajo")) {
            labErrorNombreCrear.setText("");
            labErrorApellidoCrear.setText("");
            labErrorTelefonoCrear.setText("");
            labErrorDireccionCrear.setText("");
            labErrorEmailCrear.setText("");
            labErrorFechaNacimientoCrear.setText("");
        } else if (Objects.equals(ErrorCrear, "Telefono")) {
            labErrorNombreCrear.setText("");
            labErrorApellidoCrear.setText("");
            labErrorLegajoCrear.setText("");
            labErrorDireccionCrear.setText("");
            labErrorEmailCrear.setText("");
            labErrorFechaNacimientoCrear.setText("");
        } else if (Objects.equals(ErrorCrear, "Direccion")) {
            labErrorNombreCrear.setText("");
            labErrorApellidoCrear.setText("");
            labErrorLegajoCrear.setText("");
            labErrorTelefonoCrear.setText("");
            labErrorEmailCrear.setText("");
            labErrorFechaNacimientoCrear.setText("");
        } else if (Objects.equals(ErrorCrear, "Email")) {
            labErrorNombreCrear.setText("");
            labErrorApellidoCrear.setText("");
            labErrorLegajoCrear.setText("");
            labErrorTelefonoCrear.setText("");
            labErrorDireccionCrear.setText("");
            labErrorFechaNacimientoCrear.setText("");
        }else if (Objects.equals(ErrorCrear, "FechaNacimiento")) {
            labErrorNombreCrear.setText("");
            labErrorApellidoCrear.setText("");
            labErrorLegajoCrear.setText("");
            labErrorTelefonoCrear.setText("");
            labErrorDireccionCrear.setText("");
            labErrorEmailCrear.setText("");
        }
    }

    private void labErroresModificar2(String ErrorModificar) {
        if (Objects.equals(ErrorModificar, "Nombre")) {
            labErrorApellidoModificar.setText("");
            labErrorLegajoModificar.setText("");
            labErrorTelefonoModificar.setText("");
            labErrorDireccionModificar.setText("");
            labErrorEmailModificar.setText("");
            labErrorFechaNacimientoModificar.setText("");
        } else if (Objects.equals(ErrorModificar, "Apellido")) {
            labErrorNombreModificar.setText("");
            labErrorLegajoModificar.setText("");
            labErrorTelefonoModificar.setText("");
            labErrorDireccionModificar.setText("");
            labErrorEmailModificar.setText("");
            labErrorFechaNacimientoModificar.setText("");
        } else if (Objects.equals(ErrorModificar, "Legajo")) {
            labErrorNombreModificar.setText("");
            labErrorApellidoModificar.setText("");
            labErrorTelefonoModificar.setText("");
            labErrorDireccionModificar.setText("");
            labErrorEmailModificar.setText("");
            labErrorFechaNacimientoModificar.setText("");
        } else if (Objects.equals(ErrorModificar, "Telefono")) {
            labErrorNombreModificar.setText("");
            labErrorApellidoModificar.setText("");
            labErrorLegajoModificar.setText("");
            labErrorDireccionModificar.setText("");
            labErrorEmailModificar.setText("");
            labErrorFechaNacimientoModificar.setText("");
        } else if (Objects.equals(ErrorModificar, "Direccion")) {
            labErrorNombreModificar.setText("");
            labErrorApellidoModificar.setText("");
            labErrorLegajoModificar.setText("");
            labErrorTelefonoModificar.setText("");
            labErrorEmailModificar.setText("");
            labErrorFechaNacimientoModificar.setText("");
        } else if (Objects.equals(ErrorModificar, "Email")) {
            labErrorNombreModificar.setText("");
            labErrorApellidoModificar.setText("");
            labErrorLegajoModificar.setText("");
            labErrorTelefonoModificar.setText("");
            labErrorDireccionModificar.setText("");
            labErrorFechaNacimientoModificar.setText("");
        }else if (Objects.equals(ErrorModificar, "FechaNacimiento")) {
            labErrorNombreModificar.setText("");
            labErrorApellidoModificar.setText("");
            labErrorLegajoModificar.setText("");
            labErrorTelefonoModificar.setText("");
            labErrorDireccionModificar.setText("");
            labErrorEmailModificar.setText("");
        }
    }

    // ---------------------------------------- Fechas Actuales Inicializadas ----------------------------------------
    public void fechasInicializar() {
        dpFechaNaciminetoCrear.setValue(LocalDate.of(2000, 01, 01));
        dpFechaNaciminetoCrear.setConverter(new StringConverter<LocalDate>() {
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

        dpFechaNaciminetoModificar.setValue(LocalDate.of(2000, 01, 01));
        dpFechaNaciminetoModificar.setConverter(new StringConverter<LocalDate>() {
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

        dpFechaNaciminetoEliminar.setValue(LocalDate.of(2000, 01, 01));
        dpFechaNaciminetoEliminar.setConverter(new StringConverter<LocalDate>() {
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

        dpFechaIngresoCrear.setValue(LocalDate.now());
        dpFechaIngresoCrear.setConverter(new StringConverter<LocalDate>() {
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

        dpFechaIngresoModificar.setValue(LocalDate.now());
        dpFechaIngresoModificar.setConverter(new StringConverter<LocalDate>() {
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

        dpFechaIngresoEliminar.setValue(LocalDate.now());
        dpFechaIngresoEliminar.setConverter(new StringConverter<LocalDate>() {
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
    public void closeWindowsPrincipalEmpleado() throws IOException {
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
