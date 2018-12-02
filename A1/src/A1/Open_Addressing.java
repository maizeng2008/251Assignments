package A1;

import static A1.main.*;

public class Open_Addressing {

    public int m; // number of SLOTS AVAILABLE
    public int A; // the default random number
    int w;
    int r;
    public int[] Table;

    //Constructor for the class. sets up the data structure for you
    protected Open_Addressing(int w, int seed) {

        this.w = w;
        this.r = (int) (w - 1) / 2 + 1;
        this.m = power2(r);
        this.A = generateRandom((int) power2(w - 1), (int) power2(w), seed);
        this.Table = new int[m];
        //empty slots are initalized as -1, since all keys are positive
        for (int i = 0; i < m; i++) {
            Table[i] = -1;
        }

    }

    /**
     * Implements the hash function g(k)
     */
    public int probe(int key, int i) {
    		int h = (this.A)*key % power2(this.w) >> (this.w-this.r);
    		return  (h+i) % power2(this.r);
    }

    /**
     * Checks if slot n is empty
     */
    public boolean isSlotEmpty(int hashValue) {
        return Table[hashValue] == -1;
    }

    /**
     * Inserts key k into hash table. Returns the number of collisions
     * encountered
     */
    public int insertKey(int key) {
        int encountered = 0;//the number of collision AKA the number of keys or slots that this key has visited
        int buff = key; // this variable is to store the "key" value and also then new "key" value
        boolean notAvail = true;
        buff = probe(key,encountered);
		for(int i = 0; i < m; i++)
		{
			if(key == Table[i])
				return 0;
		}
        while(notAvail)//if i did not find an empty slot
        {
        		if(Table[buff] == -1)//there is an empty which means available then break
        			break;
        		encountered++;//increase the number of collisions
	        	buff = probe(key,encountered);//change the slot index that I need to use to insert the key
	        	if(encountered == m)
	        		return encountered;
        }
        Table[buff] = key;//insert the "key"
        return encountered;
    }

    /**
     * Removes key k from hash table. Returns the number of collisions
     * encountered
     */
    public int removeKey(int key) {
	    	int slotVisited = 0;//store the number of slot that this key has been visited
	    	int i = 0;
	    	int buff = probe(key,i);//this variable is to store the "key" value and also then new "key" value
	    	while(Table[buff] != key)
	    	{
	    		i++;
	    		slotVisited++;//increase the number of slot that this key have been visited
	    		buff = probe(key,i);//renew the buff value	   
	    		if(slotVisited == m)//If the key is not in the hash table, the method should simply not change the hash table, 
	    			return slotVisited;//and output the number of slots visited.
	    	}
	    	Table[buff] = -1;//remove the key to change the value in this slot to -1
	    	return slotVisited;//output the number of collisions
    }

}
