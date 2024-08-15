
    // import javafx.application.Application;  
    // import javafx.event.ActionEvent;  
    // import javafx.event.EventHandler;  
    // import javafx.scene.Scene;  
    // import javafx.scene.control.Button;  
    // import javafx.stage.Stage;  
    // import javafx.scene.layout.StackPane;  
    // public class App extends Application{  
      
    //     @Override  
    //     public void start(Stage primaryStage) throws Exception {  
    //         // TODO Auto-generated method stub  
    //         Button btn1=new Button("Say, Hello World");  
    //         btn1.setOnAction(new EventHandler<ActionEvent>() {  
                  
    //             @Override  
    //             public void handle(ActionEvent arg0) {  
    //                 // TODO Auto-generated method stub  
    //                 System.out.println("hello world");  
    //             }  
    //         });  
    //         StackPane root=new StackPane();  
    //         root.getChildren().add(btn1);  
    //         Scene scene=new Scene(root,600,400);      
    //         primaryStage.setTitle("First JavaFX Application");  
    //         primaryStage.setScene(scene);  
    //         primaryStage.show();  
    //     }  
    //     public static void main (String[] args)  
    //     {  
    //         launch(args);  
    //     }  
      
    // }
    // import javafx.application.Application;
    // import javafx.scene.Scene;
    // import javafx.scene.chart.LineChart;
    // import javafx.scene.chart.NumberAxis;
    // import javafx.scene.chart.XYChart;
    // import javafx.stage.Stage;
    
    // import java.sql.Connection;
    // import java.sql.DriverManager;
    // import java.sql.ResultSet;
    // import java.sql.Statement;
    // import java.util.ArrayList;
    // import java.util.List;
    
    // public class App extends Application {
    
    //     public static void main(String[] args) {
    //         launch(args);
    //     }
    
    //     @Override
    //     public void start(Stage stage) {
    //         stage.setTitle("JavaFX Line Chart with MySQL Data");
    
    //         // Define X and Y axes
    //         final NumberAxis xAxis = new NumberAxis();
    //         final NumberAxis yAxis = new NumberAxis();
    //         xAxis.setLabel("Quantity");
    //         yAxis.setLabel("Price");
    
    //         // Create the line chart
    //         final LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
    //         lineChart.setTitle("Sales Data Over Quantity");
    
    //         // Define a series to store data
    //         XYChart.Series<Number, Number> series = new XYChart.Series<>();
    //         series.setName("Sales Data");
    
    //         // Fetch data from MySQL
    //         List<Double> xData = new ArrayList<>();
    //         List<Double> yData = new ArrayList<>();
    
    //         String jdbcUrl = "jdbc:mysql://localhost:3306/demo1";
    //         String username = "root";
    //         String password = "root";
    
    //         try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
    //              Statement statement = connection.createStatement()) {
    
    //             String query = "SELECT quantity, price FROM sales";
    //             ResultSet resultSet = statement.executeQuery(query);
    
    //             while (resultSet.next()) {
    //                 double quantity = resultSet.getDouble("quantity");
    //                 double price = resultSet.getDouble("price");
    //                 if (!resultSet.wasNull()) { // Check for null values
    //                     xData.add(quantity);
    //                     yData.add(price);
    //                 }
    //             }
    
    //         } catch (Exception e) {
    //             e.printStackTrace(); // Consider using a logging framework for better error handling
    //         }
    
    //         // Add data to the series
    //         for (int i = 0; i < xData.size(); i++) {
    //             series.getData().add(new XYChart.Data<>(xData.get(i), yData.get(i)));
    //         }
    
    //         // Add series to chart
    //         lineChart.getData().add(series);
    
    //         // Create and set scene
    //         Scene scene = new Scene(lineChart, 800, 600);
    //         stage.setScene(scene);
    //         stage.show();
    //     }
    // }
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("JavaFX Line Chart with MySQL Data");

        // Define X and Y axes
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Quantity");
        yAxis.setLabel("Price");

        // Create the line chart
        final LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Sales Data Over Quantity");

        // Define a series to store data
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Sales Data");

        // Fetch data from MySQL
        List<Double> xData = new ArrayList<>();
        List<Double> yData = new ArrayList<>();

        String jdbcUrl = "jdbc:mysql://localhost:3306/demo1";
        String username = "root";
        String password = "root";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             Statement statement = connection.createStatement()) {

            String query = "SELECT quantity, price FROM sales";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                double quantity = resultSet.getDouble("quantity");
                double price = resultSet.getDouble("price");
                if (!resultSet.wasNull()) { // Check for null values
                    xData.add(quantity);
                    yData.add(price);
                }
            }

        } catch (Exception e) {
            e.printStackTrace(); // Consider using a logging framework for better error handling
        }

        // Add data to the series
        for (int i = 0; i < xData.size(); i++) {
            series.getData().add(new XYChart.Data<>(xData.get(i), yData.get(i)));
        }

        // Add series to chart
        lineChart.getData().add(series);

        // Create and set scene
        Scene scene = new Scene(lineChart, 800, 600);
        stage.setScene(scene);
        stage.show();
    }
}
