import { useEffect, useState } from "react";
import { useGetDishesQuery } from "../../../../redux/query/FoodInfo.query";

const useFilterPromotions = () => {
	const [list, setList] = useState([]);
	const { data: food, isSuccess } = useGetDishesQuery();

	useEffect(() => {
		if (isSuccess) {
			const { dishes } = food._embedded;
			setList(dishes.filter((item) => item.hasPromotion === true));
		}
	}, [isSuccess]);
	return {};
};

export default useFilterPromotions;
