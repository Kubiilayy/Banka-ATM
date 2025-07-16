import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.util.Scanner;
import java.io.InputStream;
import javax.lang.model.util.ElementScanner14;
import javax.security.auth.login.AccountException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.time.format.DateTimeFormatter;

public class Bank extends Account {
    Scanner scanner = new Scanner(System.in);

    public void showWithdraw(float bakiye, float baslangicBakiye) {
    };

    public void benefit() {
    };

    Bank() {
    }

    public void hesapac(int op) throws IOException {

        int id;
        float balance;
        ArrayList<String> islemler;

        if (op == 1) {
            id = hesapidsorgulama(1);
            System.out.println(
                    "ShortTerm turu hesap yillik %17 faiz verir ve en az 1000 TL hesapta bakiye olmasi gerekiyor.");
            do {
                System.out.println("hesap baslangic bakiyesi giriniz(min 1000TL)");
                balance = scanner.nextFloat();
            } while (balance < 1000);
            Account account = new ShortTermAccount(id, balance);
            hesapyazdir(account);
            System.out.println("kullanici kayit edildi");

        } else if (op == 2) {
            id = hesapidsorgulama(1);
            System.out.println(
                    "LongTerm turu hesap yillik %24 faiz verir ve en az 1500 TL hesapta bakiye olmasi gerekiyor.");
            do {
                System.out.println("hesap baslangic bakiyesi giriniz(min 1500TL)");
                balance = scanner.nextFloat();

            } while (balance < 1500);
            Account account = new LongTermAccount(id, balance);
            hesapyazdir(account);
            System.out.println("kullanici kayit edildi");
        } else if (op == 3) {
            id = hesapidsorgulama(1);
            System.out.println(
                    "Special turu hesap yillik %12 faiz verir ve en az hesap açtigindaki kadar para hesaptabakiye olmasi gerekiyor.");
            do {
                System.out.println("hesap baslangic bakiyesi giriniz(min 1TL)");
                balance = scanner.nextFloat();

            } while (balance < 1);
            Account account = new SpecialAccount(id, balance);
            hesapyazdir(account);
            System.out.println("kullanici kayit edildi");
        } else if (op == 4) {
            id = hesapidsorgulama(1);
            System.out.println("Current turu hesap faizsizdir ve hesapta para olma zorunluluğu yoktur.");
            do {
                System.out.println("hesap baslangic bakiyesi giriniz(min 0TL)");
                balance = scanner.nextFloat();
                System.out.println("kullanici kayit edildi");

            } while (balance < 0);
            Account account = new CurrentAccount(id, balance);
            hesapyazdir(account);

        }

    }

    public void hesapyazdir(Account account) throws IOException {
        String hspid = "user.txt";
        String tempdate;

        FileWriter file = new FileWriter(hspid, true);
        BufferedWriter bWriter = new BufferedWriter(file);
        bWriter.write(String.valueOf("hesap id: " + account.getId() + " " + account.getBakiye() + " "
                + account.getBaslangicBakiye() + " " + account.getHesaptur() + " " + account.getAccountDate()) + "\n");
        bWriter.close();
        hesapidcreater(account.id);

    }

    public void hesapidcreater(int id) throws IOException {
        String strid = Integer.toString(id);
        strid = strid + ".txt";
        File file = new File(strid);
        file.createNewFile();
    }

    public int hesapidsorgulama(int op) throws IOException {

        int id;
        String strid;
        do {
            System.out.println("id giriniz");
            id = scanner.nextInt();
            strid = Integer.toString(id);
            strid = strid + ".txt";
            File file = new File(strid);
            if (file.exists()) {
                if (op == 1) {
                    System.out.println("kullanici kayitli!\nlutfen farkli bir id giriniz!");
                } else if (op == 2) {
                    break;
                }
            } else {
                if (op == 1) {
                    break;
                } else if (op == 2) {
                    System.out.println("kullanici kayitli degil lutfen dogru bir id giriniz");
                }
            }
        } while (true);
        System.out.println(id);
        return id;
    }

