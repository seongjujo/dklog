package jo.seongju.dklog.parser.impl;

import jo.seongju.dklog.domain.Log;
import jo.seongju.dklog.parser.LogParser;

import java.nio.CharBuffer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Seongju Jo. On 2020-03-15 01:50:34
 */
public class DefaultLogParser implements LogParser {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static final String QUERY_PARAM_APIKEY = "apikey";

    @Override
    public Log parse(String logLine) {

        Log log = new Log();

        final CharBuffer charBuffer = CharBuffer.wrap(logLine).asReadOnlyBuffer();

        int start = 0;
        int part = 0;

        for (int i = 0; i < charBuffer.length(); i++) {

            final char c = charBuffer.charAt(i);

            if (c == ']') {

                final CharBuffer cb = charBuffer.subSequence(start + 1, i);
                String value = cb.toString();

                switch (part) {
                    case 0:
                        log.setStatusCode(value);
                        break;
                    case 1:
                        log.setUrl(value);
                        parseUrl(cb, log);
                        break;
                    case 2:
                        log.setBrowser(value);
                        break;
                    case 3:
                        LocalDateTime callTime = parseDateTime(value);
                        log.setCallTime(callTime);
                        break;
                }

                start = i + 1;
                part++;
            }
        }

        return log;
    }

    private void parseUrl(CharBuffer urlBuffer, Log log) {

        int lastPathStart = 0;
        String serviceId = null;
        CharBuffer queryBuffer = null;

        for (int i = 0; i < urlBuffer.length(); i++) {

            final char c = urlBuffer.charAt(i);

            if (c == '/') {
                lastPathStart = i + 1;
            }
            else if (c == '?') {
                serviceId = urlBuffer.subSequence(lastPathStart, i).toString();
                queryBuffer = urlBuffer.subSequence(i + 1, urlBuffer.length());
                break;
            }
            else if (i + 1 == urlBuffer.length()) {
                serviceId = urlBuffer.subSequence(lastPathStart, urlBuffer.length()).toString();
            }
        }

        log.setServiceId(serviceId);

        if (queryBuffer == null) {
            return;
        }

        parseUrlQuery(queryBuffer, log);
    }

    private void parseUrlQuery(CharBuffer queryBuffer, Log log) {

        int lastParamStart = 0;
        int lastValueStart = 0;
        String lastParam = null;
        String lastValue = null;

        Map<String, String> params = new HashMap<>();

        for (int i = 0; i < queryBuffer.length(); i++) {

            final char c = queryBuffer.charAt(i);

            if (c == '=') {

                lastParam = queryBuffer.subSequence(lastParamStart, i).toString();
                lastValueStart = i + 1;
            }
            else if (c == '&') {
                lastValue = queryBuffer.subSequence(lastValueStart, i).toString();
                params.put(lastParam, lastValue);
                lastParamStart = i + 1;
            }
            else if (i + 1 == queryBuffer.length()) {
                lastValue = queryBuffer.subSequence(lastValueStart, queryBuffer.length()).toString();
                params.put(lastParam, lastValue);
            }
        }

        String apiKey = params.get(QUERY_PARAM_APIKEY);
        log.setApiKey(apiKey);
    }

    private LocalDateTime parseDateTime(String value) {

        return LocalDateTime.parse(value, DATE_TIME_FORMATTER);
    }
}
