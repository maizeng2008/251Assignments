import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;


class Node{
    
    public int value; /*The nodes connected by the edge*/
    public boolean pass; /*Integer so we can use Comparator*/
    
    Node(int value){
    	
    		this.value = value;
    		this.pass = false;
    		
    }
}

public class balloon {

	int number_problems = 0;
	static ArrayList<ArrayList<Node>> listOfBalloons = new ArrayList<ArrayList<Node>>();
	ArrayList<Integer> spaces = new ArrayList<Integer>();
	
	balloon(String file) {
		
        try {
			Scanner f = new Scanner(new File(file));
            number_problems = Integer.parseInt(f.nextLine()); /*first line is the number of problems*/
            String stringSpaces[] = f.nextLine().split("\\s+");
            //int spaces[] = new int[stringSpaces.length];
            for(int i = 0; i < stringSpaces.length; i++)
            {
            		spaces.add(Integer.parseInt(stringSpaces[i]));
            }

            while(f.hasNext())
            {
            		String[] line = f.nextLine().split("\\s+");
            		ArrayList<Node> balloonsPosition = new ArrayList<Node>();
            		for(int i = 0; i < line.length; i++)
            		{
            			Node n = new Node(Integer.parseInt(line[i]));
            			balloonsPosition.add(n);
            		}
            		listOfBalloons.add(balloonsPosition);
            }
            
            /*******************************************************************************************
             *      below is just to test whether the extracting from the file working or not          *
             *******************************************************************************************/
            /*for(int i = 0; i < stringSpaces.length; i++)
            {
            		System.out.println(" " + listOfBalloons.get(i));
            }*/

            f.close();
            
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated constructor stub
        
	}
	
	public static int[] calSubset(balloon g)
	{
		int[] solutions = new int[g.number_problems];//create an array to store the solution
		ArrayList<ArrayList<Node>> listOfBalloonsCopy = listOfBalloons;
		for(int i = 0; i < listOfBalloonsCopy.size(); i++)
		{
			//System.out.println(" i  = " + i);
			int initial = listOfBalloonsCopy.get(i).get(0).value;
			//listOfBalloonsCopy.get(i).get(0).pass = true;
			//int count = 0;//count is to count how many balloons that i have passed
			int wallCounter = 0;//wallCounter is to know that whether the arrow hits the wall
			//System.out.println(""+count);
			while(true)
			{
				//System.out.println("wallCounter is "+wallCounter);
				//System.out.println(""+ listOfBalloonsCopy.get(i).size());
				if(listOfBalloons.get(i).get(wallCounter).value == initial && !listOfBalloons.get(i).get(wallCounter).pass)
				{
					//System.out.println(""+count);
					listOfBalloons.get(i).get(wallCounter).pass = true;
					initial = initial - 1;
					//count++;
					//System.out.println(""+count);
				}
				if(wallCounter == listOfBalloonsCopy.get(i).size() - 1 || initial == 0)
				{
					solutions[i]++;
					wallCounter = 0;
					//count++;
					//initial = initial - 1;
					boolean anotherRound = false;
					for(int k = 0; k < listOfBalloons.get(i).size(); k++)
					{
						if(!listOfBalloons.get(i).get(k).pass)
						{
							anotherRound = true;//check if i need go on another round to check
							initial = listOfBalloons.get(i).get(k).value;
							break;
						}
					}
					if(anotherRound == false)
						break;
				}
				wallCounter++;
			}
		}
		
		return solutions;
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//long startTime = System.currentTimeMillis();
        String file = "testBalloons.txt";
        balloon g = new balloon(file);
        int solutions[] = calSubset(g);
        try (PrintWriter out = new PrintWriter("testBalloons_solution.txt")) {
	        for(int i = 0; i < solutions.length; i++)
	        {
	        		out.println("" + solutions[i]);
	        }
	        out.close();
        } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //long stopTime = System.currentTimeMillis();
        //long elapsedTime = stopTime - startTime;
        //System.out.println(elapsedTime);
        //System.out.println(g);
	}

}
