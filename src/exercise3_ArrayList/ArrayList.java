package exercise3_ArrayList;

public class ArrayList<T> implements ListADT<T> {

	private T[] array;
	private static final int DEFAULT_CAPACITY = 100;
	private int size;

	public ArrayList() {

		array = (T[]) new Object[DEFAULT_CAPACITY];
		size = 0;
	}

	@Override
	public void add(T entry) {
		if (size == array.length) {
			expandCapacity();
		}
		array[size] = entry;
		size++;
	}

	private void expandCapacity() {
		T[] biggerArray = (T[]) new Object[array.length * 2];
		for (int i = 0; i < array.length; i++) {
			biggerArray[i] = array[i];
		}
		array = biggerArray;
	}

	@Override
	public void add(int index, T entry) {
		if (size == array.length) {
			expandCapacity();
		}

		if (index < size && index >= 0) {
			// to hold the elements after the chosen element
			T[] temp = (T[]) new Object[array.length - index];
			for (int i = 0, j = index; i < temp.length; i++, j++) {
				temp[i] = array[j];
			}
			array[index] = entry; // to put the new entry
			size++;

			// to add the rest of the elements after the new entry
			for (int i = index + 1, j = 0; i < size; i++, j++) {
				array[i] = temp[j];
			}
		}
	}

	@Override
	public T remove(int index) {
		T result = null;

		if (index < size && index >= 0) {
			result = array[index];

			for (int i = index; i < size - 1; i++) {
				array[i] = array[i + 1];
			}
			array[size] = null;
			size--;
		}

		return result;
	}

	@Override
	public T replace(int index, T entry) {
		T old = null;
		if (index < size && index >= 0) {
			old = array[index];
			array[index] = entry;
		}
		return old;
	}

	@Override
	public boolean contains(T entry) {
		boolean contains = false;
		for (int i = 0; i < size; i++) {
			if (array[i].equals(entry)) {
				contains = true;
				break;
			}
		}
		return contains;
	}

	@Override
	public T get(int index) {
		T result = null;
		if (isEmpty() || (index >= 0 && index <= size() - 1)) {
			result = array[index];
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
		T[] newArray = (T[]) new Object[DEFAULT_CAPACITY];
		array = newArray;
		size = 0;
	}

	@Override
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] result = (T[]) new Object[size];
		for (int i = 0; i < size; i++) {
			result[i] = array[i];
		}
		return result;
	}

}
