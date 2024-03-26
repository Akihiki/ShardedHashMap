package org.example.dto;

public class HashMapElementDTO {
    String key;
    String value;

    public HashMapElementDTO() {
    }

    public HashMapElementDTO(String key, String value) {
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
        return "RequestType  {" +
                "key='" + key + '\'' +
                ", value=" + value +
                '}';
    }
}
