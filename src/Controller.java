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

    public Controller(Bot bot, Ramka ramka) {
        this.bot = bot;
        this.ramka = ramka;
        ramka.wczytajButton.addActionListener(new WczytajListener());
        ramka.wyslijButton.addActionListener(new WyslijListener());
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
                else
                    ramka.wiadomoscOdBota("Kolczatka: " + bot.odpiszWiadomosc(ramka.wiadomosc.getText()));
            }
        }
    }
}
