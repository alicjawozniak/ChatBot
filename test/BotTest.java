import junit.framework.TestCase;

import java.util.ArrayList;

/**
 * Created by Ala on 2015-05-22.
 */
public class BotTest extends TestCase {
    public void testCzytajTekst(){
        Bot bot = new Bot(3);
        bot.czytajTekst("raz dwa trzy cztery piec szesc siedem");

        assertEquals(5, bot.skorowidz.rozmiar);
        assertTrue(bot.skorowidz.skorowidz.containsKey("raz dwa"));
        assertTrue(bot.skorowidz.skorowidz.containsKey("dwa trzy"));
        assertTrue(bot.skorowidz.skorowidz.containsKey("trzy cztery"));
        assertTrue(bot.skorowidz.skorowidz.containsKey("cztery piec"));
        assertTrue(!bot.skorowidz.skorowidz.containsKey("szesc siedem"));

        ArrayList<String> lista = new ArrayList<String>();
        lista.add("trzy");
        assertTrue(bot.skorowidz.skorowidz.get("raz dwa").equals(lista));
    }
    public void testOdpiszWiadomosc(){
        Bot komp = new Bot(3);
        komp.czytajTekst("raz dwa trzy cztery piec szesc siedem");
        System.out.print(komp.odpiszWiadomosc("raz dwa trzy"));

        String wiad = komp.odpiszWiadomosc("raz dwa trzy");
        assertTrue(wiad.startsWith("dwa trzy cztery"));
    }
}