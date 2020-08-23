import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SkladisceIzbiraFrame extends JFrame {

    private JButton btnPreveriZalogo;
    private JButton btnDodajZalogo;
    private JButton btnDodajSkladisce;

    private IzbirniPanelZaloga izbiraPanelZaloga;
    private Database db;

    private Meni meni;

    public SkladisceIzbiraFrame() {

        setLayout(new GridLayout(1, 3, 50, 50));
        setSize(600, 400);

        izbiraPanelZaloga = new IzbirniPanelZaloga();
        meni = new Meni();
        db = new Database();

        btnPreveriZalogo = new JButton("Preveri zalogo");
        btnDodajZalogo = new JButton("Dodaj zalogo");
        btnDodajSkladisce = new JButton("Dodaj v bazo");
        btnDodajSkladisce.setAlignmentY(1);

        btnPreveriZalogo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new PreveriZalogoFrame();
            }
        });

        btnDodajZalogo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new DodajZalogoFrame();
            }
        });

        btnDodajSkladisce.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new DodajSkladisceFrame();
            }
        });

        setJMenuBar(meni.createMenuBar(getParent()));

        add(btnPreveriZalogo);
        add(btnDodajZalogo);
        add(btnDodajSkladisce);
        setVisible(true);
    }
}


