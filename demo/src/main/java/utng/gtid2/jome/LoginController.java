package utng.gtid2.jome;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Label lblMensaje;

    @FXML
    private void accionLogin() {
        String usuario = txtUsername.getText();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Principal.fxml"));
            Parent root = loader.load();

            PrimaryController controller = loader.getController();
            controller.setUsuario(usuario);

            Stage stage = (Stage) txtUsername.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Panel Principal");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            lblMensaje.setText("Error al abrir el panel principal");
        }
    }

    @FXML
    private void accionRegistrar() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("from_RegistroUsuarios.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) lblMensaje.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Registro de Usuario");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            lblMensaje.setText("Error al abrir el registro");
        }
    }
}