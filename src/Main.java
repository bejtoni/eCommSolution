import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        fillCustomerOrders("src/zzzCustomer_orders.csv");
        Calculations.calculateFinancials(Order.customerOrders);
        generateReports();
    }

    private static void generateReports() {
        Report revenueReport = new Report("Revenue");
        Report profitReport = new Report("Profit");
        Report profitPerShirtSizeReport = new Report("ProfitPerShirtSize");

        revenueReport.start();
        profitReport.start();
        profitPerShirtSizeReport.start();

        try {
            revenueReport.join();
            profitReport.join();
            profitPerShirtSizeReport.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void fillCustomerOrders(String filePath) {
        File inputFile = new File(filePath);

        try {
            Scanner s1 = new Scanner(inputFile);

            if(s1.hasNextLine()){
                s1.nextLine();
            }

            while (s1.hasNextLine()){
                String currentLine  = s1.nextLine();
                String[] lineParts = currentLine.split(",");

                try {
                    String fullName = lineParts[0].trim();
                    String shirtSize = lineParts[1].trim();
                    Boolean withDesign = Boolean.parseBoolean(lineParts[2].trim());
                    Boolean withHoodie = Boolean.parseBoolean(lineParts[3].trim());
                    String payment = lineParts[4].trim();

                    Order.customerOrders.add(new Order(fullName,shirtSize, withDesign, withHoodie, payment));
                }
                catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
            s1.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}