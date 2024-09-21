import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PanelProdukty extends JPanel {
    private List<Produkt> koszyk;
    private JPanel panelListaKoszyk;
    private JLabel sumaKoszyka;
    private JLabel uzytkownikLabel;
    private JPanel panelZamowienia;

    public PanelProdukty(GlowneOkno glowneOkno) {
        koszyk = new ArrayList<>();

        setLayout(new BorderLayout());

        setBackground(new Color(76, 0, 102));

        JPanel panelUzytkownik = new JPanel();
        panelUzytkownik.setBackground(new Color(76, 0, 102));
        panelUzytkownik.setLayout(new FlowLayout(FlowLayout.CENTER));

        String nazwaUzytkownika = glowneOkno.czyZalogowany() ? "Użytkownik: " + glowneOkno.getNazwaUzytkownika() : "Użytkownik: Gość";
        uzytkownikLabel = new JLabel(nazwaUzytkownika);
        uzytkownikLabel.setFont(new Font("Arial", Font.BOLD, 18));
        uzytkownikLabel.setForeground(Color.WHITE);

        panelUzytkownik.add(uzytkownikLabel);

        add(panelUzytkownik, BorderLayout.NORTH);

        JPanel panelListaProduktow = new JPanel();
        panelListaProduktow.setLayout(new GridBagLayout());
        panelListaProduktow.setBackground(new Color(0, 0, 139));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        List<Produkt> produkty = Produkt.getEkstensjaProdukt();
        int y = 0;
        for (Produkt produkt : produkty) {
            JPanel panelProdukt = new JPanel();
            panelProdukt.setLayout(new BorderLayout());
            panelProdukt.setPreferredSize(new Dimension(580, 50));
            panelProdukt.setBackground(new Color(255, 255, 255));
            panelProdukt.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            JLabel labelNazwa = new JLabel(produkt.getNazwa() + ", cena: " + produkt.getCena() + " zł");
            panelProdukt.add(labelNazwa, BorderLayout.CENTER);

            JButton buttonDodaj = new JButton("Dodaj do koszyka");
            buttonDodaj.addActionListener(e -> {
                koszyk.add(produkt);
                addProductToKoszykPanel(produkt);
                updateSumaKoszyka();
                System.out.println("Dodano do koszyka: " + produkt.getNazwa());
            });
            panelProdukt.add(buttonDodaj, BorderLayout.EAST);

            gbc.gridx = 0;
            gbc.gridy = y++;
            panelListaProduktow.add(panelProdukt, gbc);
        }

        JScrollPane scrollPaneProdukty = new JScrollPane(panelListaProduktow);
        scrollPaneProdukty.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPaneProdukty.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneProdukty.setPreferredSize(new Dimension(700, 500));

        JPanel panelGlowny = new JPanel();
        panelGlowny.setLayout(new BorderLayout());
        panelGlowny.add(scrollPaneProdukty, BorderLayout.CENTER);
        panelGlowny.setBackground(new Color(76, 0, 102));
        panelGlowny.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        add(panelGlowny, BorderLayout.WEST);

        panelListaKoszyk = new JPanel();
        panelListaKoszyk.setLayout(new GridBagLayout());
        panelListaKoszyk.setBackground(new Color(204, 204, 153));

        JScrollPane scrollKoszyk = new JScrollPane(panelListaKoszyk);
        scrollKoszyk.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollKoszyk.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollKoszyk.setPreferredSize(new Dimension(700, 500));

        JPanel panelKoszykGlowny = new JPanel();
        panelKoszykGlowny.setLayout(new BorderLayout());
        panelKoszykGlowny.add(scrollKoszyk, BorderLayout.CENTER);
        panelKoszykGlowny.setBackground(new Color(76, 0, 102));
        panelKoszykGlowny.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        add(panelKoszykGlowny, BorderLayout.EAST);

        panelZamowienia = new JPanel();
        panelZamowienia.setLayout(new BoxLayout(panelZamowienia, BoxLayout.Y_AXIS));
        panelZamowienia.setBackground(new Color(76, 0, 102));

        JScrollPane scrollPaneZamowienia = new JScrollPane(panelZamowienia);
        scrollPaneZamowienia.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPaneZamowienia.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneZamowienia.setPreferredSize(new Dimension(700, 500));

        JPanel panelZamowieniaGlowny = new JPanel();
        panelZamowieniaGlowny.setLayout(new BorderLayout());
        panelZamowieniaGlowny.add(scrollPaneZamowienia, BorderLayout.CENTER);
        panelZamowieniaGlowny.setBackground(new Color(76, 0, 102));
        panelZamowieniaGlowny.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        add(panelZamowieniaGlowny, BorderLayout.CENTER);

        JButton buttonZamowienie = new JButton("Złóż zamówienie");
        buttonZamowienie.setPreferredSize(new Dimension(200, 50));
        buttonZamowienie.setBackground(new Color(0, 204, 0));
        buttonZamowienie.setForeground(Color.WHITE);
        buttonZamowienie.addActionListener(e -> {
            if (glowneOkno.czyZalogowany()) {
                if (!koszyk.isEmpty()) {
                    glowneOkno.pokazPanelZamowienie(koszyk);
                } else {
                    JOptionPane.showMessageDialog(PanelProdukty.this, "Koszyk jest pusty.", "Błąd", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                glowneOkno.pokazPanelLogowania();
            }
        });

        sumaKoszyka = new JLabel("Suma: 0.00 zł");
        sumaKoszyka.setFont(new Font("Arial", Font.BOLD, 18));
        sumaKoszyka.setForeground(Color.WHITE);

        JPanel panelPrzyciskZamowienie = new JPanel();
        panelPrzyciskZamowienie.setBackground(new Color(76, 0, 102));
        panelPrzyciskZamowienie.setLayout(new GridBagLayout());
        GridBagConstraints gbcButton = new GridBagConstraints();
        gbcButton.gridx = 0;
        gbcButton.gridy = 0;
        gbcButton.anchor = GridBagConstraints.CENTER;
        panelPrzyciskZamowienie.add(buttonZamowienie, gbcButton);

        GridBagConstraints gbcSuma = new GridBagConstraints();
        gbcSuma.gridx = 0;
        gbcSuma.gridy = 1;
        gbcSuma.anchor = GridBagConstraints.CENTER;
        gbcSuma.insets = new Insets(20, 0, 0, 0);
        panelPrzyciskZamowienie.add(sumaKoszyka, gbcSuma);

        add(panelPrzyciskZamowienie, BorderLayout.SOUTH);

        aktualizujZamowienia(glowneOkno.getZalogowanyKlient());
    }

    private void addProductToKoszykPanel(Produkt produkt) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.gridx = 0;
        gbc.gridy = panelListaKoszyk.getComponentCount();

        JPanel panelProdukt = new JPanel();
        panelProdukt.setLayout(new BorderLayout());
        panelProdukt.setPreferredSize(new Dimension(580, 50));
        panelProdukt.setBackground(new Color(255, 255, 255));
        panelProdukt.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel labelNazwa = new JLabel(produkt.getNazwa() + ", cena: " + produkt.getCena() + " zł");
        panelProdukt.add(labelNazwa, BorderLayout.CENTER);

        JButton buttonUsun = new JButton("Usuń z koszyka");
        buttonUsun.addActionListener(e -> {
            koszyk.remove(produkt);
            panelListaKoszyk.remove(panelProdukt);
            panelListaKoszyk.revalidate();
            panelListaKoszyk.repaint();
            updateSumaKoszyka();
            System.out.println("Usunięto z koszyka: " + produkt.getNazwa());
        });
        panelProdukt.add(buttonUsun, BorderLayout.EAST);

        panelListaKoszyk.add(panelProdukt, gbc);
        panelListaKoszyk.revalidate();
        panelListaKoszyk.repaint();
    }

    private void updateSumaKoszyka() {
        double suma = 0.0;
        for (Produkt produkt : koszyk) {
            suma += produkt.getCena();
        }
        sumaKoszyka.setText("Suma: " + String.format("%.2f", suma) + " zł");
    }

    public void aktualizujUzytkownika(String nazwaUzytkownika) {
        uzytkownikLabel.setText(nazwaUzytkownika);
    }

    public void aktualizujZamowienia(Klient klient) {
        panelZamowienia.removeAll();

        if (klient == null || klient.getZamowienia().isEmpty()) {
            JLabel brakZamowienLabel = new JLabel("Brak zamówień.");
            brakZamowienLabel.setFont(new Font("Arial", Font.PLAIN, 18));
            brakZamowienLabel.setForeground(Color.WHITE);
            panelZamowienia.add(brakZamowienLabel);
        } else {
            for (Zamowienie zamowienie : klient.getZamowienia()) {
                JTextArea zamowienieTextArea = new JTextArea(zamowienie.toString());
                zamowienieTextArea.setFont(new Font("Arial", Font.PLAIN, 18));
                zamowienieTextArea.setForeground(Color.WHITE);
                zamowienieTextArea.setBackground(new Color(76, 0, 102));
                zamowienieTextArea.setLineWrap(true);
                zamowienieTextArea.setWrapStyleWord(true);
                zamowienieTextArea.setEditable(false);
                zamowienieTextArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                panelZamowienia.add(zamowienieTextArea);
            }
        }

        panelZamowienia.revalidate();
        panelZamowienia.repaint();
    }

    public List<Produkt> getKoszyk() {
        return koszyk;
    }

}