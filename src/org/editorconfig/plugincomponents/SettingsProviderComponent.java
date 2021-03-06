package org.editorconfig.plugincomponents;

import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.diagnostic.Logger;
import org.editorconfig.core.EditorConfig;
import org.editorconfig.core.EditorConfig.OutPair;
import org.editorconfig.core.EditorConfigException;
import org.editorconfig.core.PythonException;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SettingsProviderComponent implements ApplicationComponent {
    private static final Logger LOG =
            Logger.getInstance("#org.editorconfig.plugincomponents.SettingsProviderComponent");

    private EditorConfig editorConfig;
    
    public SettingsProviderComponent() {
        try {
            editorConfig = new EditorConfig();
        }
        catch (PythonException error) {
            LOG.error(error.getStackTrace());
        }
    }
    
    public static SettingsProviderComponent getInstance() {
        return ServiceManager.getService(SettingsProviderComponent.class); 
    }
    
    public List<OutPair> getOutPairs (String filePath) {
        final List<OutPair> outPairs;
        try {
            outPairs = editorConfig.getProperties(filePath);
            return outPairs;
        }
        catch (EditorConfigException error) {
            LOG.error(error.getStackTrace());
            return new ArrayList<OutPair>();
        }   
    }
    
    public void initComponent() {
        // TODO: insert component initialization logic here
    }

    public void disposeComponent() {
        // TODO: insert component disposal logic here
    }

    @NotNull
    public String getComponentName() {
        return "SettingsProviderComponent";
    }
}
