package org.novak.java.view;

import java.util.Scanner;

abstract class View {

    protected final Scanner scanner = new Scanner(System.in);

    public abstract void start();

    protected abstract void printMenu();

    protected abstract void handleInput(String input);
}