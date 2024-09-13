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
import javax.swing.JOptionPane;

import reactions.SystemAcess;

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
            boolean getIn = (!text.isEmpty()) && ((new SystemAcess(text)).isAcceder());
            if (getIn) {
                return "true";
            } else {
                return "false";
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
            boolean outCliente = false;
            /* El mensaje que manda el cliente */
            while (!outCliente) {
                Object getCliente = in.readObject();
                if (getCliente instanceof String) {
                    /* El mensaje que manda el cliente */
                    String text = (String) getCliente;
                    outCliente = text.endsWith("X");
                    //boolean systemAcess =  (!text.isEmpty()) && ((new SystemAcess(text)).isAcceder());
                    out.writeObject(JOptionPane.showInputDialog( String.format("El cliente dice \"%s\" y tu ?", text)));
                    out.flush();
                }
            }
            System.out.println("----------------- SE DESCONECTO ALGUIEN -----------------");
            clientSocket.close();
        } catch (IOException ex) {
            //Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            //Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
