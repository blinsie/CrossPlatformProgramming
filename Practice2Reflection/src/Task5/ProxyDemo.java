package Task5;
import java.lang.reflect.*;

public class ProxyDemo {
    interface If {
        double evalf(double x);
    }
    static class Original implements If {
        public double evalf(double x) {
            double res = Math.exp(-2.5*x)*Math.sin(x);
            System.out.println(res);
            return res;
        }
    }
    static class Handler implements InvocationHandler {
        private final If original;
        public Handler(If original) {
            this.original = original;
        }
        public Object invoke(Object proxy, Method method, Object[] args)
                throws IllegalAccessException, IllegalArgumentException,
                InvocationTargetException {
            Parameter[] parameters = method.getParameters();
            System.out.print(method.getName() + "(");
            for (Parameter parameter : parameters)
                System.out.print(parameter.getType().getName());
            System.out.print(") -----> ");
            System.out.print("RESULT: ");
            return method.invoke(original, args);
        }
    }
    public static void main(String[] args){
        Original original = new Original();
        Handler handler = new Handler(original);
        If f = (If) Proxy.newProxyInstance(If.class.getClassLoader(),
                new Class[] { If.class },
                handler);

        double start = System.currentTimeMillis();
        f.evalf(1.0);

        double finish = System.currentTimeMillis();
        System.out.println("TIME: ");
        System.out.println(finish - start);
    }
}