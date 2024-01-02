
public class NO1 {

	public static void main(String[] args) {

		String x = "string";
		String y = "string";
		String z = new String("string");

		System.out.println(x == y);
		System.out.println(x == z);
		System.out.println(x.equals(z));
		System.out.println(x.equals(y));

		NO1 ee = new NO1();
		ee.method1();
		ee.method1();
		ee.method2();
		ee.method2();
		ee.method1();
		ee.method2();

		NO1 ee2 = new NO1();
		ee2.method1();
		ee2.method1();
		ee2.method2();
		ee2.method2();
		ee2.method1();
		ee2.method2();

	}

	public void method1() {
		synchronized (String.class) {
			System.out.println("aquired lock on String object1");
			synchronized (Integer.class) {
				System.out.println("aquired lock on Integer object1");
			}
		}

	}

	public void method2() {
		synchronized (Integer.class) {
			System.out.println("aquired lock on Integer object2");
			synchronized (String.class) {
				System.out.println("aquired lock on String object2");
			}
		}

	}

}
