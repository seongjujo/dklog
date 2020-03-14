package jo.seongju.dklog.service.impl;

import jo.seongju.dklog.domain.Log;
import jo.seongju.dklog.domain.LogStatistic;
import jo.seongju.dklog.exception.LogException;
import jo.seongju.dklog.exception.ReadLogException;
import jo.seongju.dklog.exception.WriteLogException;
import jo.seongju.dklog.service.LogReader;
import jo.seongju.dklog.service.LogService;
import jo.seongju.dklog.service.LogWriter;

import java.io.*;

/**
 * Created by Seongju Jo. On 2020-03-15 00:13:43
 */
public class DefaultLogService implements LogService {

    private static final String STATUS_CODE_OK = "200";

    private LogReader reader;

    private LogWriter writer;

    public DefaultLogService(LogReader reader, LogWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    @Override
    public LogStatistic aggregate(String filePath) throws IOException, LogException {

        Reader reader = new FileReader(filePath);
        BufferedReader bufferedReader = new BufferedReader(reader);

        return aggregate(bufferedReader);
    }

    @Override
    public LogStatistic aggregate(InputStream inputStream) throws IOException, LogException {

        Reader reader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);

        return aggregate(bufferedReader);
    }

    @Override
    public LogStatistic aggregate(BufferedReader bufferedReader) throws IOException, LogException {

        LogStatistic.Builder builder = LogStatistic.Builder.create();

        try {

            String line = null;

            while ((line = bufferedReader.readLine()) != null) {

                Log log = null;
                try {
                    log = read(line);
                } catch (Exception e) {
                    throw new ReadLogException(e);
                }

                if (STATUS_CODE_OK.equals(log.getStatusCode())) {
                    builder.add(log);
                }
            }
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }

        LogStatistic logStatistic = builder.build();

        try {
            write(logStatistic);
        } catch (IOException e) {
            throw new WriteLogException(e);
        }

        return logStatistic;
    }

    @Override
    public Log read(String logLine) {

        return reader.read(logLine);
    }

    @Override
    public void write(LogStatistic logStatistic) throws IOException {

        writer.write(logStatistic);
    }
}
