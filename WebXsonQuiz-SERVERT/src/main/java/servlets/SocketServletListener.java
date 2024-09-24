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
import com.cunoc.webxsonquiz.data.servert.User;
import com.google.gson.Gson;
import fileManager.FileInput;
import fileManager.FileOutput;
import modelServert.ModelDatabaseFileManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
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
    private final int PORT_SOCKET = 5348;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        (new ModelDatabaseFileManager()).checkDataBaseIsCreated();
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
                    System.out.println("----------------- SE CONECTO ALGUIEN -----------------");
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
