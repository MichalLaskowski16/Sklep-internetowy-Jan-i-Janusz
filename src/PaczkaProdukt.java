import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PaczkaProdukt implements Serializable {

    /**Ekstensja*/
    private static List<PaczkaProdukt> ekstensjaPaczkaProdukt = new ArrayList<>();

    /**Atrybuty*/
    private final int paczkaProduktID;
    private static int dostepnePaczkaProduktID = 1;

    /**Atrybuty asocjacji*/
    private Paczka paczka;
    private Produkt produkt;
    private int iloscProduktu;

    /**Konstruktor*/
    public PaczkaProdukt(Paczka paczka, Produkt produkt, int iloscProduktu) {
        this.paczkaProduktID = dostepnePaczkaProduktID++;

        this.paczka = paczka;
        this.produkt = produkt;
        this.iloscProduktu = iloscProduktu;


        produkt.addPaczkaProdukt(this);
        paczka.addPaczkaProdukt(this);
        ekstensjaPaczkaProdukt.add(this);
    }

    /**Ekstensja*/
    public static void zapiszEkstensjaPaczkaProdukt() {
        final String extentFile = "src/Ekstensje/PaczkaProdukt.txt";
        try {
            var out = new ObjectOutputStream(new FileOutputStream(extentFile));
            out.writeObject(ekstensjaPaczkaProdukt);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void wczytajEkstensjaPaczkaProdukt() {
        final String extentFile = "src/Ekstensje/PaczkaProdukt.txt";
        File file = new File(extentFile);
        try {
            if (file.exists()) {
                var in = new ObjectInputStream(new FileInputStream(extentFile));
                ekstensjaPaczkaProdukt = (ArrayList<PaczkaProdukt>) in.readObject();
                in.close();

                if (!ekstensjaPaczkaProdukt.isEmpty())
                    dostepnePaczkaProduktID = ekstensjaPaczkaProdukt.stream().max(Comparator.comparing(PaczkaProdukt::getPaczkaProduktID)).orElse(null).getPaczkaProduktID() + 1;
            } else {
                file.getParentFile().mkdirs();
                file.createNewFile();
                zapiszEkstensjaPaczkaProdukt();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**Metody*/
    public int getPaczkaProduktID() {
        return paczkaProduktID;
    }

    public static List<PaczkaProdukt> getEkstensjaPaczkaProdukt() {
        return ekstensjaPaczkaProdukt;
    }

    @Override
    public String toString() {
        return "PaczkaProdukt{" +
                "paczkaProduktID=" + paczkaProduktID +
                ", produkt=" + produkt.getNazwa() + // Only product name to avoid cyclic call
                ", iloscProduktu=" + iloscProduktu +
                '}';
    }

}