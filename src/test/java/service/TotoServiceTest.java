package service;

import data.FileBasedDataStore;
import domain.Round;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class TotoServiceTest {
    TotoService totoService;
    FileBasedDataStore mockFileBasedDataStore;

    @Before
    public void setUp() {
        mockFileBasedDataStore = Mockito.mock(FileBasedDataStore.class);
        totoService = new TotoService(mockFileBasedDataStore);
    }

    @Test
    public void testGetRoundsShouldReturnSameRoundList() {
        List<Round> rounds = getActualRounds();
        when(mockFileBasedDataStore.getRounds()).thenReturn(rounds);
        List<Round> actual = totoService.getRounds();
        assertEquals(2, actual.size());
    }

    public List<Round> getActualRounds() {
        List<Round> rounds = new ArrayList<>();
        Round round1 = Round.builder().build();
        Round round2 = Round.builder().build();

        rounds.add(round1);
        rounds.add(round2);
        return rounds;
    }
}