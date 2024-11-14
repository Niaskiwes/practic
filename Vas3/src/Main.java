import java.util.Scanner;
class Koleso {
    private String tip;
    public Koleso(String tip) {
        this.tip = tip;
    }
    public String getTip() {
        return tip;
    }
}
class Dvigatel {
    private int moshchnost;
    public Dvigatel(int moshchnost) {
        this.moshchnost = moshchnost;
    }
    public int getMoshchnost() {
        return moshchnost;
    }
}
class Avtomobil {
    private String marka;
    private Koleso[] kolesa;
    private Dvigatel dvigatel;
    public Avtomobil(String marka, Koleso[] kolesa, Dvigatel dvigatel) {
        this.marka = marka;
        this.kolesa = kolesa;
        this.dvigatel = dvigatel;
    }
    public void ehhat() {
        System.out.println(marka + " едет.");
    }
    public void zapravljat() {
        System.out.println(marka + " заправляется.");
    }
    public void menyatKoleso(int index, Koleso novoeKoleso) {
        if (index < 0 || index >= kolesa.length) {
            System.out.println("Неверный индекс колеса.");
            return;
        }
        kolesa[index] = novoeKoleso;
        System.out.println("Колесо на позиции " + index + " было заменено.");
    }
    public void vyvestiMarku() {
        System.out.println("Марка автомобиля: " + marka);
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите марку автомобиля: ");
        String marka = scanner.nextLine();
        Koleso[] kolesa = new Koleso[4];
        System.out.print("Введите тип колеса: ");
        String Koleso = scanner.nextLine();
        System.out.print("Введите мощность двигателя: ");
        int moshchnost = scanner.nextInt();
        Dvigatel dvigatel = new Dvigatel(moshchnost);
        Avtomobil avtomobil = new Avtomobil(marka, kolesa, dvigatel);
        avtomobil.ehhat();
        avtomobil.zapravljat();
        System.out.print("Введите индекс колеса для замены (0-3): ");
        int index = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Введите тип нового колеса: ");
        String novoeKolesoTip = scanner.nextLine();
        avtomobil.menyatKoleso(index, new Koleso(novoeKolesoTip));
        avtomobil.vyvestiMarku();
        scanner.close();
    }
}
