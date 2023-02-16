package domain;

import java.util.List;
import java.util.stream.Collectors;

public class People {
	private static final int MIN_PEOPLE_SIZE_INCLUSIVE = 2;
	private static final int MAX_PEOPLE_SIZE_INCLUSIVE = 10;

	private final List<Person> people;

	private People(List<Person> people) {
		this.people = people;
	}

	public static People from(List<String> names) {
		validate(names);

		return new People(names.stream()
			.map(Person::new)
			.collect(Collectors.toUnmodifiableList()));
	}

	public int size() {
		return people.size();
	}

	public List<String> getNames() {
		return people.stream()
			.map(Person::getName)
			.collect(Collectors.toList());
	}

	private static void validate(List<String> names) {
		validateSize(names);
		validateDuplication(names);
	}

	private static void validateSize(List<String> names) {
		if (names.size() < MIN_PEOPLE_SIZE_INCLUSIVE)
			throw new IllegalArgumentException("사람은 최소 두명 이상이어야 합니다");
		if (names.size() > MAX_PEOPLE_SIZE_INCLUSIVE)
			throw new IllegalArgumentException("사람은 최대 10명 이어야 합니다");
	}

	private static void validateDuplication(List<String> names) {
		int distinctCount = (int)names.stream()
			.map(String::trim)
			.distinct()
			.count();
		if (distinctCount != names.size())
			throw new IllegalArgumentException("사람 이름은 중복되지 않아야 합니다");
	}
}
