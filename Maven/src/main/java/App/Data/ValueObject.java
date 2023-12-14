package App.Data;

import App.InputHandler.RangeCheck;

public class ValueObject {

    int intValue;
    float floatValue;
    RangeCheck check;
    Boolean booleanValue;

    public ValueObject(int intValue, RangeCheck check) {
        this.intValue = intValue;
        this.check = check;
    }

    public ValueObject(float floatValue, RangeCheck check) {
        this.floatValue = floatValue;
        this.check = check;
    }

    public ValueObject(Boolean booleanValue) {
        this.booleanValue = booleanValue;
    }

    public int getIntValue() {
        return intValue;
    }

    public float getFloatValue() {
        return floatValue;
    }

    public RangeCheck getCheck() {
        return check;
    }

    public Boolean getBoolean() {
        return booleanValue;
    }
}
