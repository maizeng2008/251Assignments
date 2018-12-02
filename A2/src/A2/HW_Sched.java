/**
 * collaborator from github dont know his student number
 * but the account name is postylem
 * only the last question is from his idea
 * the github address is 
 * https://github.com/postylem/2018-Comp251
 * 
*/
package A2;
import java.util.*;
class Assignment implements Comparator<Assignment>{
	int number;
	int weight;
	int deadline;
	int timeNow = 1;
	
	protected Assignment() {
	}
	
	protected Assignment(int number, int weight, int deadline) {
		this.number = number;
		this.weight = weight;
		this.deadline = deadline;
	}
	
	
	
	/**
	 * This method is used to sort to compare assignment objects for sorting. 
	 * The way you implement this method will define which order the assignments appear in when you sort.
	 * Return 1 if a1 should appear after a2
	 * Return -1 if a1 should appear before a2
	 * Return 0 if a1 and a2 are equivalent 
	 */
	@Override
	public int compare(Assignment a1, Assignment a2) {
		//YOUR CODE GOES HERE, DONT FORGET TO EDIT THE RETURN STATEMENT
		if(a1.weight > a2.weight)
			return -1;
		else if(a1.weight < a2.weight)
			return 1;
		else 
			return 0;
	}
}

public class HW_Sched {
	ArrayList<Assignment> Assignments = new ArrayList<Assignment>();
	int m;
	int lastDeadline = 0;
	
	protected HW_Sched(int[] weights, int[] deadlines, int size) {
		for (int i=0; i<size; i++) {
			Assignment homework = new Assignment(i, weights[i], deadlines[i]);
			this.Assignments.add(homework);
			if (homework.deadline > lastDeadline) {
				lastDeadline = homework.deadline;
			}
		}
		m =size;
	}
	
	
	/**
	 * 
	 * @return Array where output[i] corresponds to when assignment #i will be completed. output[i] is 0 if assignment #i is never completed.
	 * The homework you complete first will be given an output of 1, the second, 2, etc.
	 */
	public int[] SelectAssignments() {
		//Use the following command to sort your Assignments: 
		//Collections.sort(Assignments, new Assignment());
		//This will re-order your assignments. The resulting order will depend on how the compare function is implemented
		Collections.sort(Assignments, new Assignment());
		
		//Initializes the homeworkPlan, which you must fill out and output
		int[] homeworkPlan = new int[Assignments.size()];
		//YOUR CODE GOES HERE
		DisjointSets p = new DisjointSets(lastDeadline+1);// the time slot that we have is just from 1 to dead line
		for(Assignment asshole : Assignments)//i know this asshole is weight more than 
		{									//the others, so finish this asshole first
			int thisTime = p.find(asshole.deadline);//find this asshole's time slot
			if(thisTime >= 1) // if this asshole can be put into the queue 
			{ 								// does not have anything then go for it
				int previousTime = p.find(thisTime - 1);
				//p.join(thisTime, previousTime);
				p.enqueue(thisTime,previousTime);//if time slot this asshole trying to be terminated is already not available then move this shit to the previous slot
				homeworkPlan[asshole.number] = thisTime;//the home work number is the index and the time to finish is the order to put into
			}
			//else //if this asshole's slot have some other assholes that weight more
				//homeworkPlan[asshole.number] = 0;//then stfu bitch
		}
		
		return homeworkPlan;
	}
}
	



