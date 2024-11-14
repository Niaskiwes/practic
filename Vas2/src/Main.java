import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
class Customer {
    private int id;
    private String lastName;
    private String firstName;
    private String patronymic;
    private String address;
    private String creditCardNumber;
    private String bankAccountNumber;
    public Customer(int id, String lastName, String firstName, String patronymic, String address, String creditCardNumber, String bankAccountNumber) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.address = address;
        this.creditCardNumber = creditCardNumber;
        this.bankAccountNumber = bankAccountNumber;
    }
    @Override
    public String toString() {
        return "ID: " + id + ", �������: " + lastName + ", ���: " + firstName + ", ��������: " + patronymic +
                ", �����: " + address + ", ����� ��������� ��������: " + creditCardNumber +
                ", ����� ����������� �����: " + bankAccountNumber;
    }
    public String getCreditCardNumber() {
        return creditCardNumber;
    }
    public String getLastName() {
        return lastName;
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Customer[] customers = new Customer[] {
                new Customer(1, "��������", "������", "����������", "���������������� 48 ", "1234-5678-9101-1121", "123456789"),
                new Customer(2, "�������", "�����", "����������", "����������������� 54", "2345-6789-0123-4567", "987654321"),
                new Customer(3, "�����", "������", "������������", "�.�������� 130", "3456-7890-1234-5678", "234567890"),
                new Customer(4, "������", "����", "��������", "���������� 20", "4567-8901-2345-6789", "345678901"),
                new Customer(5, "������", "�����", "�������������", "�������� 166", "5678-9012-3456-7890", "456789012"),
        };
        Arrays.sort(customers, Comparator.comparing(Customer::getLastName));
        System.out.println("������ ����������� � ���������� �������:");
        for (Customer customer : customers) {
            System.out.println(customer);
        }
        System.out.print("������� ������ ��������� ������ ��������� ��������: ");
        String startRange = scanner.nextLine();
        System.out.print("������� ����� ��������� ������ ��������� ��������: ");
        String endRange = scanner.nextLine();

        System.out.println("������ ����������� � ������� ��������� �������� � �������� ���������:");
        for (Customer customer : customers) {
            String creditCardNumber = customer.getCreditCardNumber();
            if (creditCardNumber.compareTo(startRange) >= 0 && creditCardNumber.compareTo(endRange) <= 0) {
                System.out.println(customer);
            }
        }
        scanner.close();
    }
}
