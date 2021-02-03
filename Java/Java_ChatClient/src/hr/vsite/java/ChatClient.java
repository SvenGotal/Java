package hr.vsite.java;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.*;


public class ChatClient {
    //--------------------//
    private JPanel panel1;
    private JTextArea textChat;
    private JTextField textSend;
    private JButton sendButton;
    private JButton buttonConfig;
    private JLabel labelUsername;
    //--------------------//
    private UserConfig config;
    private String user;
    private Socket soc;
    private PrintWriter pw;
    private BufferedReader br;
    private static final Logger log = LoggerFactory.getLogger(ChatClient.class);
    //--------------------//
    private Connection connection;
    private Statement statement;
    private PreparedStatement prepstatement;
    private ResultSet rs;
    private static int messagesNumber = 0;

    public ChatClient() {

        //Listeners
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                ++messagesNumber;
                String statementSend = "INSERT INTO chatclient.history (id, user, message) VALUES (?,?,?);";

                try{
                    prepstatement = connection.prepareStatement(statementSend);
                    prepstatement.setInt(1, messagesNumber);
                    prepstatement.setString(2, config.getKorisnik());
                    prepstatement.setString(3, textSend.getText());
                    prepstatement.executeUpdate();

                }
                catch(SQLException se){
                    se.printStackTrace();
                    log.error("Unable to update database - " + se.getMessage());
                }

                send();
            }
        });
        buttonConfig.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                UserConfiguration uc = new UserConfiguration();

            }
        });


        //Constructor

        String initialStatement = "SELECT * FROM history";

        try{
            config = new UserConfig();
            config.loadParams();
            user = config.getKorisnik();
            labelUsername.setText("You are logged in as: " +user);
        }
        catch (Exception e){
            e.printStackTrace();
            log.error("Unable to load user configuration."+ e.getMessage());
        }
        log.info("User Config loaded");
        try{
            connect();
        }
        catch (Exception e){
            e.printStackTrace();
            log.error("Failed to connect to server... " + e.getMessage());
        }
        log.info("Connected to server.");

        try{
            String ConnectionString = "jdbc:mysql://localhost:3306/chatclient?user=root";
            connection = DriverManager.getConnection(ConnectionString);
        }
        catch(Exception se){
            se.printStackTrace();
            log.error("Unable to connect to DB - " + se.getMessage());
        }
        log.info("Connected to DB.");
        try{
            statement = connection.createStatement();
            rs = statement.executeQuery(initialStatement);

            int id;
            String username;
            String message;

            if(!textChat.getText().equals(""))
                textChat.append("\n");

            while(rs.next()){
                id = rs.getInt(1);
                username = rs.getString(2);
                message = rs.getString(3);
                textChat.append(id + ". " + user + ": " + message + "\n");
                messagesNumber++;

            }


        }
        catch (SQLException se){
            se.printStackTrace();
            log.error("Unable to execute initial statement..." + se.getMessage());
        }


    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("ChatClient");
        frame.setSize(550, 550);
        frame.setContentPane(new ChatClient().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

    //Deprecated method
    private void ButtonSend(){
        if(textSend.getText().equals("")){
            return;
        }

//        textChat.append(textSend.getText() + "\n");
        textChat.append(user + ": " + textSend.getText() + "\n");
        textSend.setText("");

    }
    private void close(){
        this.close();
        try{
            connection.close();
        }
        catch (SQLException se){
            se.printStackTrace();
            log.error("Unable to close connection to context." + se.getMessage());
        }
    }
    private void connect(){
        try {
            soc = new Socket(config.getHost(), config.getPort());
            pw = new PrintWriter(soc.getOutputStream());
            br = new BufferedReader(new InputStreamReader(soc.getInputStream()));
            String response;
            try {
                response = br.readLine();
                if (textChat.getText().length()>0)
                    textChat.append("\n");
                textChat.append(response);
                textSend.setText(null);
            } catch (IOException e) {
                log.error("Greška kod čitanja inicijalnog odgovora", e);JOptionPane.showMessageDialog(textChat, "Greška kod čitanja inicijalnog odgovora", "Greška!", JOptionPane.ERROR_MESSAGE);
            }
        } catch (UnknownHostException e) {
            log.error("Nepoznati host", e);
            close();
        } catch (IOException e) {
            log.error("IO iznimka", e);
            close();
        }
    }
    //Metoda koja šalje i prima poruku od servera:
    private void send(){
        pw.println(textChat.getText());
        if (pw.checkError())
        {
            JOptionPane.showMessageDialog(textChat, "Greška kod slanja poruke", "Greška!", JOptionPane.ERROR_MESSAGE);
        }
        String response;
        try {
            response = br.readLine();
            if (textChat.getText().length()>0)
                textChat.append("\n");
            textChat.append(response);
            textSend.setText(null);
        } catch (IOException e) {
            log.error("Greška kod čitanja", e);
            JOptionPane.showMessageDialog(textChat, "Greška kod čitanja odgovora", "Greška!", JOptionPane.ERROR_MESSAGE);
        }
    }
}
