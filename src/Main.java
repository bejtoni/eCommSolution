public class Main {
    public static void main(String[] args) {
        Order.fillCustomerOrders("src/zzzCustomer_orders.csv");
        Calculations.calculateFinancials(Order.customerOrders);
        generateReports();
    }

    private static void generateReports() {
        Report revenueReport = new Report("Revenue");
        Report profitReport = new Report("Profit");
        Report profitPerShirtSizeReport = new Report("ProfitPerShirtSize");

        Thread t1 = new Thread(revenueReport);
        Thread t2 = new Thread(profitReport);
        Thread t3 = new Thread(profitPerShirtSizeReport);

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}