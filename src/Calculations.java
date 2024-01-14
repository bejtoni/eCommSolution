import java.util.*;
public class Calculations {
    private static double totalRevenue = 0.0;
    private static double totalProfit = 0.0;
    private static final Map<String, Payment> paymentStrategyMap = new HashMap<>();
    private static final Map<String, Double> profitPerShirtSizeMap = new HashMap<>();


    static {
        paymentStrategyMap.put("wallet", new WalletPayment());
        paymentStrategyMap.put("bankcard", new BankcardPayment());
        paymentStrategyMap.put("visa", new VisaPayment());
        paymentStrategyMap.put("mastercard", new MastercardPayment());
        paymentStrategyMap.put("other", new OtherPayment());
    }

    // Pretpostavka da nema unsupported payment types u csv, mada i to se handluje sa ovim other
    // Dobijam payment based on string, a ako se ne poklopi sa hardcodovanim inputom ide na other
    // Lines 27-32

    public static void calculateFinancials(ArrayList<Order> customerOrders) {
        for(Order order : customerOrders){
            double currentPrice = 40.0;

            if(order.hasHoodie())currentPrice += 3.0;
            if(order.hasDesign())currentPrice += 2.0;

            totalRevenue += currentPrice;

            Payment paymentStrategy = paymentStrategyMap.get(order.getPayment());
            if (paymentStrategy == null) {
                paymentStrategy = paymentStrategyMap.get("other");
            }

            PaymentContext paymentContext = new PaymentContext(paymentStrategy);
            currentPrice -= 14.0;
            double transactionFee = paymentContext.getPaymentFee(currentPrice);
            totalProfit += (currentPrice - transactionFee);

            String shirtSize = order.getShirtSize();
            profitPerShirtSizeMap.put(shirtSize, profitPerShirtSizeMap.getOrDefault(shirtSize, 0.0) + (currentPrice - transactionFee));
        }
    }

    public static double getTotalRevenue() {
        return totalRevenue;
    }
    public static double getTotalProfit() {
        return totalProfit;
    }

    public static Map<String, Double> getProfitPerShirtSizeMap() {
        return profitPerShirtSizeMap;
    }
}
