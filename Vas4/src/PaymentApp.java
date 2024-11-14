import java.util.ArrayList;
import java.util.Scanner;
public class PaymentApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Product> products = new ArrayList<>();
        while (true) {
            System.out.println("������� �������� �������� (��� ������� ������������):");
            String productName = scanner.nextLine();
            if (productName.equalsIgnoreCase("������������")) {
                break;
            }
            System.out.println("������� ���� ��������:");
            String priceInput = scanner.nextLine();
            try {
                double price = Double.parseDouble(priceInput);
                if (price < 0) {
                    System.out.println("���� �� ����� ���� �������������. ���������� ��� ���.");
                    continue;
                }
                products.add(new Product(productName, price));
                System.out.println("������� ��������: " + productName + " - " + price + " ? ");
            } catch (NumberFormatException e) {
                System.out.println("������ �����. ����������, ������� ���������� ����.");
            }
        }
        if (!products.isEmpty()) {
            Payment payment = new Payment(products);
            double total = payment.calculateTotal();
            System.out.printf("�����: %.2f ?\n", total);
        } else {
            System.out.println("������ ��������� ����. ������ �� �������� ��� ������.");
        }
        scanner.close();
    }
    private static class Product {
        private String name;
        private double price;

        public Product(String name, double price) {
            this.name = name;
            this.price = price;
        }
        public double getPrice() {
            return price;
        }
    }
    private static class Payment {
        private ArrayList<Product> products;
        public Payment(ArrayList<Product> products) {
            this.products = products;
        }
        public double calculateTotal() {
            double total = 0;
            for (Product product : products) {
                total += product.getPrice();
            }
            return total;
        }
    }
}
