package propertiestranslator;

import com.google.api.GoogleAPIException;
import com.google.api.translate.Language;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import myjavafx.Dialog;
import myjavafx.ListViewCheckBox;
import myjavafx.MyComboBox;
import myjavafx.MyTextField;


/**
 * Page d'acceuil du traducteur de fichiers properties.
 * @author JOHAN
 */
public class HomeController {
    private Translator translator;
    private EditablePropertiesManager editablePropertiesManager;
    private FileChooser fileChooser;
    private DirectoryChooser directoryChooser;
    
    @FXML
    private MyTextField srcTextField;
    @FXML
    private MyComboBox<String> srcComboBox;
    @FXML
    private ListViewCheckBox destListView;
    @FXML
    private MyTextField destTextField;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private ProgressIndicator progressIndicator;
    @FXML
    private Button translateButton;
    
    /**
     * Capitalise le texte donne, c'est a dire le passe en minuscules avec une premiere lettre majuscule.
     * @param text text a capitaliser
     * @return le texte capitalise
     */
    public String capitalize(String text) {
        return text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();
    }
    
    public ArrayList<Language> getAvailableLanguages() {
        ArrayList<Language> results = new ArrayList<>(Arrays.asList(Language.values()));
        //languages not availables anymore
        results.remove(Language.BIHARI);
        results.remove(Language.CHEROKEE);
        results.remove(Language.DHIVEHI);
        results.remove(Language.GUARANI);
        results.remove(Language.INUKTITUT);
        results.remove(Language.ORIYA);
        results.remove(Language.SANSKRIT);
        results.remove(Language.TIBETAN);
        results.remove(Language.UIGHUR);
        results.remove(Language.FILIPINO); // == TAGALOG
        return results;
    }
    
