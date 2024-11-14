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
        System.out.println(marka + " ����.");
    }
    public void zapravljat() {
        System.out.println(marka + " ������������.");
    }
    public void menyatKoleso(int index, Koleso novoeKoleso) {
        if (index < 0 || index >= kolesa.length) {
            System.out.println("�������� ������ ������.");
            return;
        }
        kolesa[index] = novoeKoleso;
        System.out.println("������ �� ������� " + index + " ���� ��������.");
    }
    public void vyvestiMarku() {
        System.out.println("����� ����������: " + marka);
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("������� ����� ����������: ");
        String marka = scanner.nextLine();
        Koleso[] kolesa = new Koleso[4];
        System.out.print("������� ��� ������: ");
        String Koleso = scanner.nextLine();
        System.out.print("������� �������� ���������: ");
        int moshchnost = scanner.nextInt();
        Dvigatel dvigatel = new Dvigatel(moshchnost);
        Avtomobil avtomobil = new Avtomobil(marka, kolesa, dvigatel);
        avtomobil.ehhat();
        avtomobil.zapravljat();
        System.out.print("������� ������ ������ ��� ������ (0-3): ");
        int index = scanner.nextInt();
        scanner.nextLine();
        System.out.print("������� ��� ������ ������: ");
        String novoeKolesoTip = scanner.nextLine();
        avtomobil.menyatKoleso(index, new Koleso(novoeKolesoTip));
        avtomobil.vyvestiMarku();
        scanner.close();
    }
}
