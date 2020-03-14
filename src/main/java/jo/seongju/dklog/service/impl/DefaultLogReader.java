package jo.seongju.dklog.service.impl;

import jo.seongju.dklog.domain.Log;
import jo.seongju.dklog.parser.LogParser;
import jo.seongju.dklog.service.LogReader;

/**
 * Created by Seongju Jo. On 2020-03-15 02:00:17
 */
public class DefaultLogReader implements LogReader {

    private LogParser parser;

    public DefaultLogReader(LogParser parser) {
        this.parser = parser;
    }

    @Override
    public Log read(String logLine) {

        return parser.parse(logLine);
    }
}
