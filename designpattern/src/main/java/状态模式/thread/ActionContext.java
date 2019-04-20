package 状态模式.thread;

/**
 * @program: test
 * @description:
 * @author: Mr.WG
 * @create: 2019-04-20 19:15
 **/
public final class ActionContext {

    private final ThreadLocal<Context> threadLocal = new ThreadLocal<Context>() {
        @Override
        protected Context initialValue() {
            return new Context();
        }
    };

    private ActionContext() {
    }

    private static final class ContextHolder {
        private static final ActionContext context = new ActionContext();
    }

    public static ActionContext getInstance() {
        return ContextHolder.context;
    }

    public Object getStatus() {
        return threadLocal.get().getStatus();
    }

    public void setStatus(Object sth) {

        threadLocal.get().setStatus(sth);
    }
}
