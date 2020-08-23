import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileFilter;

public class Meni {

    private JFileChooser fileChooser;

    public Meni(){

        //createMenuBar();;
    }

    public JMenuBar createMenuBar(Component parent) {
        JMenuBar menuBar = new JMenuBar();

        fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new MeniUtils());

        JMenu fileMenu = new JMenu("File");
        JMenuItem shraniVFile = new JMenuItem("Shrani");
        JMenuItem exitItem = new JMenuItem("Izhod");

        fileMenu.add(shraniVFile);
        fileMenu.addSeparator();// da loÄŤi skupine v menuju
        fileMenu.add(exitItem);

        menuBar.add(fileMenu);

        fileMenu.setMnemonic(KeyEvent.VK_F);// da bliĹľnjico na file in podÄŤrta f
        shraniVFile.setMnemonic(KeyEvent.VK_S);
        exitItem.setMnemonic(KeyEvent.VK_I);

        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));// Mnemonic je za 1 tipko,

        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        shraniVFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(fileChooser.showSaveDialog(parent) == JFileChooser.APPROVE_OPTION){
                    System.out.println("bla bla");
                };
            }
        });

        return menuBar;
    }
}
