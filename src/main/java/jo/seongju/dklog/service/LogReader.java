package jo.seongju.dklog.service;

import jo.seongju.dklog.domain.Log;

/**
 * Created by Seongju Jo. On 2020-03-15 01:59:08
 */
public interface LogReader {

    Log read(String logLine);
}
