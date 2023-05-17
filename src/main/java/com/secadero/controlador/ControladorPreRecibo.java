package com.secadero.controlador;

import com.secadero.modelo.empleados.LeerEmpleado;
import com.secadero.modelo.preRecibo.PreRecibo;
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

public class ControladorPreRecibo {
    @FXML
    private Button btnBuscarEmpleado;
    @FXML
    private Button btnGenerarFolrmulario;
    @FXML
    private Button btnVolver;
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
    private Label labLegajoEmpleado;
    @FXML
    private Label labNombreEmpleado;
    @FXML
    private TableView<LeerEmpleado> tabEmpleados;
    @FXML
    private TextField textBuscarLegajoEmpleado;

    ObservableList<LeerEmpleado> listTablaEmpleados;
    int index = -1;

    // -------------------------------------------- Inicialización ----------------------------------------------
    public void initialize() {
        inicializarTablaListaEmpleados();
        fechasInicializar();
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

    //---------------------------------------- Acción Simple del Botón ------------------------------------------
    @FXML
    private void volverPrincipal() throws IOException {
        closeWindowsPrincipalPreRecibo();
    }

    //----------------------------------------- Limpiador de Campos ----------------------------------------------
    private void limpiarCampos(){
        textBuscarLegajoEmpleado.setText("");
        labNombreEmpleado.setText("");
        labApellidoEmpleado.setText("");
        labLegajoEmpleado.setText("");
        fechasInicializar();
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
