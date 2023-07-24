package org.apache.ofbiz.base.util.function;

public class Throwings {
    private Throwings() {}
    public static <T> ThrowingConsumer<T> consumer(
            ThrowingConsumer<T> c) {return c;}
    public static <T, R> ThrowingFunction<T, R> function(
            ThrowingFunction<T, R> f) {return f;}
    public static <T> ThrowingPredicate<T> predicate(
            ThrowingPredicate<T> p) {return p;}
    public static <T> ThrowingSupplier<T> supplier(
            ThrowingSupplier<T> s) {return s;}
}