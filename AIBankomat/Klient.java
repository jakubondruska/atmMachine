package AIBankomat;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Klient {

    private final String meno;
    private int pin;

    public Klient(String meno, int pin) {
        this.meno = meno;
        this.pin = pin;
        this.pin = nacitajPinZoSuboru();

    }


    public String getMeno() {
        return meno;
    }

    public int getPin() {
        return pin;
    }

    public void zmenPIN() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Zadajte prosim Vas novy pin.");
        int novyPin1 = scanner.nextInt();
        System.out.println("Zadajte prosim PIN este raz. ");
        int novyPin2 = scanner.nextInt();

        if (novyPin1 == novyPin2) {
            this.pin = novyPin1;
            System.out.println("PIN bol uspesne zmeneny ! ");
            ulozPinDoSuboru(this.pin);
            Bankomat.navratMenu();

        } else {
            System.out.println("Zadane PIN sa nezhoduju !");
        }
    }

    public boolean zadajHeslo() {

        int pocetPokusov = 3;
        Scanner scanner = new Scanner(System.in);

        while (pocetPokusov > 0) {
            System.out.println("Zadajte prosim Vas PIN: ");
            int zadanyPIN = scanner.nextInt();

            if (zadanyPIN == this.pin) {
                System.out.println("Zadany PIN je spravny, vitajte v menu. ");
                Bankomat.menuMoznosti();
                return true;

            } else {
                pocetPokusov--;
                System.out.println("PIN nieje spravny, zostava Vam " + pocetPokusov + " pokusov!");
            }
        }
        System.out.println("Vasa karta bola zablokovana !");
        System.exit(0);

        return false;
    }

    private void ulozPinDoSuboru(int novyPin) {
        try (FileWriter writer = new FileWriter("pin.txt")) {
            writer.write(Integer.toString(novyPin));
        } catch (IOException e) {
            System.out.println("Chyba pri ukladaní PINu do súboru.");
            e.printStackTrace();
        }
    }

    private int nacitajPinZoSuboru() {
        try (BufferedReader reader = new BufferedReader(new FileReader("pin.txt"))) {
            String line = reader.readLine();
            return Integer.parseInt(line);
        } catch (IOException | NumberFormatException e) {
            System.out.println("Chyba pri načítaní PINu zo súboru. Použije sa predvolený PIN.");
            return 1111; // Predvolený PIN
        }
    }
}

