package jo.seongju.dklog.service;

import jo.seongju.dklog.domain.LogStatistic;
import jo.seongju.dklog.exception.LogException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Seongju Jo. On 2020-03-15 00:06:24
 */
public interface LogService extends LogReader, LogWriter {

    LogStatistic aggregate(String filePath) throws IOException, LogException;

    LogStatistic aggregate(InputStream inputStream) throws IOException, LogException;

    LogStatistic aggregate(BufferedReader bufferedReader) throws IOException, LogException;
}
