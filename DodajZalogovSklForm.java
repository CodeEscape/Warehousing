import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DodajZalogovSklForm extends JPanel {

    private JLabel kateroSkladisce;
    private JLabel izdelkiVBazi;
    private JLabel sifraIzdelka;
    private JLabel imeIzdelka;
    private JLabel kolicina;
    private JLabel cena;

    private JComboBox izdelkiVBaziBox;
    private JTextField sifraIzdelkaBox;
    private JTextField imeIzdelkaBox;
    //private JTextField dodajIzdelekText;
    private JTextField kolicinaText;
    private JTextField cenaBox;
    private JComboBox skladisceBox;

    private JTextArea textArea1;
    private Database db;
    private TextPanel vsebina;

    private JButton izbrisi;
    private JButton dodaj;

    public DodajZalogovSklForm(TextPanel vsebina) {

        this.vsebina = vsebina;

        Dimension dim = getPreferredSize();
        dim.width = 450;
        setPreferredSize(dim);

        db = new Database();

        izdelkiVBazi = new JLabel("Izdelki v bazi");
        sifraIzdelka = new JLabel("Šifra izdelka");
        imeIzdelka = new JLabel("Ime izdelka");
        kolicina = new JLabel("Količina");
        cena = new JLabel("Cena");
        kateroSkladisce = new JLabel("Katero skladišče");
        
        sifraIzdelkaBox = new JTextField();
        sifraIzdelkaBox.setPreferredSize(new Dimension(200, 25));
        imeIzdelkaBox = new JTextField();
        imeIzdelkaBox.setPreferredSize(new Dimension(200, 25));
        //dodajIzdelekText = new JTextField();
        kolicinaText = new JTextField();
        kolicinaText.setPreferredSize(new Dimension(200, 25));
        cenaBox = new JTextField();
        cenaBox.setPreferredSize(new Dimension(200, 25));


        //Comboboxes and their settings
        izdelkiVBaziBox = new JComboBox();
        izdelkiVBaziBox.setPreferredSize(new Dimension(200, 25));
        DefaultComboBoxModel izdelek = new DefaultComboBoxModel();
        ArrayList<String> vsiIzdelki = db.getImeIzdelka();
        for (int i = 0; i < vsiIzdelki.size(); i++) {
            izdelek.addElement(vsiIzdelki.get(i));
        }
        izdelkiVBaziBox.setModel(izdelek);
        //izdelkiVBaziBox.setEditable(true);

        skladisceBox = new JComboBox();
        skladisceBox.setPreferredSize(new Dimension(200, 25));
        DefaultComboBoxModel skl = new DefaultComboBoxModel();
        ArrayList<String> vsebinaSkl = db.getImeSkladisca();
        for(int i = 0; i < vsebinaSkl.size(); i++){
            skl.addElement(vsebinaSkl.get(i));
        }
        skladisceBox.setModel(skl);
        //skladisceBox.setEditable(true);


        kolicinaText = new JTextField();
        kolicinaText.setPreferredSize(new Dimension(200, 25));

        izbrisi = new JButton("Zbriši");
        dodaj = new JButton("Dodaj izdelek");

        dodaj.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                izpisiVsebino();

                int kolicina = Integer.parseInt(kolicinaText.getText());
                String skladisce = (String)skladisceBox.getSelectedItem();
                String izdelek = imeIzdelkaBox.getText();
                int sifra = Integer.parseInt(sifraIzdelkaBox.getText());
                int cena = Integer.parseInt(cenaBox.getText());

                vsebina.appendText("Skladišče: " + skladisce + "\n");
                vsebina.appendText("Izdelek: " + izdelek + "\n");
                vsebina.appendText("Šifra izdelka: " + sifra + "\n");
                vsebina.appendText("Cena izdelka: " + cena + "\n");
                vsebina.appendText("Količina: " + kolicina + "\n");
            }
        });

        textArea1 = new JTextArea();
        textArea1.setPreferredSize(new Dimension(800, 200));

        //Border innerBorder = BorderFactory.createEmptyBorder(15, 15, 15, 15);
        //Border outerBorder = BorderFactory.createEmptyBorder(15, 15, 15, 15);
        //setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

        layoutComponents();
    }

    public void izpisiVsebino() {
        dodajIzdelek();
        //dodajSifroIzdelka();
        dodajKolicino();
        //dodajCeno();
        //dodajVIZdelkeVSkladiscu();
    }
