import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Magazyn implements Serializable {

    /**Ekstensja*/
    private static List<Magazyn> ekstensjaMagazyn = new ArrayList<>();

    /**Atrybuty*/
    private final int magazynID;
    private static int dostepneMagazynID = 1;

    private String lokacja;

    /**Atrybuty asocjacji*/
    private List<Produkt> produkty = new ArrayList<>();
    private List<MagazynProdukt> magazynProdukty = new ArrayList<>();

    /**Konstruktory*/
    public Magazyn(String lokacja) {
        this.magazynID = dostepneMagazynID++;
        this.lokacja = lokacja;
    }

    /**Ekstensja*/
    public static void zapiszEkstensjaMagazyn() {
        final String extentFile = "src/Ekstensje/Magazyn.txt";
        try {
            var out = new ObjectOutputStream(new FileOutputStream(extentFile));
            out.writeObject(ekstensjaMagazyn);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void wczytajEkstensjaMagazyn() {
        final String extentFile = "src/Ekstensje/Magazyn.txt";
        File file = new File(extentFile);
        try {
            if (file.exists()) {
                var in = new ObjectInputStream(new FileInputStream(extentFile));
                ekstensjaMagazyn = (ArrayList<Magazyn>) in.readObject();
                in.close();

                if (!ekstensjaMagazyn.isEmpty())
                    dostepneMagazynID = ekstensjaMagazyn.stream().max(Comparator.comparing(Magazyn::getMagazynID)).orElse(null).getMagazynID() + 1;
            } else {
                file.getParentFile().mkdirs();
                file.createNewFile();
                zapiszEkstensjaMagazyn();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**Asocjacje*/
    public void addProdukt(Produkt produkt, int iloscProduktu) {
        if (produkty.contains(produkt)) {
            produkty.add(produkt);
            produkt.addMagazyn(this,iloscProduktu);

            MagazynProdukt magazynProdukt = new MagazynProdukt(this, produkt, iloscProduktu);
            magazynProdukty.add(magazynProdukt);
            produkt.addMagazynProdukt(magazynProdukt);
        }
    }

    public void addMagazynProdukt(MagazynProdukt magazynProdukt) {
        if (!magazynProdukty.contains(magazynProdukt)) {
            magazynProdukty.add(magazynProdukt);
        }
    }

    /**Metody*/
    public int getMagazynID() {
        return magazynID;
    }
}