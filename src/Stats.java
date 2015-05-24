import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Ala on 2015-05-17.
 */
public class Stats {
    class NgramStats {
        String prefiks;
        int liczbaWyst;
        int prawdop;
        int liczbaSuf;
        String sufiks;
        int sufPrawdop;
        public void wypiszStaty (){
            System.out.print("pref: "+ prefiks+" liczbawyst: "+liczbaWyst+" prawdop: "+prawdop+" liczbaSuf: "+liczbaSuf+
            " sufiks: "+sufiks+" sufprawdop: "+sufPrawdop + "\n");
        }
    }
    public Stats(){
        ngram1 = new NgramStats();
        ngram2 = new NgramStats();
        ngram3 = new NgramStats();
        ngram4 = new NgramStats();
        ngram5 = new NgramStats();
    }
    NgramStats ngram1;
    NgramStats ngram2;
    NgramStats ngram3;
    NgramStats ngram4;
    NgramStats ngram5;
    public void znajdzPrefiksy (Bot bot) {
        int x;
        for (String klucz : bot.skorowidz.skorowidz.keySet()) {
            x = bot.skorowidz.skorowidz.get(klucz).size();
            if (x > ngram1.liczbaWyst){
                podmienNgramy(ngram5, ngram4);
                podmienNgramy(ngram4, ngram3);
                podmienNgramy(ngram3, ngram2);
                podmienNgramy(ngram2, ngram1);
                wrzucNgram(bot, ngram1, klucz);
            }
            else if (x > ngram2.liczbaWyst) {
                podmienNgramy(ngram5, ngram4);
                podmienNgramy(ngram4, ngram3);
                podmienNgramy(ngram3, ngram2);
                wrzucNgram(bot, ngram2, klucz);
            }
            else if (x>ngram3.liczbaWyst){
                podmienNgramy(ngram5, ngram4);
                podmienNgramy(ngram4, ngram3);
                wrzucNgram(bot, ngram3, klucz);
            }
            else if (x>ngram4.liczbaWyst){
                podmienNgramy(ngram5, ngram4);
                wrzucNgram(bot, ngram4, klucz);
            }
            else if (x>ngram5.liczbaWyst){
                wrzucNgram(bot, ngram5, klucz);
            }
        }
        znajdzPrawdop(bot, ngram1);
        znajdzPrawdop(bot, ngram2);
        znajdzPrawdop(bot, ngram3);
        znajdzPrawdop(bot, ngram4);
        znajdzPrawdop(bot, ngram5);
    }
    public void wrzucNgram (Bot bot, NgramStats ngram, String klucz){
        ngram.prefiks = klucz;
        ngram.liczbaWyst = bot.skorowidz.skorowidz.get(klucz).size();

        int a = 1, tempWyst = 0;
        String maxSuf = "", tempSuf1 = "", tempSuf2 = "";
        ArrayList<String> temp = bot.skorowidz.skorowidz.get(klucz);
        Iterator<String> iterator = temp.iterator();
        Iterator<String> iterator2;
        while (iterator.hasNext()){
            a = 1;
            tempSuf1 = iterator.next();
            iterator2 = iterator;
            while (iterator2.hasNext()){
                tempSuf2 = iterator2.next();
                if (tempSuf1.equals(tempSuf2))
                    a++;
            }
            if (a > tempWyst){
                maxSuf = tempSuf1;
            }
        }

        ngram.liczbaSuf = a;
        ngram.sufiks = maxSuf;
        ngram.sufPrawdop = 100*a/ngram.liczbaWyst;
    }
    public void podmienNgramy (NgramStats cel, NgramStats zrodlo){
        cel.liczbaWyst = zrodlo.liczbaWyst;
        cel.sufPrawdop = zrodlo.sufPrawdop;
        cel.sufiks = zrodlo.sufiks;
        cel.prefiks = zrodlo.prefiks;
        cel.liczbaSuf = zrodlo.liczbaSuf;
        cel.prawdop = zrodlo.prawdop;
    }
    public void znajdzPrawdop (Bot bot, NgramStats ngram){
        ngram.prawdop = 100*ngram.liczbaWyst/bot.skorowidz.rozmiar;
    }

    public static void main(String[] args){
        Bot mojbot = new Bot(2);
        mojbot.czytajTekst("trzy raz cztery dwa piec raz dwa raz szesc raz cztery");
        Stats mojestaty = new Stats();
        mojestaty.znajdzPrefiksy(mojbot);
        mojestaty.ngram1.wypiszStaty();
        mojestaty.ngram2.wypiszStaty();
        mojestaty.ngram3.wypiszStaty();
        mojestaty.ngram4.wypiszStaty();
        mojestaty.ngram5.wypiszStaty();
    }

}
