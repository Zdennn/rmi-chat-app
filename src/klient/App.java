package klient;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import shared.ServerService;

public class App {
    public static void main(String[] args) throws Exception {
        try {
            ServerService server = (ServerService) Naming.lookup("rmi://localhost:8000/klienti");
            System.out.println("Připojeno k serveru.");

            ClientGUI clientGUI = new ClientGUI(server);
            
        } catch (MalformedURLException | NotBoundException e) {
            System.out.println("Chyba při navazování spojení se serverem.");
        } catch (RemoteException e) {
            System.out.println("Chyba při komunikaci se serverem.");
        }
    }
}
