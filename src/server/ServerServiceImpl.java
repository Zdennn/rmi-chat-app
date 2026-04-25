package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
import shared.ClientService;
import shared.ServerService;

public class ServerServiceImpl extends UnicastRemoteObject implements ServerService {

    private final Map<String, ClientService> clientServiceMap;

    protected ServerServiceImpl() throws RemoteException {
        super();

        clientServiceMap = new HashMap<>();
    }

    @Override
    public void writeKlient(String jmenoClient, ClientService clientService) throws RemoteException {
        clientServiceMap.put(jmenoClient, clientService);
        
        System.out.println("Klient " + jmenoClient + " připojen.");
    }

    @Override
    public synchronized void napisZpravu(String odesilatel, String message) throws RemoteException {
        String zprava = odesilatel + ": " + message;
        System.out.println("Příchozí zpráva od " + odesilatel + ": " + message);

        for (Map.Entry<String, ClientService> entry : clientServiceMap.entrySet()) {
            String prijemce = entry.getKey();
            ClientService klientService = entry.getValue();

            if (!prijemce.equals(odesilatel)) {
                klientService.ziskatZpravu(zprava);
            }
        }
    }

    @Override
    public synchronized void removeKlient(String klient) throws RemoteException {
        clientServiceMap.remove(klient);
        System.out.println("Klient " + klient + " odpojen.");
    }
}
