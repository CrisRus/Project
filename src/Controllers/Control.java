package Controllers;

import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import java.math.BigInteger;
import java.security.MessageDigest;

public abstract class Control implements Initializable {
    public BorderPane borderPane;
    public StackPane stackPane;

    public void setBorderPane(BorderPane borderPane) {
        this.borderPane = borderPane;
    }


        String encryption(String password){
        String hashText;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(password.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            hashText = number.toString(16);

            while (hashText.length() < 32){
                hashText = "0" + hashText;
            }
            return hashText;
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

}
