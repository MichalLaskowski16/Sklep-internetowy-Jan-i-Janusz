import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RecenzjaZamowienia implements Serializable {

    /**Ekstensja*/
    private static List<RecenzjaZamowienia> ekstensjaRecenzjaZamowienia = new ArrayList<>();

    /**Atrybuty*/
    private final int recenzjaZamowieniaID;
    private static int dostepneRecenzjaZamowieniaID = 1;

    private int ocena;
    private String notatka;
    private Zamowienie zamowienie;

    /**Konstruktory*/
    public RecenzjaZamowienia(int ocena, String notatka, Zamowienie zamowienie) {
        this.recenzjaZamowieniaID = dostepneRecenzjaZamowieniaID++;
        this.ocena = ocena;
        this.notatka = notatka;

        this.zamowienie = zamowienie;
        this.zamowienie.setRecenzjaZamowienia(this);

        ekstensjaRecenzjaZamowienia.add(this);
    }

    public RecenzjaZamowienia(int ocena, Zamowienie zamowienie) {
        this.recenzjaZamowieniaID = dostepneRecenzjaZamowieniaID++;
        this.ocena = ocena;
        this.notatka = null;

        this.zamowienie = zamowienie;
        this.zamowienie.setRecenzjaZamowienia(this);

        ekstensjaRecenzjaZamowienia.add(this);
    }

    /**Ekstensja*/
    public static void zapiszEkstensjaRecenzjaZamowienia() {
        final String extentFile = "src/Ekstensje/RecenzjaZamowienia.txt";
        try {
            var out = new ObjectOutputStream(new FileOutputStream(extentFile));
            out.writeObject(ekstensjaRecenzjaZamowienia);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void wczytajEkstensjaRecenzjaZamowienia() {
        final String extentFile = "src/Ekstensje/RecenzjaZamowienia.txt";
        File file = new File(extentFile);
        try {
            if (file.exists()) {
                var in = new ObjectInputStream(new FileInputStream(extentFile));
                ekstensjaRecenzjaZamowienia = (ArrayList<RecenzjaZamowienia>) in.readObject();
                in.close();

                if (!ekstensjaRecenzjaZamowienia.isEmpty())
                    dostepneRecenzjaZamowieniaID = ekstensjaRecenzjaZamowienia.stream().max(Comparator.comparing(RecenzjaZamowienia::getRecenzjaZamowieniaID)).orElse(null).getRecenzjaZamowieniaID() + 1;
            } else {
                file.getParentFile().mkdirs();
                file.createNewFile();
                zapiszEkstensjaRecenzjaZamowienia();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**Metody*/
    public int getRecenzjaZamowieniaID() {
        return recenzjaZamowieniaID;
    }

    @Override
    public String toString() {
        return "Review of shipment: " + zamowienie +
                "\nRating = " + ocena +
                ", note = '" + notatka + '\'';
    }
}