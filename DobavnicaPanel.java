import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DobavnicaPanel extends JPanel {

    private JLabel dobavitelj;
    private JLabel skladisce;
    private JLabel datumNarocila;
    //private JLabel poslovnaEnota;
    private JLabel datumDostave;
    private JLabel sifraIzdelka;
    private JLabel imeIzdelka;
    private JLabel trenutnaZaloga;
    private JLabel kolicina;
    private JLabel cena;
    private JLabel nabavnik;
    private JLabel stevilkaDobavnice;
    private JLabel datumDobavnice;
    private JLabel datumPrevzema;
    //private JLabel stevilkaPrevzemnice;

    private JComboBox dobaviteljBox;
    //private JComboBox poslovnaEnotaBox;
    private JDateChooser datumNarocilaBox;
    private JComboBox skladisceBox;
    private JDateChooser datumDostaveBox;
    private JComboBox sifraIzdelkaBox;
    private JComboBox imeIzdelkaBox;
    private JComboBox trenutnaZalogaBox;
    private JTextField kolicinaBox;
    private JComboBox cenaBox;
    private JComboBox nabavnikBox;
    private JTextField stevilkaDobavniceBox;
    private JDateChooser datumDobavniceBox;
    private JDateChooser datumPrevzemaBox;
    //private JComboBox stevilkaPrevzemniceBox;

    private JTextArea textArea0;
    /*
        private JButton odstrani;
        private JButton odstrani1;
    */
    private JButton izpisi;
    private JButton izbrisi;
    private JButton dodajIzdelekBtn;

    private JButton preglejDobavnico;

    private Database db;

    private TextPanel vsebina;

    private PrijavaUporabnika uporabnik;

    private JDateChooser datumBox;
    private JLabel datum;

    public DobavnicaPanel(Database db, TextPanel vsebina) {

        Dimension dim = getPreferredSize();
        dim.width = 500; //popravit dimenzijo
        setPreferredSize(dim);

        this.db = db;

        this.vsebina = vsebina;

        uporabnik = new PrijavaUporabnika();

        datumBox = new JDateChooser();
        datumBox.setPreferredSize(new Dimension(200, 25));
        datumBox.setDateFormatString("yyyy-MM-dd");

        // Labels in njihove nastavitve
        dobavitelj = new JLabel("Dobavitelj");
        skladisce = new JLabel("Skladišče");
        //datumNarocila = new JLabel("Datum naročila");
        //poslovnaEnota = new JLabel("Poslovna enota, ki naroča");
        datumDostave = new JLabel("Predviden datum dostave");
        sifraIzdelka = new JLabel("Šifra izdelka");
        imeIzdelka = new JLabel("Ime izdelka");
        //trenutnaZaloga = new JLabel("Trenutna zaloga");
        kolicina = new JLabel("Količina, ki jo naročamo");
        cena = new JLabel("Dobaviteljeva cena");
        nabavnik = new JLabel("Kdo naroča (nabavnik)");
        stevilkaDobavnice = new JLabel("Številka dobavnice");
        datumDobavnice = new JLabel("Datum dobavnice");
        datumPrevzema = new JLabel("Datum prevzema");
        //stevilkaPrevzemnice = new JLabel("Številka prevzemnice");

        //Comboboxes and their settings
        dobaviteljBox = new JComboBox();
        DefaultComboBoxModel dobModel = new DefaultComboBoxModel();
        ArrayList<String> vsebinaJCBox = db.getImeDobaviteljev();
        for (int i = 0; i < vsebinaJCBox.size(); i++) {
            dobModel.addElement(vsebinaJCBox.get(i));
        }
        dobaviteljBox.setModel(dobModel);
        dobaviteljBox.setEditable(false);
        dobaviteljBox.setPreferredSize(new Dimension(200, 25));
/*
        poslovnaEnotaBox = new JComboBox();
        DefaultComboBoxModel poslovnaEnotaModel = new DefaultComboBoxModel();
        ArrayList<String> vsebinaPoslovneEnote = db.getPoslovnaEnota();
        for(int i = 0; i < vsebinaPoslovneEnote.size(); i++){
            poslovnaEnotaModel.addElement(vsebinaPoslovneEnote.get(i));
        }
        poslovnaEnotaBox.setModel(poslovnaEnotaModel);
        poslovnaEnotaBox.setEditable(true);
        poslovnaEnotaBox.setPreferredSize(new Dimension(200, 25));

        datumNarocilaBox = new JDateChooser();
        datumNarocilaBox.setPreferredSize(new Dimension(200, 25));
        datumNarocilaBox.setDateFormatString("yyyy-MM-dd");
*/
        skladisceBox = new JComboBox();
        skladisceBox.setPreferredSize(new Dimension(200, 25));
        DefaultComboBoxModel skl = new DefaultComboBoxModel();
        ArrayList<String> vsebinaSkl = db.getImeSkladisca();
        for (int i = 0; i < vsebinaSkl.size(); i++) {
            skl.addElement(vsebinaSkl.get(i));
        }
        skladisceBox.setModel(skl);
        //skladisceBox.setEditable(true);

        datumDostaveBox = new JDateChooser();
        datumDostaveBox.setPreferredSize(new Dimension(200, 25));
        datumDostaveBox.setDateFormatString("yyyy-MM-dd");

        sifraIzdelkaBox = new JComboBox();
        sifraIzdelkaBox.setPreferredSize(new Dimension(200, 25));
        DefaultComboBoxModel stArt = new DefaultComboBoxModel();
        ArrayList<String> vsebinaStArt = db.getSifraIzdelka();
        for (int i = 0; i < vsebinaStArt.size(); i++) {
            stArt.addElement(vsebinaStArt.get(i));
        }
        sifraIzdelkaBox.setModel(stArt);
        //sifraArtiklaBox.setEditable(true);

        imeIzdelkaBox = new JComboBox();
        imeIzdelkaBox.setPreferredSize(new Dimension(200, 25));
        DefaultComboBoxModel nazivArt = new DefaultComboBoxModel();
        ArrayList<String> vsebinaNazArt = db.getImeIzdelka();
        for (int i = 0; i < vsebinaNazArt.size(); i++) {
            nazivArt.addElement(vsebinaNazArt.get(i));
        }
        imeIzdelkaBox.setModel(nazivArt);
        //nazivArtiklaBox.setEditable(true);

        /*     TO VKLJUČIT NAZAJ??
        trenutnaZalogaBox = new JComboBox();
        trenutnaZalogaBox.setPreferredSize(new Dimension(200, 25));
        DefaultComboBoxModel trZaloga = new DefaultComboBoxModel();
        ArrayList<String> vsebinaTrZaloga = db.getTrZaloga();
        for(int i = 0; i < vsebinaTrZaloga.size(); i++){
            trZaloga.addElement(vsebinaTrZaloga.get(i));
        }
        trenutnaZalogaBox.setModel(trZaloga);
        //trenutnaZalogaBox.setEditable(true);
    */

        kolicinaBox = new JTextField();
        //kolicinaBox = new JFormattedTextField(createFormatter("#"));
        kolicinaBox.setPreferredSize(new Dimension(200, 25));

        cenaBox = new JComboBox();
        cenaBox.setPreferredSize(new Dimension(200, 25));
        DefaultComboBoxModel cenaModel = new DefaultComboBoxModel();
        ArrayList<String> vsebinaCene = db.getCena();
        for (int i = 0; i < vsebinaCene.size(); i++) {
            cenaModel.addElement(vsebinaCene.get(i));
        }
        cenaBox.setModel(cenaModel);
        //cenaBox.setEditable(true);

        nabavnikBox = new JComboBox();
        nabavnikBox.setPreferredSize(new Dimension(200, 25));
        DefaultComboBoxModel nabavnikModel = new DefaultComboBoxModel();
        nabavnikModel.addElement(uporabnik.user);
        nabavnikBox.setModel(nabavnikModel);
        /*
        DefaultComboBoxModel nabavnikModel = new DefaultComboBoxModel();
        ArrayList<String> vsebinaNabavnik = db.getNabavnik();
        for(int i = 0; i < vsebinaNabavnik.size(); i++){
            nabavnikModel.addElement(vsebinaNabavnik.get(i));
        }
        nabavnikBox.setModel(nabavnikModel);
        //nabavnikBox.setEditable(true);

        stevilkaDobavniceBox = new JComboBox();
        stevilkaDobavniceBox.setPreferredSize(new Dimension(200, 25));
        DefaultComboBoxModel stevilkaDobavniceModel = new DefaultComboBoxModel();
        ArrayList<String> stevilkaDobavnice = db.getStevilkaDobavnice();
        for (int i = 0; i < stevilkaDobavnice.size(); i++) {
            stevilkaDobavniceModel.addElement(stevilkaDobavnice.get(i));
        }
        stevilkaDobavniceBox.setModel(stevilkaDobavniceModel);
        stevilkaDobavniceBox.setEditable(true);
*/

        stevilkaDobavniceBox = new JTextField();
        stevilkaDobavniceBox.setPreferredSize(new Dimension(200, 25));

        datumDobavniceBox = new JDateChooser();
        datumDobavniceBox.setPreferredSize(new Dimension(200, 25));
        datumDobavniceBox.setDateFormatString("yyyy-MM-dd");

        datumPrevzemaBox = new JDateChooser();
        datumPrevzemaBox.setPreferredSize(new Dimension(200, 25));
        datumPrevzemaBox.setDateFormatString("yyyy-MM-dd");
/*
        stevilkaPrevzemniceBox = new JComboBox();
        stevilkaPrevzemniceBox.setPreferredSize(new Dimension(200, 25));
        DefaultComboBoxModel stevilkaPrevzemniceModel = new DefaultComboBoxModel();
        ArrayList<String> stevilkaPrevzemnice = db.getStevilkaPrevzemnice();
        for(int i = 0; i < stevilkaPrevzemnice.size(); i++){
            stevilkaPrevzemniceModel.addElement(stevilkaPrevzemnice.get(i));
        }
        stevilkaPrevzemniceBox.setModel(stevilkaPrevzemniceModel);
        stevilkaPrevzemniceBox.setEditable(true);

        ///////// Gumbi  ////////////
        btnDodajDobavitelja = new JButton("Dodaj");
        btnDodajDobavitelja.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String dobavitelj = (String)dobaviteljBox.getSelectedItem();
                db.dodajImeDobavitelja(dobavitelj);
            }
        });

*/
        //odstrani = new JButton("Odstrani");
        izbrisi = new JButton("Zbrisi");
        int height = izbrisi.getHeight();
        izbrisi.setSize(izbrisi.getWidth(), height);
        dodajIzdelekBtn = new JButton("Dodaj izdelek");
        dodajIzdelekBtn.setSize(dodajIzdelekBtn.getWidth(), height);

        //okBtn1.setSize(50, 25);
        izpisi = new JButton("Izpisi in shrani v bazo ");
        izbrisi.setSize(izpisi.getWidth(), height);
        preglejDobavnico = new JButton("Vpogled v dobavnice");

        textArea0 = new JTextArea();
        textArea0.setPreferredSize(new Dimension(800, 200));

        izbrisi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                vsebina.clearText();
                //izpisiVsebino();
                //shraniVBazo();
                //db.spremembaBoxa("dobaviteljBox", "dobaviteljBox.getSelectedItem()", 1);//to je samo za primer
            }
        });

        izpisi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vsebina.clearText();
                izpisiVsebino();
                shraniVBazo();
            }
        });

        dodajIzdelekBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodajKolicino();
                dodajVIzdelkeVDobavnico();

                String imeIzdelka = (String)imeIzdelkaBox.getSelectedItem();
                int kolicina = Integer.parseInt(kolicinaBox.getText());

                vsebina.appendText("Ime izdelka: " + imeIzdelka + "\n");
                vsebina.appendText("Količina: " + kolicina + "\n");
            }
        });

        preglejDobavnico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PregledDobavniceFrame();
            }
        });

        //Border innerBorder = BorderFactory.createEmptyBorder(15, 15, 15, 15);
        //Border outerBorder = BorderFactory.createEmptyBorder(15, 15, 15, 15);
        //setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

        layoutComponents();
    }
    /*
    public MaskFormatter createFormatter(String s) {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter(s);
        } catch (java.text.ParseException exc) {
            System.err.println("formatter is bad: " + exc.getMessage());
            System.exit(-1);
        }
        return formatter;
    }
    */
    public void izpisiVsebino() {
        String dobavitelj = (String) dobaviteljBox.getSelectedItem();
        String skladisce = (String) skladisceBox.getSelectedItem();

        Date datumDostave = datumDostaveBox.getDate();
        String pattern = "yyyy-MM-dd";
        DateFormat df = new SimpleDateFormat(pattern);
        String datumDostaveString = df.format(datumDostave);

        int sifraIzdelka = Integer.parseInt((String) sifraIzdelkaBox.getSelectedItem());
        String imeIzdelka = (String) imeIzdelkaBox.getSelectedItem();

        int kolicina = Integer.parseInt(kolicinaBox.getText());
        int cena = Integer.parseInt((String)cenaBox.getSelectedItem());
        String nabavnik = (String) nabavnikBox.getSelectedItem();
        int stevilkaDobavnice = Integer.parseInt((String)stevilkaDobavniceBox.getText());

        Date datumDobavnice = datumDobavniceBox.getDate();
        String datumDobavniceString = df.format(datumDobavnice);

        //String poslovnaEnota = (String)poslovnaEnotaBox.getSelectedItem();
        //int datumNa = Integer.parseInt(String.valueOf(datumNarocilaBox.getDate()));
        //int datumDo = Integer.parseInt(datumDostaveBox.getText());
        //////int zaloga = Integer.parseInt((String)trenutnaZalogaBox.getSelectedItem());
        //int stevilkaDobavnice = Integer.parseInt((String)stevilkaDobavniceBox.getSelectedItem());
        //int datumDobavnice = Integer.parseInt(String.valueOf(datumDobavniceBox.getDate()));
        //int datumPrevzema = Integer.parseInt(datumPrevzemaBox.getText());
        //int stevilkaPrevzemnice = Integer.parseInt((String)stevilkaPrevzemniceBox.getSelectedItem());

        vsebina.appendText("Dobavitelj: " + dobavitelj + "\n");
        vsebina.appendText("Skladišče kjer je ali kam gre blago: " + skladisce + "\n");
        vsebina.appendText("Datum dostave: " + datumDostaveString + "\n");
        vsebina.appendText("Šifra izdelka: " + sifraIzdelka + "\n");
        vsebina.appendText("Ime izdelka: " + imeIzdelka + "\n");
        vsebina.appendText("Količina, ki jo naročamo: " + kolicina + "\n");
        vsebina.appendText("Cena izdelka: " + cena + "\n");
        vsebina.appendText("Kdo nabavlja blago: " + nabavnik + "\n");
        vsebina.appendText("Številka sobavnice: " + stevilkaDobavnice + "\n");
        vsebina.appendText("Datum dobavnice: " + datumDobavniceString + "\n");
    }

    public StringBuilder podatki(){
        StringBuilder builder = null;
        String dobavitelj = (String) dobaviteljBox.getSelectedItem();
        String skladisce = (String) skladisceBox.getSelectedItem();

        Date datumDostave = datumDostaveBox.getDate();
        String pattern = "yyyy-MM-dd";
        DateFormat df = new SimpleDateFormat(pattern);
        String datumDostaveString = df.format(datumDostave);

        String sifraIzdelka = ((String) sifraIzdelkaBox.getSelectedItem());
        String imeIzdelka = (String) imeIzdelkaBox.getSelectedItem();
        String kolicina = (kolicinaBox.getText());
        String cena = ((String)cenaBox.getSelectedItem());
        String nabavnik = (String) nabavnikBox.getSelectedItem();
        String stevilkaDobavnice = ((String)stevilkaDobavniceBox.getText());

        Date datumDobavnice = datumDobavniceBox.getDate();
        String datumDobavniceString = df.format(datumDobavnice);

        builder.append(dobavitelj);
        System.out.println(builder);
        builder.append(skladisce);
        builder.append(datumDostaveString);
        builder.append(sifraIzdelka);
        builder.append(imeIzdelka);
        builder.append(kolicina);
        builder.append(cena);
        builder.append(nabavnik);
        builder.append(stevilkaDobavnice);
        builder.append(datumDobavniceString);

        return builder;
    }

    public void shraniVBazo() {
        dodajKolicino();
        dodajDobavnico();
        dodajVIzdelkeVDobavnico();
    }

    private void dodajVIzdelkeVDobavnico() {
        String imeIzdelka = ((String)imeIzdelkaBox.getSelectedItem());
        int idIzdelka = db.tujId("Izdelki", "imeIzdelka", imeIzdelka);

        int stevilkaDobavnice = Integer.parseInt(stevilkaDobavniceBox.getText());
        int idDobavnice = db.tujId("Dobavnica", "stevilkaDobavnice", stevilkaDobavnice);
        System.out.println(stevilkaDobavnice);
        System.out.println(idDobavnice);


        db.dodajIzdelkeVDobavnici(idDobavnice, idIzdelka);
    }

    private void dodajDobavnico() {
        Date datumDostave = datumDostaveBox.getDate();
        Date datumDobavnice = datumDobavniceBox.getDate();
        int stevilkaDobavnice = Integer.parseInt((String)stevilkaDobavniceBox.getText());
        String pattern = "yyyy-MM-dd";
        DateFormat df = new SimpleDateFormat(pattern);
        String datumDostaveAsString = df.format(datumDostave);
        System.out.println(datumDostaveAsString);
        String datumDobavniceAsString = df.format(datumDobavnice);
        System.out.println(datumDobavniceAsString);

        //idSkladisce
        String skladisce = (String)skladisceBox.getSelectedItem();
        int idSkladisca = db.tujId("Skladisca", "imeSkladisca", skladisce);

        //idDobavitelja
        String dobavitelj = (String) dobaviteljBox.getSelectedItem();
        int idDobavitelja = db.tujId("Dobavitelji", "imeDobavitelja", dobavitelj);

        //idUporabnika
        String uporabnik = (String) nabavnikBox.getSelectedItem();
        int idUporabinika = db.tujId("Uporabniki", "imeUporabnika", uporabnik);

        db.dodajDobavnico(datumDobavniceAsString, datumDostaveAsString, stevilkaDobavnice, idSkladisca, idDobavitelja, idUporabinika);
    }
