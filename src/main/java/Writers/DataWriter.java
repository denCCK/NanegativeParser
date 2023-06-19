package Writers;

import Models.Review;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class DataWriter {
    private final ArrayList<Review> reviews;

    private final String FONT = "OpenSans-Regular.ttf";

    public DataWriter(ArrayList<Review> reviews) {
        this.reviews = reviews;
    }

    public void saveReviewsToPdf(String path) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(path));
        } catch (DocumentException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        document.open();
        BaseFont baseFont;
        try {
            baseFont = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        } catch (DocumentException | IOException e) {
            throw new RuntimeException(e);
        }
        Font font = new Font(baseFont, 14, Font.NORMAL);

        for (int i = 0; i < reviews.size(); i++) {
            Review review = reviews.get(i);
            try {
                document.add(new Paragraph((i + 1) + ") Оценка: " + review.score(), font));
                document.add(new Paragraph("    Плюсы: " + review.benefits(), font));
                document.add(new Paragraph("    Минусы: " + review.disadvantages(), font));
                document.add(new Paragraph("    Отзыв: " + review.comment(), font));
            } catch (DocumentException e) {
                throw new RuntimeException(e);
            }
        }

        document.close();
    }
}
