package utng.gtid2.jome;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class ReporteDetalleController {

    @FXML private Label lblTitulo;
    @FXML private TextArea txtContenido;
    @FXML private Label lblMensaje;
    @FXML private Button btnCerrar;
    @FXML private Button btnSimularDescarga;

    /**
     * Recibe el título y el contenido en texto plano a mostrar.
     * Llamado desde ReportesController justo después de cargar el FXML.
     */
    public void setDatos(String titulo, String contenido) {
        lblTitulo.setText(titulo);
        txtContenido.setText(contenido);
    }

    @FXML
    private void accionSimularDescarga() {
        // pendiente: lógica real de generación/guardado de PDF
        lblMensaje.setText("Simulación: el PDF se generaría aquí.");
    }

    @FXML
    private void accionCerrar() {
        Stage stage = (Stage) btnCerrar.getScene().getWindow();
        stage.close();
    }
}