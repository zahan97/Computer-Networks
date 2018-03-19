import java.util.*;
import java.io.*;

class framing
{	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		//bytestufer();

		bitstuffer();

	}//main

	static void bitstuffer()
	{
		Scanner sc = new Scanner(System.in);

		char data[];
		char sdt[] = new char[200];
		int counter = 0;

		System.out.println("Enter the string:");
		data = sc.next().toCharArray();

		System.out.println("Stuffed String");

		int onec = 0;

		for(int k = 0; k < data.length; k++)
		{
			if(data[k] == '1')
			{
				onec++;

				if(onec == 5)
				{
					sdt[counter++] = '1';
					onec = 0;
				}

				sdt[counter++] = '1';
			}

			else if(data[k] == '0')
			{
				onec = 0;
				sdt[counter++] = '0';
			}
		}

		for(int j = 0; j<sdt.length; j++)
		{
			System.out.print(sdt[j]);
		}

		System.out.println("\nDestuffed String:");

		char des[] = new char[200];
		int remain = 0;
		onec = 0;

		for(int i = 0; i<sdt.length ;i++)
		{
			if(sdt[i] == '1')
			{	
				onec++;
				
				if(onec != 5)
				{
					des[remain++] = '1';
				}
				else
				{
					onec = 0;
				}
			}
			else
			{
				onec = 0;
				des[remain++] = '0';
			}
		}

		for(int j = 0; j<data.length; j++)
		{
			System.out.print(des[j]);
		}

		System.out.println();

	}//bitstuffer


	static void bytestufer()
	{
		Scanner sc = new Scanner(System.in);

		char data[];
		char sdt[] = new char[200];
		int counter = 0;

		System.out.println("Enter the string:");
		data = sc.next().toCharArray();

		System.out.println("The flag is $ and escape is # ");

		sdt[counter++] = '$';

		for(int i = 0; i<data.length; i++)
		{
			if(data[i] == '$' || data[i] == '#')
			{
				sdt[counter++] = '#';
				sdt[counter++] = data[i];
			}

			else
			{
				sdt[counter++] = data[i];
			}

		}//for

		sdt[counter] = '$';

		for(int j = 0; j<sdt.length; j++)
		{
			System.out.print(sdt[j]);
		}

		System.out.println("\nDestuffed String at reciever:");

		char des[] = new char[200];
		int remain = 0;
		boolean esc = false;

		for (int i = 1; i < (sdt.length - 1); i++){
			
			if(sdt[i] == '#' && esc)
			{
				des[remain++] = sdt[i];
				esc = false;
			}	
			else if(sdt[i] == '$' && esc)
			{
				des[remain++] = sdt[i];
				esc = false;
			}
			else if(sdt[i] == '#' || sdt[i] == '$')
			{
				esc = true;
			}
			else
			{
				des[remain++] = sdt[i];
			}	
		}


		for(int j = 0; j<sdt.length; j++)
		{
			System.out.print(des[j]);
		}

		System.out.println();

	}//bytestuffer
}//classm