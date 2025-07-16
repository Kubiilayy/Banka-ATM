import java.time.LocalDate;
import java.util.Scanner;

class CurrentAccount extends Account {
    Scanner scanner = new Scanner(System.in);

    public CurrentAccount(int accountID, float balance) {
        super(accountID, balance, "Current");
    }

    public CurrentAccount(int accountID, float balance, float firstbalance, LocalDate date) {
        super(accountID, balance, firstbalance, "Current", date);
    }

    public void showWithdraw(float bakiye, float baslangicBakiye) {
        float cesh;
        do {
            System.out.println("mevcut bakiyeniz :" + bakiye + "\nCekilebilir Para miktari: " + bakiye + "TL");
            cesh = scanner.nextInt();
            if (cesh < 0) {
                System.out.println("girilen deger hatali deger 0'dan kücük olamaz");
            } else if (cesh > bakiye) {
                System.out.println("Cekilebilir para miktari: " + bakiye + "TL !!");
            } else {
                super.withdraw(cesh);
                super.transactionHistory("Bakiye: -" + cesh);
                break;
            }
        } while (true);
    }

    public void benefit() {
        gunlukFaiz = 0;
    }

    public void deposit(float bakiye) {
        super.deposit(bakiye);
        super.transactionHistory("Bakiye: +" + bakiye);
    }

}