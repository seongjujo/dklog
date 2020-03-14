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

    private static LogService LOG_SERVICE;

    public static LogService getLogService() {

        if (LOG_SERVICE == null) {

            synchronized (ServiceFactory.class) {

                if (LOG_SERVICE == null) {
                    LogParser parser = new DefaultLogParser();
                    LogReader reader = new DefaultLogReader(parser);
                    LogWriter writer = new DefaultLogWriter();
                    LOG_SERVICE = new DefaultLogService(reader, writer);
                }
            }
        }

        return LOG_SERVICE;
    }
}
