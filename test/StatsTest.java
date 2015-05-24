import junit.framework.*;

/**
 * Created by Ala on 2015-05-22.
 */
public class StatsTest extends TestCase {
    public void testZnajdzPrefiksy(){
        Bot bot = new Bot(2);
        bot.czytajTekst("trzy raz cztery dwa piec raz dwa raz szesc raz cztery");
        Stats mojestaty = new Stats();
        mojestaty.znajdzPrefiksy(bot);

        assertEquals(4, mojestaty.ngram1.liczbaWyst);
        assertEquals(40, mojestaty.ngram1.prawdop);
        assertTrue(mojestaty.ngram1.prefiks.equals("raz"));
        assertEquals(2, mojestaty.ngram1.liczbaSuf);
        assertTrue(mojestaty.ngram1.sufiks.equals("cztery"));
        assertEquals(50, mojestaty.ngram1.sufPrawdop);

        assertEquals(2, mojestaty.ngram2.liczbaWyst);
        assertEquals(20, mojestaty.ngram2.prawdop);
        assertTrue(mojestaty.ngram2.prefiks.equals("dwa"));
        assertEquals(1, mojestaty.ngram2.liczbaSuf);
        assertTrue(mojestaty.ngram2.sufiks.equals("piec")||mojestaty.ngram2.sufiks.equals("raz"));
        assertEquals(50, mojestaty.ngram2.sufPrawdop);

        assertEquals(1, mojestaty.ngram3.liczbaWyst);
        assertEquals(10, mojestaty.ngram3.prawdop);
        //assertTrue(ngram2.prefiks.equals("dwa"));
        assertEquals(1, mojestaty.ngram3.liczbaSuf);
        //assertTrue(ngram2.sufiks.equals("piec")||ngram2.sufiks.equals("raz"));
        assertEquals(100, mojestaty.ngram3.sufPrawdop);
    }
}
