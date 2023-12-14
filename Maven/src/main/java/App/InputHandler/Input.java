package App.InputHandler;

import java.util.Map;
import java.util.Scanner;

/*
 * Command arguments are defined in a <String, Object[]> map above every execute
 * method this allows a single input class to proccess any number of arguments
 * of all types and handle default value cases required cases and even
 * rangechecking all with a single variable.
 * 
 * It also allows the help command to dynamically print the expected arguments,
 * default values, types and ranges for any command.
 * 
 * Command arg Map example:
 * 
 * ("ArgName", Object[]{default-value:(implicit-type-implication),RangeCheck()})
 * 
 * in cases of default value being a previous field, default value is a string
 * that is the name of the field. Since it's always a field that has already
 * been processed it recieves the value.
 * 
 * in cases of no default value, i.e required it is given as
 * "valuetypeRequired" , ex- "intRequired"
 * 
 * in cases of default value being unchanged it is "valuetypeUnchanged", ex-
 * "intUnchanged"
 */

public class Input {

    public static final String ANSI_RESET = "\u001B[0m";

    public static final String ANSI_GREEN = "\u001B[32m";

    static Scanner scnr = new Scanner(System.in);

    static public Map<String, Object[]> getCommandArgs(Map<String, Object[]> commandArgs) throws InvalidInputException {

        for (Map.Entry<String, Object[]> arg : commandArgs.entrySet()) {

            Object[] valueObject = arg.getValue();

            String endString = valueObject[0] instanceof Boolean ? " (true or false): " : ": ";

            System.out.print(ANSI_GREEN + "\nEnter " + arg.getKey() + endString + ANSI_RESET);

            String inputString = scnr.nextLine();

            if (valueObject[0] instanceof String) { // in cases of default value being a different field

                String argString = (String) valueObject[0];

                if (commandArgs.get(argString) != null) { // in cases of value being required it will fall of here

                    arg.setValue(commandArgs.get(argString)); // field name of default value is defined as value in
                                                              // commandargMap

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

                if (inputString.equals("true") || inputString.equals("false")) {
                    valueObject[0] = Boolean.parseBoolean(inputString);

                    return valueObject;
                } else {
                    throw new Exception();
                }

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

                if (valueObject[0] instanceof String) { // this is in cases that the value is required or unchanged

                    if (valueObject[0].toString().endsWith("Unchanged")) {

                        valueObject[0] = -1; // unchanged is keyed as -1 as no int or float accepts non-zero input
                                             // normally. can be keyed as a string and check for that if project is
                                             // further expanded and there is a need.

                        return valueObject;

                    }
                    System.err.println("value is a required argument");
                    System.out.println("Command failed.");

                    throw new InvalidInputException(inputString); // throws if no required values
                }

                return valueObject; // defaults to value if available
            }

            if (valueObject[0] instanceof String) { // handles parsing input for required/unchanged values

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

    public static boolean confirmAction() {

        System.out.println(ANSI_GREEN + "\tEnter yes or y to confirm action, any other key to cancel." + ANSI_RESET);
        System.out.print("\t\t");
        String userInput = scnr.nextLine();
        if (!userInput.equals("y") && !userInput.equals("yes")) {
            System.out.println("\nCommand cancelled, no changes made.");

            return false;
        }
        return true;
    }
}
