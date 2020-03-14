package jo.seongju.dklog.service.impl;

import jo.seongju.dklog.domain.LogStatistic;
import jo.seongju.dklog.service.LogWriter;

import java.io.*;
import java.text.NumberFormat;
import java.util.Map;

/**
 * Created by Seongju Jo. On 2020-03-15 02:03:20
 */
public class DefaultLogWriter implements LogWriter {

    private static final String DEFAULT_OUTPUT_PATH;

    private static final NumberFormat NUMBER_FORMAT;

    //
    // DEFAULT_OUTPUT_PATH, NUMBER_FORMAT 초기화
    //
    static {

        DEFAULT_OUTPUT_PATH = System.getProperty("user.dir") + File.separator + "output.log";

        NUMBER_FORMAT = NumberFormat.getInstance();
        NUMBER_FORMAT.setMaximumFractionDigits(0);
    }

    private String outputPath;

    public DefaultLogWriter() {
        this(DEFAULT_OUTPUT_PATH);
    }

    public DefaultLogWriter(String outputPath) {
        this.outputPath = outputPath;
    }

    @Override
    public void write(LogStatistic logStatistic) throws IOException {

        Writer fileWriter = new FileWriter(outputPath);

        BufferedWriter bufferedWriter = null;

        try {
            bufferedWriter = new BufferedWriter(fileWriter);

            //
            // 최다호출 API KEY
            //
            bufferedWriter.write("최다호출 API KEY");
            for (Map.Entry<String, Integer> entry : logStatistic.getApiKeyRank().entrySet()) {
                bufferedWriter.newLine();
                bufferedWriter.write(entry.getKey());
                break;
            }
            bufferedWriter.newLine();
            bufferedWriter.newLine();

            //
            // 상위 3개의 API Service ID 와 각각의 요청 수
            //
            bufferedWriter.write("상위 3개의 API Service ID 와 각각의 요청 수");
            int i = 0;
            for (Map.Entry<String, Integer> entry : logStatistic.getServiceRank().entrySet()) {
                if (i >= 3) {
                    break;
                }
                bufferedWriter.newLine();
                bufferedWriter.write(entry.getKey() + " : " + entry.getValue());
                i++;
            }
            bufferedWriter.newLine();
            bufferedWriter.newLine();

            //
            // 웹브라우저별 사용 비율
            //
            bufferedWriter.write("웹브라우저별 사용 비율");
            for (Map.Entry<String, Integer> entry : logStatistic.getBrowserRank().entrySet()) {
                bufferedWriter.newLine();
                double percent = (double)entry.getValue() / (double)logStatistic.getLogTotal() * 100;
                String percentAsText = NUMBER_FORMAT.format(percent) + "%";
                bufferedWriter.write(entry.getKey() + " : " + percentAsText);
            }
            bufferedWriter.newLine();
            bufferedWriter.newLine();

        } finally {
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
        }
    }
}
