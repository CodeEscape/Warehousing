import javax.swing.*;
import java.awt.*;

public class PreveriZalogoFrame extends JFrame {

    //private SkladisceFormPanel sklPanel;
    private TextPanel sklText;

    private PreveriZalogoForm preveriZalogo;
    private Meni meni;
    private JSplitPane splitPane;

    public PreveriZalogoFrame(){

        meni = new Meni();

        setLayout(new BorderLayout());

        //sklPanel = new SkladisceFormPanel();
        sklText = new TextPanel();
        preveriZalogo = new PreveriZalogoForm(sklText);

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, preveriZalogo, sklText);
        splitPane.setOneTouchExpandable(true);

        add(preveriZalogo, BorderLayout.WEST);
        add(sklText, BorderLayout.CENTER);
        add(meni.createMenuBar(getParent()), BorderLayout.NORTH);

        setMinimumSize(new Dimension(500, 400));
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
