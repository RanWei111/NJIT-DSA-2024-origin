# Report
## Question 1. What is your hash function like for hash table solution (if you implemented hash table) ：
The hash function is like
```Java
 private int hashCode(K key) {
    int hash = 0;
    String keyString = key.toString();
        for (int i = 0; i < keyString.length(); i++) {
        hash = 31 * hash + keyString.charAt(i);
    }
    return hash;
}
```
## Question 2. For binary search trees (if you implemented it), how does your implementation get the top-100 list? :
Print an array (from a binary tree) in ascending order by calling the toSortedArray() method in the KeyValue BSearchTree class.
 ```Java
 sorted = words.toSortedArray();
```
Calling Reserve() of Algorithms to reverse them
 ```Java
 Algorithms.reverse(sorted);
```
and the first 100 elements are the ones required for printing. So print out them
```Java
for (int index = 0; index < 100 && index<sorted.length; index++) {
    String word = String.format("%-20s",sorted[index].getKey()).replace(' ', '.');
    System.out.format("%4d. %s %6d%n", index + 1, word, sorted[index].getValue());
}
```
## Question 3. What can you say about the correctness of your implementation? Any issues, bugs or problems you couldn't solve? Any idea why the problem persists and what could perhaps be the solution? :
I tried to improve the quick sorting method to speed up the process, but suddenly encountered some difficult problems to solve. Of course, I eventually resolved them. The most impressive issue for me was the issue with generic interfaces. I directly declared `<K,V>`, which resulted in errors in the big picture, but I never noticed it. Specific constraints can be applied to them using `<K extends Comparable <K> , V extends Comparable<V>>`. It requires that `K` must be an instance or subclass of `Comparable<K>`, and `V` must be an instance or subclass of `Comparable<V>`.

## Question 4. What can you say about the time complexity of your implementation? How efficient is the code in reading and managing the words and their counts? How efficient is your code in getting the top-100 list? Which sorting algorithm are you using? What is the time complexity of that algorithm? :
It can greatly improve submission efficiency. The average performance in reading and managing words and their counts is **O (1)**. Sorting the words in the top 100 lists means that the time complexity may average **O (n log n)**. I use Hoare for quick sorting. In an ideal scenario, when each partitioning operation can almost evenly split the array in half, the time complexity of fast sorting can reach **O (n log n)**, where n is the length of the array to be sorted. This is because after each partition, the problem size is halved, the recursive depth is log n, and the processing time of each layer is linear, so the total time complexity is **O (n log n)**.

## Question 5. What did you find the most difficult things to understand and implement in this programming task? Why?:
It can be said that the most difficult thing is to deal with the methods of dealing with each class, and so on. I spent a lot of time repeatedly checking various types of files.
## Question 6. What did you learn doing this? :
Through self-learning, I have gained a deeper understanding of Java data structures. This not only allowed me to master more programming skills and methods, but also made me more adept at solving practical problems.