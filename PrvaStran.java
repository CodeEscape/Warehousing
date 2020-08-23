import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrvaStran {

    private IzbirniPanel izbPanel;
    private PrijavaUporabnika prijavaUp;
    private Database db;

    private Meni meni;
    private JButton prijavaBtn;
    private JFrame frame = new JFrame();
    //private boolean jePrijavljen = false;

    public PrvaStran() {

        frame.setLayout(new BorderLayout());
        frame.setSize(800, 600);

        izbPanel = new IzbirniPanel();
        meni = new Meni();
        db = new Database();

        // izbPanel.setVisible(false);
        prijavaBtn = new JButton("Prijava");
        prijavaUp = new PrijavaUporabnika(frame, this);

        frame.setJMenuBar(meni.createMenuBar(frame));

        prijavaBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                prijavaUp.setVisible(true);

            }
        });

        ((Container) frame).add(prijavaBtn, BorderLayout.NORTH);
/*
        if (jePrijavljen) {
            frame.add(izbPanel, BorderLayout.CENTER);
        }
*/
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public PrijavaUporabnika getPrijavaUp() {
        return prijavaUp;
    }

    public void setPrijavaUp(PrijavaUporabnika prijavaUp) {
        this.prijavaUp = prijavaUp;
    }

    public void prikaziIzbirniPanel() {
        izbPanel.setVisible(true);
    }

    public void uporabnikSeJePrijavil() {
        System.out.print("Tukaj");
        frame.add(izbPanel, BorderLayout.CENTER);
        SwingUtilities.updateComponentTreeUI(frame);
    }
}
