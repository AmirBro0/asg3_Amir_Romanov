package utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionUtils {

    public static void inspect(Object obj) {

        if (obj == null) {
            System.out.println("Object is null");
            return;
        }

        Class<?> cls = obj.getClass();

        System.out.println("=== REFLECTION INFO ===");
        System.out.println("Class name: " + cls.getName());
        System.out.println("Superclass: " + cls.getSuperclass().getName());

        System.out.println("\nFields:");
        Field[] fields = cls.getDeclaredFields();
        if (fields.length == 0) {
            System.out.println(" (no fields)");
        }
        for (Field f : fields) {
            System.out.println(" - " + f.getType().getSimpleName()
                    + " " + f.getName());
        }

        System.out.println("\nMethods:");
        Method[] methods = cls.getDeclaredMethods();
        if (methods.length == 0) {
            System.out.println(" (no methods)");
        }
        for (Method m : methods) {
            System.out.println(" - " + m.getReturnType().getSimpleName()
                    + " " + m.getName() + "()");
        }

        System.out.println("========================");
    }
}
