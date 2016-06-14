package ar.edu.itba.protos.pstat.configuration;

public enum Chart {

    DOWNLOADED_BYTES("DOWNLOADED_BYTES"),
    UPLOADED_BYTES("UPLOADED_BYTES"),
    DOWNLOAD_SPEED("DOWNLOAD_SPEED"),
    UPLOAD_SPEED("UPLOAD_SPEED"),
    PARSED_BYTES("PARSED_BYTES"),
    NEW_CONNECTION("NEW_CONNECTION"),
    DISCONNECTION("DISCONNECTION"),
    NONE("NONE");

    final String str;

    Chart(String str){
        this.str = str;
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
}