    public int deposit() throws IOException {
        Account account = null;
        int id = hesapidsorgulama(2);
        float bakiye;
        String[] kelimeler = hesapbilgi(id);

        do {
            System.out.println("Lutfen eklenecek para miktarini giriniz");
            bakiye = scanner.nextFloat();
            if (bakiye < 0) {
                System.out.println("eklenecek para miktari 0'dan küçük olamaz.");
            } else
                break;

        } while (true);

        if (kelimeler[5].equals("ShortTerm")) {
            account = new ShortTermAccount(Integer.parseInt(kelimeler[2]), Float.parseFloat(kelimeler[3]),
                    Float.parseFloat(kelimeler[4]), LocalDate.parse(kelimeler[6], DateTimeFormatter.ISO_DATE));
        } else if (kelimeler[5].equals("LongTerm")) {
            account = new LongTermAccount(Integer.parseInt(kelimeler[2]), Float.parseFloat(kelimeler[3]),
                    Float.parseFloat(kelimeler[4]), LocalDate.parse(kelimeler[6], DateTimeFormatter.ISO_DATE));

        } else if (kelimeler[5].equals("Special")) {
            account = new SpecialAccount(Integer.parseInt(kelimeler[2]), Float.parseFloat(kelimeler[3]),
                    Float.parseFloat(kelimeler[4]), LocalDate.parse(kelimeler[6], DateTimeFormatter.ISO_DATE));
        } else {
            account = new CurrentAccount(Integer.parseInt(kelimeler[2]), Float.parseFloat(kelimeler[3]),
                    Float.parseFloat(kelimeler[4]), LocalDate.parse(kelimeler[6], DateTimeFormatter.ISO_DATE));
        }

        account.deposit(bakiye);
        super.transactionHistory("Bakiye: +" + bakiye);
        bilgiguncelle(id);
        hesapyazdir(account);
        transactionHistoryprint(account.id, account.islem);
        System.out.println("islem basarili");
        return id;
    }

    public String[] hesapbilgi(int id) throws IOException {

        FileReader fReader = new FileReader("user.txt");
        String line, user = "hesap id: " + id;
        BufferedReader breader = new BufferedReader(fReader);
        String[] kelimeler = null;
        while ((line = breader.readLine()) != null) {
            if (line.contains(user)) {
                if (line != null) {
                    kelimeler = line.split(" ");
                }
            }
        }
        fReader.close();
        return kelimeler;
    }

    public void withdrawcontrol() throws IOException {
        int id = hesapidsorgulama(2);
        String[] kelimeler = hesapbilgi(id);
        Account account = null;
        if (kelimeler[5].equals("ShortTerm")) {
            account = new ShortTermAccount(Integer.parseInt(kelimeler[2]), Float.parseFloat(kelimeler[3]),
                    Float.parseFloat(kelimeler[4]), LocalDate.parse(kelimeler[6], DateTimeFormatter.ISO_DATE));
        } else if (kelimeler[5].equals("LongTerm")) {
            account = new LongTermAccount(Integer.parseInt(kelimeler[2]), Float.parseFloat(kelimeler[3]),
                    Float.parseFloat(kelimeler[4]), LocalDate.parse(kelimeler[6], DateTimeFormatter.ISO_DATE));

        } else if (kelimeler[5].equals("Special")) {
            account = new SpecialAccount(Integer.parseInt(kelimeler[2]), Float.parseFloat(kelimeler[3]),
                    Float.parseFloat(kelimeler[4]), LocalDate.parse(kelimeler[6], DateTimeFormatter.ISO_DATE));
        } else {
            account = new CurrentAccount(Integer.parseInt(kelimeler[2]), Float.parseFloat(kelimeler[3]),
                    Float.parseFloat(kelimeler[4]), LocalDate.parse(kelimeler[6], DateTimeFormatter.ISO_DATE));
        }
        account.showWithdraw(account.bakiye, account.baslangicBakiye);
        bilgiguncelle(id);
        hesapyazdir(account);
        transactionHistoryprint(account.id, account.islem);
    }

    public void bilgiguncelle(int id) throws IOException {

        File gecici = new File("gecici.txt");
        File user = new File("user.txt");
        BufferedReader okuyucu = new BufferedReader(new FileReader(user));
        BufferedWriter yazici = new BufferedWriter(new FileWriter(gecici));
        String line;
        String user1 = "hesap id: " + id + " ";
        while ((line = okuyucu.readLine()) != null) {
            if (line.contains(user1)) {
                continue;
            }
            yazici.write(line + "\n");
        }
        okuyucu.close();
        yazici.close();
        user.delete();
        gecici.renameTo(user);
    }

