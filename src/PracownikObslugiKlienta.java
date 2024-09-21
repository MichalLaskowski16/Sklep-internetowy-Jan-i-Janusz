import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PracownikObslugiKlienta extends Pracownik implements Serializable {

    /**Ekstensja*/
    private static List<PracownikObslugiKlienta> ekstensjaPracownikObslugiKlienta = new ArrayList<>();

    /**Atrybuty*/
    private final int pracownikObslugiKlientaID;
    private static int dostepnePracownikObslugiKlientaID = 1;

    /**Konstruktory*/
    public PracownikObslugiKlienta(String imie, String nazwisko, String pesel, String email, String numerTelefonu, int stawka) {
        super(imie, nazwisko, pesel, email, numerTelefonu, stawka);
        pracownikObslugiKlientaID = dostepnePracownikObslugiKlientaID++;

        ekstensjaPracownikObslugiKlienta.add(this);
    }

    public PracownikObslugiKlienta(String imie, String nazwisko, String pesel, String email, int stawka) {
        super(imie, nazwisko, pesel, email, stawka);
        pracownikObslugiKlientaID = dostepnePracownikObslugiKlientaID++;

        ekstensjaPracownikObslugiKlienta.add(this);
    }

    /**Ekstensja*/
    public static void zapiszEkstensjaPracownikObslugiKlienta() {
        final String extentFile = "src/Ekstensje/PracownikObslugiKlienta.txt";
        try {
            var out = new ObjectOutputStream(new FileOutputStream(extentFile));
            out.writeObject(ekstensjaPracownikObslugiKlienta);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void wczytajEkstensjaPracownikObslugiKlienta() {
        final String extentFile = "src/Ekstensje/PracownikObslugiKlienta.txt";
        File file = new File(extentFile);
        try {
            if (file.exists()) {
                var in = new ObjectInputStream(new FileInputStream(extentFile));
                ekstensjaPracownikObslugiKlienta = (ArrayList<PracownikObslugiKlienta>) in.readObject();
                in.close();

                if (!ekstensjaPracownikObslugiKlienta.isEmpty())
                    dostepnePracownikObslugiKlientaID = ekstensjaPracownikObslugiKlienta.stream().max(Comparator.comparing(PracownikObslugiKlienta::getPracownikObslugiKlientaID)).orElse(null).getPracownikObslugiKlientaID() + 1;
            } else {
                file.getParentFile().mkdirs();
                file.createNewFile();
                zapiszEkstensjaPracownikObslugiKlienta();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**Zmiana typu*/
    public PracownikObslugiKlienta(Kurier kurier) {
        super(kurier.getImie(), kurier.getNazwisko(), kurier.getPesel(), kurier.getEmail(), kurier.getNumerTelefonu() != null ? kurier.getNumerTelefonu() : null, kurier.getStawka());
        pracownikObslugiKlientaID = dostepnePracownikObslugiKlientaID++;

        Kurier.removeKurier(kurier);
        ekstensjaPracownikObslugiKlienta.add(this);
    }

    /**Metody*/
    public static void removePracownikObslugiKlienta(PracownikObslugiKlienta pracownikObslugiKlienta) {
        ekstensjaPracownikObslugiKlienta.remove(pracownikObslugiKlienta);
    }

    public int getPracownikObslugiKlientaID() {
        return pracownikObslugiKlientaID;
    }
}