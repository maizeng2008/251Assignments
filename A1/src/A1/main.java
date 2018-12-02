package A1;

import A1.Chaining.*;
import A1.Open_Addressing.*;

import static A1.main.power2;

import java.io.*;
import java.util.*;

public class main {

    /**
     * Calculate 2^w
     */
    public static int power2(int w) {
        return (int) Math.pow(2, w);
    }

    /**
     * Uniformly generate a random integer between min and max, excluding both
     */
    public static int generateRandom(int min, int max, int seed) {
        Random generator = new Random();
        //if the seed is equal or above 0, we use the input seed, otherwise not.
        if (seed >= 0) {
            generator.setSeed(seed);
        }
        int i = generator.nextInt(max - min - 1);
        return i + min + 1;
    }

    /**
     * export CSV file
     */
    public static void generateCSVOutputFile(String filePathName, ArrayList<Double> alphaList, ArrayList<Double> avColListChain, ArrayList<Double> avColListProbe) {
        File file = new File(filePathName);
        FileWriter fw;
        try {
            fw = new FileWriter(file);
            int len = alphaList.size();
            fw.append("Alpha");
            for (int i = 0; i < len; i++) {
                fw.append("," + alphaList.get(i));
            }
            fw.append('\n');
            fw.append("Chain");
            for (int i = 0; i < len; i++) {
                fw.append("," + avColListChain.get(i));
            }
            fw.append('\n');
            fw.append("Open Addressing");
            for (int i = 0; i < len; i++) {
                fw.append(", " + avColListProbe.get(i));
            }
            fw.append('\n');
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        /*===========PART 1 : Experimenting with n===================*/
        //Initializing the three arraylists that will go into the output 
        ArrayList<Double> alphaList = new ArrayList<Double>();
        ArrayList<Double> avColListChain = new ArrayList<Double>();
        ArrayList<Double> avColListProbe = new ArrayList<Double>();

        //Keys to insert into both hash tables
        int[] keysToInsert = {164, 127, 481, 132, 467, 160, 205, 186, 107, 179,
            955, 533, 858, 906, 207, 810, 110, 159, 484, 62, 387, 436, 761, 507,
            832, 881, 181, 784, 84, 133, 458, 36};

        //values of n to test for in the experiment
        int[] nList = {2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32};
        //value of w to use for the experiment on n
        int w = 10;

        for (int n : nList) {

            //initializing two hash tables with a seed
            Chaining MyChainTable = new Chaining(w, 137);
            Open_Addressing MyProbeTable = new Open_Addressing(w, 137);
            /*Use the hash tables to compute the average number of 
                        collisions over keys keysToInsert, for each value of n. 
                        The format of the three arraylists to fillis as follows:
                        
                        alphaList = arraylist of all tested alphas 
                                   (corresponding to each tested n)
                        avColListChain = average number of collisions for each
                                         Chain experiment 
                                         (make sure the order matches alphaList)
                        avColListProbe =  average number of collisions for each
                                         Linear Probe experiment
                                           (make sure the order matches)
                        The CSV file will output the result which you can visualize
             */
            //ADD YOUR CODE HERE
            /**
             * this part is for the Chaining
             */
            int mC = MyChainTable.m;//get the m for Chaining
            double alphaC = ((double)n)/((double)mC);
            double colChain = 0;
            for(int i = 0; i < n; i++)
            {
            		colChain = colChain + MyChainTable.insertKey(keysToInsert[i]);
            }
            double avColChain = (colChain)/(n);
            avColListChain.add(avColChain);
            /**
             * this part is for the linear probe
             */
            int mP = MyProbeTable.m;//get the m for probe
            double alphaP = n/mP;
            double colProbe = 0;
            for(int i = 0; i < n; i++)
            {
            		colProbe = colProbe + MyProbeTable.insertKey(keysToInsert[i]);
            }
            double avColProbe = (colProbe)/ (n);
            avColListProbe.add(avColProbe);
            alphaList.add(alphaC);//i think alpha should be the same with chain and probe
        }

        generateCSVOutputFile("n_comparison.csv", alphaList, avColListChain, avColListProbe);

        /*===========    PART 2 : Test removeKey  ===================*/
 /* In this exercise, you apply your removeKey method on an example.
        Make sure you use the same seed, 137, as you did in part 1. You will
        be penalized if you don't use the same seed.
         */
        //Please not the output CSV will be slightly wrong; ignore the labels.
        ArrayList<Double> removeCollisions = new ArrayList<Double>();
        ArrayList<Double> removeIndex = new ArrayList<Double>();
        int[] keysToRemove = {6, 8, 164, 180, 127, 3, 481, 132, 4, 467, 5, 160,
            205, 186, 107, 179};
        Open_Addressing MyProbeTableForRemove = new Open_Addressing(w, 137);//
        int n = 16;//table from task 1 with n=16.
        for(int i = 0; i < n; i++)
        {
        		MyProbeTableForRemove.insertKey(keysToInsert[i]);//insert the first 16 elements of keysToInsert.
        }
        
        int removeColProbe = 0;
        
        for(int i = 0; i < 16; i++)
        {
        		removeColProbe = MyProbeTableForRemove.removeKey(keysToRemove[i]);//Call removeKey with each of these keys.
        		removeCollisions.add((double) removeColProbe);//Store the number of collisions associated with each removal operation in the arraylist removeCollisions
        		removeIndex.add((double) i);//as well as the index of the key you just attempted to remove in removeIndex
        }
        
        generateCSVOutputFile("remove_collisions.csv", removeIndex, removeCollisions, removeCollisions);

        /*===========PART 3 : Experimenting with w===================*/

 /*In this exercise, the hash tables are random with no seed. You choose 
                values for the constant, then vary w and observe your results.
         */
        //generating random hash tables with no seed can be done by sending -1
        //as the seed. You can read the generateRandom method for detail.
        int randomNumber = 0;
        //Chaining MyChainTable = new Chaining(w, -1);
        //Open_Addressing MyProbeTable = new Open_Addressing(w, -1);
        //Lists to fill for the output CSV, exactly the same as in Task 1.
        ArrayList<Double> alphaList2 = new ArrayList<Double>();
        ArrayList<Double> avColListChain2 = new ArrayList<Double>();
        ArrayList<Double> avColListProbe2 = new ArrayList<Double>();
        /*
         * get the array we need to test w
         */
        int[] keysToInsertForTestW = new int[32];
        for(int i = 0; i < 32; i++)
        {
        		keysToInsertForTestW[i] = -1;
        }
        for(int i = 0; i < 32; i++)//create array keys to insert for testing the effect of w
        {

        		keysToInsertForTestW[i] = generateRandom(0,500,-1);//store value
        }

        //System.out.println("finish task 3");
        for(int i = 7; i < 17; i++) {
        		
        	 	int[] nList3 = {2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32};
        	 	int totalNum = 0;
        	 	for(int k : nList3)
        	 	{
        	 		totalNum = totalNum+k;
        	 	}
        	 	double avColChain = 0.0;
        	 	double avColProbe = 0.0;
        	 	double avAlph = 0.0;
        	 	for (int n3 : nList3) {
		        Chaining MyChainTable = new Chaining(i, -1);
		        Open_Addressing MyProbeTable = new Open_Addressing(i, -1);
		        /*
				 *finish the task3
		         */
		        //all needed for chain
		        int mC = MyChainTable.m;//get the m for Chaining
		        double alphaC = n3/((double)mC);
		        int colChain = 0;
		        //all needed for probe
		        int colProbe = 0;
		        for(int j = 0; j < n3; j++)
		        {
		        		//randomNumber = generateRandom(0,500,-1);
		        		colChain = colChain + MyChainTable.insertKey(keysToInsertForTestW[j]);
		        		colProbe = colProbe + MyProbeTable.insertKey(keysToInsertForTestW[j]);
		        	}
		        avColChain = avColChain+((double)colChain)/n3;
		        avColProbe = avColProbe+((double)colProbe)/n3;
		        avAlph = avAlph + alphaC;
	        	 	}
        	 		avColListChain2.add(((double) avColChain)/16);
 		        avColListProbe2.add(((double) avColProbe)/16);
 		        alphaList2.add(avAlph/16);
        	 	}

        //ADD YOUR CODE HERE
        generateCSVOutputFile("w_comparison.csv", alphaList2, avColListChain2, avColListProbe2);

    }

}
