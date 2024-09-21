import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Produkt implements Serializable {

    /**Ekstensja*/
    private static List<Produkt> ekstensjaProdukt = new ArrayList<>();

    /**Atrybuty*/
    private final int produktID;
    private static int dostepneProduktID = 1;

    private String nazwa;
    private double cena;

    /**Atrybuty asocjacji*/
    private List<Magazyn> magazyny = new ArrayList<>();
    private List<MagazynProdukt> magazynProdukty = new ArrayList<>();

    private List<Paczka> paczki = new ArrayList<>();
    private List<PaczkaProdukt> paczkaProdukty = new ArrayList<>();

    /**Konstruktor*/
    public Produkt(String nazwa, double cena) {
        this.produktID = dostepneProduktID++;
        this.nazwa = nazwa;
        this.cena = cena;

        ekstensjaProdukt.add(this);
    }

    /**Ekstensja*/
    public static void zapiszEkstensjaProdukt() {
        final String extentFile = "src/Ekstensje/Produkt.txt";
        try {
            var out = new ObjectOutputStream(new FileOutputStream(extentFile));
            out.writeObject(ekstensjaProdukt);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void wczytajEkstensjaProdukt() {
        final String extentFile = "src/Ekstensje/Produkt.txt";
        File file = new File(extentFile);
        try {
            if (file.exists()) {
                var in = new ObjectInputStream(new FileInputStream(extentFile));
                ekstensjaProdukt = (ArrayList<Produkt>) in.readObject();
                in.close();

                if (!ekstensjaProdukt.isEmpty())
                    dostepneProduktID = ekstensjaProdukt.stream().max(Comparator.comparing(Produkt::getProduktID)).orElse(null).getProduktID() + 1;
            } else {
                file.getParentFile().mkdirs();
                file.createNewFile();
                zapiszEkstensjaProdukt();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**Asocjacje*/
    public void addPaczka(Paczka paczka, int iloscProduktu) {
        if (!paczki.contains(paczka)) {
            paczki.add(paczka);
            paczka.addProdukt(this, iloscProduktu);

            PaczkaProdukt paczkaProdukt = new PaczkaProdukt(paczka, this, iloscProduktu);
            paczkaProdukty.add(paczkaProdukt);
            paczka.addPaczkaProdukt(paczkaProdukt);
        }
    }

    public void addPaczkaProdukt(PaczkaProdukt paczkaProdukt) {
        if (!paczkaProdukty.contains(paczkaProdukt)) {
            paczkaProdukty.add(paczkaProdukt);
        }
    }

    public void addMagazyn(Magazyn magazyn, int iloscProduktu) {
        if (!magazyny.contains(magazyn)) {
            magazyny.add(magazyn);
            magazyn.addProdukt(this, iloscProduktu);

            MagazynProdukt magazynProdukt = new MagazynProdukt(magazyn, this, iloscProduktu);
            magazynProdukty.add(magazynProdukt);
            magazyn.addMagazynProdukt(magazynProdukt);
        }
    }

    public void addMagazynProdukt(MagazynProdukt magazynProdukt) {
        if (!magazynProdukty.contains(magazynProdukt)) {
            magazynProdukty.add(magazynProdukt);
        }
    }

    /**Metody*/
    public String getNazwa() {
        return nazwa;
    }

    public double getCena() {
        return cena;
    }

    public static List<Produkt> getEkstensjaProdukt() {
        return ekstensjaProdukt;
    }

    public int getProduktID() {
        return produktID;
    }

    @Override
    public String toString() {
        return "Produkt{" +
                "produktID=" + produktID +
                ", nazwa='" + nazwa + '\'' +
                ", cena=" + cena +
                '}';
    }
}