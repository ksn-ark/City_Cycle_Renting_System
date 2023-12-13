package ValueInputHandlers;

public class RangeCheck {

    int intMin;
    int intMax;
    float floatMin;
    float floatMax;

    public RangeCheck(int intMin) {
        this.intMin = intMin;
        this.intMax = -1;
    }

    public RangeCheck(int intMin, int intMax) {
        this.intMin = intMin;
        this.intMax = intMax;
    }

    public RangeCheck(float floatMin) {
        this.floatMin = floatMin;
        this.floatMax = -1;
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
}
