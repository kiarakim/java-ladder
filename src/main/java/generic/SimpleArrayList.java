package generic;

import java.util.Arrays;

import org.jetbrains.annotations.NotNull;

public class SimpleArrayList<T> implements SimpleList<T> {

	private static final int DEFAULT_CAPACITY = 10;

	private T[] elementData;
	private int size;
	private int capacity;

	public SimpleArrayList() {
		this.elementData = (T[])new Object[DEFAULT_CAPACITY];
		this.size = 0;
		this.capacity = DEFAULT_CAPACITY;
	}

	@SafeVarargs
	public SimpleArrayList(T @NotNull ... values) {
		this();
		for (T value : values) {
			this.add(value);
		}
	}

	@Override
	public boolean add(T value) {
		plusSize();
		elementData[size - 1] = value;
		return true;
	}

	@Override
	public void add(int index, T value) {
		validateIndexWhenInsert(index);
		plusSize();
		System.arraycopy(elementData, index, elementData, index + 1, size - index);
		elementData[index] = value;
	}

	private void plusSize() {
		size++;
		if (size > capacity) {
			expand();
		}
	}

	private void expand() {
		elementData = Arrays.copyOf(elementData, capacity + DEFAULT_CAPACITY);
		capacity += DEFAULT_CAPACITY;
	}

	@Override
	public T set(int index, T value) {
		validateIndex(index);
		T oldValue = elementData[index];
		elementData[index] = value;
		return oldValue;
	}

	@Override
	public T get(int index) {
		validateIndex(index);
		return elementData[index];
	}

	@Override
	public boolean contains(T value) {
		return indexOf(value) != -1;
	}

	@Override
	public int indexOf(T value) {
		for (int i = 0; i < size; i++) {
			if (elementData[i].equals(value)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean remove(T value) {
		int index = indexOf(value);
		if (index == -1) {
			return false;
		}
		removeByIndex(index);
		return true;
	}

	@Override
	public T remove(int index) {
		validateIndex(index);
		T oldValue = elementData[index];
		removeByIndex(index);
		return oldValue;
	}

	private void removeByIndex(int index) {
		size--;
		System.arraycopy(elementData, index + 1, elementData, index, size - index);
		elementData[size] = null;
	}

	@Override
	public void clear() {
		capacity = DEFAULT_CAPACITY;
		size = 0;
		elementData = (T[])new Object[DEFAULT_CAPACITY];
	}

	private void validateIndex(int index) {
		if (index < 0 || index >= size) {
			throw new RuntimeException();
		}
	}

	private void validateIndexWhenInsert(int index) {
		if (index < 0 || index > size) {
			throw new RuntimeException();
		}
	}
}
