package main.Controller;

import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
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
 * This class controls and handles all processes related to the 'ReportThree.fxml' page.
 */
public class ReportThreeController implements Initializable {
    public VBox reportThreeVBOX;

    /**
     * This initialize method generates a pie chart from the database query and displays on the page.
     *
     * @param url Uniform Resource Locator, a pointer to a "resource" on the World Wide Web.
     * @param resourceBundle Resource bundles contain locale-specific objects
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PieChart p = new PieChart();
        p.setLegendSide(Side.RIGHT);
        p.setTitle("Total Appointments By Customer");

        String getStatement = "select c.Customer_Name as Customer, count(*) as Count from appointments a join customers c on c.Customer_ID = a.Customer_ID group by a.Customer_ID;";

        try {
            DBQuery.setPreparedStatement(DBConnection.getConnection(),getStatement);
            PreparedStatement ps = DBQuery.getPreparedStatement();
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()){
                PieChart.Data  data = new PieChart.Data(rs.getString("Customer")+" - "+rs.getInt("Count"),rs.getInt("Count"));
                p.getData().add(data);
            }

        }catch (SQLException s){
            s.printStackTrace();
        }


        reportThreeVBOX.setAlignment(Pos.CENTER);
        reportThreeVBOX.getChildren().add(p);



        for (final PieChart.Data data : p.getData()) {

                Tooltip tooltip = new Tooltip();
                tooltip.setShowDelay(Duration.millis(350.0));
                tooltip.setText(String.valueOf(data.getPieValue()));
                Tooltip.install(data.getNode(), tooltip);
        }


    }
}
