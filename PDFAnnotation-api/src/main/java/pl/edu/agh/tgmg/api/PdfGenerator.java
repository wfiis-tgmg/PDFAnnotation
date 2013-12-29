package pl.edu.agh.tgmg.api;

import java.io.OutputStream;
import java.util.List;

public interface PdfGenerator {
    void generate(OutputStream out, List<PdfContainer> dto);
}
