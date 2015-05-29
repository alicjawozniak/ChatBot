import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Ala on 2015-05-15.
 */
public class View {
    public static void main(String[] args) {
        Ramka mojaramka = new Ramka();
        mojaramka.setVisible(true);
        mojaramka.statsRamka.setVisible(true);
        mojaramka.statsRamka.dodajSciezke("raz dwa trzy");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mojaramka.statsRamka.dodajSciezke("dwa trzy cztery piec");
        mojaramka.statsRamka.dodajSciezke("dwa");
        mojaramka.statsRamka.dodajSciezke("dwa trzy cztery piec");
        mojaramka.statsRamka.dodajSciezke("dluuuuuuuuuuuuuuuugie slowo");
        mojaramka.statsRamka.dodajSciezke("Krol Karol kupil krolowej karolinie");
        mojaramka.statsRamka.repaint();
    }
}

class Ramka extends JFrame {
    public RzadDialog oknoDialog;
    public TopPiecFrame topPiecFrame;
    public String plikArg;
    public JButton wyslijButton = new JButton("wyslij");
    public JButton wczytajButton = new JButton("wczytaj");
    public JButton topPiecButton = new JButton("Top 5");
    public final JTextPane czat = new JTextPane();
    public final JTextField wiadomosc = new JTextField(25);
    public ImageIcon jezIkona = new ImageIcon("C:\\Users\\Ala\\IdeaProjects\\ChatBot\\echidna.png");
    public ImageIcon tyIkona = new ImageIcon("C:\\Users\\Ala\\IdeaProjects\\ChatBot\\you.png");
    public StatsFrame statsRamka;
    public ImageIcon kroki = new ImageIcon("C:\\Users\\Ala\\IdeaProjects\\ChatBot\\kroki.jpg");

