package pl.edu.agh.tgmg.api;

public @interface PdfColumn {
    int order() default Integer.MAX_VALUE;

    String name() default "";
}
