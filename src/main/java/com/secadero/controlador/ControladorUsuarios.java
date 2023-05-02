package com.secadero.controlador;

import com.secadero.modelo.empleados.CrearEmpleado;
import com.secadero.modelo.empleados.LeerEmpleado;
import com.secadero.modelo.usuarios.CrearUsuario;
import com.secadero.modelo.usuarios.EliminarUsuario;
import com.secadero.modelo.usuarios.LeerUsuario;
import com.secadero.modelo.usuarios.ModificarUsuario;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class ControladorUsuarios {
    public ControladorUsuarios(){};

    @FXML
    private Button btnActualizar;
    @FXML
    private Button btnBuscarEmpleadoCrear;
    @FXML
    private Button btnBuscarUsuario;
    @FXML
    private Button btnCrearUsuario;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnEliminarUsuario;
    @FXML
    private Button btnFiltroUsuarios;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnGuardarCambios;
    @FXML
    private Button btnModificarUsuario;
    @FXML
    private Button btnRegresarCLista;
    @FXML
    private Button btnRegresarELista;
    @FXML
    private Button btnRegresarMLista;
    @FXML
    private Button btnVolver;
    @FXML
    private ComboBox<String> cbTiposFiltrosUsuarios;
    @FXML
    private TableColumn<LeerUsuario, String> colApellido;
    @FXML
    private TableColumn<LeerEmpleado, String> colApellidoEmpleadoCrear;
    @FXML
    private TableColumn<LeerUsuario, String> colContrasenia;
    @FXML
    private TableColumn<LeerEmpleado, Integer> colDNIEmpleadoCrear;
    @FXML
    private TableColumn<LeerUsuario, Integer> colDNI;
    @FXML
    private TableColumn<LeerUsuario, Integer> colID;
    @FXML
    private TableColumn<LeerEmpleado, Integer> colIDEmpleadoCrear;
    @FXML
    private TableColumn<LeerUsuario, Integer> colLegajo;
    @FXML
    private TableColumn<LeerEmpleado, Integer> colLegajoEmpleadoCrear;
    @FXML
    private TableColumn<LeerUsuario, String> colNombre;
    @FXML
    private TableColumn<LeerEmpleado, String> colNombreEmpleadoCrear;
    @FXML
    private TableColumn<LeerUsuario, String> colNombreUsuario;
    @FXML
    private TableColumn<LeerUsuario, String> colTelefono;
    @FXML
    private TableColumn<LeerEmpleado, String> colTelefonoEmpleadoCrear;
    @FXML
    private Label labApellidoEmpleadoCrear;
    @FXML
    private Label labApellidoEmpleadoEliminar;
    @FXML
    private Label labApellidoEmpleadoModificar;
    @FXML
    private Label labErrorContraseniaCrear;
    @FXML
    private Label labErrorContraseniaModificar;
    @FXML
    private Label labErrorNombreUsuarioCrear;
    @FXML
    private Label labErrorNombreUsuarioModificar;
    @FXML
    private Label labErrorRepetirContraseniaCrear;
    @FXML
    private Label labErrorRepetirContraseniaModificar;
    @FXML
    private Label labIDEmpleadoCrear;
    @FXML
    private Label labIDEliminar;
    @FXML
    private Label labIDModificar;
    @FXML
    private Label labInformacionModificarNombreUsuario;
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
    private TabPane panelPestaniasUsuarios;
    @FXML
    private Tab tabCrearUsuario;
    @FXML
    private Tab tabEliminarUsuario;
    @FXML
    private TableView<LeerEmpleado> tabEmpleadosCrear;
    @FXML
    private Tab tabListaUsuarios;
    @FXML
    private Tab tabModificarUsuario;
    @FXML
    private TableView<LeerUsuario> tablaUsuarios;
    @FXML
    private TextField textBuscarLegajoEmpleadoCrear;
    @FXML
    private TextField textBuscarUsuario;
    @FXML
    private TextField textContraseniaCrear;
    @FXML
    private TextField textContraseniaEliminar;
    @FXML
    private TextField textContraseniaModificar;
    @FXML
    private TextField textNombreUsuarioCrear;
    @FXML
    private TextField textNombreUsuarioEliminar;
    @FXML
    private TextField textNombreUsuarioModificar;
    @FXML
    private TextField textRepetirContraseniaCrear;
    @FXML
    private TextField textRepetirContraseniaModificar;

    ObservableList<LeerUsuario> listUsuarios;
    int index = -1;

    ObservableList<LeerEmpleado> listEmpleados;
    int index2 = -1;

    // -------------------------------------------- Inicialización ----------------------------------------------
    public void initialize(){
        String[] tipoFiltro = {"Nombre", "Apellido", "Legajo", "DNI"};
        cbTiposFiltrosUsuarios.getItems().addAll(tipoFiltro);
        cbTiposFiltrosUsuarios.getSelectionModel().selectFirst();
        inicializarTabla();
        inicializarTablaEmpleado();
    }

    // -------------------------------------------- Lista de Usuarios ----------------------------------------------
    public void inicializarTabla(){
        colNombre.setCellValueFactory(new PropertyValueFactory<LeerUsuario, String>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<LeerUsuario, String>("apellido"));
        colLegajo.setCellValueFactory(new PropertyValueFactory<LeerUsuario, Integer>("legajo"));
        colDNI.setCellValueFactory(new PropertyValueFactory<LeerUsuario, Integer>("dni"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<LeerUsuario, String>("telefono"));
        colNombreUsuario.setCellValueFactory(new PropertyValueFactory<LeerUsuario, String>("nombreUsuario"));
        colContrasenia.setCellValueFactory(new PropertyValueFactory<LeerUsuario, String>("contrasenia"));
        colID.setCellValueFactory(new PropertyValueFactory<LeerUsuario, Integer>("idusuarios"));

        listUsuarios = LeerUsuario.listaUsuarios();
        tablaUsuarios.getColumns().setAll(colNombre, colApellido, colLegajo, colDNI, colTelefono, colNombreUsuario, colContrasenia, colID);
        tablaUsuarios.getItems().setAll(listUsuarios);
    }

    // ------------------------------------ Tabla de Empleados Crear ---------------------------------------------
    public void inicializarTablaEmpleado(){
        colNombreEmpleadoCrear.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, String>("nombre"));
        colApellidoEmpleadoCrear.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, String>("apellido"));
        colLegajoEmpleadoCrear.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, Integer>("legajo"));
        colDNIEmpleadoCrear.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, Integer>("dni"));
        colTelefonoEmpleadoCrear.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, String>("telefono"));
        colIDEmpleadoCrear.setCellValueFactory(new PropertyValueFactory<LeerEmpleado, Integer>("idempleados"));

        listEmpleados = LeerEmpleado.leerEmpleadoCrear();
        tabEmpleadosCrear.getColumns().setAll(colNombreEmpleadoCrear, colApellidoEmpleadoCrear, colLegajoEmpleadoCrear, colDNIEmpleadoCrear, colTelefonoEmpleadoCrear, colIDEmpleadoCrear);
        tabEmpleadosCrear.getItems().setAll(listEmpleados);
    }
    // ------------------------------------- Tomo todos los datos al Seleccionar en la tabla ------------------------
    @FXML
    private void getSelecciono() {
        index = tablaUsuarios.getSelectionModel().getSelectedIndex();
        if (index <= -1){

            return;
        }

        labNombreEmpleadoModificar.setText(colNombre.getCellData(index));
        labApellidoEmpleadoModificar.setText(colApellido.getCellData(index));
        labLegajoEmpleadoModificar.setText(colLegajo.getCellData(index).toString());
        textNombreUsuarioModificar.setText(colNombreUsuario.getCellData(index));
        textContraseniaModificar.setText(colContrasenia.getCellData(index));
        textRepetirContraseniaModificar.setText(colContrasenia.getCellData(index));
        labIDModificar.setText(colID.getCellData(index).toString());
        labInformacionModificarNombreUsuario.setText(colNombreUsuario.getCellData(index));

        labNombreEmpleadoEliminar.setText(colNombre.getCellData(index));
        labApellidoEmpleadoEliminar.setText(colApellido.getCellData(index));
        labLegajoEmpleadoEliminar.setText(colLegajo.getCellData(index).toString());
        textNombreUsuarioEliminar.setText(colNombreUsuario.getCellData(index));
        textContraseniaEliminar.setText(colContrasenia.getCellData(index));
        labIDEliminar.setText(colID.getCellData(index).toString());
    }

    @FXML
    private void getEmpleadoCrear() {
        index2 = tabEmpleadosCrear.getSelectionModel().getSelectedIndex();
        if (index2 <= -1){

            return;
        }

        labIDEmpleadoCrear.setText(colIDEmpleadoCrear.getCellData(index2).toString());
        labNombreEmpleadoCrear.setText(colNombreEmpleadoCrear.getCellData(index2));
        labApellidoEmpleadoCrear.setText(colApellidoEmpleadoCrear.getCellData(index2));
        labLegajoEmpleadoCrear.setText(colLegajoEmpleadoCrear.getCellData(index2).toString());
        textNombreUsuarioCrear.setText(colLegajoEmpleadoCrear.getCellData(index2).toString());
    }

    // --------------------------------------------- Búsqueda y Filtros -----------------------------------------
    @FXML
    private void btnBuscar() {
        ObservableList<LeerUsuario> listBuscar;
        listBuscar = LeerUsuario.buscarUsuario(textBuscarUsuario);

        if(!textBuscarUsuario.getText().equals("")){
            tablaUsuarios.getItems().setAll(listBuscar);
        }
    }

    @FXML
    private void filtroUsuarios() {
        if(textBuscarUsuario.getText().equals("")){
            ObservableList<LeerUsuario> listFiltros;
            listFiltros = LeerUsuario.filtroUsuario(cbTiposFiltrosUsuarios);

            tablaUsuarios.getItems().setAll(listFiltros);
        } else {
            ObservableList<LeerUsuario> listFiltros2;
            listFiltros2 = LeerUsuario.filtroUsuarioBusqueda(cbTiposFiltrosUsuarios, textBuscarUsuario);

            tablaUsuarios.getItems().setAll(listFiltros2);
        }
    }

    @FXML
    private void btnBuscarEmpleadoCrear(){
        ObservableList<LeerEmpleado> listBuscarEmpleadoCrear;
        listBuscarEmpleadoCrear = LeerEmpleado.buscarEmpleadoCrear(textBuscarLegajoEmpleadoCrear);
        tabEmpleadosCrear.getItems().setAll(listBuscarEmpleadoCrear);
    }

    @FXML
    private void actualizarTabla(){
        textBuscarUsuario.setText("");
        cbTiposFiltrosUsuarios.getSelectionModel().selectFirst();
        tablaUsuarios.getItems().setAll(listUsuarios);
    }

    //---------------------------------------------- Eventos Importantes ----------------------------------------
    @FXML
    private void guardar() {
        if(labIDEmpleadoCrear.getText().trim().isEmpty() || labIDEmpleadoCrear.getText() == null){
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Advertencia!");
            alerta.setContentText("Para Crear debes de tomar un Empleado");
            alerta.showAndWait();
        } else {
            camposObligatoriosCrear();
            if(Objects.equals(labErrorContraseniaCrear.getText(), "") && Objects.equals(labErrorRepetirContraseniaCrear.getText(), "")){
                validacionCamposLongitudCrear();
                if(Objects.equals(labErrorContraseniaCrear.getText(), "") && Objects.equals(labErrorRepetirContraseniaCrear.getText(), "")) {
                    if (Objects.equals(textContraseniaCrear.getText(), textRepetirContraseniaCrear.getText())) {
                        CrearUsuario usuariosCrear = new CrearUsuario();
                        usuariosCrear.agregarUsuario(textNombreUsuarioCrear, textContraseniaCrear, labIDEmpleadoCrear, labLimpiarCamposCrear);

                        if (Objects.equals(labLimpiarCamposCrear.getText(), "OK")) {
                            labLimpiarCamposCrear.setText("");
                            inicializarTabla();
                            regresarCLista();
                            limpiarCamposCrear();
                            inicializarTablaEmpleado();
                        }
                    } else {
                        labErrorRepetirContraseniaCrear.setText("La contraseña no coincide");
                    }
                }
            }
        }
    }

    @FXML
    private void modificar() {
        if(labIDModificar.getText().trim().isEmpty() || labIDModificar.getText() == null){
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Advertencia!");
            alerta.setContentText("Debe de seleccionar antes un Usuario para Modificarlo");
            alerta.showAndWait();

        } else {
            camposObligatoriosModificar();
            if(Objects.equals(labErrorContraseniaModificar.getText(), "") && Objects.equals(labErrorRepetirContraseniaModificar.getText(), "")){
                validacionCamposLongitudModificar();
                if(Objects.equals(labErrorContraseniaModificar.getText(), "") && Objects.equals(labErrorRepetirContraseniaModificar.getText(), "")) {
                    if(Objects.equals(textContraseniaModificar.getText(), textRepetirContraseniaModificar.getText())){
                        ModificarUsuario usuariosModificar = new ModificarUsuario();
                        usuariosModificar.modificarUsuario(textNombreUsuarioModificar, textContraseniaModificar, labIDModificar, labInformacionModificarNombreUsuario, labLimpiarCamposModificar);

                        if(Objects.equals(labLimpiarCamposModificar.getText(), "OK")){
                            labLimpiarCamposModificar.setText("");
                            inicializarTabla();
                            regresarCLista();
                            limpiarCamposModificar();
                            limpiarCamposEliminar();
                        }
                    } else {
                        labErrorRepetirContraseniaCrear.setText("La contraseña no coincide");
                    }
                }
            }
        }
    }

    @FXML
    private void eliminar() {
        if (labIDEliminar.getText().trim().isEmpty() || labIDEliminar.getText() == null){
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Error!");
            alerta.setContentText("Debe de seleccionar antes un Usuario para Eliminarlo");
            alerta.showAndWait();
            btnRegresarELista.requestFocus();
        } else{
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle("Confirmar Eliminar");
            alerta.setContentText("¿Desea Eliminar el Usuario?");
            Optional<ButtonType> resultado = alerta.showAndWait();
            if (resultado.isPresent() && resultado.get() == ButtonType.OK){

                EliminarUsuario usuarioEliminar = new EliminarUsuario();
                usuarioEliminar.eliminarUsuario(labIDEliminar);
                inicializarTabla();
                inicializarTablaEmpleado();
                limpiarCamposEliminar();
                limpiarCamposModificar();
                regresarCLista();
            }
        }
    }

    //------------------------------------ Acciones Simples de los Botones --------------------------------------

    @FXML
    private void crearUsuario() {
        SingleSelectionModel<Tab> modeloSeleccion = panelPestaniasUsuarios.getSelectionModel();
        modeloSeleccion.select(tabCrearUsuario);
    }

    @FXML
    private void modificarUsuario() {
        SingleSelectionModel<Tab> modeloSeleccion = panelPestaniasUsuarios.getSelectionModel();
        modeloSeleccion.select(tabModificarUsuario);
    }

    @FXML
    private void eliminarUsuario() {
        SingleSelectionModel<Tab> modeloSeleccion = panelPestaniasUsuarios.getSelectionModel();
        modeloSeleccion.select(tabEliminarUsuario);
        btnEliminar.requestFocus();
    }

    @FXML
    private void regresarCLista() {
        SingleSelectionModel<Tab> modeloSeleccion = panelPestaniasUsuarios.getSelectionModel();
        modeloSeleccion.select(tabListaUsuarios);
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
        closeWindowsPrincipalUsuario();
    }

    //---------------------------------------- Comprobación de Campos -------------------------------------------
    private void camposObligatoriosCrear(){
        if(textContraseniaCrear.getText().trim().isEmpty() || textContraseniaCrear.getText() == null){
            labErrorContraseniaCrear.setText("Campo Obligatorio");
        } else {
            labErrorContraseniaCrear.setText("");
        }
        if(textRepetirContraseniaCrear.getText().trim().isEmpty() || textRepetirContraseniaCrear.getText() == null){
            labErrorRepetirContraseniaCrear.setText("Campo Obligatorio");
        } else {
            labErrorRepetirContraseniaCrear.setText("");
        }
    }

    private void validacionCamposLongitudCrear(){
        String contrasenia = textContraseniaCrear.getText().trim();
        String repetirContrasenia = textRepetirContraseniaCrear.getText().trim();
        contrasenia = contrasenia.replaceAll("\\s+", "");
        repetirContrasenia = repetirContrasenia.replaceAll("\\s+", "");

        if (contrasenia.length() < 6) {
            labErrorContraseniaCrear.setText("Usa 6 o más caracteres");
        } else {
            labErrorContraseniaCrear.setText("");
        }
        if (repetirContrasenia.length() < 6) {
            labErrorRepetirContraseniaCrear.setText("Usa 6 o más caracteres");
        } else {
            labErrorRepetirContraseniaCrear.setText("");
        }
    }

    private void camposObligatoriosModificar(){
        if(textContraseniaModificar.getText().trim().isEmpty() || textContraseniaModificar.getText() == null){
            labErrorContraseniaModificar.setText("Campo Obligatorio");
        } else {
            labErrorContraseniaModificar.setText("");
        }
        if(textRepetirContraseniaModificar.getText().trim().isEmpty() || textRepetirContraseniaModificar.getText() == null){
            labErrorRepetirContraseniaModificar.setText("Campo Obligatorio");
        } else {
            labErrorRepetirContraseniaModificar.setText("");
        }
    }

    private void validacionCamposLongitudModificar(){
        String contrasenia = textContraseniaModificar.getText().trim();
        String repetirContrasenia = textRepetirContraseniaModificar.getText().trim();
        contrasenia = contrasenia.replaceAll("\\s+", "");
        repetirContrasenia = repetirContrasenia.replaceAll("\\s+", "");

        if (contrasenia.length() < 6) {
            labErrorContraseniaModificar.setText("Usa 6 o más caracteres");
        } else {
            labErrorContraseniaModificar.setText("");
        }
        if (repetirContrasenia.length() < 6) {
            labErrorRepetirContraseniaModificar.setText("Usa 6 o más caracteres");
        } else {
            labErrorRepetirContraseniaModificar.setText("");
        }
    }

    //----------------------------------------- Limpiador de Campos ----------------------------------------------
    private void limpiarCamposCrear(){
        labIDEmpleadoCrear.setText("");
        labNombreEmpleadoCrear.setText("");
        labApellidoEmpleadoCrear.setText("");
        labLegajoEmpleadoCrear.setText("");
        textNombreUsuarioCrear.setText("");
        textContraseniaCrear.setText("");
        textRepetirContraseniaCrear.setText("");
        labLimpiarCamposCrear.setText("");
    }

    private void limpiarCamposModificar(){
        labNombreEmpleadoModificar.setText("");
        labApellidoEmpleadoModificar.setText("");
        labLegajoEmpleadoModificar.setText("");
        textNombreUsuarioModificar.setText("");
        textContraseniaModificar.setText("");
        textRepetirContraseniaModificar.setText("");
        labIDModificar.setText("");
        labLimpiarCamposModificar.setText("");
        labInformacionModificarNombreUsuario.setText("");
    }

    private void limpiarCamposEliminar(){
        labNombreEmpleadoEliminar.setText("");
        labApellidoEmpleadoEliminar.setText("");
        labLegajoEmpleadoEliminar.setText("");
        textNombreUsuarioEliminar.setText("");
        textContraseniaEliminar.setText("");
        labIDEliminar.setText("");
    }

    // ---------------------------------- Cerrar Ventana -------------------------------------------
    public void closeWindowsPrincipalUsuario() throws IOException {
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