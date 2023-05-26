package com.secadero.controlador;

import com.secadero.modelo.empleados.LeerEmpleado;
import com.secadero.modelo.empleados.ModificarEmpleado;
import com.secadero.modelo.empleados.leerComboBoxes.AreaEmpleado;
import com.secadero.modelo.empleados.leerComboBoxes.PuestoEmpleado;
import com.secadero.modelo.licencias.CrearLicencia;
import com.secadero.modelo.licencias.LeerLicencia;
import com.secadero.modelo.preRecibo.PreRecibo;
import com.secadero.modelo.preRecibo.modificaciones.Porcentaje;
import com.secadero.modelo.preRecibo.modificaciones.Sueldo;
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
import java.util.Date;
import java.util.Objects;

public class ControladorPreRecibo {

    @FXML
    private Button btnBuscarEmpleado;
    @FXML
    private Button btnGenerarFolrmulario;
    @FXML
    private Button btnModificaciones;
    @FXML
    private Button btnModificarPorcentaje;
    @FXML
    private Button btnModificarSalario;
    @FXML
    private Button btnRegresar;
    @FXML
    private Button btnVolver;
    @FXML
    private ComboBox<String> cbArea;
    @FXML
    private ComboBox<String> cbPuesto;
    @FXML
    private TableColumn<LeerEmpleado, String> colApellido;
    @FXML
    private TableColumn<LeerEmpleado, Integer> colDNI;
    @FXML
    private TableColumn<LeerEmpleado, Integer> colIDEmpleado;
    @FXML
    private TableColumn<LeerEmpleado, Integer> colLegajo;
    @FXML
    private TableColumn<LeerEmpleado, String> colNombre;
    @FXML
    private TableColumn<LeerEmpleado, String> colTelefono;
    @FXML
    private DatePicker dpFechaDesde;
    @FXML
    private DatePicker dpFechaHasta;
    @FXML
    private Label labApellidoEmpleado;
    @FXML
    private Label labErrorAntiguedad;
    @FXML
    private Label labErrorCuotaAgraria;
    @FXML
    private Label labErrorJubilacion;
    @FXML
    private Label labErrorLey;
    @FXML
    private Label labErrorObraSocial;
    @FXML
    private Label labErrorRetroactive;
    @FXML
    private Label labErrorSeguroSepelio;
    @FXML
    private Label labErrorSueldo;
    @FXML
    private Label labLegajoEmpleado;
    @FXML
    private Label labNombreEmpleado;
    @FXML
    private Label labConfirmacionPorcentaje;
    @FXML
    private Label labConfirmacionSalario;
    @FXML
    private Label labSalarioOK;
    @FXML
    private Label labPorcentajeOK;
    @FXML
    private Label labIDRecibo;
    @FXML
    private TableView<LeerEmpleado> tabEmpleados;
    @FXML
    private Tab tabModificaciones;
    @FXML
    private Tab tabPreRecibo;
    @FXML
    private TabPane tablaRecibo;
    @FXML
    private TextField textAntiguedad;
    @FXML
    private TextField textBuscarLegajoEmpleado;
    @FXML
    private TextField textCuotaAgraria;
    @FXML
    private TextField textJubilacion;
    @FXML
    private TextField textLey;
    @FXML
    private TextField textObraSocial;
    @FXML
    private TextField textRetroactive;
    @FXML
    private TextField textSeguroSepelio;
    @FXML
    private TextField textSueldo;


    ObservableList<LeerEmpleado> listTablaEmpleados;
    int index = -1;

    // -------------------------------------------- Inicialización ----------------------------------------------
    public void initialize() {
        inicializarTablaListaEmpleados();
        fechasInicializar();
        inicializarComboBoxBD();
        inicializarDatosPorcentajes();
        comboBoxSalariosArea();
        comboBoxSalariosPuesto();
    }

    public void inicializarComboBoxBD() {
        // --------------------- Area del Empleado -----------------------
        String datoEmpleado_Area;
        AreaEmpleado areaEmpleado = new AreaEmpleado();
        ObservableList<AreaEmpleado> empleado_Area = areaEmpleado.getArea_Empleado();
        for (int i = 0; i < empleado_Area.size(); i++) {
            datoEmpleado_Area = empleado_Area.get(i).getDescripcion();
            cbArea.getItems().addAll(datoEmpleado_Area);
        }

        // --------------------- Puesto del Empleado -----------------------
        String datoEmpleado_Puesto;
        PuestoEmpleado puestoEmpleado = new PuestoEmpleado();
        ObservableList<PuestoEmpleado> empleado_Puesto = puestoEmpleado.getPuesto_Empleado();
        for (int i = 0; i < empleado_Puesto.size(); i++) {
            datoEmpleado_Puesto = empleado_Puesto.get(i).getDescripcion();
            cbPuesto.getItems().addAll(datoEmpleado_Puesto);
        }
    }

