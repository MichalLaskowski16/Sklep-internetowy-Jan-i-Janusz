import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class Zamowienie implements Serializable {

    /**Ekstensja*/
    private static List<Zamowienie> ekstensjaZamowienie = new ArrayList<>();

    /**Kolekcja*/
    enum StatusZamowienia {
        ZLOZONE,
        W_TRAKCIE_REALIZACJI,
        DORECZONE,
        ANULOWANE
    }

    /**Atrybuty*/
    private final int zamowienieID;
    private static int dostepneZamowienieID = 1;

    private String adres;
    private LocalDate dataZamowienia;
    private LocalDate dataDoreczenia = null;
    private StatusZamowienia status;

    /**Atrybuty asocjacji*/
    private Klient klient;
    private Kurier kurier;

    private RecenzjaZamowienia recenzjaZamowienia;

    private Map<Integer, Paczka> paczki = new HashMap<>();
    private static Set<Paczka> ekstensjaPaczka = new HashSet<>();

    /**Konstruktor*/
    public Zamowienie(String adres, Date dataZamowienia, Klient klient) {
        this.adres = adres;
        this.zamowienieID = dostepneZamowienieID++;
        this.dataZamowienia = LocalDate.now();
        status = StatusZamowienia.ZLOZONE;

        this.klient = klient;
        this.klient.addZamowienie(this);

        ekstensjaZamowienie.add(this);
    }

    /**Ekstensja*/
    public static void zapiszEkstensjaZamowienie() {
        final String extentFile = "src/Ekstensje/Zamowienie.txt";
        try {
            var out = new ObjectOutputStream(new FileOutputStream(extentFile));
            out.writeObject(ekstensjaZamowienie);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void wczytajEkstensjaZamowienie() {
        final String extentFile = "src/Ekstensje/Zamowienie.txt";
        File file = new File(extentFile);
        try {
            if (file.exists()) {
                var in = new ObjectInputStream(new FileInputStream(extentFile));
                ekstensjaZamowienie = (ArrayList<Zamowienie>) in.readObject();
                in.close();

                if (!ekstensjaZamowienie.isEmpty())
                    dostepneZamowienieID = ekstensjaZamowienie.stream().max(Comparator.comparing(Zamowienie::getZamowienieID)).orElse(null).getZamowienieID() + 1;
            } else {
                file.getParentFile().mkdirs();
                file.createNewFile();
                zapiszEkstensjaZamowienie();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void zapiszEkstensjaPaczka() {
        final String extentFile = "src/Ekstensje/Paczka.txt";
        try {
            var out = new ObjectOutputStream(new FileOutputStream(extentFile));
            out.writeObject(ekstensjaPaczka);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void wczytajEkstensjaPaczka() {
        final String extentFile = "src/Ekstensje/Paczka.txt";
        File file = new File(extentFile);
        try {
            if (file.exists()) {
                var in = new ObjectInputStream(new FileInputStream(extentFile));
                ekstensjaPaczka = (Set<Paczka>) in.readObject();
                in.close();

                if (!ekstensjaPaczka.isEmpty())
                    Paczka.setDostepnePaczkaID(ekstensjaPaczka.stream().max(Comparator.comparing(Paczka::getPaczkaID)).orElse(null).getPaczkaID() + 1);
            } else {
                file.getParentFile().mkdirs();
                file.createNewFile();
                zapiszEkstensjaPaczka();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**Asocjacje*/
    public void setKurier(Kurier kurier) {
        this.kurier = kurier;
        kurier.addZamowienie(this);
    }

    public void setRecenzjaZamowienia(RecenzjaZamowienia recenzjaZamowienia) {
        this.recenzjaZamowienia = recenzjaZamowienia;
    }

    public void addPaczka(Paczka paczka) throws Exception {
        if (!paczki.containsValue(paczka))
            if (ekstensjaPaczka.contains(paczka)) {
                throw new Exception("Package is already in the shipment");
            }
        paczki.put(paczki.size()+1, paczka);
        ekstensjaPaczka.add(paczka);
    }

    /**Metody*/
    public int getZamowienieID() {
        return zamowienieID;
    }

    public static Set<Paczka> getEkstensjaPaczka() {
        return ekstensjaPaczka;
    }

    public void changeStatus(StatusZamowienia status) {
        this.status = status;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public void setDataDoreczenia(LocalDate dataDoreczenia) {
        this.dataDoreczenia = dataDoreczenia;
    }

    public static List<Zamowienie> getEkstensjaZamowienie() {
        return ekstensjaZamowienie;
    }

    public Map<Integer, Paczka> getPaczki() {
        return paczki;
    }

    @Override
    public String toString() {
        return "Zamowienie nr. " + zamowienieID +
                " zlozone dnia " + dataZamowienia +
                "\nStatus: " + status;
    }
}