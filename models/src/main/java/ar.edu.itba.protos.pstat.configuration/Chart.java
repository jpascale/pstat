package ar.edu.itba.protos.pstat.configuration;

public enum Chart {

    DOWNLOADED_BYTES("DOWNLOADED_BYTES", "Downloaded Bytes", "KiB"),
    UPLOADED_BYTES("UPLOADED_BYTES", "Uploaded Bytes", "KiB/s"),
    DOWNLOAD_SPEED("DOWNLOAD_SPEED", "Download Speed", "KiB/s"),
    UPLOAD_SPEED("UPLOAD_SPEED", "Upload Speed", "KiB"),
    PARSED_BYTES("PARSED_BYTES", "Parsed Bytes", "KiB"),
    NEW_CONNECTION("NEW_CONNECTION", "New connection", "KiB"),
    DISCONNECTION("DISCONNECTION", "Disconnection", "KiB"),
    NONE("NONE", "Error", "Error");

    final String str;
    final String name;
    final String y;

    Chart(String str, String name, String y){
        this.str = str;
        this.name = name;
        this.y = y;
    }

    public static Chart getChart(String string){
        switch(string){
            case "DOWNLOADED_BYTES":
                return DOWNLOADED_BYTES;
            case "UPLOADED_BYTES":
                return UPLOADED_BYTES;
            case "DOWNLOAD_SPEED":
                return DOWNLOAD_SPEED;
            case "UPLOAD_SPEED":
                return UPLOAD_SPEED;
            case "PARSED_BYTES":
                return PARSED_BYTES;
            case "NEW_CONNECTION":
                return NEW_CONNECTION;
            case "DISCONNECTION":
                return DISCONNECTION;
            default:
                return NONE;
        }
    }

    public String getName() {
        return name;
    }

    public String getY() {
        return y;
    }
}
