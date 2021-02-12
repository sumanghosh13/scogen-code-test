package compare;

// using Lomuto's partition Scheme
public class QuikSortL {

    void Swap(int[] array,
              int position1,
              int position2)
    {
        int temp = array[position1];
        array[position1] = array[position2];
        array[position2] = temp;
    }

    /* This function takes last element as
    pivot, places the pivot element at its
    correct position in sorted array, and
    places all smaller (smaller than pivot)
    to left of pivot and all greater elements
    to right of pivot */
    int partition(int []arr, int low,
                         int high)
    {
        int pivot = arr[high];

        // Index of smaller element
        int i = (low - 1);

        for (int j = low; j <= high- 1; j++)
        {
            // If current element is smaller
            // than or equal to pivot
            if (arr[j] <= pivot)
            {
                i++; // increment index of
                // smaller element
                Swap(arr, i, j);
            }
        }
        Swap(arr, i + 1, high);
        return (i + 1);
    }

    /* The main function that
       implements QuickSort
    arr[] --> Array to be sorted,
    low --> Starting index,
    high --> Ending index */
    void quickSortL(int []arr, int low, int high)
    {
        if (low < high)
        {
        /* pi is partitioning index,
        arr[p] is now at right place */
            int pi = partition(arr, low, high);

            // Separately sort elements before
            // partition and after partition
            quickSortL(arr, low, pi - 1);
            quickSortL(arr, pi + 1, high);
        }
    }

    /* Function to print an array */
    void printArray(int []arr, int size)
    {
        int i;
        for (i = 0; i < size; i++)
            System.out.print(" " + arr[i]);
        System.out.println();
    }

}
