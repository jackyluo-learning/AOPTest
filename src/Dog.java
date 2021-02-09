public class Dog implements Animal{
    @Override
    public void bark() {
        System.out.println(this.getClass() + " is barking...");
    }
}