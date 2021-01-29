package hr.vsite.java;

public class Main {

    public static void main(String[] args) {

        //3.1
        Vlasnik vlasnikAR = new Vlasnik("123123123", "Alfred", "Rosenhauer", "Masarykova 52");
        Racun AR = new Racun(vlasnikAR, "HRK"); //b1

        Vlasnik vlasnikBP = new Vlasnik("456456456", "Bruno", "Potoćki", "Jurjevska 15");
        Racun BP = new Racun(vlasnikBP, "HRK"); //b2

        //3.1
        Promet p1 = new Promet(100, "HRK");
        Promet p2 = new Promet(90, "HRK");
        AR.UplataNaRacun(p1);
        BP.IsplataSaRacuna(p2);

        //3.3
        Promet p3 = new Promet(200, "HRK");
        Racun b3 = AR;

        //3.4
        b3.IsplataSaRacuna(p1);
        BP.UplataNaRacun(p3);

        //3.5
        Vlasnik vlasnikVJ = new Vlasnik("789789789", "Vlado", "Jelačić", "Pile I. 103");
        Racun VJ = new Racun(vlasnikVJ, "HRK");
        b3 = VJ;

        //3.6
        Promet p4 = new Promet(250, "HRK");
        b3.UplataNaRacun(p4);

        Vlasnik vTEST = new Vlasnik("000000000", "NoName", "NoLast", "NoAdr");
        RacunOrocenje orocenje = new RacunOrocenje(vTEST, "HRK");

        Promet uplataNaOrocenje = new Promet(500, "HRK");
        orocenje.PostaviKamatu(15);
        orocenje.UplataNaRacun(uplataNaOrocenje);
        System.out.println("Stanje računa: " + orocenje.VratiStanje());
        System.out.println(orocenje.ObracunKamate());
        System.out.println("Novo stanje računa: " + orocenje.VratiStanje());

        RacunTekuci tekuci = new RacunTekuci(vTEST, "HRK");
        tekuci.PostaviMinus(500);
        Promet isplataSTekuceg = new Promet(100, "HRK");
        tekuci.IsplataSaRacuna(isplataSTekuceg);

        System.out.println("Stanje tekućeg računa: " + tekuci.VratiIznos());
        System.out.println("Kamata: " + tekuci.ObracunajKamate() + " Iznos: " + tekuci.VratiIznos());
    }
}
