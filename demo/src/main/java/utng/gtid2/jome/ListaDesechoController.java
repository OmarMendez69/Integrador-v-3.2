package utng.gtid2.jome;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ListaDesechoController {

    @FXML private Button btnResultado;
    @FXML private Button btnVolver;
    @FXML private TextField txtBuscar;
    @FXML private Label lblTotalDesecho;

    @FXML
    private void accionAgregar() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("from_RegistroDesecho.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) btnResultado.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Nuevo Registro de Desecho");
        stage.show();
    }

    @FXML
    private void accionEditarDesecho1() throws IOException {
        abrirEdicion("INS-004 - Monitor Samsung", "1", "4.2", "Pantalla rota", "01/06/2026", "Angel Nolasco", "Sin observaciones");
    }

    @FXML
    private void accionEditarDesecho2() throws IOException {
        abrirEdicion("INS-005 - Teclado Logitech", "2", "0.8", "No enciende", "05/06/2026", "Jonathan Aguilar", "Sin observaciones");
    }

    @FXML
    private void accionEditarDesecho3() throws IOException {
        abrirEdicion("INS-006 - Mouse HP", "5", "0.5", "Desgaste", "08/06/2026", "Jesus Omar", "Sin observaciones");
    }

    private void abrirEdicion(String insumo, String cantidad, String peso, String motivo,
                               String fecha, String responsable, String descripcion) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("from_RegistroDesecho.fxml"));
        Parent root = loader.load();

        RegistroDesechoController controller = loader.getController();
        controller.cargarDesecho(insumo, cantidad, peso, motivo, fecha, responsable, descripcion);

        Stage stage = (Stage) btnResultado.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Editar Registro de Desecho");
        stage.show();
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
}