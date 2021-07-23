package data;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import domain.Hit;
import domain.Outcome;
import domain.Round;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static java.lang.Integer.*;

public class FileBasedDataStore implements DataStore {

    private List<Round> rounds;


    public void init(String path) {

    }

    @Override
    public List<Round> getRounds() {
        rounds = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("src/toto.csv");
            CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
            CSVReader reader = new CSVReaderBuilder(fileReader)
                    .withCSVParser(parser)
                    .build();
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                Round round = createRound(nextLine);
                rounds.add(round);
            }
        } catch (CsvValidationException | IOException e) {
            e.printStackTrace();
        }
        return rounds;
    }


    private Round createRound(String[] nextLine) {
        Round round = new Round();
        round.setYear(Integer.parseInt(nextLine[0]));
        round.setWeek(Integer.parseInt(nextLine[1]));
        setRoundOfWeek(nextLine, round);
        setLocalDate(nextLine, round);
        setHits(nextLine, round);
        setOutcomes(nextLine, round);
        return round;
    }

    private void setOutcomes(String[] nextLine, Round round) {
        List<Outcome> outcomes = getOutcomeListFromLine(nextLine);
        round.setOutcomes(outcomes);
    }

    private void setHits(String[] nextLine, Round round) {
        List<Hit> hits = getHitListFromLine(nextLine);
        round.setHits(hits);
    }

    private void setRoundOfWeek(String[] nextLine, Round round) {
        if (nextLine[2].equals("-")) nextLine[2] = "1";
        round.setRoundOfWeek(Integer.parseInt(nextLine[2]));
    }

    private void setLocalDate(String[] nextLine, Round round) {
        String date;
        if ("".equals(nextLine[3])) {
            LocalDate localDate = getDateFromYearAndNumberOfTheWeek(nextLine);
            round.setDate(localDate);
        } else {
            date = getParsableDate(nextLine[3]);
            round.setDate(LocalDate.parse(date));
        }
    }

    private LocalDate getDateFromYearAndNumberOfTheWeek(String[] nextLine) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, Integer.parseInt(nextLine[0]));
        calendar.set(Calendar.WEEK_OF_YEAR, Integer.parseInt(nextLine[1]));
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return LocalDateTime.ofInstant(calendar.toInstant(), calendar.getTimeZone().toZoneId()).toLocalDate();
    }

    public String getParsableDate(String s) {
        String date = s.replaceAll("\\.", "-");
        date = date.substring(0, date.length() - 1);
        return date;
    }

    private Hit createHit(int count, String numberOfWagers, String prize) {
        prize = prize.replaceAll("[^\\d.]", "");

        return Hit.builder().withHitCount(count).withNumberOfWagers(parseInt(numberOfWagers)).withPrize(parseInt(prize)).build();
    }


    private List<Hit> getHitListFromLine(String[] nextLine) {
        List<Hit> hits = new ArrayList<>();
        int countNum = 14;
        int lineNum = 4;
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
        if (possibleOutcome.equals("1")) {
            return Outcome._1;
        } else if (possibleOutcome.equals("2")) {
            return Outcome._2;
        } else return Outcome.X;
    }

}
