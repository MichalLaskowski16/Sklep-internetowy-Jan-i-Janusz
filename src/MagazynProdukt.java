import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MagazynProdukt implements Serializable {

    /**Ekstensja*/
    private static List<MagazynProdukt> ekstensjaMagazynProdukt = new ArrayList<>();

    /**Atrybuty*/
    private final int magazynProduktID;
    private static int dostepneMagazynProduktID = 1;

    /**Atrybuty asocjacji*/
    private Magazyn magazyn;
    private Produkt produkt;
    private int iloscProduktu;

    /**Konstruktor*/
    public MagazynProdukt(Magazyn magazyn, Produkt produkt, int iloscProduktu) {
        this.magazynProduktID = dostepneMagazynProduktID++;

        this.magazyn = magazyn;
        this.produkt = produkt;
        this.iloscProduktu = iloscProduktu;


        produkt.addMagazynProdukt(this);
        magazyn.addMagazynProdukt(this);
        ekstensjaMagazynProdukt.add(this);
    }

    /**Ekstensja*/
    public static void zapiszEkstensjaMagazynProdukt() {
        final String extentFile = "src/Ekstensje/MagazynProdukt.txt";
        try {
            var out = new ObjectOutputStream(new FileOutputStream(extentFile));
            out.writeObject(ekstensjaMagazynProdukt);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void wczytajEkstensjaMagazynProdukt() {
        final String extentFile = "src/Ekstensje/MagazynProdukt.txt";
        File file = new File(extentFile);
        try {
            if (file.exists()) {
                var in = new ObjectInputStream(new FileInputStream(extentFile));
                ekstensjaMagazynProdukt = (ArrayList<MagazynProdukt>) in.readObject();
                in.close();

                if (!ekstensjaMagazynProdukt.isEmpty())
                    dostepneMagazynProduktID = ekstensjaMagazynProdukt.stream().max(Comparator.comparing(MagazynProdukt::getMagazynProduktID)).orElse(null).getMagazynProduktID() + 1;
            } else {
                file.getParentFile().mkdirs();
                file.createNewFile();
                zapiszEkstensjaMagazynProdukt();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**Metody*/
    public int getMagazynProduktID() {
        return magazynProduktID;
    }
}