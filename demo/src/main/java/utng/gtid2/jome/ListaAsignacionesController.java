package utng.gtid2.jome;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class ListaAsignacionesController {

    @FXML private Button btnVolver;
    @FXML private Button btnRegistrarPrestamo;
    @FXML private TextField txtBuscar;
    @FXML private TableView<PrestamoHistorial> tablaAsignaciones;
    @FXML private TableColumn<PrestamoHistorial, String> colFolio;
    @FXML private TableColumn<PrestamoHistorial, String> colInsumo;
    @FXML private TableColumn<PrestamoHistorial, String> colTecnico;
    @FXML private TableColumn<PrestamoHistorial, String> colFechaPrestamo;
    @FXML private TableColumn<PrestamoHistorial, String> colFechaDevolucion;
    @FXML private TableColumn<PrestamoHistorial, String> colObservaciones;
    @FXML private Label lblTotalAsignaciones;

    @FXML
    public void initialize() {
        colFolio.setCellValueFactory(new PropertyValueFactory<>("folio"));
        colInsumo.setCellValueFactory(new PropertyValueFactory<>("insumo"));
        colTecnico.setCellValueFactory(new PropertyValueFactory<>("tecnico"));
        colFechaPrestamo.setCellValueFactory(new PropertyValueFactory<>("fechaPrestamo"));
        colFechaDevolucion.setCellValueFactory(new PropertyValueFactory<>("fechaDevolucion"));
        colObservaciones.setCellValueFactory(new PropertyValueFactory<>("observaciones"));

        tablaAsignaciones.setItems(cargarDatosEjemplo());
        lblTotalAsignaciones.setText("Total: " + tablaAsignaciones.getItems().size() + " préstamos");
    }

    private ObservableList<PrestamoHistorial> cargarDatosEjemplo() {
        return FXCollections.observableArrayList(
                new PrestamoHistorial("F001", "Memoria RAM 8GB", "Angel Nolasco", "10/06/2026", "15/06/2026", "Sin observaciones"),
                new PrestamoHistorial("F002", "Cable de Red Cat6", "Jonathan Aguilar", "12/06/2026", "18/06/2026", "Devuelto en buen estado"),
                new PrestamoHistorial("F003", "Laptop Dell", "Jesus Omar", "15/06/2026", "20/06/2026", "Pendiente de revisión")
        );
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

    // Navegación de demostración hacia el registro de préstamo
    @FXML
    private void accionAbrirPrestamo() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("from_Prestamo.fxml"));
        Parent root = loader.load();

        Stage stage = (Stage) btnRegistrarPrestamo.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Registro de Préstamo");
        stage.show();
    }

    // Modelo simple solo para mostrar el ejemplo en la tabla
    public static class PrestamoHistorial {
        private final String folio;
        private final String insumo;
        private final String tecnico;
        private final String fechaPrestamo;
        private final String fechaDevolucion;
        private final String observaciones;

        public PrestamoHistorial(String folio, String insumo, String tecnico,
                                  String fechaPrestamo, String fechaDevolucion, String observaciones) {
            this.folio = folio;
            this.insumo = insumo;
            this.tecnico = tecnico;
            this.fechaPrestamo = fechaPrestamo;
            this.fechaDevolucion = fechaDevolucion;
            this.observaciones = observaciones;
        }

        public String getFolio() { return folio; }
        public String getInsumo() { return insumo; }
        public String getTecnico() { return tecnico; }
        public String getFechaPrestamo() { return fechaPrestamo; }
        public String getFechaDevolucion() { return fechaDevolucion; }
        public String getObservaciones() { return observaciones; }
    }
}