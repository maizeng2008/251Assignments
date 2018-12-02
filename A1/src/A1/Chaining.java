package A1;

import java.util.*;
import static A1.main.*;

public class Chaining {

	public int m; // number of SLOTS AVAILABLE
	public int A; // the default random number
	int w;
	int r;
	public ArrayList<ArrayList<Integer>> Table;

	//Constructor for the class. sets up the data structure for you
	protected Chaining(int w, int seed) {
		this.w = w;
		this.r = (int) (w - 1) / 2 + 1;
		this.m = power2(r);
		this.Table = new ArrayList<ArrayList<Integer>>(m);
		for (int i = 0; i < m; i++) {
			Table.add(new ArrayList<Integer>());
			//Table.add(null);
		}
		this.A = generateRandom((int) power2(w - 1), (int) power2(w), seed);
	}

    /**
     * Implements the hash function h(k)
     */
    public int chain(int key) {
    		return (this.A)*key % power2(this.w) >> (this.w-this.r) ; // why i need this method?
    }

    /**
     * Checks if slot n is empty
     */
    public boolean isSlotEmpty(int hashValue) {
        return Table.get(hashValue).size() == 0;
    }

    /**
     * Inserts key k into hash table. Returns the number of collisions
     * encountered
     */
    public int insertKey(int key) {
        int encountered = 0;
        int buff = key; // this variable is to store the "key" value and also then new "key" value

        for(ArrayList<Integer>n : this.Table)
        {
        		if(n.size() != 0)
        		{
        			if(n.contains(key))
        				return 0;
        		}
        }
        buff = chain(key);

        Table.get(buff).add(key);
        	encountered = Table.get(buff).size()-1;//the number of collisions is the size of the list Table.get(chain(key)) - 1
        return encountered;//since this key has met this number of keys.
    }

}
