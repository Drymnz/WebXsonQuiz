package fileManager;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author drymnz
 */
public class FileInput {
    
     private FileInputStream entrada = null;;

    public String cargarArchivoTexto(File carchivo) {
        StringBuilder extraje = new StringBuilder();
        InputStreamReader lector = null;
        
        try {
            // Abrir el archivo con FileInputStream
            entrada = new FileInputStream(carchivo);
            // Crear InputStreamReader para leer con codificación UTF-8
            lector = new InputStreamReader(entrada, StandardCharsets.UTF_8);
            int valor;
            // Leer el archivo carácter por carácter
            while ((valor = lector.read()) != -1) {
                extraje.append((char) valor);
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error en la lectura: Archivo no encontrado");
            Logger.getLogger(FileInput.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error en la lectura del archivo");
            Logger.getLogger(FileInput.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Cerrar los streams de manera segura
            if (lector != null) {
                try {
                    lector.close();
                } catch (IOException ex) {
                    Logger.getLogger(FileInput.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (entrada != null) {
                try {
                    entrada.close();
                } catch (IOException ex) {
                    Logger.getLogger(FileInput.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        // Retornar el texto extraído
        return extraje.toString();
    }

    public File exiteDireccion(File verificar) {
        if (verificar.exists()) {
            return verificar;
        } else {
            try {
                if (verificar.createNewFile()) {
                    System.out.println("FUE CREADO " + verificar.getName());
                    return verificar;
                } else {
                    System.out.println("NO SE PUDO CREAR " + verificar.getName());
                }
            } catch (IOException ex) {
                Logger.getLogger(FileInput.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
}