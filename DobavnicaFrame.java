import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DobavnicaFrame extends JFrame {

    private DobavnicaPanel dobavnicaPanel;
    private TextPanel textPanelSZ;
    private Meni meni;
    private Database db;
    private JSplitPane splitPane;

    public DobavnicaFrame(){

        meni = new Meni();

        setLayout(new BorderLayout());

        db = new Database();
        textPanelSZ = new TextPanel();
        dobavnicaPanel = new DobavnicaPanel(db, textPanelSZ);

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, dobavnicaPanel, textPanelSZ);
        splitPane.setOneTouchExpandable(true);

        setJMenuBar(meni.createMenuBar(DobavnicaFrame.this));

        //add(formPanelSZ, BorderLayout.WEST);
        add(splitPane, BorderLayout.CENTER);
        //add(meni.createMenuBar(), BorderLayout.NORTH);

        setMinimumSize(new Dimension(500, 400));
        setSize(800, 600);

        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                db.disconect();
                dispose();
            }
        });

        setVisible(true);
    }
}
