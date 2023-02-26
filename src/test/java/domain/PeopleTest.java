package domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("사람들은 ")
class PeopleTest {
	@DisplayName("2~10 명이다")
	@Test
	void peopleSizeBetween2_10() {
		People people = People.from(List.of("salmn", "kiara"));

		assertThat(people.size()).isBetween(2, 10);
	}

	@DisplayName("2명 미만이면 예외가 발생한다")
	@Test
	void peopleSize1() {
		assertThatThrownBy(() -> People.from(List.of("kiara")))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("[ERROR] 참여 인원은 2 ~ 10명이어야 합니다");
	}

	@DisplayName("10명 초과면 예외가 발생한다")
	@Test
	void peopleSize11() {
		List<String> names = IntStream.range(0, 11)
			.mapToObj(i -> "kia" + i)
			.collect(Collectors.toList());

		assertThatThrownBy(() -> People.from(names))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("[ERROR] 참여 인원은 2 ~ 10명이어야 합니다");
	}

	@DisplayName("중복된 이름은 예외가 발생한다")
	@Test
	void duplication() {
		assertThatThrownBy(() -> People.from(List.of("kiara", "kiara")))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("[ERROR] 사람 이름은 중복되지 않아야 합니다");
	}

	@DisplayName("참여자 외의 다른 이름을 검색하면 예외가 발생한다.")
	@ParameterizedTest
	@ValueSource(strings = {"me", "you", "guys"})
	void searchInParticipants(String target) {
		People people = People.from(List.of("salmn", "kiara"));

		assertThatThrownBy(() -> people.checkExistence(target))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("[ERROR] 대상은 참여자에 존재하지 않습니다");
	}
}
