package ValueInputHandlers;

import java.util.Map;
import java.util.Scanner;

public class Input {

    static Scanner scnr = new Scanner(System.in);

    static public Map<String, Object[]> getCommandArgs(Map<String, Object[]> commandArgs) throws InvalidInputException {

        for (Map.Entry<String, Object[]> arg : commandArgs.entrySet()) {

            Object[] valueObject = arg.getValue();

            String endString = valueObject[0] instanceof Boolean ? " (true or false): " : ": ";

            System.out.print("\nEnter " + arg.getKey() + endString);

            String inputString = scnr.nextLine();

            if (valueObject[0] instanceof String) { // in cases of default value being a different field

                String argString = (String) valueObject[0];

                if (commandArgs.get(argString) != null) { // in cases of value being required it will fall of here

                    arg.setValue(commandArgs.get(argString));

                    continue;

                }

            }

            if (valueObject[0] instanceof Boolean) {
                booleanParser(inputString, valueObject);
                continue;
            }

            arg.setValue(intFloatParser(inputString, valueObject));

        }

        return commandArgs;
    }

    static Object booleanParser(String inputString, Object[] valueObject) throws InvalidInputException {

        if (inputString.equals("")) {

            return valueObject;

        } else {

            try {
                System.out.println(valueObject[0]);
                valueObject[0] = Boolean.parseBoolean(inputString);

                return valueObject;

            } catch (Exception e) {
                System.out.println(inputString + " is an invallid boolean arg");
                throw new InvalidInputException(inputString);
            }
        }
    }

    static Object[] intFloatParser(String inputString, Object[] valueObject)
            throws InvalidInputException {

        RangeCheck check = (RangeCheck) valueObject[1];

        try {

            if (inputString.equals("")) { // no input

                if (valueObject[0] instanceof String) {
                    System.err.println("value is a required argument");
                    System.out.println("Command failed.");

                    throw new IndexOutOfBoundsException(); // throws if no required values
                }

                return valueObject; // defaults
            }

            if (valueObject[0] instanceof String) { // handles required values
                String value = (String) valueObject[0];
                if (value.startsWith("int")) {
                    valueObject[0] = Integer.parseInt(inputString);
                }
                if (value.startsWith("float")) {
                    valueObject[0] = Float.parseFloat(inputString);
                }
            }

            if (valueObject[0] instanceof Integer) { // handles int non required values

                valueObject[0] = Integer.parseInt(inputString);

            }

            if (valueObject[0] instanceof Float) { // handles float non required values

                valueObject[0] = Float.parseFloat(inputString);

            }

            if (valueObject[0] instanceof Integer && !check.intCheck((Integer) valueObject[0])) {

                System.out.println(inputString + " is outside the expected integer range for arg");
                throw new InvalidInputException(inputString);

            }

            if (valueObject[0] instanceof Float && !check.floatCheck((Float) valueObject[0])) {

                System.out.println(inputString + " is outside the expected float range for arg");
                throw new InvalidInputException(inputString);

            }

        } catch (NumberFormatException e) {
            System.out.println(inputString + " is an invalid type of arg");
            throw new InvalidInputException(inputString);
        }

        return valueObject;
    }
}
