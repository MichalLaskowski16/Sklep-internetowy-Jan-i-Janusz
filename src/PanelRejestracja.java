import javax.swing.*;
import java.awt.*;

public class PanelRejestracja extends JPanel {
    private JTextField poleImie;
    private JTextField poleNazwisko;
    private JTextField polePesel;
    private JTextField poleEmail;
    private JTextField poleAdres;
    private JTextField poleTelefon;
    private JPasswordField poleHaslo;
    private JButton przyciskZarejestruj;
    private GlowneOkno glowneOkno;

    public PanelRejestracja(GlowneOkno glowneOkno) {
        this.glowneOkno = glowneOkno;

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel etykietaTytul = new JLabel("Rejestracja");
        etykietaTytul.setFont(new Font("Comic Sans MS", Font.ITALIC, 48));
        etykietaTytul.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        add(etykietaTytul, gbc);

        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_START;

        JLabel etykietaImie = new JLabel("Imię");
        etykietaImie.setFont(new Font("Arial", Font.PLAIN, 24));
        etykietaImie.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(etykietaImie, gbc);

        gbc.anchor = GridBagConstraints.LINE_START;

        poleImie = new JTextField(20);
        poleImie.setPreferredSize(new Dimension(300, 30));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(poleImie, gbc);

        JLabel etykietaNazwisko = new JLabel("Nazwisko");
        etykietaNazwisko.setFont(new Font("Arial", Font.PLAIN, 24));
        etykietaNazwisko.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(etykietaNazwisko, gbc);

        poleNazwisko = new JTextField(20);
        poleNazwisko.setPreferredSize(new Dimension(300, 30));
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(poleNazwisko, gbc);

        JLabel etykietaPesel = new JLabel("PESEL");
        etykietaPesel.setFont(new Font("Arial", Font.PLAIN, 24));
        etykietaPesel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(etykietaPesel, gbc);

        polePesel = new JTextField(20);
        polePesel.setPreferredSize(new Dimension(300, 30));
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(polePesel, gbc);

        JLabel etykietaEmail = new JLabel("Email");
        etykietaEmail.setFont(new Font("Arial", Font.PLAIN, 24));
        etykietaEmail.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(etykietaEmail, gbc);

        poleEmail = new JTextField(20);
        poleEmail.setPreferredSize(new Dimension(300, 30));
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(poleEmail, gbc);

        JLabel etykietaAdres = new JLabel("Adres");
        etykietaAdres.setFont(new Font("Arial", Font.PLAIN, 24));
        etykietaAdres.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(etykietaAdres, gbc);

        poleAdres = new JTextField(20);
        poleAdres.setPreferredSize(new Dimension(300, 30));
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(poleAdres, gbc);

        JLabel etykietaTelefon = new JLabel("Telefon (opcjonalne)");
        etykietaTelefon.setFont(new Font("Arial", Font.PLAIN, 24));
        etykietaTelefon.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 6;
        add(etykietaTelefon, gbc);

        poleTelefon = new JTextField(20);
        poleTelefon.setPreferredSize(new Dimension(300, 30));
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(poleTelefon, gbc);

        JLabel etykietaHaslo = new JLabel("Hasło");
        etykietaHaslo.setFont(new Font("Arial", Font.PLAIN, 24));
        etykietaHaslo.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 7;
        add(etykietaHaslo, gbc);

        poleHaslo = new JPasswordField(20);
        poleHaslo.setPreferredSize(new Dimension(300, 30));
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(poleHaslo, gbc);

        przyciskZarejestruj = new JButton("Zarejestruj się");
        przyciskZarejestruj.setPreferredSize(new Dimension(200, 40));
        przyciskZarejestruj.setBackground(new Color(0, 204, 0));
        przyciskZarejestruj.setForeground(Color.WHITE);
        przyciskZarejestruj.setFocusPainted(false);
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(przyciskZarejestruj, gbc);

        setBackground(new Color(102, 0, 153));

        przyciskZarejestruj.addActionListener(e -> {
            String imie = poleImie.getText();
            String nazwisko = poleNazwisko.getText();
            String pesel = polePesel.getText();
            String email = poleEmail.getText();
            String adres = poleAdres.getText();
            String telefon = poleTelefon.getText();
            String haslo = new String(poleHaslo.getPassword());

            if (!imie.isEmpty() && !nazwisko.isEmpty() && !pesel.isEmpty() && !email.isEmpty() && !adres.isEmpty() && !haslo.isEmpty()) {
                Klient nowyKlient;
                if (!telefon.isEmpty()) {
                    nowyKlient = new Klient(imie, nazwisko, pesel, email, telefon, email, haslo, adres);
                } else {
                    nowyKlient = new Klient(imie, nazwisko, pesel, email, email, haslo, adres);
                }
                glowneOkno.ustawZalogowany(true, email, nowyKlient);
                JOptionPane.showMessageDialog(PanelRejestracja.this, "Zarejestrowano pomyślnie.");
                glowneOkno.pokazPanelProdukty();
            } else {
                JOptionPane.showMessageDialog(PanelRejestracja.this, "Proszę wypełnić wszystkie pola.", "Błąd", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}