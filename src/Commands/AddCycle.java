package Commands;

import Data.Cycle;

import InputHandlers.*;;

public class AddCycle {

    public static void execute(int topId) {

        int intArgCount = 3;
        int booleanArgCount = 1;
        int defaultIntValues[] = { 0, 0, 0 };
        boolean defaultBooleanValues[] = { false };

        try {
            int valueArgs[] = IntValueInput.parser(intArgCount, defaultIntValues);
            boolean booleanArgs[] = BoolValueInput.parser(booleanArgCount, defaultBooleanValues);
            Cycle cycle = new Cycle(valueArgs, booleanArgs, topId);
            System.out.println(cycle.toString());

        } catch (InvalidInputException e) {
            System.out.println("Cycle not added");
            System.out.println(
                    "Expected args x-value: +ve int (0), y-value: +ve int(0), hoursRented: +ve double (0), isRented: boolean (false)");
            return;
        }

        System.out.println("Cycle Added");
    }
}
