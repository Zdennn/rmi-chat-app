package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class App {
    public static void main(String[] args) throws Exception {
        try {
            ServerServiceImpl server = new ServerServiceImpl();

            Registry registry = LocateRegistry.createRegistry(8000);
            registry.rebind("klienti", server);

            System.out.println("Server běží...");
        } catch (RemoteException e) {
            System.out.println("Chyba při spouštění serveru.");
        }
    }
}
