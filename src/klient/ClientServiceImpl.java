package klient;

import java.rmi.RemoteException;
import javax.swing.JTextArea;
import shared.ClientService;
import java.rmi.server.UnicastRemoteObject;

public class ClientServiceImpl extends UnicastRemoteObject implements ClientService {
    private JTextArea chatArea;

    public ClientServiceImpl(JTextArea chatArea) throws RemoteException {
        super();
        this.chatArea = chatArea;
    }

    @Override
    public void ziskatZpravu(String message) throws RemoteException {
        chatArea.append(message + "\n");
    }
}
