package utng.gtid2.jome;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class CatalogoController {

    @FXML private TextField txtBuscar;
    @FXML private ComboBox<String> cmbFiltroCategoria;
    @FXML private ComboBox<String> cmbFiltroEstado;
    @FXML private TableView<?> tablaMateriales;
    @FXML private TableColumn<?, ?> colId;
    @FXML private TableColumn<?, ?> colNombre;
    @FXML private TableColumn<?, ?> colCategoria;
    @FXML private TableColumn<?, ?> colCantidadTotal;
    @FXML private TableColumn<?, ?> colCantidadDisponible;
    @FXML private TableColumn<?, ?> colUbicacion;
    @FXML private TableColumn<?, ?> colCostoUnitario;
    @FXML private TableColumn<?, ?> colEstado;
    @FXML private TableColumn<?, ?> colAcciones;
    @FXML private Label lblUsuario;
    @FXML private Label lblTotalMateriales;
    @FXML private Label lblDisponibles;
    @FXML private Label lblPrestados;
    @FXML private Label lblStockBajo;
    @FXML private Label lblUltimaActualizacion;
    @FXML private Button btnVolver;

    public void setUsuario(String nombreUsuario) {
        lblUsuario.setText("👤 " + nombreUsuario);
    }

    @FXML
    private void handleAgregarMaterial() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("from_AgregarProducto.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) btnVolver.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Agregar Producto");
        stage.show();
    }

    @FXML
    private void handleActualizar() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("from_AgregarProducto.fxml"));
        Parent root = loader.load();

        AgregarProductoController controller = loader.getController();
        // TODO: cuando exista el modelo, pasar aquí los datos reales de la fila seleccionada
        controller.cargarProducto("", "", "", "", "", "", "", "");

        Stage stage = (Stage) btnVolver.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Editar Producto");
        stage.show();
    }

    /**
     * Botón "Eliminar insumo": abre from_BajaInsumo.fxml, que en esta versión
     * de la interfaz funciona como "Eliminar Producto" — para cuando el técnico
     * se equivocó al capturar o lo agregó por accidente. Elimina el insumo
     * por completo, sin motivo/fecha/responsable/autorización (eso sería
     * la baja oficial institucional, un flujo distinto a futuro).
     */
    @FXML
    private void handleEliminarMaterial() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("from_BajaInsumo.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) btnVolver.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Eliminar Producto");
        stage.show();
    }

    @FXML
    private void handleLimpiarFiltros() {
        txtBuscar.clear();
        cmbFiltroCategoria.getSelectionModel().clearSelection();
        cmbFiltroEstado.getSelectionModel().clearSelection();
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