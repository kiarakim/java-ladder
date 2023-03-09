package generic;

import java.util.Objects;

public class SimpleLinkedList<T> implements SimpleList<T> {

	int size = 0;
	private Node first;
	private Node last;

	class Node {

		public Node(Node previous, T value, Node next) {
			this.previous = previous;
			this.value = value;
			this.next = next;
		}

		Node previous;
		Node next;
		T value;
	}

	@Override
	public boolean add(T value) {
		Node newNode = new Node(last, value, null);
		if (first == null) {
			first = newNode;
		} else {
			last.next = newNode;
		}
		last = newNode;
		size++;
		return true;
	}

	@Override
	public void add(int index, T value) {
		validateIndexWhenInsert(index);
		if (index == size) {
			add(value);
			return;
		}
		Node now = first;
		for (int i = 0; i < index; i++) {
			now = now.next;
		}
		Node newNode = new Node(now.previous, value, now);
		now.previous.next = newNode;
		now.previous = newNode;
		size++;
	}

	@Override
	public T set(int index, T value) {
		validateIndex(index);
		Node now = first;
		for (int i = 0; i < index; i++) {
			now = first.next;
		}
		T oldValue = now.value;
		now.value = value;
		return oldValue;
	}

	@Override
	public T get(int index) {
		validateIndex(index);
		Node now = first;
		for (int i = 0; i < index; i++) {
			now = now.next;
		}
		return now.value;
	}

	@Override
	public boolean contains(T value) {
		Node now = first;
		boolean flag = false;
		while (now != null) {
			if (value.equals(now.value)) {
				flag = true;
				break;
			}
			now = now.next;
		}
		return flag;
	}

	@Override
	public int indexOf(T value) {
		Node now = first;
		int index = 0;
		while (now != null) {
			if (value.equals(now.value)) {
				return index;
			}
			now = now.next;
			index++;
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
	public boolean remove(T value) {
		Node now = first;
		while (now.next != null) {
			if (Objects.equals(now.value, value)) {
				removeNode(now);
				return true;
			}
			now = now.next;
		}
		return false;
	}

	@Override
	public T remove(int index) {
		validateIndex(index);
		Node now = first;
		for (int i = 0; i < index; i++) {
			now = now.next;
		}
		removeNode(now);
		return now.value;
	}

	private void removeNode(Node now) {
		now.previous.next = now.next;
		now.next.previous = now.previous;
		size--;
	}

	@Override
	public void clear() {
		first = null;
		last = null;
		size = 0;
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
