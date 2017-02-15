//By Noah Ransom

import java.util.*;
import java.util.Collections;

class Homework2
{

	public String fail = "FAIL";
	public int calls;
	/*public LinkedList<String> makeJourney(LinkedList<Integer> sticks, int distance, String workingString, Homework1 h1)
	{
        h1.calls++;
		//at top level, workingString is just a space
		//create working string that is used to base moves on
		//pass working string to recursive calls
		//if no completion possible, set string to default invalid value
	    LinkedList<String> toReturn;
	    toReturn = new LinkedList<String>();

	    for(int i = 0; i < sticks.size(); i++)
	    {
	        if(sticks.get(i) == distance)
	        {
	            //create a copy of current string that ends here, add to list
	            //toReturn += (sticks.get(i).toString); //tostring not for int: fix later
	            //added to returns
	        	String tmp = (workingString + sticks.get(i).toString());
	        	tmp = tmp.trim(); //no whitespace on front or rear
	        	toReturn.add(tmp);


	        }
	        else if(sticks.get(i) > distance)
	        {
	            //don't create a copy
	            //don't add this to the string
	        	continue;

	        }
	        else
	        {

	            //copy the current string
	            //add this number to it
	            //toReturn.add(sticks.get(i).toString)
	            //return toReturn += makeJourney(sticks, (distance - sticks.get(i)));
	        	toReturn.addAll(makeJourney(sticks, (distance - sticks.get(i)), (workingString + sticks.get(i).toString() + " "), h1));
	        }
	    }


	    return toReturn;
	}

	public void printJourneys(LinkedList<String> journeys)
	{
		Collections.sort(journeys, new LengthComparator());
		while(journeys.size() > 0)
		{
			System.out.println(journeys.remove());
		}
        System.out.println("Made " + calls + " recursive calls.");
	}

    //public int distance; //how far we need to go
    //public int numSticks; //how many pogo sticks there are
    //public Integer[] pogoSticks; //contains jump distances for pogo sticks

    public void prepSticks(LinkedList<Integer> sticks, Homework1 h1)
    {
        sticks.clear();
        h1.calls = 0;
        int distance = 0;
        Integer[] test1 = {5, 10, 1, 3};
        System.out.println("\nPerforming first test. Input: 5, 10, 1, 3");
        for(int i = 0; i < 4; i++)
        {
            sticks.add(test1[i]);
        }

        LinkedList<Integer> stickCopy = new LinkedList<Integer>();
        stickCopy = sticks;

        for(int i = 5; i <= 30; i += 5)
        {
            distance = i;
            h1.calls = 0;
            h1.makeJourney(sticks, distance, " ", h1);
            System.out.println("\t " + distance + ": " + h1.calls + " recursive calls");
            sticks = stickCopy;
        }
        stickCopy.add(7);
        stickCopy.add(4);
        sticks = stickCopy;
        System.out.println("\nTest 2. Input: 5, 10, 1, 3, 7, 4, 11, 25");
        for(int i = 20; i <= 50; i += 10)
        {
            distance = i;
            h1.calls = 0;
            h1.makeJourney(sticks, distance, " ", h1);
            System.out.println("\t " + distance + ": " + h1.calls + " recursive calls");
            sticks = stickCopy;
        }

    }*/

    public void cheapestJourney(LinkedList<Integer> pogoSticks, LinkedList<Integer> stickCosts, Integer[] coins, int distance)
    {
        Integer[] bCosts = new Integer[distance];
        String[] bTrips = new String[distance];
        for(int i = 0; i < bTrips.length; i++)
        {
            bTrips[i] = "DEFAULT";
        }
        Integer initialBalance = coins[distance];

        //(cheapest, trip) = Make-Trip-Aux (D, c, v, n, r, s)
        Pair<Integer, String> completeTrip = MakeTripAux(pogoSticks, stickCosts, coins, distance, bCosts, bTrips);
        Integer cheapestCost = (completeTrip.getElement0() + initialBalance);
        //now print cost followed by trip
        System.out.println(cheapestCost + " " + completeTrip.getElement1());
        //return new Pair(cheapestCost, cheapestTrip);
    }

