import javax.swing.*;
import java.awt.*;

public class PrevzemnicaFrame extends JFrame {

    private PrevzemnicaPanel blagoPanel;
    private TextPanel blagoText;
    private Meni meni;
    private JSplitPane splitPane;

    public PrevzemnicaFrame(){

        meni = new Meni();

        setLayout(new BorderLayout());

        blagoText = new TextPanel();
        blagoPanel = new PrevzemnicaPanel(blagoText);

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, blagoPanel, blagoText);
        splitPane.setOneTouchExpandable(true);

        setJMenuBar(meni.createMenuBar(getParent()));

        //add(blagoPanel, BorderLayout.WEST);
        //add(blagoText, BorderLayout.CENTER);
        add(splitPane, BorderLayout.CENTER);

        setMinimumSize(new Dimension(500, 400));
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }


}
