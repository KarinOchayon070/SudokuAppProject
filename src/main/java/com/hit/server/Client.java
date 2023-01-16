package com.hit.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hit.algorithm.Main;

public class Client {
	
	public Gson gson = new GsonBuilder().create();
    public PrintWriter writer;
    public Response response;
    public Request request;
    public Scanner reader;
    public Socket toServer;
    public int port = 6000;
    
   public static void main(String[] args) throws IOException, ClassNotFoundException{
	   
	   Socket myServer = new Socket("localhoxt", 6000);
	   ObjectOutputStream output = new ObjectOutputStream(myServer.getOutputStream());
	   ObjectInputStream input = new ObjectInputStream(myServer.getInputStream());
	   
	   String messageFromServer =(String)input.readObject();
	   System.out.println(messageFromServer);
	   
	   output.writeObject("bla");
	   output.flush();
	   
	   output.close();
	   input.close();
	   myServer.close();
	   
	   
	   
   }
   

}
