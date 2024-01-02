package com.liushuang.test.tool.utils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class ResourceBundleUtf8Control extends ResourceBundle.Control {

    @Override
    public ResourceBundle newBundle(final String baseName, final Locale locale, final String format,
        final ClassLoader loader, final boolean reload)
        throws IllegalAccessException, InstantiationException, IOException {
        final String bundleName = toBundleName(baseName, locale);
        final String resourceName = toResourceName(bundleName, "properties");

        try (InputStream is = loader.getResourceAsStream(resourceName);
            InputStreamReader isr = new InputStreamReader(is, "UTF-8");
            BufferedReader reader = new BufferedReader(isr)) {
            return new PropertyResourceBundle(reader);
        }
    }
}