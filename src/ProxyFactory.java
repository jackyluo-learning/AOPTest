import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactory {
    private Object targetObject;
    private BeforeClass beforeClass;
    private AfterClass afterClass;

    public Object createProxy() {
        //类生成需要的类加载器
        ClassLoader classLoader = this.getClass().getClassLoader();

        //类实现的接口数组
        Class[] interfaces = targetObject.getClass().getInterfaces();

        //调用处理器
        InvocationHandler invocationHandler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                beforeClass.before();
                System.out.println(targetObject.getClass().getInterfaces()[0]);
                Object result =  method.invoke(targetObject, args); //调用targetObject自己的方法
                afterClass.after();
                return result;
            }
        };

        return Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);
    }

    public Object getTargetObject() {
        return targetObject;
    }

    public void setTargetObject(Object targetObject) {
        this.targetObject = targetObject;
    }

    public BeforeClass getBeforeClass() {
        return beforeClass;
    }

    public void setBeforeClass(BeforeClass beforeClass) {
        this.beforeClass = beforeClass;
    }

    public AfterClass getAfterClass() {
        return afterClass;
    }

    public void setAfterClass(AfterClass afterClass) {
        this.afterClass = afterClass;
    }
}