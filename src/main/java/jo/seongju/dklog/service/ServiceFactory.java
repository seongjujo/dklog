package jo.seongju.dklog.service;

import jo.seongju.dklog.parser.LogParser;
import jo.seongju.dklog.parser.impl.DefaultLogParser;
import jo.seongju.dklog.service.impl.DefaultLogReader;
import jo.seongju.dklog.service.impl.DefaultLogService;
import jo.seongju.dklog.service.impl.DefaultLogWriter;

/**
 * Created by Seongju Jo. On 2020-03-15 04:58:21
 */
public class ServiceFactory {

    private static LogService logService;

    public static LogService getLogService() {

        if (logService == null) {

            synchronized (ServiceFactory.class) {

                if (logService == null) {

                    LogParser parser = new DefaultLogParser();
                    LogReader reader = new DefaultLogReader(parser);
                    LogWriter writer = new DefaultLogWriter();

                    logService = new DefaultLogService(reader, writer);
                }
            }
        }

        return logService;
    }
}
