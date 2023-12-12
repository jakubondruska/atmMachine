package AIBankomat;

import java.io.*;
import java.util.Scanner;


public class Bankomat {

    private double zostatok;

    public Bankomat(double zostatok) {
        this.zostatok = zostatok;
        this.zostatok = nacitajSumuZoSuboru();

    }

    public double getZostatok() {
        return zostatok;
    }

    public void ulozSumuDoSuboru(double novaSuma) {
        try (FileWriter fileWriter = new FileWriter("Penazenka.txt")) {
            fileWriter.write(Double.toString(novaSuma));
        } catch (IOException e) {
            System.out.println("Chyba pri ukladaní uctoveho zostatku do súboru.");
            e.printStackTrace();
        }
    }
    public double nacitajSumuZoSuboru(){
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("Penazenka.txt"))) {
            String line1 = bufferedReader.readLine();
            return Double.parseDouble(line1);
        } catch (IOException ex) {
            System.out.println("Chyba pri načítaní uctoveho zostatku zo súboru. Použije sa predvolený zostatok.");
            return zostatok; // Predvolený PIN

        }
    }

    public static void menuMoznosti() {
        Bankomat bankomat = new Bankomat(2000);
        Klient klient = new Klient("Jakub Ondruska", 1111);


        System.out.println("1. Pozriet zostatok na ucte: ");
        System.out.println("2. Vyber hotovosti: ");
        System.out.println("3. Vklad hotovosti: ");
        System.out.println("4. Zmenit PIN: ");
        System.out.println("5. Ukoncit: ");

        Scanner scanner = new Scanner(System.in);
        int volba = scanner.nextInt();

        switch (volba) {
            case 1:
                System.out.println("Zostatok na účte: " + bankomat.getZostatok() + " €.");
                navratMenu();
                break;
            case 2:
                bankomat.vyberHotovosti();
                navratMenu();
                break;
            case 3:
                bankomat.vkladHotovosti();
                navratMenu();
                break;
            case 4:
                klient.zmenPIN();
                break;
            case 5:
                System.out.println("Ďakujeme, že ste používali náš bankomat. Dovidenia!");
                System.exit(0);
                break;
            default:
                System.out.println("Neplatná voľba.");
        }
    }

    public static void navratMenu() {

        while (true) {

            Scanner scanner = new Scanner(System.in);
            System.out.print("Chcete sa vrátiť do hlavnej ponuky? (y/n): ");
            char odpoved = scanner.next().charAt(0);

            if (odpoved == 'y' || odpoved == 'Y') {
                menuMoznosti();
            } else if (odpoved == 'n' || odpoved == 'N') {
                System.out.println("Ďakujeme za použitie aplikácie. Koniec programu.");
                System.exit(0);
            } else {
                System.out.println("Nespravna moznost, skuste to znova !");
            }
        }
    }

    public void vyberHotovosti() {

        int sumaVyber;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Zadajte sumu na výber: ");
        sumaVyber = scanner.nextInt();

        if (sumaVyber > 5000) {
            System.out.println("Ciastku nad 5000 treba ist vybrat do banky");
            navratMenu();
        } else if (sumaVyber > zostatok) {
                System.out.println("Nedostatok prostriedkov na ucte !");
                navratMenu();
        } else if (sumaVyber >= 10) {
                zostatok -= sumaVyber;
                System.out.println("Vybrali ste " + sumaVyber + "€. Zostatok na ucte je " + zostatok + " €");
                ulozSumuDoSuboru(this.zostatok);
                navratMenu();
        } else {
                System.out.println("Nespravna operacia, prosim skuste to znova !");
            }

    }

    public void vkladHotovosti() {

        int sumaVklad;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Zadajte sumu na vklad: ");
        sumaVklad = scanner.nextInt();

        if (sumaVklad > 5000) {
                System.out.println("Ciastku nad 5000 treba ist vlozit do banky");
                navratMenu();
            } else if (sumaVklad >= 10) {
                zostatok += sumaVklad;
                System.out.println("Vlozili ste " + sumaVklad + "€. Zostatok na ucte je " + zostatok + " €");
                ulozSumuDoSuboru(this.zostatok);
                navratMenu();
            } else {
                System.out.println("Nespravna operacia, prosim skuste to znova !");
            }
    }

    public static void hlavneMenu() {

        Klient klient = new Klient("Jakub", 1111);
        System.out.println("Vitajte v Narodnej Banke Slovenskej Republiky. \n");

        klient.zadajHeslo();


        }


        }



