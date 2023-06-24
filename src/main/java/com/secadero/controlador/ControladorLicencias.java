package com.secadero.controlador;

import com.secadero.modelo.cronograma.LeerCronograma;
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
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
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
    private ComboBox<String> cbBuscarEmpleadoLista;
    @FXML
    private ComboBox<String> cbBuscarEmpleado;
    @FXML
    private TableColumn<LeerEmpleado, String> colApellido;
    @FXML
    private TableColumn<LeerEmpleado, String> colApellidoEmpleadoCrear;
    @FXML
    private TableColumn<LeerEmpleado, Integer> colDNIEmpleadoCrear;
    @FXML
    private TableColumn<LeerLicencia, String> colFechaFin;
    @FXML
    private TableColumn<LeerLicencia, String> colFechaInicio;
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
    private TableColumn<LeerEmpleado, Integer> colDNI;
    @FXML
    private TableColumn<LeerEmpleado, String> colTelefonoEmpleadoCrear;
    @FXML
    private TableColumn<LeerLicencia, String> colTipoLicencia;
    @FXML
    private TableColumn<LeerLicencia, String> colDescripcion;
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
    private Label labErrorDescripcionCrear;
    @FXML
    private Label labErrorDescripcionModificar;
    @FXML
    private Label labDescripcionCrear1;
    @FXML
    private Label labDescripcionCrear2;
    @FXML
    private Label labDescripcionModificar1;
    @FXML
    private Label labDescripcionModificar2;
    @FXML
    private Label labDescripcionEliminar1;
    @FXML
    private Label labDescripcionEliminar2;
    @FXML
    private Label labErrorDescripcionEliminar;
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
    @FXML
    private TextField textBuscarLegajoEmpleadoCrear;
    @FXML
    private TextField textDescripcionLicenciaCrear;
    @FXML
    private TextField textDescripcionLicenciaModificar;
    @FXML
    private Button btnConsultaCronogramaCrear;
    @FXML
    private Button btnConsultaCronogramaModificar;
    @FXML
    private Tab tabConsultaCronograma;
    @FXML
    private TableView<LeerCronograma> tablaCronograma;
    @FXML
    private TableColumn<LeerCronograma, String> colFecha;
    @FXML
    private TableColumn<LeerCronograma, String> colTurno;
    @FXML
    private TableColumn<LeerCronograma, String> colHoraEntrada;
    @FXML
    private TableColumn<LeerCronograma, String> colHoraSalida;
    @FXML
    private TableColumn<LeerCronograma, Integer> colIDCronograma;
    @FXML
    private Label labNombreEmpleadoConsulta;
    @FXML
    private Label labApellidoEmpleadoConsulta;
    @FXML
    private Label labLegajoEmpleadoConsulta;
    @FXML
    private Label labIDEmpleadoConsulta;
    @FXML
    private DatePicker dpBuscarFecha;
    @FXML
    private TextField textDescripcionLicenciaEliminar;

    ObservableList<LeerEmpleado> listTablaEmpleados;
    int index = -1;

    ObservableList<LeerLicencia> listLicencia;
    int index2 = -1;

    ObservableList<LeerEmpleado> listEmpleadoLicencia;
    int index3 = -1;

    ObservableList<LeerCronograma> listCronograma;
    String regresarConsultaCronograma = "0";

    // -------------------------------------------- Inicialización ----------------------------------------------
    public void initialize() {
        inicializarTablaListaEmpleados();
        inicializarTablaEmpleado();
        inicializarComboBoxBD();
        fechasInicializar();
        fechaConsultaCronograma();
        cbTipoLicenciaCrear.getSelectionModel().getSelectedItem();
        cbTipoLicenciaCrear.getSelectionModel().selectedItemProperty().addListener(this::cambioDeItem);
        String[] tipoBuscar = {"Legajo", "Nombre", "Apellido"};
        cbBuscarEmpleadoLista.getItems().addAll(tipoBuscar);
        cbBuscarEmpleado.getItems().addAll(tipoBuscar);
        cbBuscarEmpleadoLista.getSelectionModel().selectFirst();
        cbBuscarEmpleado.getSelectionModel().selectFirst();
        textDescripcionLicenciaCrear.setDisable(true);
        textDescripcionLicenciaCrear.setVisible(false);
        labDescripcionCrear1.setVisible(false);
        labDescripcionCrear2.setVisible(false);
        textDescripcionLicenciaModificar.setDisable(true);
        textDescripcionLicenciaModificar.setVisible(false);
        labDescripcionModificar1.setVisible(false);
        labDescripcionModificar2.setVisible(false);
        textDescripcionLicenciaEliminar.setDisable(true);
        textDescripcionLicenciaEliminar.setVisible(false);
        labDescripcionEliminar1.setVisible(false);
        labDescripcionEliminar2.setVisible(false);

        cbBuscarEmpleadoLista.setOnAction(event -> {
            String selectedItem = cbBuscarEmpleadoLista.getSelectionModel().getSelectedItem();
            textBuscarLegajoEmpleado.setPromptText("Ingrese el " + selectedItem);
        });

        cbBuscarEmpleado.setOnAction(event -> {
            String selectedItem = cbBuscarEmpleado.getSelectionModel().getSelectedItem();
            textBuscarLegajoEmpleadoCrear.setPromptText("Ingrese el " + selectedItem);
        });
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

    // ----------------------------------------- Tabla de Licencias ---------------------------------------------
    public void inicializarTablaLicencias(){
        colFechaInicio.setCellValueFactory(new PropertyValueFactory<LeerLicencia, String>("fecha_Inicio"));
        colFechaFin.setCellValueFactory(new PropertyValueFactory<LeerLicencia, String>("fecha_Fin"));
        colTipoLicencia.setCellValueFactory(new PropertyValueFactory<LeerLicencia, String>("descripcion"));
        colIDLicencia.setCellValueFactory(new PropertyValueFactory<LeerLicencia, Integer>("idLicencias"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<LeerLicencia, String>("descripcionLicencia"));

        listLicencia = LeerLicencia.listaLicencia(labIDEmpleadoLista);
        tablaLicencia.getColumns().setAll(colFechaInicio, colFechaFin, colTipoLicencia, colIDLicencia, colDescripcion);
        tablaLicencia.getItems().setAll(listLicencia);
    }

    // ----------------------------------------- Tabla de Cronograma ---------------------------------------------
    public void inicializarTablaCronograma(){
        colFecha.setCellValueFactory(new PropertyValueFactory<LeerCronograma, String>("fecha"));
        colTurno.setCellValueFactory(new PropertyValueFactory<LeerCronograma, String>("turno"));
        colHoraEntrada.setCellValueFactory(new PropertyValueFactory<LeerCronograma, String>("horario_entrada"));
        colHoraSalida.setCellValueFactory(new PropertyValueFactory<LeerCronograma, String>("horario_salida"));
        colIDCronograma.setCellValueFactory(new PropertyValueFactory<LeerCronograma, Integer>("idCronograma"));

        listCronograma = LeerCronograma.listaCronogramaConsulta(labIDEmpleadoConsulta);
        tablaCronograma.getColumns().setAll(colFecha, colTurno, colHoraEntrada, colHoraSalida, colIDCronograma);
        tablaCronograma.getItems().setAll(listCronograma);
    }

    // -------------------------------------- Tabla de Empleados Crear ------------------------------------------
    public void inicializarTablaEmpleado(){
        colNombreEmpleadoCrear.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, String>("nombre"));
        colApellidoEmpleadoCrear.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, String>("apellido"));
        colLegajoEmpleadoCrear.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, Integer>("legajo"));
        colDNIEmpleadoCrear.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, Integer>("dni"));
        colTelefonoEmpleadoCrear.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, String>("telefono"));
        colIDEmpleadoCrear.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, Integer>("idempleados"));

        listEmpleadoLicencia = LeerEmpleado.listaEmpleadoGeneral();
        tabEmpleadosCrear.getColumns().setAll(colNombreEmpleadoCrear, colApellidoEmpleadoCrear, colLegajoEmpleadoCrear, colDNIEmpleadoCrear, colTelefonoEmpleadoCrear, colIDEmpleadoCrear);
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
        dpFechaInicioModificar.getEditor().setText(colFechaInicio.getCellData(index2));
        dpFechaFinModificar.getEditor().setText(colFechaFin.getCellData(index2));
        cbTipoLicenciaModificar.getSelectionModel().select(colTipoLicencia.getCellData(index2));
        dpFechaModificarDuplicadaInicio.getEditor().setText(colFechaInicio.getCellData(index2));
        dpFechaModificarDuplicadaFin.getEditor().setText(colFechaFin.getCellData(index2));
        textDescripcionLicenciaModificar.setText(colDescripcion.getCellData(index2));
        comboBoxModificar();

        labIDLicenciaEliminar.setText(colIDLicencia.getCellData(index2).toString());
        dpFechaInicioEliminar.getEditor().setText(colFechaInicio.getCellData(index2));
        dpFechaFinEliminar.getEditor().setText(colFechaFin.getCellData(index2));
        cbTipoLicenciaEliminar.getSelectionModel().select(colTipoLicencia.getCellData(index2));
        textDescripcionLicenciaEliminar.setText(colDescripcion.getCellData(index2));
        comboBoxEliminar();

        labIDLicenciaLista.setText(colIDLicencia.getCellData(index2).toString());

        String fechaInicioM = colFechaInicio.getCellData(index2);
        dpFechaInicioModificar.setValue(LocalDate.of(Integer.parseInt(fechaInicioM.substring(6, 10)), Integer.parseInt(fechaInicioM.substring(3, 5)), Integer.parseInt(fechaInicioM.substring(0, 2))));
        String fechaFinM = colFechaFin.getCellData(index2);
        dpFechaFinModificar.setValue(LocalDate.of(Integer.parseInt(fechaFinM.substring(6, 10)), Integer.parseInt(fechaFinM.substring(3, 5)), Integer.parseInt(fechaFinM.substring(0, 2))));

        textTipoLicenciaDetalleEliminar(cbTipoLicenciaEliminar.getSelectionModel().getSelectedItem());
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
        textTipoLicenciaDetalleCrear(valorActual);

        labDiasDisponiblesCrear.setText(dato);
        labErrorDescripcionCrear.setText("");
    }

    public void cambioDeItem2(ObservableValue<? extends String> observable, String valorAntiguo, String valorActual){
        LeerLicencia licenciaLeer = new LeerLicencia();
        String dato = licenciaLeer.comboBoxTipoLicencia(valorActual, labIDEmpleadoModificar, dpFechaInicioModificar);
        textTipoLicenciaDetalleModificar(valorActual);

        labDiasDisponiblesModificar.setText(dato);
        labErrorDescripcionModificar.setText("");
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
        limpiarCamposGeneralizada();
        ObservableList<LeerEmpleado> listBuscarEmpleado;
        listBuscarEmpleado = LeerEmpleado.buscarEmpleadoGeneral(textBuscarLegajoEmpleado, cbBuscarEmpleadoLista
        );
        if(textBuscarLegajoEmpleado.getText().equals("")){
            String dato = cbBuscarEmpleadoLista.getSelectionModel().getSelectedItem();
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("");
            alerta.setContentText("Debe de completar el "+ dato +" para Buscar");
            Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/icono_Alerta.png")));
            Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
            stage.getIcons().add(icon);
            alerta.showAndWait();
        } else {
            tablaListaEmpleados.getItems().setAll(listBuscarEmpleado);
            labIDEmpleadoLista.setText("0");
            inicializarTablaLicencias();
        }
    }

    @FXML
    private void btnBuscarLicencia(){
        labIDLicenciaModificar.setText("");
        labIDLicenciaEliminar.setText("");
        ObservableList<LeerLicencia> listaBuscarLicencia;
        listaBuscarLicencia = LeerLicencia.buscarLicenciaFechaInicio(labIDEmpleadoLista, dpBuscarFechaInicio);
        tablaLicencia.getItems().setAll(listaBuscarLicencia);
    }

    @FXML
    private void btnBuscarEmpleadoCrear(){
        ObservableList<LeerEmpleado> listBuscarEmpleadoCrear;
        listBuscarEmpleadoCrear = LeerEmpleado.buscarEmpleadoGeneralCrear(textBuscarLegajoEmpleadoCrear, cbBuscarEmpleado);
        tabEmpleadosCrear.getItems().setAll(listBuscarEmpleadoCrear);
    }

    @FXML
    private void actualizarTabla() {
        labIDLicenciaLista.setText("0");
        labIDEmpleadoLista.setText("0");
        textBuscarLegajoEmpleado.setText("");
        tablaListaEmpleados.getItems().setAll(listEmpleadoLicencia);
        cbBuscarEmpleadoLista.getSelectionModel().getSelectedItem();
        inicializarTablaListaEmpleados();
        inicializarTablaLicencias();
        limpiarCamposModificar();
        limpiarCamposEliminar();
    }

    @FXML
    private void btnBuscarCronograma(){
        ObservableList<LeerCronograma> listaBuscarCronograma;
        listaBuscarCronograma = LeerCronograma.buscarFechaCronogramaConsulta(labIDEmpleadoConsulta, dpBuscarFecha);
        tablaCronograma.getItems().setAll(listaBuscarCronograma);
    }

    @FXML
    private void actualizarTablaConsulta(){
        inicializarTablaCronograma();
        fechaConsultaCronograma();
    }

    //---------------------------------------------- Eventos Importantes ----------------------------------------
    @FXML
    private void guardar() throws ParseException, SQLException {
        if(labIDEmpleadoCrear.getText().trim().isEmpty() || labIDEmpleadoCrear.getText() == null){
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("");
            alerta.setContentText("Para guardar debes de tomar un empleado");
            Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/icono_Alerta.png")));
            Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
            stage.getIcons().add(icon);
            alerta.showAndWait();
        } else {
            String selectedItem = cbTipoLicenciaCrear.getSelectionModel().getSelectedItem();
            if(selectedItem.equals("Enfermedad") || selectedItem.equals("Accidente")){
                camposObligatoriosCrear();
                if(Objects.equals(labErrorDescripcionCrear.getText(), "")) {
                    validacionCamposCaracteresCrear();
                    if (Objects.equals(labErrorDescripcionCrear.getText(), "")) {
                        validacionCamposLongitudCrear();
                        if (Objects.equals(labErrorDescripcionCrear.getText(), "")) {
                            CrearLicencia licenciaCrear = new CrearLicencia();
                            licenciaCrear.agregarLicencia(labIDEmpleadoCrear, labDiasDisponiblesCrear, dpFechaInicioCrear, dpFechaFinCrear, cbTipoLicenciaCrear, textDescripcionLicenciaCrear, labLimpiarCamposCrear);

                            if(Objects.equals(labLimpiarCamposCrear.getText(), "OK")){
                                labLimpiarCamposCrear.setText("");
                                labIDLicenciaLista.setText("0");
                                labIDEmpleadoLista.setText("0");
                                inicializarTablaEmpleado();
                                inicializarTablaLicencias();
                                regresarCLista();
                                limpiarCamposCrear();
                            }
                        }
                    }
                }
            } else if(selectedItem.equals("Vacaciones") || selectedItem.equals("Matrimonio") || selectedItem.equals("Muerte Familiar")){
                CrearLicencia licenciaCrear = new CrearLicencia();
                licenciaCrear.agregarLicencia(labIDEmpleadoCrear, labDiasDisponiblesCrear, dpFechaInicioCrear, dpFechaFinCrear, cbTipoLicenciaCrear, textDescripcionLicenciaCrear, labLimpiarCamposCrear);

                if(Objects.equals(labLimpiarCamposCrear.getText(), "OK")){
                    labLimpiarCamposCrear.setText("");
                    labIDLicenciaLista.setText("0");
                    labIDEmpleadoLista.setText("0");
                    inicializarTablaEmpleado();
                    inicializarTablaLicencias();
                    regresarCLista();
                    limpiarCamposCrear();
                }
            }
        }
    }

    @FXML
    private void modificar() throws SQLException, ParseException {
        if(labIDLicenciaModificar.getText().trim().isEmpty() || labIDLicenciaModificar.getText() == null){
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("");
            alerta.setContentText("Para Modificar debes de tomar una Licencia del empleado");
            Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/icono_Alerta.png")));
            Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
            stage.getIcons().add(icon);
            alerta.showAndWait();

        } else {
            String selectedItem = cbTipoLicenciaModificar.getSelectionModel().getSelectedItem();
            if(selectedItem.equals("Enfermedad") || selectedItem.equals("Accidente")) {
                camposObligatoriosModificar();
                if (Objects.equals(labErrorDescripcionModificar.getText(), "")) {
                    validacionCamposCaracteresModificar();
                    if (Objects.equals(labErrorDescripcionModificar.getText(), "")) {
                        validacionCamposLongitudModificar();
                        if (Objects.equals(labErrorDescripcionModificar.getText(), "")) {
                            ModificarLicencia licenciaModificar = new ModificarLicencia();
                            licenciaModificar.modificarLicencia(labIDEmpleadoModificar, labIDLicenciaModificar, labDiasDisponiblesModificar, dpFechaModificarDuplicadaInicio, dpFechaModificarDuplicadaFin, dpFechaInicioModificar, dpFechaFinModificar, cbTipoLicenciaModificar, textDescripcionLicenciaModificar, labLimpiarCamposModificar);

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
                        }
                    }
                }
            } else if(selectedItem.equals("Vacaciones") || selectedItem.equals("Matrimonio") || selectedItem.equals("Muerte Familiar")){
                ModificarLicencia licenciaModificar = new ModificarLicencia();
                licenciaModificar.modificarLicencia(labIDEmpleadoModificar, labIDLicenciaModificar, labDiasDisponiblesModificar, dpFechaModificarDuplicadaInicio, dpFechaModificarDuplicadaFin, dpFechaInicioModificar, dpFechaFinModificar, cbTipoLicenciaModificar, textDescripcionLicenciaModificar, labLimpiarCamposModificar);

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
            }
        }
    }

    @FXML
    private void eliminar() throws ParseException {
        String fechaInicioClave = dpFechaInicioEliminar.getEditor().getText();
        String fechaAnio = fechaInicioClave.substring(6, 10);
        String fechaMes = fechaInicioClave.substring(3, 5);
        String fechaDia = fechaInicioClave.substring(0, 2);
        String fechaModificadaInicio = (fechaAnio + "-" + fechaMes + "-" + fechaDia);
        String fechaFinClave = dpFechaFinEliminar.getEditor().getText();
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

        Date fechaActualPais = new Date();
        String Fecha_Actual = (formatoFecha.format(fechaActualPais));
        Date fechaActual = formatoFecha.parse(Fecha_Actual);
        long DiferenciasInicio = fechaActual.getTime() - fechaInicio.getTime();
        long Cant_DiasInicio = DiferenciasInicio / (1000 * 60 * 60 * 24);
        long DiferenciasFin= fechaActual.getTime() - fechaFin.getTime();
        long Cant_DiasFin= DiferenciasFin/ (1000 * 60 * 60 * 24);

        if (labIDLicenciaEliminar.getText().trim().isEmpty() || labIDLicenciaEliminar.getText() == null){
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("");
            alerta.setContentText("Debe de seleccionar antes una Licencia de dicho empleado para Eliminarlo");
            Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/icono_Alerta.png")));
            Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
            stage.getIcons().add(icon);
            alerta.showAndWait();
            btnRegresarELista.requestFocus();
        } else{
            if((-Cant_DiasInicio) >= 0 && (-Cant_DiasFin) >= 0){
                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setTitle("");
                alerta.setContentText("¿Desea Eliminar la Licencia del Empleado?");
                Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/icono_Alerta.png")));
                Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
                stage.getIcons().add(icon);
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
                Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/icono_Alerta.png")));
                Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
                stage.getIcons().add(icon);
                alerta.showAndWait();

            }
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
        if(labIDLicenciaModificar.getText().trim().isEmpty() || labIDLicenciaModificar.getText() == null){
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("");
            alerta.setContentText("Para Modificar debes de tomar una Licencia del empleado");
            Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/icono_Alerta.png")));
            Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
            stage.getIcons().add(icon);
            alerta.showAndWait();
        } else {
            SingleSelectionModel<Tab> modeloSeleccion = panelPestaniasLicencias.getSelectionModel();
            modeloSeleccion.select(tabModificarLicencia);
            dpFechaInicioModificar.requestFocus();
        }
    }

    @FXML
    private void eliminarLicencia() {
        if(labIDLicenciaEliminar.getText().trim().isEmpty() || labIDLicenciaEliminar.getText() == null){
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("");
            alerta.setContentText("Para Eliminar debes de tomar una Licencia del empleado");
            Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/icono_Alerta.png")));
            Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
            stage.getIcons().add(icon);
            alerta.showAndWait();
        } else {
            SingleSelectionModel<Tab> modeloSeleccion = panelPestaniasLicencias.getSelectionModel();
            modeloSeleccion.select(tabEliminarLicencia);
            btnEliminar.requestFocus();
        }
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
    private void consultaCronogramaCrear(){
        if(!labIDEmpleadoCrear.getText().equals("")){
            SingleSelectionModel<Tab> modeloSeleccion = panelPestaniasLicencias.getSelectionModel();
            modeloSeleccion.select(tabConsultaCronograma);
            String ID = labIDEmpleadoCrear.getText();
            labIDEmpleadoConsulta.setText(ID);
            labNombreEmpleadoConsulta.setText(labNombreEmpleadoCrear.getText());
            labApellidoEmpleadoConsulta.setText(labApellidoEmpleadoCrear.getText());
            labLegajoEmpleadoConsulta.setText(labLegajoEmpleadoCrear.getText());
            inicializarTablaCronograma();
        } else {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("");
            alerta.setContentText("Debe de seleccionar un Empleado antes para Consultar el Cronograma");
            Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/icono_Alerta.png")));
            Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
            stage.getIcons().add(icon);
            alerta.showAndWait();
        }
        regresarConsultaCronograma = "0";
    }

    @FXML
    private void consultaCronogramaModificar(){
        if(!labIDEmpleadoModificar.getText().equals("")){
            SingleSelectionModel<Tab> modeloSeleccion = panelPestaniasLicencias.getSelectionModel();
            modeloSeleccion.select(tabConsultaCronograma);
            String ID = labIDEmpleadoModificar.getText();
            labIDEmpleadoConsulta.setText(ID);
            labNombreEmpleadoConsulta.setText(labNombreEmpleadoModificar.getText());
            labApellidoEmpleadoConsulta.setText(labApellidoEmpleadoModificar.getText());
            labLegajoEmpleadoConsulta.setText(labLegajoEmpleadoModificar.getText());
            inicializarTablaCronograma();
        } else {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("");
            alerta.setContentText("Debe de seleccionar un Empleado antes para Consultar el Cronograma");
            Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/imagenes/icono_Alerta.png")));
            Stage stage = (Stage) alerta.getDialogPane().getScene().getWindow();
            stage.getIcons().add(icon);
            alerta.showAndWait();
        }
        regresarConsultaCronograma = "1";
    }

    @FXML
    private void regresarCCMenu(){
        if(Objects.equals(regresarConsultaCronograma, "0")){
            SingleSelectionModel<Tab> modeloSeleccion = panelPestaniasLicencias.getSelectionModel();
            modeloSeleccion.select(tabRegistrarLicencia);
        } else if(Objects.equals(regresarConsultaCronograma, "1")){
            SingleSelectionModel<Tab> modeloSeleccion = panelPestaniasLicencias.getSelectionModel();
            modeloSeleccion.select(tabModificarLicencia);
        }
    }

    @FXML
    private void volverEmpleados() throws IOException {
        closeWindowsPrincipalLicencias();
    }

    //---------------------------------------- Comprobación de Campos -------------------------------------------
    private void camposObligatoriosCrear(){
        if (textDescripcionLicenciaCrear.getText().trim().isEmpty() || textDescripcionLicenciaCrear.getText() == null) {
            labErrorDescripcionCrear.setText("Campo Obligatorio");
        } else {
            labErrorDescripcionCrear.setText("");
        }
    }

    private void validacionCamposLongitudCrear(){
        String motivo = textDescripcionLicenciaCrear.getText().trim();
        motivo = motivo.replaceAll("\\s+", "");

        if (motivo.length() < 8) {
            labErrorDescripcionCrear.setText("Usa 8 o más caracteres");
        } else {
            labErrorDescripcionCrear.setText("");
        }
    }

    private void validacionCamposCaracteresCrear() {
        String motivo = textDescripcionLicenciaCrear.getText().trim();
        motivo = motivo.replaceAll("\\s+", "");
        if (validarLetras(motivo)) {
            labErrorDescripcionCrear.setText("");
        } else {
            labErrorDescripcionCrear.setText("Solo se admiten Letras");
        }
    }

    private void camposObligatoriosModificar(){
        if (textDescripcionLicenciaModificar.getText().trim().isEmpty() || textDescripcionLicenciaModificar.getText() == null) {
            labErrorDescripcionModificar.setText("Campo Obligatorio");
        } else {
            labErrorDescripcionModificar.setText("");
        }
    }

    private void validacionCamposLongitudModificar(){
        String motivo = textDescripcionLicenciaModificar.getText().trim();
        motivo = motivo.replaceAll("\\s+", "");

        if (motivo.length() < 8) {
            labErrorDescripcionModificar.setText("Usa 8 o más caracteres");
        } else {
            labErrorDescripcionModificar.setText("");
        }
    }

    private void validacionCamposCaracteresModificar() {
        String motivo = textDescripcionLicenciaModificar.getText().trim();
        motivo = motivo.replaceAll("\\s+", "");
        if (validarLetras(motivo)) {
            labErrorDescripcionModificar.setText("");
        } else {
            labErrorDescripcionModificar.setText("Solo se admiten Letras");
        }
    }

    //---------------------------------------- Validación de Caracteres ------------------------------------------
    public static boolean validarLetras(String datos){
        return datos.matches("[a-zA-Z]*");
    }

    //----------------------------------------- Limpiador de Campos ----------------------------------------------
    private void limpiarCamposCrear(){
        textBuscarLegajoEmpleadoCrear.setText("");
        labIDEmpleadoCrear.setText("");
        labNombreEmpleadoCrear.setText("");
        labApellidoEmpleadoCrear.setText("");
        labLegajoEmpleadoCrear.setText("");
        cbTipoLicenciaCrear.getSelectionModel().selectFirst();
        textDescripcionLicenciaCrear.setText("");
        labLimpiarCamposCrear.setText("");
        labDiasDisponiblesCrear.setText("");
        fechasInicializar();
    }

    private void limpiarCamposModificar(){
        textBuscarLegajoEmpleado.setText("");
        labIDLicenciaModificar.setText("");
        labIDEmpleadoModificar.setText("");
        labNombreEmpleadoModificar.setText("");
        labApellidoEmpleadoModificar.setText("");
        labLegajoEmpleadoModificar.setText("");
        labLimpiarCamposModificar.setText("");
        cbTipoLicenciaModificar.getSelectionModel().selectFirst();
        textDescripcionLicenciaModificar.setText("");
        labDiasDisponiblesModificar.setText("");
        dpFechaModificarDuplicadaInicio.getEditor().setText("");
        dpFechaModificarDuplicadaFin.getEditor().setText("");
        fechasInicializar();
    }

    private void limpiarCamposEliminar(){
        textBuscarLegajoEmpleado.setText("");
        labIDLicenciaEliminar.setText("");
        labIDEmpleadoEliminar.setText("");
        labNombreEmpleadoEliminar.setText("");
        labApellidoEmpleadoEliminar.setText("");
        labLegajoEmpleadoEliminar.setText("");
        cbTipoLicenciaEliminar.getSelectionModel().selectFirst();
        textDescripcionLicenciaEliminar.setText("");
        labDiasDisponiblesEliminar.setText("");
        fechasInicializar();
    }

    private void limpiarCamposGeneralizada(){
        labIDLicenciaModificar.setText("");
        labIDEmpleadoModificar.setText("");
        labNombreEmpleadoModificar.setText("");
        labApellidoEmpleadoModificar.setText("");
        labLegajoEmpleadoModificar.setText("");
        labLimpiarCamposModificar.setText("");
        cbTipoLicenciaModificar.getSelectionModel().selectFirst();
        textDescripcionLicenciaModificar.setText("");
        labDiasDisponiblesModificar.setText("");
        dpFechaModificarDuplicadaInicio.getEditor().setText("");
        dpFechaModificarDuplicadaFin.getEditor().setText("");
        labIDLicenciaEliminar.setText("");
        labIDEmpleadoEliminar.setText("");
        labNombreEmpleadoEliminar.setText("");
        labApellidoEmpleadoEliminar.setText("");
        labLegajoEmpleadoEliminar.setText("");
        cbTipoLicenciaEliminar.getSelectionModel().selectFirst();
        textDescripcionLicenciaEliminar.setText("");
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
        fechasInicializarMyE();
    }

    // --------------------------------- Combo Box (Habilitar o Desactivar Text Filed) -------------------------------
    private void textTipoLicenciaDetalleCrear(String item){
        if(item.equals("Vacaciones") || item.equals("Matrimonio") || item.equals("Muerte Familiar")){
            textDescripcionLicenciaCrear.setDisable(true);
            textDescripcionLicenciaCrear.setVisible(false);
            labDescripcionCrear1.setVisible(false);
            labDescripcionCrear2.setVisible(false);
            textDescripcionLicenciaCrear.setText("");
        }
        if(item.equals("Enfermedad") || item.equals("Accidente")){
            textDescripcionLicenciaCrear.setDisable(false);
            textDescripcionLicenciaCrear.setVisible(true);
            labDescripcionCrear1.setVisible(true);
            labDescripcionCrear2.setVisible(true);
        }
    }

    private void textTipoLicenciaDetalleModificar(String item){
        if(item.equals("Vacaciones") || item.equals("Matrimonio") || item.equals("Muerte Familiar")){
            textDescripcionLicenciaModificar.setDisable(true);
            textDescripcionLicenciaModificar.setVisible(false);
            labDescripcionModificar1.setVisible(false);
            labDescripcionModificar2.setVisible(false);
            textDescripcionLicenciaModificar.setText("");
        }
        if(item.equals("Enfermedad") || item.equals("Accidente")){
            textDescripcionLicenciaModificar.setDisable(false);
            textDescripcionLicenciaModificar.setVisible(true);
            labDescripcionModificar1.setVisible(true);
            labDescripcionModificar2.setVisible(true);
        }
    }

    private void textTipoLicenciaDetalleEliminar(String item){
        if(item.equals("Vacaciones") || item.equals("Matrimonio") || item.equals("Muerte Familiar")){
            textDescripcionLicenciaEliminar.setDisable(true);
            textDescripcionLicenciaEliminar.setVisible(false);
            labDescripcionEliminar1.setVisible(false);
            labDescripcionEliminar2.setVisible(false);
            textDescripcionLicenciaEliminar.setText("");
        }
        if(item.equals("Enfermedad") || item.equals("Accidente")){
            textDescripcionLicenciaEliminar.setDisable(false);
            textDescripcionLicenciaEliminar.setVisible(true);
            labDescripcionEliminar1.setVisible(true);
            labDescripcionEliminar2.setVisible(true);
        }
    }

    // ---------------------------------------- Fechas Actuales Inicializadas ----------------------------------------
    public void fechasInicializar() {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        // Obtener la fecha actual
        Calendar calendar = Calendar.getInstance();
        // Sumar un día a la fecha actual
        calendar.add(Calendar.DATE, 1);
        Date fecha = calendar.getTime();
        String fecha_Actual_Mas1 = (formatoFecha.format(fecha));

        dpBuscarFechaInicio.setValue(LocalDate.now());
        dpBuscarFechaInicio.setConverter(new StringConverter<LocalDate>() {
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

        dpFechaInicioCrear.setValue(LocalDate.of(Integer.parseInt(fecha_Actual_Mas1.substring(0, 4)), Integer.parseInt(fecha_Actual_Mas1.substring(5, 7)), Integer.parseInt(fecha_Actual_Mas1.substring(8, 10))));
        dpFechaInicioCrear.setConverter(new StringConverter<LocalDate>() {
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

        dpFechaFinCrear.setValue(LocalDate.of(Integer.parseInt(fecha_Actual_Mas1.substring(0, 4)), Integer.parseInt(fecha_Actual_Mas1.substring(5, 7)), Integer.parseInt(fecha_Actual_Mas1.substring(8, 10))));
        dpFechaFinCrear.setConverter(new StringConverter<LocalDate>() {
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

        dpFechaInicioModificar.setValue(LocalDate.of(Integer.parseInt(fecha_Actual_Mas1.substring(0, 4)), Integer.parseInt(fecha_Actual_Mas1.substring(5, 7)), Integer.parseInt(fecha_Actual_Mas1.substring(8, 10))));
        dpFechaInicioModificar.setConverter(new StringConverter<LocalDate>() {
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

        dpFechaFinModificar.setValue(LocalDate.of(Integer.parseInt(fecha_Actual_Mas1.substring(0, 4)), Integer.parseInt(fecha_Actual_Mas1.substring(5, 7)), Integer.parseInt(fecha_Actual_Mas1.substring(8, 10))));
        dpFechaFinModificar.setConverter(new StringConverter<LocalDate>() {
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

        dpFechaInicioEliminar.setValue(LocalDate.of(Integer.parseInt(fecha_Actual_Mas1.substring(0, 4)), Integer.parseInt(fecha_Actual_Mas1.substring(5, 7)), Integer.parseInt(fecha_Actual_Mas1.substring(8, 10))));
        dpFechaInicioEliminar.setConverter(new StringConverter<LocalDate>() {
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

        dpFechaFinEliminar.setValue(LocalDate.of(Integer.parseInt(fecha_Actual_Mas1.substring(0, 4)), Integer.parseInt(fecha_Actual_Mas1.substring(5, 7)), Integer.parseInt(fecha_Actual_Mas1.substring(8, 10))));
        dpFechaFinEliminar.setConverter(new StringConverter<LocalDate>() {
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

    public void fechasInicializarMyE() {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
        // Obtener la fecha actual
        Calendar calendar = Calendar.getInstance();
        // Sumar un día a la fecha actual
        calendar.add(Calendar.DATE, 1);
        Date fecha = calendar.getTime();
        String fecha_Actual_Mas1 = (formatoFecha.format(fecha));
        dpFechaInicioModificar.setValue(LocalDate.of(Integer.parseInt(fecha_Actual_Mas1.substring(0, 4)), Integer.parseInt(fecha_Actual_Mas1.substring(5, 7)), Integer.parseInt(fecha_Actual_Mas1.substring(8, 10))));
        dpFechaInicioModificar.setConverter(new StringConverter<LocalDate>() {
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

        dpFechaFinModificar.setValue(LocalDate.of(Integer.parseInt(fecha_Actual_Mas1.substring(0, 4)), Integer.parseInt(fecha_Actual_Mas1.substring(5, 7)), Integer.parseInt(fecha_Actual_Mas1.substring(8, 10))));
        dpFechaFinModificar.setConverter(new StringConverter<LocalDate>() {
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

        dpFechaInicioEliminar.setValue(LocalDate.of(Integer.parseInt(fecha_Actual_Mas1.substring(0, 4)), Integer.parseInt(fecha_Actual_Mas1.substring(5, 7)), Integer.parseInt(fecha_Actual_Mas1.substring(8, 10))));
        dpFechaInicioEliminar.setConverter(new StringConverter<LocalDate>() {
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

        dpFechaFinEliminar.setValue(LocalDate.of(Integer.parseInt(fecha_Actual_Mas1.substring(0, 4)), Integer.parseInt(fecha_Actual_Mas1.substring(5, 7)), Integer.parseInt(fecha_Actual_Mas1.substring(8, 10))));
        dpFechaFinEliminar.setConverter(new StringConverter<LocalDate>() {
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

    public void fechaConsultaCronograma(){
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
    }

    // ---------------------------------- Cerrar Ventana -------------------------------------------
    public void closeWindowsPrincipalLicencias() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/empleados.fxml"));
        AnchorPane root = loader.load();
        Scene escena = new Scene(root);
        Stage escenario = new Stage();
        escenario.setTitle("Empleados");
        escenario.setScene(escena);
        escenario.getIcons().add(new javafx.scene.image.Image(Objects.requireNonNull(getClass().getResource("/imagenes/icono.png")).toString()));
        escenario.show();

        Stage myEscena = (Stage) this.btnVolver.getScene().getWindow();
        myEscena.close();
    }
}
