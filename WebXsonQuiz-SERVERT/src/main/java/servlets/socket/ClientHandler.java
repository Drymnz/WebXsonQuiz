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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelServert.ModelClientHandler;

/**
 *
 * @author drymnz
 */
public class ClientHandler implements Runnable {

    private Socket clientSocket;
    private User userclient = null;
    private int index = 0;
    private List<Trivia> listTriviaDataBase = null;
    private  boolean outCliente = false;
    private ModelClientHandler model;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
        this.model = new ModelClientHandler(this);
    }
    
    @Override
    public void run() {
        ObjectInputStream in = null;
        try {
            // Lógica para manejar la conexión del cliente
            in = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
            this.outCliente = false;
            System.out.println("Se conecto ->" + clientSocket.getLocalAddress().getHostAddress());
            /* El mensaje que manda el cliente */
            while (!this.outCliente) {
                System.out.println("----------------- ESPERANDO MENSAJE-----------------");
                /* El mensaje que manda el cliente */
                Object getCliente = in.readObject();
                out.writeObject(this.model.getResponse(getCliente));
                out.flush();
                System.out.println("----------------- REPUESTA-----------------");
            }
            System.out.println("----------------- SE DESCONECTO ALGUIEN -----------------");
            clientSocket.close();
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
    
    public Socket getClientSocket() {
        return clientSocket;
    }

    public void setOutCliente(boolean outCliente) {
        this.outCliente = outCliente;
    }

    public boolean isOutCliente() {
        return outCliente;
    }

    public User getUserclient() {
        return userclient;
    }

    public void setUserclient(User userclient) {
        this.userclient = userclient;
    }

    public List<Trivia> getListTriviaDataBase() {
        return listTriviaDataBase;
    }

    public void setListTriviaDataBase(List<Trivia> listTriviaDataBase) {
        this.listTriviaDataBase = listTriviaDataBase;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

}