/*
    private void dodajDatumDostave() {
        Date datumDostave = datumBox.getDate();
        String pattern = "yyyy-MM-dd";
        //Date date = new SimpleDateFormat("dd/MM/yyyy").parse(datumDostave);
        DateFormat df = new SimpleDateFormat(pattern);
        String dateAsString = df.format(datumDostave);
        System.out.println(dateAsString + " AAAAAAAAAA");
        db.dodajDatumDostave(dateAsString);

    }
*/
    private void dodajStevilkoDobavnice() {
        //int stevilkaDobavnice = Integer.parseInt((String) stevilkaDobavniceBox.getSelectedItem());
    }

    private void dodajCeno() {
        if(((String)cenaBox.getSelectedItem()) == ""){
            //če je string
        }
        //int cena = Integer.parseInt((String)cenaBox.getSelectedItem());
        //db.dodajCeno(cena);
    }

    private void dodajKolicino() {
        String skladisce = (String) skladisceBox.getSelectedItem();
        String nazivA = (String) imeIzdelkaBox.getSelectedItem();
        //int zaloga = Integer.parseInt((String)trenutnaZalogaBox.getSelectedItem()); ///mogoče določit, da prikaže zalogo, glede na izbran izdelek in šifro izdelka
        int kolicina = Integer.parseInt(kolicinaBox.getText());
        int novaKolicina = kolicina + db.zalogaIzdelka(skladisce, nazivA);
        db.spremeniKolicino(nazivA, skladisce, novaKolicina);
        System.out.println("Nova količina: " + novaKolicina);
    }
