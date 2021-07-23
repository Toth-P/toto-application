package data;

import org.junit.Test;

import static org.junit.Assert.*;

public class ParseServiceTest {
    ParseService parseService = new ParseService();

    @Test
    public void testGetParsableDateShouldReturnParsableString() {
        String actual = parseService.getParsableDate("2015.10.29.");
        assertEquals( "2015-10-29", actual);
    }
}
