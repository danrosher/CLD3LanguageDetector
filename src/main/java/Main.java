
import com.b8.LangDetect;
import com.b8.LangDetectResponse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    private static final Map<String, String> lang;
    static {
        lang = new HashMap<>();
        lang.put("0", "en");
        lang.put("1", "fr");
        lang.put("2", "zh");
        lang.put("3", "ru");
        lang.put("4", "pl");
        lang.put("5", "pt");
        lang.put("6", "ja");
        lang.put("7", "it");
        lang.put("8", "he");
        lang.put("9", "nl");
    }

    public static void main(String[] args) throws IOException {

        LangDetect ld = new LangDetect();
        String fileName = "src/main/lib/unix/Ling10-trainlarge/train_set.txt";
        Files.lines(Paths.get(fileName))
            .map(s -> s.split("\t"))
            .map(str -> new String[]{str[0],lang.get(str[1])})
            .forEach(str -> {
                LangDetectResponse result = ld.detect(str[0]);
                assert(result.getLanguage().equals(str[1]));
            });

    }
}
