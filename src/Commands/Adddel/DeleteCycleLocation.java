package Commands.Adddel;

import ValueInputHandlers.*;
import Data.Cycle;
import Data.ReplaceData;

import java.util.ArrayList;
import java.util.List;

public class DeleteCycleLocation {

    public static void execute(List<Cycle> cycles, String filePath) {

        int intArgCount = 4;
        String intArgNames[] = { "x-value: ", "y-value: ", "arSide-value: ", "arSide2-value: " };
        int defaultIntValues[] = { 0, 0, 1, -1 };

        RangeCheck intRanges[] = { new RangeCheck(0), new RangeCheck(0), new RangeCheck(1), new RangeCheck(1) };

        int deletedCount = 0;

        try {
            int intArgs[] = IntValueInput.parser(intArgCount, intArgNames, defaultIntValues, intRanges);

            int x = intArgs[0];
            int y = intArgs[1];
            int arSide1 = intArgs[2];
            int arSide2 = intArgs[3];

            List<Cycle> modifiedCycles = new ArrayList<>();

            for (int i = 0; i < (cycles.size()); i++) {

                Cycle cycle = cycles.get(i);
                int cyclex = cycle.getIntValues()[1];
                int cycley = cycle.getIntValues()[2];

                if (x <= cyclex && cyclex <= x + arSide1 - 1 && y <= cycley && cycley <= y + arSide2 - 1) {
                    deletedCount++;
                    continue;
                }
                modifiedCycles.add(cycle);
            }

            ReplaceData.replace(modifiedCycles, filePath);

            System.out.println("\n" + deletedCount + " Matching Records Successfully deleted");

        } catch (InvalidInputException e) {
            System.out.println("\nCycle not deleted\n");
            System.out.println(
                    "Expected args x-value: +ve int (0), y-value: +ve int(0), hoursRented: +ve double (0), isRented: boolean (false)");
            return;
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

}
