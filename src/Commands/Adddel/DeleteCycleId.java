package Commands.Adddel;

import java.util.ArrayList;
import java.util.List;

import Data.Cycle;
import Data.ReplaceData;
import ValueInputHandlers.*;

public class DeleteCycleId {
    public static void execute(List<Cycle> cycles, String filePath) throws IndexOutOfBoundsException {

        int intArgCount = 2;
        String intArgNames[] = { "Cycle Id: ", "Range: " };
        int defaultIntValues[] = { -1, 0 };

        RangeCheck intRanges[] = { new RangeCheck(0), new RangeCheck(0) };

        int deletedCount = 0;

        try {

            int intArgs[] = IntValueInput.parser(intArgCount, intArgNames, defaultIntValues, intRanges);

            int id = intArgs[0];
            int range = intArgs[1];

            List<Cycle> modifiedCycles = new ArrayList<>();

            for (int i = 0; i < (cycles.size()); i++) {

                Cycle cycle = cycles.get(i);
                int cycleId = cycle.getIntValues()[0];

                if (id <= cycleId && cycleId <= id + range) {
                    deletedCount++;
                    continue;
                }
                modifiedCycles.add(cycle);
            }

            ReplaceData.replace(modifiedCycles, filePath);

            System.out.println("\n" + deletedCount + " Matching Records Successfully deleted");

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Command failed");
            System.out.println(
                    "Expected args cycleId: +ve int(required), Range:+ve int (0)");
            return;
        } catch (InvalidInputException e) {
            System.out.println("\nFailure\n");
            System.out.println(
                    "Expected args cycleId: +ve int(required), Range:+ve int (0)");
            return;
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        System.out.println("\nSuccess");
    }

}
