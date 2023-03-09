package generic;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.Test;

class SimpleListTest {
	@Test
	void mission1() {
		SimpleList<Integer> values = new SimpleArrayList<Integer>();
		values.add(1);
		values.add(2);

		Integer first = values.get(0);
		Integer second = values.get(1);
	}

	@Test
	void mission2() {
		final String[] arrays = {"first", "second"};

		final SimpleList<String> values = SimpleList.<String>fromArrayToList(arrays);
	}

	@Test
	void mission3() {
		final SimpleList<Double> doubleValues = new SimpleArrayList<Double>(0.5, 0.7);
		final SimpleList<Integer> intValues = new SimpleArrayList<Integer>(1, 2);

		final double doubleTotal = SimpleList.sum(doubleValues); // 1.2
		final double intTotal = SimpleList.sum(intValues);  // 3
	}

	@Test
	void mission4() {
		final SimpleList<Double> doubleValues = new SimpleArrayList<Double>(-0.1, 0.5, 0.7);
		final SimpleList<Integer> intValues = new SimpleArrayList<Integer>(-10, 1, 2);

		final SimpleList<Double> filteredDoubleValues = SimpleList.filterNegative(doubleValues);
		final SimpleList<Integer> filteredIntValues = SimpleList.filterNegative(intValues);
	}

	@Test
	void mission5() {
		class Printer {

		}
		class LaserPrinter extends Printer {

		}
		class SuperLaserPrinter extends LaserPrinter {

		}

		final var laserPrinter = new LaserPrinter();
		final var superLaserPrinter = new SuperLaserPrinter();

		final SimpleList<Printer> printers = new SimpleArrayList<Printer>();
		final SimpleList<Printer> printers2 = new SimpleArrayList<Printer>();
		final SimpleList<LaserPrinter> laserPrinters = new SimpleArrayList<LaserPrinter>(laserPrinter);
		final SimpleList<SuperLaserPrinter> superLaserPrinters = new SimpleArrayList<>(superLaserPrinter);

		SimpleList.copy(laserPrinters, printers);
		SimpleList.copy(superLaserPrinters, printers2);

		assertThat(printers.get(0)).isSameAs(laserPrinter);
		assertThat(printers2.get(0)).isSameAs(superLaserPrinter);
	}

}
