package jo.seongju.dklog.parser;

import jo.seongju.dklog.domain.Log;

/**
 * Created by Seongju Jo. On 2020-03-15 01:49:28
 */
public interface LogParser {

    Log parse(String logLine);
}
