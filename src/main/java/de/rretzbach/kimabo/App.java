package de.rretzbach.kimabo;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.rretzbach.kimabo.file2file.CommandLineApp;
import de.rretzbach.kimabo.file2file.CommandLineFileResizerModule;

public class App {

    public static void main(String[] args) {
        if (args.length > 0) {
            Injector injector = Guice.createInjector(new CommandLineFileResizerModule(args));
            injector.getInstance(CommandLineApp.class).main();
            return;
        }
        // TODO: start gui
    }
}