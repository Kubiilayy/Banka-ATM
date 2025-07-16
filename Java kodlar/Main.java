import java.io.InputStream;
import java.util.Scanner;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Bank hs = new Bank();
        menu(hs);

    }

    public static void menu(Bank hs) {
        Scanner scanner = new Scanner(System.in);
        boolean ctr = true;
        while (ctr) {
            ctr = false;
            System.out.println(
                    "DUMLUPINAR BANK'A HOSGELDİNİZ\n\n\n\t------MENU------\n1. Create_S_ID_balance: Kisa vadeli hesap açar.\n2. Create_L_ID_balance: Uzun vadeli hesap açar.\n3. Create_O_ID_balance: Özel hesap açar.\n4. Create_C_ID_balance: Cari hesap açar.\n5. Increase_ID_cash: ID'ye ait hesaba 'cesh' para ekler\n6. Decrease_ID_cash: ID'ye ait hesaba 'cesh' para çeker\n7. Set_dd_mm_yy: Sistemin su anki tarihini duzenler.\n8. ShowAccount: ID'ye ait hesabin yaptigi son 5 islemi gosterir\n9. ShowIDs: Sistemdeki tum hesap numaralarini listeler\n10. Sortition: Özel hesaplar için kura çeker ve sonucu gösterir\n11. Cikis");
            int op = scanner.nextInt();
            try {
                switch (op) {

                    case 1:
                        hs.hesapac(1);
                        break;

                    case 2:
                        hs.hesapac(2);
                        break;

                    case 3:
                        hs.hesapac(3);
                        break;

                    case 4:
                        hs.hesapac(4);
                        break;

                    case 5: {
                        hs.deposit();
                        break;
                    }
                    case 6:
                        hs.withdrawcontrol();
                        break;

                    case 7:
                        hs.systemDateCreat();
                        break;

                    case 8:
                        hs.transactionHistoryShow();
                        break;
                    case 9:
                        hs.ShowIDs();
                        break;
                    case 10:
                        hs.Sortition();
                        break;
                    case 11:
                        System.out.println("Saglikli gunler");
                        break;
                    default:
                        System.out.println("hatali tuslama yaptiniz");
                        ctr = true;
                        break;

                }

            } catch (Exception e) {
            }

        }

    }
}
