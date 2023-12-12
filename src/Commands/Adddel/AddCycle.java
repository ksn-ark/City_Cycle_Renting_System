package Commands.Adddel;

import Data.Cycle;
import ValueInputHandlers.*;

public class AddCycle {

    public static void execute(int id, String filePath) {

        int intArgCount = 3;
        String intArgNames[] = { "x-value: ", "y-value: ", "hoursRented: " };

        int booleanArgCount = 1;
        String booleanArgNames[] = { "isRented:  " };

        int defaultIntValues[] = { 0, 0, 0 };
        boolean defaultBooleanValues[] = { false };

        RangeCheck intRanges[] = { new RangeCheck(0), new RangeCheck(0), new RangeCheck(0), new RangeCheck(0) };

        try {
            int intArgs[] = IntValueInput.parser(intArgCount, intArgNames, defaultIntValues, intRanges);
            boolean booleanArgs[] = BoolValueInput.parser(booleanArgCount, booleanArgNames, defaultBooleanValues);

            int x = intArgs[0];
            int y = intArgs[1];
            int hoursRented = intArgs[2];
            boolean isRented = booleanArgs[0];

            Cycle cycle = new Cycle(id, x, y, hoursRented, isRented);

            System.out.println("\n" + cycle.toString());

            cycle.add(filePath);
            cycle.addTopId(filePath, id);

        } catch (InvalidInputException e) {
            System.out.println("\nCycle not added\n");
            System.out.println(
                    "Expected args x-value: +ve int (0), y-value: +ve int(0), hoursRented: +ve double (0), isRented: boolean (false)");
            return;
        } catch (Exception e) {
            System.out.println("Error invalid csv file");
            return;
        }

        System.out.println("\nCycle Added");
    }
}
