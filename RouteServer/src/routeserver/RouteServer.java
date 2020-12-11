/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package routeserver;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import static routeserver.Dijkstra.dijkstra;
/**
 *
 * @author esdra
 */
public class RouteServer {
    private static ServerSocket server;
    private static int port = 9876;
    
    /**
     *
     * @param args
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException{
        server = new ServerSocket(port);
        //keep listens indefinitely until receives 'exit' call or program terminates
        int count =1;
        int[][] matrix = ArrayFromFile.getMatrix();
        byte[] origem = new byte[0];
        byte[] destino = new byte[0];
        int decryptOrigem = 0;
        OUTER:
        while (true) {
            System.out.println("Esperando Cli");
            Socket socket = server.accept();
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            
            switch (count) {
                case 1:
                    origem = (byte[]) ois.readObject();
                    count++;
                    decryptOrigem = Integer.parseInt(RSAUtil.decrypt(origem));
                    break;
                case 2:
                    destino = (byte[]) ois.readObject();
                    count++;
                    int decryptDestino = Integer.parseInt(RSAUtil.decrypt(destino));
                    //List<String> res = null;
                    System.out.println("Origem: " + Integer.toString(decryptOrigem));
                    System.out.println("Destino: " + Integer.toString(decryptDestino));
                    //res = Dijkstra.dijkstra(matrix, decryptOrigem, decryptDestino);
                    System.out.println("Tamanho do Retorno da Função: " + dijkstra(matrix, decryptOrigem, decryptDestino).size());
                    
                    //create ObjectOutputStream object
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    //write object to Socket
                    oos.writeObject(dijkstra(matrix, decryptOrigem, decryptDestino));                    
                    //close resources
                    oos.close();
                    ois.close();
                    socket.close();
                    count = 1;
                    break;
                    //socket.close();
                    //break OUTER;    
                default:
                    break OUTER;
            }
        }
        
        System.out.println("Shutting down Socket server!!");
        //close the ServerSocket object
        server.close();
    }
}
