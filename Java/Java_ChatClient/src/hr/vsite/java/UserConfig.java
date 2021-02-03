package hr.vsite.java;

import java.io.*;
import java.util.Properties;

public class UserConfig {

    private static String host;
    private static int port;
    private static String korisnik;

    static{
        loadParams();
    }

    public String getHost() {
        return host;
    }
    public void setHost(String host) {
        this.host = host;
    }
    public int getPort() {
        return port;
    }
    public void setPort(int port) {
        this.port = port;
    }
    public String getKorisnik() {
        return korisnik;
    }
    public void setKorisnik(String korisnik) {
        this.korisnik = korisnik;
    }


    private static final String propertiesFile = "chat.properties";
    private static final String hostPropertieName = "host";
    private static final String portPropertieName = "port";
    private static final String userPropertieName = "user";

    public static void loadParams(){
        Properties props = new Properties();
        InputStream is = null;

        try{
            File f = new File(propertiesFile);
            is = new FileInputStream(f);
        }
        catch( Exception e){
            e.printStackTrace();
            is = null;
        }

        try{
            props.load(is);
        }
        catch( Exception e){
            e.printStackTrace();
        }

        try{
            is.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        host = props.getProperty(hostPropertieName, "192.168.0.1");
        port = Integer.valueOf(props.getProperty(portPropertieName,"8080"));
        korisnik = props.getProperty(userPropertieName, "anonymous");
    }

    public static void saveParamChanges(String user, String host, String port){
        try{
            Properties props = new Properties();
            props.setProperty(hostPropertieName, host);
            props.setProperty(portPropertieName, "" + port);
            props.setProperty(userPropertieName, user);

            File f = new File(propertiesFile);
            OutputStream out = new FileOutputStream(f);
            props.store(out, "User Settings");

            out.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
