package fileManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author drymnz
 */
public class FileOutput implements Runnable {

    private FileOutputStream salida;
    private File archivoHilo;
    private String contenidoHilo;

    public FileOutput() {
        
    }

    public FileOutput(File archivo, String contenido) {
        this.archivoHilo = archivo;
        this.contenidoHilo = contenido;
    }

    public boolean aguardarTexto(File archivo, String contenido) {
        FileOutputStream salida = null;
        try {
            salida = new FileOutputStream(archivo);
            // Convertir el contenido a bytes utilizando la codificación UTF-8
            byte[] bytes = contenido.getBytes(StandardCharsets.UTF_8);
            salida.write(bytes);
            return true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileOutput.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileOutput.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (salida != null) {
                try {
                    salida.close();  // Asegurar que el stream se cierra
                } catch (IOException ex) {
                    Logger.getLogger(FileOutput.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return false;
    }

    @Override
    public void run() {
        if (this.archivoHilo!=null && this.contenidoHilo!=null) {
            aguardarTexto(this.archivoHilo, this.contenidoHilo);
        }
    }

    public void setArchivoHilo(File archivoHilo) {
        this.archivoHilo = archivoHilo;
    }

    public void setContenidoHilo(String contenidoHilo) {
        this.contenidoHilo = contenidoHilo;
    }

}