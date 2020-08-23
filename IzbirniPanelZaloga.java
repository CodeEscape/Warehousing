import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IzbirniPanelZaloga extends JPanel { //// sem ga neredil, če bi želel oblikovat border na izbirnem panelu za skladišče/zalogo

        private JButton btnPeveriZalogo;
        private JButton skladisce;

        private TextPanel vsebina;
        private PreveriZalogoForm preveriZalogo;

        public IzbirniPanelZaloga() {

            setLayout(new GridLayout(1, 3, 50, 50));

            vsebina = new TextPanel();
            preveriZalogo = new PreveriZalogoForm(vsebina);
            //sz = new FormPanelSZ();

            btnPeveriZalogo = new JButton("Preveri zalogo");

            btnPeveriZalogo.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    new PreveriZalogoForm(vsebina);
                }
            });

            Border innerBorder = BorderFactory.createEmptyBorder(15, 15, 15, 15);
            Border outerBorder = BorderFactory.createEmptyBorder(15, 15, 15, 15);
            setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

            add(preveriZalogo);

            //setVisible(false);
        }


    }


