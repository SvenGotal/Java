package hr.vsite.java;

public class RacunOrocenje extends Racun {
    protected double pozKamata;

    RacunOrocenje(Vlasnik vl, String valuta) {
        super(vl, valuta);
    }

    //Htio sam ovdje nasljediti klasu Racun ali i blokirati metodu IsplataSaRacuna jer
    //Oročenja nemaju isplate, već samo uplate.
    @Override
    public Boolean IsplataSaRacuna(Promet promet) {
        return false;
    }

    public double VratiStanje(){
        return this.iznos;
    }

    public double ObracunKamate(){
        double kamata = this.iznos * pozKamata;
        this.iznos += kamata;
        return kamata;
    }

    public void PostaviKamatu(double kamata){
        this.pozKamata = kamata/100;
    }

}
