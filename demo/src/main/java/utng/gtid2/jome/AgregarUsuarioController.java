package utng.gtid2.jome;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AgregarUsuarioController {

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtRol;

    @FXML
    private Button btnGuardar;

    private boolean modoEdicion = false;

    public void cargarUsuario(String nombre, String username, String rol) {
        modoEdicion = true;
        txtNombre.setText(nombre);
        txtUsername.setText(username);
        txtRol.setText(rol);
        btnGuardar.setText("Actualizar");
    }

    @FXML
    private void mostrarInformacion() {
        // por ahora no hace nada
    }

    @FXML
    private void accionCancelar() {
        txtNombre.clear();
        txtUsername.clear();
        txtPassword.clear();
        txtRol.clear();
    }

    @FXML
    private void accionVolver() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("from_ListaUsuarios.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) txtNombre.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Lista de Usuarios");
        stage.show();
    }
}