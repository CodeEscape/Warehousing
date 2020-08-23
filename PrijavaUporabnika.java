import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PrijavaUporabnika extends JDialog {

    private IzbirniPanel izbirniPanel;
    private JButton okButton;
    private JButton cancelButton;
    private JTextField userField;
    private JButton novUporabnik;
    private NovUporabnik novUporabnikOkno;
    private Database db;
    private JFrame frame;
    private PrvaStran prvaStran;

    public static String user;

    public PrijavaUporabnika(){
        
    }

    public PrijavaUporabnika(JFrame parent, PrvaStran prvaStran) {
        super(parent, "Preferences", false);

        this.prvaStran = prvaStran;
        this.frame = parent;

        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        novUporabnik = new JButton("Nov uporabnik");
        // this.izbirniPanel = izbirniPanel;
        novUporabnikOkno = new NovUporabnik(this);
        db = new Database();



        // cancelButton.setBorder(BorderFactory.createEmptyBorder());
        novUporabnik.setContentAreaFilled(false);

        int sirinaGumba = cancelButton.getWidth();
        cancelButton.setSize(sirinaGumba, 25);
        novUporabnik.setBorderPainted(false);
        novUporabnik.setForeground(Color.BLUE);

        userField = new JTextField(10);
        userField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                user = userField.getText();
            }
        });

        layoutControls();

        userField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    String uporabnik = userField.getText();
                    user = uporabnik;
                    int upObstaja = db.countUporabnik(uporabnik);
                    System.out.println(upObstaja);

                    if (upObstaja == 1) {
                        setVisible(false);
                        prikaziIzbirniPanel();
                        uspesnaPrijava();
                    } else if (upObstaja == 0) {
                        JOptionPane.showMessageDialog(PrijavaUporabnika.this, "Uporabnik s tem imenom ne obstaja!",
                                "Neobstojec uporabnik.", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(PrijavaUporabnika.this,
                                "Vnesli ste napacno besedo ali znak. Poskusite ponovno.");
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String uporabnik = userField.getText();
                user = uporabnik;
                int upObstaja = db.countUporabnik(uporabnik);
                System.out.println(upObstaja);

                if (upObstaja == 1) {
                    setVisible(false);
                    prikaziIzbirniPanel();
                    uspesnaPrijava();
                } else if (upObstaja == 0) {
                    JOptionPane.showMessageDialog(PrijavaUporabnika.this, "Uporabnik s tem imenom ne obstaja!",
                            "Neobstojec uporabnik.", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(PrijavaUporabnika.this,
                            "Vnesli ste napacno besedo ali znak. Poskusite ponovno.");
                }

            }
        });

        novUporabnik.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                novUporabnikOkno.setVisible(true);
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        setModal(true);
        setSize(340, 250);
        setLocationRelativeTo(parent);// da odpre okno tam kjer je glavno(parent) okno
        // setVisible(true);
    }


    protected void uspesnaPrijava() {

        prvaStran.uporabnikSeJePrijavil();
    }


    private void prikaziIzbirniPanel() {
        // this.frame.prikaziIzbirniPanel();
    }

    private void layoutControls() {

        JPanel controlsPanel = new JPanel();
        JPanel buttonsPanel = new JPanel();

        int space = 15;
        Border spaceBorder = BorderFactory.createEmptyBorder(space, space, space, space);
        Border titleBorder = BorderFactory.createTitledBorder("Prijava");

        controlsPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleBorder));

        controlsPanel.setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

        gc.gridy = 0;

        Insets rightPadding = new Insets(0, 0, 0, 15);
        Insets noPadding = new Insets(0, 0, 0, 0);

        //////// First row //////////////

        gc.weightx = 1;
        gc.weighty = 1;
        gc.fill = GridBagConstraints.NONE;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.EAST;
        gc.insets = rightPadding;
        controlsPanel.add(new JLabel("User: "), gc);

        gc.gridx++;
        gc.anchor = GridBagConstraints.WEST;
        gc.insets = noPadding;
        controlsPanel.add(userField, gc);

        ///// Next row //////////

        gc.gridy++;

        gc.weightx = 2;
        gc.weighty = 1;
        gc.fill = GridBagConstraints.NONE;

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = rightPadding;
        controlsPanel.add(novUporabnik, gc);

        ///// end //////////

        buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonsPanel.add(okButton);
        buttonsPanel.add(cancelButton);

        Dimension btnSize = cancelButton.getPreferredSize();
        okButton.setPreferredSize(btnSize);

        // add sub panels to dialog
        setLayout(new BorderLayout());
        add(controlsPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);

    }
}
