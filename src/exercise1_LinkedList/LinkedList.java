package exercise1_LinkedList;

public class LinkedList<T> implements ListADT<T> {

	private Node<T> firstNode;
	private Node<T> lastNode;
	private int size;

	public LinkedList() {
		firstNode = null;
		lastNode = null;
		size = 0;
	}

	@Override
	public void add(T entry) {
		Node<T> newNode = new Node<>(entry);
		if (firstNode == null) {
			firstNode = newNode;
			lastNode = newNode;
		} else {
			lastNode.next = newNode;
			lastNode = newNode;
		}
		size++;
	}

	@Override
	public void add(int index, T entry) {
		if (index >= 0 && index <= size) {

			Node<T> newNode = new Node<T>(entry);
			if (isEmpty()) {
				firstNode = newNode;
				lastNode = newNode;
			} else if (index == 0) {
				newNode.next = firstNode;
				firstNode = newNode;
			} else if (index == size) {
				lastNode.next = newNode;
				lastNode = newNode;
			} else {
				Node<T> nodeBefore = getNodeAtIndex(index - 1);
				Node<T> nodeAfter = getNodeAtIndex(index);
				newNode.next = nodeAfter;
				nodeBefore.next = newNode;
			}
			size++;
		} else {
			throw new IndexOutOfBoundsException();
		}
	}

	@Override
	public T remove(int index) {
		T result = null;
		if (isEmpty() || (index >= 0 && index <= size() - 1)) {

			if (index == 0) {
				result = firstNode.data;
				firstNode = firstNode.next;
				if (size == 1) {
					lastNode = null;
				}

			} else {
				Node<T> nodeBefore = getNodeAtIndex(index - 1);
				Node<T> nodeToRemove = nodeBefore.next;
				Node<T> nodeAfter = nodeToRemove.next;
				nodeBefore.next = nodeAfter;
				result = nodeToRemove.data;
				if (index == size) {
					lastNode = nodeBefore;
				}
			}

			size--;

		} else {
			throw new IndexOutOfBoundsException();
		}
		return result;

	}

	@Override
	public T replace(int index, T entry) {
		T result = null;
		if (isEmpty() || (index >= 0 && index <= size() - 1)) {
			Node<T> nodeToReplace = getNodeAtIndex(index);
			result = nodeToReplace.data;
			nodeToReplace.data = entry;
		} else {
			throw new IndexOutOfBoundsException();
		}
		return result;

	}

	private Node<T> getNodeAtIndex(int index) {
		Node<T> currentNode = firstNode;
		for (int i = 0; i < index; i++) {
			currentNode = currentNode.next;
		}
		return currentNode;

	}

	@Override
	public boolean contains(T entry) {
		boolean containsElement = false;

		Node<T> temp = firstNode;
		for (int i = 0; i < size; i++) {
			if (temp.data.equals(entry)) {
				containsElement = true;
				break;
			}
			temp = temp.next;
		}

		return containsElement;
	}

	@Override
	public T getDataFromIndex(int index) {
		T result = null;
		if (isEmpty() || (index >= 0 && index <= size() - 1)) {
			result = getNodeAtIndex(index).data;
		} else {
			throw new IndexOutOfBoundsException();
		}
		return result;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public void clear() {
		firstNode = null;
		lastNode = null;
		size = 0;

	}

	@Override
	public T[] toArray() {

		T[] temp = (T[]) new Object[size];

		for (int i = 0; i < size; i++) {
			temp[i] = getNodeAtIndex(i).data;
		}
		return temp;
	}

	private static class Node<T> {
		private T data; // entry in bag
		private Node<T> next; // link to next node

		public Node(T data) {
			this(data, null);
		}

		public Node(T data, Node<T> next) {
			this.data = data;
			this.next = next;
		}

		public T getData() {
			return data;
		}

		public Node<T> getNext() {
			return next;
		}
	} // end Node<T> class

}
