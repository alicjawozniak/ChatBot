import junit.framework.TestCase;

import java.io.FileNotFoundException;
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
    public void testCzytajPlik (){
        Bot bot = new Bot(4);

        //test na pliku
        try {
            bot.czytajPlik("C:\\Users\\Ala\\IdeaProjects\\ChatBot\\nicze.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //test na nieistniejacym pliku
        try {
            bot.czytajPlik("brakpliku");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //test na pustym pliku
    }
    public void testStaty(){
        Bot bot = new Bot(2);
        bot.czytajTekst("raz dwa trzy raz cztery dwa piec raz dwa raz szesc");

        assertTrue(bot.maSufiksy("raz"));
        assertTrue(bot.maSufiksy("trzy"));

        assertTrue(!bot.maSufiksy("szesc"));
    }
}