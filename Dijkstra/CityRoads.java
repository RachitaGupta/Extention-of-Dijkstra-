import java.util.*;
import org.graphstream.algorithm.util.FibonacciHeap;

public class CityRoads {

	@SuppressWarnings("null")
	public static void main(String args[]){
		String filename="";
		PathShort mycity=new PathShort();
		Scanner sc=new Scanner(System.in);
		System.out.println("Select an option\n1. Input by File\n2. Input by Console");
		int choice=sc.nextInt();
		try{
			// Taking input options
			if(choice==1){
				System.out.println("Enter the name of the input file");
				filename=sc.next();
				mycity.fileInput(filename);
			}
			else if(choice==2){
				mycity.consoleInput();
			}
			Dijkstras citygraph=new Dijkstras(mycity.cities);
			Dijkstras citygraphEnd=new Dijkstras(mycity.cities);
			//Edge e = new Edge();
			double max=0;
			String path="";

			// cost of roads to graph matrix
			for(int i=0;i<mycity.roads;i++){
				int parts[]=new int[3];
				String part[]=mycity.paths[i].split(":");

				int k=0;
				for(String str: part){
					parts[k]=Integer.parseInt(str);
					k++;
				}
				if (parts[2]<0){
					System.out.println("wrong input format\n cost of path cannot be negetive\n");
					break;
				}
				citygraph.addEdge(parts[0],parts[1],parts[2]);
				citygraphEnd.addEdge(parts[1],parts[0],parts[2]);
			}
			citygraph.runDijkstra(mycity.src);
			double[] startToEnd = new double[mycity.cities];
			for(int i=0;i<mycity.cities;i++)
				startToEnd[i]=citygraph.distTo[i];
			double min_cost=citygraph.distTo[mycity.dest];

			//Back to front
			citygraphEnd.runDijkstra(mycity.dest);
			double[] endToStart = new double[mycity.cities];
			for(int i=0;i<mycity.cities;i++)
				endToStart[i]=citygraphEnd.distTo[i];
			double new_min=citygraphEnd.distTo[mycity.src];
			String res[]=new String[mycity.k];
			System.out.println("Shortest Path Cost Before New Path: "+min_cost);

			for(int i=0;i<mycity.k;i++){
				int parts[]=new int[3];
				String part[]=mycity.kroads[i].split(":");

				int k=0;
				for(String str: part){
					parts[k]=Integer.parseInt(str);
					k++;
				}
				if (parts[2]<0){
					System.out.println("wrong input format\n cost of path cannot be -ve\n");
					break;
				}

				double dij_i,dij_j,dij_jEnd;
				dij_i=citygraph.distTo[parts[0]];
				dij_j=citygraph.distTo[parts[1]];
			        dij_jEnd=citygraphEnd.distTo[parts[1]];
				double costs[]=new double[mycity.k];

				if(dij_i+parts[2]+dij_jEnd<min_cost){

					//System.out.println("Bhai in the loop");
					System.out.println("\n###########\n"+mycity.kroads[i]);
					costs[i]=dij_j-dij_i-parts[2];
					if (max<costs[i]) {
						max=costs[i];
						path=mycity.kroads[i];
					}
					System.out.println("Reduction: "+costs[i]);
					System.out.println("Path Src: "+parts[0]+"; Path Dst: "+parts[1]);
				}
				else{
					costs[i]=9999;
				}
			}
			if(path!="")
				System.out.println("Max Reduction is provided by "+path);
			else
				System.out.println("No path reduces length");

		}
		catch(Exception e){
			System.out.println(e);
		}
	}
}
