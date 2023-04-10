module com.example.secadero {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;
    requires itextpdf;
    requires java.desktop;
    requires layout;

    opens com.secadero to javafx.fxml;
    exports com.secadero;

    opens com.secadero.controlador to javafx.fxml;
    exports com.secadero.controlador;

    opens com.secadero.controlador.controladorAsistencia to javafx.fxml;
    exports com.secadero.controlador.controladorAsistencia;

    opens com.secadero.modelo.asistencia to javafx.fxml;
    exports com.secadero.modelo.asistencia;

    opens com.secadero.modelo.ausencia to javafx.fxml;
    exports com.secadero.modelo.ausencia;

    opens com.secadero.modelo.cronograma to javafx.fxml;
    exports com.secadero.modelo.cronograma;

    opens com.secadero.modelo.empleados to javafx.fxml;
    exports com.secadero.modelo.empleados;

    opens com.secadero.modelo.empleados.leerComboBoxes to javafx.fxml;
    exports com.secadero.modelo.empleados.leerComboBoxes;

    opens com.secadero.modelo.informePresentismo to javafx.fxml;
    exports com.secadero.modelo.informePresentismo;

    opens com.secadero.modelo.licencias to javafx.fxml;
    exports com.secadero.modelo.licencias;

    opens com.secadero.modelo.preRecibo to javafx.fxml;
    exports com.secadero.modelo.preRecibo;

    opens com.secadero.modelo.usuarios to javafx.fxml;
    exports com.secadero.modelo.usuarios;
}