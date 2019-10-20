package com.kafka.events.integration.resource;

public enum PaymentOperationStatus {
	PAYED,
    PAYMENT_FAILED;

    public static PaymentOperationStatus fromResult(boolean result) {
        return result ? PaymentOperationStatus.PAYED : PaymentOperationStatus.PAYMENT_FAILED;
    }
}
