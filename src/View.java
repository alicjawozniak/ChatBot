import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

/**
 * Created by Ala on 2015-05-15.
 */
public class View {
    public static void main(String[] args) throws FileNotFoundException {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                Ramka mojaramka = new Ramka();
                mojaramka.setVisible(true);
                mojaramka.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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

        add(wiadomosc, BorderLayout.WEST);
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
        add(przyciskiPanel, BorderLayout.EAST);
    }
    final JTextArea czat = new JTextArea(10, 20);
    final JTextField wiadomosc = new JTextField(30);

    public void wypiszWiadomosc(String s){
        czat.append(s);
    }
}
