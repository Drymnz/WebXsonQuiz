/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import Lengua.LanguageConstants;
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
        socketThread = new Thread(() -> {
            try {
                serverSocket = new ServerSocket(this.PORT_SOCKET);
                while (!serverSocket.isClosed()) {
                    System.out.println( "El puerto esta ubicado en " + this.PORT_SOCKET);
                    this.printConsole();
                    Socket clientSocket = serverSocket.accept();
                    // Manejar la conexión del cliente en un hilo separado
                    new Thread(new ClientHandler(clientSocket)).start();
                }
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        socketThread.start();
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
    
        private void printConsole() throws SocketException{
        Enumeration e = NetworkInterface.getNetworkInterfaces();
        while (e.hasMoreElements()) {
            NetworkInterface n = (NetworkInterface) e.nextElement();
            Enumeration ee = n.getInetAddresses();
            while (ee.hasMoreElements()) {
                InetAddress i = (InetAddress) ee.nextElement();
                Pattern pattern = Pattern.compile("^((25[0-5]|(2[0-4]|1\\d|[1-9]|)\\d)(\\.(?!$)|$)){4}$");
                Matcher verify = pattern.matcher(i.getHostAddress());
                if (verify.find()) {
                    System.out.println("\nPosible ip para : "+ i.getHostAddress());
                }
            }
        }
    }
}
