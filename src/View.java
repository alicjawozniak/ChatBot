import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
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
    public final JTextPane czat = new JTextPane();
    public final JTextField wiadomosc = new JTextField(25);
    public ImageIcon jezIkona = new ImageIcon("C:\\Users\\Ala\\IdeaProjects\\ChatBot\\echidna.png");
    public ImageIcon tyIkona = new ImageIcon("C:\\Users\\Ala\\IdeaProjects\\ChatBot\\you.png");

    public Ramka() {
        setSize(520, 300);
        setLocationByPlatform(true);
        setTitle("kolCZATka");
        setLayout(new FlowLayout());
        czat.setEditable(false);
        wiadomosc.setEditable(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JScrollPane czatScrollPane = new JScrollPane(czat);
        czatScrollPane.setSize(400,200);
        JPanel czatPanel = new JPanel();
        JPanel przyciskiPanel = new JPanel();
        //JPanel statsPanel = new JPanel();

        przyciskiPanel.add(wyslijButton);
        przyciskiPanel.add(wczytajButton);

        czatPanel.add(czatScrollPane);
        czatScrollPane.setPreferredSize(new Dimension(500, 200));

        oknoDialog = new RzadDialog(this);
        add(czatScrollPane);
        add(wiadomosc);
        add(przyciskiPanel);
        //add(statsPanel, BorderLayout.SOUTH);
    }

    public void wiadomoscOdBota (String s){
        SimpleAttributeSet botAtrybuty = new SimpleAttributeSet();
        StyleConstants.setAlignment(botAtrybuty, StyleConstants.ALIGN_LEFT);
        StyleConstants.setForeground(botAtrybuty, Color.black);
        czat.insertIcon(jezIkona);
        try {
            czat.getStyledDocument().insertString(czat.getStyledDocument().getLength(), s + "\n", botAtrybuty);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }
    public void wiadomoscOdTy (String s){
        SimpleAttributeSet tyAtrybuty = new SimpleAttributeSet();
        StyleConstants.setAlignment(tyAtrybuty, StyleConstants.ALIGN_RIGHT);
        StyleConstants.setForeground(tyAtrybuty, Color.gray);
        czat.insertIcon(tyIkona);
        try {
            czat.getStyledDocument().insertString(czat.getStyledDocument().getLength(), s + "\n", tyAtrybuty);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
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