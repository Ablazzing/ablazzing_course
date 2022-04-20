package org.example;

public class Lombok_8_SneakyThrows {

    public void withoutSneakyThrows() throws Exception {
        throw new Exception("oops");
    }

    //@SneakyThrows - переводит все Checked exceptions в RuntimeException.
    public void withSneakyThrows() {
        try {
            throw new Exception("oops");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
