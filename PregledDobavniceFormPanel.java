import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class PregledDobavniceFormPanel extends JPanel {

    private JLabel dobavnica;
    private JComboBox dobavnicaBox;

    private JTextArea textArea0;
    private JButton izpisi;
    private JButton izbrisi;

    private Database db;

    private TextPanel vsebina;

    public PregledDobavniceFormPanel(TextPanel vsebina) {

        Dimension dim = getPreferredSize();
        dim.width = 500; //popravit dimenzijo
        setPreferredSize(dim);

        db = new Database();

        this.vsebina = vsebina;

        //stevilkaPrevzemnice = new JLabel("Številka prevzemnice");
        dobavnica = new JLabel("Dobavnica: ");

        dobavnicaBox = new JComboBox();
        DefaultComboBoxModel dobavnicaModel = new DefaultComboBoxModel();
        ArrayList<String> vsebinaDobavnice = db.getStevilkaDobavnice();
        for (int i = 0; i < vsebinaDobavnice.size(); i++) {
            dobavnicaModel.addElement(vsebinaDobavnice.get(i));
        }
        dobavnicaBox.setPreferredSize(new Dimension(200, 25));
        dobavnicaBox.setModel(dobavnicaModel);

        //odstrani = new JButton("Odstrani");
        izbrisi = new JButton("Zbrisi");

        //okBtn1.setSize(50, 25);
        izpisi = new JButton("Izpisi");

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
                int stevilkaDobavnice = Integer.parseInt((String)dobavnicaBox.getSelectedItem());
                    ArrayList results = new ArrayList(db.izpisiDobavnico(stevilkaDobavnice));
                    for (int i = 0; i < results.size(); i = i + 5) {
                        results.subList(i, i + 5);
                        //System.out.println(results.subList(i, i+3));
                        vsebina.appendTextList(Collections.singleton(results.subList(i, i + 5)));
                        vsebina.appendText("\n");
                    }
            }
        });



        //Border innerBorder = BorderFactory.createEmptyBorder(15, 15, 15, 15);
        //Border outerBorder = BorderFactory.createEmptyBorder(15, 15, 15, 15);
        //setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

        layoutComponents();
    }

    public void izpisiVsebino() {

        //int stevilkaPrevzemnice = Integer.parseInt((String)stevilkaPrevzemniceBox.getSelectedItem());

        vsebina.appendText("Cena izdelka: " + izbrisi + "\n");

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
        add(dobavnica, gc);

        gc.gridx = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        add(dobavnicaBox, gc);

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
        add(izpisi, gc);

    }
}

