package org.com.business.spring.boot.fw.paypay;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import jp.ne.paypay.ApiClient;
import jp.ne.paypay.ApiException;
import jp.ne.paypay.Configuration;
import jp.ne.paypay.api.PaymentApi;
import jp.ne.paypay.model.MoneyAmount;
import jp.ne.paypay.model.NotDataResponse;
import jp.ne.paypay.model.PaymentDetails;
import jp.ne.paypay.model.QRCode;
import jp.ne.paypay.model.QRCodeDetails;
import jp.ne.paypay.model.Refund;
import jp.ne.paypay.model.RefundDetails;

public class PayPayApiClient {

	// @value(${"ProductionMode"})
	private static final boolean PRODUCTION_MODE = false;
	private static final String API_KEY = "a_kHqsHMjNNp_NyBp";
	private static final String API_SECRET_KEY = "Uiu/dSGCUYCV18k7tTK7eevQu8590PHWKMWunKj+";
	private static final String ASSUME_MERCHANT = "DEVELOPER-PANEL-DEMO-c45d3843-1394-48c3-9da9-952125a00f15";

	/**
	 * クライアントをビルド
	 *
	 * @return
	 * @throws ApiException
	 */
	private ApiClient getApiClient() throws ApiException {
		// クライアントをビルドする
		ApiClient apiClient = new Configuration().getDefaultApiClient();
		// Set True for Production Environment. By Default this is set False for
		// Sandbox
		// Environment.
		apiClient.setProductionMode(PRODUCTION_MODE);
		apiClient.setApiKey(API_KEY);
		apiClient.setApiSecretKey(API_SECRET_KEY);
		apiClient.setAssumeMerchant(ASSUME_MERCHANT);
		return apiClient;
	}

	/**
	 * ウェブペイメント
	 *
	 * @param qrcodeCreatFlg
	 * @param delQRcode
	 * @return
	 * @throws ApiException
	 */
	public String payWebCreatQRCode(String merchantPaymentId)
			throws ApiException {

		// Creating the payload to create a QR Code, additional parameters can
		// be added
		// basis the API Documentation
		QRCode qrCode = new QRCode();
		qrCode.setAmount(new MoneyAmount().amount(10)
				.currency(MoneyAmount.CurrencyEnum.JPY));
		qrCode.setMerchantPaymentId(merchantPaymentId);
		qrCode.setCodeType("ORDER_QR");
		qrCode.setOrderDescription("Mune's Favourite Cake");
		qrCode.isAuthorization(false);
		qrCode.setRedirectUrl("https://paypay.ne.jp/");
		// For Deep Link, RedirectTypeEnum.APP_DEEP_LINK
		qrCode.setRedirectType(QRCode.RedirectTypeEnum.WEB_LINK);
		qrCode.setUserAgent(
				"Mozilla/5.0 (iPhone; CPU iPhone OS 10_3 like Mac OS X) AppleWebKit/602.1.50 (KHTML, like Gecko) CriOS/56.0.2924.75 Mobile/14E5239e Safari/602.1");

		// Calling the method to create a qr code
		PaymentApi apiInstance = new PaymentApi(getApiClient());
		QRCodeDetails response = apiInstance.createQRCode(qrCode);
		// Printing if the method call was SUCCESS
		System.out.println(response.getResultInfo().getCode());

		return response.getResultInfo().getCode();

	}

	/**
	 *
	 * @param delQRcode
	 * @return
	 * @throws ApiException
	 */
	public NotDataResponse payWebDelQRCode(String delQRcode)
			throws ApiException {

		// Delete a QR Code

		// Calling the method to delete a QR Code
		PaymentApi apiInstance = new PaymentApi(getApiClient());
		NotDataResponse response = apiInstance.deleteQRCode(delQRcode);
		// Printing if the method call was SUCCESS
		System.out.println(response.getResultInfo().getCode());

		return response;

	}

	/**
	 * Get Payment Details
	 *
	 * @param delQRcode
	 * @return
	 * @throws ApiException
	 */
	public PaymentDetails payWebGetPaymentDetail(String paymentId)
			throws ApiException {

		// Get Payment Details

		// Calling the method to get payment details
		PaymentApi apiInstance = new PaymentApi(getApiClient());
		PaymentDetails response = apiInstance.getCodesPaymentDetails(paymentId);
		// Printing if the method call was SUCCESS, this does not mean the
		// payment was a
		// success
		System.out.println(response.getResultInfo().getCode());
		// Printing if the transaction status for the code has COMPLETED/
		// AUTHORIZED
		System.out.println(response.getData().getStatus());
		return response;
	}

	/**
	 * Calling the method to get payment details
	 *
	 * @param delQRcode
	 * @return
	 * @throws ApiException
	 */
	public PaymentDetails payWebGetCallPaymentDetail(String paymentId)
			throws ApiException {

		// Calling the method to get payment details
		PaymentApi apiInstance = new PaymentApi(getApiClient());
		PaymentDetails response = apiInstance.getCodesPaymentDetails(paymentId);
		// Printing if the method call was SUCCESS, this does not mean the
		// payment was a
		// success
		System.out.println(response.getResultInfo().getCode());
		// Printing if the transaction status for the code has COMPLETED/
		// AUTHORIZED
		System.out.println(response.getData().getStatus());
		return response;

	}

	public RefundDetails payWebRefundDetails(String merchantRefundId,
			String paymentId) throws ApiException {

		// 決済が正常に完了しユーザーへの商品の提供後に、返品する場合にRefund a paymentを使用
		// Creating the payload to refund a Payment, additional parameters can
		// be added
		// basis the API Documentation
		Refund refund = new Refund();
		refund.setAmount(new MoneyAmount().amount(1)
				.currency(MoneyAmount.CurrencyEnum.JPY));
		refund.setMerchantRefundId(merchantRefundId);
		refund.setPaymentId(paymentId);
		refund.setRequestedAt(
				LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
		refund.setReason("reason for refund");

		// Calling the method to refund a Payment
		PaymentApi apiInstance = new PaymentApi(getApiClient());
		RefundDetails reFundresponse = apiInstance.refundPayment(refund);
		// Printing if the method call was SUCCESS
		System.out.println(reFundresponse.getResultInfo().getCode());
		return reFundresponse;
	}

}
