import java.util.*;
import java.io.*;
import java.net.*;

public class serv{
	public static void main(String[] args) throws Exception
	{
		ServerSocket serv = new ServerSocket(6262);

		System.out.println("Server Established");

		Socket client = server.accept();
		ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream);
		ObjectInputStream ois = new ObjectInputStream(client.getInputStream);
		System.out.println("Client Connected");

		

	}
}