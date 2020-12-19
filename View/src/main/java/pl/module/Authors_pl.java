package pl.module;

import java.util.ListResourceBundle;

public class Authors_pl extends ListResourceBundle {

    private final Object[][] resources = {
            {"title", "Autorzy"},
            {"author1", "Grzegorz Kucharski"},
            {"author2", "Wojciech Cwynar"}
    };

    @Override
    protected Object[][] getContents() {
        return resources;
    }
}
