import org.junit.Test;

public class Test2 {
    @Test
    public void test2() {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTargetObject(new Dog());
        proxyFactory.setBeforeClass(new BeforeClass() {
            @Override
            public void before() {
                System.out.println("Preparing bark...");
            }
        });

        proxyFactory.setAfterClass(new AfterClass() {
            @Override
            public void after() {
                System.out.println("Ending bark...");
            }
        });

        Animal newDog = (Animal) proxyFactory.createProxy();
        newDog.bark();
    }
}