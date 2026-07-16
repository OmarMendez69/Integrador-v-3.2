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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegistroDesechoController implements Initializable {

    @FXML private TextField txtFolio;
    @FXML private ComboBox<String> cmbInsumo;
    @FXML private TextField txtCantidad;
    @FXML private TextField txtPeso;
    @FXML private TextField txtMotivo;
    @FXML private TextField txtFecha;
    @FXML private TextField txtResponsable; // deshabilitado: se llenará con el usuario logueado
    @FXML private TextArea txtDescripcion;
    @FXML private Label lblError;
    @FXML private Button btnGuardar;
    @FXML private Button btnVolver;

    private boolean modoEdicion = false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Datos de ejemplo mientras no hay conexión al catálogo/BD.
        cmbInsumo.getItems().addAll(
                "INS-001 - Espuma limpiadora",
                "INS-002 - Franela de microfibra",
                "INS-003 - Cable de red cat6",
                "INS-004 - Memoria RAM DDR4",
                "INS-005 - Mouse óptico",
                "INS-006 - Teclado USB"
        );

        lblError.setText("");
    }

    /**
     * Precarga los datos para el modo edición (demostración, sin persistencia real).
     */
    public void cargarDesecho(String insumo, String cantidad, String peso,
                               String motivo, String fecha, String responsable, String descripcion) {
        modoEdicion = true;
        cmbInsumo.setValue(insumo);
        txtCantidad.setText(cantidad);
        txtPeso.setText(peso);
        txtMotivo.setText(motivo);
        txtFecha.setText(fecha);
        txtResponsable.setText(responsable);
        txtDescripcion.setText(descripcion);
        btnGuardar.setText("Actualizar");
        // txtFolio no se toca aquí: en un caso real, al editar mostraría el folio
        // ya existente del registro en lugar del texto genérico.
    }

    @FXML
    private void mostrarInformacion() {
        if (cmbInsumo.getValue() == null
                || txtCantidad.getText().isEmpty()
                || txtPeso.getText().isEmpty()
                || txtMotivo.getText().isEmpty()
                || txtFecha.getText().isEmpty()) {
            lblError.setText("Completa Insumo, Cantidad, Peso, Motivo y Fecha antes de guardar.");
            return;
        }

        lblError.setText("");

        Alert confirmacion = new Alert(Alert.AlertType.INFORMATION);
        confirmacion.setTitle(modoEdicion ? "Desecho actualizado" : "Desecho registrado");
        confirmacion.setHeaderText(null);
        confirmacion.setContentText("Se " + (modoEdicion ? "actualizó" : "registró") + " el desecho de \""
                + cmbInsumo.getValue() + "\" (" + txtCantidad.getText() + " uds, "
                + txtPeso.getText() + " kg). El stock se descontaría automáticamente del catálogo.");
        confirmacion.showAndWait();
    }

    @FXML
    private void accionCancelar() {
        cmbInsumo.getSelectionModel().clearSelection();
        txtCantidad.clear();
        txtPeso.clear();
        txtMotivo.clear();
        txtFecha.clear();
        txtDescripcion.clear();
        lblError.setText("");
        // txtFolio y txtResponsable no se limpian: los llenará el sistema
    }

    @FXML
    private void accionVolver() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("from_ListaDesecho.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) btnVolver.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Historial de Desecho");
        stage.show();
    }
}