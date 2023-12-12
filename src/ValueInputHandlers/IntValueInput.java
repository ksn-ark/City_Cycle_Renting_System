package ValueInputHandlers;

import java.util.Scanner;

public class IntValueInput {

    static Scanner scnr = new Scanner(System.in);

    public static int[] parser(int maxArgs, String[] argNames, int defaultIntValues[], RangeCheck range[])
            throws InvalidInputException {

        int valueArgs[] = new int[maxArgs];

        for (int valueCount = 0; valueCount < maxArgs; valueCount++) {

            System.out.print(argNames[valueCount]);

            String inputString = scnr.nextLine();

            try {

                if (inputString.equals("")) {
                    if (defaultIntValues[valueCount] < 0) {
                        valueArgs[valueCount] = defaultIntValues[valueCount + defaultIntValues[valueCount]];
                        continue;
                    }
                    valueArgs[valueCount] = defaultIntValues[valueCount];
                    continue;
                }

                valueArgs[valueCount] = Integer.parseInt(inputString);

                if (!range[valueCount].intCheck(valueArgs[valueCount])) {
                    System.out.println(inputString + " is outside the expected integer range for arg");
                    throw new InvalidInputException(inputString);
                }
            } catch (NumberFormatException e) {
                System.out.println(inputString + " is an invalid integer arg");
                throw new InvalidInputException(inputString);
            }

        }

        return valueArgs;

    }
}