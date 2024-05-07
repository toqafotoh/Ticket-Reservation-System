package com.project.ticketreservation.paypal;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.project.ticketreservation.models.Account;
import com.project.ticketreservation.models.PaymentModel;
import com.project.ticketreservation.services.PaymentService;

@Controller
public class PaypalController {

    @Autowired
    private PaypalService paypalService;
    Double amountt;

    @Autowired
    private PaymentService ps;

    public static String payid;

    PaymentModel model;

    @GetMapping("templates/paymentMain.html")
    public String home() {
        return "paymentMain";
    }

    @PostMapping("/payment/create")
    public RedirectView createPayment(
            @RequestParam("method") String method,
            @RequestParam("amount") String amount,
            @RequestParam("currency") String currency,
            @RequestParam("description") String description) {
        try {
            String cancelUrl = "http://localhost:9090/payment/cancel";
            String successUrl = "http://localhost:9090/payment/success";
            Payment payment = paypalService.createPayment(
                    Double.valueOf(amount),
                    currency,
                    method,
                    "sale",
                    description,
                    cancelUrl,
                    successUrl);
            amountt = Double.valueOf(amount);

            for (Links links : payment.getLinks()) {
                if (links.getRel().equals("approval_url")) {
                    return new RedirectView(links.getHref());
                }
            }
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }
        return new RedirectView("/payment/error");
    }

    @GetMapping("payment/success")
    public String paymentSuccess(
            @RequestParam("paymentId") String paymentId,
            @RequestParam("PayerID") String payerId) {
        try {
            Payment payment = paypalService.executePayment(paymentId, payerId);
            if (payment.getState().equals("approved")) {
                model = new PaymentModel(paymentId, Double.valueOf(amountt), true );
                ps.createPaymmentDB(model); // done
                ps.setTicketPaymentIDAndprice(paymentId, Double.valueOf(amountt));
                payid = paymentId;
                return "paymentSuccess";
            }

        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }
        return "paymentSuccess";
    }

    // @GetMapping("getpayid")
    // public String getpayid() {
    // return payid ;
    // }

    @GetMapping("/payment/cancel")
    public String paymentCancel() {
        return "paymentCancel";
    }

    @GetMapping("/payment/error")
    public String paymentError() {
        return "paymentError";
    }
}
