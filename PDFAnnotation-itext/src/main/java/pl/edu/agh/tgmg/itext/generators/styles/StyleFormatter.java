package pl.edu.agh.tgmg.itext.generators.styles;

import com.itextpdf.text.Element;

public interface StyleFormatter <T extends Element>{
    void addStyle(T object);
}
