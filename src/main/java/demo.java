import java.math.BigDecimal;

public class demo {
    public static void main(String[] args) {
        System.out.println( new BigDecimal(1400.099).setScale(2,BigDecimal.ROUND_HALF_UP));
    }
}
