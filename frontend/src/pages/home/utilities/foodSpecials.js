export const foodSpecials = (dishes) => {
	const template = dishes.filter((item) => {
		if (
			item.name === "Combo libra" ||
			item.name === "Tabla I" ||
			item.name === "Tabla II" ||
			item.name === "Tabla III"
		) {
			return item;
		}
	});

	return template;
};
