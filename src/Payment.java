interface Payment {
    double calculateTransactionFee(double amount);
}

class WalletPayment implements Payment {
    @Override
    public double calculateTransactionFee(double amount) {
        return 0.0;
    }
}

class BankcardPayment implements Payment {
    private static final double bankCardFeePercentage = 5.0;

    @Override
    public double calculateTransactionFee(double amount) {
        return amount * (bankCardFeePercentage / 100.0);
    }
}

class VisaPayment implements Payment {
    private static final double visaFeePercentage = 2.0;

    @Override
    public double calculateTransactionFee(double amount) {
        return amount * (visaFeePercentage / 100.0);
    }
}

class MastercardPayment implements Payment {
    private static final double mastercardFeePercentage = 3.0;

    @Override
    public double calculateTransactionFee(double amount) {
        return amount * (mastercardFeePercentage / 100.0);
    }
}

class OtherPayment implements Payment {
    private static final double otherPaymentFeePercentage = 10.0;

    @Override
    public double calculateTransactionFee(double amount) {
        return amount * (otherPaymentFeePercentage / 100.0);
    }
}

class PaymentContext {
    private Payment paymentStrategy;

    public PaymentContext(Payment paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public double getPaymentFee(double amount) {
        double transactionFee = paymentStrategy.calculateTransactionFee(amount);

        return transactionFee;
    }
}
