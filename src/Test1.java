import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;


public class Test1 {
    @Test
    public void test(){
        ClassLoader classLoader = this.getClass().getClassLoader();
        //这里创建一个空实现的调用处理器。
        InvocationHandler invocationHandler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println(method.getName() + ": I am a method.");
                return "Finished.";
            }
        };
        Object obj = Proxy.newProxyInstance(classLoader, new Class[]{TestA.class, TestB.class}, invocationHandler);
        //强转为A和B接口类型，说明生成的代理对象实现了A和B接口
        TestA a = (TestA) obj;
        TestB b = (TestB) obj;

        a.a();
        b.b();
        System.out.println("Test toString(): " + a.toString());
        System.out.println("Test toString(): " + b.toString());
    }
}