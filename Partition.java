/* ***************************************************************
 * Carter Markegard, Milica Cvrkota, Yesheng Chen                *
 * PO Box 4152                          3278                     *
 * Program for CSC 207                                           *
 *   Pictorial Loop Invariants Lab                               *
 * Assignment for Friday, October 19                             *
 *****************************************************************/


/* ***************************************************************
 * Academic honesty certification:                               *
 *   Written/online sources used:                                *
 *  Sort an array of 0s, 1s, and 2s -                            *
 *  https://www.geeksforgeeks.org/sort-an-array-of-0s-1s-and-2s/ *
 *   Help obtained                                               *
 *     CS evening tutor - Help with git                          *
 *   My signature below confirms that the above list of sources  *
 *   is complete AND that I have not talked to anyone else       *
 *   [e.g., CSC 161 students] about the solution to this problem *
 *                                                               *
 *   Signature: Carter Markegard, Milica Cvrkota, Yesheng Chen   *
 *****************************************************************/


package pictorialLoop;


public class Partition {


	/**
	 * @param a				an array of integers can't be null
	 * @param left			0, the position of the first element (pivot) in a
	 * @param right			(a.length -1) the position of the last element in a
	 * @return				the position of last un-partitioned element
	 */
	/*  this method rearrange the elements in the array base on the first element(pivot) in the array
		where all elements smaller or equal to pivot will be move to the left of pivot
		and all elements bigger or equal to pivot will be move to the right of pivot
		the left and right side are not in order. 
	 */
	public static int partition (int a[], int left, int right) {
		int l_spot = left+1;					//position of first un-partitioned element
		int r_spot = right;		//position of last un-partitioned element
		int temp;					//position for temp


		while (l_spot <= r_spot) {

			//if the first un-partitioned element is smaller or equal to 
			//last un-partitioned element and the last un-partitioned element
			//is greater than pivot then position of last un-partitioned element 
			//moved to up 
			while ((l_spot<=r_spot)&&(a[r_spot]>=a[left])) {
				r_spot--;
			}
			//if the last un-partitioned element is bigger or equal to 
			//first un-partitioned element, and first un-partitioned 
			//element is smaller than pivot then first un-partitioned element
			//moved to down
			while ((r_spot>=l_spot) && (a[l_spot]<=a[left])) {
				l_spot++;
			}

			//swap the first and last un-partitioned elements, when 
			//first un-partitioned element is bigger than pivot and
			//last un-partitioned element is smaller than pivot and
			//r_spot is greater than l_spot
			if (r_spot>l_spot) {
				temp = a[l_spot];
				a[l_spot] = a[r_spot];
				a[r_spot] = temp;
			}
		}
		//swap first element with the middle element
		temp = a[r_spot];
		a[r_spot] = a[left];
		a[left] = temp;

		return r_spot;
	}