    /**
     * Initialise la page d'accueil.
     */
    void initialize() {
        this.translator = Translator.getInstance();
        this.editablePropertiesManager = new EditablePropertiesManager();
        try {
            Translator.setKey(this.editablePropertiesManager.readProperty("key"));
        } catch (IOException ioe) {
            changeKeyAction(null);
        }
        this.fileChooser = new FileChooser();
        FileChooser.ExtensionFilter propertiesFilter = new FileChooser.ExtensionFilter("properties", "*.properties");
        this.fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Files", "*.*"),
                propertiesFilter
        );
        this.fileChooser.selectedExtensionFilterProperty().setValue(propertiesFilter);
        this.srcComboBox.setAll(getAvailableLanguages().stream()
                                      .map(lang -> lang.toString().toUpperCase() + " - " + capitalize(lang.name()))
                                      .collect(Collectors.toList()));
        this.directoryChooser = new DirectoryChooser();
        this.srcComboBox.set(0, Language.AUTO_DETECT.name());
        this.srcComboBox.select(0);
        getAvailableLanguages().stream().filter(lang -> !Language.AUTO_DETECT.equals(lang)).forEach(lang -> {
            this.destListView.add(lang.toString().toUpperCase() + " - " + capitalize(lang.name()), false);
        });
        this.destListView.add(0, "Select All", false, new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                destListView.chechAll(newValue);
            }
        });
    }
    
    @FXML
    private void changeKeyAction(ActionEvent event) {
        String key = "";
        try {
            key = this.editablePropertiesManager.readProperty("key");
        } catch (IOException ioe) {/*No pre-registered key*/}
        key = Dialog.request("Enter your Google API key", key);
        if ((key != null) && !key.isEmpty()) {
            Translator.setKey(key);
            try {
                this.editablePropertiesManager.setProperty("key", key);
            } catch (IOException ioe) {
                Dialog.warning("Impossible to write in the file '" + this.editablePropertiesManager.getPath() + "'", "Your key won't be store for further traductions");
            }
        }
    }
    
    @FXML
    private void deleteKeyAction(ActionEvent event) {
        try {
            this.editablePropertiesManager.setProperty("key", null);
        } catch (IOException ioe) {
            Dialog.warning("Impossible to write in the file '" + this.editablePropertiesManager.getPath() + "'", "Impossible to access to the config file : " + ioe.getMessage());
        }
    }
    
    @FXML
    private void srcButtonAction(ActionEvent event) {
        File initFile = new File(this.srcTextField.getText());
        if (initFile.exists()) {
            if (!initFile.isDirectory()) {
                this.fileChooser.setInitialDirectory(initFile.getAbsoluteFile().getParentFile());
            } else {
                this.fileChooser.setInitialDirectory(initFile);
            }
        } else {
            try {
                initFile = new File(this.editablePropertiesManager.readProperty("srcFile"));
                if (initFile.exists()) {
                    if (!initFile.isDirectory()) {
                        this.fileChooser.setInitialDirectory(initFile.getAbsoluteFile().getParentFile());
                    } else {
                        this.fileChooser.setInitialDirectory(initFile);
                    }
                }
            } catch (IOException ioe) {/*No previous path*/}
        }
        File file = this.fileChooser.showOpenDialog((Stage)((Node)event.getSource()).getScene().getWindow());
        if (file != null) {
            this.srcTextField.setText(file.getPath());
            if (this.destTextField.getText().isEmpty()) {
                this.destTextField.setText(file.getParent());
            }
        }
    }
    
    @FXML
    private void srcKeyPressed(KeyEvent event) {
        if (event.getCode().isLetterKey()) {
            this.srcComboBox.selectNextStartingWith(event.getText());
        }
    }
    
    @FXML
    private void destKeyPressed(KeyEvent event) {
        if (event.getCode().isLetterKey()) {
            this.destListView.selectNextStartingWith(event.getText());
        } else if (event.getCode().equals(KeyCode.ENTER)) {
            this.destListView.changeCheck(this.destListView.getSelectedIndex());
        }
    }
    
    @FXML
    private void destButtonAction(ActionEvent event) {
        File initFile = new File(this.destTextField.getText());
        if (initFile.exists()) {
            if (!initFile.isDirectory()) {
                this.directoryChooser.setInitialDirectory(initFile.getAbsoluteFile().getParentFile());
            } else {
                this.directoryChooser.setInitialDirectory(initFile);
            }
        } else {
            initFile = new File(this.srcTextField.getText());
            if (initFile.exists()) {
                if (!initFile.isDirectory()) {
                    this.directoryChooser.setInitialDirectory(initFile.getAbsoluteFile().getParentFile());
                } else {
                    this.directoryChooser.setInitialDirectory(initFile);
                }
            }
        }
        File file = this.directoryChooser.showDialog((Stage)((Node)event.getSource()).getScene().getWindow());
        if (file != null) {
            this.destTextField.setText(file.getPath());
        }
    }
    
    private void setProgress(double value) {
        this.progressBar.setProgress(value);
        this.progressIndicator.setProgress(value);
    }
    
    @FXML
    private void translateAction(ActionEvent event) {
        if (this.destListView.getSelectedTexts().isEmpty()) {
            Dialog.warning("No destination language", "Please select at least 1 destination langage !");
            return;
        }
        
        //get the source and destination language(s)
        final Language srcLang = Language.valueOf((this.srcComboBox.getSelectedItem().split(" - ").length > 1) ? 
                                             this.srcComboBox.getSelectedItem().split(" - ")[1].toUpperCase()
                                           : this.srcComboBox.getSelectedItem());
        List<Language> dest = (List<Language>)this.destListView.getSelectedTexts().stream()
                .filter(text -> text.toString().split(" - ").length > 1)
                .map(text -> text.toString().split(" - ")[1])
                .map(lang -> Language.valueOf(lang.toString().toUpperCase()))
                .collect(Collectors.toList());
        
        //init the writers
        HashMap<Language, BufferedWriter> writersMap = new HashMap<>();
        for (Language destLang : dest) {
            String fileName = destTextField.getText();
            if (!fileName.isEmpty()) fileName += File.separator;
            fileName += destLang.toString().toUpperCase();
            int number = 1;
            while(Paths.get(fileName + ".properties").toFile().exists()) {
                String numCopy = "(" + (number++) + ")";
                if ((fileName.length() > 3) && fileName.substring(fileName.length()-3).matches("\\(\\d\\)")) {
                    fileName = fileName.substring(0, fileName.length()-3) + numCopy;
                } else {
                    fileName = fileName + numCopy;
                }
            }
            fileName += ".properties";
            try {
                writersMap.put(destLang, Files.newBufferedWriter(Paths.get(fileName)));
            } catch (IOException ioe) {
                Dialog.error("Impossible to write in the file '" + fileName + "'", ioe.getMessage());
                return;
            }
        }
        
        //translate
        setProgress(0);
        this.translateButton.setDisable(true);
        new Thread(() -> translateSrc(srcLang, dest, writersMap)).start(); //avoid letting javaFX thread doing the job, causing window to stop answer
        
        //save the srcFile for the next translation
        try {
            this.editablePropertiesManager.setProperty("srcFile", this.srcTextField.getText());
        } catch (IOException ioe) {/*Impossible to save the previous srcFile and destDit in the config file*/}
    }
    
    private int lineIndex;
    private int languageIndex;
    private boolean exceptionThrown;
    /**
     * Translate the srcFile from <code>srcLang</code> to all the <code>dest</code> 
     * languages given, and store the results in the <code>writersMap</code>.
     * <code>writersMaps</code> must contains a writer for each language 
     * specify in <code>dest</code>
     * @param srcLang the source language
     * @param dest the destination languages
     * @param writersMap the writers
     * @return <tt>true</tt> is the translations have been done without any issue
     */
    private boolean translateSrc(Language srcLang, List<Language> dest, HashMap<Language, BufferedWriter> writersMap) {
        try (Stream<String> stream = Files.lines(Paths.get(this.srcTextField.getText()))) {
            //clone the stream to be able to get his size without consuming it
            String[] streamArray = stream.toArray(String[]::new);
            Supplier<Stream<String>> streamSupplier = () -> Stream.of(streamArray);
            this.lineIndex = 0;
            final double nbLines = streamSupplier.get().count();
            final double nbDest = dest.size();
            this.exceptionThrown = false;
            streamSupplier.get().forEach(line -> {
                if (!this.exceptionThrown) {
                    try {
                        this.languageIndex = 0;
                        for (final Language destLang : dest) {
                            String key;
                            String value;
                            if (line.startsWith("#")) { //comment to ignore
                                key = line;
                                value = "";
                            } else {
                                key = line.split("=")[0];
                                switch (line.split("=").length) {
                                    case 0:
                                    case 1:
                                        value = "";
                                        break;
                                    case 2:
                                        value = line.split("=")[1];
                                        break;
                                    default:
                                        value = "";
                                        String[] valueArray = line.split("=");
                                        for (int i=1; i<valueArray.length; i++) {
                                            value += valueArray[i];
                                        }
                                }
                                if (!value.isEmpty()) {
                                    if (!srcLang.equals(destLang)) {
                                        ArrayList<String> valueLines = EncodingManager.parse(value);
                                        for (int i=0; i<valueLines.size(); i++) { //replace each line by it's translation
                                            if (false)
                                            valueLines.set(i, this.translator.translate(valueLines.get(i), srcLang, destLang));
                                        }
                                        value = valueLines.stream().reduce("", (a,b) -> a + "\n" + b).substring(1);
                                    }
                                    value = EncodingManager.unParse(value);
                                }
                                key = EncodingManager.nonASCIIToUnicode(key);
                            }
                            try {
                                if (value.isEmpty()) writersMap.get(destLang).write(key);
                                else writersMap.get(destLang).write(key + "=" + value);
                                writersMap.get(destLang).newLine();
                            } catch (IOException ioe) {
                                Platform.runLater(() -> Dialog.error("Impossible to write in the file '" + writersMap.get(destLang) + "'", ioe.getMessage()));
                                this.exceptionThrown = true;
                                break;
                            }
                            Platform.runLater(() -> setProgress(this.lineIndex/nbLines + ((this.languageIndex+1)/nbDest)/nbLines));
                            this.languageIndex++;
                        }
                    } catch (GoogleAPIException gae) {
                        Platform.runLater(() -> Dialog.error("Impossible to reach google API", gae.getMessage()));
                        this.exceptionThrown = true;
                    }
                }
                this.lineIndex++;
            });
        } catch (Exception e) {
            final String message = ((e.getMessage() == null) || e.getMessage().isEmpty()) ? "Impossible to read the source file !" : e.getMessage();
            Platform.runLater(() -> Dialog.error("Impossible to read the file '" + this.srcTextField.getText() + "'", message));
            return false;
        }
        setProgress(1);
        Platform.runLater(() -> {
            this.translateButton.setDisable(false);
            if (!this.exceptionThrown) Dialog.confirmation("Translations successfuls", "All the translations have been done");
        });
        //close the writers streams
        Set<Entry<Language, BufferedWriter>> entries = new HashSet<>(writersMap.entrySet());
        entries.forEach(entry -> {
            try {
                if (entry.getValue() != null) {
                    entry.getValue().close();
                }
            } catch (IOException ioe) {/*stream already close*/}
            writersMap.remove(entry.getKey());
        });
        return (!this.exceptionThrown);
    }
}