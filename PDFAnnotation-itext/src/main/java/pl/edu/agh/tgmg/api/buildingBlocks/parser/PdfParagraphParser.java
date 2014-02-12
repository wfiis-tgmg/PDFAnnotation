package pl.edu.agh.tgmg.api.buildingBlocks.parser;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import pl.edu.agh.tgmg.api.CommonUtils;
import pl.edu.agh.tgmg.api.ParagraphElement;
import pl.edu.agh.tgmg.api.annotations.PdfMessageFeed;
import pl.edu.agh.tgmg.api.annotations.PdfParagraph;
import pl.edu.agh.tgmg.api.annotations.PdfParagraphs;
import pl.edu.agh.tgmg.api.exceptions.InvalidParagraphException;

public class PdfParagraphParser {
    
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
        for(String param : params) {
            try {
                Field field = root.getDeclaredField(param);
                if(!field.isAnnotationPresent(PdfMessageFeed.class)) {
                    throw new InvalidParagraphException("Field '" + param + 
                            "' from class " + root.getName() + " is not a MessageFeed"); 
                }
            } catch (NoSuchFieldException e) {
                throw new InvalidParagraphException("Message feed '" + param + 
                        "' does not exists in class " + root.getName());
            }
        }
    }
}