    public void inicializarDatosPorcentajes() {
        Porcentaje datos = new Porcentaje();
        ObservableList<Porcentaje> recorrerDatos = datos.leerPorcentajes();
        for (int i = 0; i < recorrerDatos.size(); i++) {
            textAntiguedad.setText(String.valueOf(recorrerDatos.get(i).getAntiguedad()));
            textRetroactive.setText(String.valueOf(recorrerDatos.get(i).getRectroactivo()));
            textJubilacion.setText(String.valueOf(recorrerDatos.get(i).getJubilacion()));
            textLey.setText(String.valueOf(recorrerDatos.get(i).getLey()));
            textObraSocial.setText(String.valueOf(recorrerDatos.get(i).getObraSocial()));
            textSeguroSepelio.setText(String.valueOf(recorrerDatos.get(i).getSeguroSepelio()));
            textCuotaAgraria.setText(String.valueOf(recorrerDatos.get(i).getCuotaAgraria()));
        }
    }

    String valor1 = "";
    String valor2 = "";

    // ------------------------ Combo Boxes Seleccionados Salario ------------------------
    public void comboBoxSalariosArea(){
        String item = cbArea.getSelectionModel().getSelectedItem();
        cbArea.getSelectionModel().selectedItemProperty().addListener(this::cambioDeItem);
        cbArea.getSelectionModel().selectFirst();
    }

    public void cambioDeItem(ObservableValue<? extends String> observable, String valorAntiguo, String valorActual){
        Sueldo sueldo = new Sueldo();
        valor1 = valorActual;
        sueldo.leerSueldo(valor1, valor2, textSueldo, labIDRecibo);
    }

    public void comboBoxSalariosPuesto(){
        String item = cbPuesto.getSelectionModel().getSelectedItem();
        cbPuesto.getSelectionModel().selectedItemProperty().addListener(this::cambioDeItem2);
        cbPuesto.getSelectionModel().selectFirst();
    }

    public void cambioDeItem2(ObservableValue<? extends String> observable, String valorAntiguo, String valorActual2){
        Sueldo sueldo = new Sueldo();
        valor2 = valorActual2;
        sueldo.leerSueldo(valor1, valor2, textSueldo, labIDRecibo);
    }

