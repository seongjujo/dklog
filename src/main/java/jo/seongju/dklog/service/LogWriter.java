package jo.seongju.dklog.service;

import jo.seongju.dklog.domain.LogStatistic;

import java.io.IOException;

/**
 * Created by Seongju Jo. On 2020-03-15 02:01:40
 */
public interface LogWriter {

    void write(LogStatistic logStatistic) throws IOException;
}
