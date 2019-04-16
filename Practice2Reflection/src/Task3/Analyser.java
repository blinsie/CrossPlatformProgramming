package Task3;

import java.lang.reflect.Method;
import java.util.Scanner;

public class Analyser {

    public static void RunMethod(Object obj, String methodName, Class<?>... params) {

        Class clss = obj.getClass();
        Object[] p = new Object[]{};//{"Jackie", 39};

        try {
            Method declaredMethod = clss.getDeclaredMethod(methodName, params);
           // declaredMethod.setAccessible(true);
            System.out.println(declaredMethod.invoke(obj, p));
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public static void showMethods(Object o) {
        Class clss = o.getClass();
        int i;
        System.out.println("Открытые методы класса: \n");
        Method[] methods = clss.getDeclaredMethods();

        for (i = 0; i < methods.length; i++)
            System.out.println(i+1 + ") " + methods[i]);

    }

    public static void main(String[] args)  {
        Dog dog = new Dog("Funny", 7);
        Scanner in = new Scanner(System.in);

        showMethods(dog);
        System.out.print("\nВведите имя метода: ");
        String name = in.nextLine();
        Class[] parameters = {};//{String.class, int.class};
        RunMethod(dog, name, parameters);
    }


    public static class Dog {
        private String name;
        private int age;

        public Dog() {
            // empty constructor
        }

        public Dog(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        private void printDog(String name, int age) {
            System.out.println(name + " is " + age + " year(s) old.");
        }
    }

}
