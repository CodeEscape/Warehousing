import javax.swing.*;
import java.awt.*;

public class DodajSkladisceFrame extends JFrame {

    //private SkladisceFormPanel sklPanel;
    private TextPanel sklText;

    private DodajSkladisceFormPanel dodajSkladisce;
    private Meni meni;

    private JSplitPane splitPane;

    public DodajSkladisceFrame() {

        meni = new Meni();

        setLayout(new BorderLayout());

        //sklPanel = new SkladisceFormPanel();
        sklText = new TextPanel();
        dodajSkladisce = new DodajSkladisceFormPanel(sklText);

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, dodajSkladisce, sklText);
        splitPane.setOneTouchExpandable(true);

        add(dodajSkladisce, BorderLayout.WEST);
        add(sklText, BorderLayout.CENTER);
        add(meni.createMenuBar(getParent()), BorderLayout.NORTH);

        setMinimumSize(new Dimension(500, 400));
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}

