import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.ArrayList;

public class DodajSkladisceFormPanel extends JPanel {

    private JLabel skladiscaLabel;
    private JLabel dodajSkladisce;

    private JTextField dodajSkladisceText;
    private JComboBox skladisceBox;

    private JLabel kupciVBazi;
    private JComboBox kupecVBaziBox;
    private JLabel kupci;
    private JTextField kupciBox;

    private JTextArea textArea1;
    private Database db;
    private TextPanel vsebina;

    private JButton btnDodajKupca;
    private JButton btnDodajSkladisce;
    private JButton btnDodajDobavitelja;

    private JLabel obstojeciDobavitelji;
    private JComboBox obstojeciDobaviteljiBox;
    private JLabel noviDobavitelji;
    private JTextField noviDobaviteljiBox;

    public DodajSkladisceFormPanel(TextPanel vsebina) {

        this.vsebina = vsebina;

        //textArea1 = new JTextArea();
        //textArea1.setPreferredSize(new Dimension(800, 200));

        Dimension dim = getPreferredSize();
        dim.width = 450;
        setPreferredSize(dim);

        db = new Database();

        // Labels in njihove nastavitve
        skladiscaLabel = new JLabel("Skladišča v bazi");
        dodajSkladisce = new JLabel("Dodaj skladišče");
        dodajSkladisceText = new JTextField();
        dodajSkladisceText.setPreferredSize(new Dimension(200, 25));

        kupciVBazi = new JLabel("Kupci v bazi: ");
        kupci = new JLabel("Dodaj kupca: ");
        kupciBox = new JTextField();
        kupciBox.setPreferredSize(new Dimension(200, 25));

        obstojeciDobavitelji = new JLabel("Dobavitelji v bazi: ");
        noviDobavitelji = new JLabel("Dodaj dobavitelja: ");
        noviDobaviteljiBox = new JTextField();
        noviDobaviteljiBox.setPreferredSize(new Dimension(200, 25));

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

        kupecVBaziBox = new JComboBox();
        kupecVBaziBox.setPreferredSize(new Dimension(200, 25));
        DefaultComboBoxModel kupecModel = new DefaultComboBoxModel();
        ArrayList<String> vsebinaKupec = db.getKupec();
        for(int i = 0; i < vsebinaKupec.size(); i++){
            kupecModel.addElement(vsebinaKupec.get(i));
        }
        kupecVBaziBox.setModel(kupecModel);
        kupecVBaziBox.setEditable(false);

        obstojeciDobaviteljiBox = new JComboBox();
        DefaultComboBoxModel dobModel = new DefaultComboBoxModel();
        ArrayList<String> vsebinaJCBox = db.getImeDobaviteljev();
        for (int i = 0; i < vsebinaJCBox.size(); i++) {
            dobModel.addElement(vsebinaJCBox.get(i));
        }
        obstojeciDobaviteljiBox.setModel(dobModel);
        obstojeciDobaviteljiBox.setEditable(false);
        obstojeciDobaviteljiBox.setPreferredSize(new Dimension(200, 25));

        btnDodajKupca = new JButton("Dodaj kupca");
        btnDodajSkladisce = new JButton("Dodaj skladišče");
        btnDodajDobavitelja = new JButton("Dodaj dobavitelja");

        btnDodajDobavitelja.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    String dobavitelj = noviDobaviteljiBox.getText();
                    int dobaviteljObstaja = db.countJeVBaziString(dobavitelj, "Dobavitelji", "imeDobavitelja");
                    if (dobaviteljObstaja == 0) {
                        db.dodajImeDobavitelja(dobavitelj);
                        vsebina.appendText("Dobavitelj uspešno dodan\n");
                        JOptionPane.showMessageDialog(DodajSkladisceFormPanel.this, "Dobavitelj je uspešno dodan.", "Izdelek dodan.", JOptionPane.INFORMATION_MESSAGE);
                    } else if (dobaviteljObstaja != 1) {
                        JOptionPane.showMessageDialog(DodajSkladisceFormPanel.this, "Dobavitelj že obstaja!", "Napaka pri vnosu.", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(DodajSkladisceFormPanel.this, "Dobavitelj ste napačno besedo ali znak. Poskusite ponovno.");
                    }
                }
        });

        btnDodajSkladisce.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Če skladišče ne obstaja se izpiše spodaj, če ne pa se izpiše SKladišče že obstaja
                String skladisce = dodajSkladisceText.getText();
                int upObstaja = db.countJeVBaziString(skladisce, "Skladisca", "imeSkladisca");
                if(upObstaja == 0){
                    db.dodajImeSkladisca(skladisce);
                    vsebina.appendText("Skladišče uspešno dodano\n");
                    JOptionPane.showMessageDialog(DodajSkladisceFormPanel.this, "Skladišče je uspešno dodano.", "Skladišče dodano.", JOptionPane.INFORMATION_MESSAGE);
                    //setVisible(false);
                } else if (upObstaja != 0){
                    JOptionPane.showMessageDialog(DodajSkladisceFormPanel.this, "Skladišče že obstaja!", "Napaka pri vnosu.", JOptionPane.ERROR_MESSAGE);
                } else{
                    JOptionPane.showMessageDialog(DodajSkladisceFormPanel.this, "Vnesli ste napačno besedo ali znak. Poskusite ponovno.");
                }


            }
        });

        btnDodajKupca.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    String kupec = kupciBox.getText();
                    int dobaviteljObstaja = db.countJeVBaziString(kupec, "Kupci", "imeKupca");
                    if (dobaviteljObstaja == 0) {
                        db.dodajKupca(kupec);
                        vsebina.appendText("Kupec uspešno dodan\n");
                        JOptionPane.showMessageDialog(DodajSkladisceFormPanel.this, "Izdelek je uspešno dodan.", "Izdelek dodan.", JOptionPane.INFORMATION_MESSAGE);
                    } else if (dobaviteljObstaja != 1) {
                        JOptionPane.showMessageDialog(DodajSkladisceFormPanel.this, "Izdelek že obstaja!", "Napaka pri vnosu.", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(DodajSkladisceFormPanel.this, "Vnesli ste napačno besedo ali znak. Poskusite ponovno.");
                    }
                }
        });




        //Border innerBorder = BorderFactory.createEmptyBorder(15, 15, 15, 15);
        //Border outerBorder = BorderFactory.createEmptyBorder(15, 15, 15, 15);
        //setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

        layoutComponents();
    }

    public void izpisiSkladisce(){
        String skladisce = dodajSkladisceText.getText();
        vsebina.appendText(skladisce);
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
        add(skladiscaLabel, gc);

        gc.gridx = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        add(skladisceBox, gc);

        gc.gridx = 2;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;

        ///////////Second row ////////////////

        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.05;

        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.LINE_START;
        add(dodajSkladisce, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(dodajSkladisceText, gc);

        gc.gridx = 2;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;

        /////// Next row //////////////////

        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.05;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.insets = new Insets(0, 0, 0, 0);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(btnDodajSkladisce, gc);

        ////////////// Next row  ///////////////

        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.05;

        gc.gridx = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 5);
        add(kupciVBazi, gc);

        gc.gridx = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        add(kupecVBaziBox, gc);

        gc.gridx = 2;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;

        ///////////Second row ////////////////

        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.05;

        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.LINE_START;
        add(kupci, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(kupciBox, gc);

        /////// Next row //////////////////

        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.05;

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(btnDodajKupca, gc);

        ////////////// Next row  ///////////////

        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.05;

        gc.gridx = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 5);
        add(obstojeciDobavitelji, gc);

        gc.gridx = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        add(obstojeciDobaviteljiBox, gc);

        ///////////Second row ////////////////

        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.05;

        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.LINE_START;
        add(noviDobavitelji, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(noviDobaviteljiBox, gc);

        /////// Next row //////////////////

        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 1;

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(btnDodajDobavitelja, gc);
    }
}

