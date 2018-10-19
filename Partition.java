package partition;


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
		int l_spot = 1;					//position of first un-partitioned element
		int r_spot = a.length - 1;		//position of last un-partitioned element
		int temp = 0;					//position for temp

		
		while (l_spot < r_spot) {
			//if first un-partitioned element is smaller than pivot and 
			//the last un-partitioned element is bigger or equal to 
			//first un-partitioned element, then first un-partitioned element
			//moved to down
			while ((a[l_spot]<=a[left]) && (r_spot>=l_spot)) {
				l_spot++;
			}
			//if the last un-partitioned element is greater than pivot and 
			//the first un-partitioned element is smaller or equal to 
			//last un-partitioned element, then position of last un-partitioned element 
			//moved to up 
			while ((a[r_spot]>=a[left]) && (l_spot<=r_spot)) {
				r_spot--;
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
	 * @param a			an array of integers
	 * @param k			an integer, 0 <= k <= a.length-1
	 * @return			the kth smallest element in a
	 */
	//finds the element that is kth smallest in the array that requires only two
	//parameter 
	public static int select (int a[], int k) {
		return selectHelper (a, k, 0, a.length-1);
	}


	/**
	 * @param a			an array of integers
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

	public static double median (int a[]) {
		double median = -1;
		int pos1 = -1;
		int pos2 = -1;

		//do the partition for every element in an array so it is sorted out
		for (int i =0; i<a.length; i++) {
			partition (a, 0, a.length);
		}
		for (int i = 0; i<a.length; i++) {
			System.out.print(a[i] + " ");
		}
		if (a.length % 2 ==1) {
			median = a[a.length/2];
		}
		//when there is even number of elements, get the average value of the two in the middle
		else {
			pos1= a.length / 2;
			pos2 = pos1-1;
			median = (a[pos1]+a[pos2])/2.0;
		}
		return median;
	}


	public static void quicksortKernel (int a[], int left, int right) {
		// int last = right;
		int middle;
		if (left<right) {

			middle = partition (a,left,right);
			quicksortKernel (a,left,middle-1);
			quicksortKernel (a, middle+1,right);
		}
	}

	public static void quicksort (int a[]) {
		quicksortKernel (a, 0 , a.length-1);
	}
	//
	//	public static int select (int a[], int n, int k) {
	//		if(n==1) {
	//			return a[0];
	//		}
	//		int middle = partition(a, 0 , n - 1);//
	//		for (int i = 0; i<n; i++) {
	//			System.out.print(a[i] + " ");
	//		}
	//		System.out.println("before");	
	//		System.out.println(middle + " K: "+ k); 
	//		System.out.println(); 
	//		
	//		if(k == middle + 1) {
	//			return a[middle];
	//		}
	//		
	//		if(k <= middle) {
	////			for (int i = 0; i<n; i++) {
	////				System.out.print(a[i] + " ");
	////			}
	////			System.out.println();
	//			
	//			select(a, middle, k);
	//		}
	//		
	//		if(k >= middle) {
	//			for (int i = 0; i<n; i++) {
	//				System.out.print(a[i] + " ");
	//			}
	//			System.out.println();
	//			select(a, middle + 1, k);
	//		}
	//		return -1; //added because the syntax requires the return statement s
	//	}



	public static void main (String args[]) {

		int [] evenLst = {55,61,71,30,10,50,60,20,91,90,80,55}; //even number of elements, one 
																//element is same as pivot
		int [] oddLst = {35,10,71,30,50,8,100}; //odd number of elements
		int [] threeLst = {0,1,2}; //three element in ascending order
		int [] twoLst = {2,1}; // two element in descending order
		int [] oneLst = {10}; //one element 
		int [] c1 = {35,10,30,50,8,90}; 
		int [] c2 = {35,10,30,50,8,90}; 
		int p =0;
		int s = -1;
		double median = -1;
		int l = 0;
		int r = evenLst.length - 1;

//		//Testing For Partition
//		//partition(evenLst, 0, evenLst.length-1); //even number of elements
//		System.out.println("partition for evenLst, even number of elements");
//		partitionPrint(evenLst);
//		//partition for evenLst, even number of elements
//		//55 61 71 30 10 50 60 20 91 90 80 55 
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
		int o = select(oddLst,4);		//middle of odd list 
		System.out.println("Selection 4th smallest element in the oddLst: "+ o );

		int e = select(evenLst,5);		//near the middle of even list
		System.out.println("Selection 5th smallest element in the evenLst: "+ e );
		
		int first=select(oneLst, 1);
		System.out.println("Selection 1th smallest element in the oneLst: "+ first );
		
		
		
		
//
//		//p = partition(b,l,r);
//		s = select (evenLst,2);
//
//		System.out.print(s+ "   ");
//		// for (int i = 0; i<b.length; i++) {
//		// System.out.print(b[i] + " ");
//		// }
//		// System.out.println();
//
//		System.out.println(partition (c1,0,6));
//		s = select(oddLst,5);
//		System.out.print(s+ "    ");
//
//		median = median (oddLst); //testing odd number of elements
//		System.out.println(); 
//		System.out.println(median);
//		median = median (c1); //testing even number of elements
//		System.out.println(); 
//		System.out.println(median);
//
//		for (int i =0; i<c2.length; i++) {
//			System.out.print(c2[i] + " ");
//		}
//		System.out.println();
//		quicksort (c2);
//		for (int i =0; i<c2.length; i++) {
//			System.out.print(c2[i] + " ");
//		}
//
//
////		for (int i = 0; i<b.length; i++) {
////			System.out.print(b[i] + " ");
////		}
////		System.out.println();
//		s = select(oddLst,5);
//		System.out.print(s+ "    ");
////
////		for (int i = 0; i<c.length; i++) {
////			System.out.print(c[i] + " ");
////		}





	}
}


