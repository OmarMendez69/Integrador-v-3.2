package utng.gtid2.jome;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
    @FXML private TableColumn<PrestamoHistorial, String> colEstado;
    @FXML private TableColumn<PrestamoHistorial, String> colObservaciones;
    @FXML private TableColumn<PrestamoHistorial, Void> colAccion;
    @FXML private Label lblTotalAsignaciones;
    @FXML private Label lblActivos;
    @FXML private Label lblVencidos;
    @FXML private Label lblDevueltos;

    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @FXML
    public void initialize() {
        colFolio.setCellValueFactory(new PropertyValueFactory<>("folio"));
        colInsumo.setCellValueFactory(new PropertyValueFactory<>("insumo"));
        colTecnico.setCellValueFactory(new PropertyValueFactory<>("tecnico"));
        colFechaPrestamo.setCellValueFactory(new PropertyValueFactory<>("fechaPrestamo"));
        colFechaDevolucion.setCellValueFactory(new PropertyValueFactory<>("fechaDevolucion"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estadoTexto"));
        colObservaciones.setCellValueFactory(new PropertyValueFactory<>("observaciones"));

        configurarColumnaAccion();

        ObservableList<PrestamoHistorial> datos = cargarDatosEjemplo();
        tablaAsignaciones.setItems(datos);

        actualizarContadores(datos);
    }

    /**
     * Columna con botón "Registrar Devolución" por fila.
     * Solo aparece si el préstamo no ha sido devuelto (Activo o Vencido).
     * Por ahora solo cambia el estado en memoria (sin persistencia real).
     */
    private void configurarColumnaAccion() {
        colAccion.setCellFactory(new Callback<>() {
            @Override
            public TableCell<PrestamoHistorial, Void> call(TableColumn<PrestamoHistorial, Void> param) {
                return new TableCell<>() {
                    private final Button btnDevolver = new Button("Registrar Devolución");

                    {
                        btnDevolver.setStyle("-fx-background-color: #2E7D32; -fx-text-fill: white; "
                                + "-fx-font-size: 10px; -fx-background-radius: 4; -fx-cursor: hand;");
                        btnDevolver.setOnAction(e -> {
                            PrestamoHistorial prestamo = getTableView().getItems().get(getIndex());
                            prestamo.setDevuelto(true);
                            getTableView().refresh();
                            actualizarContadores(tablaAsignaciones.getItems());
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            return;
                        }
                        PrestamoHistorial prestamo = getTableView().getItems().get(getIndex());
                        setGraphic(prestamo.isDevuelto() ? null : btnDevolver);
                    }
                };
            }
        });
    }

    /**
     * Datos de ejemplo pensados para que Total / Activos / Vencidos / Devueltos
     * cuadren entre sí y con las fechas reales (referencia: hoy).
     */
    private ObservableList<PrestamoHistorial> cargarDatosEjemplo() {
        return FXCollections.observableArrayList(
                new PrestamoHistorial("F001", "Memoria RAM 8GB", "Angel Nolasco", "10/06/2026", "15/06/2026", "Devuelto en buen estado", true),
                new PrestamoHistorial("F002", "Cable de Red Cat6", "Jonathan Aguilar", "10/07/2026", "20/07/2026", "Sin observaciones", false),
                new PrestamoHistorial("F003", "Laptop Dell", "Jesus Omar", "01/07/2026", "10/07/2026", "Pendiente de revisión, contactar técnico", false),
                new PrestamoHistorial("F004", "Mouse Óptico", "Angel Nolasco", "20/06/2026", "25/06/2026", "Devuelto con daño menor", true),
                new PrestamoHistorial("F005", "Teclado USB", "Jonathan Aguilar", "12/07/2026", "22/07/2026", "Sin observaciones", false)
        );
    }

    /**
     * Calcula Total / Activos / Vencidos / Devueltos a partir de la lista actual.
     * Vencido = préstamo activo (no devuelto) cuya fecha de devolución ya pasó.
     */
    private void actualizarContadores(ObservableList<PrestamoHistorial> datos) {
        LocalDate hoy = LocalDate.now();
        int total = datos.size();
        int activos = 0;
        int vencidos = 0;
        int devueltos = 0;

        for (PrestamoHistorial p : datos) {
            if (p.isDevuelto()) {
                devueltos++;
            } else {
                activos++;
                try {
                    LocalDate fechaDevolucion = LocalDate.parse(p.getFechaDevolucion(), FORMATO_FECHA);
                    if (fechaDevolucion.isBefore(hoy)) {
                        vencidos++;
                    }
                } catch (Exception e) {
                    // fecha con formato inesperado: se ignora para el conteo de vencidos
                }
            }
        }

        lblTotalAsignaciones.setText("Total: " + total + " préstamos");
        lblActivos.setText("Activos: " + activos);
        lblVencidos.setText("Vencidos: " + vencidos);
        lblDevueltos.setText("Devueltos: " + devueltos);
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

    // Modelo simple para mostrar el ejemplo en la tabla
    public static class PrestamoHistorial {
        private final String folio;
        private final String insumo;
        private final String tecnico;
        private final String fechaPrestamo;
        private final String fechaDevolucion;
        private final String observaciones;
        private boolean devuelto;

        public PrestamoHistorial(String folio, String insumo, String tecnico,
                                  String fechaPrestamo, String fechaDevolucion,
                                  String observaciones, boolean devuelto) {
            this.folio = folio;
            this.insumo = insumo;
            this.tecnico = tecnico;
            this.fechaPrestamo = fechaPrestamo;
            this.fechaDevolucion = fechaDevolucion;
            this.observaciones = observaciones;
            this.devuelto = devuelto;
        }

        public String getFolio() { return folio; }
        public String getInsumo() { return insumo; }
        public String getTecnico() { return tecnico; }
        public String getFechaPrestamo() { return fechaPrestamo; }
        public String getFechaDevolucion() { return fechaDevolucion; }
        public String getObservaciones() { return observaciones; }
        public boolean isDevuelto() { return devuelto; }
        public void setDevuelto(boolean devuelto) { this.devuelto = devuelto; }

        // Columna "Estado": derivada de devuelto + fecha, no de texto libre
        public String getEstadoTexto() {
            if (devuelto) return "Devuelto";
            LocalDate hoy = LocalDate.now();
            try {
                LocalDate fecha = LocalDate.parse(fechaDevolucion, FORMATO_FECHA);
                return fecha.isBefore(hoy) ? "Vencido" : "Activo";
            } catch (Exception e) {
                return "Activo";
            }
        }
    }
}