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
    }
    public void wrzucNgram (Bot bot, NgramStats ngram, String klucz){
        ngram.prefiks = klucz;
        ngram.liczbaWyst = bot.skorowidz.skorowidz.get(klucz).size();
        ngram.prawdop = 100*ngram.liczbaWyst/bot.skorowidz.skorowidz.size();

        int a = 0, i=0;
        String tempSuf0 = "";
        String tempSuf2;
        String tempSuf1;
        int tempWyst = 0;
        ArrayList<String> temp = bot.skorowidz.skorowidz.get(klucz);
        Iterator<String> iterator = temp.iterator();
        while (iterator.hasNext()){
            tempSuf1 = iterator.next();
            Iterator<String> iterator2 = iterator;
            while (iterator2.hasNext()){
                tempSuf2 = iterator2.next();
                if (tempSuf1.equals(tempSuf2))
                    a++;
            }
            if (a > tempWyst){
                tempSuf0 = tempSuf1;
                a = 0;
            }
            i++;
        }

        ngram.liczbaSuf = i;
        ngram.sufiks = tempSuf0;
        ngram.sufPrawdop = 100*a/i;
    }
    public void podmienNgramy (NgramStats cel, NgramStats zrodlo){
        cel.liczbaWyst = zrodlo.liczbaWyst;
        cel.sufPrawdop = zrodlo.sufPrawdop;
        cel.sufiks = zrodlo.sufiks;
        cel.prefiks = zrodlo.prefiks;
        cel.liczbaSuf = zrodlo.liczbaSuf;
        cel.prawdop = zrodlo.prawdop;
    }

    public static void main(String[] args){
        Bot mojbot = new Bot(2);
        mojbot.czytajTekst("trzy raz cztery dwa piec raz dwa raz szesc");
        Stats mojestaty = new Stats();
        mojestaty.znajdzPrefiksy(mojbot);
        mojestaty.ngram1.wypiszStaty();
        mojestaty.ngram2.wypiszStaty();
        mojestaty.ngram3.wypiszStaty();
        mojestaty.ngram4.wypiszStaty();
        mojestaty.ngram5.wypiszStaty();
    }

}
