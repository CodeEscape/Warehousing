import javax.swing.filechooser.FileFilter;
import java.io.File;

public class MeniUtils extends FileFilter {

    @Override
    public boolean accept(File file) {

        if(file.isDirectory()){
            return true;
        }

        String name = file.getName();

        String extension = Utils.getFileExtension(name);

        if(extension == null){
            return false;
        }

        if(extension.equals("txt")){
            return true;
        }
        return false;
    }

    @Override
    public String getDescription() {
        return "Files (*.txt)";
    }
}
