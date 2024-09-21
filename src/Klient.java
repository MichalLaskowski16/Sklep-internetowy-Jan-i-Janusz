import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Klient extends Osoba implements Serializable {

    /**Ekstensja*/
    private static List<Klient> ekstensjaKlient = new ArrayList<>();

    /**Atrybuty*/
    private final int klientID;
    private static int dostepneKlientID = 1;

    private String login;
    private String haslo;

    private List<String> adresy = new ArrayList<>();

    /**Atrybuty asocjacji*/
    private List<Zamowienie> zamowienia = new ArrayList<>();

    /**Konstruktory*/
    public Klient(String imie, String nazwisko, String pesel, String email, String numerTelefonu, String login, String haslo, String adres) {
        super(imie, nazwisko, pesel, email, numerTelefonu);
        this.login = login;
        this.haslo = haslo;
        this.adresy.add(adres);
        this.klientID = dostepneKlientID++;

        ekstensjaKlient.add(this);
    }

    public Klient(String imie, String nazwisko, String pesel, String email, String login, String haslo, String adres) {
        super(imie, nazwisko, pesel, email);
        this.login = login;
        this.haslo = haslo;
        this.adresy.add(adres);
        this.klientID = dostepneKlientID++;

        ekstensjaKlient.add(this);
    }

    /**Ekstensja*/
    public static void zapiszEkstensjaKlient() {
        final String extentFile = "src/Ekstensje/Clients.txt";
        try {
            var out = new ObjectOutputStream(new FileOutputStream(extentFile));
            out.writeObject(ekstensjaKlient);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void wczytajEkstensjaKlient() {
        final String extentFile = "src/Ekstensje/Clients.txt";
        File file = new File(extentFile);
        try {
            if (file.exists()) {
                var in = new ObjectInputStream(new FileInputStream(extentFile));
                ekstensjaKlient = (ArrayList<Klient>) in.readObject();
                in.close();

                if (!ekstensjaKlient.isEmpty())
                    dostepneKlientID = ekstensjaKlient.stream().max(Comparator.comparing(Klient::getKlientID)).orElse(null).getKlientID() + 1;
            } else {
                file.getParentFile().mkdirs();
                file.createNewFile();
                zapiszEkstensjaKlient();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**Asocjacje*/
    public void addZamowienie(Zamowienie zamowienie) {
        if (!zamowienia.contains(zamowienie))
            this.zamowienia.add(zamowienie);
    }

    /**Metody*/
    public void addAdres(String adres) {
        this.adresy.add(adres);
    }

    public int getKlientID() {
        return klientID;
    }

    public void removeAddress(String adres) {
        this.adresy.remove(adres);
    }

    public static List<Klient> getEkstensjaKlient() {
        return ekstensjaKlient;
    }

    public static Klient getNajstarszyKlient() {
        return ekstensjaKlient.stream().min(Comparator.comparing(Klient::getDataUrodzenia)).orElse(null);
    }

    public String getLogin() {
        return login;
    }

    public String getHaslo() {
        return haslo;
    }


    public List<String> getAdresy() {
        return adresy;
    }

    public List<Zamowienie> getZamowienia() {
        return zamowienia;
    }

    @Override
    public String toString() {
        return "Client{" +
                "client_ID=" + klientID + ", " +
                super.toString() +
                ", home_address='" + adresy + '\'' +
                '}';
    }
}