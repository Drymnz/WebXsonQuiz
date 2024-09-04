/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servlets.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author drymnz
 */
public class ClientHandler implements Runnable {

    private Socket clientSocket;
    private final String NADA = "error";

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    private String interprets(Object get) {
        if (get instanceof String) {
            String text = (String) get;
            System.out.println(text);/// mostrar el mensaje
            if (text.isEmpty()) {
                return "";
            } else {
                return "Todo bien te has conectado al socket";
            }
        }
        return NADA;
    }

    @Override
    public void run() {
        ObjectInputStream in = null;
        try {
            // Lógica para manejar la conexión del cliente
            in = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());

            Object getCliente = in.readObject();
            String json = interprets(getCliente);
            /* analysis process */
            out.writeObject(json);
        } catch (IOException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
