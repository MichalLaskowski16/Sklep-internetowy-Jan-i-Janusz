import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public abstract class Osoba implements Serializable {

    /**Atrybuty*/
    private String imie;
    private String nazwisko;
    private String pesel;
    private LocalDate dataUrodzenia;
    private String email;
    private String numerTelefonu;

    /**Konstruktory*/
    public Osoba(String imie, String nazwisko, String pesel, String email, String numerTelefonu) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
        try {
            this.dataUrodzenia = wyliczDateUrodzenia(pesel);
        } catch (DateTimeParseException e) {
            System.out.println("Błąd parsowania numeru PESEL na datę urodzenia.");
            e.printStackTrace();
        }
        this.email = email;
        this.numerTelefonu = numerTelefonu;
    }

    public Osoba(String imie, String nazwisko, String pesel, String email) {
        super();
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
        try {
            this.dataUrodzenia = wyliczDateUrodzenia(pesel);
        } catch (DateTimeParseException e) {
            System.out.println("Błąd parsowania numeru PESEL na datę urodzenia.");
            e.printStackTrace();
        }
        this.email = email;
        this.numerTelefonu = null;
    }

    /**Metody*/
    public static LocalDate wyliczDateUrodzenia(String pesel) throws DateTimeParseException {
        int rok = Integer.parseInt(pesel.substring(0, 2));
        int miesiac = Integer.parseInt(pesel.substring(2, 4));
        int dzien = Integer.parseInt(pesel.substring(4, 6));

        if (miesiac > 20) {
            rok += 2000;
            miesiac -= 20;
        } else {
            rok += 1900;
        }

        return LocalDate.of(rok, miesiac, dzien);
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public String getPesel() {
        return pesel;
    }

    public String getEmail() {
        return email;
    }

    public String getNumerTelefonu() {
        return numerTelefonu;
    }

    public LocalDate getDataUrodzenia() {
        return dataUrodzenia;
    }

    public String getFullName() {
        return imie + " " + nazwisko;
    }

    @Override
    public String toString() {
        return  "imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", pesel='" + pesel + '\'' +
                ", dataUrodzenia=" + dataUrodzenia +
                ", email='" + email + '\'' +
                ", numerTelefonu='" + (numerTelefonu != null ? numerTelefonu : "N/A") + '\'';
    }
}