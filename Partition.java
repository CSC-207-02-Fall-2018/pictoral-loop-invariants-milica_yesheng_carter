package pictorialLoop;

public class Partition {

	public static int partition (int a[], int left, int right) {


		int l_spot = 1;
		int r_spot = a.length - 1;
		int temp = 0;

		while (l_spot <= r_spot) {

			while ((a[l_spot]<=a[left]) && (r_spot>=l_spot)) {
				l_spot++;
			}
<<<<<<< HEAD
=======

>>>>>>> 560f064b1376fca89cc595aebf07b7f91830e2b1
			while ((a[r_spot]>=a[left]) && (l_spot<=r_spot)) {
				r_spot--;
			}
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

	public static int select (int a[], int k) {
		return selectHelper (a, k, 0, a.length-1);
	}

<<<<<<< HEAD

	public static int selectHelper (int a[], int k, int left, int right) {
		int position = k-1;
		int middle = 0;
		int small = 0;
		if (right ==0) {
			small = a[position];
		}
		else {
			middle = partition (a, left, right);
			if (position == middle) {
				return a[position];
			}
			else if (k <= middle) {
				small = selectHelper (a,k,left,middle-1);
			}
			else if (k >= middle) {
				small = selectHelper (a,k,middle+1,right);
			}
		}
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
=======
		}
>>>>>>> 560f064b1376fca89cc595aebf07b7f91830e2b1

	public static void quicksortKernel (int a[], int left, int right) {
		// int last = right;
		int middle;
		if (left<right) {

<<<<<<< HEAD
			middle = partition (a,left,right);
			quicksortKernel (a,left,middle-1);
			quicksortKernel (a, middle+1,right);
		}
	}

	public static void quicksort (int a[]) {
		quicksortKernel (a,0,a.length-1);
=======
		return r_spot;
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


	public static int select (int a[], int k) {
		return selectHelper (a, k, 0, a.length-1);
	}

	public static int selectHelper (int a[], int k, int left, int right) {
		int s = -1;
		int middle = partition(a,left,a.length-1);
		if(middle == k - 1) {
			return a[middle];
		}
		else if(middle < k - 1) {
			return selectHelper(a,k - middle,middle+1, right);
		}
		else  {
			return selectHelper(a,k,0,middle - 1);
		}
>>>>>>> 560f064b1376fca89cc595aebf07b7f91830e2b1
	}


	public static void main (String args[]) {

		int [] b = {55,10,71,30,50,60,20,91,90,80}; //even number of elements 
<<<<<<< HEAD
		int [] c = {35,10,71,30,50,8, 100}; //odd number of elements
		int [] c1 = {35,10,30,50,8,90}; 
		int [] c2 = {35,10,30,50,8,90}; 
		int p =0;
		int s = -1;
		double median = -1;
=======
		int [] c = {35,10,71,30,50,8}; //odd number of elements
		int l = 0;
		int r = b.length - 1;
		int p =0;
		int s = -1;
>>>>>>> 560f064b1376fca89cc595aebf07b7f91830e2b1

		//p = partition(b,l,r);
		s = select (b,2);

		System.out.print(s+ "   ");
<<<<<<< HEAD
		// for (int i = 0; i<b.length; i++) {
		// System.out.print(b[i] + " ");
		// }
		// System.out.println();

		System.out.println(partition (c1,0,6));
		s = select(c,5);
		System.out.print(s+ "    ");

		median = median (c); //testing odd number of elements
		System.out.println(); 
		System.out.println(median);
		median = median (c1); //testing even number of elements
		System.out.println(); 
		System.out.println(median);

		for (int i =0; i<c2.length; i++) {
			System.out.print(c2[i] + " ");
		}
		System.out.println();
		quicksort (c2);
		for (int i =0; i<c2.length; i++) {
			System.out.print(c2[i] + " ");
		}


=======
//		for (int i = 0; i<b.length; i++) {
//			System.out.print(b[i] + " ");
//		}
//		System.out.println();
		s = select(c,5);
		System.out.print(s+ "    ");
//
//		for (int i = 0; i<c.length; i++) {
//			System.out.print(c[i] + " ");
//		}
>>>>>>> 560f064b1376fca89cc595aebf07b7f91830e2b1





	}



<<<<<<< HEAD
}
=======
}

>>>>>>> 560f064b1376fca89cc595aebf07b7f91830e2b1
