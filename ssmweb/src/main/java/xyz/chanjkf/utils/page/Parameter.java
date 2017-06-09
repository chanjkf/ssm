package xyz.chanjkf.utils.page;

public final class Parameter {
    private ConditionType condition;
    private String name;
    private Object value;
    private Object value2;

    public Parameter(ConditionType condition, String name, Object value, Object value2) {
        this.condition = condition;
        this.name = name;
        this.value = value;
        this.value2 = value2;
    }

    public Parameter(String name, Object value, Object value2) {
        this.condition = ConditionType.between;
        this.name = name;
        this.value = value;
        this.value2 = value2;
    }


    public Parameter(ConditionType condition, String name, Object value) {
        this.condition = condition;
        this.name = name;
        this.value = value;
        this.value2 = null;
    }

    public Parameter(ConditionType condition, String name) {
        this.condition = condition;
        this.name = name;
    }

    public Parameter(String name, Object value) {
        this.condition = ConditionType.eq;
        this.name = name;
        this.value = value;
        this.value2 = null;
    }

    public Parameter(String name) {
        this.condition = ConditionType.eq;
        this.name = name;
        this.value = null;
        this.value2 = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Object getValue2() {
        return value2;
    }

    public void setValue2(Object value2) {
        this.value2 = value2;
    }


    public ConditionType getCondition() {
        return condition;
    }

    public void setCondition(ConditionType condition) {
        this.condition = condition;
    }

    public boolean isNull() {
        return value == null;
    }

    public boolean isValue2Null() {
        return value2 == null;
    }

    @Override
    public String toString() {
        return "Parameter{" +
                "condition=" + condition +
                ", name='" + name + '\'' +
                ", value=" + value +
                ", value2=" + value2 +
                '}';
    }
}
