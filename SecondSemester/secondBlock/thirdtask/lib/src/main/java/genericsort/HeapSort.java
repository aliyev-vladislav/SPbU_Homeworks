package genericsort;

/*
 * Implementation of pyramid sorting
 */
public class HeapSort <E extends Comparable<E>> {
    public void sort(E[] arr) {
        int size = arr.length;

        // Build heap (regroup an array)
        for (int i = size / 2 - 1; i >= 0; i--) {
            heapify(arr, size, i);
        }

        // Extract items from the heap one by one
        for (int i = size - 1; i >= 0; i--) {

            // Move the current root to the end
            E temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify(arr, i, 0);
        }
    }

    // A method for converting a subtree to a binary heap with a root node 'node'
    void heapify (E[] arr, int size, int node) {
        int largest = node;
        int left = 2 * node + 1;
        int right = 2 * node + 2;

        if (left < size && arr[left].compareTo(arr[largest]) > 0) {
            largest = left;
        }
        if (right < size && arr[right].compareTo(arr[largest]) > 0) {
            largest = right;
        }
        if (largest != node) {
            E swap = arr[node];
            arr[node] = arr[largest];
            arr[largest] = swap;

            // Recursively convert the affected subtree to a binary heap
            heapify(arr, size, largest);
        }
    }
}
