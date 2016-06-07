package ar.edu.itba.protos.pstat.models;

public class Metric {

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

    //Implemented taking into account new metrics could be implemented
    public class Builder{

        private Integer newConnection;
        private Integer disconnection;
        private Integer uploadedBytes;
        private Integer downloadedBytes;
        private Integer parsedBytes;

        public Builder(Integer newConnection, Integer disconnection, Integer uploadedBytes,
                       Integer downloadedBytes, Integer parsedBytes) {
            this.newConnection = newConnection;
            this.disconnection = disconnection;
            this.uploadedBytes = uploadedBytes;
            this.downloadedBytes = downloadedBytes;
            this.parsedBytes = parsedBytes;
        }

        public Metric build(){
            return new Metric(this);
        }
    }
}
