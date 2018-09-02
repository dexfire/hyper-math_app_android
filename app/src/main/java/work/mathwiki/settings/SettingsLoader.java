package work.mathwiki.settings;

/**
 * Created by Dexfire on 2018/8/29 0029.
 */

public class SettingsLoader {
    private static final SettingsLoader ourInstance = new SettingsLoader();

    public static SettingsLoader getInstance() {
        return ourInstance;
    }

    private SettingsLoader() {
    }


}
