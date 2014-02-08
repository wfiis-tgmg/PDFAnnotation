package pl.edu.agh.tgmg.api.annotations;

public @interface PdfColumn {
    int order() default Integer.MAX_VALUE;

    String name() default "";
}
