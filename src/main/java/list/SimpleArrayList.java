package list;

public class SimpleArrayList implements SimpleList {
	Object[] elements;
	int capacity;
	int size;

	public SimpleArrayList() {
		capacity = 1;
		elements = new Object[capacity];
		size = 0;
	}

	@Override
	public boolean add(String value) {
		if (size == capacity) {
			expand();
		}
		elements[size++] = value;
		return true;
	}

	@Override
	public void add(int index, String value) {
		if (size == capacity) {
			expand();
		}
		for (int i = size; i >= index; i--) {
			elements[i] = elements[i - 1];
		}
		elements[index] = value;
		size++;
	}

	private void expand() {
		capacity *= 2;
		Object[] tempArr = new Object[capacity];
		System.arraycopy(elements, 0, tempArr, 0, elements.length);
		elements = new Object[capacity];
		System.arraycopy(tempArr, 0, elements, 0, tempArr.length);
	}

	@Override
	public String set(int index, String value) {
		String oldValue = String.valueOf(elements[index]);
		elements[index] = value;
		return oldValue;
	}

	@Override
	public String get(int index) {
		if (size <= index)
			return null;
		return String.valueOf(elements[index]);
	}

	@Override
	public boolean contains(String value) {
		for (int i = 0; i < size; i++) {
			if (String.valueOf(elements[i]).equals(value)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int indexOf(String value) {
		if (value == null) {
			return -1;
		}
		for (int index = 0; index < size; index++) {
			if (String.valueOf(elements[index]).equals(value)) {
				return index;
			}
		}
		return -1;
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
	public boolean remove(String value) {
		if (!contains(value)) {
			return false;
		}
		int index = indexOf(value);
		for (int i = index; i < size - 1; i++) {
			elements[i] = elements[i + 1];
		}
		size--;
		elements[size] = null;
		return true;
	}

	@Override
	public String remove(int index) {
		if (size <= index) {
			return null;
		}
		String oldValue = String.valueOf(elements[index]);
		for (int i = index; i < size - 1; i++) {
			elements[i] = elements[i + 1];
		}
		elements[size - 1] = null;
		size--;
		return oldValue;
	}

	@Override
	public void clear() {
		for (int i = 0; i < size; i++) {
			elements[i] = null;
		}
		size = 0;
	}
}
