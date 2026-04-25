package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerService extends Remote {
    void writeKlient(String klient, ClientService clientService) throws RemoteException;
    void napisZpravu(String odesilatel, String message) throws RemoteException;
    void removeKlient(String klient) throws RemoteException;
}
