package clientv1;
/**
 * client
 * @author William Gardineer
 */
import java.net.*;
import java.io.*;

public class clientv1 {

    public static void main(String[] args) throws IOException {
        
        String host = "Router 0";
        String ilc = "[0:1:3:7]";
        
        System.out.println("Host: " + host + "\n" + "Initial Least Cost: " + ilc);
        System.out.println();
        
        ServerSocket listen = new ServerSocket(7777);
        Socket socket = new Socket("192.168.56.101", 7777);
        
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        
        while(true) {
            socket = listen.accept();
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.println("Remote: " + host + "\n" + "Initial Least Cost " + ilc);
                
            while (true) {
                String output = input.readLine();
                if (output != null)
                    System.out.println(output);
            }
        }
    }   
}