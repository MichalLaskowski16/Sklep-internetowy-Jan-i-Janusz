import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            wczytajWszystkieEkstensje();

//            new Klient("Jan", "Kowalski", "900201678901", "jan@example.com", "123456789", "jan@example.com", "haslo123", "ul. Przykładowa 1, 00-000 Miasto");
//
//            new Klient("Anna", "Nowak", "02121254321", "anna@example.com", "987654321","anna@example.com", "haslo456", "ul. Przykładowa 2, 00-000 Miasto");
//
//            new Produkt("Toster", 75.00);
//            new Produkt("Odkurzacz", 150.00);
//            new Produkt("Mikrofalowka", 200.00);
//            new Produkt("Lodówka", 1200.00);
//            new Produkt("Pralka", 1000.00);
//            new Produkt("Suszarka", 500.00);
//            new Produkt("Żelazko", 150.00);
//            new Produkt("Telewizor", 3000.00);
//            new Produkt("Laptop", 4000.00);
//            new Produkt("Tablet", 1500.00);
//            new Produkt("Smartfon", 2500.00);
//            new Produkt("Kamera", 800.00);
//            new Produkt("Głośnik", 300.00);
//            new Produkt("Zmywarka", 1200.00);
//            new Produkt("Mikser", 200.00);
//            new Produkt("Oczyszczacz powietrza", 600.00);
//            new Produkt("Robot kuchenny", 700.00);
//            new Produkt("Klimatyzator", 2000.00);
//            new Produkt("Odkurzacz automatyczny", 1500.00);

            new GlowneOkno();

            Runtime.getRuntime().addShutdownHook(new Thread(Main::zapiszWszystkieEkstensje));
        });
    }

    private static void zapiszWszystkieEkstensje() {
        Klient.zapiszEkstensjaKlient();
        Kurier.zapiszEkstensjaKurier();
        Ciezarowka.zapiszEkstensjaCiezarowka();
        Magazyn.zapiszEkstensjaMagazyn();
        MagazynProdukt.zapiszEkstensjaMagazynProdukt();
        Zamowienie.zapiszEkstensjaPaczka();
        PaczkaProdukt.zapiszEkstensjaPaczkaProdukt();
        PracownikObslugiKlienta.zapiszEkstensjaPracownikObslugiKlienta();
        Produkt.zapiszEkstensjaProdukt();
        RecenzjaZamowienia.zapiszEkstensjaRecenzjaZamowienia();
        Skuter.zapiszEkstensjaSkuter();
        Zamowienie.zapiszEkstensjaZamowienie();
    }

    private static void wczytajWszystkieEkstensje() {
        Klient.wczytajEkstensjaKlient();
        Kurier.wczytajEkstensjaKurier();
        Ciezarowka.wczytajEkstensjaCiezarowka();
        Magazyn.wczytajEkstensjaMagazyn();
        MagazynProdukt.wczytajEkstensjaMagazynProdukt();
        Zamowienie.wczytajEkstensjaPaczka();
        PaczkaProdukt.wczytajEkstensjaPaczkaProdukt();
        PracownikObslugiKlienta.wczytajEkstensjaPracownikObslugiKlienta();
        Produkt.wczytajEkstensjaProdukt();
        RecenzjaZamowienia.wczytajEkstensjaRecenzjaZamowienia();
        Skuter.wczytajEkstensjaSkuter();
        Zamowienie.wczytajEkstensjaZamowienie();
    }
}