package pl.edu.agh.tgmg.api.buildingBlocks.parser;

import pl.edu.agh.tgmg.api.annotations.styles.PdfBoxValues;
import pl.edu.agh.tgmg.itext.generators.styles.BoxValues;

public class PdfBoxValueParser {
    public BoxValues<Integer> parse(PdfBoxValues boxValue) {
        int a = boxValue.forAll();
        int b = boxValue.forBottom();
        int l = boxValue.forLeft();
        int r = boxValue.forRight();
        int t = boxValue.forTop();
        if(a >= 0 && b < 0 && l < 0 && r < 0 && t < 0) {
            return new BoxValues<Integer>(a);
        } else if(a < 0 && b >=0 && l >= 0 && r >= 0 && t >=0) {
            return new BoxValues<Integer>(b, l, r, t);
        }
        return new BoxValues<Integer>(a, b, l, r, t);
    }
}
