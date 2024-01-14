import java.io.FileWriter;
import java.util.*;
import java.util.stream.Collectors;

public class Report extends Thread {
    private static final double totalRevenue = Calculations.getTotalRevenue();
    private static final double totalProfit = Calculations.getTotalProfit();
    private static  Map<String, Double> totalPricePerShirtSize = Calculations.getProfitPerShirtSizeMap();
    private String reportName;

    public Report(String reportName) {
        this.reportName = reportName;
    }

    @Override
    public void run() {
        switch (reportName) {
            case "Revenue":
                generateRevenueReport(totalRevenue);
                break;
            case "Profit":
                generateProfitReport(totalProfit);
                break;
            case "ProfitPerShirtSize":
                generateProfitPerShirtSizeReport(totalPricePerShirtSize);
                break;
            default:
                System.out.println("Unsupported report type: " + reportName);
        }
    }

    private static void generateRevenueReport(double totalRevenue) {
        generateReport("Revenue", "Total Revenue: €" + Math.round(totalRevenue));
    }

    private static void generateProfitReport(double totalProfit) {
        generateReport("Profit", "Total Profit: €" + Math.round(totalProfit));
    }

    private static void generateProfitPerShirtSizeReport(Map<String, Double> totalPricePerShirtSize) {
        Comparator<String> sizeComparator = Comparator.comparingInt(size -> {
            switch (size) {
                case "XS": return 1;
                case "S": return 2;
                case "M": return 3;
                case "L": return 4;
                case "XL": return 5;
                case "2XL": return 6;
                case "3XL": return 7;
                default: return -1;
            }
        });

        Map<String, Integer> sortedMap = totalPricePerShirtSize.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey(sizeComparator))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> (int) Math.round(entry.getValue()),
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));

        StringBuilder content = new StringBuilder("Total Profit per Shirt Size:\n");
        sortedMap.forEach((key, value) -> content.append(key).append(": €").append(value).append("\n"));

        generateReport("ProfitPerShirtSize", content.toString());
    }


    private static void generateReport(String reportName, String content) {
        try {
            FileWriter writer = new FileWriter(reportName + " Report.txt");
            writer.write(content);
            writer.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
