package de.rretzbach.kimabo;

import de.rretzbach.kimabo.file2file.CommandLineApp;
import com.google.inject.Guice;
import com.google.inject.Injector;
import de.rretzbach.kimabo.file2file.CommandLineFileResizerModule;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new CommandLineFileResizerModule(args));

        if (args.length == 2) {
            injector.getInstance(CommandLineApp.class).main(args);
            return;
        }

        // TODO: start gui
    }
}
