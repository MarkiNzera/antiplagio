public class RabinKarpChecker {
    private Document text;
    private int patternSize;
    private int base = 26;
    private int window_start = 0;
    private int window_end = 0;
    private int mod = 5807;

    public RabinKarpChecker(Document text, int patternSize) {
        this.text = text;
        this.patternSize = patternSize;
    }


}
