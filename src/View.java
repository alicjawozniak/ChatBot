import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Ala on 2015-05-15.
 */
public class View {
    public static void main(String[] args) {
                Ramka mojaramka = new Ramka();
                mojaramka.setVisible(true);
    }
}
class Ramka extends JFrame{
    public RzadDialog oknoDialog;
    public String plikArg;
    public JButton wyslijButton = new JButton("wyslij");
    public JButton wczytajButton = new JButton("wczytaj");
    public final JTextArea czat = new JTextArea(10, 20);
    public final JTextField wiadomosc = new JTextField(25);

    public Ramka() {
        setSize(520, 300);
        setLocationByPlatform(true);
        setTitle("ChatBot");
        setLayout(new BorderLayout());
        czat.setEditable(false);
        wiadomosc.setEditable(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JScrollPane czatPanel = new JScrollPane(czat);
        JPanel przyciskiPanel = new JPanel();
        JPanel statsPanel = new JPanel();

        przyciskiPanel.add(wyslijButton);
        przyciskiPanel.add(wczytajButton);

        oknoDialog = new RzadDialog(this);
        add(czatPanel, BorderLayout.NORTH);
        add(wiadomosc, BorderLayout.WEST);
        add(przyciskiPanel, BorderLayout.EAST);
        add(statsPanel, BorderLayout.SOUTH);
    }

    public void wypiszWiadomosc(String s){
        czat.append(s+"\n");
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
    public void errorDialog (){
        JOptionPane.showMessageDialog(this, "Baza danych jest pusta, prosze wczytac tekst lub wpisac wiadomosc o wiekszej" +
                " ilosci slow niz rzad n-gramow bazy", "Pusta baza", JOptionPane.ERROR_MESSAGE);
    }
    public int getRzadArg(){
        return oknoDialog.rzadArg;
    }
    public String getPlikArg(){
        return plikArg;
    }
}