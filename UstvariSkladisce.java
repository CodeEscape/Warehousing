import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class UstvariSkladisce extends JPanel {

        private JLabel kateroSkladisce;
        private JLabel imeIzdelka;

        private JComboBox skladisceBox;
        private JComboBox imeIzdelkaBox;

        private JTextArea textArea1;
        private Database db;
        private TextPanel vsebina2;

        private JButton okBtn;

        public UstvariSkladisce() {

            this.vsebina2 = vsebina2;

            Dimension dim = getPreferredSize();
            dim.width = 450;
            setPreferredSize(dim);

            db = new Database();

            // Labels in njihove nastavitve
            kateroSkladisce = new JLabel("Skladišče");
            imeIzdelka = new JLabel("Ime izdelka");

            //Comboboxes and their settings
            skladisceBox = new JComboBox();
            skladisceBox.setPreferredSize(new Dimension(200, 25));
            DefaultComboBoxModel skl = new DefaultComboBoxModel();
            ArrayList<String> vsebinaSkl = db.getImeSkladisca();
            for(int i = 0; i < vsebinaSkl.size(); i++){
                skl.addElement(vsebinaSkl.get(i));
            }
            skladisceBox.setModel(skl);
            skladisceBox.setEditable(true);

            imeIzdelkaBox = new JComboBox();
            imeIzdelkaBox.setPreferredSize(new Dimension(200, 25));
            DefaultComboBoxModel nazivArt = new DefaultComboBoxModel();
            ArrayList<String> vsebinaNazArt = db.getImeIzdelka();
            for(int i = 0; i < vsebinaNazArt.size(); i++){
                nazivArt.addElement(vsebinaNazArt.get(i));
            }
            imeIzdelkaBox.setModel(nazivArt);
            imeIzdelkaBox.setEditable(true);

            okBtn = new JButton("Ok");

            textArea1 = new JTextArea();
            textArea1.setPreferredSize(new Dimension(800, 200));

            //Border innerBorder = BorderFactory.createEmptyBorder(15, 15, 15, 15);
            //Border outerBorder = BorderFactory.createEmptyBorder(15, 15, 15, 15);
            //setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

            layoutComponents();
        }

        public void izpisiVsebino(){

            String skladisce = (String) skladisceBox.getSelectedItem();

            vsebina2.appendText("Nabavna vrednost: " + skladisce + "\n");
        }

        public void layoutComponents() {

            setLayout(new GridBagLayout());

            GridBagConstraints gc = new GridBagConstraints();

            ////////First row /////////////////

            gc.gridy = 0;

            gc.weightx = 1;
            gc.weighty = 0.05;

            gc.gridx = 0;
            gc.fill = GridBagConstraints.NONE;
            gc.anchor = GridBagConstraints.LINE_START;
            gc.insets = new Insets(0, 0, 0, 5);
            add(kateroSkladisce, gc);

            gc.gridx = 1;
            gc.insets = new Insets(0, 0, 0, 0);
            gc.anchor = GridBagConstraints.LINE_START;
            add(skladisceBox, gc);

            ///////////Second row ////////////////

            gc.gridy++;//da se samo poveÄŤuje in da je bolj pregledno, prej je bilo v vsakem napisano 1, 2, 3...

            gc.weightx = 1;
            gc.weighty = 0.05;

            gc.gridx = 0;
            gc.insets = new Insets(0, 0, 0, 5);
            gc.anchor = GridBagConstraints.LINE_START;
            add(imeIzdelka, gc);

            gc.gridx = 1;
            gc.anchor = GridBagConstraints.LINE_START;
            gc.insets = new Insets(0, 0, 0, 0);
            add(imeIzdelkaBox, gc);

            /////// Next row //////////////////
            gc.gridy++;

            gc.weightx = 1;
            gc.weighty = 1.0;

            gc.gridx = 0;
            gc.anchor = GridBagConstraints.FIRST_LINE_END;
            gc.insets = new Insets(0, 0, 0, 0);
            add(okBtn, gc);

        }
    }





