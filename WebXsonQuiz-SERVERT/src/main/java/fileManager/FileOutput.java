package fileManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
        try {
            salida = new FileOutputStream(archivo);
            byte[] bytes = contenido.getBytes();
            salida.write(bytes);
            salida.close();
            return true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileOutput.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileOutput.class.getName()).log(Level.SEVERE, null, ex);
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