package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientService extends Remote {
    void ziskatZpravu(String message) throws RemoteException;
}
