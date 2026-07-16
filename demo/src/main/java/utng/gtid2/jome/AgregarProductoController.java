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

public class AgregarProductoController {

    @FXML private TextField txtCodigo;
    @FXML private TextField txtNombre;
    @FXML private TextField txtCategoria;
    @FXML private TextField txtUbicacion;
    @FXML private TextField txtCantidadTotal;
    @FXML private TextField txtDisponible;   // deshabilitado: lo calcula el sistema
    @FXML private TextField txtCostoUnitario;
    @FXML private TextField txtEstado;       // deshabilitado: Crítico si Disponible <= 10
    @FXML private Button btnGuardar;
    @FXML private Label lblTitulo;

    private boolean modoEdicion = false;

    /**
     * Autocompleta el formulario para edición. txtDisponible y txtEstado
     * siempre llegan calculados, nunca se capturan a mano.
     */
    public void cargarProducto(String codigo, String nombre, String categoria, String ubicacion,
                                String cantidadTotal, String disponible,
                                String costoUnitario, String estado) {
        modoEdicion = true;

        txtCodigo.setText(codigo);
        txtNombre.setText(nombre);
        txtCategoria.setText(categoria);
        txtUbicacion.setText(ubicacion);
        txtCantidadTotal.setText(cantidadTotal);
        txtDisponible.setText(disponible);
        txtCostoUnitario.setText(costoUnitario);
        txtEstado.setText(estado);

        lblTitulo.setText("Editar Producto");
        btnGuardar.setText("Actualizar");
    }

    @FXML
    private void mostrarInformacion() {
        // por ahora no hace nada
    }

    @FXML
    private void accionCancelar() {
        txtNombre.clear();
        txtCategoria.clear();
        txtUbicacion.clear();
        txtCantidadTotal.clear();
        txtCostoUnitario.clear();
        // txtCodigo, txtDisponible y txtEstado no se tocan: los llena el sistema
    }

    @FXML
    private void accionVolver() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("from_Catalogo.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) txtCodigo.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Catálogo de Insumos");
        stage.show();
    }
}