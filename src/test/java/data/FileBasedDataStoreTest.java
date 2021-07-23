package data;

import domain.Round;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class FileBasedDataStoreTest {
    FileBasedDataStore fileBasedDataStore = new FileBasedDataStore();

    @Test
    public void testGetRoundsShouldReturnRoundListWith1175SizeWhenTheFileHas1175Lines() {
        List<Round> actual = fileBasedDataStore.getRounds();
        assertEquals(1175,actual.size());
    }
}
