import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class PreveriZalogoForm extends JPanel{

        private JLabel kateroSkladisce;
        private JLabel imeIzdelka;

        private JComboBox skladisceBox;
        private JComboBox imeIzdelkaBox;

        private JTextArea textArea1;
        private Database db;
        private TextPanel vsebina;

        private JButton okBtn;
        private JButton pocisti;

        public PreveriZalogoForm(TextPanel vsebina) {

            this.vsebina = vsebina;

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
            //skladisceBox.setEditable(true);

            imeIzdelkaBox = new JComboBox();
            imeIzdelkaBox.setPreferredSize(new Dimension(200, 25));
            DefaultComboBoxModel nazivArt = new DefaultComboBoxModel();
            nazivArt.addElement("Vsi izdelki");
            ArrayList<String> vsebinaNazArt = db.getImeIzdelka();
            for(int i = 0; i < vsebinaNazArt.size(); i++){
                nazivArt.addElement(vsebinaNazArt.get(i));
            }
            imeIzdelkaBox.setModel(nazivArt);
            //imeIzdelkaBox.setEditable(true);



            okBtn = new JButton("Izpiši");
            okBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String izdelek = (String)imeIzdelkaBox.getSelectedItem();
                    String skladisce = (String)skladisceBox.getSelectedItem();
                    if(imeIzdelkaBox.getSelectedIndex() == 0) {
                        ArrayList results = new ArrayList(db.izpisiVseIzdelke());
                        for (int i = 0; i < results.size(); i = i + 3) {
                            results.subList(i, i + 3);
                            //System.out.println(results.subList(i, i+3));
                            vsebina.appendTextList(Collections.singleton(results.subList(i, i + 3)));
                            vsebina.appendText("\n");
                        }
                    }else if((db.izpisiIzdelek(izdelek, skladisce).isEmpty())){
                        vsebina.appendText("V tem skladišči ni tega izdelka\n");
                        }
                    else{
                        ArrayList results = new ArrayList(db.izpisiIzdelek(izdelek, skladisce));
                        for (int i = 0; i < results.size(); i = i + 3) {
                            results.subList(i, i + 3);
                            //System.out.println(results.subList(i, i+3));
                            vsebina.appendTextList(Collections.singleton(results.subList(i, i + 3)));
                            vsebina.appendText("\n");
                        }
                    }
                }
            });

            pocisti = new JButton("Počisti");
            pocisti.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                vsebina.clearText();
                }
            });
            textArea1 = new JTextArea();
            textArea1.setPreferredSize(new Dimension(800, 200));

            //Border innerBorder = BorderFactory.createEmptyBorder(15, 15, 15, 15);
            //Border outerBorder = BorderFactory.createEmptyBorder(15, 15, 15, 15);
            //setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

            layoutComponents();
        }

        public void izpisiVsebino(){

            String skladisce = (String)skladisceBox.getSelectedItem();
            String izdelek = (String)imeIzdelkaBox.getSelectedItem();
            int zaloga = 0;

            //če je prazen string, niso izpolnjena vsa polja
            if(skladisce == null || izdelek == null){
                JOptionPane.showMessageDialog(PreveriZalogoForm.this, "Manjkajoči podatki!",
                        "Neizpolnjena polja.", JOptionPane.ERROR_MESSAGE);
            } else{
                zaloga = db.zalogaIzdelka(skladisce, izdelek);
                vsebina.appendText("Zaloga: " + zaloga);
            }

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

            gc.gridx = 1;
            gc.anchor = GridBagConstraints.FIRST_LINE_START;
            gc.insets = new Insets(0, 0, 0, 0);
            add(pocisti, gc);

        }
    }



