public class ArrayQueueADT {
    private int size = 0;
    private Object[] vector = new Object[2];
    private int start = 0;
    private int end = -1;
    private Object output;

    public static int size(ArrayQueueADT queue) {
        return queue.size;
    }

	public static void clear(ArrayQueueADT queue) {
		queue.start = 0;
		queue.size = 0;
		queue.end = -1;
    }
	
    public static boolean isEmpty(ArrayQueueADT queue) {
        return queue.size == 0;
    }

    public static boolean enqueue(ArrayQueueADT queue, Object data) {
        //assert data != null;

        queue.size += 1;
        queue.end = (queue.end + 1) % queue.vector.length;
        queue.vector[queue.end] = data;
        ensureMaxCapacity(queue, queue.size);

        return true;
    }

    public static Object dequeue(ArrayQueueADT queue) {
        //assert queue.size > 0;

        queue.output = queue.vector[queue.start];
        queue.size -= 1;
        queue.start = (queue.start + 1) % queue.vector.length;
        //ensureMinCapacity(queue, queue.size);

        return queue.output;
    }
    
    public static Object element(ArrayQueueADT queue) {
        //assert queue.size > 0;

        return queue.vector[queue.start];
    }

    private static void ensureMaxCapacity(ArrayQueueADT queue, int capacity) {
        if (capacity < queue.vector.length) {
            return;
        }

        resizeVector(queue, capacity * 2);
    }

    private static void ensureMinCapacity(ArrayQueueADT queue, int capacity) {
        if ((capacity * 2 >= queue.vector.length) || (queue.vector.length <= 2)) {
            return;
        }

        resizeVector(queue, queue.vector.length / 2);
    }

    private static void resizeVector(ArrayQueueADT queue, int newSize) {
        Object[] tempVector = new Object[newSize];
         
        if (queue.start > queue.end) {
            for (int i = queue.start; i < queue.vector.length; i++) {
                tempVector[i - queue.start] = queue.vector[i];
            }
             
            for (int i = 0; i <= queue.end; i++) {
                tempVector[i + (queue.vector.length - queue.start)] = queue.vector[i];
            }
        } else {
            for (int i = queue.start; i <= queue.end; i++) {
                tempVector[i - queue.start] = queue.vector[i];
            }
        }
         
        queue.start = 0;
        queue.end = queue.size - 1;
         
        queue.vector = tempVector;
    }
	
	public Object[] toArray(ArrayQueueADT queue){
		Object[] answ = new Object[size(queue)];
		int i = 0;
		while (!isEmpty(queue)) {
			answ[i] = dequeue(queue);
			i++;
		}
		return answ;
	}
}
