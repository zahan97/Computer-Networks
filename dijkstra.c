#include<stdio.h>
#define infinity 999
 
void dijkstra(int n,int v,int cost[10][10],int dist[])
{
    int i,u,count,w,flag[10],min;
    
    for(i=1;i<=n;i++)
    {
        flag[i]=0;
        dist[i]=cost[v][i];
    }
    
    count=2;

    while(count<=n)
    {
        min=9999;

        for(w=1;w<=n;w++)
            if(dist[w]<min && !flag[w])
            {   
                min=dist[w];
                u=w;
            }

        flag[u]=1;
        count++;
        for(w=1;w<=n;w++)
            if((dist[u]+cost[u][w]<dist[w])&& !flag[w])
                dist[w]=dist[u]+cost[u][w];
    }
}
 
void main()
{
    int n,v,i,j,cost[10][10],dist[10];

    printf("Enter the no of nodes ");
    scanf("%d",&n);
    
    printf("Enter the cost matrix\n");
    for(i=1;i<=n;i++)
    {
        for(j=1;j<=n;j++)
        {
            scanf("%d",&cost[i][j]);
            if(cost[i][j]==0)
                cost[i][j]=infinity;
        }
    }
    
    printf("\nEnter the source vertex ");
    scanf("%d",&v);
    
    dijkstra(n,v,cost,dist);
    
    printf("\nShortest path :\n");
    for(i=1;i<=n;i++)
        if(i!=v)
            printf("%d to %d cost = %d\n",v,i,dist[i]);
}

/*output: 
Enter the no of nodes 
4

 Enter the cost matrix 
0 1 0 4 
1 0 2 0 
0 2 0 3 
4 0 3 0

Enter the source vertex 1

Shortest path : 
1 to 2 cost = 1 
1 to 3 cost = 3 
1 to 4 cost = 4 
*/