/*
////////////  Imam to isto pod količino
    private void dodajVIZdelkeVSkladiscu() {
        String imeIzdelka = imeIzdelkaBox.getText();
        int idIzdelka = db.tujId("Izdelki", "imeIzdelka", imeIzdelka);

        int imeSkladisca = Integer.parseInt((String)skladisceBox.getSelectedItem());
        int idSkladisca = db.tujId("Izdelki", "imeIzdelka", imeSkladisca);

        db.dodajIzdelkeVSkladisce(idDobavnice, idIzdelka);
    }
*/
    /*
        private void dodajCeno() {
            int cena = Integer.parseInt(cenaBox.getText());
            db.dodajCeno(cena);
        }
    */
    private void dodajKolicino() {
        int kolicina = Integer.parseInt(kolicinaText.getText());
        String skladisce = (String)skladisceBox.getSelectedItem();
        String izdelek = imeIzdelkaBox.getText();
        db.dodajKolicino(kolicina, skladisce, izdelek);
    }

    private void dodajSifroIzdelka() {
        int sifra = Integer.parseInt(sifraIzdelkaBox.getText());
        int sifraObstaja = db.countJeVBaziInt(sifra, "Izdelki", "sifraIzdelka");

        if(sifraObstaja == 0){
            db.dodajIzdelek(sifra);

            JOptionPane.showMessageDialog(DodajZalogovSklForm.this, "sifra je uspešno dodan.", "sifra dodan.", JOptionPane.INFORMATION_MESSAGE);

        } else if (sifraObstaja != 1){
            JOptionPane.showMessageDialog(DodajZalogovSklForm.this, "sifra že obstaja!", "Napaka pri vnosu.", JOptionPane.ERROR_MESSAGE);
        } else{
            JOptionPane.showMessageDialog(DodajZalogovSklForm.this, "Vnesli ste napačno besedo ali znak. Poskusite ponovno.");
        }
    }

    private void dodajIzdelek() {
        String izdelek = imeIzdelkaBox.getText();
        int izdelekObstaja = db.countJeVBaziString(izdelek, "Izdelki", "imeIzdelka");

        int sifra = Integer.parseInt(sifraIzdelkaBox.getText());
        int sifraObstaja = db.countJeVBaziInt(sifra, "Izdelki", "sifraIzdelka");

        int cena = Integer.parseInt(cenaBox.getText());

        if(izdelekObstaja == 0 || sifraObstaja == 0){
            db.vstaviIzdelek(izdelek, sifra, cena);

            JOptionPane.showMessageDialog(DodajZalogovSklForm.this, "Izdelek je uspešno dodan.", "Izdelek dodan.", JOptionPane.INFORMATION_MESSAGE);

        } else if (izdelekObstaja != 1 || sifraObstaja != 1){
            JOptionPane.showMessageDialog(DodajZalogovSklForm.this, "Izdelek že obstaja!", "Napaka pri vnosu.", JOptionPane.ERROR_MESSAGE);
        } else{
            JOptionPane.showMessageDialog(DodajZalogovSklForm.this, "Vnesli ste napačno besedo ali znak. Poskusite ponovno.");
        }
    }

    /*
        int sifraArtikla = Integer.parseInt((String) sifraIzdelkaBox.getText());
        String nazivArtikla = (String) imeIzdelkaBox.getText();
        int kolicina = Integer.parseInt(kolicinaText.getText());

        //vsebina2.appendText("Šifra artikla: " + sifraArtikla + "\n");
        //vsebina2.appendText("Naziv artikla: " + nazivArtikla + "\n");
        //vsebina2.appendText("Količina: " + kolicina + "\n");

        public void shraniVBazo(){
            String skladisce = (String)kateroSkladisceBox.getSelectedItem();
            int sifraArtikla = Integer.parseInt((String)sifraArtiklaBox.getSelectedItem());
            String nazivArtikla = (String)nazivArtiklaBox.getSelectedItem();
            int kolicina = Integer.parseInt(kolicinaBox.getText());
            int nabavnaVrednost = Integer.parseInt((String)nabavnaVrednostBox.getSelectedItem());

            db.dodajDobavitelja(sifraArtikla);
            db.dodajSkladisce(skladisce);
            db.dodajIzdelek(nazivArtikla, kolicina);//količina se mora dodat ali ne?
            db.dodajNarocila(nazivArtikla, nabavnaVrednost);
        }
    */
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

        gc.gridx = 2;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        //add(btnSkladisce, gc);

        ///////////Second row ////////////////

        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.05;

        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.LINE_START;
        add(izdelkiVBazi, gc);
        //add(sifraIzdelka, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(izdelkiVBaziBox, gc);

        /////// Next row //////////////////
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.05;

        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.LINE_START;
        add(imeIzdelka, gc);
        //add(sifraIzdelka, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(imeIzdelkaBox, gc);

        /////// Next row //////////////////

        gc.gridy++;//da se samo poveÄŤuje in da je bolj pregledno, prej je bilo v vsakem napisano 1, 2, 3...

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
        add(kolicina, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(kolicinaText, gc);

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
        gc.weighty = 1.0;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.insets = new Insets(0, 0, 0, 0);
        add(izbrisi, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 0);
        add(dodaj, gc);

    }
}



