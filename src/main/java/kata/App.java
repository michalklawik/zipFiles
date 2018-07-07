package kata;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class App
{
    public static void main( String[] args ) throws IOException {
        String path = getDirectoryPath();
        zipDirectory(path);
    }

    private static String getDirectoryPath() {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("."));
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        List<File> files = new ArrayList<>();

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile().getAbsolutePath();
        } else {
            JOptionPane.showMessageDialog(null, "Nie wybrano folderu.", "Błąd", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    private static void zipDirectory(String path) throws IOException {

        FileOutputStream fos = new FileOutputStream("zipedDire.zip");
        ZipOutputStream zipOutputStream = new ZipOutputStream(fos);
        File directoryToZip = new File(path);

        zipFile(directoryToZip, directoryToZip.getName(), zipOutputStream);
        zipOutputStream.close();
        fos.close();
    }

    private static void zipFile(File directoryToZip, String name, ZipOutputStream zipOutputStream) throws IOException {
        if (directoryToZip.isDirectory()) {
            File[] files = directoryToZip.listFiles();
            for (File file : files) {
                zipFile(file, name + "/" + file.getName(), zipOutputStream);
            }
            return;
        }
        FileInputStream fis = new FileInputStream(directoryToZip);
        ZipEntry zipEntry = new ZipEntry(name);
        zipOutputStream.putNextEntry(zipEntry);
        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zipOutputStream.write(bytes, 0, length);
        }
        fis.close();
    }
}
