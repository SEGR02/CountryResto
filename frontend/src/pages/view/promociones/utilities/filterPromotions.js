export const filterPromotions = (dishes) => {
	const template = dishes.filter((item) => item.hasPromotion === true);
	return template;
};
