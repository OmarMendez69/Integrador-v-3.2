package utng.gtid2.jome;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ListaUsuariosController {

    @FXML
    private Button btnResultado;

    @FXML
    private void accionAgregar() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("from_AgregarUsuario.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) btnResultado.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Agregar Usuario");
        stage.show();
    }

    @FXML
    private void accionEditarUsuario1() throws IOException {
        abrirEdicion("Jesus Omar", "jomar", "Admin");
    }

    @FXML
    private void accionEditarUsuario2() throws IOException {
        abrirEdicion("Jesua Rico", "jrico", "Tecnico");
    }

    @FXML
    private void accionEditarUsuario3() throws IOException {
        abrirEdicion("Angel de Jesus", "ajesus", "Usuario");
    }

    private void abrirEdicion(String nombre, String username, String rol) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("from_AgregarUsuario.fxml"));
        Parent root = loader.load();

        AgregarUsuarioController controller = loader.getController();
        controller.cargarUsuario(nombre, username, rol);

        Stage stage = (Stage) btnResultado.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Editar Usuario");
        stage.show();
    }

    @FXML
    private void accionVolver() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Principal.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) btnResultado.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Panel Principal");
        stage.show();
    }
}