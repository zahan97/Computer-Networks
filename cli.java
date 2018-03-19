import java.util.*;
import java.io.*;
import java.net.*;

public class cli{
	public static void main(String[] args) throws Exception
	{	
		Scanner sc = new Scanner(System.in);

		Socket client = new Socket("localhost",6262);

		ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream);
		ObjectInputStream ois = new ObjectInputStream(client.getInputStream);
		System.out.println("Server Connected");

	

	}
}