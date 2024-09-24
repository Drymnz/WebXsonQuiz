package modelServert;

import com.cunoc.webxsonquiz.data.servert.QuizAttempt;
import com.cunoc.webxsonquiz.data.servert.Trivia;
import java.util.ArrayList;
import reactions.DataBaseListTrivia;
import reactions.SystemAcess;
import servlets.socket.ClientHandler;

/**
 *
 * @author drymnz
 */
public class ModelClientHandler {

    private ClientHandler client;

    public ModelClientHandler(ClientHandler client) {
        this.client = client;
    }

    private Boolean loginClient(Object get) {
        if (get instanceof String) {
            String text = (String) get;
            if (text.isEmpty()) {
                return false;
            }
            this.client.setUserclient((new SystemAcess(text)).loginSystem());
            System.out.println(this.client.getUserclient().toString());
            return this.client.getUserclient() != null;
        }
        return false;
    }

    public Object getResponse(Object getCliente) {
        try {
            // Retorna una nueva instancia del tipo T usando reflection
            if (getCliente instanceof String) {
                String text = (String) getCliente;
                System.out.println(this.client.getClientSocket().getLocalAddress().getHostAddress() + "Peticion > " + text);
                if (text.equals("false")) {
                    this.client.setOutCliente(!this.client.isOutCliente());
                    System.out.println("CERRAR a " + this.client.getClientSocket().getLocalAddress().getHostAddress());
                    return false;
                } else {
                    boolean login = this.loginClient(text);
                    String nameCliente = this.client.getClientSocket() != null ? this.client.getUserclient().getId() : this.client.getClientSocket().getLocalAddress().getHostAddress();
                    if (login) {
                        System.out.println("Entro: " + nameCliente);
                        return this.client.getUserclient();

                    } else {
                        System.out.println("Intento de cliente: " + nameCliente);
                        return false;
                    }
                }
            } else if (getCliente instanceof ArrayList) {
                this.client.setIndex(0);
                this.client.setListTriviaDataBase((new DataBaseListTrivia().getListTrivias()));
                if (this.client.getListTriviaDataBase().size() > this.client.getIndex()) {
                    Trivia newTrivia = this.client.getListTriviaDataBase().get(this.client.getIndex());
                    System.out.println("Le manda las trivias " + (this.client.getIndex() + 1) + "/" + this.client.getListTriviaDataBase().size() + " => " + this.client.getClientSocket().getLocalAddress().getHostAddress());
                    return newTrivia;
                } else {
                    return false;
                }
            } else if (getCliente instanceof Trivia) {
                this.client.setIndex(this.client.getIndex() + 1);
                if (this.client.getListTriviaDataBase().size() > this.client.getIndex()) {
                    Trivia newTrivia = this.client.getListTriviaDataBase().get(this.client.getIndex());
                    System.out.println("Le manda las trivias " + (this.client.getIndex() + 1) + "/" + this.client.getListTriviaDataBase().size() + " => " + this.client.getClientSocket().getLocalAddress().getHostAddress());
                    return newTrivia;
                } else {
                    return false;
                }
            }else if (getCliente instanceof QuizAttempt){
                QuizAttempt obtener = (QuizAttempt) getCliente;
                System.out.println(obtener.toString());
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Manejo de error
        }
    }

}
