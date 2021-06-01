package chesspuzzle.results;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.time.LocalDateTime;
import java.time.Year;

public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {

    public LocalDateTime unmarshal(String s) throws Exception {
        return LocalDateTime.parse(s);
    }

    public String marshal(LocalDateTime ldt) throws Exception {
        return ldt.toString();
    }

}