    public Pair<Integer, String> MakeTripAux(LinkedList<Integer> sticks, LinkedList<Integer> stickCosts,
                                    Integer[] coins, int distance, Integer[] bCosts, String[] bTrips)
    {
        if(bTrips[distance] != "DEFAULT")
        {
            //return the stuff
        }

        int bestCost = 0;
        boolean checkedFirstStick = false;

        if(distance == 0)
        {
            //we've finished the trip. pick up any coins at the last position
            bestCost = (0 + coins[0]);
        }
        else
        {
            for(int i = 0; i < sticks.size(); i++)
            {
                if((distance - sticks.get(i)) < 0)
                {
                    //skip rest of loop because we'd overshoot
                    continue;
                }
                else if((distance - sticks.get(i)) == 0)
                {
                    //recursion base case
                    if(bestCost < (bCosts[i] + coins[i]))
                    {
                        bestCost = (bCosts[i] + coins[i]);
                        bTrips[i] = (sticks.get(i).toString());
                    }
                }
                else
                {
                    //(costCandidate, tripCandidate)
                    Pair<Integer, String> tmpPair = MakeTripAux(sticks, stickCosts, coins,
                                                        distance-sticks.get(i), bCosts, bTrips);

                    //costCandidate += stickCosts.get(i);
                    if(((tmpPair.getElement0() + stickCosts.get(i)) < bestCost) || checkedFirstStick == false)
                        //cheaper than current cheapest, so update
                        //append this jump to value fetched, then put in s
                        checkedFirstStick = true;
                        bTrips[distance] = (tmpPair.getElement1() + " " + sticks.get(i).toString());
                        bTrips[distance] = bTrips[distance].trim();
                        bestCost = (tmpPair.getElement0() + stickCosts.get(i));
                }
            }
        }
        bCosts[distance] = bestCost;
        return(Pair.createPair(bCosts[distance], bTrips[distance]));
    }

    public boolean processInt(Integer current)
    {
        if(current < 0)
        {
            //we're looking at a coin value
            return true;
        }
        return false; //next thing is coin loc
    }

    public static void main(String[] args)
    {
        System.out.println("Please enter an input.\n");
    	Homework1 h1 = new Homework1();
        h1.calls = 0;
    	LinkedList<Integer> pogoSticks = new LinkedList<Integer>();
        LinkedList<Integer> stickCosts = new LinkedList<Integer>();
    	int distance;
        //pogoSticks.addAll(test);
        //h1.prepSticks(pogoSticks, h1);
    	Scanner scan = new Scanner(System.in);
        String type = scan.next();
    	//System.out.println("Enter an input.");
    	distance = scan.nextInt();
        Integer[] coins = new Integer[distance + 1];
        for(int i = 0; i <= distance; i++)
        {
            //initialize everything to 0
            //if no pile of coins, then nothing changes
            coins[i] = 0;
        }
    	while(scan.hasNext())
    	{
            //process next int
            Integer tmp = scan.nextInt();
            if(h1.processInt(tmp)) //this is coin
            {
                int tmpIdx = scan.nextInt();
                if(tmpIdx > distance)
                {
                    //skip this coin pile, because we can't reach it
                }
                else
                {
                    coins[tmpIdx] = tmp;
                }
            }
            else //this is not a coin
            {
                int newStick = scan.nextInt();
                pogoSticks.add(newStick);
                stickCosts.add(tmp); //because first element of pair is cost
            }
    	}
    	scan.close();
        for(int i = 0; i <= distance; i++)
        {
            System.out.println("Value at position " + i + ": " + coins[i]);
        }

        for(int i = 0; i < pogoSticks.size(); i++)
        {
            System.out.println("Pogo stick length " + pogoSticks.get(i) + " has cost " + stickCosts.get(i) + ".");
        }
    }
}
