import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NovUporabnik extends JDialog {

    private JButton dodajButton;
    private JButton cancelButton;
    private JTextField userField;
    private Database db;

    public  NovUporabnik(JDialog parent){

        super(parent, "Nov uporabnik", false);

        dodajButton = new JButton("Dodaj");
        cancelButton = new JButton("Cancel");
        db = new Database();
        //cancelButton.setBorder(BorderFactory.createEmptyBorder());

        //dodajButton.setPreferredSize(new Dimension(150, 25));
        //dodajButton.setMinimumSize(new Dimension(50, 25));
        dodajButton.setSize(50, 25);
        int sirinaGumba = dodajButton.getWidth();
        cancelButton.setSize(sirinaGumba, 25);


        userField = new JTextField(10);

        layoutControls();

        dodajButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//search, if null, doesnt exist, if nnot null, exists
                String uporabnik = userField.getText();
                int upObstaja = db.countUporabnik(uporabnik);
                if(upObstaja == 0){
                    db.dodajUporabnika(uporabnik);
                    JOptionPane.showMessageDialog(NovUporabnik.this, "Nov uporabnik je uspešno dodan.", "Dodan uporabnik.", JOptionPane.INFORMATION_MESSAGE);
                    setVisible(false);
                } else if (upObstaja != 0){
                    JOptionPane.showMessageDialog(NovUporabnik.this, "Uporabnik že obstaja!", "Napaka pri iskanju.", JOptionPane.ERROR_MESSAGE);
                } else{
                    JOptionPane.showMessageDialog(NovUporabnik.this, "Vnesli ste napačno besedo ali znak. Poskusite ponovno.");
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        setSize(340, 250);
        setLocationRelativeTo(parent);//da odpre okno tam kjer je glavno(parent) okno
        //setVisible(true);
    }

    private void layoutControls() {

        JPanel controlsPanel = new JPanel();
        JPanel buttonsPanel = new JPanel();

        int space = 15;
        Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
        Border titleBorder = BorderFactory.createTitledBorder("Database Preferences");

        controlsPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleBorder));

        controlsPanel.setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        gc.gridy = 0;

        Insets rightPadding = new Insets(0, 0, 0, 15);
        Insets noPadding = new Insets(0, 0, 0, 0);

        //////// First row  //////////////

        gc.weightx = 1;
        gc.weighty = 1;
        gc.fill = GridBagConstraints.NONE;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = rightPadding;
        controlsPanel.add(new JLabel("Uporabniško ime: "), gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = noPadding;
        controlsPanel.add(userField, gc);

        ///// end //////////

        buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.add(dodajButton);
        buttonsPanel.add(cancelButton);

        //Dimension btnSize = cancelButton.getPreferredSize();
        //dodajButton.setPreferredSize(btnSize);

        //add sub panels to dialog
        setLayout(new BorderLayout());
        add(controlsPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);
    }

}
