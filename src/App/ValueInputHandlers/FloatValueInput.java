package ValueInputHandlers;

import java.util.Scanner;

public class FloatValueInput {

    static Scanner scnr = new Scanner(System.in);

    public static float[] parser(int maxArgs, String[] argNames, float defaultValues[], RangeCheck range[])
            throws InvalidInputException {

        float valueArgs[] = new float[maxArgs];

        for (int valueCount = 0; valueCount < maxArgs; valueCount++) {

            System.out.print(argNames[valueCount]);

            String inputString = scnr.nextLine();

            try {

                if (inputString.equals("")) {
                    valueArgs[valueCount] = defaultValues[valueCount];
                    continue;
                }

                valueArgs[valueCount] = Float.parseFloat(inputString);

                if (!range[valueCount].floatCheck(valueArgs[valueCount])) {
                    System.out.println(inputString + " is outside the expected float range for arg");
                    throw new InvalidInputException(inputString);
                }
            } catch (NumberFormatException e) {
                System.out.println(inputString + " is an invalid float arg");
                throw new InvalidInputException(inputString);
            }

        }

        return valueArgs;

    }
}
