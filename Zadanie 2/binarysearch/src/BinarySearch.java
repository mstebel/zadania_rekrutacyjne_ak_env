class BinarySearch {

    public static void main(String[] args) {
        BinarySearch binarySearch = new BinarySearch();
        boolean isInArray = binarySearch.search(new int[]{6, 4, 1, 0, -2}, 10);
        System.out.println(isInArray);
    }

    public boolean search(int[] arr, int element) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int middle = (low + high) / 2;
            if (element == arr[middle]) {
                return true;
            } else if (element > arr[middle]) {
                high = middle - 1;
            } else {
                low = middle + 1;
            }
        }
        return false;
    }
}
