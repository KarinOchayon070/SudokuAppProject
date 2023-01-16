package com.hit.server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Server {
	
	private static boolean isServerUp = true;

    public static void main(String[] args) throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(100);
        //server is up
        ServerSocket server = new ServerSocket(6000);
        System.out.println("Server Is Up");
        Socket request;

        while (isServerUp) {
            try {
            	//I want my server to listen to clients requests
                request = server.accept();
                pool.execute(new HandleRequest(request));
//                new Thread(new HandleRequest(client)).start();
            } catch (IOException e) {
                System.out.println("Timeout");
            }
        }
        pool.shutdown();
        server.close();
    }
   
}
