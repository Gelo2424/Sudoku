package pl.module;

import java.util.ListResourceBundle;

public class Authors_en extends ListResourceBundle {

    private final Object[][] resources = {
            {"title", "Authors"},
            {"author1", "Grzegorz Kucharski"},
            {"author2", "Wojciech Cwynar"}
    };

    @Override
    protected Object[][] getContents() {
        return resources;
    }
}
