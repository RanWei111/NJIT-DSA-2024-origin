package oy.tol.tra;

public class Algorithms {
   
    public static <T extends Comparable<T>> void sort(T [] grades) {
        int j = 0;
        while(j < grades.length){
           int i = grades.length-1;
           while (i > 0) {
              if (grades[i].compareTo(grades[i-1])<0) {
                 T tmp = grades[i];
                 grades[i] = grades[i-1];
                 grades[i-1] = tmp;
              }
              i--;
           }
           j++;
        }
     
    }
   public static <T> void reverse(T [] grades) {
        int i = 0;
        while (i < grades.length/2) {
           T temp = grades[i];
           grades[i] = grades[grades.length-i-1];
           grades[grades.length-i-1] = temp;
           i++;
       }
}
public static <E extends Comparable<E>> void fastSort(E [] array) {
   if (array == null || array.length <= 1){
       return;
   }
   quickSort(array,0,array.length-1);
}

public static <E extends Comparable<E>> void quickSort(E [] array, int begin, int end){
   if (begin < end){
       int p = partition(array,begin,end);
       quickSort(array,begin,p-1);
       quickSort(array,p+1,end);
   }
}
private static <E extends Comparable<E>> int partition(E [] array, int begin, int end) {
   int i = begin-1;
   for (int leftIndex = begin;leftIndex<end;leftIndex++){
       if (array[leftIndex].compareTo(array[end])<0){
           i++;
           swap(array,i,leftIndex);
       }
   }
   swap(array,i+1,end);
   return i+1;
}
public static <T> void swap(T[] array,int i,int j){
   T temp = array[i];
   array[i] = array[j];
   array[j] = temp;
}

public static <T extends Comparable<T>> int binarySearch(T aValue, T [] fromArray, int fromIndex, int toIndex) {
   if (fromIndex > toIndex) {
       return -1;
   }

   int middle = (fromIndex+toIndex)/2;

   if (aValue.compareTo(fromArray[middle]) == 0) {
       return middle;
   }
   else if (aValue.compareTo(fromArray[middle]) < 0) {
       return binarySearch(aValue, fromArray, fromIndex, middle - 1);
   }
   else {
       return binarySearch(aValue, fromArray, middle + 1, toIndex);
   }

}

}