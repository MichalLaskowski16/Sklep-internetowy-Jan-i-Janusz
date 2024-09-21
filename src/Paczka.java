import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Paczka implements Serializable {

    /**Ekstensja*/
    private static List<Paczka> ekstensjaPaczka = new ArrayList<>();

    /**Atrybuty*/
    private final int paczkaID;
    private static int dostepnePaczkaID = 1;

    /**Atrybuty asocjacji*/
    private Zamowienie zamowienie;

    private Map<Integer, Produkt> produkty = new HashMap<>();
    private List<PaczkaProdukt> paczkaProdukty = new ArrayList<>();

    /**Konstruktor*/
    private Paczka(Zamowienie zamowienie) {
        this.paczkaID = dostepnePaczkaID++;
        this.zamowienie = zamowienie;
    }

    public static Paczka createPaczka(Zamowienie zamowienie) throws Exception {
        if (zamowienie == null) {
            throw new Exception("Zamowienie nie istnieje");
        }

        Paczka nowaPaczka = new Paczka(zamowienie);

        zamowienie.addPaczka(nowaPaczka);

        return nowaPaczka;
    }

    /**Asocjacje*/
    public void addProdukt(Produkt produkt, int iloscProduktu) {
        if (!produkty.containsValue(produkt)) {
            produkty.put(produkty.size()+1,produkt);
            produkt.addPaczka(this,iloscProduktu);

            PaczkaProdukt paczkaProdukt = new PaczkaProdukt(this, produkt, iloscProduktu);
            paczkaProdukty.add(paczkaProdukt);
            produkt.addPaczkaProdukt(paczkaProdukt);
        }
    }

    public void addPaczkaProdukt(PaczkaProdukt paczkaProdukt) {
        if (!paczkaProdukty.contains(paczkaProdukt)) {
            paczkaProdukty.add(paczkaProdukt);
        }
    }

    /**Metody*/
    public int getPaczkaID() {
        return paczkaID;
    }

    public static void setDostepnePaczkaID(int dostepnePaczkaID) {
        Paczka.dostepnePaczkaID = dostepnePaczkaID;
    }

    @Override
    public String toString() {
        StringBuilder produktyStr = new StringBuilder();
        for (Map.Entry<Integer, Produkt> entry : produkty.entrySet()) {
            produktyStr.append("ProduktID: ").append(entry.getKey())
                    .append(", Produkt: ").append(entry.getValue().getNazwa())
                    .append("\n");
        }

        StringBuilder paczkaProduktyStr = new StringBuilder();
        for (PaczkaProdukt pp : paczkaProdukty) {
            paczkaProduktyStr.append(pp.toString()).append("\n");
        }

        return "Paczka{" +
                "paczkaID=" + paczkaID +
                ", zamowienieID=" + zamowienie.getZamowienieID() +
                ", produkty=\n" + produktyStr.toString() +
                ", paczkaProdukty=\n" + paczkaProduktyStr.toString() +
                '}';
    }

}