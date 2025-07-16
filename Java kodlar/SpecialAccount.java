import java.time.LocalDate;
import java.util.Scanner;

class SpecialAccount extends Account {
    Scanner scanner = new Scanner(System.in);

    public SpecialAccount(int accountID, float balance) {
        super(accountID, balance, "Special");
    }

    public SpecialAccount(int accountID, float balance, float firstbalance, LocalDate date) {
        super(accountID, balance, firstbalance, "Special", date);
    }

    public void showWithdraw(float bakiye, float baslangicBakiye) {
        float cesh;
        do {
            System.out.println("mevcut bakiyeniz :" + bakiye
                    + "\nHesap türünüz Special oldugundan hesabinizda minimum hesap acilis bakiyesi kadar bakiye("
                    + baslangicBakiye + "TL)olmalidir\nCekilebilir Para miktari: " + (bakiye - baslangicBakiye) + "TL");
            cesh = scanner.nextInt();
            if (cesh < 0) {
                System.out.println("girilen deger hatali deger 0'dan kücük olamaz");
            } else if (cesh > (bakiye - baslangicBakiye)) {
                System.out.println("Cekilebilir para miktari: " + (bakiye - baslangicBakiye) + "TL !!");
            } else {
                super.withdraw(cesh);
                super.transactionHistory("Bakiye: -" + cesh);
                break;
            }
        } while (true);
    }

    public void deposit(float bakiye) {
        super.deposit(bakiye);
    }

    public void benefit() {
        gunlukFaiz = ((12 / 100) * bakiye) / 360;
    }
}