package hr.vsite.java;

public class Vlasnik {

    private final String oib;
    private String ime;
    private String prezime;
    private String adresa;

    public Vlasnik(String oib, String ime, String prezime, String adresa){
        this.oib = oib;
        this.ime = ime;
        this.prezime = prezime;
        this.adresa = adresa;
    }

    public String VratiIme(){
        return this.ime + " " + this.prezime;
    }
    public String VratiAdresu(){
        return this.adresa;
    }
    public void PromijeniIme(String ime){
        this.ime = ime;
    }
    public void PromijeniPrezime(String prezime){
        this.prezime = prezime;
    }
    public void PromijeniAdresu(String adresa){
        this.adresa = adresa;
    }

}
