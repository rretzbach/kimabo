/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.rretzbach.kimabo.file2file;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import de.rretzbach.kimabo.*;
import org.apache.commons.cli.*;

/**
 * @author rretzbach
 */
public class CommandLineFileResizerModule extends AbstractModule {
    private String seriesName = "Unnamed";
    private String baseDirectory = ".";
    private int pagesPerBook = 300;
    private int beginBookIndex = 0;

    public CommandLineFileResizerModule(String[] args) {
        try {
            Options options = new Options();
            options.addOption("s", "series-name", true, "Name of the series");
            options.addOption("d", "directory", true, "Base directory");
            options.addOption("p", "pages-per-book", true, "Number of pages per book");
            options.addOption("b", "begin-book-index", true, "Index of first book to render");
            options.addOption("h", "help", false, "print this message");

            CommandLineParser parser = new GnuParser();
            CommandLine cmd = parser.parse( options, args);

            if (cmd.hasOption("help")) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp( "kimabo", options );
                System.exit(0);
            }
            if (cmd.hasOption("series-name")) {
                seriesName = cmd.getOptionValue("series-name");
            }
            if (cmd.hasOption("directory")) {
                baseDirectory = cmd.getOptionValue("directory");
            }
            if (cmd.hasOption("pages-per-book")) {
                pagesPerBook = Integer.parseInt(cmd.getOptionValue("pages-per-book"));
            }
            if (cmd.hasOption("begin-book-index")) {
                beginBookIndex = Integer.parseInt(cmd.getOptionValue("begin-book-index"));
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void configure() {
        bind(new TypeLiteral<ImageReader<String>>() {
        }).to(FileImageReader.class);
        bind(new TypeLiteral<ImageWriter<String>>() {
        }).to(FileImageWriter.class);
        bind(new TypeLiteral<TargetFinder<String, String>>() {
        }).to(DirTargetFinder.class);
        bind(new TypeLiteral<SourceProvider<String>>() {
        }).to(FileSourceProvider.class);
        bind(new TypeLiteral<Resizer<String, String>>() {
        }).to(new TypeLiteral<ParallelResizer<String, String>>(){});

        bindConstant().annotatedWith(SeriesName.class).to(seriesName);
        bindConstant().annotatedWith(PagesPerBook.class).to(pagesPerBook);
        bindConstant().annotatedWith(BaseInputDir.class).to(baseDirectory);
        bindConstant().annotatedWith(BeginBookIndex.class).to(beginBookIndex);
        bindConstant().annotatedWith(PreferredWidth.class).to(600);
        bindConstant().annotatedWith(PreferredHeight.class).to(800);
    }
}
