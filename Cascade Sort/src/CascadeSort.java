
import java.util.*;
public class CascadeSort {

    // Main sorting method
    public static void cascadeSort(int[] arr) {
        int n = arr.length;
        
        if (arr == null || arr.length < 2) return;
        
        List<int[]> windows = new ArrayList<>();

        // Phase 1: find adjacent inversions
        for (int i = 0; i<n-1; i++) {
            if (arr[i] > arr[i + 1]) {
                windows.add(new int[]{i, i + 1});
            }
        }
        // Phase 2: expand and merge windows
        boolean changed = true;
        while (changed) {
            changed = false;

            // Expand each window outward by 1
            for (int[] w : windows) {
                int newStart = Math.max(0, w[0] - 1);
                int newEnd = Math.min(n - 1, w[1] + 1);
                if (newStart < w[0] || newEnd > w[1]) {
                    w[0] = newStart;
                    w[1] = newEnd;
                    changed = true;
                }
            }

            // Merge overlapping/adjacent windows
            windows.sort(Comparator.comparingInt(a -> a[0]));
            List<int[]> merged = new ArrayList<>();
            for (int[] w : windows) {
                if (merged.isEmpty() || w[0] > merged.get(merged.size() - 1)[1] + 1) {
                    merged.add(new int[]{w[0], w[1]});
                } else {
                    int[] last = merged.get(merged.size() - 1);
                    last[1] = Math.max(last[1], w[1]);
                }
            }
            windows = merged;
        }

        // Phase 3: sort each window using insertion sort
        for (int[] w : windows) {
            insertionSort(arr, w[0], w[1]);
        }
    }

    // Insertion sort
     static void insertionSort(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= left && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
    public static void main(String[] args) {
        //int[] data = {1000, 2,12345,0 ,10 ,67890, 1,10092, 4, 3,88, 8,7 ,102,6,9,5};
    	int[] data = {3,1,2};
    	//int[] data = {1, 3, 2, 4, 6, 5};
        System.out.println("Original: " + Arrays.toString(data));
        cascadeSort(data);
        System.out.println("Sorted: " + Arrays.toString(data));
        
        long start = System.nanoTime();
        cascadeSort(data);
        long end = System.nanoTime();
        System.out.println("Time taken: " + (end - start));
        
    }
}