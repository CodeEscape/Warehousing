import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PregledPrevzemniceFrame extends JFrame{

    private PregledPrevzemniceFormPanel pregledPrevzemniceFormPanel;
    private TextPanel vsebina;
    private Meni meni;
    private Database db;
    private JSplitPane splitPane;

    public PregledPrevzemniceFrame() {

        meni = new Meni();

        setLayout(new BorderLayout());

        db = new Database();
        vsebina = new TextPanel();
        pregledPrevzemniceFormPanel = new PregledPrevzemniceFormPanel(vsebina);

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pregledPrevzemniceFormPanel, vsebina);
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


