public class ArrayQueueModule {
    private static int size = 0;
    private static Object[] vector = new Object[5];
    private static int start = 0;
    private static int end = 0;

    ArrayQueueModule() {
        System.out.println("\ncreating\n");
    }

	public static void clear() {
        size = 0;
		start = 0;
		end = 0;
    }

    public static int size() {
        return size;
    }

    public static boolean isEmpty() {
        return size == 0;
    }
	
    public static boolean enqueue(Object data) {
        ensureMaxCapacity(size + 1);
        size += 1;
        vector[end] = data;
        end = (end + 1) % vector.length;
        return true;
    }

    public static Object dequeue() {
        Object output = vector[start];
        size -= 1;
        start = (start + 1) % vector.length;
        return output;
    }
    
    public static Object element() {
        //assert size > 0;
        return vector[start];
    }

    private static void ensureMaxCapacity(int capacity) {
        if (capacity < vector.length) {
            return;
        }
        resizeVector(capacity * 2);
    }

//    private static void ensureMinCapacity(int capacity) {
//        if ((capacity * 2 >= vector.length) || (vector.length <= 2)) {
//            return;
//        }
//
//        resizeVector(vector.length / 2);
//    }

    private static void resizeVector(int newSize) {
        assert newSize >= size();
        Object[] tempVector = new Object[newSize];

        for (int i = start; i != end; i = (i + 1) % vector.length) {
            tempVector[i - start] = vector[i];
        }
         
        start = 0;
        end = size;
         
        vector = tempVector;
    }
	
	public static Object[] toArray(){
        System.out.println("TO ARRAY");
		Object[] answ = new Object[size()];
		int k = 0;
		while (!isEmpty()) {
			answ[k] = dequeue();
			k++;
		}
//        for (i = 0; i < answ.length; ++i){
//            System.out.println(answ[i]);
//        }
		return answ;
	}


//    public static void main(string[] args){
//		for (int i = 0; i < 100000; i++)
//		{
//			arrayqueuemodule.enqueue(i + 1);
//		}
//        system.out.println(arrayqueuemodule.size());
//		object[] a;
//		//ArrayQueueModule.dequeue();
//		a = new Object[ArrayQueueModule.size()];
//		a = ArrayQueueModule.toArray();
//
//		for (int i = 0; i < a.length; i++){
//			System.out.println(a[i]);
//            System.out.println(ArrayQueueModule.size());
//		}
//        System.out.println(a.length);
//
//	}

}
