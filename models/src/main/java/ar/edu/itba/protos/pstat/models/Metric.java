package ar.edu.itba.protos.pstat.models;

import ar.edu.itba.protos.pstat.configuration.Chart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Metric {

    private static final Logger LOG = LoggerFactory.getLogger(Metric.class.getSimpleName());

    private static final int ZERO = 0;

    private final Integer newConnection;
    private final Integer disconnection;
    private final Integer uploadedBytes;
    private final Integer downloadedBytes;
    private final Integer parsedBytes;

    private Metric (Builder builder){
        this.newConnection = builder.newConnection;
        this.disconnection = builder.disconnection;
        this.uploadedBytes = builder.uploadedBytes;
        this.downloadedBytes = builder.downloadedBytes;
        this.parsedBytes = builder.parsedBytes;

        LOG.info("Creating Metric: builder => {}", builder);
    }

    public Integer getMetric(Chart chart){
        switch(chart){
            case NEW_CONNECTION:
                return getNewConnection();
            case DISCONNECTION:
                return getDisconnection();
            case UPLOADED_BYTES:
                return getUploadedBytes();
            case DOWNLOADED_BYTES:
                return getDownloadedBytes();
            case PARSED_BYTES:
                return getParsedBytes();
            case DOWNLOAD_SPEED:
                return getDownloadedBytes();
            case UPLOAD_SPEED:
                return getUploadedBytes();
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        return "Metric{" +
                "newConnection=" + newConnection +
                ", disconnection=" + disconnection +
                ", uploadedBytes=" + uploadedBytes +
                ", downloadedBytes=" + downloadedBytes +
                ", parsedBytes=" + parsedBytes +
                '}';
    }

    public Integer getNewConnection() {
        return newConnection;
    }

    public Integer getDisconnection() {
        return disconnection;
    }

    public Integer getUploadedBytes() {
        return uploadedBytes;
    }

    public Integer getDownloadedBytes() {
        return downloadedBytes;
    }

    public Integer getParsedBytes() {
        return parsedBytes;
    }

    public Metric getNullMetric(){

        return new Builder().newConnection(ZERO)
                            .disconnection(ZERO)
                            .uploadedBytes(ZERO)
                            .downloadedBytes(ZERO)
                            .parsedBytes(ZERO)
                            .rotatedImages(ZERO)
                            .build();
    }

    //Implemented taking into account new metrics could be implemented
    public static class Builder{

        private Integer newConnection;
        private Integer disconnection;
        private Integer uploadedBytes;
        private Integer downloadedBytes;
        private Integer parsedBytes;
        private Integer rotatedImages;

        public Builder(Integer newConnection, Integer disconnection, Integer uploadedBytes,
                       Integer downloadedBytes, Integer parsedBytes, Integer rotatedImages) {
            this.newConnection = newConnection;
            this.disconnection = disconnection;
            this.uploadedBytes = uploadedBytes;
            this.downloadedBytes = downloadedBytes;
            this.parsedBytes = parsedBytes;
            this.rotatedImages = rotatedImages;
        }

        public Builder(){

        }

        @Override
        public String toString() {
            return "Builder{" +
                    "newConnection=" + newConnection +
                    ", disconnection=" + disconnection +
                    ", uploadedBytes=" + uploadedBytes +
                    ", downloadedBytes=" + downloadedBytes +
                    ", rotatedImages=" + rotatedImages +
                    ", parsedBytes=" + parsedBytes +
                    '}';
        }

        public Builder newConnection(Integer newConnection) {
            this.newConnection = newConnection;
            return this;
        }

        public Builder disconnection(Integer disconnection) {
            this.disconnection = disconnection;
            return this;
        }

        public Builder uploadedBytes(Integer uploadedBytes) {
            this.uploadedBytes = uploadedBytes;
            return this;
        }

        public Builder downloadedBytes(Integer downloadedBytes) {
            this.downloadedBytes = downloadedBytes;
            return this;
        }

        public Builder parsedBytes(Integer parsedBytes) {
            this.parsedBytes = parsedBytes;
            return this;
        }

        public Builder rotatedImages(Integer rotatedImages) {
            this.rotatedImages = rotatedImages;
            return this;
        }

        public Metric build(){
            return new Metric(this);
        }
    }
}
