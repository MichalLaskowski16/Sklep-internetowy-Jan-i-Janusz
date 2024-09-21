public abstract class Pojazd {

    /**Kolekcje*/
    public enum Status {
        ZEPSUTY,
        W_NAPRAWIE,
        SPRAWNY
    }

    /**Atrybuty*/
    private Status status;
    private String numerRejestracyjny;

    /**Atrybuty asocjacji*/
    private Kurier kurier;

    /**Konstruktor*/
    public Pojazd(String numerRejestracyjny) {
        this.status = Status.SPRAWNY;
        this.numerRejestracyjny = numerRejestracyjny;
    }

    /**Metody*/
    public void setStatus(Status status) {
        this.status = status;
    }

    public abstract float obliczCzasDostawy(float dystans);

    /**Asocjacje*/
    public void setKurier(Kurier kurier) {
        if (this.kurier!=kurier) {
            this.kurier = kurier;
            kurier.addPojazd(this);
        }
    }
}