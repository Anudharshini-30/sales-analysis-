import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

 class myprogram {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/demo1";
        String user = "root";
        String password = "root";

        try {
            
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the database!");

            
            Statement statement = connection.createStatement();

            
            String totalSalesQuery = "SELECT product_id, SUM(quantity * price) AS total_sales " +
                                     "FROM sales GROUP BY product_id";
            ResultSet resultSet1 = statement.executeQuery(totalSalesQuery);

            System.out.println("Total Sales per Product:");
            while (resultSet1.next()) {
                int productId = resultSet1.getInt("product_id");
                double totalSales = resultSet1.getDouble("total_sales");
                System.out.println("Product ID: " + productId + ", Total Sales: " + totalSales);
            }

            
            String topCustomersQuery = "SELECT customer_id, SUM(quantity * price) AS total_spent " +
                                       "FROM sales GROUP BY customer_id ORDER BY total_spent DESC LIMIT 5";
            ResultSet resultSet2 = statement.executeQuery(topCustomersQuery);

            System.out.println("\nTop 5 Customers by Spending:");
            while (resultSet2.next()) {
                int customerId = resultSet2.getInt("customer_id");
                double totalSpent = resultSet2.getDouble("total_spent");
                System.out.println("Customer ID: " + customerId + ", Total Spent: " + totalSpent);
            }

            
            String monthlySalesQuery = "SELECT MONTH(order_date) AS month, SUM(quantity * price) AS total_sales " +
                                       "FROM sales WHERE YEAR(order_date) = YEAR(CURDATE()) " +
                                       "GROUP BY MONTH(order_date)";
            ResultSet resultSet3 = statement.executeQuery(monthlySalesQuery);

            System.out.println("\nMonthly Sales Summary:");
            while (resultSet3.next()) {
                int month = resultSet3.getInt("month");
                double totalSales = resultSet3.getDouble("total_sales");
                System.out.println("Month: " + month + ", Total Sales: " + totalSales);
            }

            // Close the connection
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}