package klient;

import shared.ServerService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ClientGUI {
    private String jmenoKlienta;
    private ServerService server;
    private JTextArea chatArea;
    private JTextField inputField;

    public ClientGUI(ServerService server) {
        this.server = server;
        initComponents();
    }

    public void initComponents() {
        String userName;
        do {
            userName = JOptionPane.showInputDialog(null, "Zadejte své jméno:", "Přihlášení", JOptionPane.PLAIN_MESSAGE);
            if (userName == null) {
                System.exit(0);
            }
            if (userName.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Jméno nesmí být prázdné. Zkuste to prosím znovu.", "Chyba",
                        JOptionPane.ERROR_MESSAGE);
            }
        } while (userName == null || userName.trim().isEmpty());

        jmenoKlienta = userName;

        JFrame frame = new JFrame("Chatovací aplikace - " + jmenoKlienta);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputField = new JTextField();
        JButton sendButton = new JButton("Odeslat");
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        frame.add(inputPanel, BorderLayout.SOUTH);

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                odpojit();
            }
        });

        frame.setVisible(true);

        try {
            ClientServiceImpl clientServiceImpl = new ClientServiceImpl(chatArea);
            server.writeKlient(jmenoKlienta, clientServiceImpl);
        } catch (RemoteException e) {
            System.out.println("Chyba při registraci klienta.");
        }
    }

    private void sendMessage() {
        String message = inputField.getText().trim();
        if (!message.isEmpty()) {
            try {
                server.napisZpravu(jmenoKlienta, message);
                inputField.setText("");
                chatArea.append("Ty: " + message + "\n");
            } catch (RemoteException e) {
                System.out.println("Chyba při odesílání zprávy.");
                System.out.println(e);
            }
        }
    }

    private void odpojit() {
        try {
            server.removeKlient(jmenoKlienta);
            JOptionPane.showMessageDialog(null, "Byli jste odpojeni.", "Odpojení", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Odpojeno od serveru");
            System.exit(0);
        } catch (RemoteException e) {
            System.out.println("Chyba při odpojování klienta.");
        }
    }
}