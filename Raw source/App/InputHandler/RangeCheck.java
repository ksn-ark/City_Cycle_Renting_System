package InputHandler;

public class RangeCheck {

    int intMin = -1;
    int intMax = -1;
    float floatMin = -1;
    float floatMax = -1;

    public RangeCheck(int intMin) {
        this.intMin = intMin;
    }

    public RangeCheck(int intMin, int intMax) {
        this.intMin = intMin;
        this.intMax = intMax;
    }

    public RangeCheck(float floatMin) {
        this.floatMin = floatMin;
    }

    public RangeCheck(float floatMin, float floatMax) {
        this.floatMin = floatMin;
        this.floatMax = floatMax;
    }

    public boolean intCheck(int input) {
        if (intMax == -1) {
            return intMin <= input;
        }
        return intMin <= input && input <= intMax;
    }

    public boolean floatCheck(float input) {
        if (floatMax == -1) {
            return floatMin <= input;
        }
        return floatMin <= input && input <= floatMax;
    }

    public String getRange() {

        String output = "(";

        if (intMin != -1) {
            output += intMin;
            if (intMax != -1) {
                output += ", " + intMax;
            } else {
                output += ", max int value";
            }
        }
        if (floatMin != -1) {
            output += floatMin;
            if (floatMax != -1) {
                output += ", " + floatMax;
            } else {
                output += ", max float value";
            }
        }

        output += ") inclusive";

        return output;
    }

}
