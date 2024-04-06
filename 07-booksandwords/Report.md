# Report
## Question 1. What is your hash function like for hash table solution (if you implemented hash table) ：
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

I tried to improve the quick sorting method to speed up the process, but suddenly encountered some difficult problems to solve. Of course, I eventually resolved them. The most impressive issue for me was the issue with generic interfaces. I directly declared [<K, V>], which resulted in errors in the big picture, but I never noticed it. Specific constraints can be applied to them using [<K extends Comparable <K> , V extends Comparable<V>>]. It requires that [K] must be an instance or subclass of [Comparable<K>], and [V] must be an instance or subclass of [Comparable<V>].

## Question 4. What can you say about the time complexity of your implementation? How efficient is the code in reading and managing the words and their counts? How efficient is your code in getting the top-100 list? Which sorting algorithm are you using? What is the time complexity of that algorithm? :



## Question 5. What did you find the most difficult things to understand and implement in this programming task? Why?:


## Question 6. What did you learn doing this? :