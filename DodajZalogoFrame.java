import javax.swing.*;
import java.awt.*;

public class DodajZalogoFrame extends JFrame {

    private DodajZalogovSklForm dodajZalogo;
    private TextPanel vsebina;
    private JSplitPane splitPane;

    private Meni meni;

    public DodajZalogoFrame() {

        meni = new Meni();

        setLayout(new BorderLayout());


        //sklPanel = new SkladisceFormPanel();
        vsebina = new TextPanel();
        dodajZalogo = new DodajZalogovSklForm(vsebina);

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, dodajZalogo, vsebina);
        splitPane.setOneTouchExpandable(true);

        //add(dodajZalogo, BorderLayout.WEST);
        add(splitPane, BorderLayout.CENTER);
        add(meni.createMenuBar(getParent()), BorderLayout.NORTH);

        setMinimumSize(new Dimension(500, 400));
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}



