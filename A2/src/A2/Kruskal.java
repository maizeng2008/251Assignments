package A2;
import java.util.*;

public class Kruskal{

    public static WGraph kruskal(WGraph g){
    		WGraph t = new WGraph();
    		DisjointSets p = new DisjointSets(g.getNbNodes());
    		for(int i = 0; i< g.listOfEdgesSorted().size(); i++)
    		{
    			if(IsSafe(p,g.listOfEdgesSorted().get(i)))
    			{
    				t.addEdge(g.listOfEdgesSorted().get(i));
    				p.union(g.listOfEdgesSorted().get(i).nodes[0], g.listOfEdgesSorted().get(i).nodes[1]);
    			}
    		}
        return t;
    }

    public static Boolean IsSafe(DisjointSets p, Edge e){
	    	if(p.find(e.nodes[0]) == p.find(e.nodes[1]))
	    		return false;
        return true;
    }

    public static void main(String[] args){

        String file = args[0];
        WGraph g = new WGraph(file);
        WGraph t = kruskal(g);
        System.out.println(t);

   } 
}
