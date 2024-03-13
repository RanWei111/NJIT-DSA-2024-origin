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
}