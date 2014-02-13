package pl.edu.agh.tgmg.api.buildingBlocks.parser;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

import pl.edu.agh.tgmg.api.BlankI18nResolverImpl;
import pl.edu.agh.tgmg.api.DocumentStructureImpl;
import pl.edu.agh.tgmg.api.I18nResolver;
import pl.edu.agh.tgmg.api.PdfElement;
import pl.edu.agh.tgmg.api.annotations.PdfAfterDocument;
import pl.edu.agh.tgmg.api.annotations.PdfDocument;
import pl.edu.agh.tgmg.api.annotations.PdfFlowDataCell;
import pl.edu.agh.tgmg.api.annotations.PdfFlowTextCells;
import pl.edu.agh.tgmg.api.annotations.PdfParagraph;
import pl.edu.agh.tgmg.api.annotations.PdfParagraphs;
import pl.edu.agh.tgmg.api.annotations.PdfSignature;
import pl.edu.agh.tgmg.api.annotations.PdfTable;
import pl.edu.agh.tgmg.api.buildingBlocks.DocumentMetaData;
import pl.edu.agh.tgmg.api.buildingBlocks.DocumentStructure;
import pl.edu.agh.tgmg.api.exceptions.AnnotationParserException;
import pl.edu.agh.tgmg.api.exceptions.InvalidAnnotationException;
import pl.edu.agh.tgmg.itext.generators.buildingblocks.SingleDataTable;
import pl.edu.agh.tgmg.itext.generators.styles.StyleResolver;
import pl.edu.agh.tgmg.itext.generators.styles.StyleResolverImpl;

public class PdfAnnotationParserImpl implements PdfAnnotationParser {

    PdfTableParser tableParser;
    PdfParagraphParser paragraphParser;
    PdfMetadataParser metadataParser;
    PdfSignatureParser signatureParser;
    PdfFlowCellParser flowCellParser;

    public PdfAnnotationParserImpl() {
        this(new StyleResolverImpl());
    }

    public PdfAnnotationParserImpl(StyleResolver styleResolver) {
        this(styleResolver, new BlankI18nResolverImpl());
    }

    public PdfAnnotationParserImpl(StyleResolver styleResolver,I18nResolver i18nResolver) {

        tableParser = new PdfTableParser(styleResolver);
        paragraphParser = new PdfParagraphParser(styleResolver);
        metadataParser = new PdfMetadataParser(i18nResolver);
        signatureParser = new PdfSignatureParser(styleResolver);
        flowCellParser = new PdfFlowCellParser(styleResolver);
    }
    
    @Override
    public DocumentStructure parse(Class<?> root) throws AnnotationParserException {
        PdfDocument document = root.getAnnotation(PdfDocument.class);
        if(document == null) {
            throw new InvalidAnnotationException("Class " + root.getName() + " is not a PdfDocument");
        }
        
        DocumentMetaData metadata = metadataParser.parse(document);
        List<PdfElement> elements = new LinkedList<PdfElement>();
        
        Field[] fields = root.getDeclaredFields();
        for(int i=0;i<fields.length;i++) {
            Field field = fields[i];
            PdfSignature signature = field.getAnnotation(PdfSignature.class);
            if(signature != null) {
                elements.add(signatureParser.parse(signature, root));
            }
            PdfParagraph paragraph = field.getAnnotation(PdfParagraph.class);
            if(paragraph != null) {
                elements.add(paragraphParser.parse(paragraph, root));
            }
            PdfParagraphs paragraphs = field.getAnnotation(PdfParagraphs.class);
            if(paragraphs != null) {
                elements.addAll(paragraphParser.parse(paragraphs, root));
            }
            if(field.isAnnotationPresent(PdfTable.class)) {
                elements.add(tableParser.parseTable(field));
            } else if(field.isAnnotationPresent(PdfFlowTextCells.class) || 
                    field.isAnnotationPresent(PdfFlowDataCell.class)) {
                List<SingleDataTable> tables = flowCellParser.parse(root, i);
                i += flowCellParser.cellsRetrieved() - 1;
                elements.addAll(tables);
            }
        }
        
        PdfAfterDocument pdfAfter = root.getAnnotation(PdfAfterDocument.class);
        if(pdfAfter != null) {
            for(PdfParagraph paragraph : pdfAfter.paragraphs()) {
                elements.add(paragraphParser.parse(paragraph, root));
            }
            for(PdfSignature signature : pdfAfter.signatures()) {
                elements.add(signatureParser.parse(signature, root));
            }
        }
        
        return new DocumentStructureImpl(elements, metadata);
    }

}
