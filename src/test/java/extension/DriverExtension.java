package extension;


import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import static project.properties.DriverManager.closeDriver;
import static project.properties.DriverManager.getWebDriver;

public class DriverExtension implements BeforeAllCallback, AfterAllCallback {


    @Override
    public void afterAll(ExtensionContext extensionContext) throws Exception {
        closeDriver();
    }

    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        getWebDriver();

    }
}
