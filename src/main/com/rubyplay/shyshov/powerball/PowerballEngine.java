/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.rubyplay.shyshov.powerball;

import java.util.*;

public class PowerballEngine {
    private ArrayList<int[]> tickets;
    private int ticketNumber;
    private ArrayList<Integer> numbers;
    private Map<String, ArrayList<Integer>> winners;
    private int powerball;
    private boolean finished;

    public boolean startGame() {
        tickets = new ArrayList<>();
        numbers = new ArrayList<>();
        winners = new HashMap<>();
        winners.put("PB", new ArrayList<>());
        winners.put("1+PB", new ArrayList<>());
        winners.put("2+PB", new ArrayList<>());
        winners.put("3", new ArrayList<>());
        winners.put("3+PB", new ArrayList<>());
        winners.put("4", new ArrayList<>());
        winners.put("4+PB", new ArrayList<>());
        winners.put("5", new ArrayList<>());
        winners.put("5+PB", new ArrayList<>());
        ticketNumber = 0;
        finished = false;
        return true;
    };

    /**
     * @param numbers 5 regular balls + powerball
     * @return ticket id
     */
    public int registerTicket(int[] numbers) {
        this.tickets.add(numbers);
        return ticketNumber++;
    };

    public ArrayList<Integer> draw() {
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            boolean generated = false;
            while (!generated) {
                int j = random.nextInt(69) + 1;
                if (!numbers.contains(j)) {
                    numbers.add(j);
                    generated = true;
                }
            }
        }
        numbers.add(random.nextInt(26) + 1);
        collectWins();
        finished = true;
        return numbers;
    }

    private void collectWins() {
        for (int i = 0; i < tickets.size(); i++) {
            int[] player = tickets.get(i);
            int k = 0;
            boolean power = false;
            for (int j = 0; j < 5; j++) {
                if (numbers.contains(player[j])) {
                    k++;
                }
            }
            if (player[5] == numbers.get(5)) {
                power = true;
            }
            if (k == 0 && power) {
                winners.get("PB").add(i);
            } else if (k == 1 && power) {
                winners.get("1+PB").add(i);
            } else if (k == 2 && power) {
                winners.get("2+PB").add(i);
            } else if (k == 3 && !power) {
                winners.get("3").add(i);
            } else if (k == 3 && power) {
                winners.get("3+PB").add(i);
            } else if (k == 4 && !power) {
                winners.get("4").add(i);
            } else if (k ==4 && power) {
                winners.get("4+PB").add(i);
            } else if (k == 5 && !power) {
                winners.get("5").add(i);
            } else if (k ==5 && power) {
                winners.get("5+PB").add(i);
            }
        }
    }

    public Map<String, ArrayList<Integer>> getWinners() {
        return winners;
    }
}
