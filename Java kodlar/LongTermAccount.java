import java.time.LocalDate;
import java.util.Scanner;

class LongTermAccount extends Account {
    Scanner scanner = new Scanner(System.in);

    public LongTermAccount(int accountID, float balance) {
        super(accountID, balance, "LongTerm");
    }

    public LongTermAccount(int accountID, float balance, float firstbalance, LocalDate date) {
        super(accountID, balance, firstbalance, "LongTerm", date);
    }

    public void deposit(float bakiye) {
        super.deposit(bakiye);
        super.transactionHistory("Bakiye: +" + bakiye);
    }

    public void showWithdraw(float bakiye, float baslangicBakiye) {
        float cesh;
        do {
            System.out.println("mevcut bakiyeniz :" + bakiye
                    + "\nHesap türünüz LongTerm oldugundan hesabinizda minimum 1500TL bakiye olmali\nCekilebilir Para miktari: "
                    + (bakiye - 1500) + "TL");
            cesh = scanner.nextInt();
            if (cesh < 0) {
                System.out.println("girilen deger hatali deger 0'dan kücük olamaz");
            } else if (cesh > (bakiye - 1500)) {
                System.out.println("Cekilebilir para miktari: " + (bakiye - 1500) + "TL !!");
            } else {
                super.withdraw(cesh);
                super.transactionHistory("Bakiye: -" + cesh);
                break;
            }
        } while (true);
    }

    public void benefit() {
        gunlukFaiz = ((24 / 100) * bakiye) / 360;
    }

}