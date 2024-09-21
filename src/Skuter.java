import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Skuter extends Pojazd implements Serializable {

    /**Ekstensja*/
    private static List<Skuter> ekstensjaSkuter = new ArrayList<>();

    /**Atrybuty*/
    private final int skuterID;
    private static int dostepneSkuterID = 1;

    /**Konstruktor*/
    public Skuter(String numerRejestracyjny) {
        super(numerRejestracyjny);
        this.skuterID = dostepneSkuterID++;

        ekstensjaSkuter.add(this);
    }

    /**Ekstensja*/
    public static void zapiszEkstensjaSkuter() {
        final String extentFile = "src/Ekstensje/Skuter.txt";
        try {
            var out = new ObjectOutputStream(new FileOutputStream(extentFile));
            out.writeObject(ekstensjaSkuter);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void wczytajEkstensjaSkuter() {
        final String extentFile = "src/Ekstensje/Skuter.txt";
        File file = new File(extentFile);
        try {
            if (file.exists()) {
                var in = new ObjectInputStream(new FileInputStream(extentFile));
                ekstensjaSkuter = (ArrayList<Skuter>) in.readObject();
                in.close();

                if (!ekstensjaSkuter.isEmpty())
                    dostepneSkuterID = ekstensjaSkuter.stream().max(Comparator.comparing(Skuter::getSkuterID)).orElse(null).getSkuterID() + 1;
            } else {
                file.getParentFile().mkdirs();
                file.createNewFile();
                zapiszEkstensjaSkuter();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**Metody*/
    public int getSkuterID() {
        return skuterID;
    }

    @Override
    public float obliczCzasDostawy(float dystans) {
        int szybkosc = 35;
        return dystans / szybkosc;
    }
}