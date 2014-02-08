package pl.edu.agh.tgmg.api;

public interface PdfAnnotationParser {
    DocumentStructure parse(Class<? extends PdfContainer> root);
}
