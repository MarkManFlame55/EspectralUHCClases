package net.mmf55dev.uhcclases.classes;

public class UhcClass {
    String name;

    UhcClass(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public static UhcClass ASSASSIN = new UhcClass("assassin");
    public static UhcClass IRON_GOLEM = new UhcClass("iron_golem");
    public static UhcClass WARDEN = new UhcClass("warden");
    public static UhcClass BLAZE = new UhcClass("blaze");
    public static UhcClass DOLPHIN = new UhcClass("dolphin");
    public static UhcClass ARCHER = new UhcClass("archer");
    public static UhcClass SLEEPY = new UhcClass("sleepy");
    public static UhcClass WITCH = new UhcClass("witch");
    public static UhcClass NULL = new UhcClass("null");
}
