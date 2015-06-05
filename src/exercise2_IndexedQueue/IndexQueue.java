package exercise2_IndexedQueue;

public class IndexQueue<T> implements QueueADT<T> {

	private LinkedList<T> list;

	public IndexQueue() {
		list = new LinkedList<>();
	}

	@Override
	public void enqueue(T newEntry) {
		list.add(newEntry);
	}

	@Override
	public T dequeue() {

		return list.remove(0);
	}

	@Override
	public T getFront() {
		return list.getDataFromIndex(0);
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public void clear() {
		list.clear();
	}

}
