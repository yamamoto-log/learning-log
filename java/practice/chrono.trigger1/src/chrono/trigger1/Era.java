package chrono.trigger1;

public enum Era {
    PREHISTORIC("原始", -65000000),
    DARK_AGES("古代", -12000),
    MIDDLE_AGES("中世", 600),
    PRESENT("現代", 1000),
    FUTURE("未来", 2300),
    END_OF_TIME("時の最果て", 0);

    private final String japaneseName;
    private final int year;

    Era(String japaneseName, int year) {
        this.japaneseName = japaneseName;
        this.year = year;
    }

    public String getJapaneseName() {
        return this.japaneseName;
    }

    public int getYear() {
        return this.year;
    }
}