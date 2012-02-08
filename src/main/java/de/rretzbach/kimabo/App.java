package de.rretzbach.kimabo;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.rretzbach.kimabo.file2file.CommandLineApp;
import de.rretzbach.kimabo.file2file.CommandLineFileResizerModule;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new CommandLineFileResizerModule(args));

        //if (args.length == 2) {
        injector.getInstance(CommandLineApp.class).main();
        return;
        //}

        // TODO: start gui
    }
}
