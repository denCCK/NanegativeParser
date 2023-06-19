import Models.Review;
import Parsers.NanegativeParser;
import Writers.DataWriter;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите URL с сайта Nanegative.ru:");

        Scanner scanner = new Scanner(System.in);
        String url = scanner.nextLine();

        NanegativeParser parser = new NanegativeParser(url);
        ArrayList<Review> allReviews = parser.parse();

        DataWriter writer = new DataWriter(allReviews);
        writer.saveReviewsToPdf("reviews.pdf");
    }
}