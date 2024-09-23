/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reactions;

import static java.lang.Thread.sleep;
import servlets.socket.ClientHandler;
/**
 *
 * @author drymnz
 */
public class ServerTimer implements Runnable {
    private boolean outClientServert = false;
    private int timeServert = 1800;
    private final int TIME_START = 3600;
    private ClientHandler client;

    public ServerTimer(ClientHandler client) {
        this.client = client;
    }
    
    public void setTimeServert(int timeServert) {
        this.timeServert = timeServert;
    }
    
    public void restore(){
        this.timeServert = this.TIME_START;
    }
    
    @Override
    public void run() {
        while (!outClientServert) {            
            try {
                sleep(1000);
                this.timeServert--;
                this.outClientServert = this.timeServert==0;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } 
        }
        //this.client.setOutCliente(outClientServert);
    }
}
