import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Account {

    protected int id;
    protected float bakiye = 0;
    protected float baslangicBakiye = 0;
    protected float gunlukFaiz;
    protected String hesaptur;
    private LocalDate systemDefoultDate = LocalDate.of(2023, 06, 06);
    protected LocalDate systemNowDate;
    protected LocalDate account_date;
    String islem = null;

    public Account() {
        systemDefoultDate = LocalDate.now();
        account_date = systemNowDate;

    }

    public Account(int id, float baslangicBakiye, String hesaptur) {
        this.id = id;
        this.baslangicBakiye = baslangicBakiye;
        this.hesaptur = hesaptur;
        this.bakiye = baslangicBakiye;
        account_date = LocalDate.now();
    }

    public Account(int id, float bakiye, float baslangicBakiye, String hesaptur, LocalDate account_date) {
        this.id = id;
        this.baslangicBakiye = baslangicBakiye;
        this.hesaptur = hesaptur;
        this.bakiye = bakiye;
        this.account_date = account_date;
    }

    public int getId() {
        return id;
    }

    public float getBakiye() {
        return bakiye;
    }

    public float getBaslangicBakiye() {
        return baslangicBakiye;
    }

    public String getHesaptur() {
        return hesaptur;
    }

    public LocalDate getAccountDate() {
        return account_date;
    }

    public void tarih() {
        System.out.println("Sistem başlangic tarihi: " + systemDefoultDate);
    }

    public void deposit(float amounth) {
        this.bakiye += amounth;
    }

    public void transactionHistory(String islem) {
        LocalDate date = LocalDate.now();
        this.islem = islem + "   " + date.toString();
    }

    public abstract void benefit();

    public abstract void showWithdraw(float bakiye, float baslangicBakiye);

    public void withdraw(float cesh) {
        bakiye -= cesh;
        System.out.println("islem basarili");
    }

    public void systemDate(int yil, int ay, int gun) {
        this.systemNowDate = LocalDate.of(yil, ay, gun);
        System.out.println("Sistemin su anki tarihi: " + this.systemNowDate + " olarak ayarlandi");
    }

    public void systemDatenow() {
        this.systemNowDate = LocalDate.now();
        System.out.println("Sistemin su anki tarihi: " + this.systemNowDate + " olarak ayarlandi");
    }
}
