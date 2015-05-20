import javax.swing.*;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by Ala on 2015-05-17.
 */
public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                Ramka ramka = new Ramka();
                ramka.setVisible(true);
                Bot bot = new Bot(ramka.getRzadArg());
                Controller kontroler = new Controller(bot, ramka);
            }
        });
    }
}
