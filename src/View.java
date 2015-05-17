import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;

/**
 * Created by Ala on 2015-05-15.
 */
public class View {
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                Ramka mojaramka = new Ramka();
                mojaramka.setVisible(true);
                mojaramka.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                System.out.print("plik " + mojaramka.getPlikArg()+" n: "+mojaramka.getRzadArg());
                if(mojaramka.getPlikArg() != null){
                    mojaramka.wypiszWiadomosc(mojaramka.getPlikArg());
                }
            }
        });
    }
}
class Ramka extends JFrame{
    public RzadDialog oknoDialog;
    public String plikArg;
    public Ramka() {
        setSize(500, 300);
        setLocationByPlatform(true);
        setTitle("ChatBot");
        setLayout(new BorderLayout());
        czat.setEditable(false);
        wiadomosc.setEditable(true);

        JButton wyslijButton = new JButton("wyslij");
        JButton wczytajButton = new JButton("wczytaj");

        JScrollPane czatPanel = new JScrollPane(czat);
        JPanel przyciskiPanel = new JPanel();
        JPanel statsPanel = new JPanel();

        przyciskiPanel.add(wyslijButton);
        przyciskiPanel.add(wczytajButton);

        wyslijButton.addActionListener(new ActionListener() {
                                           @Override
                                           public void actionPerformed(ActionEvent e) {
                                               wypiszWiadomosc("Ty: " + wiadomosc.getText() + "\n");
                                           }
                                       }
        );
        wczytajButton.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                JFileChooser chooser = new JFileChooser();
                                                chooser.showOpenDialog(null);
                                                plikArg = chooser.getSelectedFile().getAbsolutePath();
                                                try {
                                                    FileReader fr = new FileReader(plikArg);
                                                }
                                                catch (Exception exc){
                                                    exc.printStackTrace();
                                                }
                                            }
                                        }
        );
        oknoDialog = new RzadDialog(this);
        add(czatPanel, BorderLayout.NORTH);
        add(wiadomosc, BorderLayout.WEST);
        add(przyciskiPanel, BorderLayout.EAST);
        add(statsPanel, BorderLayout.SOUTH);
    }
    final JTextArea czat = new JTextArea(10, 20);
    final JTextField wiadomosc = new JTextField(25);

    public void wypiszWiadomosc(String s){
        czat.append(s);
    }
    class RzadDialog extends JDialog{
        JSlider rzadSlider;
        int rzadArg;
        public RzadDialog(JFrame owner){
            super(owner, "Rzad n-gramow", true);
            setLocationByPlatform(true);
            setSize(400, 150);

            setLayout(new BorderLayout());

            rzadSlider = new JSlider(2, 10, 2);
            rzadSlider.setMajorTickSpacing(1);
            rzadSlider.setPaintLabels(true);
            rzadSlider.setPaintTicks(true);
            rzadSlider.setSnapToTicks(true);
            rzadSlider.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {

                }
            });
            JButton okButton = new JButton("Ok");
            okButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    rzadArg = rzadSlider.getValue();
                    System.out.print(rzadArg);
                    setVisible(false);
                }
            });
            JPanel panel = new JPanel();

            panel.add(okButton);
            add(new JLabel("Wybierz rzad n-gramow w bazie"), BorderLayout.NORTH);
            add(panel, BorderLayout.SOUTH);
            add(rzadSlider, BorderLayout.CENTER);
            setVisible(true);
        }
    }
    public int getRzadArg(){
        return oknoDialog.rzadArg;
    }
    public String getPlikArg(){
        return plikArg;
    }
}
class Parametry {
    public int getRzad() {
        return rzad;
    }

    public void setRzad(int rzad) {
        this.rzad = rzad;
    }

    private int rzad;

    public String getPlik() {
        return plik;
    }

    public void setPlik(String plik) {
        this.plik = plik;
    }

    private String plik;

    public void pobierz(String nazwa, int rzad){
        this.plik = nazwa;
        this.rzad = rzad;
    }

    @Override
    public String toString() {
        return ("plik: "+plik+" rzad: "+rzad);
    }
}