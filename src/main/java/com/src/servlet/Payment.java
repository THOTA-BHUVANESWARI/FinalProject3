package com.src.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Payment")
public class Payment extends HttpServlet {

    private static final Pattern DIGITS = Pattern.compile("^\\d+$");

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String cardNumber = safe(req.getParameter("cardNumber"));
        String expiry = safe(req.getParameter("expiry"));
        String cvv = safe(req.getParameter("cvv"));
        String postal = safe(req.getParameter("postal"));

        String cardDigits = cardNumber.replaceAll("\\D", ""); // only digits
        boolean hasError = false;

        // 🔹 Validate card number
        if (cardDigits.length() < 13 || cardDigits.length() > 19
                || !DIGITS.matcher(cardDigits).matches()
                || !luhnCheck(cardDigits)) {
            req.setAttribute("cardError", "This card number is not valid.");
            hasError = true;
        }

        // 🔹 Validate expiry date (MM/YY)
        if (expiry == null || !expiry.matches("^\\d{2}/\\d{2}$")) {
            req.setAttribute("expiryError", "This expiration date is not valid.");
            hasError = true;
        } else {
            String[] parts = expiry.split("/");
            int mm = Integer.parseInt(parts[0]);
            int yy = Integer.parseInt(parts[1]);
            if (mm < 1 || mm > 12) {
                req.setAttribute("expiryError", "Invalid month.");
                hasError = true;
            } else {
                int fullYear = 2000 + yy;
                YearMonth cardYm = YearMonth.of(fullYear, mm);
                YearMonth nowYm = YearMonth.from(LocalDate.now());
                if (cardYm.isBefore(nowYm)) {
                    req.setAttribute("expiryError", "Card expired.");
                    hasError = true;
                }
            }
        }

        // 🔹 Card type and CVV validation
        String type = cardType(cardDigits);
        if ("AMEX".equals(type)) {
            if (cvv == null || !cvv.matches("^\\d{4}$")) {
                req.setAttribute("cvvError", "AMEX CVV must be 4 digits.");
                hasError = true;
            }
        } else {
            if (cvv == null || !cvv.matches("^\\d{3}$")) {
                req.setAttribute("cvvError", "CVV must be 3 digits.");
                hasError = true;
            }
        }

        // 🔹 Postal validation
        if (postal == null || postal.trim().isEmpty()) {
            req.setAttribute("postalError", "Postal code required.");
            hasError = true;
        }

        // 🔹 If any errors → back to form
        if (hasError) {
            req.setAttribute("cardNumber", cardNumber);
            req.setAttribute("expiry", expiry);
            req.setAttribute("cvv", cvv);
            req.setAttribute("postal", postal);
            req.getRequestDispatcher("/payment.jsp").forward(req, resp);
            return;
        }

        // ✅ Payment successful (demo only)
        req.setAttribute("maskedCard", maskCard(cardDigits));
        req.setAttribute("cardType", type);
        req.getRequestDispatcher("/success.jsp").forward(req, resp);
    }

    // Helper: safely trim strings
    private static String safe(String s) {
        return s == null ? "" : s.trim();
    }

    // Helper: detect card type by prefix
    private static String cardType(String digits) {
        if (digits.matches("^3[47].*")) return "AMEX";
        if (digits.matches("^4.*")) return "VISA";
        if (digits.matches("^5[1-5].*")) return "MASTERCARD";
        if (digits.matches("^6.*")) return "DISCOVER";
        return "UNKNOWN";
    }

    // Helper: mask card number
    private static String maskCard(String digits) {
        if (digits.length() <= 4) return digits;
        String last4 = digits.substring(digits.length() - 4);
        return "•••• •••• •••• " + last4;
    }

    // Helper: Luhn check
    private static boolean luhnCheck(String cardNumber) {
        int sum = 0;
        boolean alternate = false;
        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            int n = cardNumber.charAt(i) - '0';
            if (alternate) {
                n *= 2;
                if (n > 9) n -= 9;
            }
            sum += n;
            alternate = !alternate;
        }
        return (sum % 10) == 0;
    }
}
