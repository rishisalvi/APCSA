/**
 *	SortMethods - Sorts an array of Integers in ascending order.
 *
 *	@author Rishi Salvi
 *	@since	November 30, 2023
 */
public class SortMethods {
	
	/**
	 *	Bubble Sort algorithm - in ascending order
	 *	@param arr		array of Integer objects to sort
	 */
	public void bubbleSort(Integer [] arr) {
		for (int outer = arr.length - 1; outer > 0; outer--)
			for (int inner = 0; inner < outer; inner++)
				if (arr[inner].compareTo(arr[inner + 1]) > 0)
					swap(arr, inner, inner + 1); 
	}
	
	/**
	 *	Swaps two Integer objects in array arr
	 *	@param arr		array of Integer objects
	 *	@param x		index of first object to swap
	 *	@param y		index of second object to swap
	 */
	private void swap(Integer[] arr, int x, int y) {
		Integer temp = arr[x];
		arr[x] = arr[y];
		arr[y] = temp; 
	}
	
	/**
	 *	Selection Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 */
	public void selectionSort(Integer [] arr) {
		for (int outer = arr.length - 1; outer > 0; outer--){
			int maxIndex = 0;
			for (int inner = 0; inner <= outer; inner++)
				if (arr[inner].compareTo(arr[maxIndex]) > 0)
					maxIndex = inner;
			swap(arr, maxIndex, outer); 
		}
	}
	
	/**
	 *	Insertion Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 */
	public void insertionSort(Integer [] arr) {
		for (int outer = 1; outer < arr.length; outer++){
			int inner = outer - 1;
			while (inner >= 0 && arr[inner].compareTo(arr[inner + 1]) > 0){
				swap(arr, inner, inner + 1);
			inner--; 
			}
		}
	}
	
	/**
	 *	Merge Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 */
	public void mergeSort(Integer [] arr) {
		recursiveSort(arr, 0, arr.length - 1);
	}
	
	public void recursiveSort(Integer[] arr, int start, int end){
		if (start - end <= 2){
			if (start - end == 2 && arr[start].compareTo(arr[start + 1]) > 0)
				swap(arr, start, end); 
		}
		int splitIndex = (start + end)/2; 
		recursiveSort(arr, start, splitIndex);
		recursiveSort(arr, splitIndex + 1, end);
		merge(arr, start, splitIndex, end); 
	}
	
	public void merge(Integer[] arr, int start1, int middle, int end){
		int start2 = middle + 1; 
		int index = start1; 
		Integer[] temp = new Integer[arr.length]; 
		while (start1 <= middle && start2 <= end){
			if (arr[start1].compareTo(arr[start2]) > 0){
				temp[index] = arr[start1]; 
				start1++; 
			}
			else{
				temp[index] = arr[start2];
				start2++; 
			}
			index++; 
		}
		
		while(start1 <= middle){
			temp[index] = arr[start1];
			start1++; 
			index++; 
		}
		
		while(start2 <= end){
			temp[index] = arr[start2]; 
			start2++; 
			end++; 
		}
		
		for (int i = 0; i < arr.length; i++)
			arr[i] = temp[i]; 
	}
	
	/*****************************************************************/
	/************************* For Testing ***************************/
	/*****************************************************************/
	
	/**
	 *	Print an array of Integers to the screen
	 *	@param arr		the array of Integers
	 */
	public void printArray(Integer[] arr) {
		if (arr.length == 0) System.out.print("(");
		else System.out.printf("( %4d", arr[0]);
		for (int a = 1; a < arr.length; a++) {
			if (a % 10 == 0) System.out.printf(",\n  %4d", arr[a]);
			else System.out.printf(", %4d", arr[a]);
		}
		System.out.println(" )");
	}

	public static void main(String[] args) {
		SortMethods se = new SortMethods();
		se.run();
	}
	
	public void run() {
		Integer[] arr = new Integer[10];
		// Fill arr with random numbers
		for (int a = 0; a < 10; a++)
			arr[a] = (int)(Math.random() * 100) + 1;
		System.out.println("\nBubble Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		bubbleSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();

		for (int a = 0; a < 10; a++)
			arr[a] = (int)(Math.random() * 100) + 1;
		System.out.println("\nSelection Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		selectionSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();

		for (int a = 0; a < 10; a++)
			arr[a] = (int)(Math.random() * 100) + 1;
		System.out.println("\nInsertion Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		insertionSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();
/*		
		for (int a = 0; a < 10; a++)
			arr[a] = (int)(Math.random() * 100) + 1;
		System.out.println("\nMerge Sort");
		System.out.println("Array before sort:");
		printArray(arr);
		System.out.println();
		mergeSort(arr);
		System.out.println("Array after sort:");
		printArray(arr);
		System.out.println();
*/
	}
}
