class Item {
	constructor(
		id = 0,
		amount = 0,
		name = "",
		cost = 0,
		subTotal = 0,
		ShippingCost = "",
		DiscountCode = "",
	) {
		this.id = id;
		this.amount = amount;
		this.name = name;
		this.cost = cost;
		this.subTotal = subTotal;
		this.ShippingCost = ShippingCost;
		this.DiscountCode = DiscountCode;
	}
}

export default Item;
