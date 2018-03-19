#include <stdio.h>
#include <stdlib.h>

struct node
{
	int dist[20];
	int from[20];
}rt[10];

int main()
{
	int costmat[20][20];

	int i,j,n,k,count = 0;

	printf("Enter the no. of nodes:\n");
	scanf("%d", &n);

	printf("Enter the cost matrix:\n");

	for(i=0; i<n; i++)
		for(j=0; j<n; j++)
		{
			scanf("%d",&costmat[i][j]);
			if(i == j)
				costmat[i][j] = 0;

			rt[i].dist[j] = costmat[i][j];
			rt[i].from[j] = j;
		}

	do
	{
		count = 0;

		for(i=0; i<n; i++)
			for(j=0; j<n; j++)
				for(k=0; k<n; k++)
				{
					if(rt[i].dist[j] > costmat[i][k] + rt[k].dist[j])
					{
						rt[i].dist[j] = costmat[i][k] + rt[k].dist[j];
						rt[i].from[j] = k;
						count++;
					}
				}

	}while(count != 0);

	for(i=0; i<n; i++)
	{	
		printf("For router %d:\n", i+1);
		for(j=0;j<n;j++)
		{
			printf("%d -> %d : cost = %d\n", j+1, rt[i].from[j] + 1, rt[i].dist[j]);
		}
	}
	printf("\n");
}