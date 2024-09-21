import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PanelLogowania extends JPanel {
    private JTextField poleLogin;
    private JPasswordField poleHaslo;
    private JButton przyciskZaloguj;
    private JButton przyciskUtworzKonto;
    private GlowneOkno glowneOkno;

    public PanelLogowania(GlowneOkno glowneOkno) {
        this.glowneOkno = glowneOkno;

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel etykietaTytul = new JLabel("JAN I JANUSZ");
        etykietaTytul.setFont(new Font("Comic Sans MS", Font.ITALIC, 48));
        etykietaTytul.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        add(etykietaTytul, gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_END;

        JLabel etykietaLogin = new JLabel("Login");
        etykietaLogin.setFont(new Font("Arial", Font.PLAIN, 24));
        etykietaLogin.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(etykietaLogin, gbc);

        poleLogin = new JTextField(20);
        poleLogin.setPreferredSize(new Dimension(300, 30));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(poleLogin, gbc);

        JLabel etykietaHaslo = new JLabel("Hasło");
        etykietaHaslo.setFont(new Font("Arial", Font.PLAIN, 24));
        etykietaHaslo.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(etykietaHaslo, gbc);

        poleHaslo = new JPasswordField(20);
        poleHaslo.setPreferredSize(new Dimension(300, 30));
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(poleHaslo, gbc);

        przyciskZaloguj = new JButton("Zaloguj się");
        przyciskZaloguj.setPreferredSize(new Dimension(200, 40));
        przyciskZaloguj.setBackground(new Color(0, 204, 0));
        przyciskZaloguj.setForeground(Color.WHITE);
        przyciskZaloguj.setFocusPainted(false);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(przyciskZaloguj, gbc);

        przyciskUtworzKonto = new JButton("Utwórz konto");
        przyciskUtworzKonto.setPreferredSize(new Dimension(200, 40));
        przyciskUtworzKonto.setBackground(new Color(128, 128, 128));
        przyciskUtworzKonto.setForeground(Color.WHITE);
        przyciskUtworzKonto.setFocusPainted(false);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(przyciskUtworzKonto, gbc);

        setBackground(new Color(102, 0, 153));

        przyciskZaloguj.addActionListener(e -> {

            String login = poleLogin.getText();
            String haslo = new String(poleHaslo.getPassword());

            Klient klient = sprawdzDaneLogowania(login, haslo);
            if (klient != null) {
                glowneOkno.ustawZalogowany(true, login, klient);
                JOptionPane.showMessageDialog(PanelLogowania.this, "Zalogowano pomyślnie.");
                glowneOkno.pokazPanelProdukty();
            } else {
                JOptionPane.showMessageDialog(PanelLogowania.this, "Nieprawidłowy login lub hasło.", "Błąd", JOptionPane.ERROR_MESSAGE);
            }
        });

        przyciskUtworzKonto.addActionListener(e -> glowneOkno.pokazPanelRejestracja());
    }

    private Klient sprawdzDaneLogowania(String login, String haslo) {
        List<Klient> klienci = Klient.getEkstensjaKlient();
        for (Klient klient : klienci) {
            if (klient.getLogin().equals(login) && klient.getHaslo().equals(haslo)) {
                return klient;
            }
        }
        return null;
    }
}