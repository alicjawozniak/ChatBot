import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by Ala on 2015-05-16.
 */

public class Bot {
    public Bot(int rzad) {
        skorowidz = new BazaDanych();
        skorowidz.rzad = rzad;
    }

    public void czytajPlik(String plik) throws FileNotFoundException {
        try {
            new File(plik);
        } catch (Exception e) {
            System.out.print("Nie znaleziono pliku");
            e.printStackTrace();
        }

        Scanner in = new Scanner(new File(plik));
        String prefiks = "";
        String sufiks = "";
        for (int a = 0; a < skorowidz.rzad; ) {
            for (int i = 0; i < skorowidz.rzad - 1 && in.hasNext(); i++) {
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
    public String odpiszSlowo (String prefiks){
        Random rand = new Random();
        String slowo;
        ArrayList<String> temp;
        if (skorowidz.skorowidz.containsKey(prefiks)) {
            slowo = prefiks;
        } else {
            int numerklucza = rand.nextInt(skorowidz.skorowidz.size());
            List<String> klucze = new ArrayList<String>(skorowidz.skorowidz.keySet());
            slowo = klucze.get(numerklucza);
        }
        temp = skorowidz.skorowidz.get(slowo);
        int numer = rand.nextInt(temp.size());
        return temp.get(numer);
    }
    public String odpiszWiadomosc (String wejscie){
        Random rand = new Random();
        int liczba = rand.nextInt(10)+3;
        String ostatnie = odczytajOstatnie(wejscie);
        String zwrotna = ostatnie;

        for (int i=0; i<liczba; i++){
            zwrotna = zwrotna + " " +odpiszSlowo(ostatnie);
            ostatnie = odczytajOstatnie(zwrotna);
        }
        return "Komputer: " + zwrotna;
    }
    public String odczytajOstatnie (String wejscie){
        String[] temp = wejscie.split(" ");
        String zwrotna = "";
        for (int i=0; i<skorowidz.rzad-1; i++){
            zwrotna = zwrotna + temp[temp.length-skorowidz.rzad+1+i];
            if (i<skorowidz.rzad-2)
                zwrotna = zwrotna + " ";
        }
        return zwrotna;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Bot komp = new Bot(3);
        komp.czytajTekst("raz dwa trzy cztery piec szesc siedem", 3);
        System.out.print(komp.odpiszWiadomosc("raz dwa trzy"));
    }

    private BazaDanych skorowidz;
}

class BazaDanych {
    public Map<String, ArrayList<String>> skorowidz = new HashMap<String, ArrayList<String>>();
    public int rzad;

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
