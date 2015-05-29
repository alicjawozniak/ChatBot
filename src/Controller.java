import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Created by Ala on 2015-05-20.
 */
public class Controller {
    Bot bot;
    Ramka ramka;
    Stats staty;

    public Controller(Bot bot, Ramka ramka, Stats staty) {
        this.bot = bot;
        this.ramka = ramka;
        this.staty = staty;
        ramka.wczytajButton.addActionListener(new WczytajListener());
        ramka.wyslijButton.addActionListener(new WyslijListener());
        ramka.topPiecButton.addActionListener(new TopPiecListener());
    }

    class WczytajListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileFilter(new FileNameExtensionFilter("Pliki tekstowe", "txt"));
            chooser.showOpenDialog(null);
            String plikArg = chooser.getSelectedFile().getAbsolutePath();
            try {
                new FileReader(plikArg);
                new Scanner(plikArg);
            } catch (Exception exc) {
                exc.printStackTrace();
            }
            try {
                bot.czytajPlik(plikArg);
                System.out.print("wczytano\n");
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }

        }
    }

    class WyslijListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!ramka.wiadomosc.getText().equals("")) {
                ramka.wiadomoscOdTy("Ty: " + ramka.wiadomosc.getText());
                bot.czytajTekst(ramka.wiadomosc.getText());
                if (bot.isEmpty())
                    ramka.errorDialog();
                else {
                    ramka.statsRamka.czyscSciezki();
                    String wiadtemp = bot.odpiszWiadomosc(ramka.wiadomosc.getText());
                    ramka.wiadomoscOdBota("Kolczatka: " + wiadtemp);
                    //ladowanie statystyk
                    String temp = "";
                    int przelacznikWypisano = 0;
                    for (String s: wiadtemp.split(" ")){
                        if (bot.maSufiksy(s)) {
                            temp = temp + " " + s;
                            przelacznikWypisano = 0;
                        }
                        else{
                            temp = temp + " " +s;
                            ramka.statsRamka.dodajSciezke(temp.substring(1));
                            temp = "";
                            przelacznikWypisano = 1;
                        }
                    }
                    if (przelacznikWypisano == 0)
                        ramka.statsRamka.dodajSciezke(temp.substring(1));
                    ramka.statsRamka.repaint();
                }
            }
        }
    }
    class TopPiecListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!bot.isEmpty()) {
                staty.czyscNgramy();
                staty.znajdzTopPiec(bot);
                int rozmiar = bot.skorowidz.skorowidz.size();

                ramka.topPiecFrame.remove(ramka.topPiecFrame.panel1);
                ramka.topPiecFrame.panel1 = ramka.topPiecFrame.stworzPanel(1, staty.ngram1.prefiks, staty.ngram1.sufiks, staty.ngram1.prawdop,
                        staty.ngram1.sufPrawdop, staty.ngram1.liczbaWyst);
                ramka.topPiecFrame.add(ramka.topPiecFrame.panel1);

                if(rozmiar>1){ramka.topPiecFrame.remove(ramka.topPiecFrame.panel2);
                ramka.topPiecFrame.panel2 = ramka.topPiecFrame.stworzPanel(2, staty.ngram2.prefiks, staty.ngram2.sufiks, staty.ngram2.prawdop,
                        staty.ngram2.sufPrawdop, staty.ngram2.liczbaWyst);
                ramka.topPiecFrame.add(ramka.topPiecFrame.panel2);}

                if(rozmiar>2){ramka.topPiecFrame.remove(ramka.topPiecFrame.panel3);
                ramka.topPiecFrame.panel3 = ramka.topPiecFrame.stworzPanel(3, staty.ngram3.prefiks, staty.ngram3.sufiks, staty.ngram3.prawdop,
                        staty.ngram3.sufPrawdop, staty.ngram3.liczbaWyst);
                ramka.topPiecFrame.add(ramka.topPiecFrame.panel3);}

                if(rozmiar>3){ramka.topPiecFrame.remove(ramka.topPiecFrame.panel4);
                ramka.topPiecFrame.panel4 = ramka.topPiecFrame.stworzPanel(4, staty.ngram4.prefiks, staty.ngram4.sufiks, staty.ngram4.prawdop,
                        staty.ngram4.sufPrawdop, staty.ngram4.liczbaWyst);
                ramka.topPiecFrame.add(ramka.topPiecFrame.panel4);}

                if(rozmiar>4){ramka.topPiecFrame.remove(ramka.topPiecFrame.panel5);
                ramka.topPiecFrame.panel5 = ramka.topPiecFrame.stworzPanel(5, staty.ngram5.prefiks, staty.ngram5.sufiks, staty.ngram5.prawdop,
                        staty.ngram5.sufPrawdop, staty.ngram5.liczbaWyst);
                ramka.topPiecFrame.add(ramka.topPiecFrame.panel5);}

                ramka.topPiecFrame.setVisible(true);
            }
        }
    }
}
