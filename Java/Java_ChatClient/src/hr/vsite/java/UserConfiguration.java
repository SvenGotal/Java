package hr.vsite.java;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserConfiguration extends JDialog {
    private JPanel panel2;
    private JTextField textFieldUser;
    private JTextField textFieldHost;
    private JTextField textFieldPort;
    private JButton buttonCancel;
    private JButton buttonSave;


    private UserConfig configuration;

    private void closeWindow(){
        this.dispose();
    }

    public UserConfiguration() {

        this.setContentPane(panel2);
        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        this.pack();
        this.setVisible(true);



        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                UserConfig uc = new UserConfig();

                String[] params = new String[3];
                params[0] = textFieldUser.getText();
                params[1] = textFieldHost.getText();
                params[2] = textFieldPort.getText();

                Boolean user, host, port;
                user = !params[0].equals("");
                host = !params[1].equals("");
                port = !params[2].equals("");

                if(user && host && port){

                    uc.saveParamChanges(params[0], params[1], params[2]);
                    closeWindow();
                }


                else
                    JOptionPane.showOptionDialog(
                            panel2,
                            "All fields must have a value.",
                            "Unsaved data!",
                            JOptionPane.CANCEL_OPTION,
                            JOptionPane.WARNING_MESSAGE,
                            null,
                            new String[] {"Cancel"},
                            "Cancel"
                            );
            }
        });
        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                closeWindow();
            }
        });
    }

    private Boolean checkChanges(){
        configuration = new UserConfig();
        configuration.loadParams();

        Boolean user, host, port;
        user = configuration.getKorisnik() == textFieldUser.getText();
        host = configuration.getHost() == textFieldHost.getText();
        port = configuration.getPort() == Integer.valueOf(textFieldPort.getText());

        if(user && host && port)
            return true;

        return false;
    }


}
