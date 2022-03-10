package com.github.maxomys.geometrydash.file;

public abstract class Serialize {

    public abstract String serialize(int tabSize);

    public String addStringProperty(String name, String value, int tabSize, boolean newLine, boolean comma) {
        return addTabs(tabSize) + "\"" + name + "\": " + "\"" + value + "\"" + addEnding(newLine, comma);
    }

    public String addIntProperty(String name, int value, int tabSize, boolean newLine, boolean comma) {
        return addTabs(tabSize) + String.format("\"%s\": %d", name, value) + addEnding(newLine, comma);
    }

    public String addFloatProperty(String name, float value, int tabSize, boolean newline, boolean comma) {
        return addTabs(tabSize) + "\"" + name + "\": " + value + "f" + addEnding(newline, comma);
    }

    public String addDoubleProperty(String name, double value, int tabSize, boolean newline, boolean comma) {
        return addTabs(tabSize) + "\"" + name + "\": " + value + addEnding(newline, comma);
    }

    public String addBooleanProperty(String name, boolean value, int tabSize, boolean newline, boolean comma) {
        return addTabs(tabSize) + "\"" + name + "\": " + value + addEnding(newline, comma);
    }

    public String beginObjectProperty(String name, int tabSize) {
        return addTabs(tabSize) + "\"" + name + "\": {" + addEnding(true, false);
    }

    public String closeObjectProperty(int tabSize) {
        return addTabs(tabSize) + "}";
    }

    public String addTabs(int tabSize) {
        return "\t".repeat(tabSize);
    }

    public String addEnding(boolean newLine, boolean comma) {
        String str = "";
        if (comma)
            str += ",";
        if (newLine)
            str += "\n";
        return str;
    }

}
