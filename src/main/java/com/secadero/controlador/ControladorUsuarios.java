package com.secadero.controlador;

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
    private ComboBox<String> cbRolCrear;
    @FXML
    private ComboBox<String> cbRolEliminar;
    @FXML
    private ComboBox<String> cbRolModificar;
    @FXML
    private ComboBox<String> cbTiposFiltrosUsuarios;
    @FXML
    private TableColumn<LeerUsuario, String> colApellido;
    @FXML
    private TableColumn<LeerUsuario, String> colContrasenia;
    @FXML
    private TableColumn<LeerUsuario, String> colEmail;
    @FXML
    private TableColumn<LeerUsuario, Integer> colID;
    @FXML
    private TableColumn<LeerUsuario, Integer> colLegajo;
    @FXML
    private TableColumn<LeerUsuario, String> colNombre;
    @FXML
    private TableColumn<LeerUsuario, String> colRol;
    @FXML
    private TableColumn<LeerUsuario, String> colTelefono;
    @FXML
    private TableColumn<LeerUsuario, String> colNombreUsuario;
    @FXML
    private TableColumn<LeerUsuario, Integer> colDNIUsuario;
    @FXML
    private Label labErrorApellidoCrear;
    @FXML
    private Label labErrorApellidoModificar;
    @FXML
    private Label labErrorContraseniaCrear;
    @FXML
    private Label labErrorContraseniaModificar;
    @FXML
    private Label labErrorEmailCrear;
    @FXML
    private Label labErrorEmailModificar;
    @FXML
    private Label labErrorLegajoCrear;
    @FXML
    private Label labErrorLegajoModificar;
    @FXML
    private Label labErrorNombreCrear;
    @FXML
    private Label labErrorNombreModificar;
    @FXML
    private Label labErrorNombreUsuarioCrear;
    @FXML
    private Label labErrorNombreUsuarioModificar;
    @FXML
    private Label labErrorRepetirContraseniaCrear;
    @FXML
    private Label labErrorRepetirContraseniaModificar;
    @FXML
    private Label labErrorTelefonoCrear;
    @FXML
    private Label labErrorTelefonoModificar;
    @FXML
    private Label labIDEliminar;
    @FXML
    private Label labIDModificar;
    @FXML
    private Label labInformacionModificarNombreUsuario;
    @FXML
    private Label labInformacionModificarLegajo;
    @FXML
    private Label labLimpiarCamposCrear;
    @FXML
    private Label labLimpiarCamposModificar;
    @FXML
    private Label labInformacionModificarDNI;
    @FXML
    private Label labErrorDNICrear;
    @FXML
    private Label labErrorDNIModificar;
    @FXML
    private TabPane panelPestaniasUsuarios;
    @FXML
    private Tab tabCrearUsuario;
    @FXML
    private Tab tabEliminarUsuario;
    @FXML
    private Tab tabListaUsuarios;
    @FXML
    private Tab tabModificarUsuario;
    @FXML
    private TableView<LeerUsuario> tablaUsuarios;
    @FXML
    private TextField textApellidoCrear;
    @FXML
    private TextField textApellidoEliminar;
    @FXML
    private TextField textApellidoModificar;
    @FXML
    private TextField textBuscarUsuario;
    @FXML
    private TextField textContraseniaCrear;
    @FXML
    private TextField textContraseniaEliminar;
    @FXML
    private TextField textContraseniaModificar;
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
    private TextField textNombreUsuarioCrear;
    @FXML
    private TextField textNombreUsuarioEliminar;
    @FXML
    private TextField textNombreUsuarioModificar;
    @FXML
    private TextField textRepetirContraseniaCrear;
    @FXML
    private TextField textRepetirContraseniaModificar;
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

    ObservableList<LeerUsuario> listUsuarios;
    int index = -1;
    String ErrorCrear = "";
    String ErrorModificar = "";


    // -------------------------------------------- Inicialización ----------------------------------------------
    public void initialize(){
        String[] tipoFiltro = {"Nombre", "Apellido", "Legajo", "DNI", "Rol"};
        String[] tipoRol = {"Administrador", "Operador"};
        cbTiposFiltrosUsuarios.getItems().addAll(tipoFiltro);
        cbTiposFiltrosUsuarios.getSelectionModel().selectFirst();
        cbRolCrear.getItems().addAll(tipoRol);
        cbRolCrear.getSelectionModel().selectFirst();
        cbRolModificar.getItems().addAll(tipoRol);
        cbRolModificar.getSelectionModel().selectFirst();
        cbRolEliminar.getItems().addAll(tipoRol);
        cbRolEliminar.getSelectionModel().selectFirst();
        inicializarTabla();
    }

    // -------------------------------------------- Lista de Usuarios ----------------------------------------------
    public void inicializarTabla(){
        colNombre.setCellValueFactory(new PropertyValueFactory<LeerUsuario, String>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<LeerUsuario, String>("apellido"));
        colLegajo.setCellValueFactory(new PropertyValueFactory<LeerUsuario, Integer>("legajo"));
        colDNIUsuario.setCellValueFactory(new PropertyValueFactory<LeerUsuario, Integer>("dni"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<LeerUsuario, String>("telefono"));
        colNombreUsuario.setCellValueFactory(new PropertyValueFactory<LeerUsuario, String>("nombreUsuario"));
        colContrasenia.setCellValueFactory(new PropertyValueFactory<LeerUsuario, String>("contrasenia"));
        colRol.setCellValueFactory(new PropertyValueFactory<LeerUsuario, String>("rol"));
        colEmail.setCellValueFactory(new PropertyValueFactory<LeerUsuario, String>("email"));
        colID.setCellValueFactory(new PropertyValueFactory<LeerUsuario, Integer>("idusuarios"));

        listUsuarios = LeerUsuario.listaUsuarios();
        tablaUsuarios.getColumns().setAll(colNombre, colApellido, colLegajo, colDNIUsuario, colTelefono, colNombreUsuario, colContrasenia, colRol, colEmail, colID);
        tablaUsuarios.getItems().setAll(listUsuarios);
    }

    // ------------------------------------- Tomo todos los datos al Seleccionar en la tabla ------------------------
    @FXML
    private void getSelecciono() {
        index = tablaUsuarios.getSelectionModel().getSelectedIndex();
        if (index <= -1){

            return;
        }
        labIDModificar.setText(colID.getCellData(index).toString());
        textNombreModificar.setText(colNombre.getCellData(index));
        textApellidoModificar.setText(colApellido.getCellData(index));
        textLegajoModificar.setText(colLegajo.getCellData(index).toString());
        labInformacionModificarLegajo.setText(colLegajo.getCellData(index).toString());
        textDNIModificar.setText(colDNIUsuario.getCellData(index).toString());
        labInformacionModificarDNI.setText(colDNIUsuario.getCellData(index).toString());
        textEmailModificar.setText(colEmail.getCellData(index));
        textTelefonoModificar.setText(colTelefono.getCellData(index));
        textNombreUsuarioModificar.setText(colNombreUsuario.getCellData(index));
        labInformacionModificarNombreUsuario.setText(colNombreUsuario.getCellData(index));
        textContraseniaModificar.setText(colContrasenia.getCellData(index));
        textRepetirContraseniaModificar.setText(colContrasenia.getCellData(index));
        cbRolModificar.getSelectionModel().select(colRol.getCellData(index));

        labIDEliminar.setText(colID.getCellData(index).toString());
        textNombreEliminar.setText(colNombre.getCellData(index));
        textApellidoEliminar.setText(colApellido.getCellData(index));
        textLegajoEliminar.setText(colLegajo.getCellData(index).toString());
        textDNIEliminar.setText(colDNIUsuario.getCellData(index).toString());
        textEmailEliminar.setText(colEmail.getCellData(index));
        textTelefonoEliminar.setText(colTelefono.getCellData(index));
        textNombreUsuarioEliminar.setText(colNombreUsuario.getCellData(index));
        textContraseniaEliminar.setText(colContrasenia.getCellData(index));
        cbRolEliminar.getSelectionModel().select(colRol.getCellData(index));
    }

    // --------------------------------------------- Búsqueda y Filtros -----------------------------------------
    @FXML
    private void btnBuscar() {
        ObservableList<LeerUsuario> listBuscar;
        listBuscar = LeerUsuario.buscarUsuario(textBuscarUsuario);

        if(textBuscarUsuario.getText().equals("")){
            tablaUsuarios.getItems().setAll(listUsuarios);
        } else {
            tablaUsuarios.getItems().setAll(listBuscar);
        }
    }

    @FXML
    private void filtroUsuarios() {
        ObservableList<LeerUsuario> listFiltro;
        listFiltro = LeerUsuario.filtroUsuario(cbTiposFiltrosUsuarios);

        tablaUsuarios.getItems().setAll(listFiltro);
    }

    //---------------------------------------------- Eventos Importantes ----------------------------------------
    @FXML
    private void guardar() {
        ErrorCrear = "";
        TextField[] campos = {textNombreCrear, textApellidoCrear, textLegajoCrear, textEmailCrear, textTelefonoCrear, textContraseniaCrear, textRepetirContraseniaCrear};
        TextField[] campoLegajo = {textLegajoCrear};
        TextField[] campoDNI = {textDNICrear};
        TextField[] campoTelefono = {textTelefonoCrear};
        labErroresCrear();
        if(comprobarValoresCrear(campos)){
            if(validarLetras(textNombreCrear.getText())){
                if(validarLetras(textApellidoCrear.getText())){
                    if(validarNumeros(textLegajoCrear.getText())){
                        if(comprobarValoresCrearLegajo(campoLegajo)){
                            if(validarNumeros(textDNICrear.getText())){
                                if(comprobarValoresCrearDNI(campoDNI)){
                                    if(validarLetrasEmail(textEmailCrear.getText())){
                                        if(validarNumerosTelefono(textTelefonoCrear.getText())){
                                            if(comprobarValoresCrearTelefono(campoTelefono)){
                                                if(validarLetras(textNombreUsuarioCrear.getText())){
                                                    if (Objects.equals(textContraseniaCrear.getText(), textRepetirContraseniaCrear.getText())){
                                                        labErroresCrear();
                                                        CrearUsuario usuarioCrear = new CrearUsuario();
                                                        usuarioCrear.agregarUsuario(textNombreCrear, textApellidoCrear, textLegajoCrear, textDNICrear, textEmailCrear, textTelefonoCrear,textNombreUsuarioCrear, textContraseniaCrear, cbRolCrear, labLimpiarCamposCrear);

                                                        if(Objects.equals(labLimpiarCamposCrear.getText(), "OK")){
                                                            inicializarTabla();
                                                            regresarCLista();
                                                            limpiarCamposCrear();
                                                        }
                                                    } else {
                                                        labErrorRepetirContraseniaCrear.setText("La Contraseña no coincide");
                                                        ErrorCrear = "RepetirContrasenia";
                                                        labErroresCrear2(ErrorCrear);
                                                    }
                                                } else {
                                                    labErrorNombreUsuarioCrear.setText("Solo se admiten Letras");
                                                    ErrorCrear = "Nombre_Usuario";
                                                    labErroresCrear2(ErrorCrear);
                                                }
                                            } else {
                                                labErrorTelefonoCrear.setText("Solo se permite hasta 10 Números");
                                                ErrorCrear = "Telefono";
                                                labErroresCrear2(ErrorCrear);
                                            }

                                        } else {
                                            labErrorTelefonoCrear.setText("Solo se admiten Números");
                                            ErrorCrear = "Telefono";
                                            labErroresCrear2(ErrorCrear);
                                        }
                                    } else {
                                        labErrorEmailCrear.setText("No se parece a un E-mail");
                                        ErrorCrear = "Email";
                                        labErroresCrear2(ErrorCrear);
                                    }
                                } else {
                                    labErrorDNICrear.setText("Solo se permite hasta 8 Números");
                                    ErrorCrear = "DNI";
                                    labErroresCrear2(ErrorCrear);
                                }
                            } else {
                                labErrorDNICrear.setText("Solo se admiten Números");
                                ErrorCrear = "DNI";
                                labErroresCrear2(ErrorCrear);
                            }

                        } else {
                            labErrorLegajoCrear.setText("Solo se permite hasta 8 Números");
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
    private void modificar() {
        TextField[] campos = {textNombreModificar, textApellidoModificar, textLegajoModificar, textDNIModificar, textEmailModificar, textTelefonoModificar, textContraseniaModificar, textRepetirContraseniaModificar};
        TextField[] campoLegajo = {textLegajoModificar};
        TextField[] campoDNI = {textDNIModificar};
        TextField[] campoTelefono = {textTelefonoModificar};
        Label[] id = {labIDModificar};
        ErrorModificar = "";
        labErroresModificar();
        if(comprobarIDModificar(id)){
            if(comprobarValoresModificar(campos)){
                if(validarLetras(textNombreModificar.getText())){
                    if(validarLetras(textApellidoModificar.getText())){
                        if(validarNumeros(textLegajoModificar.getText())){
                            if(comprobarValoresModificarLegajo(campoLegajo)){
                                if(validarNumeros(textDNIModificar.getText())){
                                    if(comprobarValoresModificarDNI(campoDNI)){
                                        if(validarLetrasEmail(textEmailModificar.getText())){
                                            if(validarNumerosTelefono(textTelefonoModificar.getText())){
                                                if(comprobarValoresModificarTelefono(campoTelefono)){
                                                    if(validarLetras(textNombreUsuarioModificar.getText())){
                                                        if (Objects.equals(textContraseniaModificar.getText(), textRepetirContraseniaModificar.getText())){
                                                            labErroresModificar();
                                                            ModificarUsuario usuarioModificar = new ModificarUsuario();
                                                            usuarioModificar.modificarUsuario(textNombreModificar, textApellidoModificar, textLegajoModificar, textDNIModificar, textEmailModificar, textTelefonoModificar, textNombreUsuarioModificar, textContraseniaModificar, cbRolModificar, labIDModificar, labLimpiarCamposModificar, labInformacionModificarLegajo, labInformacionModificarNombreUsuario, labInformacionModificarDNI);

                                                            if(Objects.equals(labLimpiarCamposModificar.getText(), "OK")){
                                                                inicializarTabla();
                                                                regresarCLista();
                                                                limpiarCamposModificar();
                                                                limpiarCamposEliminar();
                                                            }
                                                        } else {
                                                            labErrorRepetirContraseniaModificar.setText("La Contraseña no coincide");
                                                            ErrorModificar = "RepetirContrasenia";
                                                            labErroresModificar2(ErrorModificar);
                                                        }
                                                    } else {
                                                        labErrorNombreUsuarioModificar.setText("Solo se admiten Letras");
                                                        ErrorModificar = "Nombre_Usuario";
                                                        labErroresModificar2(ErrorModificar);
                                                    }
                                                } else {
                                                    labErrorTelefonoModificar.setText("Solo se permite hasta 10 Números");
                                                    ErrorModificar = "Telefono";
                                                    labErroresModificar2(ErrorModificar);
                                                }
                                            } else {
                                                labErrorTelefonoModificar.setText("Solo se admiten Números");
                                                ErrorModificar = "Telefono";
                                                labErroresModificar2(ErrorModificar);
                                            }
                                        } else {
                                            labErrorEmailModificar.setText("No se parece a un E-mail");
                                            ErrorModificar = "Email";
                                            labErroresModificar2(ErrorModificar);
                                        }
                                    } else {
                                        labErrorDNIModificar.setText("Solo se permite hasta 8 Números");
                                        ErrorModificar = "DNI";
                                        labErroresModificar2(ErrorModificar);
                                    }
                                } else {
                                    labErrorDNIModificar.setText("Solo se admiten Números");
                                    ErrorModificar = "DNI";
                                    labErroresModificar2(ErrorModificar);
                                }

                            } else {
                                labErrorLegajoModificar.setText("Solo se permite hasta 8 Números");
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
            } else {
                Alert alerta = new Alert(Alert.AlertType.WARNING);
                alerta.setTitle("Error!");
                alerta.setContentText("Debe de completar todos los campos y que NO sean cortos.");
                alerta.showAndWait();
            }
        } else {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Error!");
            alerta.setContentText("Debe de seleccionar antes un Usuario para Modificarlo");
            alerta.showAndWait();
        }

    }

    @FXML
    private void eliminar() {
        Label[] id = {labIDEliminar};
        if (comprobarIDEliminar(id)){
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
            alerta.setTitle("Confirmar Eliminar");
            alerta.setContentText("¿Desea Eliminar el Usuario?");
            Optional<ButtonType> resultado = alerta.showAndWait();
            if (resultado.isPresent() && resultado.get() == ButtonType.OK){

                EliminarUsuario usuarioEliminar = new EliminarUsuario();
                usuarioEliminar.eliminarUsuario(labIDEliminar);
                inicializarTabla();

                limpiarCamposEliminar();
                limpiarCamposModificar();
                regresarCLista();
            }
        } else{
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Error!");
            alerta.setContentText("Debe de seleccionar antes un Usuario para Eliminarlo");
            alerta.showAndWait();
            btnRegresarELista.requestFocus();
        }
    }

    //------------------------------------ Acciones Simples de los Botones --------------------------------------

    @FXML
    private void crearUsuario() {
        SingleSelectionModel<Tab> modeloSeleccion = panelPestaniasUsuarios.getSelectionModel();
        modeloSeleccion.select(tabCrearUsuario);
        textNombreCrear.requestFocus();
    }

    @FXML
    private void modificarUsuario() {
        SingleSelectionModel<Tab> modeloSeleccion = panelPestaniasUsuarios.getSelectionModel();
        modeloSeleccion.select(tabModificarUsuario);
        textNombreModificar.requestFocus();
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
    private boolean comprobarValoresCrear(TextField[] campos){
        boolean valido = true;
        for (int i = 0; i < campos.length; i++) {
            String valor = campos[i].getText();
            if(valor == null || valor.trim().isEmpty() || textNombreCrear.getLength() < 4|| textApellidoCrear.getLength() < 4|| textLegajoCrear.getLength() < 4 || textDNICrear.getLength() < 6  || textEmailCrear.getLength() < 8 || textTelefonoCrear.getLength() < 9 || textContraseniaCrear.getLength() < 4 || textRepetirContraseniaCrear.getLength() < 4){
                valido = false;
            }
        }
        return valido;
    }

    private boolean comprobarValoresModificar(TextField[] campos){
        boolean valido = true;
        for (int i = 0; i < campos.length; i++) {
            String valor = campos[i].getText();
            if(valor == null || valor.trim().isEmpty() || textNombreModificar.getLength() < 4|| textApellidoModificar.getLength() < 4|| textLegajoModificar.getLength() < 4 || textDNIModificar.getLength() < 6 || textEmailModificar.getLength() < 8 || textTelefonoModificar.getLength() < 9 || textContraseniaModificar.getLength() < 4 || textRepetirContraseniaModificar.getLength() < 4){
                valido = false;
            }
        }
        return valido;
    }

    private boolean comprobarValoresCrearLegajo(TextField[] campoLegajo){
        boolean valido = true;
        for (int i = 0; i < campoLegajo.length; i++) {
            String valor = campoLegajo[i].getText();
            if(valor == null || valor.trim().isEmpty() || textLegajoCrear.getLength() > 8){
                valido = false;
            }
        }
        return valido;
    }

    private boolean comprobarValoresModificarLegajo(TextField[] campoLegajo){
        boolean valido = true;
        for (int i = 0; i < campoLegajo.length; i++) {
            String valor = campoLegajo[i].getText();
            if(valor == null || valor.trim().isEmpty() || textLegajoModificar.getLength() > 8){
                valido = false;
            }
        }
        return valido;
    }

    private boolean comprobarValoresCrearDNI(TextField[] campoDNI){
        boolean valido = true;
        for (int i = 0; i < campoDNI.length; i++) {
            String valor = campoDNI[i].getText();
            if(valor == null || valor.trim().isEmpty() || textDNICrear.getLength() > 8){
                valido = false;
            }
        }
        return valido;
    }

    private boolean comprobarValoresModificarDNI(TextField[] campoDNI){
        boolean valido = true;
        for (int i = 0; i < campoDNI.length; i++) {
            String valor = campoDNI[i].getText();
            if(valor == null || valor.trim().isEmpty() || textDNIModificar.getLength() > 8){
                valido = false;
            }
        }
        return valido;
    }

    private boolean comprobarValoresCrearTelefono(TextField[] campoTelefono){
        boolean valido = true;
        for (int i = 0; i < campoTelefono.length; i++) {
            String valor = campoTelefono[i].getText();
            if(valor == null || valor.trim().isEmpty() || textTelefonoCrear.getLength() > 11){
                valido = false;
            }
        }
        return valido;
    }

    private boolean comprobarValoresModificarTelefono(TextField[] campoTelefono){
        boolean valido = true;
        for (int i = 0; i < campoTelefono.length; i++) {
            String valor = campoTelefono[i].getText();
            if(valor == null || valor.trim().isEmpty() || textTelefonoModificar.getLength() > 11){
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
        textDNICrear.setText("");
        textEmailCrear.setText("");
        textTelefonoCrear.setText("");
        textNombreUsuarioCrear.setText("");
        textContraseniaCrear.setText("");
        textRepetirContraseniaCrear.setText("");
        cbRolCrear.getSelectionModel().selectFirst();
        labLimpiarCamposCrear.setText("");
    }

    private void limpiarCamposModificar(){
        textNombreModificar.setText("");
        textApellidoModificar.setText("");
        textLegajoModificar.setText("");
        textDNIModificar.setText("");
        textEmailModificar.setText("");
        textTelefonoModificar.setText("");
        textNombreUsuarioModificar.setText("");
        textContraseniaModificar.setText("");
        textRepetirContraseniaModificar.setText("");
        cbRolModificar.getSelectionModel().selectFirst();
        labIDModificar.setText("");
        labLimpiarCamposModificar.setText("");
        labInformacionModificarLegajo.setText("");
        labInformacionModificarNombreUsuario.setText("");
        labInformacionModificarDNI.setText("");
    }

    private void limpiarCamposEliminar(){
        textNombreEliminar.setText("");
        textApellidoEliminar.setText("");
        textLegajoEliminar.setText("");
        textDNIEliminar.setText("");
        textEmailEliminar.setText("");
        textTelefonoEliminar.setText("");
        textNombreUsuarioEliminar.setText("");
        textContraseniaEliminar.setText("");
        cbRolEliminar.getSelectionModel().selectFirst();
        labIDEliminar.setText("");
    }

    // ----------------------------------------------- Labels de Errores ----------------------------------------
    private void labErroresCrear(){
        labErrorNombreCrear.setText("");
        labErrorApellidoCrear.setText("");
        labErrorLegajoCrear.setText("");
        labErrorDNICrear.setText("");
        labErrorEmailCrear.setText("");
        labErrorTelefonoCrear.setText("");
        labErrorNombreUsuarioCrear.setText("");
        labErrorContraseniaCrear.setText("");
        labErrorRepetirContraseniaCrear.setText("");
    }

    private void labErroresModificar(){
        labErrorNombreModificar.setText("");
        labErrorApellidoModificar.setText("");
        labErrorLegajoModificar.setText("");
        labErrorDNIModificar.setText("");
        labErrorEmailModificar.setText("");
        labErrorTelefonoModificar.setText("");
        labErrorNombreUsuarioModificar.setText("");
        labErrorContraseniaModificar.setText("");
        labErrorRepetirContraseniaModificar.setText("");
    }

    private void labErroresCrear2(String ErrorCrear){
        if(Objects.equals(ErrorCrear, "Nombre")){
            labErrorApellidoCrear.setText("");
            labErrorLegajoCrear.setText("");
            labErrorDNICrear.setText("");
            labErrorEmailCrear.setText("");
            labErrorTelefonoCrear.setText("");
            labErrorContraseniaCrear.setText("");
            labErrorNombreUsuarioCrear.setText("");
            labErrorRepetirContraseniaCrear.setText("");
        } else if(Objects.equals(ErrorCrear, "Apellido")){
            labErrorNombreCrear.setText("");
            labErrorLegajoCrear.setText("");
            labErrorDNICrear.setText("");
            labErrorEmailCrear.setText("");
            labErrorTelefonoCrear.setText("");
            labErrorNombreUsuarioCrear.setText("");
            labErrorContraseniaCrear.setText("");
            labErrorRepetirContraseniaCrear.setText("");
        } else if(Objects.equals(ErrorCrear, "Legajo")){
            labErrorDNICrear.setText("");
            labErrorNombreCrear.setText("");
            labErrorApellidoCrear.setText("");
            labErrorEmailCrear.setText("");
            labErrorTelefonoCrear.setText("");
            labErrorNombreUsuarioCrear.setText("");
            labErrorContraseniaCrear.setText("");
            labErrorRepetirContraseniaCrear.setText("");
        } else if(Objects.equals(ErrorCrear, "Email")){
            labErrorNombreCrear.setText("");
            labErrorApellidoCrear.setText("");
            labErrorLegajoCrear.setText("");
            labErrorDNICrear.setText("");
            labErrorTelefonoCrear.setText("");
            labErrorNombreUsuarioCrear.setText("");
            labErrorContraseniaCrear.setText("");
            labErrorRepetirContraseniaCrear.setText("");
        } else if(Objects.equals(ErrorCrear, "Telefono")){
            labErrorNombreCrear.setText("");
            labErrorApellidoCrear.setText("");
            labErrorLegajoCrear.setText("");
            labErrorDNICrear.setText("");
            labErrorEmailCrear.setText("");
            labErrorNombreUsuarioCrear.setText("");
            labErrorContraseniaCrear.setText("");
            labErrorRepetirContraseniaCrear.setText("");
        } else if(Objects.equals(ErrorCrear, "Contrasenia")){
            labErrorNombreCrear.setText("");
            labErrorApellidoCrear.setText("");
            labErrorLegajoCrear.setText("");
            labErrorDNICrear.setText("");
            labErrorEmailCrear.setText("");
            labErrorTelefonoCrear.setText("");
            labErrorNombreUsuarioCrear.setText("");
            labErrorRepetirContraseniaCrear.setText("");
        } else if(Objects.equals(ErrorCrear, "RepetirContrasenia")){
            labErrorNombreCrear.setText("");
            labErrorApellidoCrear.setText("");
            labErrorLegajoCrear.setText("");
            labErrorDNICrear.setText("");
            labErrorEmailCrear.setText("");
            labErrorTelefonoCrear.setText("");
            labErrorNombreUsuarioCrear.setText("");
            labErrorContraseniaCrear.setText("");
        } else if(Objects.equals(ErrorCrear, "DNI")){
            labErrorNombreCrear.setText("");
            labErrorApellidoCrear.setText("");
            labErrorLegajoCrear.setText("");
            labErrorEmailCrear.setText("");
            labErrorTelefonoCrear.setText("");
            labErrorNombreUsuarioCrear.setText("");
            labErrorContraseniaCrear.setText("");
            labErrorRepetirContraseniaCrear.setText("");
        } else if(Objects.equals(ErrorCrear, "Nombre_Usuario")){
            labErrorNombreCrear.setText("");
            labErrorApellidoCrear.setText("");
            labErrorLegajoCrear.setText("");
            labErrorDNICrear.setText("");
            labErrorEmailCrear.setText("");
            labErrorTelefonoCrear.setText("");
            labErrorContraseniaCrear.setText("");
            labErrorRepetirContraseniaCrear.setText("");
        }
    }

    private void labErroresModificar2(String ErrorModificar) {
        if (Objects.equals(ErrorModificar, "Nombre")) {
            labErrorApellidoModificar.setText("");
            labErrorLegajoModificar.setText("");
            labErrorDNIModificar.setText("");
            labErrorEmailModificar.setText("");
            labErrorTelefonoModificar.setText("");
            labErrorNombreUsuarioModificar.setText("");
            labErrorContraseniaModificar.setText("");
            labErrorRepetirContraseniaModificar.setText("");
        } else if (Objects.equals(ErrorModificar, "Apellido")) {
            labErrorNombreModificar.setText("");
            labErrorLegajoModificar.setText("");
            labErrorDNIModificar.setText("");
            labErrorEmailModificar.setText("");
            labErrorTelefonoModificar.setText("");
            labErrorNombreUsuarioModificar.setText("");
            labErrorContraseniaModificar.setText("");
            labErrorRepetirContraseniaModificar.setText("");
        } else if (Objects.equals(ErrorModificar, "Legajo")) {
            labErrorDNIModificar.setText("");
            labErrorNombreModificar.setText("");
            labErrorApellidoModificar.setText("");
            labErrorEmailModificar.setText("");
            labErrorTelefonoModificar.setText("");
            labErrorNombreUsuarioModificar.setText("");
            labErrorContraseniaModificar.setText("");
            labErrorRepetirContraseniaModificar.setText("");
        } else if (Objects.equals(ErrorModificar, "Email")) {
            labErrorNombreModificar.setText("");
            labErrorApellidoModificar.setText("");
            labErrorLegajoModificar.setText("");
            labErrorDNIModificar.setText("");
            labErrorTelefonoModificar.setText("");
            labErrorNombreUsuarioModificar.setText("");
            labErrorContraseniaModificar.setText("");
            labErrorRepetirContraseniaModificar.setText("");
        } else if (Objects.equals(ErrorModificar, "Telefono")) {
            labErrorNombreModificar.setText("");
            labErrorApellidoModificar.setText("");
            labErrorLegajoModificar.setText("");
            labErrorDNIModificar.setText("");
            labErrorEmailModificar.setText("");
            labErrorNombreUsuarioModificar.setText("");
            labErrorContraseniaModificar.setText("");
            labErrorRepetirContraseniaModificar.setText("");
        } else if (Objects.equals(ErrorModificar, "Contrasenia")) {
            labErrorNombreModificar.setText("");
            labErrorApellidoModificar.setText("");
            labErrorLegajoModificar.setText("");
            labErrorDNIModificar.setText("");
            labErrorEmailModificar.setText("");
            labErrorTelefonoModificar.setText("");
            labErrorNombreUsuarioModificar.setText("");
            labErrorRepetirContraseniaModificar.setText("");
        } else if (Objects.equals(ErrorModificar, "RepetirContrasenia")) {
            labErrorNombreModificar.setText("");
            labErrorApellidoModificar.setText("");
            labErrorLegajoModificar.setText("");
            labErrorDNIModificar.setText("");
            labErrorEmailModificar.setText("");
            labErrorTelefonoModificar.setText("");
            labErrorNombreUsuarioModificar.setText("");
            labErrorContraseniaModificar.setText("");
        } else if(Objects.equals(ErrorModificar, "DNI")){
            labErrorNombreModificar.setText("");
            labErrorApellidoModificar.setText("");
            labErrorLegajoModificar.setText("");
            labErrorEmailModificar.setText("");
            labErrorTelefonoModificar.setText("");
            labErrorContraseniaModificar.setText("");
            labErrorNombreUsuarioModificar.setText("");
            labErrorRepetirContraseniaModificar.setText("");
        } else if(Objects.equals(ErrorModificar, "Nombre_Usuario")){
            labErrorNombreModificar.setText("");
            labErrorApellidoModificar.setText("");
            labErrorLegajoModificar.setText("");
            labErrorEmailModificar.setText("");
            labErrorTelefonoModificar.setText("");
            labErrorContraseniaModificar.setText("");
            labErrorRepetirContraseniaModificar.setText("");
        }
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