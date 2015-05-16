import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Ala on 2015-05-16.
 */

public class Bot {
    public Bot() {
        skorowidz = new BazaDanych();
    }

    public void czytajPlik(String plik, int rzad) throws FileNotFoundException {
        try {
            new File(plik);
        } catch (Exception e) {
            System.out.print("Nie znaleziono pliku");
            e.printStackTrace();
        }

        Scanner in = new Scanner(new File(plik));
        String prefiks = "";
        String sufiks = "";
        for (int a = 0; a < rzad; ) {
            for (int i = 0; i < rzad - 1 && in.hasNext(); i++) {
                if (i>0)
                    prefiks = prefiks + " ";
                prefiks = prefiks + in.next();
            }
            if (in.hasNext()) {
                sufiks = sufiks + in.next();
                skorowidz.dodajngram(prefiks, sufiks);
            } else {
                in.close();
                in = new Scanner(new File(plik));
                for (int j=0; j<a+1; j++)
                    in.next();
                a++;
            }
            prefiks = "";
            sufiks = "";
        }
    }

    public void czytajTekst(String tekst, int rzad) {
        Scanner in = new Scanner(tekst);
        String prefiks = "";
        String sufiks = "";
        for (int a = 0; a < rzad; ) {
            for (int i = 0; i < rzad - 1 && in.hasNext(); i++) {
                if (i>0)
                    prefiks = prefiks + " ";
                prefiks = prefiks + in.next();
            }
            if (in.hasNext()) {
                sufiks = sufiks + in.next();
                skorowidz.dodajngram(prefiks, sufiks);
            } else {
                in.close();
                in = new Scanner(tekst);
                for (int j=0; j<a+1; j++)
                    in.next();
                a++;
            }
            prefiks = "";
            sufiks = "";
        }
    }
    public String odpisz (){

    }



    public static void main(String[] args) throws FileNotFoundException {
        Bot komp = new Bot();
        komp.czytajTekst("raz dwa trzy cztery piec szesc siedem", 4);
        komp.skorowidz.wypisz();
    }

    private BazaDanych skorowidz;
}

class BazaDanych {
    private Map<String, ArrayList<String>> skorowidz = new HashMap<String, ArrayList<String>>();

    public void dodajngram(String pref, String sufiks) {
        if (skorowidz.containsKey(pref)) {
            ArrayList<String> temp = skorowidz.get(pref);
            temp.add(sufiks);
        } else {
            ArrayList<String> temp = new ArrayList<String>();
            temp.add(sufiks);
            skorowidz.put(pref, temp);
        }
    }
    public String wypisz() {
        System.out.print(skorowidz);
        return skorowidz.toString();
    }
}