    public Ramka() {
        setSize(540, 300);
        setLocationByPlatform(true);
        setTitle("kolCZATka");
        setLayout(new FlowLayout());
        czat.setEditable(false);
        wiadomosc.setEditable(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        statsRamka = new StatsFrame();
        topPiecFrame = new TopPiecFrame();

        JScrollPane czatScrollPane = new JScrollPane(czat);
        czatScrollPane.setSize(400, 200);
        JPanel czatPanel = new JPanel();
        JPanel przyciskiPanel = new JPanel();
        //JPanel statsPanel = new JPanel();

        przyciskiPanel.add(wyslijButton);
        przyciskiPanel.add(wczytajButton);
        przyciskiPanel.add(topPiecButton);

        czatPanel.add(czatScrollPane);
        czatScrollPane.setPreferredSize(new Dimension(500, 200));

        oknoDialog = new RzadDialog(this);
        add(czatScrollPane);
        add(wiadomosc);
        add(przyciskiPanel);
    }

    public void wiadomoscOdBota(String s) {
        SimpleAttributeSet botAtrybuty = new SimpleAttributeSet();
        StyleConstants.setAlignment(botAtrybuty, StyleConstants.ALIGN_LEFT);
        StyleConstants.setForeground(botAtrybuty, Color.black);
        try {
            czat.setCaretPosition(czat.getStyledDocument().getLength());
            czat.insertIcon(jezIkona);
            czat.getStyledDocument().insertString(czat.getStyledDocument().getLength(), s + "\n", botAtrybuty);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    public void wiadomoscOdTy(String s) {
        SimpleAttributeSet tyAtrybuty = new SimpleAttributeSet();
        StyleConstants.setAlignment(tyAtrybuty, StyleConstants.ALIGN_RIGHT);
        StyleConstants.setForeground(tyAtrybuty, Color.gray);
        try {
            czat.setCaretPosition(czat.getStyledDocument().getLength());
            czat.insertIcon(tyIkona);
            czat.getStyledDocument().insertString(czat.getStyledDocument().getLength(), s + "\n", tyAtrybuty);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    class RzadDialog extends JDialog {
        JSlider rzadSlider;
        int rzadArg;

        public RzadDialog(final JFrame owner) {
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

    public void errorDialog() {
        JOptionPane.showMessageDialog(this, "Baza danych jest pusta, prosze wczytac tekst lub wpisac wiadomosc o wiekszej" +
                " ilosci slow niz rzad n-gramow bazy", "Pusta baza", JOptionPane.ERROR_MESSAGE);
    }

    public int getRzadArg() {
        return oknoDialog.rzadArg;
    }

    public String getPlikArg() {
        return plikArg;
    }

    public class StatsFrame extends JFrame {
        ArrayList<String> listaSciezek;
        int yWspolrzTekst;
        int yWspolrzIkona;
        int xWspolrz;
        Image jezimage;

        public StatsFrame() {
            setSize(750, 350);
            setLocationByPlatform(true);
            setTitle("Statystyki");
            setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            add(new StatsComponent());
            listaSciezek = new ArrayList<String>();
            yWspolrzTekst = 40;
            yWspolrzIkona = 20;
            xWspolrz = 10;
            try {
                jezimage = ImageIO.read(new File("C:\\Users\\Ala\\IdeaProjects\\ChatBot\\kroki.jpg"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void dodajSciezke(String sciezka) {
            this.listaSciezek.add(sciezka);
        }

        public void czyscSciezki() {
            listaSciezek.clear();
            yWspolrzTekst = 40;
            yWspolrzIkona = 20;
            xWspolrz = 10;
        }

        class StatsComponent extends JComponent {
            @Override
            public void paint(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setPaint(Color.white);
                g2.fillRect(0, 0, 1200, 1200);
                g2.setFont(new Font("Dialog", Font.BOLD, 15));
                g2.setColor(Color.black);
                int i = 0;


                for (String sciezka : listaSciezek) {
                    for (String s : sciezka.split(" ")) {
                        g2.drawString(s, xWspolrz, yWspolrzTekst);
                        xWspolrz += (s.length() * 10);
                        if (i < sciezka.split(" ").length - 1) {
                            g2.drawImage(jezimage, xWspolrz, yWspolrzIkona, null);
                            xWspolrz += 50;
                        }
                        i++;
                    }
                    yWspolrzTekst += 40;
                    yWspolrzIkona += 40;
                    xWspolrz = 10;
                    i = 0;
                }
                yWspolrzTekst = 40;
                yWspolrzIkona = 20;
            }
        }
    }

    class TopPiecFrame extends JFrame {
        JPanel panel1;
        JPanel panel2;
        JPanel panel3;
        JPanel panel4;
        JPanel panel5;

        public TopPiecFrame() {
            setSize(950, 200);
            setLocationByPlatform(true);
            setTitle("Top Piec");
            setLayout(new FlowLayout());
            setDefaultCloseOperation(HIDE_ON_CLOSE);

            panel1 = new JPanel();
            add(panel1);
            panel2 = new JPanel();
            add(panel2);
            panel3 = new JPanel();
            add(panel3);
            panel4 = new JPanel();
            add(panel4);
            panel5 = new JPanel();
            add(panel5);
        }

        public JPanel stworzPanel(int miejsce, String prefiks, String sufiks, int prawdop, int sufPrawdop, int liczbaWyst) {
            JPanel panel = new JPanel();
            JLabel miejsceLabel = new JLabel("Miejsce " + miejsce);
            miejsceLabel.setForeground(Color.red);
            panel.add(miejsceLabel);
            panel.add(new JLabel("Prefiks: " + prefiks));
            panel.add(new JLabel("Liczba Wystapien: " + liczbaWyst));
            panel.add(new JLabel("Prawdopodobienstwo wystapienia: " + prawdop + "%"));
            panel.add(new JLabel("Najczestszy sufiks: " + sufiks));
            if (sufPrawdop != 0) panel.add(new JLabel("Prawdopodobienstwo tego sufiksa: " + sufPrawdop + "%"));
            else panel.add(new JLabel("Prawdopodobienstwo tego sufiksa: <0%"));

            return panel;
        }
    }
}