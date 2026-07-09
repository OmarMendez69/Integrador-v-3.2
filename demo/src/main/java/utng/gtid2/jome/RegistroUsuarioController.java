package utng.gtid2.jome;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RegistroUsuarioController {

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Button btnResultado;

    @FXML
    private Button btnLogin;

    @FXML
    private Label lblRegistro;

    @FXML
    private void mostrarInformacion() {
        String nombre = txtNombre.getText();
        String username = txtUsername.getText();

        lblRegistro.setText("Usuario " + username + " (" + nombre + ") registrado.");
    }

    @FXML
    private void accionVolverLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Pantalla_Login.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) btnLogin.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Login");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            lblRegistro.setText("Error al volver al login");
        }
    }
}