	/**
	 * @param a			an array of integers
	 */
	//print the array of integers
	public static void partitionPrint (int a[]) {
		for (int i = 0; i<a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}


	/**
	 * @param a			an array of integers/ none empty 
	 * @param k			an integer, 0 <= k <= a.length-1
	 * @return			the kth smallest element in a
	 */
	//finds the element that is kth smallest in the array that requires only two
	//parameter 
	public static int select (int a[], int k) {
		return selectHelper (a, k, 0, a.length-1);
	}


	/**
	 * @param a			an array of integers, none empty 
	 * @param k			an integer, 0 < k <= a.length-1
	 * @param left		0, the position of the first element (pivot) in a
	 * @param right		(a.length -1) the position of the last element in a
	 * @return			the kth smallest element in a 
	 */
	//finds the element that is kth smallest in the array
	public static int selectHelper (int a[], int k, int left, int right) {
		int position = k-1;
		int middle;
		int small=0;;
		//if the list contains only one element, then the kth smallest is that element
		if (right ==0) {
			small = a[position];
		}
		else {
			//find the middle position of the array
			middle = partition (a, left, right);
			//if position is the as the middle return element at the middle
			if (position == middle) {
				return a[position];
			}
			//recursive call to selectHelper on the left side of the middle, 
			//middle is been updated by the partition procedure, until position==middle
			else if (position <= middle) {
				small = selectHelper (a,k,left,middle-1);
			}
			//recursive call to selectHelper on the right of the middle,
			//middle is been updated by the partition procedure, until position==middle
			else if (position >= middle) {
				small = selectHelper (a,k,(middle+1),right);
			}
		}
		//return the kth smallest element
		return small;
	}

	/**
	 * @param a		  array of integers/ none empty 
	 * @return		  the median of the integers in the array
	 */
	//Find the median of the array 
	public static double median (int a[]) {
		double median = -1;
		double pos1 = -1;
		double pos2 = -1;
		//if the array is odd, then the median is the (half of the length)+1 smallest element in
		//the array
		if (a.length % 2 ==1) {
			median = select(a, (int)(a.length/2 +1));
		}
		//when there is even number of elements, we find the average of the (half of the length)+1 
		//smallest element and the (half of the length)+2 smallest elements in the array 
		else {
			pos1= (a.length / 2);
			pos2 = pos1+1;
			//We use a multitude of double and int casts to ensure
			//that we divide with doubles and return ints. When we
			//divide with ints, the math doesn't work and gives us
			//whole values.
			median = ((double)(select(a, (int)pos1)+ (double)select(a, (int)pos2))/2);
		}
		return median;
	}

	/**
	 * A helper function to kernel that usues quicksort to sort an int array.
	 * @param a an int array
	 * @param left the index of the first element in the array.(pivot)
	 * @param right the index of the last element in the array.
	 */
	public static void quicksortKernel (int a[], int left, int right) {
		int middle;
		//partition until the array is sorted
		if (left<right) {
			middle = partition (a,left,right);
			//recurses through the left size of the array
			if (left < middle-1) {
				quicksortKernel (a,left,middle-1);
			}
			//recurses through the right side of the array
			if (middle+1 < right) {
				quicksortKernel (a, middle+1,right);
			}
		}
	}

	/**
	 * The husk for a quicksort algorithm. Prints the resulting array.
	 * @param a an int array to be sorted.
	 */
	public static void quicksort (int a[]) {
		quicksortKernel (a, 0 , a.length-1);
		partitionPrint(a);
	}

	/**
	 * @param a			an array of string
	 */
	//print the array of integers
	public static void dutchPrint (String a[]) {
		for (int i = 0; i<a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}

	/**
	 * Sorts an array of Strings until all red Strings are on the left,
	 * all white Strings are in the middle, and all blue strings are on
	 * the right. 
	 * Precondition:
	 * a must be an array of three specific strings:
	 * "red"
	 * "white"
	 * "blue"
	 * @param a an String array.
	 */
	public static void dutchFlagB (String a[]) {
		//Define our loop invariant starting boundaries.
		int mid =0; //Tracks the first unsorted element
		int low =0; //Tracks the first white element
		int hi = a.length-1; //Tracks the last unsorted element
		//Used later to switch values in the array.
		String temp;
		//Sorting the array until the white zone passes the blue zone.
		while (mid <=hi) {
			if (a[mid].equals("red")) {
				//Swaps the values of mid and low
				temp = a[mid];
				a[mid] = a[low];
				a[low] = temp;
				low++;
				mid++;
			}
			else if(a[mid].equals("white")) {
				//white zone grows by one
				mid++;
			}
			else if(a[mid].equals("blue")) {
				//Swaps the values of mid and hi
				temp = a[mid];
				a[mid] = a[hi];
				a[hi] = temp;
				hi--;
			}
			else {
				System.err.println("Error: unexpected color string recieved.");
			}
		}
	}

	/**
	 * Sorts an array of Strings until all red Strings are on the left,
	 * all white Strings are in the middle, and all blue strings are on
	 * the right. 
	 * Precondition:
	 * a must be an array of three specific strings:
	 * "red"
	 * "white"
	 * "blue"
	 * @param a an String array.
	 */
	public static void dutchFlagC (String a[]) {
		//Define our loop invariant starting boundaries.
		int mid =a.length-1; //mid is the last unsorted
		int low =0; //low is the first unsorted
		int hi = a.length-1; //hi is the last white value
		String temp;
		//Loops until all of the unsorted are gone
		while (mid >= low) {
			if (a[mid].equals("red")) {
				//Swap mid and low
				temp = a[mid];
				a[mid] = a[low];
				a[low] = temp;
				low++;

			}
			else if(a[mid].equals("white")) {
				mid--;
			}
			else if(a[mid].equals("blue")) {
				//Swap mid and hi
				temp = a[mid];
				a[mid] = a[hi];
				a[hi] = temp;
				hi--;
				mid--;
			}
			else {
				System.err.println("Error: unexpected color string recieved.");
			}
		}
	}

	public static void main (String args[]) {

		int [] evenLst = {55,61,71,55,30,10,50,60,20,91,90,80}; //even number of elements, one 
		//element is same as pivot
		int [] oddLst = {35,10,71,30,50,8,100}; //odd number of elements
		int [] threeLst = {0,1,2}; //three element in ascending order
		int [] twoLst = {2,1}; // two element in descending order
		int [] oneLst = {10}; //one element 
		int [] c1 = {35,10,30,50,8,90}; 
		int [] c2 = {35,10,30,50,8,90}; 
		String [] colors1 = 
			{"blue","red","white","white","blue","red","white","blue"};
		String [] colors2 = 
			{"blue"};
		String [] colors3 = 
			{"red","white","blue"};
		String [] colors4 = 
			{"red","white"};
		String [] colors5 = 
			{"blue","white","red"};
		int p =0;
		int s = -1;
		double median = -1;
		int l = 0;
		int r = evenLst.length - 1;

		//Testing For Partition
		//		partition(evenLst, 0, evenLst.length-1); //even number of elements
		//		System.out.println("partition for evenLst, even number of elements");
		//		partitionPrint(evenLst);
		//		//partition for evenLst, even number of elements
		//		//10 20 50 55 30 55 71 60 61 91 90 80 
		//		
		//		//partition(oddLst, 0, oddLst.length-1);//odd number of elements
		//		System.out.println("partition for oddLst, odd number of elements");
		//		partitionPrint(oddLst);
		//		//partition for oddLst, odd number of elements
		//		//30 10 8 35 50 71 100 
		//		
		//		partition(threeLst, 0, threeLst.length-1);//three elements
		//		System.out.println("partition for threeLst, three elements in ascending");
		//		partitionPrint(threeLst);
		//		//partition for threeLst, three elements in ascending
		//		//0 1 2 
		//		
		//		partition(twoLst, 0, twoLst.length-1);//two elements
		//		System.out.println("partition for twoLst, two elements in descending");
		//		partitionPrint(twoLst);
		//		//partition for twoLst, two elements in descending
		//		//1 2
		//		
		//		partition(oneLst, 0, oneLst.length-1);//two elements
		//		System.out.println("partition for oneLst, one element");
		//		partitionPrint(oneLst);
		//		//partition for oneLst, one element
		//		//10

		//Testing For Selection
		//		int o = select(oddLst,4);		//middle of odd list 
		//		System.out.println("Selection 4th smallest element in the oddLst: "+ o );
		//		//Selection 4th smallest element in the oddLst: 35
		//
		//		int e = select(evenLst,6);		//near the middle of even list
		//		System.out.println("Selection 6th smallest element in the evenLst: "+ e );
		//		//Selection 6th smallest element in the evenLst: 55
		//		
		//		int first=select(oneLst, 1);
		//		System.out.println("Selection 1th smallest element in the oneLst: "+ first );
		//		//Selection 1th smallest element in the oneLst: 10


		//		//Test for median:
		//		System.out.println("the median of oddLst is: "+median(oddLst));
		//		//the median of oddLst is: 35.0
		//		System.out.println("the median of evenLst is: "+median(evenLst));
		//		//the median of evenLst is: 57.5
		//		
		//		//Test for quicksort:
		//		quicksort(oddLst);
		//		//output: 8 10 30 35 50 71 100 
		//		quicksort(evenLst);
		//		//output:10 20 30 50 55 55 60 61 71 80 90 91 

		/*Test case checking to see if dutch flag B to work with an expected
		 * string array of colors.
		 * Output:
		 * red red white white white blue blue blue 
		 */
		dutchFlagB(colors1);
		dutchPrint(colors1);

		/*Tests if dutchFlagB works for a list of one.
		 * Output:
		 * blue 
		 */
		dutchFlagB(colors2);
		dutchPrint(colors2);

		/*Tests if dutchFlagB works on a list already in order.
		 * Output:
		 * red white blue 
		 */

		dutchFlagB(colors3);
		dutchPrint(colors3);

		/*Tests if dutchFlagB works on a list of two.
		 * Output:
		 *red white
		 */

		dutchFlagB(colors4);
		dutchPrint(colors4);

		/*Tests if dutchFlagB works on a list in reverse order.
		 * Output:
		 * red white blue 
		 */

		dutchFlagB(colors5);
		dutchPrint(colors5);


		/*Test case checking to see if dutch flag C to work with an expected
		 * string array of colors.
		 * Output:
		 * red red white white white blue blue blue 
		 */
		dutchFlagC(colors1);
		dutchPrint(colors1);

		/*Tests if dutchFlagC works for a list of one.
		 * Output:
		 * blue 
		 */
		dutchFlagC(colors2);
		dutchPrint(colors2);

		/*Tests if dutchFlagC works on a list already in order.
		 * Output:
		 * red white blue 
		 */

		dutchFlagC(colors3);
		dutchPrint(colors3);

		/*Tests if dutchFlagC works on a list of two.
		 * Output:
		 *red white
		 */

		dutchFlagC(colors4);
		dutchPrint(colors4);

		/*Tests if dutchFlagC works on a list in reverse order.
		 * Output:
		 * red white blue 
		 */

		dutchFlagC(colors5);
		dutchPrint(colors5);
	}
}


