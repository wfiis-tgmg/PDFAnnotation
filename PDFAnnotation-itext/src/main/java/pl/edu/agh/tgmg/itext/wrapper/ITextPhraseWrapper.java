package pl.edu.agh.tgmg.itext.wrapper;

import com.itextpdf.text.Phrase;
import pl.edu.agh.tgmg.api.buildingBlocks.parser.CellWrapper;

public class ITextPhraseWrapper implements CellWrapper {

    Phrase phrase;

    public ITextPhraseWrapper(Phrase phrase) {
        this.phrase = phrase;
    }

    @Override
    public Type getType() {
        return Type.PHRASE;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object getValue() {
        return phrase;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