    // ----------------------------------------- Tabla de Empleados ---------------------------------------------
    public void inicializarTablaListaEmpleados(){
        colNombre.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, String>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, String>("apellido"));
        colLegajo.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, Integer>("legajo"));
        colDNI.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, Integer>("dni"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, String>("telefono"));
        colIDEmpleado.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, Integer>("idempleados"));

        listTablaEmpleados = LeerEmpleado.listaEmpleadoGeneral();
        tabEmpleados.getColumns().setAll(colNombre, colApellido, colLegajo, colDNI, colTelefono, colIDEmpleado);
        tabEmpleados.getItems().setAll(listTablaEmpleados);
    }

    // ----------------------------- Tomo todos los datos al Seleccionar en la tabla ----------------------------
    @FXML
    private void getListaEmpleados(){
        index = tabEmpleados.getSelectionModel().getSelectedIndex();
        if (index <= -1){

            return;
        }
        labNombreEmpleado.setText(colNombre.getCellData(index));
        labApellidoEmpleado.setText(colApellido.getCellData(index));
        labLegajoEmpleado.setText(colLegajo.getCellData(index).toString());
    }

    // ----------------------------------- Búsqueda del Legajo del Empleado -------------------------------------
    @FXML
    private void buscarEmpleado() {
        ObservableList<LeerEmpleado> listBuscarEmpleado;
        listBuscarEmpleado = LeerEmpleado.buscarEmpleadoInformes(textBuscarLegajoEmpleado);
        tabEmpleados.getItems().setAll(listBuscarEmpleado);
    }

    //------------------------------------------ Evento Importante ----------------------------------------------
    @FXML
    private void generar() throws ParseException {
        String fechaInicioClave = dpFechaDesde.getEditor().getText();
        String fechaAnio = fechaInicioClave.substring(6, 10);
        String fechaMes = fechaInicioClave.substring(3, 5);
        String fechaDia = fechaInicioClave.substring(0, 2);
        String fechaModificadaInicio = (fechaAnio + "-" + fechaMes + "-" + fechaDia);
        String fechaFinClave = dpFechaHasta.getEditor().getText();
        String fechaAnio2 = fechaFinClave.substring(6, 10);
        String fechaMes2 = fechaFinClave.substring(3, 5);
        String fechaDia2 = fechaFinClave.substring(0, 2);
        String fechaModificadaFin = (fechaAnio2 + "-" + fechaMes2 + "-" + fechaDia2);

        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        // ------------------------- Fecha Inicio -------------------------------
        String fechaI = fechaModificadaInicio;
        Date fechaInicio = formatoFecha.parse(fechaI);
        // --------------------------- Fecha Fin --------------------------------
        String fechaF = fechaModificadaFin;
        Date fechaFin = formatoFecha.parse(fechaF);
        // --------------------- Diferencia entre Fechas ------------------------
        long Diferencias = fechaInicio.getTime() - fechaFin.getTime();
        long Cant_Dias = Diferencias / (1000 * 60 * 60 * 24);

        if(labLegajoEmpleado.getText().equals("")){
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error!");
            alerta.setContentText("Error debe de seleccionar un empleado antes");
            alerta.showAndWait();
        } else {
            if((-Cant_Dias) >= 0){
                PreRecibo preReciboD = new PreRecibo();
                preReciboD.preRecibo(labLegajoEmpleado, dpFechaDesde, dpFechaHasta);
                inicializarTablaListaEmpleados();
                fechasInicializar();
                limpiarCampos();
            } else {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error de Fechas!");
                alerta.setContentText("La Fecha de Inicio debe de ser Antes de la Fecha de Fin");
                alerta.showAndWait();
            }
        }
    }

    @FXML
    private void modificarSalario(){
        camposObligatoriosSalario();
        if (Objects.equals(labErrorSueldo.getText(), "")) {
            validacionCamposCaracteresSalario();
            if (Objects.equals(labErrorSueldo.getText(), "")) {
                validacionCamposLongitudSalario();
                if (Objects.equals(labErrorSueldo.getText(), "")) {
                    Sueldo sueldo = new Sueldo();
                    sueldo.modificarSueldo(textSueldo, labIDRecibo, labConfirmacionSalario);

                    if(Objects.equals(labConfirmacionSalario.getText(), "OK")){
                        labConfirmacionSalario.setText("");
                        labSalarioOK.setText("☑");
                        comboBoxSalariosArea();
                        comboBoxSalariosPuesto();
                    }
                }
            }
        }
    }

    @FXML
    private void modificarPorcentaje(){
        camposObligatoriosPorcentajes();
        if (Objects.equals(labErrorAntiguedad.getText(), "") && Objects.equals(labErrorRetroactive.getText(), "") && Objects.equals(labErrorJubilacion.getText(), "") && Objects.equals(labErrorLey.getText(), "") && Objects.equals(labErrorObraSocial.getText(), "") && Objects.equals(labErrorSeguroSepelio.getText(), "") && Objects.equals(labErrorCuotaAgraria.getText(), "")){
            validacionCamposCaracteresPorcentajes();
            if (Objects.equals(labErrorAntiguedad.getText(), "") && Objects.equals(labErrorRetroactive.getText(), "") && Objects.equals(labErrorJubilacion.getText(), "") && Objects.equals(labErrorLey.getText(), "") && Objects.equals(labErrorObraSocial.getText(), "") && Objects.equals(labErrorSeguroSepelio.getText(), "") && Objects.equals(labErrorCuotaAgraria.getText(), "")){
                validacionCamposLongitudPorcentajes();
                if (Objects.equals(labErrorAntiguedad.getText(), "") && Objects.equals(labErrorRetroactive.getText(), "") && Objects.equals(labErrorJubilacion.getText(), "") && Objects.equals(labErrorLey.getText(), "") && Objects.equals(labErrorObraSocial.getText(), "") && Objects.equals(labErrorSeguroSepelio.getText(), "") && Objects.equals(labErrorCuotaAgraria.getText(), "")){
                    Porcentaje porcentaje = new Porcentaje();
                    porcentaje.modificarPorcentajes(textAntiguedad, textRetroactive, textJubilacion, textLey, textObraSocial, textSeguroSepelio, textCuotaAgraria, labConfirmacionPorcentaje);

                    if(Objects.equals(labConfirmacionPorcentaje.getText(), "OK")){
                        labConfirmacionPorcentaje.setText("");
                        labPorcentajeOK.setText("☑");
                        inicializarDatosPorcentajes();
                    }
                }
            }
        }
    }

    //---------------------------------------- Acción Simple del Botón ------------------------------------------
    @FXML
    private void modificaciones(){
        SingleSelectionModel<Tab> modeloSeleccion = tablaRecibo.getSelectionModel();
        modeloSeleccion.select(tabModificaciones);
        limpiarCamposErrorPorcentajes();
        inicializarDatosPorcentajes();
        comboBoxSalariosArea();
        comboBoxSalariosPuesto();
        labSalarioOK.setText("");
        labPorcentajeOK.setText("");
    }

    @FXML
    private void regresar(){
        SingleSelectionModel<Tab> modeloSeleccion = tablaRecibo.getSelectionModel();
        modeloSeleccion.select(tabPreRecibo);
        labSalarioOK.setText("");
        labPorcentajeOK.setText("");
    }

    @FXML
    private void volverPrincipal() throws IOException {
        closeWindowsPrincipalPreRecibo();
    }

    //---------------------------------------- Validación de Caracteres ------------------------------------------
    public static boolean validarNumeros(String datos){
        return datos.matches("[0-9]*");
    }

    //----------------------------------------- Limpiador de Campos ----------------------------------------------
    private void limpiarCampos(){
        textBuscarLegajoEmpleado.setText("");
        labNombreEmpleado.setText("");
        labApellidoEmpleado.setText("");
        labLegajoEmpleado.setText("");
        fechasInicializar();
    }

    private void limpiarCamposErrorPorcentajes(){
        labErrorSueldo.setText("");
        labErrorAntiguedad.setText("");
        labErrorRetroactive.setText("");
        labErrorJubilacion.setText("");
        labErrorLey.setText("");
        labErrorObraSocial.setText("");
        labErrorSeguroSepelio.setText("");
        labErrorCuotaAgraria.setText("");
    }

    //---------------------------------------- Comprobación de Campos -------------------------------------------
    private void camposObligatoriosSalario(){
        if(textSueldo.getText().trim().isEmpty() || textSueldo.getText() == null){
            labErrorSueldo.setText("Campo Obligatorio");
        } else {
            labErrorSueldo.setText("");
        }
    }

    private void validacionCamposLongitudSalario(){
        String sueldo = textSueldo.getText().trim();
        sueldo = sueldo.replaceAll("\\s+", "");
        if(sueldo.length() > 7){
            labErrorSueldo.setText("Disponible hasta 7 dígitos");
        } else {
            labErrorSueldo.setText("");
        }
    }

    private void validacionCamposCaracteresSalario(){
        String sueldo = textSueldo.getText().trim();
        sueldo = sueldo.replaceAll("\\s+", "");
        if(validarNumeros(sueldo)){
            labErrorSueldo.setText("");
        } else {
            labErrorSueldo.setText("Solo Números");
        }
    }

    private void camposObligatoriosPorcentajes(){
        if(textAntiguedad.getText().trim().isEmpty() || textAntiguedad.getText() == null){
            labErrorAntiguedad.setText("Campo Obligatorio");
        } else {
            labErrorAntiguedad.setText("");
        }
        if(textRetroactive.getText().trim().isEmpty() || textRetroactive.getText() == null){
            labErrorRetroactive.setText("Campo Obligatorio");
        } else {
            labErrorRetroactive.setText("");
        }
        if(textJubilacion.getText().trim().isEmpty() || textJubilacion.getText() == null){
            labErrorJubilacion.setText("Campo Obligatorio");
        } else {
            labErrorJubilacion.setText("");
        }
        if(textLey.getText().trim().isEmpty() || textLey.getText() == null){
            labErrorLey.setText("Campo Obligatorio");
        } else {
            labErrorLey.setText("");
        }
        if(textObraSocial.getText().trim().isEmpty() || textObraSocial.getText() == null){
            labErrorObraSocial.setText("Campo Obligatorio");
        } else {
            labErrorObraSocial.setText("");
        }
        if(textSeguroSepelio.getText().trim().isEmpty() || textSeguroSepelio.getText() == null){
            labErrorSeguroSepelio.setText("Campo Obligatorio");
        } else {
            labErrorSeguroSepelio.setText("");
        }
        if(textCuotaAgraria.getText().trim().isEmpty() || textCuotaAgraria.getText() == null){
            labErrorCuotaAgraria.setText("Campo Obligatorio");
        } else {
            labErrorCuotaAgraria.setText("");
        }
    }

    private void validacionCamposLongitudPorcentajes(){
        String antiguedad = textAntiguedad.getText().trim();
        String retroactive = textRetroactive.getText().trim();
        String jubilacion = textJubilacion.getText().trim();
        String ley = textLey.getText().trim();
        String obraSocial = textObraSocial.getText().trim();
        String seguroSepelio = textSeguroSepelio.getText().trim();
        String cuotaAgraria = textCuotaAgraria.getText().trim();
        antiguedad = antiguedad.replaceAll("\\s+", "");
        retroactive = retroactive.replaceAll("\\s+", "");
        jubilacion = jubilacion.replaceAll("\\s+", "");
        ley = ley.replaceAll("\\s+", "");
        obraSocial = obraSocial.replaceAll("\\s+", "");
        seguroSepelio = seguroSepelio.replaceAll("\\s+", "");
        cuotaAgraria = cuotaAgraria.replaceAll("\\s+", "");

        if(antiguedad.length() > 4){
            labErrorAntiguedad.setText("Solo hasta 4 dígitos");
        } else {
            labErrorAntiguedad.setText("");
        }
        if(retroactive.length() > 4){
            labErrorRetroactive.setText("Solo hasta 4 dígitos");
        } else {
            labErrorRetroactive.setText("");
        }
        if(jubilacion.length() > 4){
            labErrorJubilacion.setText("Solo hasta 4 dígitos");
        } else {
            labErrorJubilacion.setText("");
        }
        if(ley.length() > 4){
            labErrorLey.setText("Solo hasta 4 dígitos");
        } else {
            labErrorLey.setText("");
        }
        if(obraSocial.length() > 4){
            labErrorObraSocial.setText("Solo hasta 4 dígitos");
        } else {
            labErrorObraSocial.setText("");
        }
        if(seguroSepelio.length() > 4){
            labErrorSeguroSepelio.setText("Solo hasta 4 dígitos");
        } else {
            labErrorSeguroSepelio.setText("");
        }
        if(cuotaAgraria.length() > 4){
            labErrorCuotaAgraria.setText("Solo hasta 4 dígitos");
        } else {
            labErrorCuotaAgraria.setText("");
        }
    }

    private void validacionCamposCaracteresPorcentajes(){
        String antiguedad = textAntiguedad.getText().trim();
        String retroactive = textRetroactive.getText().trim();
        String jubilacion = textJubilacion.getText().trim();
        String ley = textLey.getText().trim();
        String obraSocial = textObraSocial.getText().trim();
        String seguroSepelio = textSeguroSepelio.getText().trim();
        String cuotaAgraria = textCuotaAgraria.getText().trim();
        antiguedad = antiguedad.replaceAll("\\s+", "");
        retroactive = retroactive.replaceAll("\\s+", "");
        jubilacion = jubilacion.replaceAll("\\s+", "");
        ley = ley.replaceAll("\\s+", "");
        obraSocial = obraSocial.replaceAll("\\s+", "");
        seguroSepelio = seguroSepelio.replaceAll("\\s+", "");
        cuotaAgraria = cuotaAgraria.replaceAll("\\s+", "");

        if(validarNumeros(antiguedad)){
            labErrorAntiguedad.setText("");
        } else {
            labErrorAntiguedad.setText("Solo Números");
        }
        if(validarNumeros(retroactive)){
            labErrorRetroactive.setText("");
        } else {
            labErrorRetroactive.setText("Solo Números");
        }
        if(validarNumeros(jubilacion)){
            labErrorJubilacion.setText("");
        } else {
            labErrorJubilacion.setText("Solo Números");
        }
        if(validarNumeros(ley)){
            labErrorLey.setText("");
        } else {
            labErrorLey.setText("Solo Números");
        }
        if(validarNumeros(obraSocial)){
            labErrorObraSocial.setText("");
        } else {
            labErrorObraSocial.setText("Solo Números");
        }
        if(validarNumeros(seguroSepelio)){
            labErrorSeguroSepelio.setText("");
        } else {
            labErrorSeguroSepelio.setText("Solo Números");
        }
        if(validarNumeros(cuotaAgraria)){
            labErrorCuotaAgraria.setText("");
        } else {
            labErrorCuotaAgraria.setText("Solo Números");
        }
    }
    // ---------------------------------------- Fechas Actuales Inicializadas ----------------------------------------
    public void fechasInicializar() {
        dpFechaDesde.setValue(LocalDate.now());
        dpFechaDesde.setConverter(new StringConverter<LocalDate>() {
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

        dpFechaHasta.setValue(LocalDate.now());
        dpFechaHasta.setConverter(new StringConverter<LocalDate>() {
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
    public void closeWindowsPrincipalPreRecibo() throws IOException {
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
