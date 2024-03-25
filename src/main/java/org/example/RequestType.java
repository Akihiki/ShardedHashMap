package org.example;

public class RequestType {
    String key;
    String value;

    public RequestType() {
    }

    public RequestType(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "RequestType reda {" +
                "key='" + key + '\'' +
                ", value=" + value +
                '}';
    }
}
