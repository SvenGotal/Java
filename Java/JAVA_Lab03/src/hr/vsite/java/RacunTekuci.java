package hr.vsite.java;

public class RacunTekuci extends Racun{
    protected double kamata;
    protected double dzvMinus;

    RacunTekuci(Vlasnik vl, String valuta){
        super (vl, valuta);
    }

    @Override
    public Boolean UplataNaRacun(Promet promet) {
        obracunKamate();
        return super.UplataNaRacun(promet);
    }

    @Override
    public Boolean IsplataSaRacuna(Promet promet) {

        if(this.iznos - promet.iznos < this.dzvMinus)
            return false;

        this.iznos -= promet.iznos;
        return true;
    }

    private void obracunKamate(){
        if(this.iznos < 0 || this.iznos > 0){
            this.kamata =  0.15;
        }
        else
            this.kamata = 0.00;
    }

    public double ObracunajKamate(){
        obracunKamate();
        double kamata = this.iznos * this.kamata;
        this.iznos += kamata;
        return kamata;
    }

    public void PostaviMinus(double dzvMinus){
        this.dzvMinus = -dzvMinus;
    }
}
