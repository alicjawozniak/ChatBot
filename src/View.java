import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Ala on 2015-05-15.
 */
public class View {
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                //Ramka mojaramka = new Ramka();
                //mojaramka.setVisible(true);
                //mojaramka.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                WczytywanieRamka ram = new WczytywanieRamka();
                ram.setVisible(true);
                ram.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            }
        });
    }
}
class Ramka extends JFrame{
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

                                            }
                                        }
        );
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
}
class Parametry {
    public int rzad;
    public String plik;

    public void pobierz(String nazwa, int rzad){
        this.plik = nazwa;
        this.rzad = rzad;
    }
}
class WczytywanieRamka extends JFrame{
    public WczytywanieRamka(){
        setSize(600, 500);
        setLayout(new BorderLayout());
        setLocationByPlatform(true);
        setTitle("Wczytaj");

        JLabel wczytywanieLabel = new JLabel("Wybierz rzad ngramow i plik tekstowy");

        chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter("Pliki tekstowe", "txt"));
        chooser.addActionListener(new OtworzPlikListener());

        final JSlider rzadSlider = new JSlider(2, 10, 2);
        rzadSlider.setMajorTickSpacing(1);
        rzadSlider.setPaintLabels(true);
        rzadSlider.setPaintTicks(true);
        rzadSlider.setSnapToTicks(true);
        rzadSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider tempSlider = (JSlider) e.getSource();
                rzad = tempSlider.getValue();
                System.out.print(rzad);
            }
        });

        add(wczytywanieLabel, BorderLayout.NORTH);
        add(rzadSlider, BorderLayout.CENTER);
        add(chooser, BorderLayout.SOUTH);

    }
    public String nazwa;
    public int rzad;
    JFileChooser chooser;
    public class OtworzPlikListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            nazwa = chooser.getSelectedFile().getAbsolutePath();
        }
    }

}