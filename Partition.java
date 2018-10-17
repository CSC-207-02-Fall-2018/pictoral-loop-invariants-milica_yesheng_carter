package pictorialLoop;

public class Partition {

	public static void partition (int a[], int left, int right) {


		int l_spot = 1;
		int r_spot = a.length - 1;
		int temp = 0;

		while (l_spot <= r_spot) {

			while ((a[l_spot]<=a[left]) && (a[r_spot]>=a[l_spot])) {
				l_spot++;
			}

			while ((a[r_spot]>=a[left]) && (a[l_spot]<=a[r_spot])) {
				r_spot--;
			}
			if (a[r_spot]>a[l_spot]) {
				temp = a[l_spot];
				a[l_spot] = a[r_spot];
				a[r_spot] = temp;
//				if(l_spot!=r_spot +1) {
//					l_spot++;
//					r_spot--;
//				}
			}

			//			if (a[r_spot] >= a[left]) {
			//				r_spot--;
			//			}
			//			if (a[l_spot] <= a[left]) {
			//				l_spot++;
			//			}
			//			if (a[l_spot]>a[left] && a[r_spot]<a[left]){ 
			//				temp = a[l_spot];
			//				a[l_spot] = a[r_spot];
			//				a[r_spot] = temp;
			//				l_spot++;
			//				r_spot--;
			//			}
			//		}
			//		temp = a[r_spot];
			//		a[r_spot] = a[left];
			//		a[left] = temp;

		}
		System.out.println();
		for (int i = 0; i<a.length; i++) {
			System.out.print(a[i] + " ");
		}
		//swap l_spot and r_spot
		//		temp = a[l_spot];
		//		a[l_spot] = a[r_spot];
		//		a[r_spot] = temp;
		//		System.out.println();
		//		for (int i = 0; i<a.length; i++) {
		//			System.out.print(a[i] + " ");
		//		}

		//swap first element with the middle element
		temp = a[r_spot];
		a[r_spot] = a[left];
		a[left] = temp;

	}


	public static void main (String args[]) {

		int [] b = {55,10,71,30,50,60,20,91,90,80}; //even number of elements 
		int [] c = {35,10,71,30,50,8}; //odd number of elements
		int l = 0;
		int r = b.length - 1;


		partition(b,l,r);
		System.out.println();
		for (int i = 0; i<b.length; i++) {
			System.out.print(b[i] + " ");
		}
		System.out.println();
		partition(c,l,(c.length -1));
		//		for (int i = 0; i<c.length; i++) {
		//			System.out.print(c[i] + " ");
		//		}



	}
}
