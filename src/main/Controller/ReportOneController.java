package main.Controller;

import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import main.Util.DBConnection;
import main.Util.DBQuery;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * @author Ron Mercier - 001406973
 *
 * This class controls and handles all processes related to the 'ReportOne.fxml' page.
 */
public class ReportOneController implements Initializable {
    public BarChart<String,Number> appointmentsByTypeBarChart;
    public CategoryAxis monthAxis;
    public NumberAxis appointmentAxis;
    public VBox reportOneVBOX;

    /**
     * This method initializes the ReportOne view
     * @param url Uniform Resource Locator, a pointer to a "resource" on the World Wide Web
     * @param resourceBundle Resource bundles contain locale-specific objects
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buildAppointmentsByWeekBarChart();
    }

    /**
     * This method builds the BarChart shown in the view, gathering data from the Appointments table in the
     * database
     */
    public void buildAppointmentsByWeekBarChart(){

        monthAxis = new CategoryAxis();
        monthAxis.setLabel("Months - 2021");

        appointmentAxis = new NumberAxis();
        appointmentAxis.setTickUnit(1.0);
        appointmentAxis.setMinorTickVisible(false);
        appointmentAxis.setLabel("Appointments");

        appointmentsByTypeBarChart = new BarChart<>(monthAxis,appointmentAxis);
        appointmentsByTypeBarChart.setTitle("Appointments By Type");

        XYChart.Series<String,Number> dataSeries1 = new XYChart.Series<>();
        dataSeries1.setName("Urgent");

        XYChart.Series<String,Number> dataSeries2 = new XYChart.Series<>();
        dataSeries2.setName("Regular");
        String getStatement = "select count(Title) as Count,Type,MONTHNAME(Start) as mn,MONTH(Start) as Month from appointments group by MONTH(Start),mn,Type order by Month;";


        try {
            DBQuery.setPreparedStatement(DBConnection.getConnection(),getStatement);
            PreparedStatement ps = DBQuery.getPreparedStatement();
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()){

                if (rs.getString("Type").equals("Urgent")){

                    dataSeries1.getData().add(new XYChart.Data<>(rs.getString("mn"),rs.getInt("Count")));

                }
                if (rs.getString("Type").equals("Regular")){

                    dataSeries2.getData().add(new XYChart.Data<>(rs.getString("mn"),rs.getInt("Count")));
                }
            }

            appointmentsByTypeBarChart.getData().add(dataSeries1);
            appointmentsByTypeBarChart.getData().add(dataSeries2);



        }catch (SQLException s){
            s.printStackTrace();
        }
        appointmentsByTypeBarChart.setBarGap(3);
        appointmentsByTypeBarChart.setCategoryGap(20);
        reportOneVBOX.setAlignment(Pos.CENTER);
        reportOneVBOX.getChildren().add(appointmentsByTypeBarChart);

        for (final XYChart.Series<String, Number> series : appointmentsByTypeBarChart.getData()) {
            for (final XYChart.Data<String, Number> data : series.getData()) {
                Tooltip tooltip = new Tooltip();
                tooltip.setShowDelay(Duration.millis(350.0));
                tooltip.setText(data.getXValue() +" - "+
                        data.getYValue().toString());
                Tooltip.install(data.getNode(), tooltip);
            }
        }
    }
}
