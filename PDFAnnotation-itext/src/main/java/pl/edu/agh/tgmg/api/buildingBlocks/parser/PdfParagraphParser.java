package pl.edu.agh.tgmg.api.buildingBlocks.parser;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import pl.edu.agh.tgmg.api.CommonUtils;
import pl.edu.agh.tgmg.api.annotations.PdfParagraph;
import pl.edu.agh.tgmg.api.annotations.PdfParagraphs;
import pl.edu.agh.tgmg.api.exceptions.InvalidAnnotationException;
import pl.edu.agh.tgmg.api.exceptions.InvalidParagraphException;
import pl.edu.agh.tgmg.api.exceptions.ReflectionException;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.ParagraphElement;
import pl.edu.agh.tgmg.itext.generators.styles.StyleResolver;

public class PdfParagraphParser {
    
    private StyleResolver styleRepository = new StyleResolver();
    
    public PdfParagraphParser() {}
    
    public PdfParagraphParser(StyleResolver styleRepository) {
        this.styleRepository = styleRepository;
    }

    public List<ParagraphElement> parse(PdfParagraphs paragraphs, Class<?> root) throws InvalidParagraphException {
        List<ParagraphElement> result = new LinkedList<ParagraphElement>();
        for(PdfParagraph paragraph : paragraphs.value()) {
            result.add(parse(paragraph, root));
        }
        return result;
    }
    
    public ParagraphElement parse(PdfParagraph paragraph, Class<?> root) throws InvalidParagraphException {
        List<String> params = Arrays.asList(paragraph.messageFieldNames());
        String text = CommonUtils.processText(paragraph.value(), paragraph.value());
        checkParams(params, root);
        return new ParagraphElement(params, text);
    }
    
    private void checkParams(List<String> params, Class<?> root) throws InvalidParagraphException {
        try {
            for(String param : params) {
                CommonUtils.checkFieldMessageFeed(param, root);
            }
        } catch (InvalidAnnotationException | ReflectionException e) {
            throw new InvalidParagraphException(e);
        }
    }
}
