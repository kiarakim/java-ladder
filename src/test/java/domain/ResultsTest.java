package domain;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("예상 실행 결과")
class ResultsTest {
	@DisplayName("개수는 참여자의 수와 같아야 한다")
	@Test
	void resultSameAsPeopleSize() {
		int peopleSize = 2;
		Results results = Results.from(List.of("꽝", "5000"), peopleSize);

		assertThat(results.size()).isEqualTo(peopleSize);
	}

	@DisplayName("개수가 참여자의 수와 다르면 예외가 발생한다")
	@Test
	void resultSize_ShouldMatchPeopleSize() {
		int peopleSize = 2;

		assertThatThrownBy(() ->
			Results.from(List.of("꽝", "5000", "다음"), peopleSize))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("[ERROR] 참여자의 수와 결과는 같아야 합니다");
	}

	@DisplayName("2개 미만이면 예외가 발생한다")
	@Test
	void resultsSize1() {
		assertThatThrownBy(() -> Results.from(List.of("3000"), 1))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("[ERROR] 예상 결과 개수는 2 ~ 10개여야 합니다");
	}

	@DisplayName("10개 초과면 예외가 발생한다")
	@Test
	void resultsSize11() {
		List<String> sequences = IntStream.range(0, 11)
			.mapToObj(i -> "당첨" + i)
			.collect(Collectors.toList());

		assertThatThrownBy(() -> Results.from(sequences, 11))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("[ERROR] 예상 결과 개수는 2 ~ 10개여야 합니다");
	}
}
