package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void eiLisataMiinusta() {
        varasto.lisaaVarastoon(-1.4);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void eiLisataLiikaa() {
        varasto.lisaaVarastoon(12);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void eiOtetaMiinusta() {
        varasto.lisaaVarastoon(6);
        varasto.otaVarastosta(-1.2);
        assertEquals(6, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void eiOtetaLiikaa() {
        varasto.lisaaVarastoon(6);
        double otettu = varasto.otaVarastosta(8);
        assertEquals(6, otettu, vertailuTarkkuus);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void varastoMerkkijonoksi() {
        assertTrue(varasto.toString().equals("saldo = 0.0, vielä tilaa 10.0"));
    }
    
    @Test
    public void tehdaanErilaisiaVarastojaTesti() {
        varasto = new Varasto(-2);
        assertEquals("Varaston koko miinuksella tekee varaston, jolla on väärä tilavuus", 0, varasto.getTilavuus(), vertailuTarkkuus);
        varasto = new Varasto(10, 5);
        assertEquals("Varaston (10, 5) tilavuus väärin", 10, varasto.getTilavuus(), vertailuTarkkuus);
        assertEquals("Varaston (10, 5) saldo väärin", 5, varasto.getSaldo(), vertailuTarkkuus);
        varasto = new Varasto(2, 5);
        assertEquals("Ylitäyden varaston saldo väärin", 2, varasto.getSaldo(), vertailuTarkkuus);
        varasto = new Varasto(2, -1.5);
        assertEquals("Varaston alkusaldo miinuksella tekee varaston, jolla on väärä alkusaldo", 0, varasto.getSaldo(), vertailuTarkkuus);
        varasto = new Varasto(-1.5, 2);
        assertEquals("Varaston koko miinuksella tekee varaston, jolla on väärä tilavuus", 0, varasto.getTilavuus(), vertailuTarkkuus);
    }

}