import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Kurier extends Pracownik implements Serializable {

    /**Ekstensja*/
    private static List<Kurier> ekstensjaKurier = new ArrayList<>();

    /**Atrybuty*/
    private final int kurierID;
    private static int dostepneKurierID = 1;

    /**Atrybuty asocjacji*/
    private List<Zamowienie> zamowienia = new ArrayList<>();
    private List<Pojazd> pojazdy = new ArrayList<>();

    /**Konstruktory*/
    public Kurier(String imie, String nazwisko, String pesel, String email, String numerTelefonu, int stawka) {
        super(imie, nazwisko, pesel, email, numerTelefonu, stawka);
        this.kurierID = dostepneKurierID++;

        ekstensjaKurier.add(this);
    }

    public Kurier(String imie, String nazwisko, String pesel, String email, int stawka) {
        super(imie, nazwisko, pesel, email, stawka);
        this.kurierID = dostepneKurierID++;

        ekstensjaKurier.add(this);
    }

    /**Ekstensja*/
    public static void zapiszEkstensjaKurier() {
        final String extentFile = "src/Ekstensje/Kuriers.txt";
        try {
            var out = new ObjectOutputStream(new FileOutputStream(extentFile));
            out.writeObject(ekstensjaKurier);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void wczytajEkstensjaKurier() {
        final String extentFile = "src/Ekstensje/Kuriers.txt";
        File file = new File(extentFile);
        try {
            if (file.exists()) {
                var in = new ObjectInputStream(new FileInputStream(extentFile));
                ekstensjaKurier = (ArrayList<Kurier>) in.readObject();
                in.close();

                if (!ekstensjaKurier.isEmpty())
                    dostepneKurierID = ekstensjaKurier.stream().max(Comparator.comparing(Kurier::getKurierID)).orElse(null).getKurierID() + 1;
            } else {
                file.getParentFile().mkdirs();
                file.createNewFile();
                zapiszEkstensjaKurier();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**Zmiana typu*/
    public Kurier(PracownikObslugiKlienta pracownikObslugiKlienta) {
        super(pracownikObslugiKlienta.getImie(), pracownikObslugiKlienta.getNazwisko(), pracownikObslugiKlienta.getPesel(), pracownikObslugiKlienta.getEmail(), pracownikObslugiKlienta.getNumerTelefonu() != null ? pracownikObslugiKlienta.getNumerTelefonu() : null, pracownikObslugiKlienta.getStawka());
        this.kurierID = dostepneKurierID++;

        PracownikObslugiKlienta.removePracownikObslugiKlienta(pracownikObslugiKlienta);
        ekstensjaKurier.add(this);
    }

    /**Metody*/
    public static void removeKurier(Kurier kurier) {
        ekstensjaKurier.remove(kurier);
    }

    public int getKurierID() {
        return kurierID;
    }

    /**Asocjacje*/
    public void addZamowienie(Zamowienie zamowienie) {
        if (!zamowienia.contains(zamowienie))
            this.zamowienia.add(zamowienie);
            zamowienie.setKurier(this);
    }

    public void addPojazd(Pojazd pojazd) {
        if (!pojazdy.contains(pojazd)) {
            this.pojazdy.add(pojazd);
            pojazd.setKurier(this);
        }
    }
}