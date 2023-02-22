package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import domain.Ladder;
import domain.Level;
import domain.People;
import domain.Results;
import domain.Stool;
import view.InputView;
import view.OutputView;

public class LadderController {
	private static final Map<String, String> resultTable = new HashMap<>();

	private final InputView inputView;
	private final OutputView outputView;
	private People people;
	private Results results;
	private Ladder ladder;

	public LadderController(InputView inputView, OutputView outputView) {
		this.inputView = inputView;
		this.outputView = outputView;
	}

	public void init() {
		repeat(() -> people = People.from(inputView.readNames()));
		repeat(() -> results = Results.from(inputView.readRewards()));
		repeat(() -> ladder = Ladder.from(inputView.readHeight(), people.size()));
	}

	public void showLadder() {
		outputView.printResult(people.getNames(), getLadder(), results.getResults());
	}

	public void play() {
		for (int position = 0; position < people.size(); position++) {
			int newPosition = ladder.start(position);
			resultTable.put(people.getNames().get(position), results.getResult(newPosition));
		}
		askWanted();
	}

	public void askWanted() {
		String target = inputView.readTarget();
		if (target.equals("all")) {
			outputView.printAll(resultTable);
			return;
		}
		askEach(target);
	}

	private void askEach(String target) {
		try {
			people.checkExistence(target);
			outputView.printWanted(target, resultTable);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		} finally {
			askWanted();
		}
	}

	private List<Boolean> getLevel(Level level) {
		return level.getStools().stream()
			.map(Stool::isStool)
			.collect(Collectors.toList());
	}

	private List<List<Boolean>> getLadder() {
		return ladder.getLadder().stream()
			.map(this::getLevel)
			.collect(Collectors.toList());
	}

	private void repeat(Runnable repeatable) {
		try {
			repeatable.run();
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
			repeat(repeatable);
		}
	}
}
