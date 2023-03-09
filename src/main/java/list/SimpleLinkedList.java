package list;

import java.util.Objects;

public class SimpleLinkedList implements SimpleList {

	int size = 0;
	private Node first;
	private Node last;

	@Override
	public boolean add(String value) {
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
	public void add(int index, String value) {
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
	public String set(int index, String value) {
		validateIndex(index);
		Node now = first;
		for (int i = 0; i < index; i++) {
			now = first.next;
		}
		String oldValue = now.value;
		now.value = value;
		return oldValue;
	}

	@Override
	public String get(int index) {
		validateIndex(index);
		Node now = first;
		for (int i = 0; i < index; i++) {
			now = now.next;
		}
		return now.value;
	}

	@Override
	public boolean contains(String value) {
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
	public int indexOf(String value) {
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
	public boolean remove(String value) {
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
	public String remove(int index) {
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

	static class Node {

		Node previous;
		Node next;
		String value;

		public Node(Node previous, String value, Node next) {
			this.previous = previous;
			this.value = value;
			this.next = next;
		}
	}
}