    public void ShowIDs() throws IOException {
        FileReader fReader = new FileReader("user.txt");
        BufferedReader bReader = new BufferedReader(fReader);
        String line;
        String[] kelimeler = null;
        System.out.println("\t\t\t----Tum id'ler-----");
        while ((line = bReader.readLine()) != null) {
            if (line != null) {
                kelimeler = line.split(" ");
                System.out.print(kelimeler[1] + kelimeler[2] + " ");
            }
        }
        fReader.close();
        bReader.close();
    }

    public void transactionHistoryprint(int id, String str) throws IOException {
        String hspid = id + ".txt";
        FileWriter file = new FileWriter(hspid, true);
        BufferedWriter bWriter = new BufferedWriter(file);
        bWriter.write(String.valueOf(str + "\n"));
        bWriter.close();
    }

    public void transactionHistoryShow() throws IOException {
        List<String> lines = new ArrayList<>();
        int id = hesapidsorgulama(2);
        String user = id + ".txt", line;
        FileReader file = new FileReader(user);
        BufferedReader bReader = new BufferedReader(file);
        while ((line = bReader.readLine()) != null) {
            lines.add(line);
        }
        int baslangicIndex = Math.max(0, lines.size() - 5);
        for (int i = baslangicIndex; i < lines.size(); i++) {
            System.out.println(lines.get(i));
        }
        file.close();
        bReader.close();
    }

    public void systemDateCreat() {
        System.out.println("Sistemin suanki tarihini bugunun tarihi icin 1'i özel bir zaman icin 2'yi tuslayin");
        int op = scanner.nextInt();
        if (op == 1) {
            super.systemDatenow();

        } else if (op == 2) {
            System.out.println("yil giriniz");
            int yil = scanner.nextInt();
            System.out.println("ay giriniz");
            int ay = scanner.nextInt();
            System.out.println("gun giriniz");
            int gun = scanner.nextInt();
            super.systemDate(yil, ay, gun);
        }

        else {
            System.out.println("Hatali tuslama yaptiniz");
        }
    }

    public List<int[]> AllSpecialAccounts() throws IOException {
        FileReader fReader = new FileReader("user.txt");
        BufferedReader bReader = new BufferedReader(fReader);
        String line;
        List<int[]> specialAccounts = new ArrayList<>();
        while ((line = bReader.readLine()) != null) {
            String[] kelimeler = line.split(" ");
            if (kelimeler[5].equals("Special")) {
                float floatValue = Float.parseFloat(kelimeler[3]);
                int[] account = new int[2];
                account[0] = Integer.parseInt(kelimeler[2]);
                account[1] = (int) floatValue;
                specialAccounts.add(account);
            }

        }

        fReader.close();
        bReader.close();
        return specialAccounts;
    }

    public void Sortition() throws IOException {
        List<int[]> accounts = AllSpecialAccounts();

        double totalPoints = 0;
        for (int[] account : accounts) {
            totalPoints += account[1] / 2000.0;
        }

        Random random = new Random();
        double randomValue = random.nextDouble();
        double cumulativeProbability = 0;
        int[] winningAccount = null;

        for (int[] account : accounts) {
            double probability = account[1] / 2000.0 / totalPoints;
            cumulativeProbability += probability;

            if (randomValue <= cumulativeProbability) {
                winningAccount = account;
                break;
            }
        }

        if (winningAccount != null) {
            System.out.println("Çekiliş Sonucu:");
            System.out.println("Ödül Kazanan Hesap: " + winningAccount[0]);
            System.out.println("Ödül Miktarı: 1000 TL");

            String userFile = winningAccount[0] + ".txt";
            FileWriter fileWriter = new FileWriter(userFile, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            LocalDate date = LocalDate.now();
            bufferedWriter.write("Çekiliş Sonucu: +1000 TL " + date);
            bufferedWriter.newLine();
            bufferedWriter.close();
            fileWriter.close();
        } else {
            System.out.println("Çekiliş Sonucu:");
            System.out.println("Ödül kazanan hesap bulunamadı.");
        }
        String[] kelimeler = hesapbilgi(winningAccount[0]);
        Account account = new SpecialAccount(Integer.parseInt(kelimeler[2]), Float.parseFloat(kelimeler[3]),
                Float.parseFloat(kelimeler[4]), LocalDate.parse(kelimeler[6], DateTimeFormatter.ISO_DATE));
        account.deposit(1000);
        bilgiguncelle(account.id);
        hesapyazdir(account);
    }
}
