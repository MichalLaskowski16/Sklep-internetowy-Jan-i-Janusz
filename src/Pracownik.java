public abstract class Pracownik extends Osoba {

    /**Atrybuty*/
    private int stawka;
    private static int minimalnaStawka = 4500;
    private int staz; //w miesiacach

    /**Konstruktory*/
    public Pracownik(String imie, String nazwisko, String pesel, String email, String numerTelefonu, int stawka) {
        super(imie, nazwisko, pesel, email, numerTelefonu);
        this.stawka = stawka;
        staz = 0;
    }

    public Pracownik(String imie, String nazwisko, String pesel, String email, int stawka) {
        super(imie, nazwisko, pesel, email);
        this.stawka = stawka;
        staz = 0;
    }

    /**Metody*/
    public int getStawka() {
        return stawka;
    }

    public static int getMinimalnaStawka() {
        return minimalnaStawka;
    }

    public int getStaz() {
        return staz;
    }
}