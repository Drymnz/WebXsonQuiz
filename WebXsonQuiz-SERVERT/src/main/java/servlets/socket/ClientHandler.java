/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servlets.socket;

import com.cunoc.webxsonquiz.data.servert.Trivia;
import com.cunoc.webxsonquiz.data.servert.User;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import reactions.DataBaseListTrivia;

import reactions.SystemAcess;

/**
 *
 * @author drymnz
 */
public class ClientHandler implements Runnable {

    private Socket clientSocket;
    private User userclient = null;
    private int index = 0;
    private List<Trivia> listTriviaDataBase = null;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    private Boolean loginClient(Object get) {
        if (get instanceof String) {
            String text = (String) get;
            if (text.isEmpty()) {
                return false;
            }
            this.userclient = (new SystemAcess(text)).loginSystem();
            System.out.println(this.userclient.toString());
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
            System.out.println("Se conecto ->" + clientSocket.getLocalAddress().getHostAddress());
            /* El mensaje que manda el cliente */
            while (!outCliente) {
                System.out.println("----------------- ESPERANDO MENSAJE-----------------");
                /* El mensaje que manda el cliente */
                Object getCliente = in.readObject();

                if (getCliente instanceof String) {
                    String text = (String) getCliente;
                    System.out.println(clientSocket.getLocalAddress().getHostAddress() + "Peticion > " + text);
                    if (text.equals("false")) {
                        outCliente = !outCliente;
                        System.out.println("CERRAR a " + clientSocket.getLocalAddress().getHostAddress());
                        out.writeObject(false);
                        out.flush();
                    } else {
                        boolean login = this.loginClient(text);
                        String nameCliente = this.clientSocket != null ? this.userclient.getId() : clientSocket.getLocalAddress().getHostAddress();
                        if (login) {
                            System.out.println("Entro: "+nameCliente);
                            out.writeObject(this.userclient);
                            out.flush();
                        } else {
                            System.out.println("Intento de cliente: "+nameCliente);
                            out.writeObject(login);
                            out.flush();
                        }
                    }
                }else
                if (getCliente instanceof ArrayList) {
                    this.index = 0;
                    this.listTriviaDataBase = (new DataBaseListTrivia().getListTrivias());
                    if (this.listTriviaDataBase.size() > this.index) {
                        Trivia newTrivia = listTriviaDataBase.get(this.index);
                        System.out.println("Le manda las trivias " + (this.index + 1) + "/" + this.listTriviaDataBase.size() + " => " + clientSocket.getLocalAddress().getHostAddress());
                        out.writeObject(newTrivia);
                        out.flush();
                    } else {
                        out.writeObject(false);
                        out.flush();
                    }
                }else
                if (getCliente instanceof Trivia) {
                    index++;
                    if (this.listTriviaDataBase.size() > this.index) {
                        Trivia newTrivia = listTriviaDataBase.get(this.index);
                        System.out.println("Le manda las trivias " + (this.index + 1) + "/" + this.listTriviaDataBase.size() + " => " + clientSocket.getLocalAddress().getHostAddress());
                        out.writeObject(newTrivia);
                        out.flush();
                    } else {
                        out.writeObject(false);
                        out.flush();
                    }
                }
                if (getCliente == null) {
                    outCliente = true;
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
