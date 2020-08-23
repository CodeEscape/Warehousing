import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PrevzemnicaPanel extends JPanel {

    private JLabel kupec;
    private JLabel datumPrevzemnice;
    private JLabel stevilkaPrevzemnice;
    private JLabel kateroSkladisce;
    private JLabel fazaNarocila;
    //private JLabel sifra;
    private JLabel imeIzdelka;
    private JLabel kolicina;

    private JComboBox kupecBox;
    private JDateChooser datumPrevzemniceBox;
    private JTextField stevilkaPrevzemniceBox;
    private JComboBox skladisceBox;
    private JComboBox fazaNarocilaBox;
    //private JComboBox sifraArtiklaBox;
    private JComboBox imeIzdelkaBox;
    private JTextField kolicinaBox;

    //private JTextArea textArea1;
    private Database db;
    private TextPanel vsebina2;


    private JButton pregledPrevzemnic;
    private JButton dodajIzdelekBtn;
    private JButton natisni;

    public PrevzemnicaPanel(TextPanel vsebina2) {

        db = new Database();

        this.vsebina2 = vsebina2;
        //textArea1 = new JTextArea();
        //textArea1.setPreferredSize(new Dimension(800, 200));

        pregledPrevzemnic = new JButton("Vpogled v pretekle prevzemnice");

        Dimension dim = getPreferredSize();
        dim.width = 480;
        setPreferredSize(dim);

        // Labels in njihove nastavitve
        kupec = new JLabel("Kupec");
        datumPrevzemnice = new JLabel("Datum naročila");
        stevilkaPrevzemnice = new JLabel("Številka naročila");
        //datumIzdaje = new JLabel("Datum izdaje in številka");
        kateroSkladisce = new JLabel("Iz katerega skladišča gre proizvod");
        //sifra = new JLabel("Šifra");
        fazaNarocila = new JLabel("V kakšni fazi je naročilo");
        kolicina = new JLabel("Količina");
        imeIzdelka = new JLabel("Ime izdelka");

        //Comboboxes and their settings

        kupecBox = new JComboBox();
        kupecBox.setPreferredSize(new Dimension(200, 25));
        DefaultComboBoxModel kupecModel = new DefaultComboBoxModel();
        ArrayList<String> vsebinaKupec = db.getKupec();
        for(int i = 0; i < vsebinaKupec.size(); i++){
            kupecModel.addElement(vsebinaKupec.get(i));
        }
        kupecBox.setModel(kupecModel);
        kupecBox.setEditable(false);

        datumPrevzemniceBox = new JDateChooser();
        datumPrevzemniceBox.setPreferredSize(new Dimension(200, 25));
        datumPrevzemniceBox.setDateFormatString("yyyy-MM-dd");

        stevilkaPrevzemniceBox = new JTextField();
        stevilkaPrevzemniceBox.setPreferredSize(new Dimension(200, 25));
        stevilkaPrevzemniceBox.setEditable(false);
        stevilkaPrevzemniceBox.setText(String.valueOf(db.steviloPrevzemnice()));

        skladisceBox = new JComboBox();
        skladisceBox.setPreferredSize(new Dimension(200, 25));
        DefaultComboBoxModel skladisceModel = new DefaultComboBoxModel();
        ArrayList<String> vsebinaSkladisce = db.getImeSkladisca();
        for(int i = 0; i < vsebinaSkladisce.size(); i++){
            skladisceModel.addElement(vsebinaSkladisce.get(i));
        }
        skladisceBox.setModel(skladisceModel);
        //skladisceBox.setEditable(true);
/*
        sifraArtiklaBox = new JComboBox();
        sifraArtiklaBox.setPreferredSize(new Dimension(200, 25));
        DefaultComboBoxModel stArtModel = new DefaultComboBoxModel();
        ArrayList<String> vsebinaStArt = db.getSifraIzdelka();
        for(int i = 0; i < vsebinaStArt.size(); i++){
            stArtModel.addElement(vsebinaStArt.get(i));
        }
        sifraArtiklaBox.setModel(stArtModel);
        sifraArtiklaBox.setEditable(true);
*/
        fazaNarocilaBox = new JComboBox();
        fazaNarocilaBox.setPreferredSize(new Dimension(200, 25));
        DefaultComboBoxModel fazaNarocilaModel = new DefaultComboBoxModel();
        fazaNarocilaModel.addElement("Na poti");
        fazaNarocilaModel.addElement("V odpremi");
        fazaNarocilaModel.addElement("Dostavljeno");
        fazaNarocilaBox.setModel(fazaNarocilaModel);
        //fazaNarocilaBox.setEditable(true);

        imeIzdelkaBox = new JComboBox();
        imeIzdelkaBox.setPreferredSize(new Dimension(200, 25));
        DefaultComboBoxModel nazivIzdelkaModel = new DefaultComboBoxModel();
        ArrayList<String> vsebinaImeArt = db.getImeIzdelka();
        for(int i = 0; i < vsebinaImeArt.size(); i++){
            nazivIzdelkaModel.addElement(vsebinaImeArt.get(i));
        }
        imeIzdelkaBox.setModel(nazivIzdelkaModel);
        //imeIzdelkaBox.setEditable(true);

        kolicinaBox = new JTextField();
        kolicinaBox.setPreferredSize(new Dimension(200, 25));

        dodajIzdelekBtn = new JButton("Dodaj izdelek");
        natisni = new JButton("Shrani in izpiši");

        natisni.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vsebina2.clearText();
                izpisiVsebino();
                shraniVBazo();

            }
        });

        dodajIzdelekBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                odvzemiKolicino();
                dodajIzdelkeVPrevzemnico();

                String imeIzdelka = (String)imeIzdelkaBox.getSelectedItem();
                int kolicina = Integer.parseInt(kolicinaBox.getText());

                vsebina2.appendText("Ime izdelka: " + imeIzdelka + "\n");
                vsebina2.appendText("Količina: " + kolicina + "\n");
            }
        });

        pregledPrevzemnic.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PregledPrevzemniceFrame();
            }
        });

        //Border innerBorder = BorderFactory.createEmptyBorder(15, 15, 15, 15);
        //Border outerBorder = BorderFactory.createEmptyBorder(15, 15, 15, 15);
        //setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

        layoutComponents();
    }

    public void izpisiVsebino(){
        String kupec = (String)kupecBox.getSelectedItem();
        System.out.println(kupec);

        Date datumDobavnice = datumPrevzemniceBox.getDate();
        String pattern = "yyyy-MM-dd";
        DateFormat df = new SimpleDateFormat(pattern);
        String datumNarocilaAsString = df.format(datumDobavnice);

        String skladisce = (String)skladisceBox.getSelectedItem();
        String fazaNarocila = (String)fazaNarocilaBox.getSelectedItem();
        int stevilkaDobavnice = Integer.parseInt(stevilkaPrevzemniceBox.getText());
        String imeIzdelka = (String)imeIzdelkaBox.getSelectedItem();
        int kolicina = Integer.parseInt(kolicinaBox.getText());

        vsebina2.appendText("Kupec: " + kupec + "\n");
        vsebina2.appendText("Datum dobavnice: " + datumNarocilaAsString + "\n");
        vsebina2.appendText("Iz katerega skladišča: " + skladisce + "\n");
        vsebina2.appendText("Faza naročila: " + fazaNarocila + "\n");
        vsebina2.appendText("Številka dobavnice: " + stevilkaDobavnice + "\n");
        vsebina2.appendText("Ime izdelka: " + imeIzdelka + "\n");
        vsebina2.appendText("Količina: " + kolicina + "\n");
    }

    public void shraniVBazo(){
       dodajPrevzemnico();
       dodajIzdelkeVPrevzemnico();
       odvzemiKolicino();
    }

    private void dodajPrevzemnico() {
        Date datumDobavnice = datumPrevzemniceBox.getDate();
        String pattern = "yyyy-MM-dd";
        DateFormat df = new SimpleDateFormat(pattern);
        String datumNarocilaAsString = df.format(datumDobavnice);

        int stevilkaPrevzemnice = Integer.parseInt(stevilkaPrevzemniceBox.getText());

        //idSkladisce
        String skladisce = (String)skladisceBox.getSelectedItem();
        int idSkladisca = db.tujId("Skladisca", "imeSkladisca", skladisce);

        //idKupec
        String kupec = (String)kupecBox.getSelectedItem();
        int idKupca = db.tujId("Kupci", "imeKupca", kupec);

        db.dodajPrevzemnico(datumNarocilaAsString, stevilkaPrevzemnice, idKupca, idSkladisca);
    }

    private void dodajIzdelkeVPrevzemnico() {
        String imeIzdelka = ((String)imeIzdelkaBox.getSelectedItem());
        int idIzdelka = db.tujId("Izdelki", "imeIzdelka", imeIzdelka);

        int stevilkaPrevzemnice = Integer.parseInt(stevilkaPrevzemniceBox.getText());
        int idPrevzemnice = db.tujId("Prevzemnica", "stevilkaPrevzemnice", stevilkaPrevzemnice);

        db.dodajIzdelkeVPrevzemnico(idPrevzemnice, idIzdelka);
    }

    private void odvzemiKolicino() {
        String skladisce = (String) skladisceBox.getSelectedItem();
        String imeIzdelka = (String)imeIzdelkaBox.getSelectedItem();
        //int zaloga = Integer.parseInt((String)trenutnaZalogaBox.getSelectedItem()); ///mogoče določit, da prikaže zalogo, glede na izbran izdelek in šifro izdelka
        int kolicina = Integer.parseInt(kolicinaBox.getText());
        int novaKolicina = db.zalogaIzdelka(skladisce, imeIzdelka) - kolicina;
        db.spremeniKolicino(imeIzdelka, skladisce, novaKolicina);
        System.out.println("Nova količina: " + novaKolicina);
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
        add(kupec, gc);

        gc.gridx = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        add(kupecBox, gc);

        gc.gridx = 2;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        //add(btnKupec, gc);

        ///////////Second row ////////////////

        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.05;

        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.LINE_START;
        add(datumPrevzemnice, gc);

        gc.gridx = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        add(datumPrevzemniceBox, gc);

        gc.gridx = 2;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        //add(btnDatumNarocila, gc);

        /////// Next row //////////////////

        gc.gridy++;//da se samo poveÄŤuje in da je bolj pregledno, prej je bilo v vsakem napisano 1, 2, 3...

        gc.weightx = 1;
        gc.weighty = 0.05;

        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.LINE_START;
        //add(datumIzdaje, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        //add(datumIzdajeBox, gc);

        gc.gridx = 2;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        //add(btnDatumIzdaje, gc);

        /////// Next row //////////////////

        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.05;

        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.LINE_START;
        add(kateroSkladisce, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(skladisceBox, gc);

        gc.gridx = 2;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        //add(btnSkladisce, gc);

        /////// Next row //////////////////

        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.05;

        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.LINE_START;
        add(fazaNarocila, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(fazaNarocilaBox, gc);

        /////// Next row //////////////////

        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.05;

        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.LINE_START;
        add(stevilkaPrevzemnice, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(stevilkaPrevzemniceBox, gc);

        gc.gridx = 2;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        //add(btnSifraIzdelka, gc);

        /////// Next row //////////////////

        gc.gridy++;

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

        gc.gridx = 2;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        //add(btnImeIzdelka, gc);

        /////// Next row //////////////////

        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.05;

        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.LINE_START;
        add(kolicina, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(kolicinaBox, gc);

        gc.gridx = 2;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        //add(btnKolicina, gc);

        /////// Next row //////////////////

        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 1.0;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.insets = new Insets(0, 0, 0, 0);
        add(dodajIzdelekBtn, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(natisni, gc);

        /////// Next row //////////////////

        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 1.0;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(pregledPrevzemnic, gc);

    }
}
