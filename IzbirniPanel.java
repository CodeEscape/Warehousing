import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IzbirniPanel extends JPanel {

    private JButton dobavnicaBtn;
    private JButton dodajVBazoBtn;
    private JButton prevzemnicaBtn;

    //private FormPanelSZ sz;

    public IzbirniPanel() {

        setLayout(new GridLayout(1, 3, 50, 50));

        dobavnicaBtn = new JButton("Dobavnica");
        //sprejemZalog.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        dodajVBazoBtn = new JButton("Dodaj v bazo");
        prevzemnicaBtn = new JButton("Prevzemnica");

        //sz = new FormPanelSZ();

        dobavnicaBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new DobavnicaFrame();
            }
        });

        dodajVBazoBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new SkladisceIzbiraFrame();
            }
        });

        prevzemnicaBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new PrevzemnicaFrame();
            }
        });

        Border innerBorder = BorderFactory.createEmptyBorder(15, 15, 15, 15);
        Border outerBorder = BorderFactory.createEmptyBorder(15, 15, 15, 15);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

        //add(Box.createVerticalStrut(8));
        add(dobavnicaBtn);
        add(dodajVBazoBtn);
        add(prevzemnicaBtn);

        //setVisible(false);
    }


}
