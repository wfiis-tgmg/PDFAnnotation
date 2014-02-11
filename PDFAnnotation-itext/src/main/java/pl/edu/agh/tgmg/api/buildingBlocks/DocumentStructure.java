package pl.edu.agh.tgmg.api.buildingBlocks;

import java.util.List;

import pl.edu.agh.tgmg.api.PdfElement;

public interface DocumentStructure {

    List<? extends PdfElement> getPdfElements();
    DocumentMetaData getMetaData();
}
