import java.awt.*;

/**
 * Created by Ala on 2015-05-17.
 */
public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                Ramka ramka = new Ramka();
                ramka.statsRamka.setVisible(true);
                ramka.setVisible(true);
                Bot bot = new Bot(ramka.getRzadArg());
                Stats staty = new Stats();
                Controller kontroler = new Controller(bot, ramka, staty);
            }
        });
    }
}
