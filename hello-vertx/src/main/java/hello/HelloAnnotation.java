package hello;

@interface MyAnnotation {
    String name();
    String address();
}

@MyAnnotation(name = "Mark", address = "Dublin")
public class HelloAnnotation {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
