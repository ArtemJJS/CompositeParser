package by.anelkin.task2.parser;

import by.anelkin.task2.composite.Composite;
import by.anelkin.task2.reader.TextReader;
import org.apache.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.anelkin.task2.composite.Composite.ComponentType.*;


public class ParserText implements ParserComposite {
    private ParserComposite nextParser = new ParserParagraph();
    private static final Logger logger = Logger.getLogger(TextReader.class);

    @Override
    public Composite parse(String text) {
        Composite composite = new Composite(TEXT);
        if (text == null) {
            logger.warn("Incorrect text value (null)!");
            return composite;
        }
        Pattern pattern = Pattern.compile(PARAGRAPH.getREGEXP());
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            String paragraph = matcher.group();
            Composite compositeParagraph = nextParser.parse(paragraph);
            composite.add(compositeParagraph);
        }
        return composite;
    }

    public ParserComposite getNextParser() {
        return nextParser;
    }

    public void setNextParser(ParserComposite nextParser) {
        this.nextParser = nextParser;
    }
}
