import java.util.*;
import java.io.*;

class CRC
{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.println("No. of bits in frame:");
		int n = sc.nextInt();
		
		System.out.println("No. of bits in generator:");
		int v = sc.nextInt();

		int totlen = n + v -1;

		int divi[] = new int[totlen];
		int disor[] = new int[v];
		int rem[] = new int[totlen];

		System.out.println("Enter the frame:");
		for(int i=0;i<n;i++){
			divi[i] = sc.nextInt();
		}
		
		System.out.println("Enter the generator:");
		for(int i = 0; i<v; i++)
			disor[i] = sc.nextInt();

		for(int i = n; i < totlen; i++)
			divi[i] = 0;

		for(int i=0;i<totlen;i++)
			System.out.print(divi[i]+" ");

		for(int i=0;i<totlen;i++)
			rem[i] = divi[i];
		
		System.out.println("\nCRC Code-");
		
		int cur = 0;
		int j;
		while(true)
		{
			for(j=0;j<v;j++)
				rem[cur + j] = rem[cur + j]^disor[j];

			while(rem[cur] == 0 && cur != rem.length -1)
				cur++;

			if(rem.length - cur < v)
				break;
		}

		int adder = totlen - (v - 1);

		int finalframe[] = new int[totlen];

		for(int i = 0; i < totlen; i++)
			finalframe[i] = divi[i];

		for(int i = adder-1; i<totlen; i++)
			finalframe[i] = finalframe[i]^rem[i];

		for(int i = 0;i<totlen;i++)
			System.out.print(finalframe[i] + " ");

		cur = 0;
		j = 0;
		while(true)
		{
			for(j=0;j<v;j++)
				finalframe[cur + j] = finalframe[cur + j]^disor[j];

			while(finalframe[cur] == 0 && cur != finalframe.length -1)
				cur++;

			if(finalframe.length - cur < v)
				break;
		}

		int z;
		for(z=0; z<totlen; z++)
		{
			if(finalframe[z] != 0)
				break;
		}

		if(z != totlen)
			System.out.println("\nError detected");
		else
			System.out.println("\nNo Error Detected");




		System.out.println();

	}//main
}