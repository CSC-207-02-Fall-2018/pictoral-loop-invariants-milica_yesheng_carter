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
	}


	public static void main (String args[]) {

		int [] b = {55,10,71,30,50,60,20,91,90,80}; //even number of elements 
		int [] c = {35,10,71,30,50,8}; //odd number of elements
		int l = 0;
		int r = b.length - 1;
		int p =0;
		int s = -1;

		//p = partition(b,l,r);
		s = select (b,2);

		System.out.print(s+ "   ");
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





	}



}

