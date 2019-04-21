import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @program: test 不可修改的，线程安全的
 * @description:
 * @author: Mr.WG
 * @create: 2019-04-20 17:49
 **/
final public class ImmutableSth {
    private final Integer sth;
    private final List<String> datas = Arrays.asList("a", "b");

    public ImmutableSth(Integer sth) {
        this.sth = sth;
    }

    public Integer getSth() {
        return sth;
    }

    public List<String> getDatas() {
        return Collections.unmodifiableList(datas);
    }
}