/*
    private void dodajDobavitelja() {
        String dobavitelj = (String) dobaviteljBox.getSelectedItem();
        int dobaviteljObstaja = db.countJeVBaziString(dobavitelj, "Dobavitelji", "imeDobavitelja");
        if (dobaviteljObstaja == 0) {
            db.dodajImeDobavitelja(dobavitelj);
            JOptionPane.showMessageDialog(FormPanelSZ.this, "Izdelek je uspešno dodan.", "Izdelek dodan.", JOptionPane.INFORMATION_MESSAGE);
        } else if (dobaviteljObstaja != 1) {
            JOptionPane.showMessageDialog(FormPanelSZ.this, "Izdelek že obstaja!", "Napaka pri vnosu.", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(FormPanelSZ.this, "Vnesli ste napačno besedo ali znak. Poskusite ponovno.");
        }
    }

    private void dodajDatumDobavnice() {
        int datumDobavnice = datumDobavnice = Integer.parseInt(String.valueOf(datumDobavniceBox.getDate())); //Če je star datum dat napako
        db.dodajDatumDobavnice(datumDobavnice);
    }
*/
    public void dodajIzdelek() {
        int sifraA = Integer.parseInt((String) sifraIzdelkaBox.getSelectedItem());
        String nazivA = (String) imeIzdelkaBox.getSelectedItem();
        int kolicina = Integer.parseInt(kolicinaBox.getText());
        int cena = Integer.parseInt((String) cenaBox.getSelectedItem());

        vsebina.appendText("Ime izdelka: " + nazivA + "\n");
        vsebina.appendText("Količina, ki jo naročamo: " + kolicina + "\n");
        vsebina.appendText("Šifra izdelka: " + sifraA + "\n");
        vsebina.appendText("Cena izdelka: " + cena + "\n");
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
        add(dobavitelj, gc);

        gc.gridx = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        add(dobaviteljBox, gc);

        gc.gridx = 2;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        //add(btnDodajDobavitelja, gc);

        //gc.gridx = 3;
        //gc.insets = new Insets(0, 0, 0, 0);
        //gc.anchor = GridBagConstraints.LINE_START;
        //add(odstrani, gc);

        ///////////Second row ////////////////

        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.05;

        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.LINE_START;
        add(skladisce, gc);

        gc.gridx = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        add(skladisceBox, gc);

        gc.gridx = 2;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        //add(btnSkladisce, gc);

        /////// Next row //////////////////

        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.05;

        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.LINE_START;
        add(datumDostave, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(datumDostaveBox, gc);

        /////// Next row //////////////////

        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.05;

        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.LINE_START;
        add(sifraIzdelka, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(sifraIzdelkaBox, gc);

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
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        //add(btnKolicina, gc);

        /////// Next row //////////////////

        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.05;

        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.LINE_START;
        add(cena, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(cenaBox, gc);

        /////// Next row //////////////////

        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.05;

        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.LINE_START;
        add(nabavnik, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(nabavnikBox, gc);

        /////// Next row //////////////////

        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.05;

        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.LINE_START;
        add(stevilkaDobavnice, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(stevilkaDobavniceBox, gc);

        /////// Next row //////////////////

        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.05;

        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.LINE_START;
        add(datumDobavnice, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(datumDobavniceBox, gc);

        /////// Next row //////////////////

        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 1.0;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.insets = new Insets(10, 0, 0, 20);
        add(izbrisi, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(10, 20, 0, 0);
        add(izpisi, gc);

        gc.gridx = 2;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(10, 0, 0, 0);
        add(dodajIzdelekBtn, gc);

        /////// Next row //////////////////

        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 1.0;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.insets = new Insets(0, 0, 0, 0);
        add(preglejDobavnico, gc);

    }
}
