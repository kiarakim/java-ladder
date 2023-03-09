package generic;

public interface SimpleList<T> {

	static <T> SimpleList<T> fromArrayToList(T[] array) {
		final SimpleArrayList<T> values = new SimpleArrayList<>();
		for (final T t : array) {
			values.add(t);
		}
		return values;
	}

	static <T extends Number> double sum(SimpleList<T> list) {
		double result = 0;
		for (int i = 0; i < list.size(); i++) {
			result += list.get(i).doubleValue();
		}
		return result;
	}

	static <T extends Number> SimpleList<T> filterNegative(SimpleList<T> list) {
		SimpleList<T> result = new SimpleArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).doubleValue() > 0) {
				result.add(list.get(i));
			}
		}
		return result;
	}

	static <T> void copy(SimpleList<? extends T> from, SimpleList<? super T> to) {
		to.clear();
		for (int i = 0; i < from.size(); i++) {
			to.add(from.get(i));
		}
	}

	boolean add(T value);

	void add(int index, T value);

	T set(int index, T value);

	T get(int index);

	boolean contains(T value);

	int indexOf(T value);

	int size();

	boolean isEmpty();

	boolean remove(T value);

	T remove(int index);

	void clear();

}
