import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PregledDobavniceFrame extends JFrame {

    private PregledDobavniceFormPanel pregledDobavniceForm;
    private TextPanel vsebina;
    private Meni meni;
    private Database db;
    private JSplitPane splitPane;

    public PregledDobavniceFrame() {

        meni = new Meni();

        setLayout(new BorderLayout());

        db = new Database();
        vsebina = new TextPanel();
        pregledDobavniceForm = new PregledDobavniceFormPanel(vsebina);

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pregledDobavniceForm, vsebina);
        splitPane.setOneTouchExpandable(true);

        setJMenuBar(meni.createMenuBar(getParent()));

        //add(formPanelSZ, BorderLayout.WEST);
        add(splitPane, BorderLayout.CENTER);
        //add(meni.createMenuBar(), BorderLayout.NORTH);

        setMinimumSize(new Dimension(500, 400));
        setSize(800, 600);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                db.disconect();
                dispose();
            }
        });

        setVisible(true);
    }
}


