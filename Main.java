import javax.swing.*;

public class Main {

    private PrvaStran prva;

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new PrvaStran();
            }
        });
    }
}
