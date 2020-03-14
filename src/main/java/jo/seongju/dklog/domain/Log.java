package jo.seongju.dklog.domain;

import java.time.LocalDateTime;

/**
 * Created by Seongju Jo. On 2020-03-15 00:16:35
 */
public class Log {

    private String statusCode;

    private String url;

    private String serviceId;

    private String browser;

    private String apiKey;

    private LocalDateTime callTime;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public LocalDateTime getCallTime() {
        return callTime;
    }

    public void setCallTime(LocalDateTime callTime) {
        this.callTime = callTime;
    }

    @Override
    public String toString() {
        return "Log{" +
                "statusCode='" + statusCode + '\'' +
                ", url='" + url + '\'' +
                ", serviceId='" + serviceId + '\'' +
                ", browser='" + browser + '\'' +
                ", apiKey='" + apiKey + '\'' +
                ", callTime=" + callTime +
                '}';
    }
}
