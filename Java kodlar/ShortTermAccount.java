import java.nio.file.AtomicMoveNotSupportedException;
import java.time.LocalDate;
import java.util.Scanner;

class ShortTermAccount extends Account {
    Scanner scanner = new Scanner(System.in);

    public ShortTermAccount(int accountID, float firstbalance) {
        super(accountID, firstbalance, "ShortTerm");
    }

    public ShortTermAccount(int accountID, float balance, float firstbalance, LocalDate date) {
        super(accountID, balance, firstbalance, "ShortTerm", date);
    }

    public void deposit(float bakiye) {
        super.deposit(bakiye);
        super.transactionHistory("Bakiye: +" + bakiye);
    }

    public void showWithdraw(float bakiye, float baslangicBakiye) {
        float cesh;
        do {
            System.out.println("mevcut bakiyeniz :" + bakiye
                    + "\nHesap türünüz ShortTerm oldugundan hesabinizda minimum 1000TL bakiye olmali\nCekilebilir Para miktari: "
                    + (bakiye - 1000) + "TL");
            cesh = scanner.nextInt();
            if (cesh < 0) {
                System.out.println("girilen deger hatali deger 0'dan kücük olamaz");
            } else if (cesh > (bakiye - 1000)) {
                System.out.println("Cekilebilir para miktari: " + (bakiye - 1000) + "TL !!");
            } else {
                super.withdraw(cesh);
                super.transactionHistory("Bakiye: -" + cesh);
                break;
            }
        } while (true);
    }

    public void benefit() {
        gunlukFaiz = ((17 / 100) * bakiye) / 360;
    }
}