package jo.seongju.dklog.domain;

import java.util.*;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

/**
 * Created by Seongju Jo. On 2020-03-15 00:11:39
 */
public class LogStatistic {

    private int logTotal;

    private Map<String, Integer> apiKeyRank = new HashMap<>();

    private Map<String, Integer> serviceRank = new LinkedHashMap<>();

    private Map<String, Integer> browserRank = new LinkedHashMap<>();

    private LogStatistic() {

    }

    public int getLogTotal() {
        return logTotal;
    }

    public Map<String, Integer> getApiKeyRank() {
        return apiKeyRank;
    }

    public Map<String, Integer> getServiceRank() {
        return serviceRank;
    }

    public Map<String, Integer> getBrowserRank() {
        return browserRank;
    }

    public static class Builder {

        private Map<String, Integer> apiKeyCountMap = new HashMap<>();

        private Map<String, Integer> serviceCountMap = new HashMap<>();

        private Map<String, Integer> browserCountMap = new HashMap<>();

        private List<Log> logs = new ArrayList<>();

        private Builder() {
        }

        public static Builder create() {

            return new Builder();
        }

        public Builder add(Log log) {

            logs.add(log);

            String apiKey = log.getApiKey();
            String serviceId = log.getServiceId();
            String browser = log.getBrowser();

            addApiKey(apiKey);
            addService(serviceId);
            addBrowser(browser);

            return this;
        }

        public LogStatistic build() {

            Map<String, Integer> apiKeyCountMapSorted = sortByValueDesc(apiKeyCountMap);
            Map<String, Integer> serviceCountMapSorted = sortByValueDesc(serviceCountMap);
            Map<String, Integer> browserCountMapSorted = sortByValueDesc(browserCountMap);


            LogStatistic logStatistic = new LogStatistic();
            logStatistic.logTotal = logs.size();
            logStatistic.apiKeyRank = apiKeyCountMapSorted;
            logStatistic.serviceRank = serviceCountMapSorted;
            logStatistic.browserRank = browserCountMapSorted;

            return logStatistic;
        }

        private  Map<String, Integer> sortByValueDesc(Map<String, Integer> map) {

            return map.entrySet()
                    .stream()
                    .sorted(Collections.reverseOrder(comparingByValue()))
                    .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
        }

        private void addApiKey(String apiKey) {

            int count = 0;

            if (apiKeyCountMap.containsKey(apiKey)) {
                count = apiKeyCountMap.get(apiKey);
            }

            apiKeyCountMap.put(apiKey, ++count);
        }

        private void addService(String serviceId) {

            int count = 0;

            if (serviceCountMap.containsKey(serviceId)) {
                count = serviceCountMap.get(serviceId);
            }

            serviceCountMap.put(serviceId, ++count);
        }

        private void addBrowser(String browser) {

            int count = 0;

            if (browserCountMap.containsKey(browser)) {
                count = browserCountMap.get(browser);
            }

            browserCountMap.put(browser, ++count);
        }
    }
}
