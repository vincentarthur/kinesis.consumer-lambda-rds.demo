package kinesis.lambda.rds.demo.entity;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.io.IOException;

/**
 * Created by vwmc on 9/16/17.
 */
@Data
public class SampleRecord {

    private final static ObjectMapper JSON = new ObjectMapper();
    private String id;
    private String content;

    static {
        JSON.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public byte[] toJsonAsBytes() {
        try {
            return JSON.writeValueAsBytes(this);
        } catch (IOException e) {
            return null;
        }
    }

    public static SampleRecord fromJsonAsBytes(byte[] bytes) {
        try {
            return JSON.readValue(bytes, SampleRecord.class);
        } catch (IOException e) {
            return null;
        }
    }


}
