import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.List;

public class PanelZamowienie extends JPanel {
    private JComboBox<String> adresComboBox;
    private JButton przyciskOplac;
    private GlowneOkno glowneOkno;
    private List<Produkt> koszyk;

    public PanelZamowienie(GlowneOkno glowneOkno) {
        this.glowneOkno = glowneOkno;

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel etykietaTytul = new JLabel("Zamówienie");
        etykietaTytul.setFont(new Font("Comic Sans MS", Font.ITALIC, 48));
        etykietaTytul.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(etykietaTytul, gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_START;

        JLabel etykietaAdres = new JLabel("Wybierz adres:");
        etykietaAdres.setFont(new Font("Arial", Font.PLAIN, 24));
        etykietaAdres.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(etykietaAdres, gbc);

        adresComboBox = new JComboBox<>();
        adresComboBox.setPreferredSize(new Dimension(300, 30));
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(adresComboBox, gbc);

        przyciskOplac = new JButton("Opłać zamówienie");
        przyciskOplac.setPreferredSize(new Dimension(200, 40));
        przyciskOplac.setBackground(new Color(0, 204, 0));
        przyciskOplac.setForeground(Color.WHITE);
        przyciskOplac.setFocusPainted(false);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(przyciskOplac, gbc);

        setBackground(new Color(102, 0, 153));

        przyciskOplac.addActionListener(e -> {
            String wybranyAdres = (String) adresComboBox.getSelectedItem();
            if (wybranyAdres != null && !wybranyAdres.isEmpty()) {
                Klient klient = glowneOkno.getZalogowanyKlient();
                if (klient != null) {
                    try {

                        Zamowienie noweZamowienie = new Zamowienie(wybranyAdres, new Date(), klient);

                        for (Produkt produkt : koszyk) {
                            Paczka nowaPaczka = Paczka.createPaczka(noweZamowienie);
                            nowaPaczka.addProdukt(produkt, 1);
                        }

                        JOptionPane.showMessageDialog(PanelZamowienie.this, "Zamówienie zostało złożone.");
                        glowneOkno.pokazPanelProdukty();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(PanelZamowienie.this, "Błąd: " + ex.getMessage(), "Błąd", JOptionPane.ERROR_MESSAGE);
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(PanelZamowienie.this, "Błąd: Brak zalogowanego użytkownika.", "Błąd", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(PanelZamowienie.this, "Proszę wybrać adres.", "Błąd", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public void ustawAdresy(Klient klient) {
        adresComboBox.removeAllItems();
        for (String adres : klient.getAdresy()) {
            adresComboBox.addItem(adres);
        }
    }

    public void ustawKoszyk(List<Produkt> koszyk) {
        this.koszyk = koszyk;
    }
}