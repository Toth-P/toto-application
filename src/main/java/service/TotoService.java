package service;

import data.DataStore;
import data.FileBasedDataStore;
import domain.*;

import java.time.LocalDate;
import java.util.List;

public class TotoService implements DataStore {
    public TotoService(FileBasedDataStore fileBasedDataStore) {
        this.fileBasedDataStore = fileBasedDataStore;
    }

    private DataStore dataStore;
    ResultService resultService = new ResultService();
    PriceService priceService = new PriceService();
    StatisticService statisticService = new StatisticService();
    InputService inputService = new InputService();
    FileBasedDataStore fileBasedDataStore;
    @Override
    public List<Round> getRounds() {

        return fileBasedDataStore.getRounds();
    }

    public int getLargestPrize() {
        return priceService.getLargestPrize(getRounds());
    }

    public Statistics calculateStatistics() {
        return statisticService.calculateStatistics(getRounds());
    }

    public List<Outcome> getOutcomeListFromInput(String inputOutcomes) {
        return inputService.getOutcomeListFromInput(inputOutcomes);
    }

    public Result getResult(LocalDate localDate, List<Outcome> outcomeList) {
        return resultService.getResult(getRounds(), localDate, outcomeList);
    }
}
