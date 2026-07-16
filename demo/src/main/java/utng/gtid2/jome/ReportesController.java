package utng.gtid2.jome;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ReportesController {

    @FXML private Button btnVolver;

    @FXML
    private void descargarResumen() {
        String contenido =
                "===================================\n" +
                "  REPORTE - RESUMEN GENERAL\n" +
                "===================================\n\n" +
                "Insumos registrados:          86\n" +
                "Insumos en estado crítico:    7\n" +
                "Técnicos activos:             5\n" +
                "Desecho del mes:              12.4 kg\n\n" +
                "-----------------------------------\n" +
                "Generado por CGTI - Sistema de Inventario\n";
        abrirDetalle("Reporte - Resumen General", contenido);
    }

    @FXML
    private void descargarCatalogo() {
        String contenido =
                "===================================\n" +
                "  REPORTE - CATÁLOGO DE INSUMOS\n" +
                "===================================\n\n" +
                "Código   Nombre           Categoría     Cantidad\n" +
                "-----------------------------------------------\n" +
                "P001     Laptop Dell      Insumo        2\n" +
                "P002     Tinta Negra      Consumible    5\n" +
                "P003     Cable HDMI       Insumo        10\n\n" +
                "-----------------------------------\n" +
                "Generado por CGTI - Sistema de Inventario\n";
        abrirDetalle("Reporte - Catálogo de Insumos", contenido);
    }

    @FXML
    private void descargarAsignaciones() {
        String contenido =
                "===================================\n" +
                "  REPORTE - ASIGNACIONES\n" +
                "===================================\n\n" +
                "Folio    Técnico            Material      Fecha\n" +
                "-----------------------------------------------\n" +
                "F001     Angel Nolasco      RAM 8GB       10/06/26\n" +
                "F002     Jonathan Aguilar   Cable Red     12/06/26\n" +
                "F003     Jesus Omar         Laptop Dell   15/06/26\n\n" +
                "-----------------------------------\n" +
                "Generado por CGTI - Sistema de Inventario\n";
        abrirDetalle("Reporte - Asignaciones", contenido);
    }

    @FXML
    private void descargarDesecho() {
        String contenido =
                "===================================\n" +
                "  REPORTE - REGISTRO DE DESECHO\n" +
                "===================================\n\n" +
                "Código   Equipo             Motivo         Fecha\n" +
                "-----------------------------------------------\n" +
                "E001     Monitor Samsung    Pantalla rota  01/06/26\n" +
                "E002     Teclado Logitech   No enciende    05/06/26\n" +
                "E003     Mouse HP           Desgaste       08/06/26\n\n" +
                "-----------------------------------\n" +
                "Generado por CGTI - Sistema de Inventario\n";
        abrirDetalle("Reporte - Registro de Desecho", contenido);
    }

    @FXML
    private void descargarUsuarios() {
        String contenido =
                "===================================\n" +
                "  REPORTE - USUARIOS REGISTRADOS\n" +
                "===================================\n\n" +
                "ID    Nombre              Rol\n" +
                "-----------------------------------\n" +
                "001   Jesus Omar          Admin\n" +
                "002   Jesua Rico          Tecnico\n" +
                "003   Angel de Jesus      Usuario\n\n" +
                "-----------------------------------\n" +
                "Generado por CGTI - Sistema de Inventario\n";
        abrirDetalle("Reporte - Usuarios Registrados", contenido);
    }

    @FXML
    private void descargarReporte() {
        String contenido =
                "===================================\n" +
                "  REPORTE GENERAL - SISTEMA CGTI\n" +
                "===================================\n\n" +
                "--- Resumen General ---\n" +
                "Insumos registrados: 86 | Críticos: 7 | Técnicos: 5 | Desecho: 12.4 kg\n\n" +
                "--- Catálogo de Insumos ---\n" +
                "P001 Laptop Dell (2) | P002 Tinta Negra (5) | P003 Cable HDMI (10)\n\n" +
                "--- Asignaciones ---\n" +
                "F001 Angel Nolasco - RAM 8GB (10/06/26)\n" +
                "F002 Jonathan Aguilar - Cable Red (12/06/26)\n" +
                "F003 Jesus Omar - Laptop Dell (15/06/26)\n\n" +
                "--- Registro de Desecho ---\n" +
                "E001 Monitor Samsung - Pantalla rota (01/06/26)\n" +
                "E002 Teclado Logitech - No enciende (05/06/26)\n" +
                "E003 Mouse HP - Desgaste (08/06/26)\n\n" +
                "--- Usuarios Registrados ---\n" +
                "001 Jesus Omar (Admin) | 002 Jesua Rico (Tecnico) | 003 Angel de Jesus (Usuario)\n\n" +
                "-----------------------------------\n" +
                "Generado por CGTI - Sistema de Inventario\n";
        abrirDetalle("Reporte General del Sistema", contenido);
    }

    /**
     * Carga ReporteDetalle.fxml como ventana modal y le pasa el
     * título y contenido a mostrar.
     */
    private void abrirDetalle(String titulo, String contenido) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ReporteDetalle.fxml"));
            Parent root = loader.load();

            ReporteDetalleController controller = loader.getController();
            controller.setDatos(titulo, contenido);

            Stage ventana = new Stage();
            ventana.initModality(Modality.APPLICATION_MODAL);
            ventana.setTitle(titulo);
            ventana.setScene(new Scene(root));
            ventana.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
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