import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GlowneOkno extends JFrame {
    private boolean zalogowany;
    private String nazwaUzytkownika;
    private Klient zalogowanyKlient;
    private PanelProdukty panelProdukty;
    private PanelLogowania panelLogowania;
    private PanelRejestracja panelRejestracja;
    private PanelZamowienie panelZamowienie;

    public GlowneOkno() {
        setTitle("Sklep internetowy Jan i Janusz");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(800, 600));

        panelProdukty = new PanelProdukty(this);
        panelLogowania = new PanelLogowania(this);
        panelRejestracja = new PanelRejestracja(this);
        panelZamowienie = new PanelZamowienie(this);

        pokazPanelProdukty();

        setVisible(true);
    }

    public void ustawZalogowany(boolean zalogowany, String nazwaUzytkownika, Klient klient) {
        this.zalogowany = zalogowany;
        this.nazwaUzytkownika = nazwaUzytkownika;
        this.zalogowanyKlient = klient;
        panelProdukty.aktualizujUzytkownika(zalogowany ? "Użytkownik: " + nazwaUzytkownika : "Użytkownik: Gość");
    }

    public boolean czyZalogowany() {
        return zalogowany;
    }

    public String getNazwaUzytkownika() {
        return nazwaUzytkownika;
    }

    public Klient getZalogowanyKlient() {
        return zalogowanyKlient;
    }

    public void pokazPanelProdukty() {
        getContentPane().removeAll();
        panelProdukty = new PanelProdukty(this);
        add(panelProdukty);
        revalidate();
        repaint();
    }

    public void pokazPanelLogowania() {
        getContentPane().removeAll();
        add(panelLogowania);
        revalidate();
        repaint();
    }

    public void pokazPanelRejestracja() {
        getContentPane().removeAll();
        add(panelRejestracja);
        revalidate();
        repaint();
    }

    public void pokazPanelZamowienie(List<Produkt> koszyk) {
        getContentPane().removeAll();
        panelZamowienie.ustawAdresy(zalogowanyKlient);
        panelZamowienie.ustawKoszyk(koszyk);
        add(panelZamowienie);
        revalidate();
        repaint();
    }
}