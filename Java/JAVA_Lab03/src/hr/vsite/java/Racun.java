package hr.vsite.java;

public class Racun {

    protected double iznos;
    protected final String brRacuna;
    protected Vlasnik vlasnik;
    protected String valuta;
    protected double iznosUplata;
    protected double iznosIsplata;

    Racun (){
        this.iznos = 0;
        this.vlasnik = null;
        this.brRacuna = "";
        this.valuta = "";
        this.iznosUplata = 0;
        this.iznosIsplata = 0;
    }
    Racun(Vlasnik vl, String valuta){
        this.iznos = 0;
        this.brRacuna = "HR01" + vl.hashCode();
        this.vlasnik = vl;
        this.valuta = valuta;
        this.iznosUplata = 0;
        this.iznosIsplata = 0;
    }

    public String VratiBrojRacuna(){
        return this.brRacuna;
    }
    public String VratiImeVlasnika(){
        return this.vlasnik.VratiIme();
    }
    public String VratiIznos(){
        return String.valueOf(iznos);
    }

    public Boolean UplataNaRacun(Promet promet){

        if(this.valuta.contentEquals(promet.valuta)){
            this.iznos += promet.iznos;
            this.iznosUplata += iznos;
            return true;
        }
        return false;
    }
    public Boolean IsplataSaRacuna(Promet promet){

        if(!this.valuta.contentEquals(promet.valuta))
            return false;

        this.iznos -= iznos;
        this.iznosIsplata += iznos;
        return true;
    }
}
