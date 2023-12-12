package Commands.Adddel;

import Data.Cycle;
import ValueInputHandlers.*;;

public class AddCycle {

    public static void execute(int id, String filePath) {

        int intArgCount = 3;
        int booleanArgCount = 1;
        int defaultIntValues[] = { 0, 0, 0 };
        RangeCheck range[] = { new RangeCheck(0), new RangeCheck(0), new RangeCheck(0), new RangeCheck(0) };

        boolean defaultBooleanValues[] = { false };

        try {
            int valueArgs[] = IntValueInput.parser(intArgCount, defaultIntValues, range);
            boolean booleanArgs[] = BoolValueInput.parser(booleanArgCount, defaultBooleanValues);
            int x = valueArgs[0];
            int y = valueArgs[1];
            int hoursRented = valueArgs[2];
            boolean isRented = booleanArgs[0];
            Cycle cycle = new Cycle(id, x, y, hoursRented, isRented);
            System.out.println(cycle.toString());
            cycle.add(filePath);

        } catch (InvalidInputException e) {
            System.out.println("Cycle not added");
            System.out.println(
                    "Expected args x-value: +ve int (0), y-value: +ve int(0), hoursRented: +ve double (0), isRented: boolean (false)");
            return;
        }

        System.out.println("Cycle Added");
    }
}
