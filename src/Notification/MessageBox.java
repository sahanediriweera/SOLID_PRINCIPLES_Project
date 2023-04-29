package Notification;

import javax.swing.*;
import java.awt.*;

public class MessageBox extends Frame {
    public MessageBox(){

    }

    public MessageBox(String message){
        setSize(200,200);
        setVisible(true);
        setLocationRelativeTo(null);
        JOptionPane.showMessageDialog(this,message,"Error",JOptionPane.INFORMATION_MESSAGE);
    }
}
