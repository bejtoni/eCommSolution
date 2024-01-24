import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Order {
    private final String fullName;
    private final String shirtSize;
    private final Boolean withDesign;
    private final Boolean withHoodie;
    private final String payment;

    public static final ArrayList<Order> customerOrders = new ArrayList<>();

    public Order(String fullName, String shirtSize, Boolean withDesign, Boolean withHoodie, String payment) {
        this.fullName = fullName;
        this.shirtSize = shirtSize;
        this.withDesign = withDesign;
        this.withHoodie = withHoodie;
        this.payment = payment;
    }

    public static void fillCustomerOrders(String filePath) {
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

    public Boolean hasDesign() {
        return withDesign;
    }

    public Boolean hasHoodie() {
        return withHoodie;
    }

    public String getPayment() {
        return payment;
    }

    public String getShirtSize() {
        return shirtSize;
    }

    @Override
    public String toString() {
        return "Order{" +
                "fullName='" + fullName + '\'' +
                ", shirtSize='" + shirtSize + '\'' +
                ", withDesign=" + withDesign +
                ", withHoodie=" + withHoodie +
                ", payment='" + payment + '\'' +
                '}';
    }
}
