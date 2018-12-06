import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
/*
 * collaborate with Yudong Zhou
 * student ID is 260721223
 */

class Element{
    
    public int value; /*The nodes connected by the edge*/
    public boolean visit; /*Integer so we can use Comparator*/
    
    Element(int value){
    	
    		this.value = value;
    		this.visit = false;
    		
    }
}



public class mancala {
	int number_problems = 0;
	static ArrayList<ArrayList<Element>> listOfBoards = new ArrayList<ArrayList<Element>>();
	ArrayList<Integer> spaces = new ArrayList<Integer>();	
	
	public mancala(String file) {
		// TODO Auto-generated constructor stub
		try {
			Scanner f = new Scanner(new File(file));
			number_problems = Integer.parseInt(f.nextLine()); /*first line is the number of problems*/
	        //String stringSpaces[] = f.nextLine().split("\\s+");
	        
            while(f.hasNext())
            {
            		String[] line = f.nextLine().split("\\s+");
            		ArrayList<Element> pebblesPosition = new ArrayList<Element>();
            		for(int i = 0; i < line.length; i++)
            		{
            			Element n = new Element(Integer.parseInt(line[i]));
            			pebblesPosition.add(n);
            		}
            		listOfBoards.add(pebblesPosition);
            }
            
            
            /*for(int i = 0; i < number_problems; i++)
            {
            		for(int j = 0; j < 12; j++)
            		{
            			System.out.print("  " + listOfBoards.get(i).get(j).value);
            		}
            		System.out.println("\n");
            }*/
            
            f.close();
            
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		// TODO Auto-generated method stub
		String file = "testMancala.txt";
        mancala g = new mancala(file);
        int solutions[] = new int[g.number_problems];
        try (PrintWriter out = new PrintWriter("testMancala_solution.txt")) {
	        for(int i = 0; i < g.number_problems; i++)
	        {
	    			/*System.out.println("Now it is " + i);
				System.out.println("Original is ");*/
				/*for(int m = 0; m < 12; m++)
				{
					System.out.print(" " + g.listOfBoards.get(i).get(m).value);
				}
				System.out.println("\n");*/
	        		solutions[i] = calValue(g.listOfBoards.get(i));
	        		//System.out.println("The number " + i + " is " +solutions[i]);
				//System.out.println("\n");
	        		out.println(""+solutions[i]);
	        }
	        out.close();
        } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        long end = System.currentTimeMillis();
        System.out.println("" + (end - start));
        
	}

	private static int calValue(ArrayList<Element> arrayList) {
		// TODO Auto-generated method stub
		int w = 0;
		int y = 0;
		boolean check1 = false;
		boolean check2 = false;
		for(int i = 0; i < 12; i++)
		{
			if(arrayList.get(i).value == 1)
			{
				if(i < 11)
				{
					if(arrayList.get(i+1).value == 1) // if there are two pebbles beside each other -> 11 
					{
						if(i > 0)
						{
							if(arrayList.get(i-1).value == 0)//if there is an empty spot to the left of them -> 011
							{
								check1 = true;
								ArrayList<Element> copy1 = new ArrayList<Element>();
								for(int c = 0; c < 12; c++)
								{
									Element node = new Element(arrayList.get(c).value);
									copy1.add(node);
								}
								copy1.get(i-1).value = 1;
								copy1.get(i).value = 0;
								copy1.get(i+1).value = 0;
								//System.out.println("1 is ");
								//System.out.println("\n");
								/*for(int m = 0; m < 12; m++)
								{
									System.out.print(" " + copy1.get(m).value);
								}
								System.out.println("\n");*/
								w = calValue(copy1);
							}
						}
						if(i < 10)
						{
							if(arrayList.get(i+2).value == 0)//if there is an empty spot to the right of them -> 110
							{
								check2 = true;
								ArrayList<Element> copy2 = new ArrayList<Element>();
								for(int c = 0; c < 12; c++)
								{
									Element node = new Element(arrayList.get(c).value);
									copy2.add(node);
								}
								copy2.get(i).value = 0;
								copy2.get(i+1).value = 0;
								copy2.get(i+2).value = 1;
								//System.out.println("2 is ");
								//System.out.println("\n");
								/*for(int m = 0; m < 12; m++)
								{
									System.out.print(" " + copy2.get(m).value);
								}
								System.out.println("\n");*/
								y = calValue(copy2);
							}
						}
					}
				}
			}
		}
		//System.out.println("Now the original is ");
		/*for(int c = 0; c < 12; c++)
		{
			System.out.print(" " + arrayList.get(c).value);
		}*/
		//System.out.println("\n");
		int count = 0;
		for(int i = 0; i < 12; i++)
		{
			if(arrayList.get(i).value == 1)
			{
				count++;
			}
		}
		//System.out.println("count is " + count + "\n" + "w is " + w + " y is " + y + "\n");
		if(w < count && check1)
		{
			count = w;
		}
		if(y < count &&  check2)
		{
			count = y;
		}
		
		return count;
	}


}
