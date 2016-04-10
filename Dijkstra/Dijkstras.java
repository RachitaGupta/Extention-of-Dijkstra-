import java.util.ArrayList;
import java.util.Arrays;
import org.graphstream.algorithm.util.FibonacciHeap;

public class Dijkstras {

    class Edge{
        int to, from;
        double weight;

        Edge(int from, int to, double weight){
            this.to = to;
            this.from = from;
            this.weight = weight;
        }

        public String toString(){
            return from+":"+to+":"+weight;
        }
    }

    public ArrayList<ArrayList<Edge>> graph;
    private int edgeTo[];
    public double distTo[];
    private boolean inQ[];
    private FibonacciHeap.Node nodeRef[];
    private int V;

    FibonacciHeap<Double, Integer> pq;

    Dijkstras(int V){
        graph = new ArrayList<>();
        graph.ensureCapacity(V);
        for(int i=0;i<V;++i)graph.add(new ArrayList<>());
        edgeTo = new int[V];
        distTo = new double[V];
        this.V = V;
    }

    public void addEdge(int from, int to, double weight){
        graph.get(from).add(new Edge(from, to, weight));

    }

    public ArrayList getEdge(int from){
        return graph.get(from);

    }

    public void runDijkstra(int s){
        pq = new FibonacciHeap<>();
        inQ = new boolean[V];
        nodeRef = new FibonacciHeap.Node[V];

        Arrays.fill(distTo, Double.POSITIVE_INFINITY);
        distTo[s] = 0;

        nodeRef[s] = pq.add(0.0, s);
        inQ[s] = true;

        while(!pq.isEmpty()){
            int v = pq.extractMin();
            for(Edge e : graph.get(v)){
               relax(e);
            }
        }
    }


    public void relax(Edge e){
        if(!inQ[e.to]){
            distTo[e.to] = distTo[e.from]+e.weight;
            nodeRef[e.to] = pq.add(distTo[e.to], e.to);

            inQ[e.to] = true;
            edgeTo[e.to] = e.from;
        }else if(distTo[e.to] > distTo[e.from] + e.weight){
            distTo[e.to] = distTo[e.from] + e.weight;
            edgeTo[e.to] = e.from;
            pq.decreaseKey(nodeRef[e.to], distTo[e.to]);
        }
    }

    public int[] findPath(int s, int d, int n) {
      int i=d;
      //System.out.println(d);
      int[] actualPath= new int[n];
      actualPath[0]=i;
      int j=1;
      while(i!=s) {
        actualPath[j]=edgeTo[i];
        i=edgeTo[i];
        j++;
      }

      System.out.println(Arrays.toString(actualPath));
      return actualPath;
    }


    /*

    public static void main(String[] args){
        Dijkstras d = new Dijkstras(4);

        d.addEdge(0,1,5);   d.addEdge(3,0,9);
        d.addEdge(0,3,9);   d.addEdge(3,2,4);
        d.addEdge(0,2,8);
        d.addEdge(1,2,12);
        d.addEdge(1,3,15);



        d.runDijkstra(0);

        System.out.println("Results: ");
        for(int i=0;i<d.V;++i){
            System.out.println("Vertex "+i+" edgeTo["+i+"] = "+d.edgeTo[i]+" distTo["+i+"] = "+d.distTo[i]);
        }
    }
    */
}
