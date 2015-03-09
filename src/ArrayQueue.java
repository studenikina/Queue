public class ArrayQueue {
    private int size;
    private Object[] vector;
    private int start;
    private int end;
    private Object output;

    ArrayQueue() {
        size = 0;
        start = 0;
        end = -1;
        vector = new Object[2];
    }
	
	public void clear() {
		start = 0;
		size = 0;
		end = -1;
    }
	
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean enqueue(Object data) {
        //assert data != null;

        size += 1;
        end = (end + 1) % vector.length;
		ensureMaxCapacity(size);
        vector[end] = data;
        //ensureMaxCapacity(size);

        return true;
    }

    public Object dequeue() {
        //assert size > 0;

        output = vector[start];
        size -= 1;
        start = (start + 1) % vector.length;
        //ensureMinCapacity(size);

        return output;
    }
    
    public Object element() {
        //assert size > 0;

        return vector[start];
    }

    private void ensureMaxCapacity(int capacity) {
        if (capacity < vector.length) {
            return;
        }

        resizeVector(capacity * 2);
    }

    private void ensureMinCapacity(int capacity) {
        if ((capacity * 2 >= vector.length) || (vector.length <= 2)) {
            return;
        }

        resizeVector(vector.length / 2);
    }

    private void resizeVector(int newSize) {
        Object[] tempVector = new Object[newSize];
         
        if (start > end) {
            for (int i = start; i < vector.length; i++) {
                tempVector[i - start] = vector[i];
            }
             
            for (int i = 0; i <= end; i++) {
                tempVector[i + (vector.length - start)] = vector[i];
            }
        } else {
            for (int i = start; i <= end; i++) {
                tempVector[i - start] = vector[i];
            }
        }
        
        start = 0;
        end = size - 1;
         
        vector = tempVector;    
	}
	
	public Object[] toArray(ArrayQueue queue){
		Object[] answ = new Object[queue.size()];
		int i = 0;
		while (!queue.isEmpty()) {
			answ[i] = queue.dequeue();
			i++;
		}
		return answ;
	}
}