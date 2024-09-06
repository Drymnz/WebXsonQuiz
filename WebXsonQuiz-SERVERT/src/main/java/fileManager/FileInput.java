package fileManager;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author drymnz
 */
public class FileInput {
    
     private FileInputStream entrada;

    public String cargarArchivoTexto(File carchivo) {
        String extraje = "";
        try {
            entrada = new FileInputStream(carchivo);
            int valor;
            while ((valor = entrada.read()) != -1) {
                char caracter = (char) valor;
                extraje += caracter;
            }
            entrada.close();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "error en lectura");
            Logger.getLogger(FileInput.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "error en lectura");
            Logger.getLogger(FileInput.class.getName()).log(Level.SEVERE, null, ex);
        }
        return extraje;
    }

    public File exiteDireccion(File verificar) {
        if (verificar.exists()) {
            return verificar;
        } else {
            if (verificar.mkdirs()) {
                System.out.println("FUE CREADO " + verificar.getName());
                return verificar;
            } else {
                System.out.println("NO SE PUDO CREAR " + verificar.getName());
            }
        }
        return null;
    }
}