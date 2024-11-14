import java.util.Scanner;
class InvalidValueException extends Exception {
    public InvalidValueException(String message) {
        super(message);
    }
}
class WheelIndexOutOfBoundsException extends Exception {
    public WheelIndexOutOfBoundsException(String message) {
        super(message);
    }
}
class Koleso {
    private String tip;

    public Koleso(String tip) throws InvalidValueException {
        if (tip == null || tip.isEmpty()) {
            throw new InvalidValueException("��� ������ �� ����� ���� ������.");
        }
        this.tip = tip;
    }
    public String getTip() {
        return tip;
    }
}
class Dvigatel {
    private int moshchnost;

    public Dvigatel(int moshchnost) throws InvalidValueException {
        if (moshchnost <= 0) {
            throw new InvalidValueException("�������� ��������� ������ ���� �������������.");
        }
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

    public Avtomobil(String marka, Koleso[] kolesa, Dvigatel dvigatel) throws InvalidValueException {
        if (marka == null || marka.isEmpty() || kolesa == null || kolesa.length == 0 || dvigatel == null) {
            throw new InvalidValueException("������ ��� �������� ����������: ��������� ������� ������.");
        }
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
        try {
            if (index < 0 || index >= kolesa.length) {
                throw new WheelIndexOutOfBoundsException("�������� ������ ������: " + index);
            }
            kolesa[index] = novoeKoleso;
            System.out.println("������ �� ������� " + index + " ���� ��������.");
        } catch (WheelIndexOutOfBoundsException e) {
            System.out.println("������: " + e.getMessage());
        }
    }
    public void vyvestiMarku() {
        System.out.println("����� ����������: " + marka);
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String marka = "";
        Koleso[] kolesa = new Koleso[4];
        Dvigatel dvigatel = null;
        while (true) {
            try {
                System.out.print("������� ����� ����������: ");
                marka = scanner.nextLine();
                if (marka.isEmpty()) {
                    throw new InvalidValueException("����� �� ����� ���� ������.");
                }
                break;
            } catch (InvalidValueException e) {
                System.out.println("������: " + e.getMessage());
            }
        }
        for (int i = 0; i < kolesa.length; i++) {
            while (true) {
                try {
                    System.out.print("������� ��� ������ #" + (i + 1) + ": ");
                    String tip = scanner.nextLine();
                    kolesa[i] = new Koleso(tip);
                    break;
                } catch (InvalidValueException e) {
                    System.out.println("������: " + e.getMessage());
                }
            }
        }
        while (true) {
            try {
                System.out.print("������� �������� ���������: ");
                int moshchnost = Integer.parseInt(scanner.nextLine());
                dvigatel = new Dvigatel(moshchnost);
                break;
            } catch (NumberFormatException e) {
                System.out.println("������: ������� ����� ������������� ����� ��� ��������.");
            } catch (InvalidValueException e) {
                System.out.println("������: " + e.getMessage());
            }
        }
        Avtomobil avtomobil = null;
        try {
            avtomobil = new Avtomobil(marka, kolesa, dvigatel);
        } catch (InvalidValueException e) {
            System.out.println("������ ��� �������� ����������: " + e.getMessage());
            scanner.close();
            return;
        }
        avtomobil.ehhat();
        avtomobil.zapravljat();
        while (true) {
            try {
                System.out.print("������� ������ ������ ��� ������ (0-3): ");
                int index = Integer.parseInt(scanner.nextLine());
                if (index < 0 || index >= kolesa.length) {
                    throw new WheelIndexOutOfBoundsException("������ ������ ������ ���� � ��������� �� 0 �� " + (kolesa.length - 1) + ".");
                }
                System.out.print("������� ��� ������ ������: ");
                String novoeKolesoTip = scanner.nextLine();
                avtomobil.menyatKoleso(index, new Koleso(novoeKolesoTip));
                break;
            } catch (NumberFormatException e) {
                System.out.println("������: ������� ����� ����� ��� ������� ������.");
            } catch (InvalidValueException e) {
                System.out.println("������: " + e.getMessage());
            } catch (WheelIndexOutOfBoundsException e) {
                System.out.println("������: " + e.getMessage());
            }
        }
        avtomobil.vyvestiMarku();
        scanner.close();
    }
}
