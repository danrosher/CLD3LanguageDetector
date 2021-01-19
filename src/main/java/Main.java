
import com.b8.LangDetect;
import com.b8.LangDetectResponse;

public class Main {

    public static void main(String[] args) {
        LangDetect ld = new LangDetect();
        String englishText = "This piece of text is in English";
        LangDetectResponse result = ld.detect(englishText);
        assert(result.getLanguage().equals("English"));
        assert(result.isReliable());
        assert(result.getProportion() == 1f);
        System.out.println("result:"+result);

    }
}
