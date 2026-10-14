package chrono.trigger1;

public enum Element {
    HEAVEN("天"),
    WATER("水"),
    FIRE("火"),
    SHADOW("冥"),
    NONE("無");

    private final String japaneseName;

    Element(String japaneseName) {
        this.japaneseName = japaneseName;
    }

    public String getJapaneseName() {
        return this.japaneseName;
    }
}