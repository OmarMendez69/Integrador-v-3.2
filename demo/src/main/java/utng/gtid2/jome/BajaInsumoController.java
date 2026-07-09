package utng.gtid2.jome;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * A pesar del nombre de la clase (BajaInsumo), esta pantalla funciona como
 * "Eliminar Producto": borra por completo un insumo del catálogo cuando
 * el técnico lo agregó por error o con datos equivocados. No lleva motivo,
 * fecha, responsable ni autorización — eso correspondería a la baja oficial
 * institucional, un flujo distinto que se implementaría en otra pantalla.
 */
public class BajaInsumoController {

    @FXML private TextField txtCodigo;
    @FXML private TextField txtNombre;
    @FXML private TextField txtCategoria;
    @FXML private Button btnVolver;

    /**
     * Autocompleta (solo lectura) los datos del insumo seleccionado en el catálogo.
     */
    public void cargarProducto(String codigo, String nombre, String categoria) {
        txtCodigo.setText(codigo);
        txtNombre.setText(nombre);
        txtCategoria.setText(categoria);
    }

    @FXML
    private void mostrarInformacion() {
        // por ahora no hace nada
    }

    @FXML
    private void accionCancelar() {
        txtCodigo.clear();
        txtNombre.clear();
        txtCategoria.clear();
    }

    @FXML
    private void accionVolver() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("from_Catalogo.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) btnVolver.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Catálogo de Insumos");
        stage.show();
    }
}