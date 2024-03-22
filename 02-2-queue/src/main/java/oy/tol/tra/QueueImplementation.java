package oy.tol.tra;

public class QueueImplementation<E> implements QueueInterface<E>{

    private Object [] itemArray;
    private int capacity;
    private int head = 0;
    private int tail = 0;
    private int size = 0;
    private static final int DEFAULT_QUEUE_SIZE = 10;
    public QueueImplementation() throws QueueAllocationException{
        this(DEFAULT_QUEUE_SIZE);
    }

    public QueueImplementation(int capacity) throws QueueAllocationException {
        if (capacity < 2){
            throw  new QueueAllocationException("Capacity must greater than 2!");
        }
        try {
            itemArray = new Object[capacity];
            this.capacity = capacity;
            head = 0;
            tail = 0;
            size = 0;
        } catch (OutOfMemoryError e) {
            throw new QueueAllocationException("Failed to allocate memory for the stack.");
        }
    }


    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public void enqueue(E element) throws QueueAllocationException, NullPointerException {
        if (element == null){
            throw new NullPointerException("The element to enqueue can't be null");
        }
        if (size >= capacity){
            try {
                int newCapacity = 2 * capacity;
                Object [] newArray = new Object[newCapacity];
                int i = 0;
               
                while (i<size){
                    if (head+i < capacity){
                        newArray[i] = itemArray[head+i];
                    }else {
                        newArray[i] = itemArray[i-(capacity-head)];
                    }
                    i++;
                }
                itemArray = newArray;
                capacity = newCapacity;
                head = 0;
                tail = size;
            } catch (OutOfMemoryError e) {
                throw new QueueAllocationException("Failed to allocate more room for the stack.");
            }
        }
        itemArray[tail] = element;
        if (tail == capacity-1){
            tail = 0;
        }else {
            tail = tail+1;
        }
        size++;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E dequeue() throws QueueIsEmptyException {
        if (head == tail && size != capacity){
            throw new QueueIsEmptyException("There's no data in the queue.");
        }
        Object dequeueElement = itemArray[head];

        if (head == capacity-1){
            head = 0;
        }else {
            head = head+1;
        }
        size--;
        return (E) dequeueElement;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E element() throws QueueIsEmptyException {
        if (head == tail && size != capacity){
            throw new QueueIsEmptyException("There's no data in the queue.");
        }
        Object el = itemArray[head];
        return (E) el;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (head == tail && size != capacity){
            return true;
        }
        else return false;
    }

    @Override
    public void clear() {
        for (int i=0;i < capacity;i++){
            itemArray[i] = null;
        }
        head = 0;
        tail = 0;
        size = 0;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder builder = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            builder.append(itemArray[(head + i) % itemArray.length].toString());
            if (i < size - 1) {
                builder.append(", ");
            }
        }
        builder.append("]");
        return builder.toString();
    }
}