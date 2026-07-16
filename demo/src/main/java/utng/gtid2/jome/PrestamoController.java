package utng.gtid2.jome;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PrestamoController implements Initializable {

    @FXML private TextField txtFolio;
    @FXML private ComboBox<String> cmbInsumo;
    @FXML private ComboBox<String> cmbResponsable;
    @FXML private TextField txtFechaPrestamo;
    @FXML private TextField txtFechaDevolucion;
    @FXML private TextField txtObservaciones;
    @FXML private Label lblError;
    @FXML private Button btnVolver;
    @FXML private Button btnVerHistorial;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Datos de ejemplo mientras no hay conexión al catálogo/BD.
        cmbInsumo.getItems().addAll(
                "Espuma limpiadora",
                "Franela de microfibra",
                "Cable de red cat6",
                "Memoria RAM DDR4",
                "Mouse óptico",
                "Teclado USB"
        );

        // Responsable/Técnico ahora es un dropdown (FK a Usuario) en vez de texto libre.
        cmbResponsable.getItems().addAll(
                "Angel Nolasco",
                "Jonathan Aguilar",
                "Jesus Omar"
        );

        lblError.setText("");
    }

    @FXML
    private void mostrarInformacion() {
        if (txtFolio.getText().isEmpty()
                || cmbInsumo.getValue() == null
                || cmbResponsable.getValue() == null
                || txtFechaPrestamo.getText().isEmpty()) {
            lblError.setText("Completa Folio, Insumo, Responsable y Fecha de Préstamo antes de registrar.");
            return;
        }

        lblError.setText("");

        Alert confirmacion = new Alert(Alert.AlertType.INFORMATION);
        confirmacion.setTitle("Préstamo registrado");
        confirmacion.setHeaderText(null);
        confirmacion.setContentText("Préstamo de \"" + cmbInsumo.getValue() + "\" registrado correctamente.");
        confirmacion.showAndWait();
    }

    @FXML
    private void accionCancelar() {
        cmbInsumo.getSelectionModel().clearSelection();
        cmbResponsable.getSelectionModel().clearSelection();
        txtFechaPrestamo.clear();
        txtFechaDevolucion.clear();
        txtObservaciones.clear();
        lblError.setText("");
    }

    @FXML
    private void accionVolver() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Principal.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) btnVolver.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Panel Principal");
        stage.show();
    }

    @FXML
    private void accionVerHistorial() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("from_ListaAsignaciones.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) btnVerHistorial.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Historial de Préstamos");
        stage.show();
    }
}