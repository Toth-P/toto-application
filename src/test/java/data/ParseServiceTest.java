package data;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class ParseServiceTest {
    ParseService parseService = new ParseService();

    @Test
    public void testGetParsableDateShouldReturnParsedDate() {
        LocalDate actual = parseService.getParsedDate("2015.10.29.");
        LocalDate expected = LocalDate.parse("2015-10-29");
        assertEquals( expected, actual);
    }




}
