package list;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SimpleArrayListTest {
	SimpleList list;

	@BeforeEach
	void before() {
		list = new SimpleArrayList();
	}

	@Test
	void add() {
		assertThat(list.add("a")).isTrue();
		assertThat(list.add("b")).isTrue();
		assertThat(list.size()).isEqualTo(2);
		assertThat(list.get(0)).isEqualTo("a");
		assertThat(list.get(1)).isEqualTo("b");
	}

	@DisplayName("요소 추가시 배열이 가득 찬 경우, 배열의 크기를 늘리고 추가한다.")
	@Test
	void add2() {
		for (int i = 0; i < 10; i++) {
			list.add(String.valueOf(i));
		}
		list.add("add");
		assertThat(list.get(10)).isEqualTo("add");
	}

	@Test
	void addByIndex() {
		list.add("a");
		list.add("b");
		list.add(1, "c");
		assertThat(list.get(0)).isEqualTo("a");
		assertThat(list.get(1)).isEqualTo("c");
		assertThat(list.get(2)).isEqualTo("b");
	}

	@DisplayName("특정 인덱스에 요소 추가시 배열이 가득 찬 경우, 배열의 크기를 늘리고 삽입한다.")
	@Test
	void addByIndex2() {
		for (int i = 0; i < 10; i++) {
			list.add(String.valueOf(i));
		}
		list.add(5, "add");
		assertThat(list.get(4)).isEqualTo("4");
		assertThat(list.get(5)).isEqualTo("add");
		assertThat(list.get(6)).isEqualTo("5");
		assertThat(list.get(10)).isEqualTo("9");
	}

	@DisplayName("size와 동일한 인덱스로 add할 경우 성공한다.")
	@Test
	void addByIndex3() {
		list.add(0, "a");
		assertThat(list.get(0)).isEqualTo("a");
	}

	@DisplayName("size보다 큰 인덱스로 add할 경우 예외가 발생한다.")
	@Test
	void addByIndex4() {
		assertThatThrownBy(() -> list.add(1, "a"));
	}

	@DisplayName("0보다 작은 인덱스로 add할 경우 예외가 발생한다.")
	@Test
	void addByIndex5() {
		assertThatThrownBy(() -> list.add(-1, "a"));
	}

	@Test
	void set() {
		list.add("a");
		assertThat(list.set(0, "b")).isEqualTo("a");
		assertThat(list.get(0)).isEqualTo("b");
	}

	@DisplayName("0보다 작은 인덱스에 set할 경우 예외가 발생한다.")
	@Test
	void set2() {
		assertThatThrownBy(() -> list.set(-1, "a"));
	}

	@DisplayName("size보다 크거나 같은 인덱스에 set할 경우 예외가 발생한다.")
	@Test
	void set3() {
		assertThatThrownBy(() -> list.set(0, "a"));
	}

	@Test
	void get() {
		list.add("a");
		assertThat(list.get(0)).isEqualTo("a");
	}

	@DisplayName("0보다 작은 인덱스로 get할경우 예외가 발생한다.")
	@Test
	void get2() {
		assertThatThrownBy(() -> list.get(-1));
	}

	@DisplayName("size보다 크거나 같은 인덱스로 get할경우 예외가 발생한다.")
	@Test
	void get3() {
		assertThatThrownBy(() -> list.get(0));
	}

	@Test
	void contains() {
		list.add("a");
		assertThat(list.contains("a")).isTrue();
		assertThat(list.contains("b")).isFalse();
	}

	@Test
	void indexOf() {
		list.add("a");
		list.add("b");
		assertThat(list.indexOf("a")).isEqualTo(0);
		assertThat(list.indexOf("b")).isEqualTo(1);
	}

	@DisplayName("존재하지 않는 요소의 인덱스를 조회할 경우 -1를 반환한다.")
	@Test
	void indexOf2() {
		assertThat(list.indexOf("a")).isEqualTo(-1);
	}

	@Test
	void size() {
		assertThat(list.size()).isEqualTo(0);

		list.add("a");
		list.add("b");
		assertThat(list.size()).isEqualTo(2);
	}

	@Test
	void isEmpty() {
		assertThat(list.isEmpty()).isTrue();
		list.add("a");
		assertThat(list.isEmpty()).isFalse();
	}

	@DisplayName("값으로 remove한다.")
	@Test
	void remove() {
		list.add("a");
		list.add("b");
		list.add("c");
		assertThat(list.remove("no")).isFalse();
		assertThat(list.remove("b")).isTrue();
		assertThat(list.size()).isEqualTo(2);
		assertThat(list.indexOf("c")).isEqualTo(1);
	}

	@DisplayName("인덱스로 remove한다.")
	@Test
	void remove2() {
		list.add("a");
		list.add("b");
		list.add("c");
		assertThat(list.remove(1)).isEqualTo("b");
		assertThat(list.size()).isEqualTo(2);
		assertThat(list.get(1)).isEqualTo("c");
	}

	@DisplayName("0보다 작은 인덱스로 remove하면 예외가 발생한다.")
	@Test
	void remove3() {
		assertThatThrownBy(() -> list.remove(-1));
	}

	@DisplayName("size보다 크거나 같은 인덱스로 remove하면 예외가 발생한다.")
	@Test
	void remove4() {
		assertThatThrownBy(() -> list.remove(0));
	}

	@Test
	void clear() {
		list.add("a");
		list.add("b");
		list.clear();
		assertThat(list.size()).isEqualTo(0);
		assertThatThrownBy(() -> list.get(0));
	}
}
