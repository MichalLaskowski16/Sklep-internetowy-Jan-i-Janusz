import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Ciezarowka extends Pojazd implements Serializable {

    /**Ekstensja*/
    private static List<Ciezarowka> ekstensjaCiezarowka = new ArrayList<>();

    /**Atrybuty*/
    private final int ciezarowkaID;
    private static int dostepneCiezarowkaID = 1;

    /**Konstruktor*/
    public Ciezarowka(String numerRejestracyjny) {
        super(numerRejestracyjny);
        this.ciezarowkaID = dostepneCiezarowkaID++;

        ekstensjaCiezarowka.add(this);
    }

    /**Ekstensja*/
    public static void zapiszEkstensjaCiezarowka() {
        final String extentFile = "src/Ekstensje/Ciezarowka.txt";
        try {
            var out = new ObjectOutputStream(new FileOutputStream(extentFile));
            out.writeObject(ekstensjaCiezarowka);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void wczytajEkstensjaCiezarowka() {
        final String extentFile = "src/Ekstensje/Ciezarowka.txt";
        File file = new File(extentFile);
        try {
            if (file.exists()) {
                var in = new ObjectInputStream(new FileInputStream(extentFile));
                ekstensjaCiezarowka = (ArrayList<Ciezarowka>) in.readObject();
                in.close();

                if (!ekstensjaCiezarowka.isEmpty())
                    dostepneCiezarowkaID = ekstensjaCiezarowka.stream().max(Comparator.comparing(Ciezarowka::getCiezarowkaID)).orElse(null).getCiezarowkaID() + 1;
            } else {
                file.getParentFile().mkdirs();
                file.createNewFile();
                zapiszEkstensjaCiezarowka();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**Metody*/
    @Override
    public float obliczCzasDostawy(float dystans) {
        int szybkosc = 70;
        return dystans/szybkosc;
    }

    public int getCiezarowkaID() {
        return ciezarowkaID;
    }
}