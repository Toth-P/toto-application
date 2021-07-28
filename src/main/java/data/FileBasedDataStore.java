package data;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import domain.Hit;
import domain.Outcome;
import domain.Round;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static java.lang.Integer.*;

/**
 * Data class to import data from file.
 */
public class FileBasedDataStore implements DataStore {

    public static final String REGEX_FOR_HIT = "[^\\d.]";
    public static final String REPLACEMENT_FOR_HIT = "";
    public static final int COUNT_NUMBER_FOR_HITCOUNT = 14;
    public static final int LINE_NUMBER = 4;
    private List<Round> rounds;

    ParseService parseService = new ParseService();

    @Override
    public List<Round> getRounds() {
        rounds = new ArrayList<>();
        try {
            CSVReader reader = createCSVReader();
            createRoundsFromFile(reader);
        } catch (CsvValidationException | IOException e) {
            e.printStackTrace();
        }
        return rounds;
    }

    private void createRoundsFromFile(CSVReader reader) throws IOException, CsvValidationException {
        String[] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            Round round = createRound(nextLine);
            rounds.add(round);
        }
    }

    private CSVReader createCSVReader() throws FileNotFoundException {
        FileReader fileReader = new FileReader("src/toto.csv");
        CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
        return new CSVReaderBuilder(fileReader)
                .withCSVParser(parser)
                .build();
    }


    private Round createRound(String[] nextLine) {
        return Round.builder()
                .withYear(Integer.parseInt(nextLine[0]))
                .withWeek(Integer.parseInt(nextLine[1]))
                .withRoundOfWeek(getRoundOfWeek(nextLine))
                .withDate(getLocalDate(nextLine))
                .withHits(getHits(nextLine))
                .withOutcomes(getOutcomes(nextLine))
                .build();
    }

    private List<Outcome> getOutcomes(String[] nextLine) {
        return getOutcomeListFromLine(nextLine);
    }

    private List<Hit> getHits(String[] nextLine) {
        return getHitListFromLine(nextLine);
    }

    private int getRoundOfWeek(String[] nextLine) {
        if ("-".equals(nextLine[2])) nextLine[2] = "1";
        return Integer.parseInt(nextLine[2]);
    }

    private LocalDate getLocalDate(String[] nextLine) {
        if ("".equals(nextLine[3])) {
            return getDateFromYearAndNumberOfTheWeek(nextLine);
        } else {
            return parseService.getParsedDate(nextLine[3]);
        }
    }

    public LocalDate getDateFromYearAndNumberOfTheWeek(String[] nextLine) {
        int week = Integer.parseInt(nextLine[1]);
        int year = Integer.parseInt(nextLine[0]);
        WeekFields weekFields = WeekFields.of(Locale.getDefault());

        return LocalDate.now()
                .withYear(year)
                .with(weekFields.weekOfYear(), week)
                .with(weekFields.dayOfWeek(), 2);
    }

    private Hit createHit(int count, String numberOfWagers, String prize) {
        prize = prize.replaceAll(REGEX_FOR_HIT, REPLACEMENT_FOR_HIT);

        return Hit.builder()
                .withHitCount(count)
                .withNumberOfWagers(parseInt(numberOfWagers))
                .withPrize(parseInt(prize))
                .build();
    }

    private List<Hit> getHitListFromLine(String[] nextLine) {
        List<Hit> hits = new ArrayList<>();
        int countNum = COUNT_NUMBER_FOR_HITCOUNT;
        int lineNum = LINE_NUMBER;
        for (int i = 0; i < 5; i++) {
            hits.add(createHit(countNum, nextLine[lineNum], nextLine[lineNum + 1]));
            countNum--;
            lineNum += 2;
        }
        return hits;
    }

    private List<Outcome> getOutcomeListFromLine(String[] nextLine) {
        List<Outcome> outcomes = new ArrayList<>();
        for (int i = 14; i < 28; i++) {
            outcomes.add(createOutcome(nextLine[i]));

        }
        return outcomes;
    }

    private Outcome createOutcome(String possibleOutcome) {
        if (Outcome._1.getOutcome().equals(possibleOutcome)) {
            return Outcome._1;
        } else if (Outcome._2.getOutcome().equals(possibleOutcome)) {
            return Outcome._2;
        } else return Outcome.X;
    }
}
