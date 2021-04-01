package string_test.string_test;

/**
 * @autor ws
 * @description
 * @date 2020/8/7 20:04
 **/
public class TestEqualEgore {

    public static void main(String[] args) {
        System.out.println("TEST123".equalsIgnoreCase("test123"));
        System.out.println("TEST  ".trim().equalsIgnoreCase("test ".trim()));
        System.out.println("TEST".trim().equalsIgnoreCase("test ".trim()));
        System.out.println("".equalsIgnoreCase(""));
        System.out.println("".equalsIgnoreCase(null));
    }
}
