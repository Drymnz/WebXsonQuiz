/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servlets.socket;

import LexicalAndSyntacticAnalyzer.objectAnalyzer.User;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import reactions.SystemAcess;

/**
 *
 * @author drymnz
 */
public class ClientHandler implements Runnable {

    private Socket clientSocket;
    private final String NADA = "error";
    private User userclient = null;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    private Boolean loginClient(Object get) {
        if (get instanceof String) {
            String text = (String) get;
            if (!text.isEmpty()) {
                return false;
            }
            this.userclient = (new SystemAcess(text)).loginSystem();
            return this.userclient != null;
        }
        return false;
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
                System.out.println("----------------- ESPERANDO MENSAJE-----------------");
                /* El mensaje que manda el cliente */
                Object getCliente = in.readObject();
                if (getCliente instanceof String) {
                    String text = (String) getCliente;
                    System.out.println("Peticion > " + text);
                    if (text.endsWith("false")) {
                        outCliente = !outCliente;
                        System.out.println("CERRAR");
                        out.writeObject(false);
                        out.flush();
                    } else {
                        boolean login = this.loginClient(text);
                        System.out.println("envio > " + login);
                        if (login) {
                            out.writeObject(this.userclient);
                            out.flush();
                        } else {
                            out.writeObject(this.loginClient(text));
                            out.flush();
                        }
                    }
                }
                System.out.println("----------------- REPUESTA-----------------");
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
