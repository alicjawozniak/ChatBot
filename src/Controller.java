import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;

/**
 * Created by Ala on 2015-05-20.
 */
public class Controller {
    Bot bot;
    Ramka ramka;
    public Controller(Bot bot, Ramka ramka){
        this.bot = bot;
        this.ramka = ramka;
        ramka.wczytajButton.addActionListener(new WczytajListener());
        ramka.wyslijButton.addActionListener(new WyslijListener());
    }
    class WczytajListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser chooser = new JFileChooser();
            chooser.showOpenDialog(null);
            String plikArg = chooser.getSelectedFile().getAbsolutePath();
            try {
                FileReader fr = new FileReader(plikArg);
            }
            catch (Exception exc){
                exc.printStackTrace();
            }
        }
    }
    class WyslijListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            ramka.wypiszWiadomosc("Ty: " + ramka.wiadomosc.getText());
        }
    }
}
