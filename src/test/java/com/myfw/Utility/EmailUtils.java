package com.myfw.Utility;

import javax.mail.*;
import javax.mail.search.FlagTerm;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class EmailUtils {


    public static String check(String host, String subject, String user, String password) throws InterruptedException {
        String emailtext = null;
        try {

            // create properties
            Properties properties = new Properties();

            properties.put("mail.imap.host", host);
            properties.put("mail.imap.port", "993");
            properties.put("mail.imap.starttls.enable", "true");
            properties.put("mail.imap.ssl.trust", host);

            Session emailSession = Session.getDefaultInstance(properties);

            // create the imap store object and connect to the imap server
            Store store = emailSession.getStore("imaps");

            store.connect(host, user, password);

            // create the inbox object and open it
            Folder inbox = store.getFolder("Inbox");
            inbox.open(Folder.READ_WRITE);


            // retrieve the messages from the folder in an array and print it
            Message[] messages = inbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));
            System.out.println("messages.length---" + messages.length);

                Message message = messages[messages.length-1];
                message.setFlag(Flags.Flag.SEEN, true);
                if(message.getSubject().contains(subject)) {
                    System.out.println("---------------------------------");
                    emailtext = message.getContent().toString();
                }


            inbox.close(false);
            store.close();

        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(emailtext==null){
            Thread.sleep(30000);
        }
        return emailtext;
    }

    public static String getOtp() throws IOException, InterruptedException {
        Properties prop = new Properties();
        prop.load(new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/config/email.properties"));
        String host =  prop.getProperty("host");
        String subject = prop.getProperty("subject");
        String username = prop.getProperty("gmail_username");
        String password = prop.getProperty("gmail_password");

        String emailText =  check(host, subject, username, password);
        String regex = "[^\\d]+";
        String[] OTP = emailText.split(regex);
        int otp = OTP.length;
        emailText = OTP[otp-1];
        System.out.println("OTP: " + emailText);

        return emailText;
    }




    public static void main(String[] args) throws IOException, InterruptedException {
Thread.sleep(5000);
        String emailOtp = EmailUtils.getOtp();
        char[] otp = new char[emailOtp.length()];
        for (int i = 0; i < emailOtp.length(); i++) {
            otp[i] = emailOtp.charAt(i);
            System.out.println(otp[i]);
        }

    }

}
