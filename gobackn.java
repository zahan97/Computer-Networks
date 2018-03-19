import java.io.*;
import java.util.*;
import java.net.*;
import java.lang.Math;

/*
The sender sends a packet from arr which is a number from the range 0 - 2^k - 1
- There's 3 pointers, start, end, pointer. Ignore end, its useless
	- start points to the start of the sliding window,
	- pointer points to the current position in the window
- It sends all packets inside the window one by one in each iteration without checking for ack
- Then it waits for a response from the client/receiver
- If the response is -1, theres an error and it does nothing and moves on
- Otherwise it checks if arr[start] == received message
- In that case, the packet has been acknowledged and the window can slide forward, and wait for the next ack
*/

class Sender{
	Socket socket = null;
	ObjectInputStream inp = null;
	ObjectOutputStream out = null;

	Sender(String address, int port){
		try{
			socket = new Socket(address, port);
			System.out.println("Connected!");
			out = new ObjectOutputStream(socket.getOutputStream());
			out.flush();
			inp = new ObjectInputStream(socket.getInputStream());
			System.out.println("HELLO");
		}
		catch(Exception e){
			System.out.println("Server: Oh noes");
		}
	}

	public void run(int k, int n){
		//Packets
		int arr [] = new int[n];
		//Window size
		int mod = (int) Math.pow(2, k);

		for(int i = 0 ; i < n ; i ++){
			arr[i] = i%mod;
		}

		int start = 0, end = Math.min(n, mod - 2), pointer = 0;

		do{
			try{
				// Send a packet
				System.out.println("Sending " + arr[start + pointer] + " :" + start);
				out.writeObject(arr[start + pointer]);
				pointer = (pointer + 1)%mod;
				// Read ack
				int message = (int) inp.readObject();
				if(message == -1){
					// for errors, or when unexpected packet sent
				}
				else if(message == arr[start]){
					// Increment window if correct ACK was received
					System.out.println("ACK" + message + " received"); 
					start ++; 
					end++; 
					pointer = 0;
				}
 			}
			catch(Exception e){
				break;
			}
		} while(start < n);

		try{
			out.writeObject(-1);
		} catch(Exception e){}

		while(true){
			try{
			socket.close();
			inp.close();
			out.close();
			break;
			}
			catch(Exception e){

			}
		}
	}
}

/*
Client has one pointer -> counter. This is the sliding window of size 1

- It starts by reading a message
- If the message is -1, it stops(this is how i end the sequence)
- Otherwise it checks if received message == counter
- If its not, it gives -1, otherwise an ack
*/

class Client{
	Socket socket = null;
	ObjectInputStream inp = null;
	ObjectOutputStream out = null;

	Client(int port){
		try{
			ServerSocket serversocket = new ServerSocket(port);
			serversocket.setSoTimeout(10000000);
			System.out.println("Client is accepting connections");
			Socket socket = serversocket.accept();	
			System.out.println("Connected!");
			out = new ObjectOutputStream(socket.getOutputStream());
			out.flush();
			inp = new ObjectInputStream(socket.getInputStream());
		}
		catch(Exception e){
			System.out.println("Client: Oh noes");
		}
	}

	public void run(int k){
		int dropped = 0;
		int counter = 0;
		int mod = (int) Math.pow(2, k);
		System.out.println("Running");
		
		do{
			try{
				// Get a packet
				int message = (int) inp.readObject();
				// End of packets - stop
				if(message == -1) break;
				
				// Randomly drop at most 3 packets over time
				if(dropped < 3 && (int) (Math.random()*10) > 7){
					System.out.println("Dropped " + message);
					dropped += 1;
					out.writeObject(-1);
					continue;
				}
				
				// Window size is 1, if the correct one is received
				// Increment the window and send ACK
				if(message == counter){
					System.out.println("Received " + message);
					out.writeObject(message);
					counter = (counter+1)%mod;
				}
				else{
					out.writeObject(-1);
				}
			} catch(Exception e){

			}

		} while(true);

		try{
			socket.close();
			inp.close();
			out.close();
		} catch(Exception e){}
	}
}


class gobackn{
	public static void main(String args []){
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter sequence number size (bits)");
		int k = sc.nextInt();
		System.out.println("Enter number of packets");
		int n = sc.nextInt();

		System.out.println("1. Client\n2.Server");
		int choice = sc.nextInt();

		int port = 5000;
		String address = "localhost";

		if(choice == 2){
			Sender sender = new Sender(address, port);
			sender.run(k, n);
		}
		else{
			Client client = new Client(port);
			client.run(k);
		}
	}
}