package org.raul.plugins.simpleHardcoreMode;

public class Config {

    private final String lang;
    private final String welcome_message;
    private final String kick_message;
    private final String ban_message;
    private final String broadcast_message;
    private final String enter_message;
    private final int default_lives;
    private final boolean permaban;
    private int ban_time_minutes = 0;
    private int ban_time_hours = 0;
    private int ban_time_days = 0;
    private long ban_total_time = 0;

    public Config(String lang, String welcome_message, String kick_message, String ban_message, String broadcast_message, String enter_message, int default_lives, boolean permaban, int ban_time_minutes, int ban_time_hours, int ban_time_days) {
        this.lang = lang;
        this.welcome_message = welcome_message;
        this.kick_message = kick_message;
        this.ban_message = ban_message;
        this.broadcast_message = broadcast_message;
        this.enter_message = enter_message;
        this.default_lives = default_lives;
        this.permaban = permaban;
        if (!this.permaban) {
            this.ban_time_minutes = Math.max(ban_time_minutes, 0);
            this.ban_time_hours = Math.max(ban_time_hours, 0);
            this.ban_time_days = Math.max(ban_time_days, 0);
            this.ban_total_time = ((long) this.ban_time_minutes * 60 * 1000) + ((long) this.ban_time_hours * 3600 * 1000) + ((long) this.ban_time_days * 86400 * 1000);
        }
    }

    public long getBan_total_time() {
        return ban_total_time;
    }

    public int getBan_time_days() {
        return ban_time_days;
    }

    public int getBan_time_hours() {
        return ban_time_hours;
    }

    public int getBan_time_minutes() {
        return ban_time_minutes;
    }

    public boolean isPermaban() {
        return permaban;
    }

    public int getDefault_lives() {
        return default_lives;
    }

    public String getEnter_message() {
        return enter_message;
    }

    public String getBroadcast_message() {
        return broadcast_message;
    }

    public String getBan_message() {
        return ban_message;
    }

    public String getKick_message() {
        return kick_message;
    }

    public String getWelcome_message() {
        return welcome_message;
    }

    public String getLang() {
        return lang;
    }


}
