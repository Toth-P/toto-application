import data.FileBasedDataStore;
import data.ParseService;
import domain.Outcome;
import domain.Result;
import service.TotoService;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Main class to process the requested date and tips and calculate the prize.
 */
public class App {
    public static void main(String[] args) {
        FileBasedDataStore fileBasedDataStore = new FileBasedDataStore();
        TotoService totoService = new TotoService(fileBasedDataStore);
        ParseService parseService = new ParseService();
        Scanner keyboardInput = new Scanner(System.in);

        playToto(totoService, parseService, keyboardInput);
    }

    private static void playToto(TotoService totoService, ParseService parseService, Scanner keyboardInput) {
        String outcome;
        String date;

        statistics(totoService);
        do {
            System.out.println("Enter a date in this format: YYYY.MM.DD.  (e.g. 2015.10.29.)");
            date = keyboardInput.nextLine();
        } while (!date.matches("^\\d{4}\\.(0[1-9]|1[012])\\.(0[1-9]|[12][0-9]|3[01]).$"));

        do {
            System.out.println("Enter a possible outcomes for the 14 match.  (e.g. 21112221211112)");
            System.out.println("Team #1 won: 1 , Team #2 won: 2, Draw: X");
            outcome = keyboardInput.nextLine();
        } while (!outcome.matches("^[xX1-2]{14}"));

        List<Outcome> outcomeList = totoService.getOutcomeListFromInput(outcome);
        try {
            LocalDate parsedDate = parseService.getParsedDate(date);
            try {
                Result result = totoService.getResult(parsedDate, outcomeList);
                statistics(totoService);
                inputs(outcome, date);
                System.out.println("Result: hits: " + result.getHitCount() + ", amount: " + NumberFormat.getNumberInstance(Locale.US).format(result.getPrize()) + " Ft");
            } catch (NoSuchElementException e) {
                statistics(totoService);
                inputs(outcome, date);
                System.out.println("There was no such round");
            }
        } catch (DateTimeParseException e) {
            System.out.println("The date does not exist: " + date);
        }
    }

    private static void inputs(String outcome, String date) {
        System.out.println("Enter date: " + date);
        System.out.println("Enter outcomes: " + outcome);
    }

    private static void statistics(TotoService totoService) {
        System.out.println("The largest prize ever recorded: " + NumberFormat.getNumberInstance(Locale.US).format(totoService.getLargestPrize()) + " Ft");
        System.out.println(totoService.calculateStatistics().toString());
    }
}
