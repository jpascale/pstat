package ar.edu.itba.protos.pstat.configuration;

public class Value {

    private final String value;

    public Value(String value) {
        this.value = value;
    }

    public int asInt() {
        return Integer.parseInt(this.value);
    }

    @Override
    public String toString() {
        return value;
    }
}
