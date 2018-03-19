import java.util.*;
import java.io.*;

class leakybucket
{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n, i, g;
		
		System.out.println("Enter the size of the bucket:");
		n = sc.nextInt();

		System.out.println("Enter the no of groups:");
		g = sc.nextInt();

		int pckno[] = new int[g];
		int inbwd[] = new int[g];
		int totinbwd = 0, totpck = 0;

		for(i=0; i < g; i++)
		{
			System.out.println("Enter the no. of packets in grp"+i);
			pckno[i] = sc.nextInt();

			totpck += pckno[i];

			do{
				System.out.println("Bucket full");
				System.out.println("Enter a value less than "+(n - totpck));
				pckno[i] = sc.nextInt();
			}while(totpck + pckno[i] > n);

			System.out.println("Enter the bandwidth of grp"+i);
			inbwd[i] = sc.nextInt();

			totinbwd += pckno[i]*inbwd[i];
		}

		System.out.println("The total required bandwidth is "+totinbwd);

		System.out.println("Enter the output bandwidth:");
		int outbwd = sc.nextInt();

		int temp = totinbwd, rempck = totpck;

		while(temp >= outbwd && rempck > 0)
		{
			System.out.println("Data Sent\n"+"Remaining Packets = "+(--rempck));
			System.out.println("Remaining Bandwidth: "+(temp -= outbwd));

			if(outbwd > temp && rempck > 0)
			{
				System.out.println(rempck+"packet(s) discarded");
			}
		}
		System.out.println();
	}
}












