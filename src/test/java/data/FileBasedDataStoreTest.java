package data;

import domain.Round;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FileBasedDataStoreTest {
    FileBasedDataStore fileBasedDataStore = new FileBasedDataStore();

    @Test
    public void testGetRoundsShouldReturnRoundListWith1175SizeWhenTheFileHas1175Lines() {
        List<Round> actual = fileBasedDataStore.getRounds();
        assertEquals(1175, actual.size());
    }

    @Test
    public void getDateFromYearAndNumberOfTheWeek() {
        String[] yearAndWeekOfYear1 = {"2021", "2"};
        String[] yearAndWeekOfYear2 = {"2021", "3"};
        LocalDate actual1 = fileBasedDataStore.getDateFromYearAndNumberOfTheWeek(yearAndWeekOfYear1);
        LocalDate actual2 = fileBasedDataStore.getDateFromYearAndNumberOfTheWeek(yearAndWeekOfYear2);
        LocalDate expected1 = LocalDate.parse("2021-01-04");
        LocalDate expected2 = LocalDate.parse("2021-01-11");
        assertEquals(expected1, actual1);
        assertEquals(expected2, actual2);
    }
}
