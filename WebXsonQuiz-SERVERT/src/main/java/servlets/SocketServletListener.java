package servlets;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import Lengua.LanguageConstants;
import LexicalAndSyntacticAnalyzer.objectAnalyzer.User;
import com.google.gson.Gson;
import fileManager.FileInput;
import fileManager.FileOutput;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import reactions.ConstantSystem;
import servlets.socket.ClientHandler;

/**
 *
 * @author drymnz
 */
@WebListener
public class SocketServletListener implements ServletContextListener {

    private ServerSocket serverSocket;
    private Thread socketThread;
    private int PORT_SOCKET = 7090;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        checkDataBaseIsCreated();
        System.out.println("----------------- INICIO DEL Thread -----------------");
        socketThread = new Thread(() -> {
            System.out.println("----------------- INICIO DEL Thread -----------------");
            try {
                serverSocket = new ServerSocket(this.PORT_SOCKET);
                while (!serverSocket.isClosed()) {
                    System.out.println(LanguageConstants.LOCATION_ON_THE_PORT + this.PORT_SOCKET);
                    //Esta parte espera una coneccion 
                    this.printConsole();
                    Socket clientSocket = serverSocket.accept();
                    // Manejar la conexión del cliente en un hilo separado
                    new Thread(new ClientHandler(clientSocket)).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("----------------- FINAL DEL SOCKET -----------------");
        });
        socketThread.start();
        System.out.println("----------------- FINAL DEL Thread -----------------");
    }

    private void checkDataBaseIsCreated() {
        File archivoTxt = new File(ConstantSystem.SYSTEM_DIR, ConstantSystem.NAME_FILE_DATA_BASE_USER);
        boolean create = false;
        if (archivoTxt.exists()) {
            String tue = (new FileInput().cargarArchivoTexto(archivoTxt));
            if (tue.isBlank()) {
                create = !create;
            }
        } else {
            create = !create;
        }
        if (create) {
            List<User> listaUser = new ArrayList<>();

            listaUser.add(new User("ADMIN", "admin", "Benjamin de Jesus Perez Aguilar", "CUNOC", "2001-04-2"));
            listaUser.add(new User("BJ_97", "admin", "Benjamin de Jesus Perez Aguilar", "CUNOC", "1997-07-28"));

            // Convertir la lista de objetos a JSON
            Gson gson = new Gson();
            String json = gson.toJson(listaUser);

            if ((new FileOutput()).aguardarTexto(archivoTxt, json)) {
                System.out.println("Exito");
            } else {
                System.out.println("fracaso");
            }
            System.out.println(archivoTxt.getAbsolutePath());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        socketThread.interrupt();
    }

    private void printConsole() throws SocketException {
        Enumeration e = NetworkInterface.getNetworkInterfaces();
        while (e.hasMoreElements()) {
            NetworkInterface n = (NetworkInterface) e.nextElement();
            Enumeration ee = n.getInetAddresses();
            while (ee.hasMoreElements()) {
                InetAddress i = (InetAddress) ee.nextElement();
                Pattern pattern = Pattern.compile("^((25[0-5]|(2[0-4]|1\\d|[1-9]|)\\d)(\\.(?!$)|$)){4}$");
                Matcher verify = pattern.matcher(i.getHostAddress());
                if (verify.find()) {
                    System.out.println(LanguageConstants.POSSIBLE_IP_SOCKET + i.getHostAddress());
                }
            }
        }
    }
}
