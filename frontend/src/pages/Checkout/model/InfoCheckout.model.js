class InfoCheckout {
	constructor(
		type = "DEBIT",
		number = "",
		holder = "local",
		expirationDate = "",
		cvv = "",
	) {
		this.type = type;
		this.number = number;
		this.holder = holder;
		this.expirationDate = expirationDate;
		this.cvv = cvv;
	}
}
export default InfoCheckout;
