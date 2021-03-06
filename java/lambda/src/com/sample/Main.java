package com.sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        //************
        // Suppliers :: Accepts void -> returns Object
        //************
        Supplier<String> singleLineSupplier = () -> "Hello! I'm a single line supplier";
        System.out.println(singleLineSupplier.get());

        Supplier<String> multiLineSupplier = () -> {
            String s = "Hello! ";
            s += "I'm a multi line supplier";
            return s;
        };
        System.out.println(multiLineSupplier.get());

        //************
        // Consumers :: Takes Object -> return void
        //************
        Consumer<String> simpleStringConsumer = (String s) -> System.out.println(s);
        simpleStringConsumer.accept("Hi! I'm a consumer");

        //************
        // Predicates :: Takes Object -> return boolean
        //************
        List<String> strings = new ArrayList<>(List.of("one", "two", "three", "four", "five"));

        List<String> asParams = strings.stream().collect(Collectors.toList());
        Predicate<String> filter = (String s) -> !s.startsWith("t");
        asParams.removeIf(filter);
        asParams.forEach(simpleStringConsumer);

        // Inline version:
        List<String> inLine = strings.stream().collect(Collectors.toList());
        inLine.removeIf(s -> !s.startsWith("t"));
        inLine.forEach(s -> System.out.println(s));

        //************
        // Functions :: Takes Object T -> returns Object R
        //************
        User sarah = new User("Sarah", 28);
        User james = new User("James", 35);
        User mary = new User("Mary", 33);
        User john24 = new User("John", 24);
        User john25 = new User("John", 25);

        List<User> users = Arrays.asList(sarah, james, mary, john24, john25);
        List<String> names = new ArrayList<>();
        Function<User, String> toName = (User u) -> u.getName();
        for (User user : users) {
           String name = toName.apply(user);
           names.add(name);
        }
        users.forEach(bla -> System.out.println(bla)); // Here bla is an User
        names.forEach(bla -> System.out.println(bla)); // Same code but here bla is a String

        //************
        // Specialized Suppliers and Functions > To improve performance
        //************
        IntSupplier intSupplier = () -> 10;
        System.out.println("i = " + intSupplier.getAsInt());

        DoubleToIntFunction doubleToIntFunction = (double d) -> (int) Math.floor(d);
        System.out.println("Pi = " + doubleToIntFunction.applyAsInt(Math.PI));

        //************
        // Chaining
        //************
        // Consumers
        Consumer<String> c1 = s -> System.out.println("c1 consumes "+s);
        Consumer<String> c2 = s -> System.out.println("c2 consumes "+s);

        Consumer<String> consumerChain = c1.andThen(c2);
        consumerChain.accept("Hello");

        // Predicates
        Predicate<String> isNull = s -> s == null;
        Predicate<String> isEmpty = s -> s.isEmpty();
        Predicate<String> predicateChain = isNull.negate().and(isEmpty.negate());

        System.out.println("For null = " + predicateChain.test(null));
        System.out.println("For empty = " + predicateChain.test(""));
        System.out.println("For Hello = " + predicateChain.test("Hello"));

        //************
        // Comparators
        //************
        List<String> stringsToCompare = Arrays.asList("one", "two", "three", "four", "five", "six", "seven", "eight", "nine");

        List<String> alphabeticalSort = new ArrayList<>(stringsToCompare);
        Comparator<String> alphabeticalOrder = (String s1, String s2) -> s1.compareTo(s2);
        alphabeticalSort.sort(alphabeticalOrder);
        System.out.println(alphabeticalSort);

        List<String> lengthSort = new ArrayList<>(stringsToCompare);
        Comparator<String> lengthOrder = (s1, s2) -> Integer.compare(s1.length(), s2.length());
        lengthSort.sort(lengthOrder);
        System.out.println(lengthSort);

        // Function passing
        List<String> lengthSortUsingFunction = new ArrayList<>(stringsToCompare);
        Function<String, Integer> toLength = s -> s.length();
        Comparator<String> withFunction = Comparator.comparing(toLength);
        lengthSortUsingFunction.sort(withFunction);
        System.out.println(lengthSortUsingFunction);

        // Improving performance avoiding unboxing
        List<String> lengthSortWithoutUnboxing = new ArrayList<>(stringsToCompare);
        ToIntFunction<String> toLengthWithoutUnboxing = s -> s.length();
        Comparator<String> withoutUnboxing = Comparator.comparingInt(toLengthWithoutUnboxing);
        lengthSortWithoutUnboxing.sort(withoutUnboxing);
        System.out.println(lengthSortWithoutUnboxing);

        // Chaining comparators
        Comparator<User> cmpName = Comparator.comparing(user -> user.getName());
        Comparator<User> cmpAge = Comparator.comparing(user -> user.getAge());
        users.sort(cmpAge.thenComparing(cmpName).reversed()); // First by age, then by name and finally reverse order.
        users.forEach(u -> System.out.println(u));
    }
}

class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User: "+name+" Age: "+age;
    }
}
