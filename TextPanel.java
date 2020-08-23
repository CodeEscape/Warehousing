import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TextPanel extends JPanel {

    private JTextArea textArea;

    public TextPanel(){
        textArea = new JTextArea();

        setLayout(new BorderLayout());

        add(new JScrollPane(textArea), BorderLayout.CENTER);

        setMinimumSize(new Dimension(200, 600));

        //Border innerBorder = BorderFactory.createEmptyBorder(15, 15, 15, 15);
        //Border outerBorder = BorderFactory.createEmptyBorder(15, 15, 15, 15);
        //setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
    }

    public void appendText(String text){
        textArea.append(text);
    }

    public void appendTextArray(ArrayList<String> text){
        textArea.append(String.valueOf(text));
    }

    public void appendTextList(List<String> text){
        textArea.append(String.valueOf(text));
    }

    public void clearText(){
        textArea.setText(null);
    }

    public void appendTextList(Collection<List<ArrayList<String>>> partitioned) {
        textArea.append(String.valueOf(partitioned));
    }
}
