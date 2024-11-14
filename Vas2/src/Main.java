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
        return "ID: " + id + ", Фамилия: " + lastName + ", Имя: " + firstName + ", Отчество: " + patronymic +
                ", Адрес: " + address + ", Номер кредитной карточки: " + creditCardNumber +
                ", Номер банковского счета: " + bankAccountNumber;
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
                new Customer(1, "Сеничкин", "Никита", "Валерьевич", "Коммунистическая 48 ", "1234-5678-9101-1121", "123456789"),
                new Customer(2, "Антонов", "Вадим", "Дмитриевич", "Интернауиональная 54", "2345-6789-0123-4567", "987654321"),
                new Customer(3, "Бабич", "Михаил", "Валентинович", "П.Морозова 130", "3456-7890-1234-5678", "234567890"),
                new Customer(4, "Иванов", "Иван", "Иванович", "Емельянова 20", "4567-8901-2345-6789", "345678901"),
                new Customer(5, "Петров", "Денис", "Александрович", "Горькогт 166", "5678-9012-3456-7890", "456789012"),
        };
        Arrays.sort(customers, Comparator.comparing(Customer::getLastName));
        System.out.println("Список покупателей в алфавитном порядке:");
        for (Customer customer : customers) {
            System.out.println(customer);
        }
        System.out.print("Введите начало интервала номера кредитной карточки: ");
        String startRange = scanner.nextLine();
        System.out.print("Введите конец интервала номера кредитной карточки: ");
        String endRange = scanner.nextLine();

        System.out.println("Список покупателей с номером кредитной карточки в заданном интервале:");
        for (Customer customer : customers) {
            String creditCardNumber = customer.getCreditCardNumber();
            if (creditCardNumber.compareTo(startRange) >= 0 && creditCardNumber.compareTo(endRange) <= 0) {
                System.out.println(customer);
            }
        }
        scanner.close();
    }
}
