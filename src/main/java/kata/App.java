package kata;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App
{
    public static void main( String[] args )
    {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("."));
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        List<File> files = new ArrayList<>();

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            files = Arrays.asList(chooser.getCurrentDirectory());
            System.out.println("Folder: " + chooser.getCurrentDirectory());
        } else {
            JOptionPane.showMessageDialog(null, "Nie wybrano folderu.", "Błąd", JOptionPane.ERROR_MESSAGE);
        }
    }
}
